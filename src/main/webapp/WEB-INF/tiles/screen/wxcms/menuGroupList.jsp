\<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/menugrouplist.js"></script>

<form name="ListForm" id="ListForm" action="${basePath}/background/wxcms/accountMenuGroup/list" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<input type="hidden" id="id" name="id" value="" />
	<div class="right">
		<div id = "msg" style="display: none">${msg}</div>
		<div class="board errorB" id="dateFormat"
			style="display: none">
		</div>
		<div class="board noticeB" id="noticeinfo">
			<span>每个账号可以创建多套菜单，根据不同情况选择不同的菜单</span>		
		</div>
		<h2>菜单组管理</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">菜单组名称：</th>
					<td><input type="text" id="name"
						name="baseDo.name" size="55" value="${query.baseDo.name}" /></td>
					<th width="15%">是否在用：</th>
					<td>
						<c:choose>
							<c:when test="${query.baseDo.enable == 1}">
								 <input id="enable" name="baseDo.enable" checked="checked"  value="1" type="checkbox" />
							</c:when>
							<c:otherwise>
								<input id="enable" name="baseDo.enable" value="1" type="checkbox" />
							</c:otherwise>
						</c:choose>						
					</td>
				</tr>
				<tr>
					<td colspan="4"><div class="formActionB buttomBlue">
							<a href="#" id="search">检索</a><a href="#" id="reset"">重置</a>
						</div></td>
				</tr>
			</table>
		</div>
		<div style="padding: 10px 30px 10px 0; text-align: right;">
			<a
				href="modify?timeStamp=${current_time}"
				target="_self">[ 添加菜单组 ]</a>
		</div>
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging1.jsp" />
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable"
					name="showListTable">
					<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: left;">
						<th width="20px"></th>
						<th>菜单组名称</th>
						<th width="80px">是否在用</th>
						<th width="180px">操作</th>
					</tr>
					<c:forEach var="menuGroup" items="${menuGroupList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td><input type="radio" name="groupId" value="${menuGroup.id}"/></td>
						<td>${menuGroup.name}</td>
						<td>
							<c:choose>
								<c:when test="${menuGroup.enable == 1}">
									<span>是</span>
								</c:when>
								<c:otherwise>
									<span>否</span>
								</c:otherwise>
							</c:choose>													
						</td>
						<td>
							<a href="modify?id=${menuGroup.id}">编辑菜单组</a>
							<a href="${basePath}/background/wxcms/accountmenu/list?baseDo.gid=${menuGroup.id}">管理组内菜单</a>
							<a href="javascript:void(0)" id="deleteMenuGroup"  onclick="deleteMenuGroup('${menuGroup.id}');return false;">删除</a>							
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
			<a href="modify?timeStamp=${current_time}" target="_self">[ 添加菜单组 ]</a>
		</div>
		<div class="formActionB buttomBlue" >
			<a href="javascript:void(0)" id="doPublish"  onclick="doPublish();return false;">生成微信账号菜单</a>
			<a href="javascript:void(0)" id="doCancel"  onclick="doCancel();return false;">删除微信账号菜单</a>			
		</div>
	</div>
</form>
