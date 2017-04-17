
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

	
<%@ page isELIgnored="false"%>


<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width,user-scalable=no" name="viewport">
		<title>${news.title}</title>
		<style>
			#id_description p{
				font-size: 16px;
				letter-spacing:2px;
				line-height:22px;
				font-family: "Microsoft YaHei" ! important;
				color: #3e3e3e;
			}
			#id_description img{
				margin-top: 2px;
				border-radius:5px; 
			}
		</style>
	</head>
	
	<body style="margin: 0px;padding: 0px;background-color:#e7e8eb;">
		<div style="padding:0px 20px;width:740px;margin:0 auto;background-color:#fff;min-height:500px;">
			<div style="height:10px;"></div>
			
			<div style="font-weight:bold;font-size:20px;border-bottom:1px solid #e7e8eb;height:50px;line-height:50px;">
			${news.title}
			</div>
		
			<div style="margin-top:10px;font-size:13px;">
				<span style="color:#676767;"><fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				<span style="margin-left:10px;color:#676767;">${news.author}</span>
				<span style="margin-left:10px;color:#607fa6;">kwm</span>
			</div>
		
			<div style="margin:15px 0px;">
				<c:if test="${news.showpic == 1}">
					<img src="${news.picpath}" style="width:100%;border:none;">
				</c:if>
			</div>
			
			<div id="id_description">
				${news.description}
			</div>
			
			<div style="height:60px;"></div>
		</div>
	</body>
</html>

