function UpdateStatusError(errorId, selectStatus){
	var xhttp;
	
	var url = "UpdateStatusErrorController?errorId="+errorId+"&errorStatus="+selectStatus;
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			var data = xhttp.responseText;
            document.getElementById("main-quanlyE").innerHTML = data;
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}

function UpdateStatusNotiFromUser(notiId, selectStatus){
	var xhttp;
	
	var url = "UpdateStatusNotiFromUserController?notiId="+notiId+"&notiStatus="+selectStatus;
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			var data = xhttp.responseText;
            document.getElementById("main-errU").innerHTML = data;
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}

function UpdateStatusNotiFromAdmin(notiId, selectStatus){
	var xhttp;
	
	var url = "UpdateStatusNotiFromAdminController?notiId="+notiId+"&notiStatus="+selectStatus;
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			var data = xhttp.responseText;
            document.getElementById("main-thongbaofrom").innerHTML = data;
		}
	}	
	xhttp.open("POST", url, true);
	xhttp.send();
}