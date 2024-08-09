function SearchItemFiter(){
	var xhttp;
	var location = encodeURIComponent(document.getElementById('search_scope_location').value);
	var price = encodeURIComponent(document.forms['filter-item-form']['price-range'].value);
	var type = encodeURIComponent(document.forms['filter-item-form']['type-item'].value);
	var nameInput = encodeURIComponent(document.forms['search-form']['search-name'].value);
	
	var url = "SearchItemFilterController?location="+location+"&price="+price+"&type="+type;
	if(nameInput.length > 0){
		url += "&nameInput="+nameInput;
	}
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			var data = xhttp.responseText;
			document.getElementById("list-restaurant").innerHTML = data;
		}
	}	
	xhttp.open("GET", url, true);
	xhttp.send();
}

function SearchItemLocation(urlLocation){
	var xhttp;
	var location = encodeURIComponent(document.getElementById('search_scope_location').value);
	
	var url = "SearchItemLocationController?location="+location+"&urlLocation="+encodeURIComponent(urlLocation);
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			var data = xhttp.responseText;
			document.getElementById("list-restaurant").innerHTML = data;
		}
	}	
	xhttp.open("GET", url, true);
	xhttp.send();
}

function SearchItemName(urlLocation){
	var xhttp;
	var location = encodeURIComponent(document.getElementById('search_scope_location').value);
	var nameInput = encodeURIComponent(document.forms['search-form']['search-name'].value);
	
	var url = "SearchItemNameInputController?location="+location+"&urlLocation="+encodeURIComponent(urlLocation)+"&nameInput="+nameInput;
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest();
	}
	else{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4){
			var data = xhttp.responseText;
			document.getElementById("list-restaurant").innerHTML = data;
		}
	}	
	xhttp.open("GET", url, true);
	xhttp.send();
}