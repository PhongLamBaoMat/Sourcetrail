<img src="docs/documentation/logo.png" height="250" alt="Sourcetrail">

Tài liệu hướng dẫn cho phiên bản 2021.4

# Lời chào

Đây là tài liệu được dịch lại từ [phiên bản tiếng Anh](DOCUMENTATION.en.md). Tài liệu sẽ giúp bạn hiểu hơn về cách sử dụng Sourcetrail.

## Tổng quan

Sourcetrail là phần mềm hỗ trợ tương tác, đọc hiểu mã nguồn bằng cách phân tích và minh hoạ cấu trúc được khai báo trong mã nguồn. Phần mềm cung cấp một giao diện đơn giản gồm ba thành phần:

<img src="docs/documentation/concept.png" height="400" alt="Sourcetrail Concept">

- **Search (tìm kiếm)**: sử dụng thanh tìm kiếm để tìm và chọn các tên có trong mã nguồn.
- **Graph (đồ thị)**: đồ thị minh hoạ cấu trúc của mã nguồn. Nó tập trung vào minh hoạ mối quan hệ của tên được chọn với những tên tên khác.
- **Code (mã nguồn)**: mã nguồn hiển thị danh sách các đoạn mã chứa tên được chọn. Nhấn chọn vào vị trí của một đoạn mã sẽ hiển thị bạn đến toàn bộ của tập tin chứa đoán mã đó.

Lưu ý: Sourcetrail hiện tải chỉ hỗ trợ phân tích ngôn ngữ C/C++, Java và Python.

## Ngôn ngữ hỗ trợ

### C/C++
C/C++ được hỗ trợ bởi [Clang 21.1.8](https://clang.llvm.org/). Mọi vấn đề liên quan đến việc phân tích mã nguồn C/C++, vui lòng kiểm tra qua [Clang language compatibility](https://clang.llvm.org/compatibility.html) hoặc gửi báo cáo lỗi đến chúng tôi tại [Issues](https://github.com/PhongLamBaoMat/Sourcetrail/issues).

### Java
Sourcetrail hỗ trợ Java 25 và các phiên bản thấp hơn bởi [Eclipse JDT](https://github.com/eclipse/eclipse.jdt.core). Nếu bạn gặp bất kì lỗi nào, vui lòng gửi báo cáo đến chúng tôi tại [Issues](https://github.com/PhongLamBaoMat/Sourcetrail/issues).

### Python
Sourcetrail hỗ trợ cả Python 2 và 3 bởi [CoatiSoftware SourcetrailPythonIndexer](https://github.com/CoatiSoftware/SourcetrailPythonIndexer). Hiện CoatiSoftware đã dừng phát triển nên chúng tôi không thể hỗ trợ thêm.

# Hướng dẫn sử dụng

Phần này sẽ giúp bạn hiểu hơn về khởi tạo dự án và giao diện sử dụng của Sourcetrail. Danh sách gạch đầu dòng sẽ cung cấp cho bạn các bước tiếp theo cần thực hiện:

## Khởi động Sourcetrail

Sau khi [đã tải](https://github.com/PhongLamBaoMat/Sourcetrail/releases) Sourcetrail thành công. Bạn có thể chạy ứng dụng.

> **Công việc**
> * Chạy Sourcetrail.

Sau khi chạy Sourcetrail, bạn sẽ thấy [Cửa sổ bắt đầu](#cửa-sổ-bắt-đầu). Từ đây bạn có thể tạo dự án riêng của bạn hoặc chọn dự án đã có sẵn.

> **Công việc**
> * Chọn **New Project** để tạo một dự án mới.
> * hoặc chọn một từ **Recent Projects** _(ví dụ: TicTacToe)_ và tiếp tục với [cửa sổ bắt đầu](#cửa-sổ-bắt-đầu)

<img src="docs/documentation/start_window.png" height="600" alt="Start Window">
    
## Tạo một dự án mới

Khi tạo một dự án Sourcetrail mới, bạn sẽ dùng [trình thiết lập dự án](#trình-thiết-lập-dự-án). Quá trình thiết lập được chia thành nhiều bước. Tuỳ thuộc vào cấu trúc dự án và hệ thống xây dựng được sử dụng, sẽ có các loại thiết lập khác nhau. Chọn đúng phương thức thiết lại rất quan trọng và có thể khiến quá trình trở nên dễ dàng hơn.

> **Công việc**
> * Điền tên dự án vào **Name** và chọn **Location** cho vị trí lưu dữ án.
> * Chọn **Add Source Group** to thêm các tập tin mã nguồn vào dự án.

<img src="docs/documentation/project_setup_wizard_start.png" height="500" alt="Project Setup Wizard Start">

## Thêm Nhóm mã nguồn (Source Group)

Các dự án Sourcetrail chứa nhiều *Nhóm mã nguồn*. Mỗi Nhóm mã nguồn sử dụng một ngôn ngữ nhất định, tập hợp các tập tin, các cấu hình để phân tích những tập tin đấy. Mỗi ngôn ngữ lập trình được hỗ trợ lại có nhiều loại Nhóm mã nguồn khác nhau. Ngoài ra, việc tạo một Nhóm mã nguồn duy nhất là đủ cho hầu hết các dự án.

Hãy cuộn xuống hình ảnh phía dưới để xem hướng dẫn chi tiết về cách thiết lập.

> **Công việc**
> * Hãy chọn loại thiết lập **Nhóm mã nguồn** mà bạn muốn và quay lại đây ngay sau khi dự án được tạo.

<img src="docs/documentation/project_setup_wizard_source_group_type.png" height="600" alt="Project Setup Wizard Source Group Type">

#### Thiết lập Nhóm mã nguồn cho C/C++

Các kiểu thiết lập Nhóm mã nguồn cho C và C++ đều giống nhau.

**Bạn đang sử dụng CMake, Make hoặc Qt Creator?**

Nếu bạn đang sử dụng [CMake](https://cmake.org) hoặc [Make](https://www.gnu.org/software/make/) để xây môi trường, bạn có thể xuất ra tập tin `compile_commands.json` là [clang JSON Compilation Database](https://clang.llvm.org/docs/JSONCompilationDatabase.html). Cơ sở dữ liệu biên dịch chứa tất cả thông tin cần thiết để xây dựng dự án, chẳng hạn như tập tin mã nguồn, bao gồm đường dẫn và cờ trình biên dịch. Việc có Cơ sở dữ liệu tổng hợp giúp việc thiết lập dự án trong Sourcetrail dễ dàng hơn rất nhiều. Chúng tôi khuyên bạn nên sử dụng phương pháp này nếu có thể.

Tạo một Cơ sở dữ liệu biên dịch:
- Thêm cờ `CMAKE_EXPORT_COMPILE_COMMANDS=On` nếu bạn sử dụng **CMake**. (Không hỗ trợ trên Visual Studio)
- Đối với dự án **Make** sử dụng [Bear](https://github.com/rizsotto/Bear). Bear tạo một tập tin `compile_commands.json` trong quá trình giả lập biên dịch. Bear đã được thử trên FreeBSD, GNU/Linux và OS X.
- Chọn "Generate Compilation Database" từ menu "Build" nếu bạn sử dụng **Qt Creator since version 4.8**

Nếu bạn có thể tạo tập tin `compile_commands.json`, vui lòng tiếp tục đọc [Tạo Nhóm mã nguồn từ Cơ sở dữ liệu biên dịch](#cc-from-compilation-database) và trở về sau khi bạn đã hoàn thành việc tạo dự án.

<img src="docs/documentation/project_setup_wizard_start_cdb.png" height="600" alt="Project Setup Wizard Start CDB">

**Bạn đang sử dụng Visual Studio?**

Nếu bạn đang sử dụng Visual Studio, bạn có thể đọc [tạo Nhóm mã nguồn từ Visual Studio](#tạo-nhóm-mã-nguồn-từ-visual-studio)

<img src="docs/documentation/project_setup_wizard_start_vs.png" height="600" alt="Project Setup Wizard Start VS">

**Tạo dự án trống**

Nếu bạn không thử sử dụng các cách ở trên để tạo dự án, bạn có thể đọc [tạo một Nhóm mã nguồn C/C++ trống](#empty-cc-source-group) và trở lại đây nếu dự án đã được tạo.

<img src="docs/documentation/project_setup_wizard_source_group_type.png" height="600" alt="Project Setup Wizard Source Group Type">

#### Thiết lập Nhóm mã nguồn cho Java

**Bạn đang sử dụng Gradle?**

Nếu bạn đang sử dụng Gradle, bạn có thể đọc [tạo Nhóm mã nguồn từ Gradle](#java-source-group-from-gradle) để tự động thiết lập Nhóm mã nguồn cho dự án của bạn.

<img src="docs/documentation/project_setup_wizard_start_java_gradle.png" height="600" alt="Project Setup Wizard Start Java Gradle">

**Bạn đang sử dụng Maven?**

Nếu bạn đang sử dụng Maven, vui lòng đọc [tạo Nhóm mã nguồn từ Maven](#java-source-group-from-maven) và quay lại đây khi dự án đã được tạo.

<img src="docs/documentation/project_setup_wizard_start_java_maven.png" height="600" alt="Project Setup Wizard Start Java Maven">

**Tạo dự án trống**

Nếu dự án của bạn không sử dụng Gradle hoặc Maven, vui lòng đọc [tạo Nhóm mã nguồn Java trống](#empty-java-source-group) và quay lại đây khi dự án đã được tạo.

<img src="docs/documentation/project_setup_wizard_start_java_empty.png" height="600" alt="Project Setup Wizard Start Java Empty">

#### Thiết lập Nhóm mã nguồn cho Python

**Tạo dự án trống**

Nếu bạn muốn phân tích mã nguồn Python bằng Sourcetrail, vui lòng đọc [tạo Nhóm mã nguồn Python trống](#empty-python-source-group) và trở lại đây khi dự án đã được tạo.

## Phân tích mã nguồn

Sau khi dự án đã được tạo, Sourcetrail sẽ hỏi bạn liệu bạn có muốn bắt đầu phân tích? Chọn **Start** và chờ cho quá trình phân tích hoàn thành. Việc này có thể hơi tốn thời gian. Hộp thoại phân tích ([Indexing Dialog](#hộp-thoại-phân-tích)) và thanh trạng thái ([Status Bar](#thanh-trạng-thái)) sẽ cung cấp cho bạn thông tin về tiến độ. Nếu không, giao diện người dùng sẽ trống. Sourcetrail phân tích tất cả các tên và các mối quan hệ của chúng trong toàn bộ mã nguồn được cung cấp.

> **Công việc**
> * Chọn **Start** khi được hỏi bắt đầu phân tích.
> * Chờ cho đến khi phân tích các tập tin mã nguồn kết thúc.
> * Hoặc chọn **Stop** hoặc ấn ESC để tạm dừng phân tích (Sourcetrail sẽ cung cấp những thông tin đã thu thập được và việc phân tích có thể tiếp tục sau đó bằng làm mới ([refreshing](#làm-mới))).

!["Indexing"](docs/documentation/indexing.png "Indexing")

Sau khi phân tích thành công, Sourcetrail sẽ hiện thị tổng quát những tên được phân tích trong cửa sổ đồ thị ([graph view](#cửa-sổ-đồ-thị)) và một số thống kê trong cửa sổ mã nguồn ([code view](#cửa-sổ-mã-nguồn)).

#### Khắc phục lỗi

Nếu quá trình phân tích phát sinh lỗi, màn hình trạng thái sẽ hiển thị danh sách lỗi. Bạn có thể chọn vào nhãn lỗi ở phía bên phải thanh trạng thái hoặc vào một trong các lỗi trong bảng lỗi để xem vị trí của chúng.

> **Công việc**
> * Sửa những lỗi của bạn và làm mới ([refresh](#làm-mới)) để phân tích lại những tập tin chứa lỗi (Sourcetrail không phân tích lại những tập tin không có thay đổi, chọn **Force Refresh** từ [menu chỉnh sửa](#edit)).
> * Hoặc bỏ qua chúng và tiếp tịch với những tập tin chưa được phân tích.

!["Error View"](docs/documentation/error_view.png "Error View")

## Giao diện người dùng

Như đã đề cập ở trước, giao diện người dùng của Sourcetrail được chia thành ba màn hình chính. Bạn có thể sắp xếp lại chúng như mong muốn hoặc tách rời màn hình từ cửa sổ chính của ứng dụng sang một màn hình cửa sổ khác.

Cả ba màn hình hiển thị thông tin về tên được chọn:

!["Main Screen"](docs/documentation/main_screen.png "Main Screen")

### 1. Thanh tìm kiếm

[Thanh tìm kiếm](#thanh-tìm-kiếm) cho phép bạn dễ dàng tìm kiếm những tên đã được phân tích. Sử dụng nó để tìm những lớp (class) và hàm (function) mà bạn muốn xem. Ngoài ra, nó cũng chứa các nút giao diện người dùng để điều hướng [quay lại, tiến lên và lịch sử](#quay-lại-tiến-lên-và-lịch-sử) cũng như làm mới.

<img src="docs/documentation/search_view.png" width="750" alt="Search View">

Khi nhập tìm kiếm, [thanh gợi ý](#thanh-gợi-ý) sẽ hiện thị một danh sách ngắn gọn những tên có thể khớp. Lưu ý rằng Sourcetrail sử dụng thuật toán khớp mờ, cho phép bạn bỏ qua các ký tự khi nhập tìm kiếm.

<img src="docs/documentation/search_view_completion.png" width="800" alt="Search View Completion">

### 2. Đồ thị trực quan

[Đồ thị trực quan](#màn-hình-đồ-thị) hiển thị tên được chọn ở trạng thái hoạt động và những tên khác có mối liên quan. Hình ảnh đồ thị được tạo thành từ các nốt và cạnh.

- **Nốt:** tất cả tên ở trong mã nguồn sẽ được như là các [nốt](#nốt), ví dụ: `functions` (hàm), `classes` (lớp) hoặc `files` (tập tin). Các nốt có thành viên (ví dụ `lớp`,...) có thể được mở rộng để hiện thị tất cả nội dung của chúng, số ở mũi tên mở rộng cho biết có bao nhiêu thành viên bị ẩn. Nhấp vào một nốt sẽ kích hoạt nó và cập nhật tất cả các màn mình xem theo lựa chọn mới. Kéo một nốt có thể được sử dụng để thay đổi vị trí của nó.
- **Cạnh:** mỗi quan hệ giữa các tên được hiện thị như những [cạnh](#cạnh), như là `kiểu sử dụng`, `lời gọi hàm` hoặc `tập tin chứa`. Thỉnh thoảng, các cạnh được nhóm lại với nhau với một con số cho biết số lượng cạnh được chứa trong nhóm. Nhấp vào một cạnh sẽ làm nổi bật vị trí trong mã nguồn của nó ở màn hình mã nguồn.

!["Graph View Graph"](docs/documentation/graph_view_graph.png "Graph View Graph")

#### Màu sắc:

Các loại nốt và cạnh khác nhau cũng được hiển thị bằng các màu khác nhau. Sơ đồ màu mặc định được cấu hình theo bên dưới:

| Màu | Nốt | Cạnh
| --- | --- | ---
| xám | kiểu dữ liệu và lớp | kiểu sử dụng
| vàng | hàm và phương thức | lời gọi
| xanh dương | biến và thuộc tính | truy cập biến

#### Gạch chéo

Các nốt được hiển thị bằng đường gạch chéo là các nốt đã được sử dụng trong các tập tin nguồn được phân tích của bạn, nhưng chưa được định nghĩa. Nhấp vào chúng sẽ hiển thị tất cả các vị trí mà không được cung cấp khai báo.

!["Node Non Indexed"](docs/documentation/node_non_indexed.png "Node Non Indexed")

#### Chú thích

Để xem danh sách đầy đủ tất cả các nốt và cạnh hiện có, hãy xem [Chú thích đồ thị](#chú-thích-đồ-thị) tích hợp bằng cách nhấp vào nút `?` ở góc dưới bên phải của [màn hình đồ thị](#màn-hình-đồ-thị).

### 3. Màn hình mã nguồn

[Màn hình mã nguồn](#màn-hình-mã-nguồn) hiển thị tất cả vị trí hiện tại của tên trong những mã nguồn được phân tích. Bạn không thể chỉnh sửa mã nguồn. Tính năng làm nổi bật cú pháp được sử dụng để tăng khả năng đọc hiểu. Các vị trí mã nguồn được bao quanh bởi một khung, khi di chuột qua có thể nhấp để kích hoạt tên tương ứng. Các vị trí mã nguồn đang hoạt động sẽ được tô sáng.

<img src="docs/documentation/code_view.png" width="800" alt="Code View">

Vị trí mã nguồn được hiển thị dưới dạng các đoạn mã, bao gồm dòng được quan tâm và các dòng bổ sung được thêm vào đầu và cuối để cung cấp thông tin về ngữ cảnh của nó. Các đoạn mã này được hiển thị trong các tập tin.

**Lưu ý:** một tập tin có thể được chọn như một tên bằng cách chọn vào tên của nó ở thanh tiêu đề. Bằng cách ấn vào thanh tiêu đề hoặc biểu tượng ở bên phải thanh tiêu đề, tập tin có để chuyển sang ba chế độ:

- **Tối thiểu:** tập tin không hiểu bất kì nội dung nào.

<img src="docs/documentation/snippet_minimized.png" width="800" alt="Snippet Minimized">

- **Đoạn:** tập tin hiển thị các đoạn mã chứa những vị trí được chọn được phân tách bằng các dòng.

<img src="docs/documentation/snippet_snippets.png" width="800" alt="Snippet Snippets">

- **Tối đa:** [Màn hình mã nguồn](#màn-hình-mã-nguồn) chuyển sang [chế độ một tập tin](#chế-độ-một-tập-tin) và toàn bộ nội dung của tập tin sẽ được hiển thị.

<img src="docs/documentation/code_view_single.png" width="800" alt="Code View Single">

Đọc [tập tin mã nguồn](#tập-tin) để có thêm thông tin.

## Bắt đầu khám phá!

Đến đây, bạn nên hiểu những điều cơ bản về giao diện người dùng của Sourcetrail và có thể bắt đầu khám phá mã nguồn của mình. Sourcetrail sẽ cho phép bạn nhìn mã nguồn từ một góc nhìn hoàn toàn mới, bằng cách cung cấp cho bạn cái nhìn tổng quan ngắn gọn về các thành phần và cách nhanh hơn để đi sâu vào chi tiết bên trong, đồng thời luôn duy trì kết nối với các chi tiết triển khai của mã nguồn thực tế.

Vui lòng xem hướng dẫn sử dụng chi tiết hơn bên dưới để biết thêm thông tin.

Nhóm Sourcetrail chúc bạn có một khởi đầu tốt với sản phẩm của chúng tôi, tiết kiệm được nhiều thời gian, tăng năng suất và có mã nguồn sạch hơn nhiều.

> **Công việc**
> * Bắt đầu khám phá và chúc vui vẻ!


# Cài đặt

## Windows

## MacOS

## Linux

## Thư mục dữ liệu

Thư mục dữ liệu chứa những tập tin nhất định được dùng bởi Sourcetrail để chạy chương trình. Sau khi thực hiện [hướng dẫn cài đặt](#cài-đặt), thư mục dữ liệu sẽ ở những đường dẫn dưới đây tuỳ vào hệ điều hành.

| Hệ điều hành | Đường dẫn
| --- | ---
| Windows | `C:/Users/You/AppData/Local/Coati Software/Sourcetrail` _(dùng cho dữ liệu và cài đặt tự tuỳ biến)_ `install_directory/Coati Software/Sourcetrail/data` _(dùng cho dữ liệu cố định)_
| MacOS | `~/Library/Application Support/Sourcetrail`
| Linux | `~/.config/sourcetrail`

## Tìm thư viện hệ thống

### Windows

### MacOS

### Linux

## Tìm thư viện Java

Phiên bản Sourcetrail hiện tại yêu cầu cài đặt môi trường chạy Java 8 để lập chỉ mục bất kỳ dự án Java nào. Hãy đảm bảo rằng Sourcetrail và JRE của bạn có cùng loại hoặc cùng kiến trúc (Sourcetrail 64 bit yêu cầu JRE 64 bit). Để tìm tệp thư viện cần thiết, vui lòng tham khảo mô tả thích hợp bên dưới.

### Windows

### MacOS

### Linux


# Giao diện

## Cửa sổ chính

### Cửa sổ con

Ba màn hình của Sourcetrail được sắp xếp thành những cửa sổ con, có thể được bố trí tự do trong cửa sổ chính hoặc tách rời khỏi nó. Mỗi cửa số con có một thanh tiêu đề hiển thị tên của nó và 2 nút ấn để đóng cửa sổ con và tách nó khỏi cửa sổ chính.

!["Main Window"](docs/documentation/main_window.png "Main Window")

**Thao tác**

- Kéo cửa sổ con trên thanh tiêu đề để sắp xếp lại vị trí trong cửa sổ chính, tách hoặc gắn lại cửa sổ con.
- Nhấn vào biểu tượng "x" để đóng cửa sổ con. Có thể mở lại cửa sổ con từ [menu xem](#view).
- Nhấn vào biểu tượng "□" để tách cửa sổ con khỏi cửa sổ chính.

### Thanh thẻ

Thanh thẻ nằm ở phía trên cùng của [cửa sổ chính](#cửa-sổ-chính) và được sử dụng để mở nhiều tên cùng lúc.

!["Tab Bar"](docs/documentation/tab_bar.png "Tab Bar")

**Thao tác**

- Nhấp vào biểu tượng `+` để mở thẻ mới hoặc sử dụng [phím tắt thẻ mới](#phím-tắt).
- Nhấp vào một thẻ để kích hoạt và hiển thị nội dung của thẻ đó hoặc sử dụng [phím tắt chuyển thẻ tiếp theo/trước đó](#phím-tắt).
- Nhấp vào biểu tượng `x` ở bên phải mỗi thẻ để đóng thẻ đó hoặc sử dụng [phím tắt đóng thẻ](#phím-tắt).
- Nhấp và kéo một thẻ để thay đổi vị trí của thẻ đó.

### Thanh trạng thái

Thanh trạng thái nằm ở phía dưới [cửa sổ chính](#cửa-sổ-chính) và được sử dụng để hiển thị thông tin về trạng thái của Sourcetrail và các tiến trình đang chạy cho người dùng.

Nó hiển thị:

- Thông báo trạng thái gần nhất.
- Số lượng lỗi nếu dự án đang phân tích có lỗi.
- Thanh tiến trình phân tích trong khi đang phân tích.

!["Status Bar"](docs/documentation/status_bar.png "Status Bar")

**Thao tác**

- Nhấp vào số lượng thông báo trạng thái để hiển thị thẻ [trạng thái](#thẻ-trạng-thái).
- Nhấp vào số lượng lỗi để hiển thị vị trí lỗi trong [màn hình mã nguồn](#màn-hình-mã-nguồn).
- Nhấp vào thanh tiến trình lập chỉ mục để hiển thị [hộp thoại phân tích](#hộp-thoại-phân-tích) mục nếu chúng bị ẩn.

### Thanh tìm kiếm trên màn hình

Thanh tìm kiếm trên màn hình được dùng để tìm kiếm những nội dung hiển thị trên [màn hình đồ thị](#màn-hình-đồ-thị) và [màn hình mã nguồn](#màn-hình-mã-nguồn). Nó được hiển thị ở dưới [cửa sổ chính](#cửa-sổ-chính) khi sử dụng thao tác [tìm trên màn hình](#phím-tắt).

!["Onscreen Search Bar"](docs/documentation/onscreen_search_bar.png "Onscreen Search Bar")

**Thao tác**

- Nhập vào tìm kiếm để tìm kiếm các nội dung trong [màn hình đồ thị](#màn-hình-đồ-thị) và [màn hình mã nguồn](#màn-hình-mã-nguồn).
- Nhấp vào các nút mũi tên bên cạnh khung tìm kiếm để chuyển đến các vị trí khớp với kết quả tìm kiếm.
- Sử dụng các hộp kiểm bên phải khung tìm kiếm để xác định nội dung chế độ xem nào sẽ được tìm kiếm.
- Nhấp vào biểu tượng `x` ở bên phải để đóng thanh tìm kiếm trên màn hình hoặc nhấn `ESC`.

## Các cửa sổ

### Cửa sổ bắt đầu

Cửa sổ bắt đầu luôn xuất hiện khi bạn mở Sourcetrail. Nó cho phép tạo mới dự án hoặc mở dự án cũ.

<img src="docs/documentation/start_window.png" width="700" alt="Start Window">
    
**Tao tác**

- Nhấp vào `New Project` sẽ dẫn bạn đến [trình thiết lập dự án](#trình-thiết-lập-dự-án).
- Nhấp vào `Open Project` sẽ cho phép bạn mở một dự án Sourcetrail trước đó bằng cách chọn trong hộp thoại tập tin.
- Nhấp vào một trong các `Recent Projects` sẽ mở dự án đó. Danh sách hiển thị tối đa 7 dự án theo thứ tự gần đây nhất.
- Ấn `ESC` để đóng cửa sổ.
- Nhấp `Check for new version` để kết nối đến [https://github.com/PhongLamBaoMat/Sourcetrail/releases](https://github.com/PhongLamBaoMat/Sourcetrail/releases) để xem phiên bản mới nhất.

### Khung danh sách đường dẫn

Khung danh sách đường dẫn là một phần của giao diện người dùng, được sử dụng trong [cửa sổ tuỳ chọn](#cửa-sổ-tuỳ-chọn) và [trình thiết lập dự án](#trình-thiết-lập-dự-án). Nó cho phép nhập đường dẫn của các tập tin và thư mục.
Nếu bạn muốn sử dụng các biến môi trường, bác có thể nhập một trong cách dưới .

`${VARIABLE_NAME}` hoặc `$%VARIABLE_NAME%`

<img src="docs/documentation/path_list_box.png" width="700" alt="Path List Box">

**Thao tác**

- Nhấp vào biểu tượng `+` để thêm một đường dẫn mới.
- Nhấp bào biết tượng `-` để xoá đường dẫn đã chọn.
- Nhấp vào đường dẫn để chọn nó.
- Nhập đường dẫn bằng cách gõ bàn phím.
- Nhấp vào `...` bên trong đường dẫn để mở hộp thoại tập tin để chọn một tập tin hoặc thư mục.
- Thêm trực tiếp nhiều đường dẫn vào khung bằng cách kéo thả các đối tượng từ hệ thống tập tin của bạn.
- Nhấp vào biển tượng cây bút ở góc dưới bên phải để chỉnh sửa danh sách dưới dạng văn bản.

#### Chỉnh sửa văn bản

Bằng cách ấn vào biểu tượng cây bút ở dưới góc phải, bạn có thể mở khung chỉnh sửa văn bản. Nó cho phép bạn chỉnh sửa toàn bộ danh sách trong một khung văn bản. Mỗi dòng tương ứng một mục trong danh sách.

<img src="docs/documentation/path_list_box_edit.png" width="600" alt="Path List Box Edit">

**Thao tác**

- Thêm hoặc xoá các mục trong danh sách bằng bàn phím.
- Nhấp vào `Cancel` để huỷ chỉnh sửa văn bản.
- Nhấp vào `Save` để lưu các thay đổi của bạn vào danh sách.

### Cửa sổ tuỳ chọn

Cửa sổ tuỳ chọn cho phép bạn chỉnh sửa các cài đặt cho toàn bộ các dự án. Bạn có thể mởi tuỳ chỉnh từ menu [Edit/Preferences](#edit).

<img src="docs/documentation/preferences_screen.png" width="800" alt="Preferences Screen">

| Cài đặt | Mô tả
| --- | ---
| Font Face | Lựa chọn phông chữ được hiện thị trong toàn bộ giao diện.
| Font Size | Đặt kích thước phông chữ của giao diện. Bạn có thể thay đổi kích thước phông chữ bằng các thao tác trong [menu xem](#view).
| Tab Width | Khai báo khoảng cách của một phím tab ở màn hình mã nguồn.
| Text Encoding | Khai báo định dạng mã hoá văn bản để hiện thị ở màn hình mã nguồn.
| Color scheme | Chọn bảng màu mà Sourcetrail nên hiển thị. Các bác màu được nằm trong [thư mục dữ liệu](#thư-mục-dữ-liệu)/color-schemes/
| Animations | Cho phép các hiệu ứng khi thao tác trên giao diện hay không.
| Built-In Types | Cho phép các kiểu dự liệu cơ bản như `int` hoặc `bool` có được hiển thị khi được tham chiếu trong màn hình đồ thị hay không.
| Directory in File Title | Cho phép hiển thị thư mục cha của một tập tin trong dự án.
| Auto Scaling to DPI | **(Chỉ có ở Linux)** Tự động điều chỉnh tỷ lệ theo độ phân giải DPI của có được bật hay không. Cài đặt này điều chỉnh biến môi trường `QT_AUTO_SCREEN_SCALE_FACTOR` của Qt Framework. Chọn 'system' để giữ nguyên thiết lập hiện tại.
| Scale Factor | **(Chỉ có ở Linux)** Hệ số tỷ lệ màn hình cho giao diện người dùng của ứng dụng. Cài đặt này điều chỉnh biến môi trường `QT_SCALE_FACTOR` của Qt Framework. Chọn 'system' để giữ nguyên thiết lập hiện tại.
| Scroll Speed | Hệ số nhân cho tốc độ cuộn chuột. Giá trị nhỏ hơn 1 sẽ cuộn chậm hơn, giá trị lớn hơn 1 cuộn nhanh hơn.
| Graph Zoom | Chuyển đổi hành vi mặc định của con lăn chuột trong biểu đồ giữa cuộn và phóng to.
| Logging | Chọn ô này cho phép ghi nhật ký vào bảng điều khiển và vào tập tin nhật ký. Mặc định cài đặt này bị tắt để tăng tốc độ cho Sourcetrail. Nếu bạn gặp lỗi khi sử dụng Sourcetrail, chúng tôi khuyến khích bật cài đặt này để chúng tôi có thể hiểu rõ lỗi hơn.
| Indexer Logging | Khi được bật, Sourcetrail sẽ ghi lại thông tin chi tiết quá trình phân tích. Dữ liệu nhật ký này có thể giúp chúng tôi khắc phục sự cố.
| Automatic Update Check | Tự động kiểm tra cập nhật phiên bản mới.
| Sourcetrail Port | Cổng mà Sourcetrail sử dụng để lắng nghe thông tin từ các tiện ích mở rộng.
| Plugin Port | Cổng mà Sourcetrail sẽ gửi thông tin đến tiện ích mở rộng.
| Indexer threads | Xác định luồng song song được sử dụng trong khi phân tích. Đặt giá trị này thành 'default' sẽ khiến Sourcetrail tự động tính số lượng luồng lý tưởng dựa trên CPU và lượng luồng tối đa cho việc phân tích.
| Multi process C/C++ indexing | Cho phép các luồng của trình phân tích C/C++ chạy trong một tiến trình riêng biệt. Điều này ngăn ứng dụng bị lỗi do các ngoại lệ không lường trước được trong quá trình phân tích.
| Java Path | Nếu bạn muốn Sourcetrail phân tích mã nguồn Java, vui lòng xác định đường dẫn đến thư viện Java 8. Bạn có thể sử dụng nút bên dưới để tự tìm đường dẫn hoặc thêm đường dẫn bằng tay. Bạn có thể đọc thêm [tìm đường dẫn thư viện Java](#tìm-thư-viện-java) để được hướng dẫn kĩ hơn.
| JRE System Library | Thêm các tập tin JAR của thư viện hệ thống JRE. Những tập tin này có thể tìm thấy ở thư mục JRE. Bạn có thể chọn nút ở dưới để tự động tìm kiếm hoặc thêm thủ công.
| Maven Path | Chỉ cần khi phân tích dự án sử dụng Maven, cung cấp đường dẫn đến tập tin thực thi của Maven. Bạn có thể sử dụng tự động tìm kiếm.
| Post Processing | Bước xử lý hậu kỳ giải quyết các tham chiếu chưa được giải quyết sau khi đã phân tích xong. Những tham chiếu này sẽ được đánh dấu là "ambigous" để chỉ ra rằng một số cạnh này có thể không bao giờ được gặp trong quá trình thực thi phân tích mã nguồn vì quá trình xử lý hậu kỳ chỉ dựa vào tên và kiểu dữ liệu.
| Global Include Paths | Thiết lập đường dẫn tìm kiếm tiêu đề được sử dụng cho **toàn bộ** dự án của bạn (ví dụ: tiêu đề thư viện chuẩn,...). Tuỳ chọn tự động phát hiện các đường dẫn này có sẵn cho trình biên dịch Clang, GCC và Visual Studio. Đọc [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn) để được hướng dẫn tự thêm đường dẫn. Đọc [tìm tập tin thư viện Java](#tìm-thư-viện-Java) để tìm thư viện Java.
| Global Framework Search Paths | **(Chỉ có trên MacOS)** Xác định đường dẫn tìm kiếm cho các tệp `.framework` cho tất cả dự án của bạn. Tuỳ chọn tự động phát hiện các đường dẫn này có sẵn cho Clang và GCC. Đọc [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn) để được hướng dẫn tự thêm đường dẫn.

### Trình thiết lập dự án

Trình thiết lập dự án cho phép bạn tạo một dự án Sourcetrail mới. Bằng cách điền tên và đường dẫn của dự án và thêm một số **Nhóm mã nguồn** để khai báo những tập tin mã nguồn cần được phân tích. Có nhiều cách để tạo Nhóm mã nguồn, đối với hầu hết các dự án, chỉ cần thêm một Nhóm mã nguồn là đủ.

Thêm thông tin chi tiết tại [THIẾT LẬP DỰ ÁN](#thiết-lập-dự-án).

<img src="docs/documentation/project_setup_wizard_start.png" width="800" alt="Project Setup Wizard Start">
    
### Hộp thoại phân tích

Hộp thoại này xuất hiện khi Sourcetrail đang phân tích dự án của bạn. Toàn bộ giao diện người dùng sẽ bị đóng băng nếu như hộp thoại này xuất hiện.

#### Hộp thoại bắt đầu phân tích

Hộp thoại này hiển thị số lượng tập được phân tích trước khi việc phân tích bắt đầu. Có nhiều chế độ làm mới khác nhau:

- **Các tập tin được cập nhật:** phân tích lại toàn bộ tập tin được chỉnh sửa từ lần phân tích cuối, những tập tin có liên quan đến chúng và tập tin mới.
- **Các tập tin chưa hoàn thành & đã cập nhật:** phân tích lại toàn bộ tập tin có lỗi trong lần phân tích trước, những tập tin có liên quan đến chúng và tập tin mới.
- **Toàn bộ tập tin:** xoá lần phân tích trước và phân tích lại từ đầu.

**Phân tích mã nguồn Python**

Đối với các dự án Python, một hợp đánh dấu **Phân tích mã nguồn Python** được hiển thị thêm. Khi đánh dấu vào, các tham chiếu trong mã nguồn của bạn (các lệnh gọi, cách dùng,... ) được giải quyết theo tên, điều này không thực sự chính xoác hoàn toàn nhưng nó sẽ nhanh hơn phân tích sâu. Dùng tuỳ chọn này để phân tích nhanh và bắt đầu đọc mã nguồn trong khi phân tích sâu đang thực hiện.

<img src="docs/documentation/start_indexing_dialog.png" width="600" alt="Start Indexing Dialog">

**Thao tác**
- Chuyển chế độ phân tích bằng cách chọn tuỳ chọn khác.
- Chọn `Cancle` sẽ huỷ phân tích.
- Chọn `Start` sẽ bắt đầu phân tích mã nguồn.

#### Hộp thoại tiến trình

Hộp thoại này hiện thị tiến trình phân tích của Sourcetrail, tiến trình này không thể bị huỷ.

<img src="docs/documentation/progress_dialog.png" width="600" alt="Progress Dialog">

**Thao tác**
- Chọn `Hide` sẽ ẩn hộp thoại. Bạn có thể hiển thị nó lại bằng cách chọn thanh tiến trình phân tích ở [thanh tiến trình](#thanh-tiến-trình) hoặc [làm mới](#làm-mới).

#### Hộp thoại phân tích

Hôp thoại hiển thị thông tin về quá trình phân tích của bạn, bằng cách hiển thị số lượng tập tin đã được phân tích, tập tin đang được phân tích, số lượng lỗi và phần trăm hoàn thành phân tích.

**Thao tác**
- Chọn `Stop` hoặc ấn `ESC` sẽ huỷ phân tích. Sourcetrail vẫn chờ luồng phân tích đang chạy hoàn thành. Bạn có thể tiếp tục phân tích bằng [làm mới](#làm-mới).
- Chọn `Hide` sẽ ẩn hộp thoại. Bạn có thể hiển thị nó lại bằng cách chọn thanh tiến trình phân tích ở [thanh tiến trình](#thanh-tiến-trình) hoặc [làm mới](#làm-mới).

#### Hộp thoại hoàn thành phân tích

Hộp thoại xuất hiện sau khi phân tích kết thúc, hiển thị thông tin và những tập tin được phân tích, tổng thời gian và lỗi.

<img src="docs/documentation/finished_indexing_dialog.png" width="600" alt="Finished Indexing Dialog">
    
#### Hộp thoại huỷ phân tích

Hộp thoại xuất hiện sau khi phân tích bị dừng, hiển thị thông tin về những tập tin đã được phân tích, tổng thời gian và lỗi. Bạn có thể chọn phân tích mới hoặc tiếp tục phân tích.

<img src="docs/documentation/finished_indexing_dialog.png" width="600" alt="Interrupted Indexing Dialog">

**Thao tác**
- Chọn `Discard` để bỏ qua phân tích mới.
- Chọn `Keep` sẽ chuyển dự án của bạn tới phân tích mới và bỏ qua phân tích hiện tại.

## Menu

### Project

- **New Project**
  * Phím tắt: [dự án mới](#phím-tắt)
  * Mở [trình thiết lập dự án](#trình-thiết-lập-dự-án) để tạo dự án mới.
- **Open Project**
  * Phím tắt: [mở dự án](#phím-tắt)
  * Mở hộp thoại chọn tập tin để bạn chọn dự án Sourcetrail có sẵn từ ổ cứng trên máy bạn. 
- **Recent Projects**
  * Mở một menu nhỏ để chọn những dự án Sourcetrail gần đây.
- **Edit Project**
  * Mở [hộp thoải chỉnh sửa dự án](#trình-thiết-lập-dự-án) của bạn để bạn sửa đổi.
- **Thoát**
  * Thoát Sourcetrail.

### Edit
- **Refresh**
  * Phím tắt: [làm mới](#phím-tắt)
  * Làm mới sẽ kiểm tra toàn bộ tập tin mã nguồn được phân tích để cập nhật và phân tích lại những thay đổi.
- **Full Refresh**
  * Phím tắt: [làm mới toàn bộ](#phím-tắt)
  * Làm mới toàn bộ sẽ phân tích lại toàn bộ dự án.
- **Find Symbol**
  * Phím tắt: [tìm tên](#phím-tắt)
  * Tuỳ chọn sẽ tập trung vào thanh tìm kiếm.
- **Find On-Screen**
  * Phím trên: [tìm trên màn hình](#phím-tắt)
  * Hiển thị [thanh tìm kiếm trên màn hình](#thanh-tìm-kiếm-trên-màn-hình) để tìm kiếm nội dung hiển thị trong [màn hình đồ thị](#màn-hành-đồ-thị) và [màn hình mã nguồn](#màn-hình-mã-nguồn).
- **Next Reference**
  * Phím tắt: [tham chiếu tiếp](#phím-tắt)
  * Tuỳ chọn này lướt đến vị trí tiếp theo của tên được chọn trong mã nguồn.
- **Previous Reference**
  * Phím tắt: [thao chiếu trước](#phím-tắt)
  * Tuỳ chọn này lướt đến vị trí trước đó của tên được chọn trong mã nguồn.
- **Next Local Reference**
  * Phím tắt: [tham chiếu cục bộ tiếp](#phím-tắt)
  * Tuỳ chọn này để lặp đến vị trí tiếp theo của tên hoặc cạnh cục bộ được chọn trong mã nguồn.
- **Previous Local Reference**
  * Phím tắt: [tham chiếu cục bộ trước](#phím-tắt)
  * Tuỳ chọn này để lặp đến vị trí trước đó của tên hoặc cạnh cục bộ được chọn trong mã nguồn.
- **To overview**
  * Phím tắt: [đến tổng quan](#phím-tắt)
  * Tuỳ chọn này sẽ hiển thị tổng quát của dự án.
- **Preferences**
  * Phím tắt: [tuỳ chọn](#phím-tắt)
  * Mở [cửa sổ tuỳ chọn](#cửa-sổ-tuỳ-chọn)
  
### View
- **New Tab**
  * Mở một thẻ mới.
- **Close Tab**
  * Đóng thẻ hiện tại.
- **Select Next Tab**
  * Chuyển đến thẻ tiếp theo bên phải của thẻ hiện tại.
- **Select Previous Tab**
  * Chuyển đến thẻ tiếp theo bên trái của thẻ hiện tại.
- **Show Start Window**
  * Hiển thị [cửa sổ bắt đầu](#cửa-sổ-bắt-đầu).
- **Show Title Bars**
  * Bật/tắt hiển thị của các thanh phía trên.
- **Reset window layout**
  * Khôi phục tất cả cửa sổ về vị trí ban đầu.
- **Search Window**
  * Bật/tắt hiển thị cửa sổ tìm kiếm. Bạn cũng có thể thực hiện việc này bằng cách đóng cửa sổ tìm kiếm khi ấn vào biểu tượng `x` trên thanh tiêu đề của nó. (Xem [cửa sổ tiện ích](#cửa-sổ-tiện-ích))
- **Graph Window**
  * Bật/tắt hiển thị màn hình đồ thị. Bạn cũng có thể thực hiện việc này bằng cách đóng màn hình đồ thị khi ấn vào biểu tượng `x` trên thanh tiêu đề của nó. (Xem [cửa sổ tiện ích](#cửa-sổ-tiện-ích))
- **Code Window**
  * Bật/tắt hiển thị màn hình mã nguồn. Bạn cũng có thể thực hiện việc này bằng cách đóng màn hình mã nguồn khi ấn vào biểu tượng `x` trên thanh tiêu đề của nó. (Xem [cửa sổ tiện ích](#cửa-sổ-tiện-ích))
- **Status Window**
  * Bật/tắt hiển thị màn hình trạng thái. Bạn cũng có thể thực hiện việc này bằng cách đóng màn hình trạng thái khi ấn vào biểu tượng `x` trên thanh tiêu đề của nó. (Xem [cửa sổ tiện ích](#cửa-sổ-tiện-ích))
- **Larger Font**
  * Phím tắt: [phong chữ lớn hơn](#phím-tắt)
  * Tăng kích thước phong chữ của màn hình chính.
- **Smaller Font**
  * Phím tắt: [phong chữ nhỏ hơn](#phím-tắt)
  * Giảm kích thước phong chữ của màn hình chính.
- **Reset Font Size**
  * Phím tắt: [khôi phục phong chữ](#phím-tắt)
  * Khôi phục kích thước phong chữ về như ban đầu.
  
### History

- **Back**
  * Phím tắt: [trước](#phím-tắt)
  * Hoàn tác thao tác điều hướng trước đấy.
- **Forward**
  * Phím tắt: [tiếp](#phím-tắt)
  * Thực hiện lại thao tác điều hướng đã bị huỷ bỏ.
- **Recently Active Symbols**
  * Liệt kê lịch sử của tên theo tứ tự thời gian.

### Bookmarks
- **Bookmark Active Symbol**
  * Phím tắt: [đánh dấu tên](#phím-tắt)
  * Mở [trình tạo đánh dấu](#trình-tạo-đánh-dấu) để tạo đánh dấu trang mới.
- **Bookmark Manager**
  * Phím tắt: [quản lý đánh dấu trang](#phím-tắt)
  * Mở hộp thoại [quản lý đánh dấu trang](#quản-lý-đánh-dấu-trang) để xem tất cả các đánh dấu trang.
- **Recent Bookmarks**
  * Danh sách các đánh dấu trang được thêm.
  
### Help
- **About**
  * Hiển thị thông tin bản quyền của Sourcetrail.
- **Keyboard Shortcuts**
  * Hiển thị bảng phím tắt của Sourcetrail.
- **Documentation**
  * Mở tài liệu này trên trình duyệt.
- **Changelog**
  * Mở [changelog](https://github.com/PhongLamBaoMat/Sourcetrail/blob/main/CHANGELOG.md) của Sourcetrail trên trình duyệt.
- **Bug Tracker**
  * Mở quản lý lỗi của Sourcetrail trên trình duyệt.
- **License**
  * Mở một cửa sổ chứa giấy phép của Sourcetrail và tất cả giấy phép của bên thứ ba.
- **Show Data Folder**
  * Mở trình quản lý tập tin hiển thị [thư mục dữ liệu](#thư-mục-dữ-liệu).
- **Show Log Folder**
  * Mở trình quản lý tập tin trong [data](#thư-mục-dữ-liệu)/logs, nơi lưu trữ tất cả các tập tin nhật ký. Bạn có thể bật tính năng ghi nhật ký trong tập tin trong [cửa sổ tuỳ chọn](#cửa-sổ-tuỳ-chọn).

### Phím tắt

#### Chung
| Phím tắt | Windows | MacOS | Linux
| --- | --- | --- | ---
| Chuyển giữa màn hình đồ thị và mã nguồn | `Tab` | `Tab` | `Tab`
| Tuỳ chọn | `Ctrl + ,` | `Cmd + ,` | `Ctrl + ,`
| Dự án mới | `Ctrl + N` | `Cmd + N` | `Ctrl + N`
| Mở dự án | `Ctrl + O` | `Cmd + O` | `Ctrl + O`
| Đóng cửa sổ | `Alt + F4` | `Cmd + W` | `Ctrl + W`
| Ẩn cửa sổ |  | `Cmd + H` |
| Làm mới | `F5` | `Cmd + R` | `F5`
| Làm mới toàn bộ | `Shift + F5` | `Cmd + Shift + R` | `Shift + F5`
| Trước | `Z` / `Alt + Left` / `Backspace` | `Z` / `Cmd + [` / `Backspace` | `Z` / `Alt + Left` / `Backspace`
| Tiếp | `Shift + Z` / `Alt + Right` | `Shift + Z` / `Cmd + ]` | `Shift + Z` / `Alt + Right`
| Tìm tên | `Ctrl + F` | `Cmd + F` | `Ctrl + F`
| Tìm văn bản | `Ctrl + Shift + F` | `Cmd + Shift + F` | `Ctrl + Shift + F`
| Tìm trên màn hình | `Ctrl + D` | `Cmd + D` | `Ctrl + D`
| Đến tổng quan | `Ctrl + Home` | `Cmd + Home` / `Cmd + Up` | `Ctrl + Home`
| Thẻ mới | `Ctrl + T` | `Cmd + T` | `Ctrl + T`
| Đóng thẻ | `Ctrl + W` | `Cmd + W` | `Ctrl + W`
| Chuyển thẻ tiếp theo | `Ctrl + Tab` | `Ctrl + Tab` | `Ctrl + Tab`
| Chuyển thẻ trước đó | `Ctrl + Shift + Tab` | `Ctrl + Shift + Tab` | `Ctrl + Shift + Tab`
| Phong chữ lớn hơn | `Ctrl + +` | `Cmd + +` | `Ctrl + +`
| Phong chữ nhỏ hơn | `Ctrl + -` | `Cmd + -` | `Ctrl + -`
| Khôi phục phong chữ | `Ctrl + 0` | `Cmd + 0` | `Ctrl + 0`
| Đánh dấu tên | `Ctrl + S` | `Cmd + S` | `Ctrl + S`
| Quản lý đánh dấu trang | `Ctrl + B` | `Cmd + B` | `Ctrl + B`

#### Màn hình đồ thị

| Phím tắt | Windows | MacOS | Linux
| --- | --- | --- | ---
| Di chuyển giữa các nốt | `WASD` / `HJKL` / `Arrows` | `WASD` / `HJKL` / `Arrows` | `WASD` / `HJKL` / `Arrows`
| Di chuyển giữa các cạnh  | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows` | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows` | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows`
| Chọn nốt/cạnh | `Enter` / `E` | `Enter` / `E` | `Enter` / `E`
| Chọn nốt trong thẻ mới | `Ctrl + Shift + Enter` / `Ctrl + Shift + E` | `Cmd + Shift + Enter` / `Cmd + Shift + E` | `Ctrl + Shift + Enter` / `Ctrl + Shift + E`
| Mở rộng/thu gọn nốt | `Shift + Enter` / `Shift + E` | `Shift + Enter` / `Shift + E` | `Shift + Enter` / `Shift + E`
| Pan | `Ctrl + Arrows` | `Cmd + Arrows` | `Ctrl + Arrows`
| Phóng to | `Ctrl + Shift + Up` / `Ctrl + Mouse Wheel Up` | `Cmd + Shift + Up` / `Cmd + Mouse Wheel Up` | `Ctrl + Shift + Up` / `Ctrl + Mouse Wheel Up`
| Thu nhỏ | `Ctrl + Shift + Down` / `Ctrl + Mouse Wheel Down` | `Cmd + Shift + Down` / `Cmd + Mouse Wheel Down` | `Ctrl + Shift + Down` / `Ctrl + Mouse Wheel Down`
| Khôi phục thu phóng | `0` | `0` | `0`
| Mở hộp thoại tuỳ chỉnh liên kết | `Ctrl + U` | `Cmd + U` | `Ctrl + U`

#### Màn hình mã nguồn

| Phím tắt | Windows | MacOS | Linux
| --- | --- | --- | ---
| Tham chiếu tiếp | `Ctrl + G` | `Cmd + G` | `Ctrl + G`
| Tham chiếu trước | `Ctrl + Shift + G` | `Cmd + Shift + G` | `Ctrl + Shift + G`
| Tham chiếu cục bộ tiếp | `Ctrl + T` | `Cmd + T` | `Ctrl + T`
| Tham chiếu cục bộ trước | `Ctrl + Shift + T` | `Cmd + Shift + T` | `Ctrl + Shift + T`
| Di chuyển trong màn hình mã nguồn | `WASD` / `HJKL` / `Arrows` | `WASD` / `HJKL` / `Arrows` | `WASD` / `HJKL` / `Arrows`
| Di chuyển đến tham chiếu gần nhất | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows` | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows` | `Shift + WASD` / `Shift + HJKL` / `Shift + Arrows`
| Chọn | `Enter` / `E` | `Enter` / `E` | `Enter` / `E`
| Chọn ở thẻ mới | `Ctrl + Shift + Enter` / `Ctrl + Shift + E` | `Cmd + Shift + Enter` / `Cmd + Shift + E` | `Ctrl + Shift + Enter` / `Ctrl + Shift + E`
| Cuộn chuột | `Ctrl + Arrows` | `Cmd + Arrows` | `Ctrl + Arrows`

## Màn hình đồ thị

Màn hình đồ thị hiển thị trức quan tên đang được chọn và tất cả các mối quan hệ của nó với các biểu tượng khác dưới dạng hình ảnh đồ thị tương tác. Bạn cũng có thể hiển thị toàn bộ đồ thị lời gọi, chuỗi kế thừa hoặc cây bao gồm bằng cách sử dụng thanh công cụ ở bên trái. Tìm hiểu thêm tại [tuỳ chỉnh liên kết](#tuỳ-chỉnh-liên-kết).

<img src="docs/documentation/graph_view.png" width="750" alt="Graph View">

#### Thao tác

**Nút nhấn**
- Sử dụng thanh điều hướng ở góc trên bên trái để tạo [tuỳ chỉnh liên kết](#tuỳ-chỉnh-liên-kết).
- Sử dụng các nút nhóm ở trên bên trái để bật [nhóm nút](#nhóm-nút) bằng namespace/gói hoặc tập tin.
- Ấn `+` và `-` ở góc dưới bên trái để thay đổi mức độ phóng to.
- Ấn `?` ở góc dưới bên phải để hiển thị [chú thích đồ thị](#chú-thích-đồ-thị).

**Panning**
- Dùng chuột kéo vùng nền.
- Cuộn chuột sang trái/phải và lên/xuống trên bàn di chuột.
- Sử dụng các phím `W` `A` `S` `D`.

**Thu phóng**
- Giữ phím `Ctrl/Cmd` và cuộn bằng con lắn chuột hoặc bàn di chuột.
- Nhấn `Shift + W` hoặc `Shift + S`.
- Nhấm `0` để khôi phục mức thu phóng.

**Menu ngữ cảnh**
- **Open in New Tab:** mở một thẻ mới với nốt nằm dưới con trỏ chuột.
- **Back:** đi ngược theo lịch sử.
- **Forward:** đi tiếp theo lịch sử.
- **Show Definition:** hiển thị định nghĩa của nút dưới con trỏ chuột trong [màn hình mã nguồn](#màn-hình-mã-nguồn).
- **Expand Node:** mở rộng nốt ở dưới con trỏ chuột.
- **Collapse Node:** thu gọn nốt ở dưới con trỏ chuột.
- **Hide Node:** ẩn nốt dưới con trỏ chuột.
- **Hide Edge:** ẩn cạnh dưới con trỏ chuột.
- **Bookmard Node:** đánh dấu nút dưới con trỏ chuột.
- **Save As Image:** lưu đồ thị hiện tại thành tập tin hình ảnh. Các định dạng có thể lưu là `PNG`, `JPEG`, `BMP` và `SVG`.
- **Save To Clipboard:** lưu đồ thị hiện tại như ảnh `PNG` tới bộ nhớ tạm.
- **Copy Name**: sao chép tên của nốt dưới con trỏ chuột tới bộ nhớ tạm.
- **Copy Full Path:** sao chép đường dẫn tập tin của nút dưới con trỏ chuột tới bộ nhớ tạm.
- **Open Containing Folder:** hiển thị tập tin trong trình quản lý tập tin của nốt dưới con trở chuột.

### Nốt

Màu sắc hiển thị tương ứng với bảng màu mặc định.

| Kiểu nốt | Ảnh
| --- | ---
| **Tập tin**: Tập tin không được phân tích là các tập tin không nằm trong bất kì nhóm mà nguồn, do đó không được phân tích bởi Sourcetrail. Các tập tin không đầy đủ đã gây ra lỗi trong quá trình phân tích hoặc là một phần của quá trình phân tích lỗi. | !["Node File"](docs/documentation/node_file.png "Node File")
| **Macro** | !["Node Macro"](docs/documentation/node_macro.png "Node Macro")
| **Namespace, gói & mô-đun** | !["Node Namespace"](docs/documentation/node_namespace.png "Node Namespace")
| **Lớp & Cấu trúc**: hiển thị các thành viên lòng nhau và được phân tách theo phạm vi truy cập: `public`, `protected`, `pricate`. Mặc định, chỉ những thành viên có cạnh mới được hiển thị. Biểu tượng mũi tên cho phép mở rộng và thu gọn chúng. Con số cho biết bao nhiêu nút bị ẩn. | !["Node Class"](docs/documentation/node_class.png "Node Class")
| **Kiểu** | !["Node Type"](docs/documentation/node_type.png "Node Type")
| **Kiểu dữ liệu** | !["Node Typedef"](docs/documentation/node_typedef.png "Node Typedef")
| **Biến & trường** | !["Node Variable"](docs/documentation/node_variable.png "Node Variable")
| **Hàm & phương thức** | !["Node Function"](docs/documentation/node_function.png "Node Function")
| **Enum & hằng số enum** | !["Node Enum"](docs/documentation/node_enum.png "Node Enum")
| **Tập**: tập các nốt gộp các nốt để giảm kích thước của màn hình đồ thị. Tên gọi mô tả loại nốt nào được nhóm lại. Con số cho biết có bao nhiêu nốt được nhóm lại.| !["Node Bundle"](docs/documentation/node_bundle.png "Node Bundle")
| **Nhóm: nốt nhóm cho thấy tất cả các nốt con bên trong đều có điểm chung nào đó, ví dụ như cùng một tập tin hoặc namespace. | !["Node Group"](docs/documentation/node_group.png "Node Group")

**Tương tác**

- Ấn vào nốt để chọn.
- Kéo một nốt để thay đổi vị trí của nó.
- Nhấp vào biểu tượng mũi tên trong các nốt lớp để mở rộng và thu gọn chúng.
- Nhấp vào một tập nốt để mở rộng nó.
- Di chuột qua một nốt để xem chú thích hiển thị loại nốt đó.

### Cạnh

Màu sắc hiển thị tương ứng với bảng màu mặc định.

| Kiểu cạnh | Ảnh
| --- | ---
| **File Include** | !["Edge Include"](docs/documentation/edge_include.png "Edge Include")
| **Sử dụng kiểu dữ liệu** | !["Edge Use"](docs/documentation/edge_type_usage.png "Edge Use")
| **Sử dụng biến** | !["Edge Variable Use"](docs/documentation/edge_variable_use.png "Edge Variable Use")
| **Gọi hàm** | !["Edge Call"](docs/documentation/edge_call.png "Edge Call")
| **Kế thừa** | !["Edge Inheritance"](docs/documentation/edge_inheritance.png "Edge Inheritance")
| **Ghi đè phương thức** | !["Edge Override"](docs/documentation/edge_override.png "Edge Override")
| **Chuyên môn hóa mẫu & sử dụng lập luận mẫu** | !["Edge Template Param"](docs/documentation/edge_template_param.png "Edge Template Param")
| **Chuyên môn hóa thành viên mẫu** | !["Edget Template Member Specialization"](docs/documentation/edge_template_member_specialization.png "Edget Template Member Specialization")
| **Gộp cạnh**: Gộp nhiều cạnh giữa các nút con của hai nút. Độ dày của cạnh cho biết số lượng cạnh được gộp. Di chuột qua cạnh để xem số lượng cạnh được gộp. | !["Edge Bunled Edges"](docs/documentation/edge_bundled_edges.png "Edge Bunled Edges")

**Thao tác**

- Nhấn vào một cạnh để xem vị trí của nó trong [màn hình mã nguồn](#màn-hình-mã-nguồn).
- Nhấn vào một nhóm các cạnh để xem tất cả các cạnh tương ứng.
- Di chuột qua một cạnh để xem chú giải hiển thị loại cạnh đó.

### Tuỳ chỉnh liên kết 

Sử dụng thanh công cụ ở góc trên bên trái, bạn có thể hiển thị toàn bộ biểu đồ hàm gọi, chuỗi kế thừa hoặc cây bao gồm tên nếu tên được chọn. Hoặc bạn có thể sử dụng hộp thoại tuỳ chỉnh liên kết để hiển thị biểu đồ dựa trên các tiêu chí tuỳ chỉnh.

!["Call Graph"](docs/documentation/call_graph.png "Call Graph")

**Thao tác**

- Nhấp vào nút mũi tên để mở rộng/thu gọn `Custom Trail`. 
- Nhấp vào `Custom Trail Dialog` để hiển thị `Custom Trail Dialog`.
- Nhấp vào `Predefined Custom Trail` để hiển thị biểu đồ các nốt phụ thuộc dựa trên tên được chọn.
- Thay đổi vị trí thanh trượt để thay đổi độ sâu tối đa của biểu đồ. Di chuyển thanh trượt lên trên cùng sẽ sử dụng độ sâu vô hạn.
- Nhấp vào một nốt để kích hoạt nó.
- Nhấp vào một cạnh để hiển thị vị trí trong mã nguồn của nó trong [màn hình mã nguồn](#màn-hình-mã-nguồn).

### Hộp thoại tuỳ chỉnh liên kết

Bạn có thể truy cập hộp thoại tuỳ chỉnh liên kết trong phần điều khiển đường dẫn ở góc trên bên trái của màn hình đồ thị. Hộp thoại này cho phép hiển thị các tuỳ chỉnh liên kết dựa trên các tiêu chí nhất định.

<img src="docs/documentation/custom_trail.png" width="700" alt="Custom Trail">

Mỗi tuỳ chỉnh liên kết đều có một tên bắt đầu cụ thể, sau đó có thể chọn 1 trong 3 chế độ khác nhau:

| Chế độ | Mô tả
| --- | ---
| **To Target Symbol** | Chỉ định tên mục tiêu. Đồ thị sẽ chỉ chứa các đường dẫn từ điểm gốc đến tên mục tiêu.
| **All Referenced** | Đồ thị sẽ chỉ chứa các nốt được tham chiếu bởi tên gốc.
| **All Referencing** | Đồ thị sẽ chỉ chứa các nốt phụ thuộc vào tên gốc.

Các tuỳ chọn bổ sung cho phép xác định thông tin nào nên được hiển thị:

| Tuỳ chọn | Mô tả
| --- | ---
| **Maximum Depth** | Xác định độ sâu của đồ thị. Khi tìm kiếm các đường dẫn từ điểm gốc đến mục tiêu, tất cả các đường dẫn vượt quá độ sâu này sẽ không được tìm thấy.
| **Layout Direction** | Xác định xem đồ thị nên được hiển thị theo chiều dọc hay chiều ngang.
| **Node Filters** | Xác định loại nốt nào sẽ là một phần của đồ thị. Chỉ các loại nốt có trong dự án mới được hiển thị. Cài đặt này bị bỏ qua đối với các nốt gốc và đích.
| **Edge Filters** | Xác định loại cạnh nào sẽ là một phần của đồ thị kết quả. Chỉ các loại nốt có trong dự án mới được hiển thị. Cạnh đặc biệt "member" xác định xem mối quan hệ cha-con có được coi là cạnh hay không.

**Thao tác**

- Tìm kiếm tên bắt đầu và đích bằng thanh tìm kiếm. Các thao tác tương tự như trong [thanh tìm kiếm](#thanh-tìm-kiếm).
- Kéo thanh `Max Depth` để thay đổi độ sâu tối đa của đồ thị.
- Nhấp vào `Check All` và `Uncheck All` để chọn tất cả các bộ lọc nốt hoặc cạnh có sẵn.
- Nhấp vào `Cancel` để đóng hộp thoại.
- Nhấp vào `Search` để tìm kiếm liên kết tuỳ chỉnh bằng cách sử dụng các tuỳ chọn đã chọn.

### Nhóm nốt

Sử dụng các nút nhóm ở góc trên bên trái, bạn có thể xác định xem các nút trong đồ thị có nên được nhóm theo namespace/gói hoặc tập tin hay không.

!["Grouping Buttons"](docs/documentation/grouping_buttons.png "Grouping Buttons")

#### Namespace/Gói

Tất cả các nốt cùng namespace hoặc gói được nhóm lại với nhau trong một nốt nhóm riêng biệt.

<img src="docs/documentation/grouping_namespace.png" width="800" alt="Grouping Namespace">

#### Nhóm tập tin

Tất cả các nốt được định nghĩa trong cùng một mã nguồn được nhóm lại với nhau trong một nốt nhóm riêng biệt.

<img src="docs/documentation/grouping_file.png" width="800" alt="Grouping File">

**Thao tác**

- Nhấp vào tên nhóm để chọn namespace/gói hoặc nốt tập tin tương ứng.

### Chú thích đồ thị

Nhấp vào nốt `?` ở góc dưới bên phải của màn hình đồ thị hoặc nhập từ khoá `legend` ở [thanh tìm kiếm](#thanh-tìm-kiếm) để hiển thị chú thích. Nó giúp bạn có một cái nhìn tổng quan về các loại nốt và cạch khác nhau cũng như các ví dụ về bố cục đồ thị.

!["Graph Legend"](docs/documentation/graph_legend.png "Graph Legend")

## Màn hình mã nguồn

Màn hình mã nguồn hiển thị mã nguồn tương ứng của các tên được chọn. Màn hình mã nguồn có hai chế độ. Ở chế độ danh sách, nó chứa danh sách một hoặc nhiều tập tin. Ở chế độ đơn, nó hiển thị đầy đủ mã nguồn của một tập tin.

**Tương tác**

- Lặp lại các tham chiếu của ký hiệu hiện đang hoạt động bằng cách sử dụng điều hướng `reference` ở góc trên bên trái.
- Lặp lại các tham chiếu cục bộ của biến cục bộ được chọn hoặc nhiều tham chiếu của một tên trong phạm vi cục bộ bằng cách sử dụng thanh điều hướng `local reference` ở giữa. 
- Chuyển đổi giữa chế độ `Snippet List` và `Single File` bằng cách chọn `mode selection` ở góc trên bên phải.
- Cuộc lên và xuống để xem các tập tin nguồn khác nhau.

<img src="docs/documentation/code_view_show_errors.png" width="800" alt="Code View Show Errors">

Nếu một tập tin đang xem có lỗi trong quá trình phân tích, biểu tượng của tập tin đó sẽ chứa dấu `x` và có một nút là `Show Errors` hiển thị trên thanh tiêu đề của tập tin. Nhấp vào `Show Errors` sẽ chỉ hiển thị cho bạn các lỗi khiến tập tin này không hoàn thành ở [thẻ lỗi](#thẻ-lỗi).

### Chế độ danh sách đoạn mã

Ở chế độ này, màn hình mã nguồn sẽ hiển thị tất cả tham chiếu của tên được chọn cùng lúc. Đoạn mã ở trên cùng sẽ hiển thị định nghĩa của tên nếu có.

#### Tập tin

Mỗi tập tin có thanh tiêu đề cùng với tên tập tin. Nhấp vào thanh tiêu đề sẽ thay đổi trạng thái hiển thị. Có 2 trạng thái khác nhau:

- **Thu nhỏ:** tập tin không hiển thị nội dung của nó.

<img src="docs/documentation/snippet_minimized.png" width="800" alt="Snippet Minimized">

- **Đoạn mã:** tập tin hiển thị các đoạn mã chứa các vị trí được làm nổi bật được phân tách bằng dòng.

<img src="docs/documentation/snippet_snippets.png" width="800" alt="Snippet Snippets">

**Thao tác**

- Di chuột qua tiêu đề để xem đường dẫn đầy đủ của tập tin.
- Nhập vào tiêu đề để kích hoạt nốt tương ứng của tập tin và chuyển sang chế độ xem đầy đủ mã nguồn.
- Nhấp vào thanh tiêu đề để thu nhỏ tập tin hoặc hiển một đoạn mã của nó.
- Nhấp vào nút đoạn mã để chuyển sang chế độ xem đoạn mã hoặc toàn bộ tập tin, tuỳ thuộc vào nốt tập tin.
- Nhấp vào nút phóng to để chuyển sang [chế độ tập tin đơn](#chế-độ-tập-tin-đơn).

#### Đoạn mã

Một đoạn mã chứa các dòng cần quan tâm cho tên được chọn, được bao quanh bởi một số dòng khác để cung cấp thêm ngữ cảnh. Các tên khác được Sourcetrail phân tích sẽ được đóng khung bằng một hộp khi di chuột qua. Ở đây, Sourcetrail phân biệt giữa các tên cục bộ và tên có thể liên quan đến bất kỳ phần nào khác của mã nguồn. Trong trường hợp đoạn mã là một phần của một lớp (class), hàm (function) hoặc namespace, đầu đoạn mã cung cấp sẽ có thêm một dòng bổ sung về ngữ cảnh của đoạn mã (ví dụ: phạm vi xung quanh,...).

<img src="docs/documentation/code_view_snippet.png" width="800" alt="Code View Snippet">

**Tương tác**

- Nhấp vào dòng trên cùng để hiển thị toàn bộ phạm vi xung quanh đoạn mã.
- Nhấp vào biểu tượng được đóng khung để chọn nó.
- Nhấp vào biểu tượng cục bộ được đóng khung để làm nổi bật tất cả những vị trí của nó trong đoạn mã hiển thị.

### Chế độ tập tin đơn

Ở chế độ tập tin đơn, bạn sẽ chỉ thấy một tập tin tại một thời điểm. Tập tin đầu tiên được hiển thị thường là tập tin chứa định nghĩa của đoạn mã được chọn nếu có. Ngoài ra, giao diện người dùng giống như trong [chế độ danh sách đoạn mã](#chế-độ-danh-sách-đoạn-mã).

<img src="docs/documentation/code_view_single.png" width="800" alt="Code View Single">

**Tương tác**

- Di chuột qua tiêu đề để xem đường dẫn đầy đủ của tập tin.
- Nhấp vào tiêu đề để kích hoạt nốt tương ứng của tập tin.
- Nhấp vào nút đoạn mã để chuyển sang [chế độ danh sách đoạn mã](#chế-độ-danh-sách-đoạn-mã).

## Màn hình tìm kiếm

Màn hình tìm kiếm chứa thanh tìm kiếm và một số thành phần liên quan đến giao diện người dùng.

<img src="docs/documentation/search_view.png" width="800" alt="Search View">

### Quay lại, tiến lên và lịch sử

Nút `Backward` bên trái cho phép bạn hoàn tác các thao tác điều hướng gần đây nhất và nút `Forward` bên phải cho phép bạn thực hiện lại các thao tác điều hướng đã hoàn tác. Cả hai nút chỉ khả dụng khi các thao tác tương ứng khả dụng. 

Nút ở giữa hiển thị danh sách các tên được chọn gần đây. Chọn một tên để kích hoạt nó.

!["Undo Redo View"](docs/documentation/undo_redo_buttons.png "Undo Redo View")

!["History List"](docs/documentation/history_list.png "History List")

**Tương tác**

- Di chuột qua các nút để xem chú thích.
- Nhấn vào các nút để thực hiện tạo tác tương ứng.
- Nhấn vào nút lịch sử để hiển thị ngăn xếp các tên và nhấp vào một đối tượng để kích hoạt.

### Làm mới

Nút làm mới cho phép bạn làm mới dự án hiện tại và phân tích lại toàn bộ các tập tin đã được cập nhật, thêm vào và xoá. Để phân tích lại toàn bộ dự án, hãy chọn tuỳ chọn **Force Refresh** từ [menu chỉnh sửa](#edit).

!["Refresh Button"](docs/documentation/refresh_button.png "Refresh Button")

**Tương tác**

- Di chuột qua các nút để xem chú giải.
- Nhấn nút làm mới để làm mới dự án

### Nút tổng quan

Hiển thị màn hình tổng quan, cung cấp tóm tắt về dự án hiện tại. Màn hình tổng quan được hiển thị sau khi dự án được nạp. Hoặc sử dụng phím tắt [đến tổng quan](#phím-tắt)

!["Overview Button"](docs/documentation/overview_button.png "Overview Button")

**Thao tác**

- Nhấn nút tổng quan để hiển thị tổng quan dự án.

### Thanh tìm kiếm

Thanh tìm kiếm cho phép bạn nhập yêu cầu tìm kiếm để tìm một trong các tên đã được phân tích của Sourcetrail. Hiện tại, chức năng này chưa thể tìm kiếm đầy đủ nội dung trên tất cả tập tin. Trường tìm kiếm cho phép hầu hết các thao tác chỉnh sửa văn bản thông thường của các trường văn bản khác. Khi bạn nhập yêu cầu, [thanh gợi ý](#thanh-gợi-ý) sẽ hiển thị kết quả tìm kiếm phù hợp với chuỗi bạn đã nhập.

!["Search Bar"](docs/documentation/search_bar.png "Search Bar")

**Thao tác**

- Tập trung vào thanh tìm kiếm bằng cách nhập vào đó hoặc sử dụng thao tác [tìm tên](#tìm-tên).
- Nhập yêu cầu tìm kiếm của bạn bằng cách gõ bàn phím.
- Nhấm phím Enter hoặc nhấp vào biểu tượng tìm kiếm ở bên phải để bắt đầu tìm kiếm.
- Thanh tìm kiếm cho phép hầu hết các tương tác quen thuộc từ các trường văn bản khác như di chuyển con trỏ, sao chép & dán và chọn văn bản.

### Thanh gợi ý 

Thanh tự động hoàn thành hiển thị tất cả các [nốt](#nốt) khớp với các yêu cầu tìm kiếm của bạn trong tất cả các tên được phân tích. Kết quả khớp được xác đỉnh bởi thuật toán khớp mờ, cho phép bạn bỏ qua các ký tự. Thanh gợi ý hiển thi các ký tự nào trong từ khớp và hiển thị màu nốt tương ứng. Loại nốt được hiển thị ở bên phải.

<img src="docs/documentation/search_view_completion.png" width="800" alt="Search View Completion">

**Tương tác**

- Sử dụng các phím mũi tên lên và xuống để chuyển đổi giữa các kết quả tìm kiếm.
- Nhấm phím tab hoặc nhấp vào kết quả tìm kiếm sẽ chèn nó vào trường tìm kiếm.
- Nhấm phím Enter sẽ chọn kết quả tìm kiếm và gửi yêu cầu tìm kiếm.

### Từ khoá

Ngoài ra, chế độ xem tìm kiếm cung cấp các từ khoá cụ thể để chọn một nhóm tên nhất định.

| Từ khoá | Tác dụng
| --- | ---
| **overview** | Hiển thị tổng quan về tất cả các tên được phân tích trong [màn hình đồ thị](#màn-hình-đồ-thị) và một số thống kê trong [màn hình mã nguồn](#màn-hình-mã-nguồn).
| **error** | Hiển thị tất cả các lỗi trong [màn hình mã nguồn](#màn-hình-mã-nguồn).

### Tìm kiếm đầy đủ văn bản

Tìm kiếm một chuỗi ký tự nhất định trong tất cả các tập tin đã được phân tích bằng cách đặt `?` ở đầu truy vấn tìm kiếm của bạn. Mặc định, tìm kiếm toàn văn bản không phân biệt chữ hoa chữ thường, hãy sử dụng `??` để phân biệt chữ hoa thường.

!["Search View Fulltext"](docs/documentation/search_view_fulltext.png "Search View Fulltext")

**Thao tác**

- Bắt đầu tìm kiếm bằng `?` hoặc sử dụng thao tác [tìm văn bản](#phím-tắt) để tìm kiếm toàn văn bản không phân biệt chữ hoa thường.
- Bắt đầu tìm kiếm bằng `??` để tìm kiếm toàn văn bản phân biệt chữ hoa thường.

### Nút đánh dấu trang

Nút `Bookmark Active Symbol` ở bên trái mở [trình tạo đánh dấu trang](#trình-tạo-đánh-dấu-trang) để tạo đánh dấu trang mới. Nút `Bookmark Manager` ở bên phải được sử dụng để hiển thị [quản lý đánh dấu trang](#quản-lý-đánh-dấu-trang) để mở và chỉnh sửa các đánh dấu trang của bạn.

!["Bookmark Buttons"](docs/documentation/bookmark_buttons.png "Bookmark Buttons")

**Thao tác**

- Di chuột qua các nút để xem chú thích công cụ.
- Nhấn vào các nút để thực hiện hành động tương ứng.

### Trình tạo đánh dấu trang

Sử dụng trình tạo đánh dấu trang để tạo hoặc chỉnh sửa đánh dấu trang.

<img src="docs/documentation/bookmark_creator.png" width="700" alt="Bookmark Creator">

| Cài đặt | Mô tả
| --- | ---
| **Name** | Tên của đánh dấu trang. Ban đầu, tên của nốt hoặc cạnh được sử dụng.
| **Comment** | Thêm tuỳ chọn bình luận vào đánh dấu trang.
| **Category** | Thêm đánh dấu trang vào một danh mục cụ thể. Các đánh dấu trang cùng danh mục sẽ được nhóm lại với nhau trong [quản lý đánh dấu trang](#quản-lý-đánh-dấu-trang).

**Thao tác**

- Nhấn `Cancel` để đóng mà không thay đổi.
- Nhấn `Create/Save` để lưu đánh dấu trang hoặc áp dụng các thay đổi.

### Quản lý đánh dấu trang

Sử dụng quản lý đánh dấu trang để xem và các đánh dấu trang. Các đánh dấu trang được hiển thị theo dòng bên dưới danh mục tương ứng. Nếu một dấu trang không có danh mục, nó sẽ được đặt trong danh mục `default`. Các nút để xoá/chỉnh sửa danh mục hoặc dấu trang chỉ hiển thị khi di chuột qua dòng tương ứng. Thông tin đánh dấu trang được lưu trữ trong tập tin `.srctrlbm` riêng biệt bên cạnh tập tin `.strtrlprj` của bạn.

<img src="docs/documentation/bookmark_manager.png" width="800" alt="Bookmark Manager">

**Thao tác**

- Nhấn vào tên để mở đánh dấu trang.
- Mở/thu gọn bình luận đánh dấu trang bằng cách nhấp vào bên trong dòng đánh dấu trang.
- Nhấp vào nút `Edit` để thay đổi nội dung của dấu trang.
- Nhấp vào nút `Delete` tại một dấu trang để xoá nó.
- Mở/thu gọn các danh mục bằng nhấp vào dòng của chúng.
- Nhấp vào nút `Delete` tại một danh mục để xoá danh mục đó và tất cả các dấu trang bên trong.
- Nhấp vào `Show` để chuyển đổi giữa hiển thị các nốt và/hoặc các cạnh.
- Nhấp vào `Sorting` để thay đổi thứ tự các dấu trang trong các danh mục của chúng.

## Màn hình trạng thái

Màn hình này cung cấp các thẻ khác nhau với thông tin về dự án của bạn. Màn hình này mặc định bị ẩn.

**Thao tác**

- Nhấp vào các tiêu đề trên cùng để chuyển đổi giữa các thẻ.
- Nhấp vào nút `x` ở góc trên bên phải để đóng.

### Thẻ trạng thái

Bảng này cung cấp một số thông tin về các cập nhật trạng thái trong khi chạy Sourcetrail. Nó có thể hữu ích để tìm ra lý do tại sao hoạt động không như mong đợi.

!["Status View Status"](docs/documentation/status_view_status.png "Status View Status")

**Thao tác**

- Nhấp đúp vào một ô trong bảng để chọn văn bản để sao chép và dán.
- Sử dụng các hộp kiểm tra bên dưới để lọc các thông báo hiển thị theo loại.
- Nhấp vào `Clear Table` để xoá tất cả các hàng khỏi bảng.

### Thẻ lỗi

Danh sách này hiển thị các lỗi xảy ra trong quá trình phân tích.

!["Status View Error"](docs/documentation/status_view_error.png "Status View Error")

Thông tin được hiển thị bao gồm:
- **Type:** LỖI hoặc NGHIÊM TRỌNG. Lỗi NGHIÊM TRỌNG gây ra nhiều thông tin bị thiếu vì trình phân tích phải dừng lại ở lỗi này.
- **Error message:** nội dung lỗi.
- **File:** tập tin bị lỗi.
- **Line number:** dòng có lỗi.
- **Indexed:** tập tin đã được phân tích hay chưa.
- **Translation Unit:** tập tin gây ra lỗi này trong khi đang được phân tích.

**Thao tác**

- Nhấp vào một dòng lỗi để xem vị trí của lỗi trong [màn hình mã nguồn](#màn-hình-mã-nguồn).
- Nhấp vào tiêu đề cột để sắp xếp các lỗi tăng dần hoặc giảm dần theo thông tin.
- Nhấp đúp vào ô trong bảng để chọn văn bản cần sao chép và dán.
- Sử dụng các hộp kiểm tra bên dưới để lọc các lỗi hiển thị theo các tiêu chí nhất định.
- Nhấp vào `Edit Project` để mở [hộp thoại sửa dự án](#trình-thiết-lập-dự-án).

## Chú thích công cụ

Hiển thị thông tin về các tên được di chuột trong [màn hình đồ thị](#màn-hình-đồ-thị) và [màn hình mã nguồn](#màn-hình-mã-nguồn).

<img src="docs/documentation/tooltip.png" width="500" alt="Tooltip">

Thông tin được hiển thị bao gồm:
- Loại tên
- Khả năng truy cập (ví dụ: công khai hoặc riêng tư)
- Số lượng tham chiếu
- Tên
- Tên có thể nhấp vào được đối với các biến và các trường toàn cục
- Đầy đủ dấu hiệu nhận biết về tên kiểu trả về có thể nhấp vào được và tên kiểu tham số cho các hàm và phương thức.

**Thao tác**

- Nhấp vào tên để kích hoạt chúng.

# Thiết lập dự án

Thiết lập dự án trong Sourcetrail được thực hiện thông qua [trình thiết lập dự án](#trình-thiết-lập-dự-án), có thể truy cập trên [cửa sổ bắt đầu](#cửa-sổ-bắt-đầu) bằng cách ấn vào `New Project` hoặc từ [menu dự án](#project) với thao tác `New Project`.

Trình thiết lập dự án cho phép bạn tạo một dự án Sourcetrail mới. Bằng cách điền tên và đường dẫn của dự án và thêm một số **Nhóm mã nguồn** để khai báo những tập tin mã nguồn cần được phân tích. Có nhiều cách để tạo Nhóm mã nguồn, đối với hầu hết các dự án, chỉ cần thêm một Nhóm mã nguồn là đủ.

Sau khi bạn đã thêm Nhóm mã nguồn vào dự án của mình, hộp thoại này cho phép bạn chỉnh sửa thiết lập của chúng, cũng như xoá hoặc sao chép chúng.

<img src="docs/documentation/project_setup_wizard_start.png" width="800" alt="Project Setup Wizard Start">

| Cài đặt | Mô tả
| --- | ---
| Sourcetrail Project Name | Tên của dự án. Đây cũng sẽ là tên của tập tin `.srctrlprj` được tạo bởi Sourcetrail.
| Sourcetrail Project Location | Chọn đường dẫn lưu của tập tin dự án từ hộp thoại.

**Thao tác**

- Nhấp vào `Cancel` hoặc nhấn `ESC` sẽ đóng cửa sổ và huỷ quá trình thiết lập.
- Nhấp vào `Add Source Group` sẽ đưa bạn đến trang Tạo nhóm mã nguồn mới.
- Nhấp vào `General` sẽ đưa bạn đến trang thiết lập tên và đường dẫn của dự án.
- Nhấp vào `Source Group` để chỉnh sửa thiết lập và thay đổi tên của nhóm.
- Nhấp vào `+` bên dưới danh sách Nhóm mã nguồn sẽ đưa bạn đến trang tạo Nhóm mã nguồn mới.
- Nhấp vào `-` bên dưới danh sách Nhóm mã nguồn sẽ xoá Nhóm mã nguồn hiện đang được chọn.
- Nhấp vào `☐` bên dưới danh sách Nhóm mã nguồn sẽ sao chép Nhóm mã nguồn hiện đang được chọn.
- Nhấp vào `Create` sẽ đóng cửa sổ này và tạo dự án mới.

Cửa sổ này cũng được sử dụng khi chỉnh sửa dự án. Bạn có thể chọn một Nhóm mã nguồn từ danh sách bên trái và chỉnh sửa nó. Ngoài ra, bạn cũng có thể đặt cờ `active` của một số Nhóm mã nguồn thành `false` để chúng sẽ không được phân tích khi làm dự án mới.

<img src="docs/documentation/project_setup_wizard_edit.png" width="800" alt="Project Setup Wizard Edit">

**Thao tác**

- Thay đổi tên Nhóm mã nguồn.
- Kích hoạt/vô hiệu hoá Nhóm mã nguồn được phân tích.
- Thay đổi bất kỳ cài đặt nào được xác định trong quá trình thiết lập Nhóm mã nguồn.

## Chọn loại nhóm mã nguồn

Khi tạo một nhóm mã nguồn mới, bạn có thể chọn giữa các phương pháp thiết lập khác nhau tuỳ thuộc vào ngôn ngữ dự án của bạn. Vui lòng xem [hướng dẫn nhanh](#hướng-dẫn-sử-dụng) để được trợ giúp về việc chọn đúng loại Nhóm mã nguồn.

<img src="docs/documentation/project_setup_wizard_source_group_type.png" width="600" alt="Project Setup Wizard Source Group Type">

**Thao tác**

- Chọn ngôn ngữ được sử dụng trong `Code Project`.
- Chọn loại nhóm mã nguồn sẽ xác định cách bạn nhập tất cả thông tin cần thiết.
- Nhấp vào `Cancel` hoặc nhấn `ESC` sẽ đóng cửa sổ.
- Nhấp vào `Next` sẽ đưa bạn đến bước tiếp theo của quá trình thiết lập.


## Empty C/C++ Source Group

Empty C/C++ Source Groups có các tuỳ chọn thiết lập sau:

| Thiết lập | Mô tả
| --- | ---
| Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích cho dự án của bạn. Thông thường, chuẩn ngôn ngữ mới nhất sẽ được chọn trước (xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ)).
| Cross-compilation | Chọn **Use specific target** và sau đó chọn một nền tảng làm mục tiêu cụ thể. Vui lòng xem tài liệu trình biên dịch Clang về [cross-compilation](https://clang.llvm.org/docs/CrossCompilation.html) để biết thêm thông tin.
| Files & Directories to Index | Các đường dẫn này xác định các tập tin và thư mục sẽ được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin nguồn và tập tin tiêu đề. Nếu mã nguồn của dự án nằm ở một vị trí, nhưng các tệp tin mã nguồn được tạo ra được lưu ở một vị trí khác, bạn cũng cần thêm thư mục đó. Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.cpp`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này. 
| Include Paths | Include Paths được dùng để quyết các lệnh `#include` trong các tập tin mã nguồn và tập tin tiêu đề được phân tích. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ `-I` hoặc `-iquote` . Thêm tất cả các đường dẫn mà các lệnh `#include` trong toàn bộ dự án của bạn có liên quan đến. Nếu tất cả lệnh `#include` được chỉ định liên quan đến thư mục gốc của dự án, vui lòng thêm thư mục gốc đó vào đây. Nếu dự án của bạn có sử dụng thư viện bên ngoài (ví dụ: boost,...), vui lòng thêm các thư mục đó (ví dụ: thêm `path/to/boost_home/include`). Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Global Include Paths | Global Include Paths sẽ được sử dụng trong tất cả dự án của bạn, ngoài *Include Paths* dành riêng cho dự án. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ '-ísystem'. Sử dụng chúng để thêm các đường dẫn tiêu đề hệ thống (xem [tìm thư viện hệ thống](#thư-viện-hệ-thống) hoặc sử dụng tính năng tự động phát hiện bên dưới).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Framework Search Paths (chỉ MacOS) | Những đường dẫn này dùng để tìm các tập tin `.framework` được sử bởi dự án của bạn. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).)
| Global Framework Search Paths (chỉ MacOS) | Framework Search Paths được dùng cho toàn bộ các dự án. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn). Để biết hướng dẫn về cách tìm đường dẫn thư viện hệ thống, xem [tìm thư viện hệ thống](#tìm-thư-viện-hệ-thống)).
| Compiler Flags | Xác định các cờ trình biên dịch bổ sung được sử dụng trong quá trình phân tích, bao gồm cả dấu gạch ngang (ví dụ: dùng `-DRELEASE` để thêm `#define RELEASE`).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Precompiled Header File | Chọn đường dẫn đến tập tin tiêu đề sẽ được sử dụng để tạo tập tin tiêu đề được biên dịch trước. Tập tin tiêu đề được biên dịch trước sẽ được tạo trước khi phân tích. Nếu không cung cấp đường dẫn, sẽ không có tập tin tiêu đề được biên dịch trước nào được tạo.
| Precompiled Header Flags | Xác định xem các cờ biên dịch đã cung cấp có nên sử dụng cho việc tạo tập tin tiêu được biên dịch trước hay không và xác định thêm các cờ chỉ dành cho việc tạo các tập tin tiêu đề đã biên dịch trước<br /><br />Để biết hướng dẫn về các thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).

## C/C++ from Compilation Database

Nếu bạn đang sử dụng [CMake](https://cmake.org/), bạn có thể sử dụng cờ `CMAKE_EXPORT_COMPILE_COMMANDS=On` và sau đó dử dụng tập tin `.json` đã xuất để thiết lập dự án Sourcetrail.

Đối với các dự án không sử dụng CMake, bạn có thể sử dụng [Bear](https://github.com/rizsotto/Bear). Bear tạo ra một tập tin `.json` trong quá trình mô phỏng biên dịch. Bear đã được thử nghiệm trên FreeBSD, GNU/Linux hoặc OS X.

C/C++ from Compilation Database cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Compilation Database | Chọn tập tin cơ sở dữ liệu biên dịch cho dự án. Sourcetrail sẽ phân tích dự án của bạn dựa trên các lệnh biên dịch mà tập tin này chứa, sử dụng tất cả các đường dẫn được thêm bằng `include` và cờ biên dịch của các lệnh biên dịch này. Dự án sẽ được cập nhật với các thay đổi trong cơ sở dữ liệu biên dịch sau mỗi làn làm mới. Bạn có thể sử dụng các biên môi trường với `${ENV_VAR}`.
| Header Files & Directories to Index | Cơ sở dữ liệu biên dịch của bạn đã cho biết các tập tin nào thuộc dự án. Nhưng Sourcetrail vẫn cần biết các tập tin tiêu đề nào cần phân tích và tập tin nào cần bỏ qua. Việc chọn bỏ qua phân tích các tập tin tiêu đề hệ thống hoặc thư viện bên ngoài sẽ cải thiện đáng kể thổng thể hiệu năng phân tích dự án. Sử dụng danh sách này để xác định các tập tin tiêu đề nào nên được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin có trong đó. Bạn có thể sử dụng các biến môi trường với ${ENV_VAR}.<br />**Gợi ý**: chỉ cần nhập đường dẫn gốc của dự án nếu bạn muốn Sourcetrail phân tích tất cả tập tin tiêu đề có trong đó mà nó gặp phải.<br /><br />Để biết đướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại bỏ quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho các ký tự trừ `\` hoăc `/` (ví dụ: `src/*/test.h` khớp `src/app/test.h` nhưng không khớp `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý(ví dụ: `src**test.h` khớp `src/app/test.h` cũng như `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Additional Include Paths | Additional Include Paths được dùng để quyết các lệnh `#include` trong các tập tin mã nguồn và tập tin tiêu đề được phân tích. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ `-I` hoặc `-iquote` . Thêm tất cả các đường dẫn mà các lệnh `#include` trong toàn bộ dự án của bạn có liên quan đến. Nếu tất cả lệnh `#include` được chỉ định liên quan đến thư mục gốc của dự án, vui lòng thêm thư mục gốc đó vào đây. Nếu dự án của bạn có sử dụng thư viện bên ngoài (ví dụ: boost,...), vui lòng thêm các thư mục đó (ví dụ: thêm `path/to/boost_home/include`). Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Global Include Paths | Global Include Paths sẽ được sử dụng trong tất cả dự án của bạn, ngoài *Include Paths* dành riêng cho dự án. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ '-ísystem'. Sử dụng chúng để thêm các đường dẫn tiêu đề hệ thống (xem [tìm thư viện hệ thống](#thư-viện-hệ-thống) hoặc sử dụng tính năng tự động phát hiện bên dưới).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Framework Search Paths (chỉ MacOS) | Những đường dẫn này dùng để tìm các tập tin `.framework` được sử bởi dự án của bạn. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).)
| Global Framework Search Paths (macOS only) | Framework Search Paths được dùng cho toàn bộ các dự án. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn). Để biết hướng dẫn về cách tìm đường dẫn thư viện hệ thống, xem [tìm thư viện hệ thống](#tìm-thư-viện-hệ-thống)).
| Additional Compiler Flags | Xác định các cờ trình biên dịch bổ sung được sử dụng trong quá trình phân tích, bao gồm cả dấu gạch ngang (ví dụ: dùng `-DRELEASE` để thêm `#define RELEASE`).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Precompiled Header File | Chọn đường dẫn đến tập tin tiêu đề sẽ được sử dụng để tạo tập tin tiêu đề được biên dịch trước. Tập tin tiêu đề được biên dịch trước sẽ được tạo trước khi phân tích. Nếu không cung cấp đường dẫn, sẽ không có tập tin tiêu đề được biên dịch trước nào được tạo.
| Precompiled Header Flags | Xác định xem các cờ biên dịch đã cung cấp có nên sử dụng cho việc tạo tập tin tiêu được biên dịch trước hay không và xác định thêm các cờ chỉ dành cho việc tạo các tập tin tiêu đề đã biên dịch trước<br /><br />Để biết hướng dẫn về các thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).

## C/C++ from Visual Studio

Nếu bạn đang sử dụng Visual Studio, bạn có thể tạo tập tin cơ sở dữ liệu biên dịch bằng [Visual Studio Plugin](#visual-studio) và sử dụng nó để tạo dự án với Sourcetrail.

<img src="docs/documentation/project_setup_wizard_visual_studio.png" width="700" alt="Project Setup Wizard Visual Studio">

**Thao tác**

- Nhấp vào `Create CDB` để thông báo [Visual Studio Plugin](#visual-studio) rằng dự án nên được xuất dứoi dạng cơ sở dữ liệu biên dịch. Vui lòng xem [Visual Studio Plugin](#visual-studio) để biết thêm chi tiết về các bước trong Visual Studio.

> Sau khi hoàn tất quá trình xuất trong Visual Studio, thiết lập Nhóm mã nguồn sử dụng các bước tương tự như tạo Nhóm mã nguồn trực tiếp [từ cơ sở dữ liệu biên dịch](#c-c++-from-compilation-database). 

## C/C++ from Code::Blocks

Nếu bạn đã có một dự án Code::Blocks (`.cbp`), bạn có thể sử dụng nó để tạo dự án với Sourcetrail. Lưu ý rằng tập tin `.cbp` sẽ được tạo bở **QtCreator** nếu tập tin `CMakeLists.txt` được nhập.

C/C++ from Code::Blocks cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| C++ Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích các tập tin C++ của dự án. Thông thường, chuẩn ngôn ngữ nhất được chọn trước ở đây. (Xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ))
| C Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích các tập tin C của dự án. Thông thường, chuẩn ngôn ngữ nhất được chọn trước ở đây. (Xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ))
| Code::Blocks Project | Chọn tập tin `.cbp` cho dự án. Sourcetrail sẽ chỉ phân tích dự án của bạn dựa trên cài đặt được chỉ định bởi tập tin dự án Code::Blocks. Dự án Sourcetrail của bạn sẽ luôn được cập nhật với những thay đổi trong tập tin dự của Code::Blocks mỗi làm được làm mới. Bạn có thể sử dụng các biến môi trường với ${ENV_VAR}.
| Header Files & Directories to Index | Dự án Code::Blocks cho biết các tập tin nào thuộc dự án. Nhưng Sourcetrail vẫn cần biết các tập tin tiêu đề nào cần phân tích và tập tin nào cần bỏ qua. Việc chọn bỏ qua phân tích các tập tin tiêu đề hệ thống hoặc thư viện bên ngoài sẽ cải thiện đáng kể thổng thể hiệu năng phân tích dự án. Sử dụng danh sách này để xác định các tập tin tiêu đề nào nên được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin có trong đó. Bạn có thể sử dụng các biến môi trường với ${ENV_VAR}.<br />**Gợi ý**: chỉ cần nhập đường dẫn gốc của dự án nếu bạn muốn Sourcetrail phân tích tất cả tập tin tiêu đề có trong đó mà nó gặp phải.<br /><br />Để biết đướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.cpp`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này. 
| Additional Include Paths | Additional Include Paths được dùng để quyết các lệnh `#include` trong các tập tin mã nguồn và tập tin tiêu đề được phân tích. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ `-I` hoặc `-iquote` . Thêm tất cả các đường dẫn mà các lệnh `#include` trong toàn bộ dự án của bạn có liên quan đến. Nếu tất cả lệnh `#include` được chỉ định liên quan đến thư mục gốc của dự án, vui lòng thêm thư mục gốc đó vào đây. Nếu dự án của bạn có sử dụng thư viện bên ngoài (ví dụ: boost,...), vui lòng thêm các thư mục đó (ví dụ: thêm `path/to/boost_home/include`). Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Global Include Paths | Global Include Paths sẽ được sử dụng trong tất cả dự án của bạn, ngoài *Include Paths* dành riêng cho dự án. Những đường dẫn này thường được truỳ cho trình biên dịch bằng cờ '-ísystem'. Sử dụng chúng để thêm các đường dẫn tiêu đề hệ thống (xem [tìm thư viện hệ thống](#thư-viện-hệ-thống) hoặc sử dụng tính năng tự động phát hiện bên dưới).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Framework Search Paths (chỉ MacOS) | Những đường dẫn này dùng để tìm các tập tin `.framework` được sử bởi dự án của bạn. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).)
| Global Framework Search Paths (macOS only) | Framework Search Paths được dùng cho toàn bộ các dự án. (<br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn). Để biết hướng dẫn về cách tìm đường dẫn thư viện hệ thống, xem [tìm thư viện hệ thống](#tìm-thư-viện-hệ-thống)).
| Additional Compiler Flags | Xác định các cờ trình biên dịch bổ sung được sử dụng trong quá trình phân tích, bao gồm cả dấu gạch ngang (ví dụ: dùng `-DRELEASE` để thêm `#define RELEASE`).<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).

## Empty Java Source Group

Chọn tuỳ chọn này nếu bạn muốn phân tích các tập tin Java bằng Sourcetrail.

Empty Java Source Group cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích các tập tin C++ của dự án. Thông thường, chuẩn ngôn ngữ nhất được chọn trước ở đây. (Xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ))
| Files & Directories to Index | Các đường dẫn này xác định các tập tin và thư mục sẽ được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin nguồn và tập tin tiêu đề. Nếu mã nguồn của dự án nằm ở một vị trí, nhưng các tệp tin mã nguồn được tạo ra được lưu ở một vị trí khác, bạn cũng cần thêm thư mục đó. Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.java`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này. 
| Class Path | Nhập tất cả các tập tin .jar mà dự án của bạn phụ thuộc vào. Nếu dự án của bạn phụ thuộc vào những tập tin java chưa được biên dịch nên chưa được phân tích, vui lòng thêm thư mục gốc của các tập tin .java vào đây (tập tin chứa tất cả tên gói có liên quan đến). Bạn có thể sử dụng các biên môi trường với ${ENV_VAR}.
| JRE System Library | Đánh dấu vào ô này để sử dụng các thư viện jar của hệ thống JRE được xác định trong cài đặt ứng dụng. Tắt cài đặt này nếu bạn muốn sử dụng thư viện hệ thống JRE khác cho dự án này và thêm các jar tương ứng vào *Class Path*.

## Java Source Group from Gradle

Nếu bạn đang sử dụng Gradle để quản lý và xây dựng dự án của mình, hãy sử dụng cách này để tạo Nhóm mã nguồn Sourcetrail.

Java from Gradle cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích các tập tin C++ của dự án. Thông thường, chuẩn ngôn ngữ nhất được chọn trước ở đây. (Xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ))
| Gradle Project File | Đường dẫn đến tập tin `build.gradle` trong thư mục gốc của dự án Gradle của bạn.
| Should Index Tests | Hộp này cho biết liệu Sourcetrail có phân tích các mã nguồn kiểm thử thuộc dự án Gradle của bạn hay không.
| Intermediate Dependencies Directory | Thư mục nơi Sourcetrail lưu trữ tất cả các `.jar` mà dự án phụ thuộc.
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.java`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này.

## Java Source Group from Maven

Nếu bạn đang sử dụng Maven để quản lý và xây dựng dự án của mình, hãy sử dụng cách này để tạo Nhóm mã nguồn Sourcetrail.

Java Source Group from Maven cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Standard | Chọn chuẩn ngôn ngữ sẽ được sử dụng để phân tích các tập tin C++ của dự án. Thông thường, chuẩn ngôn ngữ nhất được chọn trước ở đây. (Xem [ngôn ngữ hỗ trợ](#ngôn-ngữ-hỗ-trợ))
| Maven Project File | Đường dẫn đến tập tin `pom.xml` trong thư mục gốc của dự án Maven.
| Should Index Tests | Hộp này cho biết liệu Sourcetrail có phân tích các mã nguồn kiểm thử thuộc dự án Maven của bạn hay không.
| Intermediate Dependencies Directory | Thư mục nơi Sourcetrail lưu trữ tất cả các `.jar` mà dự án phụ thuộc.
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.java`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này.

## Empty Python Source Group

Chọn tuỳ chọn này bạn muốn phân tích các tập tin Python bằng Sourcetrail.

Empty Python Source Group cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Python Environment | Bạn có thể xác định đường dẫn đến thu mục hoặc tập tin thực của môi trường (ảo) Python sẽ được sử dụng để giải quyết các phần phụ thuộc trong mã nguồn được phân tích.<br />Nếu bạn chạy:<br />`$ cd c:\dev\python\envs`<br />`$ virtualenv py37`<br />bạn sẽ đặt nó thành `C:\dev\python\envs\py37` hoặc `C:\dev\python\envs\py37\Scripts\python.exe`.<br />Để trống để sử dụng môi trường Python mặc định. Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.
| Files & Directories to Index | Các đường dẫn này xác định các tập tin và thư mục sẽ được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin nguồn và tập tin tiêu đề. Nếu mã nguồn của dự án nằm ở một vị trí, nhưng các tệp tin mã nguồn được tạo ra được lưu ở một vị trí khác, bạn cũng cần thêm thư mục đó. Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.java`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này.

## Custom Command Source Group

Trong Custom Command Source Group, bạn có thể xác định một tập hợp các tập tin và dòng lệnh để phân tích từng tập tin này. Loại Nhóm mã nguồn này được tạo để tích với các bộ phân tích tuỳ chỉnh được viết bằng [SourcetrailDB](https://github.com/CoatiSoftware/SourcetrailDB).

Custom Command Source Group cung cấp các cài đặt sau:

| Cài đặt | Mô tả
| --- | ---
| Custom Command | Dòng lệnh được thực thi cho từng tập tin mã nguồn. Bạn có thể chuyển các tham số được xác định trước khác nhau:<br /><ul><li>**%{SOURCE_FILE_PATH}**: Đường dẫn đến từ tập tin trong nhóm mã nguồn (bắt buộc)</li><li>**%{DATABASE_FILE_PATH}**: Đường dẫn đến cơ sở dữ liệu của dự án</li><li>**%{DATABASE_VERSION}**: Phiên bản cơ sở dữ liệu của phiên bản Sourcetrail đã sử dụng</li><li>**%{PROJECT_FILE_PATH}**: Đường dẫn đến tập tin dự án</li></ul>
| Run in Parallel | Liệu các tập tin có nên được xử lý song song hay không.
| Files & Directories to Index | Các đường dẫn này xác định các tập tin và thư mục sẽ được Sourcetrail phân tích. Cung cấp một thư mục để thêm đệ quy tất cả các tập tin nguồn và tập tin tiêu đề. Nếu mã nguồn của dự án nằm ở một vị trí, nhưng các tệp tin mã nguồn được tạo ra được lưu ở một vị trí khác, bạn cũng cần thêm thư mục đó. Bạn có thể sử dụng các biến môi trường với `${ENV_VAR}`.<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, hãy xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).
| Excluded Files & Directories | 
Các đường dẫn này xác định các tập tin và thư mục sẽ bị loại khỏi quá trình phân tích.<br />Gợi ý:<br /><ul><li>Bạn có thể sử dụng ký tự đại diện `*` để đại diện cho tất cả ký tự, ngoại trừ `\` hoặc `/` (ví dụ: `src/*/test.h` khớp với `src/app/test.h` nhưng không khớp với `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng ký tự đại diện `**` để đại diện cho các ký tự tuỳ ý (ví dụ: `src**test.h` khớp với `src/app/test.h` và `src/app/widget/test.h` hoặc `src/test.h`)</li><li>Bạn có thể sử dụng các biến môn trường với `${ENV_VAR}`<br /><br />Để biết hướng dẫn về cách thêm đường dẫn, xem [khung danh sách đường dẫn](#khung-danh-sách-đường-dẫn).</li></ul>
| Source File Extensions | Xác định các phần mở rộng hợp lệ cho các tập tin mã nguồn bao dấu chấm, ví dụ: `.ext`. Sourcetrail sẽ chỉ cố gắng phân tích các tập tin khớp với một trong các phần mở rộng này.

# Câu hỏi thường gặp

### Tôi có thể sử dụng Sourcetrail với các mã nguồn bí mật không?

Có. Sourcetrail lưu dữ liệu hoàn toàn ngoại tuyến. Sourcetrail chỉ kết nối với Internet để kiểm tra xem có phiên bản mới hơn hay không.

### Điều gì xảy ra với dữ liệu được phân tích?

1. Dữ liệu được lưu trữ trong tập tin `.srctrldb` nằm trong cùng thư mục với tập tin `.srctrlprj`. Tập tin cơ sở dữ liệu này được Sourcetrail sử dụng để phục vụ mục đích cho phép bạn điều hướng mã nguồn của mình và cũng cho phép Sourcetrail mở lại dự án mà không cần phân tích lại mã nguồn.
2. Nếu tính năng ghi nhật ký được bật, một số dữ liệu sẽ được ghi vào tập tin nhật ký được lưu trong [data](#thư-mục-dữ-liệu)/logs. Những nhật ký này có thể hữu ích cho chúng tôi trong việc sửa lỗi. Chúng tôi có thể yêu cầu thông tin nhật ký về các báo cáo lỗi, nhưng bạn chỉ nên cung cấp những tin đó nếu mã nguồn của bạn không được bảo mật.

**Lưu ý:** tập tin `.srctrldb` thực chất là tập tin cơ sở dữ liệu `.sqlite` và có thể được kiếm tra bằng [trình duyệt DB dành cho SQLite](https://sqlitebrowser.org/).
