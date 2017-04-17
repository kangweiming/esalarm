<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/urltoken.js"></script>

<form name="getUrlForm" id="getUrlForm" action="${basePath}/background/wxcms/getUrl" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" name="id" value="${account.id}"/>
	<!-- 用于防刷新 -->
	<input type="hidden" name="token" value="${token}">
	<div class="right">
		<div class="board errorB" id="errorinfo" style="display: none"></div>
		<div class="board successB" id="successflag" style="display: none">${successflag}</div>
		<div class="board noticeB" id="noticeinfo">
			<span>填写 <span style="color:#555;">公众号ID</span>，点击 <span style="color:#555;">保存</span> 按钮，
			系统将自动生成 URL 和 Token 。将它们填写到公众平台 
			<span style="color:#555;">开发者中心</span> 中，公众账号即可升级成为开发者账号
			</span>		
		</div>
		<h2>URL 和 Token</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">公众号ID：</th>
					<td>
						<input id="id_account" name="account" size="20" type="text" value="${account.account}"/>
						<span class="remark">*(字母或者数字)</span>
					</td>
				</tr>
				<tr>
					<th>URL：</th>
					<td>
						<input id="id_url" readonly="readonly" name="url" size="100" type="text" value="${account.url}"/>
					</td>
				</tr>
				<tr>
					<th>Token：</th>
					<td>
						<input id="id_tocken" readonly="readonly" name="token" size="100" type="text" value="${account.token}"/>
					</td>
				</tr>
				<tr>
					<th>AppId：</th>
					<td>
						<input name="appid" size="100" type="text" value="${account.appid}"/>
					</td>
				</tr>
				<tr>
					<th>AppSecret：</th>
					<td>
						<input name="appsecret" size="100" type="text" value="${account.appsecret}"/>
					</td>
				</tr>
				<tr>
					<th>消息条数：</th>
					<td>
						<select id="msgcount" name="msgcount" value="${goods.goodsState}">
							<option value="1" <c:if  test="${1 == account.msgcount}">selected</c:if>>1条</option>
							<option value="2" <c:if  test="${2 == account.msgcount}">selected</c:if>>2条</option>
							<option value="3" <c:if  test="${3 == account.msgcount}">selected</c:if>>3条</option>
							<option value="4" <c:if  test="${4 == account.msgcount}">selected</c:if>>4条</option>
							<option value="5" <c:if  test="${5 == account.msgcount}">selected</c:if>>5条</option>
							<option value="6" <c:if  test="${6 == account.msgcount}">selected</c:if>>6条</option>
							<option value="7" <c:if  test="${7 == account.msgcount}">selected</c:if>>7条</option>
							<option value="8" <c:if  test="${8 == account.msgcount}">selected</c:if>>8条</option>
						</select>					
					</td>
					
				</tr>								
				<tr>
					<td colspan="2">
						<div class="formActionB buttomBlue">
							<a href="#" id="submit">保存</a>																		
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
