<%@page import="BEAN.ErrorSystem"%>
<%@page import="java.util.List"%>
<%@page import="BEAN.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Admin</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- file css -->
<link rel="stylesheet" href="Admin/css/main.css">
<link rel="stylesheet" href="Admin/css/errU.css">
<link rel="stylesheet" href="Admin/css/error.css">
<link rel="stylesheet" href="Admin/css/home_section.css">
<link rel="stylesheet" href="Admin/css/res.css">
<link rel="stylesheet" href="Admin/css/tbtoUser.css">
<link rel="stylesheet" href="Admin/css/user.css">
<link rel="stylesheet" href="Admin/css/filterUser.css">
<link rel="stylesheet" href="Admin/css/tongquan.css">

</head>
<body>
	<%
		User admin = (User) session.getAttribute("sessionAdmin");
		String nameAdmin = admin.getNameUser();
		String avaUrlAdmin = admin.getUrlAvatar();
		
		//lỗi
		long cntItemStatus0 = (long) request.getAttribute("cntErrorStatus0");
		long cntItemStatus1 = (long) request.getAttribute("cntErrorStatus1");
		
		//tài khoản người dùng
		long cntCustomer = (long) request.getAttribute("cntCustomer");
		long cntRestaurant = (long) request.getAttribute("cntRestaurant");	
		
		//thông báo phía người dùng
		long cntNotiFromUserStatus0 = (long) request.getAttribute("cntNotiFromUserStatus0");
		long cntNotiFromUserStatus1 = (long) request.getAttribute("cntNotiFromUserStatus1");
	%>

	<div class="sidebar">
		<div class="logo_details">
			<div class="logo_name">Admin Manager</div>			
		</div>
		
		<ul>
			<li>
				<a href="#" class="active">
					<i class='bx bx-grid-alt'></i>
					<span class="links_name">Tổng quan</span>
				</a>
			</li>
	      
			<li>
				<a href="#">
					<i class='bx bx-user'></i>
					<span class="links_name">Quản lý khách hàng</span>
				</a>
			</li>
	      
			<li>
				<a href="#">
					<i class='bx bx-user'></i>
					<span class="links_name">Quản lý nhà hàng</span>
				</a>
			</li>
	      
			<li>
				<a href="#">
					<i class='bx bxs-truck'></i>
					<span class="links_name">Lỗi hệ thống</span>
				</a>
			</li>
			
			<li>
				<a href="#">
					<i class='bx bxs-truck'></i>
					<span class="links_name">Báo lỗi từ người dùng</span>
				</a>
			</li>
			
			<li>
				<a href="#">
					<i class='bx bx-dollar' ></i>
					<span class="links_name">Thông báo người dùng</span>
				</a>
			</li>
		</ul>
	</div>
	<!-- End Sideber -->
	
	<section class="home_section">
    	<div class="topbar">
			<div class="user_wrapper">
				<img src="<%=avaUrlAdmin %>" alt="">
				<h3>Xin chào, <%=nameAdmin %></h3>
			</div>
			
			<div class="logOut">
				<a href="LogoutController">Đăng xuất</a>
 	 		</div>
		</div>
		
		<div class="tong-quan">
			<div class="main-tongquan" id="main-tongquan">
				<div class="tq">
					<h1>Số lượng khách hàng: <%=cntCustomer %></h1>
				</div>
				
				<div class="tq">
					<h1>Số lượng nhà hàng: <%=cntRestaurant %></h1>
				</div>
				
				<div class="tq">
					<h1>Lỗi hệ thống chưa xử lý: <%=cntItemStatus0 %></h1>
				</div>
				
				<div class="tq">
					<h1>Lỗi hệ thống đang xử lý: <%=cntItemStatus1 %></h1>
				</div>
				
				<div class="tq">
					<h1>Thông báo từ người dùng chưa xử lý: <%=cntNotiFromUserStatus0 %></h1>
				</div>
				
				<div class="tq">
					<h1>Thông báo từ người dùng đang xử lý: <%=cntNotiFromUserStatus1 %></h1>
				</div>
			</div>
		</div>
		
		<div class="quan-ly-user">
			<div class="filter-section">
				<form id="filter-form" name="form-admin-user-filter">
					<label for="search-name">Tên:</label>
					<input type="text" id="search-name" name="name" placeholder="Nhập tên...">
		          
					<label for="sort-order">Loại:</label>
					<select id="sort-order" name="sort">
						<option value="1">Khách hàng</option>
						<option value="2">Nhà hàng</option>
					</select>
          
					<label for="record-count">Số lượng bản ghi:</label>
					<input type="number" id="record-count" name="recordCount" min="1" placeholder="Số lượng bản ghi" required="required">
		          
		          <button type="button" onclick="DisplayUserFilter()">Lọc</button>
				</form>
			</div>
			
			<div class="main-quanlyU" id="main-quanlyU">
				<table>
					<thead>
						<tr>
							<th>Tên đăng nhập</th>
							<th>Email</th>
							<th>Số điện thoại</th>
							<th>Giới tính</th>
							<th>Xóa tài khoản</th>
							<th>Xóa lịch sử đặt hàng</th>
						</tr>
						
						<c:forEach items="${listUser }" var="user">
							<tr>
								<td title="${user.getNameUser() }-[${user.getUserId() }]">${user.getNameLogin() }</td>
								<td>${user.getEmailUser() }</td>
								<td>${user.getPhoneUser() }</td>
								<td>${user.getGender() == 1 ? 'Nam' : 'Nữ' }</td>
								<td><button class="remove-button" onclick="DeleteUser(${user.getUserId()})">Xóa</button></td>
								<td><button class="remove-button" onclick="DeleteCartStatus3(${user.getUserId()})">Xóa</button></td>
							</tr>
						</c:forEach>
						
					</thead>
				</table>
			</div>
		</div>
		
		<div class="quan-ly-res">
			<div class="filter-section">
				<form id="filter-form" name="form-admin-restaurant-filter">
					<label for="search-name">Tên/Địa chỉ:</label>
					<input type="text" id="search-name" name="name" placeholder="Nhập tên...">
          
					<label for="record-count">Số lượng bản ghi:</label>
					<input type="number" id="record-count" name="recordCount" min="1" placeholder="Số lượng bản ghi" required="required">
		          
		          <button type="button" onclick="DisplayRestaurantFilter()">Lọc</button>
				</form>
			</div>		
		
			<div class="main-quanlyR" id="main-quanlyR">
				<table>
					<thead>
						<tr>
							<th>Tên nhà hàng</th>
							<th>Chủ nhà hàng</th>
							<th>Địa chỉ</th>
							<th>Hình thức bán hàng</th>
							<th>Xóa tài khoản</th>
						</tr>
						
						<c:forEach items="${listRestaurant }" var="restaurant">
							<tr>
								<td title="[${restaurant.getRestaurantId() }]">${restaurant.getRestaurantName() }</td>
								<td title="${restaurant.getRestaurantOwnerFullName() }-[${restaurant.getRestaurantOwnerId() }]">${restaurant.getRestaurantOwnerLoginName() }</td>
								<td>${restaurant.getRestaurantLocation() }</td>
								<td>${restaurant.getCuisineTypeDESC() }</td>
								<td><button class="remove-button" type="button" onclick="DeleteRestaurant(${restaurant.getRestaurantId()})">Xóa</button></td>
							</tr>
						</c:forEach>
						
					</thead>
				</table>
			</div>
		</div>
		
		<div class="quan-ly-err">
			<div class="main-quanlyE" id="main-quanlyE">
				<table>
					<thead>
						<tr>
							<th>Loại lỗi</th>
							<th>Mô tả</th>
							<th>Thời điểm xảy ra lỗi</th>
							<th>Trạng thái xử lý</th>
						</tr>
						
						<c:forEach items="${errorList }" var="error">
							<tr>
								<td>${error.getErrorType() }</td>
								<td>${error.getErrorDesc() }</td>
								<td>${error.getErrorOccurrenceTime() }</td>
								<td>
									<select onchange="UpdateStatusError(${error.getErrorId()}, this.value)" style="padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;">
										<option value="0" ${error.getErrorStatus() == 0 ? 'selected' : ''}>Chưa xử lý</option>
										<option value="1" ${error.getErrorStatus() == 1 ? 'selected' : ''}>Đang xử lý</option>
										<option value="2" ${error.getErrorStatus() == 2 ? 'selected' : ''}>Đã xử lý</option>
									</select>
								</td>
							</tr>
						</c:forEach>						
					</thead>
				</table>
			</div>
		</div>

		<div class="quan-ly-errU">
			<div class="main-errU" id="main-errU">
				<table>
					<thead>
						<tr>
							<th>Tên tài khoản người dùng</th>
							<th>Chủ đề</th>
							<th>Nội dung</th>
							<th>Thời gian gửi</th>
							<th>Trạng thái xử lý</th>
						</tr>
						
						<c:forEach items="${listNotiFromUser }" var="noti">
							<tr>
								<td title="${noti.getFullNameSender() }-[${noti.getSenderId() }]">${noti.getNameLoginSender() }</td>
								<td>${noti.getTitle() }</td>
								<td>${noti.getContent() }</td>
								<td>${noti.getSendingTime() }</td>
								<td>
									<select onchange="UpdateStatusNotiFromUser(${noti.getNotificationId() }, this.value)" style="padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;">
										<option value="0" ${noti.getStatus() == 0 ? 'selected' : ''}>Chưa xử lý</option>
										<option value="1" ${noti.getStatus() == 1 ? 'selected' : ''}>Đang xử lý</option>
										<option value="2" ${noti.getStatus() == 2 ? 'selected' : ''}>Đã xử lý</option>
									</select>
								</td>
							</tr>
						</c:forEach>
					</thead>
				</table>
			</div>
		</div>
    
		<div class="thong-bao-user">
			<form id="notification-form">
				<div class="form-group">
					<label for="topic">Gửi tới:</label>
					<input type="text" id="recipientuser" name="send-user" required placeholder="Nhập '@all' tới mọi người hoặc tên đăng nhập người dùng">
				</div>
				
				<div class="form-group">
					<label for="topic">Chủ đề:</label>
					<input type="text" id="topic" name="topic" required>
				</div>
				
				<div class="form-group">
					<label for="message">Nội dung:</label>
					<textarea id="message" name="message" required></textarea>
				</div>
				
				<div class="form-group">
					<button type="button" onclick="SendNotiToUser()">Gửi thông báo</button>
				</div>
			</form>
		</div>
 	</section>
	
	<!-- file js -->
	<script type="text/javascript" src="Admin/js/ProcessAdmin.js"></script>
	<script type="text/javascript" src="Admin/js/UpdateStatus.js"></script>
	<script type="text/javascript" src="Admin/js/SendNotiToUser.js"></script>
	<script type="text/javascript" src="Admin/js/ProcessUser.js"></script>
	
</body>
</html>