<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
</head>
<body>
<h2>发生了错误</h2>
<div>
	${excepitonType} 
	</br>
	
	<c:forEach var="msg" items="${exceptionTrace}" varStatus="status">
		${msg}
	</c:forEach> 	
	</br>
	${exceptionMessage}
	
</div>
</body>
</html>
