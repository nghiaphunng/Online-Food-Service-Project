function SendNotiToAdmin(){
	var topic = document.getElementById('topicToAdmin').value;
	var message = document.getElementById('messageToAdmin').value;
	
	var url = "SendNotiToAdminController?&topic="+encodeURIComponent(topic)+"&message="+encodeURIComponent(message);
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
				topic = document.getElementById('topic').value = '';
				document.getElementById('message').value = '';
			}
			else alert("Gửi thông báo thấi bại");
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}