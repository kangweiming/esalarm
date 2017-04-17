<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/msgtextmodify.js"></script>

<form  action="modifyAction" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="id" name="id" value="${msgText.id}" />
	<input type="hidden" id="baseId" name="baseId" value="${msgText.baseId}" />
	<input type="hidden" name="token" value="${token}">
	<div class="right">
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<div class="board noticeB" id="noticeinfo">
			如果 <span style="color:#555;">关键字 </span> 
			为 <span style="color:#555;">subscribe</span> ，那么此消息为订阅消息		
		</div>			
		<h2>${bodyTitle}</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">关键字：</th>
					<td><input id="inputcode" name="inputcode" type="text" value="${msgBase.inputcode}"/>
					</td>
				</tr>
				<tr>
					<th>消息内容：</th>
					<td><textarea id="content" name="content" rows="10" cols="30">${msgText.content}</textarea>
					</td>
				</tr>				
				<tr>
					<td colspan="2">
						<div class="formActionB buttomBlue">
							<c:choose>
								<c:when test="${msgText.id != null}">
									 <a href="#" id="submit">更新</a><a href="#" id="refresh" >刷新</a>
								</c:when>
								<c:otherwise>
									<a href="#" id="submit">添加</a><a href="#" id="reset" >重置</a>
								</c:otherwise>
							</c:choose>																			
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>

