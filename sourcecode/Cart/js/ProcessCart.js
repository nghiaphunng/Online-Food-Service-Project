function updateQuantityItemCart(cartId, itemId, newQuantity){
	var url = "UpdateQuantityItemCart?cartId=" + cartId + "&itemId=" + itemId + "&newQuantity=" + newQuantity;
	if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            location.reload();
        } else if (xhttp.readyState == 4 && xhttp.status != 200) {
            alert("Cập nhật số lượng sản phẩm không thành công");
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function updateCartStatus(cartId, newStatus){
	var url = "UpdateCartStatus?cartId=" + cartId + "&newStatus=" + newStatus;
	if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            alert("Xác nhận thành công");
			location.reload();
        } else if (xhttp.readyState == 4 && xhttp.status != 200) {
            alert("Xác nhận thất bại");
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}
