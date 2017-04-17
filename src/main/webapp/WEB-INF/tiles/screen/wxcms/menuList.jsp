\<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/menulist.js"></script>

<form name="ListForm" id="ListForm" action="${basePath}/background/wxcms/accountmenu/list" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<input type="hidden" id="gid" name="baseDo.gid" value="${query.baseDo.gid}" />	
	<input type="hidden" id="id" name="id" value="" />
	<div class="right">
		<div id = "msg" style="display: none">${msg}</div>
		<div class="board errorB" id="dateFormat"
			style="display: none">
		</div>
		<div class="board noticeB" id="noticeinfo">
			<span>注意菜单组中菜单的类型及顺序</span>		
		</div>
		<h2>菜单组 [ ${menuGroup.name} ] 菜单管理</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">菜单名称：</th>
					<td colspan="2"><input type="text" id="name" name="baseDo.name" size="55" value="${query.baseDo.name}" /></td>
					<th width="15%">关键字：</th>
					<td colspan="2">
						<input type="text" id="inputcode" name="baseDo.inputcode" size="55" value="${query.baseDo.inputcode}" />						
					</td>
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
				href="modify?gid=${menuGroup.id}&timeStamp=${current_time}"
				target="_self">[ 添加菜单 ]</a>
		</div>
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging1.jsp" />
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable" name="showListTable">
					<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: center;">
						<th rowspan="2" style="width: 100px;">名称</th>
						<th colspan="3" >消息类型</th>
						<th rowspan="2" style="width: 100px;">一级菜单</th>
						<th rowspan="2" style="width: 50px;">顺序</th>
						<th rowspan="2" width="60px">操作</th>
					</tr>
					<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: center;">
						<th style="width:150px;">关键字消息</th>
						<th style="width:150px;">指定消息</th>
						<th>链接消息</th>
					</tr>
					<c:forEach var="menu" items="${menulist}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${menu.name}</td>
						<td>${menu.inputcode}</td>
						<td>${menu.msgId}</td>
						<td>${menu.url}</td>
						<td>${menu.parentName}</td>
						<td>${menu.sort}</td>
						<td>
							<a href="modify?gid=${menuGroup.id}&id=${menu.id}&timeStamp=${current_time}" target="_self">编辑</a>
							<a href="javascript:void(0)" id="deleteMenu"  onclick="deleteMenu('${menu.id}');return false;">删除</a>							
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
			<a href="modify?gid=${menuGroup.id}&timeStamp=${current_time}" target="_self">[ 添加菜单 ]</a>
		</div>
	</div>
</form>
