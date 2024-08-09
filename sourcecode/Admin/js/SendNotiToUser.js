function SendNotiToUser(){
	var recipientuser = document.getElementById('recipientuser').value;
	var topic = document.getElementById('topic').value;
	var message = document.getElementById('message').value;
	
	var url = "SendNotiToUserController?recipientuser="+encodeURIComponent(recipientuser)+"&topic="+encodeURIComponent(topic)+"&message="+encodeURIComponent(message);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			if(xhttp.status == 200) {
				alert("Gửi thông báo thành công");
				document.getElementById('recipientuser').value = '';
				topic = document.getElementById('topic').value = '';
				document.getElementById('message').value = '';
			}
			else alert("Gửi thông báo thấi bại");
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}