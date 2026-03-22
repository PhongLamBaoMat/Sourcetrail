# Sourcetrail

*__Lưu ý:__ Đây là dự án tiếp tục duy trì từ dự án [CoatiSoftware/Sourcetrail](https://github.com/CoatiSoftware/Sourcetrail) đã bị dừng phát triển từ năm 2021.*

Sourcetrail là một phần mềm mã nguồn mở hỗ trợ việc đọc hiểu nhanh các mã nguồn được viết bằng C, C++, Python và Java.

!["Giao diện Sourcetrail"](docs/readme/user_interface.png "Giao diện Sourcetrail")

Sourcetrail là phần mềm:
* Miễn phí
* Hoạt động không cần mạng internet
* Chạy trên các hệ điều hành Windows, MacOS và Linux
* Hỗ trợ phân tích mã nguồn ngôn ngữ C, C++, Java và Python

## Hướng dẫn sử dụng

Để cài đặt Sourcetrail trên máy, bạn có thể tải bản cài đặt phù hợp với hệ điều hành trên máy tại [Releases](https://github.com/PhongLamBaoMat/Sourcetrail/releases) và tiến hành cài đặt.

Sau khi hoàn thành tiến trình cài đặt, làm theo [Hướng dẫn sử dụng](DOCUMENTATION.md#hướng-dẫn-sử-dụng) để làm quen với Sourcetrail.

## Báo cáo lỗi

Bạn có thể báo cáo lỗi bạn gặp ở mục [Issues](https://github.com/PhongLamBaoMat/Sourcetrail/issues).

## Đóng góp phát triển

* Vui lòng đọc và làm theo hướng dẫn ở tập tin [CONTRIBUTING.md](CONTRIBUTING.md).
* Bạn có thể tìm và xem những lỗi có gán nhãn [good first issue](https://github.com/PhongLamBaoMat/Sourcetrail/issues?q=is%3Aopen+is%3Aissue+label%3A%22good+first+issue%22) để bắt việc đóng góp.

# Hướng dẫn biên dịch dự án

Biên dịch Sourcetrail cần cài thêm các thư viện bên ngoài. Tuy nhiên, chúng tôi đã sử dụng `vcpkg` để việc quản lý và cài đặt các thư viện này trở nên dễ dàng hơn.

Sourcetrail là một bộ công cụ bao gồm ứng dụng cơ sở và các trình phân tích mã nguồn theo ngôn ngữ lập trình được hỗ trợ.

## Biên dịch ứng dụng cơ sở

### Trên MacOS

1. Cài trình quản lý thư viện `vcpkg`
```sh
brew install vcpkg
git clone https://github.com/microsoft/vcpkg.git "$HOME/vcpkg"
export VCPKG_ROOT="$HOME/vcpkg"
```
Bạn có thể tham khảo thêm về cách sử dụng `vcpkg` ở [Learn vcpkg](https://learn.microsoft.com/en-us/vcpkg/get_started/get-started)

2. Cài đặt thư viện, các công cụ hỗ trợ và tải mã nguồn dự án
```sh
brew install qt@5 cmake ninja imagemagick
git clone https://github.com/PhongLamBaoMat/Sourcetrail
```

3. Khởi tạo môi trường biên dịch ứng dụng cơ sở
```sh
cd Sourcetrail
cmake --preset=release -DQt5_DIR=$(brew --prefix qt@5)/lib/cmake/Qt5
```

4. Tiến hành lênh biên dịch
```sh
cd build/release
ninja Sourcetrail
```

### Trên Linux
- Cần được hoàn thiện hướng dẫn.

### Trên Windows
- Cần được hoàn thiện hướng dẫn.

## Biên dịch trình phân tích mã nguồn C/C++
### Yêu cầu
- __LLVM/Clang__ từ phiên bản 21.1.8 trở lên
  * __Lý do__: sử dụng để xử lý, phân tích mã nguồn C/C++, thiết lập và duyệt cây cú pháp trừu tượng (Abstract Syntax Tree) và báo lỗi.
  * __Cài đặt trên MacOS__: chạy lệnh `brew install llvm@21`.
  * __Cài đặt trên Linux__: cần được hoàn thiện hướng dẫn.
  * __Cài đặt trên Windows__: cần được hoàn thiện hướng dẫn.
  
### Biên dịch
Thêm tham số cho lúc chạy CMake ở bước 3 - Khởi tạo môi trường biên dịch ứng dụng cở sở:
  ```
  -DClang_DIR=<path/to/llvm_build>/lib/cmake/clang
  -DBUILD_CXX_LANGUAGE_PACKAGE=ON
  ```

## Biên dịch trình phân tích mã nguồn Java
### Yêu cầu
- __JDK__ từ phiên bản 25 trở lên
  * __Lý do__: sử dụng để gọi thư viện Java từ C++ thông qua JNI.
  * __Lưu ý__: chắc chắn giá trị `<jdk_root>/bin` đã tồn tại trong biến môi trường `PATH` và biến `JAVA_HOME` cũng đã được thiết lập.

- __Maven__
  * __Lý do__: dùng để chạy các mẫu kiểm tra tự động của Sourcetrail
  * __Lưu ý__: chắc chắn giá trị `.../apache-maven-x.x.x./bin` đã tồn tại trong biến môi trường `PATH` và biến `M2_HOME` và `MAVEN_HOME` cũng đã được thiết lập.

### Biên dịch
Thêm tham số cho lúc chạy CMake ở bước 3 - Khởi tạo môi trường biên dịch ứng dụng cở sở:
  ```
  -DBUILD_JAVA_LANGUAGE_PACKAGE=ON
  ```
  
## Biên dịch trình phân tích mã nguồn Python
### Biên dịch
Thêm tham số cho lúc chạy CMake ở bước 3 - Khởi tạo môi trường biên dịch ứng dụng cở sở:
  ```
  -DBUILD_PYTHON_LANGUAGE_PACKAGE=ON
  ```

## Đóng gói ứng dụng

### Trên MacOS
Sau khi cài đặt ứng dụng thành công, bạn chỉ cần chạy tập tin `bundle_install.sh` ở trong thu mục `Sourcetrail/build/release/app`. Tập tin sẽ tạo ra một tập tin đóng gói mới có tên là `Sourcetrail_<phiên-bản>.dmg`.

### Trên Linux
- Cần hoàn thiện hướng dẫn.

### Trên Windows
- Cần hoàn thiện hướng dẫn.

# Chạy các bộ kiểm tra
- Cần hoàn thiện hướng dẫn.

# Giấy phép
Sourcetrail cấp phép theo [GNU General Public License Version 3](LICENSE.txt).

# Nhãn hiệu
Tên "Sourcetrail" là nhãn hiệu thuộc về Coati Software và không nằm trong những tài sản thuộc bản quyền GNU GPLv3.
