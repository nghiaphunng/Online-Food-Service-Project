<%@page import="BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>

<link rel="icon" type="image/x-icon" href="img/logo/logo.ico">

<meta name="viewport" content="width=device-width, initial-scale=1"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="Home/css/stylehome.css">
<link rel="stylesheet" href="Home/css/stylemenu.css">
<link rel="stylesheet" href="Home/css/tooltip.css">

</head>
<body>
	<%
		User user = (User) session.getAttribute("sessionUser");
		String nameAva;
		if (user != null) {
			nameAva = user.getUrlAvatar();
		}
	%>

	<jsp:include page="menu.jsp"></jsp:include>
	
	<div id="main" style="margin-top: 70px;margin-left:50px;">
		<button class="filter-button">Bộ Lọc</button>
		
		<div class="filter-box" id="filterBox">
            <form name="filter-item-form">
                <label for="price-range">Giá:</label>
                <select id="price-range" name="price-range">
                    <option value="0">Dưới 30.000 VNĐ</option>
                    <option value="1">Từ 30-50.000 VNĐ</option>
                    <option value="2">Từ 50-100.000 VNĐ</option>
                    <option value="3">Trên 100.000 VNĐ</option>
                </select>

                <label for="type">Loại:</label>
                <select id="type-item" name="type-item">
                    <option value="0">Tất cả</option>
                    <option value="1">Đồ ăn</option>
                    <option value="2">Đồ uống</option>
                    <option value="3">Combo</option>
                    <option>
                </select>
                <button type="button" onclick="SearchItemFiter()">Áp dụng</button>
            </form>
        </div>	
	</div>
	
	<div class="now-list-restaurant">
        <div class="list-restaurant" id="list-restaurant">
        	<c:forEach items="${itemList }" var="item">
	            <div class="item-restaurant">
	                <div class="item-content">
	                    <div class="img-restaurant">
							<img class="cover" src="img/item/${item.getImage() }" alt="" style="width:230px;height:200px; border-radius: 6px 6px 0 0; border: 1px solid #524f4f;">
						</div>
	                    <div class="info-restaurant">
	                        <div class="info-basic-res">  
	                            <a href="RestaurantViewForward?restaurantId=${item.getRestaurantId() }" class="name-res" title="${item.getName() }">${item.getName() }</a>
	                            <div class="address-res" title="${item.getNameRestaurant() }" >${item.getLocationRestaurant() }</div>
	                        </div>
	                        <div class="inf">
		                        <button class="price-button">${item.getPrice() } VNĐ</button>
		                        <%
		                        	if (user != null){
		                        		if(user.getTypeUser() == 1){
		                        %>
		                        <button class="add-button" onclick="AddTocart(<%=user.getUserId() %>, ${item.getItemId()}, ${item.getRestaurantId() })">Thêm vào giỏ hàng</button>
	                        	<%
		                        		} else if (user.getTypeUser() == 2){
	                        	%>	
	                        	<button class="add-button" onclick="alert('Bạn không có tính năng này')">Thêm vào giỏ hàng</button>   
	                        	<%
		                        		}
	                        	%>          	
	                        	<%
		                        	} else{
	                        	%>
	                        	 <button class="add-button" onclick="alert('Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng')">Thêm vào giỏ hàng</button>
	                        	<%
		                        	}
	                        	%>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </c:forEach>
		</div>
	</div>
           
	
	<!-- file js -->
	<script type="text/javascript" src="Home/js/pageone.js"></script>
	<script type="text/javascript" src="Home/js/Search.js"></script>
	
	<script type="text/javascript" src="User/js/ProcessCart.js"></script>
</body>
</html>