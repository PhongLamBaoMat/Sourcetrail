#include "utilityApp.h"

#include <boost/asio/readable_pipe.hpp>
#include <boost/filesystem/file_status.hpp>
#include <boost/process/v2/environment.hpp>
#include <boost/process/v2/process.hpp>
#include <boost/process/v2/start_dir.hpp>
#include <boost/process/v2/stdio.hpp>
#include <boost/system/detail/error_code.hpp>
#include <chrono>
#include <memory>
#include <mutex>
#include <set>

#include <boost/asio/buffer.hpp>
#include <boost/asio/cancel_after.hpp>
#include <boost/asio/io_context.hpp>
#include <boost/asio/read.hpp>
#include <boost/process.hpp>

#include <QThread>

#include "ApplicationArchitectureType.h"
#include "ScopedFunctor.h"
#include "logging.h"
#include "utilityString.h"

namespace utility
{
std::mutex s_runningProcessesMutex;
std::set<std::shared_ptr<boost::process::process>> s_runningProcesses;
}	 // namespace utility

std::string utility::getDocumentationLink()
{
	return "https://github.com/CoatiSoftware/Sourcetrail/blob/master/DOCUMENTATION.md";
}

std::wstring utility::searchPath(const std::wstring& exe, bool& ok)
{
	auto path = boost::process::environment::find_executable(exe);
	if (path.empty())
	{
		ok = false;
	}
	else
	{
		ok = true;
	}
	return path.wstring();
}

utility::ProcessOutput utility::executeProcess(
	const std::wstring& command,
	const std::vector<std::wstring>& arguments,
	const FilePath& workingDirectory,
	const bool waitUntilNoOutput,
	const int timeout,
	bool logProcessOutput)
{
	std::string output = "";
	int exitCode = 255;

	try
	{
		boost::asio::io_context ctx;
		boost::asio::readable_pipe rp {ctx};
		std::shared_ptr<boost::process::process> process;

		std::unordered_map<boost::process::environment::key, boost::process::environment::value> env;
		for (const auto& kv: boost::process::environment::current())
		{
			if (kv.key().string() == "PATH")
			{
				env[kv.key()] = kv.value().string() + ":/opt/local/bin:/usr/local/bin:$HOME/bin";
			}
			else
			{
				env[kv.key()] = kv.value();
			}
		}
		std::wstring executable_file = command;
		if (!boost::filesystem::is_regular_file(executable_file))
		{
			executable_file = boost::process::environment::find_executable(command).wstring();
		}

		if (workingDirectory.empty())
		{
			process = std::make_shared<boost::process::process>(
				ctx,
				executable_file,
				arguments,
				boost::process::process_environment(env),
				boost::process::process_stdio {.out = rp, .err = rp});
		}
		else
		{
			process = std::make_shared<boost::process::process>(
				ctx,
				executable_file,
				arguments,
				boost::process::process_start_dir(workingDirectory.wstr()),
				boost::process::process_environment(env),
				boost::process::process_stdio {.out = rp, .err = rp});
		}

		{
			std::lock_guard<std::mutex> lock(s_runningProcessesMutex);
			s_runningProcesses.insert(process);
		}

		ScopedFunctor remover(
			[process]()
			{
				std::lock_guard<std::mutex> lock(s_runningProcessesMutex);
				s_runningProcesses.erase(process);
			});

		bool outputReceived = false;
		std::vector<char> buf(128);
		auto stdOutBuffer = boost::asio::buffer(buf);
		std::string logBuffer;

		std::function<void(const boost::system::error_code& ec, std::size_t n)> onStdOut =
			[&output, &buf, &stdOutBuffer, &rp, &onStdOut, &outputReceived, &logBuffer, logProcessOutput](
				const boost::system::error_code& ec, std::size_t size)
		{
			std::string text;
			text.reserve(size);
			text.insert(text.end(), buf.begin(), buf.begin() + size);

			if (!text.empty())
			{
				outputReceived = true;
			}

			output += text;
			if (logProcessOutput)
			{
				logBuffer += text;
				const bool isEndOfLine = (logBuffer.back() == '\n');
				const std::vector<std::string> lines = utility::splitToVector(logBuffer, "\n");
				for (size_t i = 0; i < lines.size() - (isEndOfLine ? 0 : 1); i++)
				{
					LOG_INFO_BARE("Process output: " + lines[i]);
				}
				if (isEndOfLine)
				{
					logBuffer.clear();
				}
				else
				{
					logBuffer = lines.back();
				}
			}
			if (!ec)
			{
				boost::asio::async_read(rp, stdOutBuffer, onStdOut);
			}
		};

		boost::asio::async_read(rp, stdOutBuffer, onStdOut);
		ctx.run();

		if (timeout > 0)
		{
			if (waitUntilNoOutput)
			{
				while (process->running())
				{
					process->async_wait(
						boost::asio::cancel_after(
							std::chrono::milliseconds(timeout),
							[&](const boost::system::error_code ec, std::size_t) {}));
					if (!outputReceived)
					{
						LOG_WARNING(
							"Canceling process because it did not generate any output during the "
							"last " +
							std::to_string(timeout / 1000) + " seconds.");
						process->terminate();
						break;
					}
					outputReceived = false;
				}
			}
			else
			{
				process->async_wait(
					boost::asio::cancel_after(
						std::chrono::milliseconds(timeout),
						[&](const boost::system::error_code ec, std::size_t) {}));
				if (process->running())
				{
					LOG_WARNING(
						"Canceling process because it timed out after " +
						std::to_string(timeout / 1000) + " seconds.");
					process->terminate();
				}
			}
		}
		else
		{
			process->wait();
		}

		if (logProcessOutput)
		{
			for (const std::string& line: utility::splitToVector(logBuffer, "\n"))
			{
				LOG_INFO_BARE("Process output: " + line);
			}
		}

		exitCode = process->exit_code();
	}
	catch (const boost::system::system_error& e)
	{
		ProcessOutput ret;
		ret.error = utility::decodeFromUtf8(e.code().message()) + L" '" + command + L"'";
		ret.exitCode = e.code().value();
		LOG_ERROR_BARE(L"Process error: " + ret.error);

		return ret;
	}


	ProcessOutput ret;
	ret.output = utility::trim(utility::decodeFromUtf8(output));
	ret.exitCode = exitCode;
	return ret;
}


void utility::killRunningProcesses()
{
	std::lock_guard<std::mutex> lock(s_runningProcessesMutex);
	for (auto process: s_runningProcesses)
	{
		process->terminate();
	}
}

int utility::getIdealThreadCount()
{
	int threadCount = QThread::idealThreadCount();
	if (getOsType() == OS_WINDOWS)
	{
		threadCount -= 1;
	}
	return std::max(1, threadCount);
}

std::string utility::getOsTypeString()
{
	// WARNING: Don't change these string. The server API relies on them.
	switch (utility::getOsType())
	{
	case OS_WINDOWS:
		return "windows";
	case OS_MAC:
		return "macOS";
	case OS_LINUX:
		return "linux";
	default:
		break;
	}
	return "unknown";
}

std::string utility::getApplicationArchitectureString()
{
	switch (utility::getApplicationArchitectureType())
	{
	case APPLICATION_ARCHITECTURE_X86_32:
		return "x86";
	case APPLICATION_ARCHITECTURE_X86_64:
		return "amd64";
	case APPLICATION_ARCHITECTURE_ARM_64:
		return "arm64";
	default:
		return "Unknown";
	}
}

std::wstring utility::getApplicationArchitectureWstring()
{
	switch (utility::getApplicationArchitectureType())
	{
	case APPLICATION_ARCHITECTURE_X86_32:
		return L"x86";
	case APPLICATION_ARCHITECTURE_X86_64:
		return L"amd64";
	case APPLICATION_ARCHITECTURE_ARM_64:
		return L"arm64";
	default:
		return L"Unknown";
	}
}
