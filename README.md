# Online-Food-Service-Project
## 1.Thành viên và công việc
- **Duy Nghĩa**:
  - Vị trí: trưởng nhóm
  - Công việc:
    - Phân chia các công việc cho các thành viên
    - Tham gia thiết kế và xây dựng chức năng cơ sở dữ liệu của dự án 
    - Xử lý toàn bộ logic phía BackEnd
- **Thái Hà**:
  - Vị trí: thành viên 
  - Công việc:
    - Tham gia thiết kế và xây dựng chức năng cơ sở dữ liệu của dự án 
    - Thiết kế phần FrontEnd
- **Phan Long**:
  - Vị trí: thành viên 
  - Công việc:
    - Tham gia thiết kế và xây dựng chức năng cơ sở dữ liệu của dự án 
    - Thiết kế phần FrontEnd
- **Mạnh Tuấn**:
  - Vị trí: thành viên 
  - Công việc:
    - Tham gia thiết kế và xây dựng chức năng cơ sở dữ liệu của dự án

## 2.Các chức năng của trang web 
### 2.1.Chức năng tìm kiếm
- Mô tả:
  - Tìm kiếm theo địa điểm nhà hàng
  - Tìm kiếm theo loại sản phẩm: đồ ăn, đồ uống, combo hoặc tất cả
  - Tìm kiếm theo tên sản phẩm hoặc tên nhà hàng
  - Tìm kiếm hợp
### 2.2.Đăng ký, đăng nhập
- Mô tả:
  - Đối với chức năng đăng ký
    - Mật khẩu tối thiểu 6 kí tự
    - Tên tài khoản không kí tự trắng, không dấu
    - Tên email, tên đăng nhập không được trùng giữa các tài khoản
  - Đối với chức năng đăng nhập
    - kiểm tra tên tài khoản và mật khẩu có tồn tại trong bảng user của cơ sở dữ liệu
### 2.3.Gửi thông báo, lời nhắn tới hệ thống 
- Mô tả:
  - Các tài khoản sau khi đăng ký thành công, khi đăng nhập đều có chức năng gửi lời nhắn tới hệ thống gồm: chủ đề và nội dung
  - Bên phía hệ thống sẽ nhận được và có 3 trạng thái: Chưa/Đang/Đã xử lý và có thể nhắn lại tới người dùng thông báo cho họ
  - Các tài khoản người dùng cũng đều hiển thị danh sách thông báo từ phía hệ thống và có 2 trạng thái: Chưa xem và đã xem
### 2.4.Chức năng riêng của người dùng là Khách hàng 
- Mô tả:
  - **Chỉnh sửa thông tin cá nhân**: Đối với khác hàng chỉ có thể sửa được các thông tin sau:
    - Cập nhật ảnh đại diện
    - Cập nhật họ tên nhưng không thể cập nhật tên tài khoản và email đã đăng ký
    - Cập nhật địa chỉ
    - Cập nhật số điện thoại
    - Giới tính
    - Đổi mật khẩu
    - **Nhưng, để đổi thành công đều cần xác nhận mật khẩu hiện tại phải đúng**
   - **Đặt bàn tiệc**: Khách hàng sau khi đăng nhập thành công có thể ghé vào trang view của nhà hàng có nút bấm đặt hàng, hiển thị ra một form đăng ký với các thông tin sau:
     - Ngày đặt
     - Số lượng chỗ ngồi
     - Số điện thoại để bên nhà hàng liên hệ
     - Thêm ghi chú
  - **Mua sản phẩm**: sẽ có 4 trạng thái khi người dùng lướt web để thêm các sản phẩm vào giỏ hàng
     - Chờ xác nhận: khi người dùng thêm các sản phẩm vào giỏ hàng thì những sản phẩm đó sẽ được lưu ở đây. Khi người dùng vào trang giỏ hàng trên thanh menu để kiểm tra sẽ có thể tự điều chỉnh số lượng mua hoặc xóa sản phẩm đang ở giỏ hàng bằng cách giảm số lượng về 0 hoặc nhập 1 số âm (số < 0) vào ô nhập số lượng
     - Chờ nhà hàng xác nhận: khi người dùng bấm nút "Xác nhận mua hàng" phía dưới mỗi đơn hàng thì toàn bộ sản phẩm trong đơn hàng đó sẽ được lưu ở trạng thái "Chờ nhà hàng xác nhận"
     - Chờ giao hàng: Sau khi bên nhà hàng bấm nút "Xác nhận gửi hàng" ở phần "Chờ nhà hàng xác nhận" thì đơn hàng sẽ được lưu ở trạng thái "Chờ giao hàng". 
     - Giao hàng thành công: Nếu sau khi nhận hàng thành công, người dùng bấm nút "Đã nhận hàng" thì đơn hàng sẽ được lưu ở trạng thái "Giao hàng thành công"
  
### 2.5.Chức năng riêng của người dùng là Nhà hàng 
- Mô tả:
  - **Chỉnh sửa thông tin nhà hàng**:
    - Có thể thay đổi các thông tin bản thân chủ(admin) của nhà hàng tương tự như đối với tài khoản khách hàng:
      - Cập nhật ảnh đại diện
      - Cập nhật họ tên nhưng không thể cập nhật tên tài khoản và email đã đăng ký
      - Cập nhật địa chỉ
      - Cập nhật số điện thoại
      - Giới tính
      - Đổi mật khẩu
      - **Nhưng, để đổi thành công đều cần xác nhận mật khẩu hiện tại phải đúng**
     - **Ngoài ra**, ở tài khoản thuộc loại Nhà hàng thì có thể thay đổi thông tin của nhà hàng đó
      - Cập nhật ảnh đại diện cho nhà hàng
      - Cập nhật tên nhà hàng
      - Cập nhật địa chỉ nhà hàng
      - Cập nhật loại hình thức kinh doanh của nhà hàng
  
### 2.6.Chức năng của Admin
- Mô tả:
   - Hiển thị thông tin tổng quan: Cập nhật các công việc, tiến độ cần giải quyết
     - Số lượng khách hàng
     - Số lượng nhà hàng
     - Lỗi hệ thống chưa xử lý
     - Lỗi hệ thống đang xử lý
     - Thông báo từ người dùng chưa xử lý
     - Thông báo từ người dùng đang xử lý
   - Quản lý khách hàng(người dùng)
     - Tìm kiếm thông tin người dùng bằng bộ lọc: tên người dùng (họ tên hoặc tên đăng nhập), loại người dùng(khách hàng hay nhà hàng), số lượng bản ghi trả về
     - Xóa lịch sử đặt hàng: xóa toàn bộ những đơn hàng giao thành công và các thông tin có liên quan đến đơn hàng đó đối với tài khoản đó
     - Xóa tài khoản người dùng: xóa toàn bộ thông tin có liên quan về tài khoản người dùng đó
   - Quản lý nhà hàng
     - Tìm kiếm thông tin nhà hàng bằng bộ lọc: tên/địa chỉ nhà hàng, số lượng bản ghi trả về
     - Xóa thông tin nhà hàng
   - Báo lỗi từ hệ thống
   - Báo lỗi, thông báo từ người dùng
   - Thông báo tới người dùng: có thể thông báo tới tất cả tài khoản người dùng hoặc một người cụ thể bằng tên đăng nhập của họ
## 3.Video demo 
- Link video trên youtube : (https://youtu.be/2y1YJKyC0Gw)


    

