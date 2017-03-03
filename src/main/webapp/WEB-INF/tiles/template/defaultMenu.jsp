<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="left">
<div class="menu_left">
<!-- 人员菜单 begin -->
<!-- 建立登录机制后，需要验证登录及登录后的权限 -->
<h3>人员管理</h3>
<ul>
	<li><a href="${basePath}/background/userList?init=true&timeStamp=${current_time}">人员信息</a></li>
</ul>
<!-- 人员菜单 begin -->

<sec:authorize access="hasRole('ADMIN')">
	<h3>任务管理</h3>
	<ul>
		<li><a href="${basePath}/background/triggerList?init=true&timeStamp=${current_time}">任务信息</a></li>
	</ul>
</sec:authorize>
</div>
</div>