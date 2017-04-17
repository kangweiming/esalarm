<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bops.css" />
		<script type="text/javascript" src="${basePath}/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			function showMsg(type){
				if(type == '1'){//news
					$('#id_msgtype').val('news');
					$('#id_news_msg').css('display','block');
					$('#id_text_msg').css('display','none');
					
					$('#id_news_span').attr('class','btn');
					$('#id_text_span').attr('class','gray-btn');
				}else{
					$('#id_msgtype').val('text');
					$('#id_news_msg').css('display','none');
					$('#id_text_msg').css('display','block');
					
					$('#id_text_span').attr('class','btn');
					$('#id_news_span').attr('class','gray-btn');
				}
			}
		</script>
	</head>
	
	<body>
		<div class="formA">
		
			<div style="text-align:center;">
				<input type="hidden" id="id_msgtype" name="msgtype" value="news"/>
				<span id="id_news_span" class="btn" style="width:150px;height:30px;text-align:center;" onclick="showMsg('1')" >图文消息</span>
				<span id="id_text_span" class="gray-btn" style="width:150px;height:30px;text-align:center;" onclick="showMsg('2')" >文本消息</span>
			</div>
			
			<div style="text-align:left;margin-top:20px;">
				<div id="id_news_msg">
					<div>
						<table width="100%" cellspacing="1" cellpadding="2" style="table-layout: fixed;border:0px" >
						<thead>
							<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: center;">
								<td style="width: 100px;">图文消息</td>
								<td style="width: 100px;">ID</td>
								<td>标题</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="news" items="${newsList}" varStatus="status">
								<c:choose>
									<c:when test="${status.count%2==0}">
										<tr style="background: #ECF6FC; height: 30px;">
									</c:when>
									<c:otherwise>
										<tr style="height: 30px;">
									</c:otherwise>
								</c:choose>
											<td><input type="checkbox" value="${news.baseId}" name="checkname"/></td>
											<td>${news.baseId}</td>
											<td>${news.title}</td>
										</tr>							
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				<div id="id_text_msg" style="display:none;">
					<div>
						<table width="100%" cellspacing="1" cellpadding="2" style="table-layout: fixed;border:0px">
							<thead>
								<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: center;">
									<td style="width: 100px;">文本消息</td>
									<td style="width: 100px;">ID</td>
									<td>描述</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="text" items="${textList}" varStatus="status">
									<c:choose>
										<c:when test="${status.count%2==0}">
											<tr style="background: #ECF6FC; height: 30px;">
										</c:when>
										<c:otherwise>
											<tr style="height: 30px;">
										</c:otherwise>
									</c:choose>
											<td><input value="${text.baseId}" type="radio" name="radioname"/></td>
											<td>${text.baseId}</td>
											<td>${text.content}</td>
											</tr>							
								</c:forEach>							
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

