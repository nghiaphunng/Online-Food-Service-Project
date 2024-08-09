function UpdateStatusReservationFromRestaurant(ReservationId, Status){
	var url = "UpdateStatusReservationController?&ReservationId="+encodeURIComponent(ReservationId)+"&Status="+encodeURIComponent(Status);
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
				var data = xhttp.responseText;
				document.getElementById('show-Reservation').innerHTML = data;
			}
			else alert("Cập nhật đơn đặt bàn của khách hàng thất bại");
		}
	}	
	xhttp.open("GET", url, true);
	xhttp.send();
}

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