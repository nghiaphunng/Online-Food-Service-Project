<%@page import="BEAN.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giỏ hàng</title>

<!--  file css -->
<link rel="stylesheet" href="Cart/css/sidebar.css">
<link rel="stylesheet" href="Cart/css/topbar.css">
<link rel="stylesheet" href="Cart/css/giohang.css">
<link rel="stylesheet" href="Cart/css/choxacnhan.css">

</head>
<body>
	<%
		User user = (User) session.getAttribute("sessionUser");
		String nameAva = user.getUrlAvatar();
		String fullNameUser = user.getNameUser();
	%>
	
	<div class="sidebar">
    	<div class="logo_details">
    		<div class="logo_name">Giỏ Hàng</div>
    	</div>
    	
		<ul>			
			<li>
				<a href="#">
					<i class='bx bx-user'></i>
					<span class="links_name">Chờ nhà hàng xác nhận</span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class='bx bxs-truck'></i>
					<span class="links_name">Giao hàng thành công</span>
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
				<div class="khohang">
				    <a href="HomeForward">Trang chủ</a>
				</div>
				<div class="logOut">
				    <a href="LogoutController">Đăng xuất</a>
				</div>
			</div>
   		</div>

	<div class="chonhahang">
		<c:forEach items="${cartItemStatus1 }" var="cart">
			<div class="main-choxacnhan" id="main-choxacnhan">
				<h3>${cart.value[0].getNameRestaurant() }</h3>
				<table>
					<thead>
						<tr>
							<th>Tên khách hàng</th>
						    <th>Tên sản phẩm</th>
						    <th>Hình ảnh</th>
						    <th>Giá</th>
						    <th>Số lượng</th>
						    <th>Tổng</th>
						    <th>Trạng thái</th>
						</tr>
						<c:forEach items="${cart.value }" var="item">
							<tr>
								<td title="Liên hệ: ${item.getPhoneUser() } Địa chỉ: ${item.getAddressUser() }">${item.getFullNameUser() }</td>
							    <td>${item.getNameItem() }</td>
							    <td><img src="img/item/${item.getUrlItem() }" alt="error" width="50"></td>
							    <td>${item.getPriceItem() } VNĐ</td>
							    <td><input type="number" value="${item.getQuantityItem() }"  class="quantity" readonly></td>
							    <td class="total">${item.getPriceItem() }</td>
							    <td>Đang chờ nhà hàng xác nhận</td>
							</tr>
						</c:forEach>	
					</thead>
					<tbody></tbody>
				</table>
	
				<div class="restaurant-total">
					<button class="pay-button" onclick="updateCartStatus(${cart.value[0].getCartId()}, 2)">Xác nhận gửi hàng</button>		    
				    <span>Tổng tiền: </span>
				    <span class="total-price">0d</span>
				</div>
			</div>
		</c:forEach>	
	</div>
	
	<div class="thanhcong">
		<c:forEach items="${cartItemStatus3 }" var="cart">
			<div class="main-choxacnhan" id="main-choxacnhan">
				<h3>${cart.value[0].getNameRestaurant() }</h3>
				<table>
					<thead>
						<tr>
							<th>Tên khách hàng</th>
						    <th>Tên sản phẩm</th>
						    <th>Hình ảnh</th>
						    <th>Giá</th>
						    <th>Số lượng</th>
						    <th>Tổng</th>
						    <th>Hành động</th>
						</tr>
						<c:forEach items="${cart.value }" var="item">
							 <tr>
							 	<td title="Liên hệ: ${item.getPhoneUser() } Địa chỉ: ${item.getAddressUser() }">${item.getFullNameUser() }</td>
							     <td>${item.getNameItem() }</td>
							     <td><img src="img/item/${item.getUrlItem() }" alt="erorr" width="50"></td>
							     <td>${item.getPriceItem() } VNĐ</td>
							     <td><input type="number" value="${item.getQuantityItem() }"  class="quantity" readonly></td>
							     <td class="total">${item.getPriceItem() }</td>
							     <td>Giao hàng thành công</td>
							 </tr>
						</c:forEach>
					 </thead>
				     <tbody></tbody>
				 </table>
				 
				<div class="restaurant-total">
				    <span>Tổng tiền: </span>
				    <span class="total-price">0d</span>			    
				</div>
			</div>
		</c:forEach>	
	</div>
</section>

</body>
<script>
    document.querySelector(".chonhahang").style.display = "display";
    document.querySelector(".thanhcong").style.display = "none";

  	document.addEventListener("DOMContentLoaded", function() {
    const links = document.querySelectorAll(".sidebar li a");
  
    links.forEach(link => {
      link.addEventListener("click", function(event) {
        event.preventDefault();
  
        const currentActive = document.querySelector(".sidebar li a.active");
        if (currentActive) {
          currentActive.classList.remove("active");
        }

        link.classList.add("active");

        const linkText = link.querySelector(".links_name").textContent.trim();
		if (linkText === "Chờ nhà hàng xác nhận") {
          document.querySelector(".chonhahang").style.display = "block";
          document.querySelector(".thanhcong").style.display = "none";
    
        }
		else if (linkText === "Giao hàng thành công") {
          document.querySelector(".chonhahang").style.display = "none";
          document.querySelector(".thanhcong").style.display = "block";   
        }   
      });
    });
  });

  function calculateTotalPrice() {
  const carts = document.querySelectorAll('.main-choxacnhan');
  
  carts.forEach(cart => {
    let total = 0;
    const rows = cart.querySelectorAll('tr');
    
    rows.forEach(row => {
      const quantityInput = row.querySelector('.quantity');
      const priceElement = row.querySelector('td:nth-child(4)');
      const totalElement = row.querySelector('.total');
      
      if (quantityInput && priceElement && totalElement) {
        const quantity = parseInt(quantityInput.value);
        const price = parseFloat(priceElement.textContent.replace('VNĐ', '').replace(',', ''));
        const itemTotal = quantity * price;
        
        total += itemTotal;
        totalElement.textContent = itemTotal.toLocaleString() + ' VNĐ';
      }
    });

    const totalPriceElement = cart.querySelector('.total-price');
    totalPriceElement.textContent = total.toLocaleString() + ' VNĐ';
  });
}

// Event listener to recalculate total when quantity changes
document.addEventListener('DOMContentLoaded', () => {
  const quantityInputs = document.querySelectorAll('.quantity');

  quantityInputs.forEach(input => {
    input.addEventListener('change', calculateTotalPrice);
  });

  // Initial calculation
  calculateTotalPrice();
});

  
  </script>
  
  <script type="text/javascript" src="Cart/js/ProcessCart.js"></script>
</body>
</html>