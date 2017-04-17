<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/msgtextlist.js"></script>

<form name="ListForm" id="ListForm" action="${basePath}/background/wxcms/msgtext/list" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<input type="hidden" id="id" name="id" value="" />
	<input type="hidden" id="baseId" name="baseId" value="" />
	<div class="right">
		<div id = "msg" style="display: none">${msg}</div>
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<h2>文本消息管理</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">关键词：</th>
					<td colspan="2"><input type="text" id="inputcode"
						name="baseDo.inputcode" size="55" value="${query.baseDo.inputcode}" /></td>
					<th width="15%">消息内容：</th>
					<td colspan="2"><input type="text" id="content"
						name="baseDo.content" size="55" value="${query.baseDo.content}" /></td>
				</tr>
				<tr>
					<td colspan="6"><div class="formActionB buttomBlue">
							<a href="#" id="search">检索</a><a href="#" id="reset"">重置</a>
						</div></td>
				</tr>
			</table>
		</div>
		<div style="padding: 10px 30px 10px 0; text-align: right;">
			<a
				href="modify?timeStamp=${current_time}"
				target="_self">[ 添加文本信息 ]</a>
		</div>
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging1.jsp" />
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable"
					name="showListTable">
					<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: left;">
						<th width="40px">ID</th>
						<th width="60px">关键词</th>
						<th>消息内容</th>
						<th width="80px">操作</th>
					</tr>
					<c:forEach var="msgText" items="${msgTextList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${msgText.baseId}</td>
						<td>${msgText.inputcode}</td>
						<c:choose>
							<c:when test="${fn:length(msgText.content) > 100}">
								<td class="show-tooltip" title="${msgText.content}">${fn:substring(msgText.content, 0, 100)} ...</td>
							</c:when>
							<c:otherwise>
								<td class="show-tooltip" title="${msgText.content}">${msgText.content}</td>
							</c:otherwise>
						</c:choose>
						
						<td>
							<a href="modify?id=${msgText.id}">编辑</a>
							<a href="javascript:void(0)" id="deleteMsgText"  onclick="deleteMsgText('${msgText.id}','${msgText.baseId}');return false;">删除</a>
						</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div class="listB">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="cen">
								<h1>没有找到符合您要求的记录！</h1>
							</td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging2.jsp" />
		<div style="padding: 10px 30px 10px 0; text-align: right;">
			<a
				href="modify?timeStamp=${current_time}"
				target="_self">[ 添加文本信息 ]</a>
		</div>
	</div>
</form>
