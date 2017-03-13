<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div style="margin">	
Dear <strong>${user.username}</strong>, Welcome to Home Page.  
    <a href="<c:url value="/logout" />">Logout</a>  
    
</div>    	