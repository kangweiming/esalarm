<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/menugroupmodify.js"></script>

<form  id="form-modify" action="modifyAction" method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<!-- 防止重复刷新 -->
	<input type="hidden" name="token" value="${token}">
	<input type="hidden" id="id" name="id" value="${menuGroup.id}" />
	
	<div class="right">		
		<h2>${bodyTitle}</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">菜单组名称：</th>
					<td><input id="name" name="name" type="text" value="${menuGroup.name}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="formActionB buttomBlue">
							<c:choose>
								<c:when test="${menuGroup.id != null}">
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

