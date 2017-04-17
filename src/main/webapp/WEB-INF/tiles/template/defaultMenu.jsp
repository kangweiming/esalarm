<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="left">
<div class="menu_left">
<!-- 人员管理菜单 begin -->
<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
	<h3>人员管理</h3>
	<ul>
		<li><a href="${basePath}/background/user/userList?init=true&timeStamp=${current_time}">人员管理</a></li>
	</ul>
</sec:authorize>
<!-- end -->
<!-- 定时任务管理菜单 begin -->
<sec:authorize access="hasRole('ADMIN')">
	<h3>定时任务管理</h3>
	<ul>
		<li><a href="${basePath}/background/schedule/triggerList?init=true&timeStamp=${current_time}">定时任务管理</a></li>
	</ul>
</sec:authorize>
<!-- end -->

<!-- 流量商品管理菜单 begin -->
<sec:authorize access="hasRole('ADMIN')">
	<h3>流量商城管理</h3>
	<ul>
		<li><a href="${basePath}/background/flow/flowGoodsList?init=true&timeStamp=${current_time}">流量商品列表</a></li>
	</ul>
</sec:authorize>
<!-- end -->

<!-- 微信管理菜单 begin -->
<sec:authorize access="hasRole('ADMIN')">
	<h3>微信管理</h3>
	<ul>
		<li><a href="${basePath}/background/wxcms/urltoken?timeStamp=${current_time}">URL和Token</a></li>
	</ul>
	<ul>
		<li><a href="${basePath}/background/wxcms/msgtext/list?init=true&timeStamp=${current_time}">文本消息管理</a></li>
	</ul>
	<ul>
		<li><a href="${basePath}/background/wxcms/msgnews/list?init=true&timeStamp=${current_time}">图文消息管理</a></li>
	</ul>
	<ul>
		<li><a href="${basePath}/background/wxcms/accountMenuGroup/list?init=true&timeStamp=${current_time}">菜单管理</a></li>
	</ul>
</sec:authorize>
<!-- end -->

</div>
</div>