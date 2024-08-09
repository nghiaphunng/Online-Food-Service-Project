<%@page import="BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>

<body>
	<nav id="menu" class="navbar-menu">	
		<div class="logo">
        	<a href="HomeForward"><img alt="error" src="img/logo/logo_noback.png">KaiTsukiFood</a>
        </div>
		
		<div class="location-menu">
			<select id="search_scope_location" name="search_scope" onchange="SearchItemLocation('<%=request.getAttribute("urlLocation") %>')">
	          	<option value="hà nội">Khu vực Hà Nội</option>
	            <option value="ba đình">Ba Đình</option>
	            <option value="hà đông">Hà Đông</option>
	            <option value="hoàng mai">Hoàng Mai</option>
	            <option value="thanh xuân">Thanh Xuân</option>
	            <option value="thanh trì">Thanh Trì</option>
	            <option value="nam từ liên">Nam Từ Liêm</option>
	            <option value="cầu giấy">Cầu Giấy</option>
	            <option value="hai bà trưng">Hai Bà Trưng</option>
	            <option value="long biên">Long Biên</option>
	            <option value="gia lâm">Gia Lâm</option>
	            <option value="thường tín">Thường Tín</option>
	            <option value="đống đa">Đống Đa</option>
	            <option value="hoàn kiếm">Hoàn Kiếm</option>
	            <option value="tây hồ">Tây Hồ</option>
	            <option value="hoài đức">Hoài Đức</option>
	            <option value="bắc từ liêm">Bắc Từ Liêm</option>
			</select>
		</div>
		
		<div class="typefood-menu">
			<ul>
				<li><a href="HomeForward">Tất cả</a></li>
				<li><a href="FoodForward">Đồ ăn</a></li>
				<li><a href="DrinkForward">Đồ uống</a></li>
				<li><a href="ComboFoodForward">Combo</a></li>
			</ul>
		</div>
	        
        <div class="search-menu">
            <form name="search-form" onsubmit="return false;">
                <input type="text" placeholder="Tìm kiếm..." name="search-name">
                <button type="button" onclick="SearchItemName('<%=request.getAttribute("urlLocation") %>')"><i class="fa fa-search"></i></button>
			</form>
 		</div>
 		
 		<div class="cart-menu">
 			<% 
				if(session.getAttribute("sessionUser") != null){
					User user = (User) session.getAttribute("sessionUser");
					if(user.getTypeUser() == 1){ //khách hàng
			%>
				<a href="CartForward"><i class="fa fa-shopping-cart"></i>Giỏ hàng</a>
			<%
					}
				else if(user.getTypeUser() == 2){ //nhà hàng
			%>
                <a href="CartRestaurantForward"><i class="fa fa-shopping-cart"></i>Giỏ hàng</a>
            <%
				}
 			}
            %>
                                
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
			User user = (User) session.getAttribute("sessionUser");
			String nameAva = user.getUrlAvatar();
	    %>
	    <div class="tooltip">
            <img class="cover" src="<%=nameAva %>" style="margin-top:3px;width:30px;height:30px;border:1px solid #ff6600;border-radius:50%;">
            <div class="tooltiptext">
                <a class="chinhsua" href="LogoutController">Đăng xuất</a>
                <a class="chinhsua" href="UserPageForward">Cài đặt</a>
            </div>
        </div>
	    <%
			}
	    %>
    </nav>
    
</body>
</html>