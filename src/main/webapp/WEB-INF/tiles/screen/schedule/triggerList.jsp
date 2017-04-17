<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>

<script type="text/javascript" src="${basePath}/js/schedule/triggerlist.js"></script>

<form name="triggerListForm" id="triggerListForm" action="triggerList"
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	
	<input type="hidden" id="opt" name="opt" value="" />
	<input type="hidden" id="optName" name="optName" value="" />
	<input type="hidden" id="optGroup" name="optGroup" value="" />
	
	<input type="hidden" name="token" value="${token}">
	
	<div class="right">
		<div class="board errorB" id="dateFormat" 
			style="display: none"></div>
		<h2>任务信息</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>任务名称：</th>
					<td colspan="5"><input type="text" id="triggerName"
						name="triggerName" size="55" value="${query.triggerName}" />
						<span class="remark">模糊查询</span>
						</td>
				</tr>
				<tr>
					<th>任务分组：</th>
					<td colspan="5"><input type="text" id="triggerGroup"
						name="triggerGroup" size="55" value="${query.triggerGroup}" />
						<span class="remark">模糊查询</span>
						</td>
				</tr>
				<tr>
					<th>任务状态：</th>
					<td colspan="5">
						<select id="triggerStatus" name="triggerStatus" value="${query.triggerStatus}">
						<option value="">请选择</option>
						<c:forEach var="state" items="${triggerState}" varStatus="status">
							<c:choose>
								<c:when test="${state == query.triggerStatus}">
									 <option value="${state}"  selected>${state}</option>
								</c:when>
								<c:otherwise>
									<option value="${state}">${state}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
  							
						</select>	
					</td>
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
					<td><span class="remark">日期格式：YYYY-MM-DD (Trigger可能没有endTime)</span></td>
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
				href="taskAdd?timeStamp=${current_time}"
				target="_self">[ 添加任务 ]</a>
		</div>
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable"
					name="showListTable">
					<tr
						style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: left;">
						<th width="80px">任务名称</th>
						<th width="80px">任务分组</th>
						<th>任务描述</th>
						<th>DSL</th>
						<th width="80px">时间表达式</th>
						<th width="80px">状态</th>
						<th width="115px">开始时间</th>
						<th width="115px">下次触发时间</th>
						<th width="60px">触发次数</th>
						<th width="160px">操作</th>
					</tr>
					<c:forEach var="task" items="${taskList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;" >
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${task.triggerKey.name}</td>
						<td>${task.trigger.group}</td>
						
						<c:choose>
							<c:when test="${fn:length(task.jobDetail.description) > 20}">
								<td class="show-tooltip" title="${task.jobDetail.description}">${fn:substring(task.jobDetail.description, 0, 20)} ...</td>
							</c:when>
							<c:otherwise>
								<td class="show-tooltip" title="${task.jobDetail.description}">${task.jobDetail.description}</td>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${fn:length(task.DSL) > 20}">
								<td class="show-tooltip" title="${task.DSL}">${fn:substring(task.DSL, 0, 20)} ...</td>
							</c:when>
							<c:otherwise>
								<td class="show-tooltip" title="${task.DSL}">${task.DSL}</td>
							</c:otherwise>
						</c:choose>
												
						<td>${task.cronExpression}</td>
						<td>${task.triggerStatus}</td>
						<td><fmt:formatDate value="${task.trigger.getStartTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${task.trigger.getNextFireTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${task.fireCount}</td>
						<td>							
							<a href="javascript:void(0)" id="deleteTrigger"  onclick="deleteTrigger('${task.triggerKey.name}','${task.triggerKey.group}');return false;">删除</a>
							<c:choose>
								<c:when test="${task.triggerStatus == 'NORMAL'}">
									<a href="javascript:void(0)" id="pasueTrigger"  onclick="pauseTrigger('${task.triggerKey.name}','${task.triggerKey.group}');return false;">暂停</a>
								</c:when>
								<c:when test="${task.triggerStatus == 'PAUSED'}">
									<a href="javascript:void(0)" id="resumeTrigger" onclick="resumeTrigger('${task.triggerKey.name}','${task.triggerKey.group}');return false;">恢复</a>
								</c:when>
							</c:choose>								
								
							<a href="javascript:void(0)" id="runTrigger"  onclick="runTrigger('${task.jobKey.name}','${task.jobKey.group}');return false;">立即运行一次</a>
						    	
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
								<h1>没有找到符合您要求的任务记录！</h1>
							</td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
		<tiles:insertTemplate template="/WEB-INF/tiles/template/paging2.jsp" />
		<div style="padding: 10px 30px 10px 0; text-align: right;">
			<a
				href="taskAdd?timeStamp=${current_time}"
				target="_self">[ 添加任务 ]</a>
		</div>
	</div>	
</form>

