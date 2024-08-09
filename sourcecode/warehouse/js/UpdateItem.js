function updateItemId(itemId){
	var nameItem = document.getElementById('nameItem-' + itemId).value;
	var priceItem = document.getElementById('priceItem-' + itemId).value;
	var quantityItem = document.getElementById('quantityItem-' + itemId).value;
	var desItem = document.getElementById('desItem-' + itemId).value;
	var desSearchItem = document.getElementById('desSearchItem-' + itemId).value;
	
	if(!nameItem || nameItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về tên sản phẩm");
	}
	else if(!priceItem || priceItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về giá sản phẩm");
	}
	else if(!quantityItem || quantityItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về số lượng còn lại của sản phẩm");
	}
	else{
		var xhttp;	
		var url = "UpdateItemController?itemId="+encodeURIComponent(itemId)+"&nameItem="+encodeURIComponent(nameItem.trim())+"&priceItem="
				+encodeURIComponent(priceItem)+"&quantityItem="+encodeURIComponent(quantityItem)
				+"&desItem="+encodeURIComponent(desItem.trim())+"&desSearchItem="+encodeURIComponent(desSearchItem.trim());
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
					if (data.startsWith("<table>")) {
	                   alert("Cập nhật sản phẩm thành công");
	                    document.getElementById("main-danhsachsp").innerHTML = data;                    
	                } else {
	                    alert(data);
	                }
	            }
	            else{
					alert("Lỗi cập nhật sản phẩm");
				}
			}
		}
		xhttp.open("GET", url, true);
		xhttp.send();
	}	
}

function deleteItemId(itemId){
	var xhttp;
	
	var url = "DeleteItemController?itemId="+encodeURIComponent(itemId);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			var data = xhttp.responseText;
            document.getElementById("main-danhsachsp").innerHTML = data;
		}
	}
	xhttp.open("GET", url, true);
	xhttp.send();
}

function updateImgItemId(itemId){
	var form = document.getElementById("updateImageItem-" + itemId);
	var formData = new FormData(form);
	var xhttp;
	
	var url = "UpdateImgItemController?itemId="+encodeURIComponent(itemId);
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
					if (data.startsWith("<table>")) {
	                   alert("Cập nhật ảnh sản phẩm thành công");
	                    document.getElementById("main-danhsachsp").innerHTML = data;     
	                    assignImgItemChangeEvents();          
	                } else {
	                    alert(data);
	                }
	            }
	            else{
					alert("Lỗi cập nhật sản phẩm");
				}
			}
	}
	xhttp.open("POST", url, true);
	xhttp.send(formData);
}

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
		alert("Bạn không được bỏ thông tin về số lượng còn lại của sản phẩm");
	}
	else if(!typeItem || typeItem.trim() === ''){
		alert("Bạn không được bỏ thông tin về số loại của sản phẩm");
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