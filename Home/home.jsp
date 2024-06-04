<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<% 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
	request.setAttribute("path", path);
%>

<link rel="icon" type="image/x-icon" href="${path}/img/logo/logo.ico">

<meta name="viewport" content="width=device-width, initial-scale=1"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path%>/Home/css/stylemenu.css">
<link rel="stylesheet" href="<%=path%>/Home/css/stylehome.css">

</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<div id = "main" style="margin-top: 100px;">
		<div class="split left">
		
		</div>
		
		<div class="split center">
			<div id="item_container">
				<c:forEach items="${itemList}" var="item">
					<div class="food_item">
						<img class="food_img" alt="error" src="${path}/img/item/${item.getImage()}">
						<div class="food_name">${item.getName()}</div>
						<div class="food_desc">${item.getDescription()}</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="split right">
			
		</div>
	</div>
	
</body>
</html>