function DeleteUser(userId){
	var url = "DeleteUserIdController?userId="+encodeURIComponent(userId);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				alert("Xóa tài khoản người dùng thành công");
				var data = xhttp.responseText;
				document.getElementById('main-quanlyU').innerHTML = data;
			}
			else alert("Xóa tài khoản người dùng thất bại");
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}

function DeleteRestaurant(RestaurantId){
	var url = "DeleteRestaurantIdController?RestaurantId="+encodeURIComponent(RestaurantId);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				alert("Xóa tài khoản nhà hàng thành công");
				var data = xhttp.responseText;
				document.getElementById('main-quanlyR').innerHTML = data;
			}
			else alert("Xóa tài khoản nhà hàng thất bại");
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}

function DeleteCartStatus3(userId){
	var url = "DeleteCartStatus3Controller?userId="+encodeURIComponent(userId);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				alert("Xóa lịch sử giao hàng người dùng thành công");
				var data = xhttp.responseText;
				document.getElementById('main-quanlyU').innerHTML = data;
			}
			else alert("Xóa lịch sử giao hàng người dùng thấi bại");
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}

function DisplayUserFilter(){
	var form = document.forms['form-admin-user-filter'];
    var name = form['name'].value;
    var type = form['sort'].value;
    var limit = form['recordCount'].value;
	var url = "DisplayUserFilterController?name="+encodeURIComponent(name)+"&type="+encodeURIComponent(type)+"&limit="+encodeURIComponent(limit);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				var data = xhttp.responseText;
				document.getElementById('main-quanlyU').innerHTML = data;
			}
		}
	}	
	xhttp.open("GET", url, true);
	xhttp.send();
}

function DisplayRestaurantFilter(){
	var form = document.forms['form-admin-restaurant-filter'];
    var name = form['name'].value;
    var limit = form['recordCount'].value;
	var url = "DisplayRestaurantFilterController?name="+encodeURIComponent(name)+"&limit="+encodeURIComponent(limit);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				var data = xhttp.responseText;
				document.getElementById('main-quanlyR').innerHTML = data;
			}
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}