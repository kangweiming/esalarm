<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div style="margin:0 auto;width:400px; height:100px; ">

	Dear <strong>${user.username}</strong>, You are not authorized to access this page  
    <a class="logoutlink" href="<c:url value="/logout" />">Logout</a> 
   
</div>    	