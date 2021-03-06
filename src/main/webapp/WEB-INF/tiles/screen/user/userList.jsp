
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/user/showuser.js"></script>

<form name="userListForm" id="userListForm" action="userList" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<div class="right">
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<h2>人员信息</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>人员姓名：</th>
					<td colspan="5"><input type="text" id="userName"
						name="userName" size="55" value="${query.userName}" /></td>
				</tr>
				<tr>
					<th>密码：</th>
					<td colspan="5"><input type="text" id="password"
						name="password" size="55" value="${query.password}" /></td>
				</tr>
				<tr>
					<th>年龄：</th>
					<td colspan="5"><input type="text" id="age"
						name="age" size="55" value="${query.age}" /></td>
				</tr>
				<tr>
					<th width="15%">开始时间：</th>
					<td>开始</td>
					<td><input type="text" id="gmtStart1" name="gmtStart1"
						size="12" value="${query.gmtStart1}" class="date-pick" /></td>
					<td>结束</td>
					<td><input type="text" id="gmtEnd1" name="gmtEnd1" size="12"
						value="${query.gmtEnd1}" class="date-pick" /></td>
					<td><span class="remark">日期格式：YYYY-MM-DD</span></td>
				</tr>
				<tr>
					<th width="15%">结束时间：</th>
					<td>开始</td>
					<td><input type="text" id="gmtStart2" name="gmtStart2"
						size="12" value="${query.gmtStart2}" class="date-pick" /></td>
					<td>结束</td>
					<td><input type="text" id="gmtEnd2" name="gmtEnd2" size="12"
						value="${query.gmtEnd2}" class="date-pick" /></td>
					<td><span class="remark">日期格式：YYYY-MM-DD</span></td>
				</tr>
				<tr>
					<td colspan="6"><div class="formActionB buttomBlue">
							<a href="#" id="search">检索</a><a href="#" id="reset"">重置</a>
						</div></td>
				</tr>
			</table>
		</div>
		<br /> <br />
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging1.jsp" />
		<div style="padding: 10px 30px 10px 0; text-align: right;">
			<a
				href="userAdd?timeStamp=${current_time}"
				target="_self">[ 添加人员 ]</a>
		</div>
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable"
					name="showListTable">
					<tr
						style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: left;">
						<th width="10%">ID</th>
						<th>人员姓名</th>
						<th>密码</th>
						<th>年龄</th>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>
						<th width="10%">操作</th>
					</tr>
					<c:forEach var="user" items="${userList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${user.id}</td>
						<td>${user.userName}</td>
						<td>${user.password}</td>
						<td>${user.age}</td>
						<td>startTime</td>
						<td>endTime</td>
						<td>
							<a href="userUpdate?id=${user.id}&timeStamp=${current_time}"
								target="_self id="userUpdate">编辑</a> 
							<a href="userDelete?id=${user.id}&timeStamp=${current_time}"
								target="_self id="userDelete">删除</a>
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
				href="userAdd?timeStamp=${current_time}"
				target="_self">[ 添加人员 ]</a>
		</div>
	</div>
</form>
<!--  
	<span>es查询结果:</span>
	<c:forEach var="obj" items="${ls}">
		<br />
		<br />	
		<span>obj.jct.transName : ${obj.get('jct').get('transName')}</span>
		<span>obj: ${obj}</span>
		<br />
		<br />
	</c:forEach>
-->
