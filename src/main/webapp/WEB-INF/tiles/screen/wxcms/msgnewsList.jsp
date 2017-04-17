\<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/msgnewslist.js"></script>

<form name="ListForm" id="ListForm" action="${basePath}/background/wxcms/msgnews/list" 
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
		<h2>图文消息管理</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">关键词：</th>
					<td colspan="2"><input type="text" id="inputcode"
						name="baseDo.inputcode" size="55" value="${query.baseDo.inputcode}" /></td>
					<th width="15%">标题：</th>
					<td colspan="2"><input type="text" id="title"
						name="baseDo.title" size="55" value="${query.baseDo.title}" /></td>
				</tr>
				<tr>
					<th>作者：</th>
					<td colspan="2"><input type="text" id="author"
						name="baseDo.author" size="55" value="${query.baseDo.author}" /></td>
					<th>简介：</th>
					<td colspan="2"><input type="text" id="brief"
						name="baseDo.brief" size="55" value="${query.baseDo.brief}" /></td>
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
				target="_self">[ 添加图文信息 ]</a>
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
						<th width="80px">作者</th>
						<th width="120px">标题</th>
						<th>简介</th>
						<th width="90px">操作</th>
					</tr>
					<c:forEach var="msgNews" items="${msgNewsList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${msgNews.baseId}</td>
						<td>${msgNews.inputcode}</td>
						<td>${msgNews.author}</td>
						<td>${msgNews.title}</td>
						<c:choose>
							<c:when test="${fn:length(msgNews.brief) > 100}">
								<td class="show-tooltip" title="${msgNews.brief}">${fn:substring(msgNews.brief, 0, 100)} ...</td>
							</c:when>
							<c:otherwise>
								<td class="show-tooltip" title="${msgNews.brief}">${msgNews.brief}</td>
							</c:otherwise>
						</c:choose>
						
						<td>
							<a href="modify?id=${msgNews.id}">编辑</a>
							<a href="javascript:void(0)" id="deleteMsgText"  onclick="deleteMsgText('${msgNews.id}','${msgNews.baseId}');return false;">删除</a>							
							<c:choose>
								<c:when test="${msgNews.fromurl !=null && msgNews.fromurl !='' }">
									<a href="${msgNews.fromurl}" target="_blank">预览</a>
								</c:when>
								<c:otherwise>
									<a href="${msgNews.url}" target="_blank">预览</a>
								</c:otherwise>
							</c:choose>							
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
				target="_self">[ 添加图文信息 ]</a>
		</div>
	</div>
</form>
