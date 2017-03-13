
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSP页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>纯JSP测试页-es</title>
<link rel="stylesheet" type="text/css" href="css/bops.css" />
</head>

<script type="text/javascript" src="js/user/showuser.js"></script>

<body>

	 
	<span>es查询结果:</span>
	<div>
		<c:forEach var="obj" items="${ls}">
			<br />
			<br />	
			<span>obj.jct : ${obj.get('jct')}</span>
			<span>obj: ${obj}</span>
			<br />
			<br />
		</c:forEach>
	</div>

</body>
</html>