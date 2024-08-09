<%@page import="BEAN.Restaurant"%>
<%@page import="BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang cá nhân</title>

<!-- file css -->
<link rel="stylesheet" href="User/css/sidebar.css">
<link rel="stylesheet" href="User/css/topbar.css">
<link rel="stylesheet" href="User/css/thongbaoto.css">
<link rel="stylesheet" href="User/css/infoUser.css">
<link rel="stylesheet" href="User/css/changePass.css">
<link rel="stylesheet" href="User/css/thongbaofrom.css">
<link rel="stylesheet" href="User/css/lichsu.css">

</head>
<body>
	<c:if test="${not empty messageUpdateInfoUser}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageUpdateInfoUser}");
	        });
	    </script>
	</c:if>
	<c:if test="${not empty messageUpdateAvaUser}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageUpdateAvaUser}");
	        });
	    </script>
	</c:if>
	<c:if test="${not empty messageUpdateInfoRestaurant}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageUpdateInfoRestaurant}");
	        });
	    </script>
	</c:if>
	<c:if test="${not empty messageUpdateAvaRestaurant}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageUpdateAvaRestaurant}");
	        });
	    </script>
	</c:if>
	<c:if test="${not empty messageUpdatePasswordUser}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageUpdatePasswordUser}");
	        });
	    </script>
	</c:if>
	
	<%
		//người dùng
		User user = (User) session.getAttribute("sessionUser");
		String nameAva = user.getUrlAvatar();
		String fullNameUser = user.getNameUser();
		String nameLoginUser = user.getNameLogin();
		String emailUser = user.getEmailUser();
		String addressUser = user.getAddress();
		String phoneUser = user.getPhoneUser();
		int genderUser = user.getGender();
		int typeUser = user.getTypeUser();
		
		//nhà hàng
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
	%>
	
	<div class="sidebar">
		<div class="logo_details">
			<div class="logo_name">Cài đặt</div>
		</div>
		
		<ul>
			<li>
				<a href="#" class="active">
					<i class='bx bx-grid-alt'></i>
					<span class="links_name">Thông tin tài khoản</span>
				</a>
			</li>
			
			<li>
				<a href="#">
					<i class='bx bx-user'></i>
					<span class="links_name">Thay đổi mật khẩu</span>
				</a>
			</li>
			
			<li>
				<a href="#">
					<i class='bx bx-user'></i>
					<span class="links_name">Thông báo từ hệ thống</span>
				</a>
			</li>
		
			<li>
				<a href="#">
					<i class='bx bxs-truck'></i>
					<span class="links_name">Gửi thông báo tới hệ thống</span>
				</a>
			</li>

			<li>
				<a href="#">
					<i class='bx bx-dollar' ></i>
					<span class="links_name">Đặt bàn tiệc</span>
				</a>
			</li>
    	</ul>
	</div>
	<!-- End Sideber -->
	
	<section class="home_section">
		<div class="topbar">
			<div class="user_wrapper">
			    <img src="<%=nameAva %>" alt="error">
			    <h3>Xin chào, <%=fullNameUser %></h3>
			</div>
			
	        <div class="chung">
	        <%
	        	if(typeUser == 2){
	        %>
	            <div class="khohang">
	                <a href="WarehouseForward">Kho hàng</a>
	            </div>
	        <%
	        	}
	        %>
	            <div class="logOut">
	                <a href="HomeForward">Trang chủ</a>
	            </div>
	        </div>
    	</div>
    	
		<div class="infoUser">
			<div class="main-infoUser" id="main-infoUser">
				<h2>Thông tin cá nhân</h2>
				<form action="UpdateAvaUser" method="POST" enctype="multipart/form-data">
					<img id="avatarPreview" src="<%=nameAva %>" alt="error">
					<input type="file" id="avatar" name="avatar" placeholder="cập nhật ảnh đại diện">
					<button type="submit">Cập nhật ảnh đại diện</button>
				</form>
				
				<form name="update-info-form" action="UpdateInfoUser" method="POST">
				    <input type="text" name="fullNameUser" placeholder="Tên người dùng" required value="<%=fullNameUser %>">
				    <input type="text" name="nameLoginUser" placeholder="Tên đăng nhập" required readonly value="<%=nameLoginUser %>">
				    <input type="text" name="emailUser" placeholder="Địa chỉ email" required readonly value="<%=emailUser %>">
				    <input type="text" name="addressUser" placeholder="Địa chỉ" required value="<%=addressUser %>">
				    <input type="text" name="phoneUser" placeholder="Số điện thoại" required value="<%=phoneUser %>">
				    <select name="genderUser">
				        <option value="1" <%= (genderUser == 1) ? "selected" : "" %>>Nam</option>
				        <option value="2" <%= (genderUser == 2) ? "selected" : "" %>>Nữ</option>
				    </select>
				    <input type="password" name="passwordUser" placeholder="Nhập mật khẩu xác nhận" required>
				    <button type="submit">Cập nhật thông tin cá nhân</button>
				</form>
				
				<%
					if(restaurant != null){
				%>
				<br>
				<hr style="border: 3px solid aquamarine;">
				<br>
				<h2>Thông tin nhà hàng</h2>
				<form action="UpdateAvaRestaurant" method="POST" enctype="multipart/form-data">
<!-- 	            	<label for="avatar"> -->
						<img id="avatarPreview" src="<%=restaurant.getImgRestaurant() %>" alt="error">
<!-- 						<h5>Tải ảnh lên</h5> -->
<!-- 					</label> -->
					<input type="file" id="avatar" name="avatar">
					<button type="submit">Cập nhật ảnh nhà hàng</button>
	             </form>
	             
	             <form action="UpdateInfoRestaurant" method="POST">
	                <input type="text" placeholder="Tên nhà hàng" name="name" required value="<%=restaurant.getRestaurantName() %>">
	                <input type="text" placeholder="Địa chỉ" required name="location" value="<%=restaurant.getRestaurantLocation() %>">
	                <input type="text" placeholder="Loại sản phẩm" required name="type" value="<%=restaurant.getCuisineTypeDESC() %>">
	                <button type="submit">Cập nhật thông tin nhà hàng</button>
            	</form>
            	<br>
            	<%
					}
            	%>
			</div>
		</div>

	    <div class="changePass">
			<div class="main-changePass" id="main-changePass">
				<form action="UpdatePasswordUser" method="POST" onsubmit="return validateUpdatePassForm()">
					<input type="password" name="oldPassword" id="oldPasswordUpdateForm" placeholder="Nhập mật khẩu cũ" required>
					<input type="password" name="newPassword" id="newPasswordUpdateForm" placeholder="Nhập mật khẩu mới" required>
					<input type="password" id="newConfirmPasswordUpdateForm" placeholder="Xác nhận mật khẩu mới" required>
					<button type="submit" onclick="">Xác nhận</button>
				</form>
			</div>
	    </div>
	    
		<div class="thongbaofrom">
			<div class="main-thongbaofrom" id="main-thongbaofrom">
				<table>
					<thead>
						<tr>
							<th>Chủ đề</th>
							<th>Nội dung</th>
							<th>Thời gian gửi</th>
							<th>Trạng thái</th>
						</tr>
						
						<c:forEach items="${listNotiFromUser }" var="noti">
							<tr>
								<td>${noti.getTitle() }</td>
								<td>${noti.getContent() }</td>
								<td>${noti.getSendingTime() }</td>
								<td>
									<select onchange="UpdateStatusNotiFromAdmin(${noti.getNotificationId() }, this.value)" style="padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;">
										<option value="0" ${noti.getStatus() == 0 ? 'selected' : ''}>Chưa xem</option>
										<option value="2" ${noti.getStatus() == 2 ? 'selected' : ''}>Đã xem</option>
									</select>
								</td>
							</tr>
						</c:forEach>	
					</thead>
				  </table>
			</div>
		</div>
		
		<div class="thongbaoto">
			<div class="main-thongbaoto" id="main-thongbaoto">
				<form id="notification-form">
					<div class="form-group">
						<label for="topic">Chủ đề:</label>
						<input type="text" id="topicToAdmin" name="topic" required>
					</div>
					
					<div class="form-group">
						<label for="message">Nội dung:</label>
						<textarea id="messageToAdmin" name="message" required></textarea>
					</div>
					
					<div class="form-group">
						<button type="button" onclick="SendNotiToAdmin()">Gửi thông báo</button>
					</div>
				</form>
			</div>
		</div>

		<div class="lichsu">
			<div class="main-thongbaofrom" id="show-Reservation">
				<table>
		            <thead>
		                <tr>
		                  <%
		                  	if(typeUser == 1){ //khách hàng
		                  %>
		                  <th>Nhà hàng</th>
		                  <%
		                  	} else if(typeUser == 2){ //nhà hàng
		                  %>
		                  <th>Khách hàng</th>
		                  <%
		                  	}
		                  %>
		                  <th>Số khách</th>
		                  <th>Ngày đặt</th>
		                  <th>Ghi chú</th>
		                  <th>Trạng thái</th>
		                </tr>
		                
		                <c:forEach items="${reservationList }" var="res">
			                <tr>
			                  <%
			                  	if(typeUser == 1){ //khách hàng
			                  %>
			                  <td title="Địa chỉ: ${res.getLocationRestaurant() }-Liên hệ: ${res.getPhoneRestaurant() }">${res.getNameRestaurant() }</td>
			                  <%
			                  	} else if(typeUser == 2){ //nhà hàng
			                  %>
			                  <td title="Liên hệ: ${res.getPhoneCustomer() }">${res.getFullNameCustomer() }</td>
			                  <%
			                  	}
			                  %>
			                  <td>${res.getNumberOfGuests() }</td>
			                  <td>${res.getReservationDate() }</td>
			                  <td>${res.getAdditionalReminder() }</td>
			                  <td>
				                  <%
				                  	if(typeUser == 1){ //khách hàng
				                  %>
				                 	${res.getStatus() == 1 ? 'Đã xác nhận' : 'Chưa xác nhận'}
				                  <%
				                  	} else if(typeUser == 2){ //nhà hàng
				                  %>
				                  	<select onchange="UpdateStatusReservationFromRestaurant(${res.getReservationId() }, this.value)" style="padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;">
				                  		<option value="0" ${res.getStatus() == 0 ? 'selected' : ''}>Chưa xác nhận</option>
				                  		<option value="1" ${res.getStatus() == 1 ? 'selected' : ''}>Đã xác nhận</option>
			                  		</select>
				                  <%
				                  	}
				                  %>
			                  </td>
			                </tr>
		                </c:forEach>
		            </thead>
				</table>
        	</div>
      </div>
		
		
	</section>
		
	<!-- file js -->
	<script type="text/javascript" src="User/js/ProcessPageUser.js"></script>
	<script type="text/javascript" src="User/js/ValidateForm.js"></script>
	<script type="text/javascript" src="User/js/SendNotiToAdmin.js"></script>
	<script type="text/javascript" src="User/js/ProcessReservation.js"></script>
	
	<script type="text/javascript" src="Admin/js/UpdateStatus.js"></script>

</body>
</html>