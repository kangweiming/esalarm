
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSP页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员信息</title>
<link rel="stylesheet" type="text/css" href="css/bops.css" />
</head>

<script type="text/javascript" src="js/user/showuser.js"></script>

<body>
	<form name="bigBuyerListForm" id="bigBuyerListForm"
		action="${backofficeModule.setTarget('/bigbuyer/bigBuyerList.htm')}"
		method="post">
		<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<input type="hidden" id="curPage" name="curPage" value="$!{curPage}" />
		<input type="hidden" id="goPage" name="goPage" value="$!{goPage}" />
		<input type="hidden" id="flag" name="flag" value="$!{flag}" />

		<div class="right">
			<div class="board errorB" id="dateFormat" name="dateFormat"
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
						<td colspan="5"><input type="text" id="password"
							name="password" size="55" value="${query.age}" /></td>
					</tr>
					<tr>
						<th width="15%">开始时间：</th>
						<td>开始</td>
						<td><input type="text" id="gmtStart1" name="gmtStart1"
							size="12" value="" class="date-pick" /></td>
						<td>结束</td>
						<td><input type="text" id="gmtEnd1" name="gmtEnd1" size="12"
							value="" class="date-pick" /></td>
						<td><span class="remark">日期格式：YYYY-MM-DD</span></td>
					</tr>
					<tr>
						<th width="15%">结束时间：</th>
						<td>开始</td>
						<td><input type="text" id="gmtStart2" name="gmtStart2"
							size="12" value="" class="date-pick" /></td>
						<td>结束</td>
						<td><input type="text" id="gmtEnd2" name="gmtEnd2" size="12"
							value="" class="date-pick" /></td>
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
			<c:if test="${query != null && query.totalItem > 0}">
				<div class="pagebar">

					<c:choose>
						<c:when test="${query != null && query.totalItem > 0}">
							<span class="floatr" style="padding-top: 4px;">显示第
								${query.pageFristItem} - ${query.pageLastItem} 条记录，共
								${query.totalItem}条记录</span>
						</c:when>

						<c:otherwise>
							<span class="floatr" style="padding-top: 4px;">显示第 0 - 0
								条记录，共 0条记录</span>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when
							test="${query != null && query.totalItem > 0 && !query.isFirstPage()}">
							<a href="#" onclick="onchagePage(1);">[首页]</a>
						</c:when>

						<c:otherwise>
							<a>[首页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;

					<c:choose>
						<c:when test="${query.isFirstPage()}">
							<a>[上一页]</a>
						</c:when>

						<c:otherwise>
							<a href="#" onclick="onchagePage(${query.getPreviousPage()});">[上一页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;

					<c:choose>
						<c:when test="${query.isLastPage()}">
							<a>[下一页]</a>
						</c:when>

						<c:otherwise>
							<a href="#" onclick="onchagePage(${query.getNextPage()});">[下一页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;
					<c:choose>
						<c:when
							test="${query != null && query.totalItem > 0 && !query.isLastPage()}">
							<a href="#" onclick="onchagePage(${query.getTotalPage()});">[末页]</a>
						</c:when>
						<c:otherwise>
							<a>[末页]</a>
						</c:otherwise>
					</c:choose>

					<span style="padding-left: 20px;" id="bottom">第${query.getCurrentPage()}
						页， 共 ${query.getTotalPage()} 页&nbsp;转到: <input id="toPage1"
						name="toPage1" value="" type="text" size="2" maxlength="4" /> <input
						type="button" name="Submit3" value=" Go "
						onclick="goToPageDirectory('toPage1')" />
					</span>
				</div>

			</c:if>

			<div style="padding: 10px 30px 10px 0; text-align: right;">
				<a
					href="${backofficeModule}/bigbuyer/bigBuyerAdd.htm?timeStamp=$!{current_time}"
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

							<td><a
								href="${backofficeModule}/bigbuyer/exceldownload/bigBuyerList.htm?id=$!{bigBuyerDO.getId()}"
								id="exportExl">导出</a> <a
								href="${backofficeModule}/bigbuyer/bigBuyerSignUpList.htm?bigbuyer_id=$!{bigBuyerDO.getId()}&timeStamp=$!{current_time}"
								target="_blank" id="checkSignUpList">查看</a></td>
							</tr>
						</c:forEach>

					</table>
				</c:when>

				<c:otherwise>
					<div class="listB">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="cen">
									<h1>没有找到符合您要求的人员记录！</h1>
								</td>
							</tr>
						</table>
					</div>
				</c:otherwise>
			</c:choose>

			<c:if test="${query != null && query.totalItem > 0}">
				<div class="pagebar">

					<c:choose>
						<c:when test="${query != null && query.totalItem > 0}">
							<span class="floatr" style="padding-top: 4px;">显示第
								${query.pageFristItem} - ${query.pageLastItem} 条记录，共
								${query.totalItem}条记录</span>
						</c:when>

						<c:otherwise>
							<span class="floatr" style="padding-top: 4px;">显示第 0 - 0
								条记录，共 0条记录</span>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when
							test="${query != null && query.totalItem > 0 && !query.isFirstPage()}">
							<a href="#" onclick="onchagePage(1);">[首页]</a>
						</c:when>

						<c:otherwise>
							<a>[首页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;

					<c:choose>
						<c:when test="${query.isFirstPage()}">
							<a>[上一页]</a>
						</c:when>

						<c:otherwise>
							<a href="#" onclick="onchagePage(${query.getPreviousPage()});">[上一页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;

					<c:choose>
						<c:when test="${query.isLastPage()}">
							<a>[下一页]</a>
						</c:when>

						<c:otherwise>
							<a href="#" onclick="onchagePage(${query.getNextPage()});">[下一页]</a>
						</c:otherwise>
					</c:choose>

					&nbsp;&nbsp;
					<c:choose>
						<c:when
							test="${query != null && query.totalItem > 0 && !query.isLastPage()}">
							<a href="#" onclick="onchagePage(${query.getTotalPage()});">[末页]</a>
						</c:when>
						<c:otherwise>
							<a>[末页]</a>
						</c:otherwise>
					</c:choose>

					<span style="padding-left: 20px;" id="bottom">第${query.getCurrentPage()}
						页， 共 ${query.getTotalPage()} 页&nbsp;转到: <input id="toPage1"
						name="toPage1" value="" type="text" size="2" maxlength="4" /> <input
						type="button" name="Submit3" value=" Go "
						onclick="goToPageDirectory('toPage1')" />
					</span>
				</div>

			</c:if>

			<div style="padding: 10px 30px 10px 0; text-align: right;">
				<a
					href="${backofficeModule}/bigbuyer/bigBuyerAdd.htm?timeStamp=$!{current_time}"
					target="_self">[ 添加人员 ]</a>
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

</body>
</html>