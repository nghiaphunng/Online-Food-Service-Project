function AddTocart(userId, itemId, restaurantId){
	var url = "AddToCartController?userId="+encodeURIComponent(userId)+"&itemId="+encodeURIComponent(itemId)+"&restaurantId="+encodeURIComponent(restaurantId);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				alert(xhttp.responseText);
			}
			else{
				alert('Xin lỗi, đã xảy ra lỗi hệ thống. Chúng tôi sẽ sớm khắc phục');
			}
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}