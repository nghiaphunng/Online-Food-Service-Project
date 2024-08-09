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
 
<title>Quản lý kho hàng</title>
 

<!-- file css -->
<link rel="stylesheet" href="warehouse/css/sidebar.css">
<link rel="stylesheet" href="warehouse/css/topbar.css">
<link rel="stylesheet" href="warehouse/css/danhsachsp.css">
<link rel="stylesheet" href="warehouse/css/themsp.css">
<link rel="stylesheet" href="warehouse/css/filter.css">
<style type="text/css">


.file-upload-wrapper {
    display: flex;
    align-items: center;
}

.file-upload-input {
    display: none;
}

.file-upload-button, .submit-button, .delete-button {
    padding: 5px 10px;
    cursor: pointer;
    white-space: nowrap; /* Prevent text from wrapping */
}

.file-upload-button {
	margin: auto 0px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
}

.submit-button {
	font-size: 15px;
	margin: auto 0px;
    background-color: #28a745;
    color: white;
    border: none;
}

.delete-button {
	margin: auto 0px;
    background-color: #dc3545;
    color: white;
    border: none;
}

.file-upload-button:focus, .submit-button:focus, .delete-button:focus {
    outline: none;
}
</style>
</head>
<body>
	<%
		//người dùng
		User user = (User) session.getAttribute("sessionUser");		
		//nhà hàng
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant"); 
	%>
	<div class="sidebar">
    	<div class="logo_details">     
			<div class="logo_name">Kho hàng</div>
    	</div>
    <ul>
      <li>
        <a href="#" class="active">
          <i class='bx bx-grid-alt'></i>
          <span class="links_name">
            Danh sách sản phẩm
          </span>
        </a>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-user'></i>
          <span class="links_name">
            Thêm sản phẩm
          </span>
        </a>
      </li>
    </ul>
</div>
  <!-- End Sideber -->
  <section class="home_section">
    <div class="topbar">
        <div class="user_wrapper">
            <img src="<%=restaurant.getImgRestaurant() %>" alt="error">
            <h3>Xin chào, <%=restaurant.getRestaurantName() %></h3>
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
    <div class="danhsachsp">
        <div class="filter-section">
            <form id="filter-form">
              <label for="search-name">Tên:</label>
              <input type="text" id="search-name" name="name" placeholder="Nhập tên...">
              
              <label for="sort-order">Sắp xếp:</label>
              <select id="sort-order" name="sort">
                <option value="0">Bán nhiều nhất</option>
                <option value="1">Bán ít nhất</option>
                <option value="2">Sớm nhất</option>
                <option value="3">Mới nhất</option>
              </select>
              
              
              
              <button type="submit">Lọc</button>
            </form>
          </div>
      <div class="main-danhsachsp" id="main-danhsachsp">
        <table>
            <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Hình ảnh</th>
                    <th>Cập nhật ảnh</th>
                    <th title="Đơn vị VNĐ">Giá</th>
                    <th>Số lượng đã bán</th>
                    <th>Hàng tồn</th>
                    <th>Mô tả</th>
                    <th title="Sản phẩm sẽ dễ tiếp cận hơn với khách hàng khi họ tìm kiếm">Từ khóa</th>
                    <th>Xóa</th>
                    <th>Cập nhật</th>
                </tr>
			</thead>
			
			<tbody>
                <c:forEach items="${listItemRestaurant }" var="item">
	                <tr>
	                    <td><input type="text" id="nameItem-${item.getItemId()}" name="itemName" value="${item.getName()}" title="${item.getName()}"><br></td>
	                    <td>
	                        <img src="img/item/${item.getImage() }" alt="error" width="50" id="display-img">
	                    </td>
	                    <td>
	                		<form id="updateImageItem-${item.getItemId()}" method="post" enctype="multipart/form-data">
	                            <div class="file-upload-wrapper">
	                                <input type="file" class="file-upload-input" id="file-upload-input-${item.getItemId()}" name="imageItem">
	                                <label for="file-upload-input-${item.getItemId()}" class="file-upload-button" id="file-upload-button-${item.getItemId()}">Chọn tệp</label>
	                                <button type="button" class="submit-button" onclick="updateImgItemId(${item.getItemId()})">Cập nhật</button>
	                            </div>
							</form>
	                    </td>                   
	                    <td><input type="text" id="priceItem-${item.getItemId()}" name="price" value="${item.getPrice() }"></td>
	                    <td><input type="number" value="${item.getSalesCount() }" class="quantity" readonly title="Đây là số lượng đơn bán không thể thay đổi"></td>
	                    <td><input type="number" id="quantityItem-${item.getItemId()}" value="${item.getNumberOfItem() }" min="0" class="quantity" ></td>	                    
	                    <td><input type="text" id="desItem-${item.getItemId()}" name="des" value="${item.getDescription() }" title="${item.getDescription() }"><br></td>
	                    <td><input type="text" id="desSearchItem-${item.getItemId()}" name="desSearch" value="${item.getSearchSuggestion() }" title="${item.getSearchSuggestion() }"><br></td>
	                    <td>
	                    	<button type="button" class="delete-button" onclick="deleteItemId(${item.getItemId()})">Xóa</button>
	                    </td>
 	                    <td>
	                    	<button type="button" class="submit-button" onclick="updateItemId(${item.getItemId()})">Cập nhật</button>
 	                    </td>
	                </tr>
	             </c:forEach>                   
            </tbody>  
        </table>
        </div>
    </div>
    
    <div class="themsp">
        <div class="modal-container" id="modal-container">
            <div id="modal">
                <div class="modal-header" id="modal-header">
                    <h3>Thêm món ăn</h3>
                </div>
                <div class="modal-body">
                    <form name="add-item-form" id="add-item-form-<%=restaurant.getRestaurantId() %>">
                        <label for="name">Tên món:</label>
                        <input type="text" id="name" name="nameItem"><br>
                        <label for="des">Mô tả:</label>
                        <input type="text" id="des" name="desItem"><br>
                        <label for="price" title="Chỉ cần nhập số (Ví dụ: 10000 tức 10000 VNĐ)">Giá:</label>
                        <input type="text" id="price" name="priceItem"><br>
                        <label>Mặt hàng:</label>
                        <select name="typeItem">
                            <option value="1">Đồ ăn</option>
                            <option value="2">Đồ uống</option>
                            <option value="3">Combo</option>
                        </select><br>
                        <label for="type" title="Sản phẩm sẽ dễ tiếp cận hơn với khách hàng khi họ tìm kiếm">Từ khóa tìm kiếm:</label>
                        <input type="text" id="type" name="desSearchItem"><br>
                        <label for="soluong">Số lượng:</label>
                        <input type="text" id="soluong" name="quantityItem" value="1"><br><br>
                        <input style="background-color: #ff6600; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-top: 10px;"
                        	type="button" value="Thêm sản phẩm" id="submit" onclick="insertItem(<%=restaurant.getRestaurantId() %>)">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- file js -->
<script type="text/javascript">
function assignImgItemChangeEvents() {
    <c:forEach items="${listItemRestaurant}" var="item">
        document.getElementById('file-upload-input-${item.getItemId()}').addEventListener('change', function() {
            const fileName = this.files.length > 0 ? this.files[0].name : 'Chọn tệp';
            const button = document.getElementById('file-upload-button-${item.getItemId()}');
            button.textContent = fileName.length > 20 ? fileName.slice(0, 20) + '...' : fileName;
        });
    </c:forEach>
}

window.onload = function() {
	assignImgItemChangeEvents();
};
</script>
<script type="text/javascript" src="warehouse/js/ProcessPage.js"></script>
<script type="text/javascript" src="warehouse/js/UpdateItem.js"></script>

<script type="text/javascript">
function insertItem(restaurantId){
	var form = document.getElementById('add-item-form-' + restaurantId);
	var nameItem = form.elements['nameItem'].value;
	var desItem = form.elements['desItem'].value;
	var desSearchItem = form.elements['desSearchItem'].value;
	var priceItem = form.elements['priceItem'].value;
	var typeItem = form.elements['typeItem'].value;
	var quantityItem = form.elements['quantityItem'].value;

	if(!nameItem || nameItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về tên sản phẩm");
	}
	else if(!priceItem || priceItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về giá sản phẩm");
	}
	else if(!quantityItem || quantityItem.trim() === ''){
		alert("Bạn không được bor thông tin về số lượng còn lại của sản phẩm");
	}
	else if(!typeItem || typeItem.trim() === ''){
		alert("Bạn không được bor thông tin về số lượng còn lại của sản phẩm");
	}
	else{
		var xhttp;	
		var url = "InsertItemController?restaurantId="+encodeURIComponent(restaurantId)
					+"&nameItem="+encodeURIComponent(nameItem)
					+"&desItem="+encodeURIComponent(desItem)
					+"&desSearchItem="+encodeURIComponent(desSearchItem)
					+"&priceItem="+encodeURIComponent(priceItem)
					+"&typeItem="+encodeURIComponent(typeItem)
					+"&quantityItem="+encodeURIComponent(quantityItem);
					
		if(window.XMLHttpRequest){
			xhttp = new XMLHttpRequest();
		}
		else{
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4){
				if(xhttp.status == 200){
					var data = xhttp.responseText;
		            alert(data);
		           	location.reload();
				}
				else{
					alert("Lỗi thêm sản phẩm");
				}	
			}	
		}
		xhttp.open("GET", url, true);
		xhttp.send();
	}
}
</script>
</body>
</html>