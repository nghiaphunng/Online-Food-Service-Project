<%@page import="BEAN.User"%>
<%@page import="BEAN.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang nhà hàng</title>
<link rel="icon" type="image/x-icon" href="img/logo/logo.ico">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- file css -->
<link rel="stylesheet" href="Home/css/stylemenu.css">
<link rel="stylesheet" href="Home/css/tooltip.css">

<link rel="stylesheet" href="Restaurant/css/body.css">
<link rel="stylesheet" href="Restaurant/css/button.css">
<link rel="stylesheet" href="Restaurant/css/stylehome.css">
<link rel="stylesheet" href="Restaurant/css/thedatban.css">

</head>
<body>
	<%
		User user = (User) session.getAttribute("sessionUser");
		String nameAva;
		if (user != null) {
			nameAva = user.getUrlAvatar();
		}
	%>

	<jsp:include page="/Home/menu.jsp"></jsp:include>
	
	<div class="modal-container" id="modal-container" >
        <div id="modal">
            <div class="modal-header" id="modal-header">
                <h3>Đặt bàn</h3>
                <button class="close"><i id="btn-close" class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal-body">
                <form name="form-reservation">
                    <label for="name">Tên người đặt:</label>
                    <%
		            	if (user != null){
		            %>
		            <input type="text" id="name" name="name" required value="<%=user.getNameUser() %>" disabled="disabled"><br>
		           	<%
		            	} 
		            	else {
		           	%>
                    <input type="text" id="name" name="name" required disabled="disabled"><br>
                    <%
		            	} 
		           	%>
                    <label for="date">Ngày đặt:</label>
                    <input type="date" id="date" name="date" required><br>
                    <label for="guests">Số lượng khách:</label>
                    <input type="number" id="guests" name="guests" min="1" required><br>
                    <label for="phone">Số điện thoại liên hệ:</label>
                    <input type="tel" id="phone" name="phone" pattern="[0-9]{10,11}" placeholder="Số điện thoại (10 hoặc 11 số)" required><br>
                    <label for="note">Ghi chú:</label>
                    <textarea id="note" name="note" rows="4"></textarea><br>
                    <%
		            	if (user != null){
		            %>
		            <input style="padding: 10px; color: white; border: none; background-color: #ff6600; font-size: 18px; font-weight: bold;" type="button" value="Gửi" id="submit" onclick="SendReservation(${restaurant.getRestaurantId() }, <%=user.getUserId() %>)">
		           	<%
		            	} 
		            	else {
		           	%>
		           	<input style="padding: 10px; color: white; border: none; background-color: #ff6600; font-size: 18px; font-weight: bold;" type="button" value="Gửi">
					<%
		            	} 
		           	%>
                </form>           
            </div>
        </div>
    </div>
    <div class="space">
        <div class="now-detail-restaurant">
            <div class="container">
                <div class="detail-restaurant-img"><img src="${restaurant.getImgRestaurant() }"></div>
            </div>
        </div>
        <div class="detail-retaurant-info">
            <h1 class="name-restaurant">${restaurant.getRestaurantName() }</h1>
            <div class="address-restaurant">Địa chỉ: ${restaurant.getRestaurantLocation() }</div>
            <div class="total-amount">Tổng sản phẩm bán được: ${restaurant.getTotalItemSalesCount() }</div>
            <div class="">Chủ nhà hàng: ${restaurant.getRestaurantOwnerFullName() }</div>
            <div class="">Liên hệ: ${restaurant.getPhoneRestaurant() }</div> 
            <%
            	if (user != null){
            			if(user.getTypeUser() == 2){
            %>
            	<button class="datban" onclick="alert('Bạn không thể sử dụng chức năng này khi là chủ nhà hàng')">Đặt bàn</button>
            <%
            	} else if(user.getTypeUser() == 1){
            %>	
            	<button class="datban" id="datban">Đặt bàn</button>
            <%
            	}
            %>
           	<%
            	} else {
           	%>
           		<button class="datban" onclick="alert('Bạn cần đăng nhập để có thể đặt bàn')">Đặt bàn</button>   
            <%
            	}
           	%>
        </div>
    </div>
	
	<c:if test="${not empty listItemRestaurantType1}">
	    <div class="food">Đồ ăn</div>
	    <div class="now-list-restaurant">
	        <div class="list-restaurant">
	        	<c:forEach items="${listItemRestaurantType1 }" var="item">
		            <div class="item-restaurant">
		                <div class="item-content">
		                    <div class="img-restaurant">
		                        <img class="cover" src="img/item/${item.getImage() }" alt="error" style="width:230px;height:200px; border-radius: 6px 6px 0 0; border: 1px solid #524f4f;">
		                    </div>
		                    <div class="info-restaurant">
		                        <div class="info-basic-res">
		                            <a class="name-res" title="${item.getName() }" href="">${item.getName() }</a>
		                            <div class="address-res">Số lượt bán: ${item.getSalesCount() }</div>
		                        </div>
		                        <div class="inf">
		                            <button class="price-button">${item.getPrice() } VNĐ</button>
		                            <%
			                        	if (user != null){
			                        %>
			                        <button class="add-button" onclick="AddTocart(<%=user.getUserId() %>, ${item.getItemId()}, ${item.getRestaurantId() })">Thêm vào giỏ hàng</button>
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
    </c:if>
    
    <c:if test="${not empty listItemRestaurantType2}">
	    <div class="title-menu">Đồ uống</div>
	    <div class="now-list-restaurant">
	        <div class="list-restaurant">
	        	<c:forEach items="${listItemRestaurantType2 }" var="item">
		            <div class="item-restaurant">
		                <div class="item-content">
		                    <div class="img-restaurant">
		                        <img class="cover" src="img/item/${item.getImage() }" alt="error" style="width:230px;height:200px; border-radius: 6px 6px 0 0; border: 1px solid #524f4f;">
		                    </div>
		                    <div class="info-restaurant">
		                        <div class="info-basic-res">
		                            <a class="name-res" title="${item.getName() }" href="">${item.getName() }</a>
		                            <div class="address-res" >Số lượt bán: ${item.getSalesCount() }</div>
		                        </div>
		                        <div class="inf">
		                            <button class="price-button">${item.getPrice() } VNĐ</button>
		                            <%
			                        	if (user != null){
			                        %>
			                        <button class="add-button" onclick="AddTocart(<%=user.getUserId() %>, ${item.getItemId()}, ${item.getRestaurantId() })">Thêm vào giỏ hàng</button>
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
    </c:if>
    
    <c:if test="${not empty listItemRestaurantType3}">
    <div class="title-menu">Combo</div>
    <div class="now-list-restaurant">
        <div class="list-restaurant">
        	<c:forEach items="${listItemRestaurantType3 }" var="item">
	            <div class="item-restaurant">
	                <div class="item-content">
	                    <div class="img-restaurant">
	                        <img class="cover" src="img/item/${item.getImage() }" alt="error" style="width:230px;height:200px; border-radius: 6px 6px 0 0; border: 1px solid #524f4f;">	
	                    </div>
	                    <div class="info-restaurant">
	                        <div class="info-basic-res">
	                            <a class="name-res" title="${item.getName() }" href="">${item.getName() }</a>	
	                            <div class="address-res" >Số lượt bán: ${item.getSalesCount() }</div>
	                        </div>
	                        <div class="inf">
	                            <button class="price-button">${item.getPrice() } VNĐ</button>
	                            <%
		                        	if (user != null){
		                        %>
		                        <button class="add-button" onclick="AddTocart(<%=user.getUserId() %>, ${item.getItemId()}, ${item.getRestaurantId() })">Thêm vào giỏ hàng</button>
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
    </c:if>
    
    <!-- file js -->
    <script type="text/javascript" src="Restaurant/js/pageone.js"></script>
    
    <script type="text/javascript" src="User/js/ProcessReservation.js"></script>
    
    <script type="text/javascript">
    function SendReservation(RestaurantId, CustomerId){
    	var form = document.forms['form-reservation'];
    	var ReservationDate = encodeURIComponent(form.elements['date'].value);
    	var NumberOfGuests = encodeURIComponent(form.elements['guests'].value);
    	var AdditionalReminder = "SĐT: " + encodeURIComponent(form.elements['phone'].value) +"-Lời nhắc: "+ encodeURIComponent(form.elements['note'].value)
    	var url = "SendReservationController?&RestaurantId="+encodeURIComponent(RestaurantId)+"&CustomerId="+encodeURIComponent(CustomerId)+"&ReservationDate="+ReservationDate
    			+ "&NumberOfGuests="+NumberOfGuests+"&AdditionalReminder="+AdditionalReminder;
    	if(window.XMLHttpRequest){
    		xhttp = new XMLHttpRequest();
    	}
    	else{
    		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	
    	xhttp.onreadystatechange = function(){
    		if(xhttp.readyState == 4){
    			if(xhttp.status == 200) {
    				alert("Cập nhật đơn đặt bàn của khách hàng thành công");
    			}
    			else alert("Cập nhật đơn đặt bàn của khách hàng thất bại");
    		}
    	}	
    	xhttp.open("GET", url, true);
    	xhttp.send();
    }
    </script>
</body>
</html>