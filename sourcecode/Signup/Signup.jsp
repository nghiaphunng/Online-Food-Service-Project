<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Trang đăng ký</title>

<% 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
   	request.setAttribute("path", path);
%>

<!-- file css -->
<link rel="stylesheet" href="<%=path%>/Signup/signup_style.css">

</head>
<body>
	<c:if test="${not empty messageSignup}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageSignup}");
	        });
	    </script>
	</c:if>
	<div class="container">
	   <div class="title">Đăng ký</div>
	   
	   <div class="content">
	     <form action="SignupController" method="POST" name="form_sign_up" onsubmit="return validateFormSignUp()">
	      <div class="user-details">
	        <div class="input-box">
	          <span class="details">Họ Tên</span>
	          <input type="text" placeholder="Nhập họ tên" required name="nameUser">
	        </div>
	        <div class="input-box">
	          <span class="details">Tên Tài Khoản</span>
	          <input type="text" placeholder="Nhập tên tài khoản" required name="nameLogin">
	        </div>
	        <div class="input-box">
	          <span class="details">Email</span>
	          <input type="text" placeholder="Nhập email" required name="emailUser">
	        </div>
	        <div class="input-box">
	          <span class="details">Số Điện Thoại</span>
	          <input type="text" placeholder="Nhập số điện thoại" required name="phoneUser">
	        </div>
	        <div class="input-box">
	          <span class="details">Mật Khẩu</span>
	          <input type="password" placeholder="Nhập mật khẩu" required name="passwordUser">
	        </div>
	        <div class="input-box">
	          <span class="details">Địa chỉ</span>
	          <input type="text" placeholder="Nhập địa chỉ" required name="addressUser">
	        </div>
	      </div>
	      <div class="final-details">
	        <label for="gender">Giới Tính:</label>
	          <select id="gender" name="genderUser">
	            <option value="1">Nam</option>
	            <option value="2">Nữ</option>
	          </select>
	      </div>
	      <div class="final-details">
	        <label for="role">Bạn Là:</label>
	          <select id="role" name="role">
	            <option value="1">Khách Hàng</option>
	            <option value="2">Nhà Hàng</option>
	          </select>
	      </div>
	      <div class="button">
	        <input type="submit" value="Đăng ký">
	      </div>
	      <div class="trangchu">
	        <a href="HomeForward">Trang Chủ</a>
	      </div>
	    </form>
	
	  </div>
	</div>
	
	<!-- file javascript -->
	<script type="text/javascript" src="<%=path%>/Signup/CheckFormSignUp.js"></script>
</body>
</html>