<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Trang đăng nhập</title>

<% 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
    request.setAttribute("path", path);
%>

<!-- file css -->
<link rel="stylesheet" href="<%=path%>/Login/login_style.css">

</head>
<body>
	<c:if test="${not empty messageSignup}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageSignup}");
	        });
	    </script>
	</c:if>
	<c:if test="${not empty messageLogin}">
	    <script>
	        window.addEventListener("load", function() {
	            alert("${messageLogin}");
	        });
	    </script>
	</c:if>

   <div class="wrapper">
      <div class="title">
         Đăng nhập
      </div>

      <form id="loginForm" action="LoginController" method="POST">
         <div class="field">
            <input type="text" name="nameLogin" required>
            <label>Tên tài khoản</label>
         </div>
         <div class="field">
            <input type="password" name="passwordUser" required>
            <label>Mật khẩu</label>
         </div>
         <div class="content">
            <div class="checkbox">
               <input type="checkbox" id="remember-me">
               <label for="remember-me">Nhớ mật khẩu</label>
            </div>
            <div class="pass-link">
               <a href="#">Quên mật khẩu?</a>
            </div>
         </div>
         <div class="field">
            <input type="submit" value="Đăng nhập">
         </div>
         <div class="signup-link">
            Chưa có tài khoản? <a href="SignupForward">Đăng ký ngay</a>
         </div>
      </form>
   </div>
</body>
</html>