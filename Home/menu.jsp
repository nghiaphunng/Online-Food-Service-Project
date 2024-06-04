<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1"/>
<% 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
	request.setAttribute("path", path);
%>
s
</head>

<body>
	<nav id="menu" class="navbar-menu">	
		<div class="logo">
        	<a href="trang-chu"><img alt="error" src="${path}/img/logo/logo_noback.png">KaiTsukiFood</a>
        </div>
		
		<div class="location-menu">
			<select name="search_scope" onchange="SearchNameCourse(document.search_form.search.value, document.search_form.search_scope.value)">
	          	<option value="0">Khu vực</option>
	            <option value="1">Ba Đình</option>
	            <option value="2">Hà Đông</option>
	            <option value="3">Hoàng Mai</option>
	            <option value="4">Thanh Xuân</option>
	            <option value="5">Thanh Trì</option>
	            <option value="6">Nam Từ Liêm</option>
	            <option value="7">Cầu Giấy</option>
	            <option value="8">Hai Bà Trưng</option>
	            <option value="9">Long Biên</option>
	            <option value="10">Gia Lâm</option>
	            <option value="11">Thường Tín</option>
	            <option value="12">Đống Đa</option>
	            <option value="13">Hoàn Kiếm</option>
	            <option value="14">Tây Hồ</option>
	            <option value="15">Hoài Đức</option>
	            <option value="16">Bắc Từ Liêm</option>
			</select>
		</div>
		
		<div class="typefood-menu">
			<ul>
				<li><a href="FoodForward">Đồ ăn</a></li>
				<li><a href="DrinkForward">Đồ uống</a></li>
			</ul>
		</div>
	        
        <div class="search-menu">
            <form name="search_form" onsubmit="return false;">
                <input type="text" placeholder="Tìm kiếm..." name="search" oninput="SearchNameCourse(this.value, document.search_form.search_scope.value)">
                <button type="button"><i class="fa fa-search"></i></button>
			</form>
 		</div>
 		
 		<div class="cart-menu">
                <a href="cart"><i class="fa fa-shopping-cart"></i>Giỏ hàng</a>
        </div>
        
        <% 
			if(session.getAttribute("sessionUser") == null)
			{
		%>
		<div class="login-menu">
        	<a href="LoginForward">Đăng nhập</a>
     	</div>
        <%
			}
		else
			{
	    %>
	    <div class="login-menu">
	    	<a href="LogoutController">Đăng xuất</a>
	    </div>
	    <%
			}
	    %>
    </nav>
    
</body>
</html>