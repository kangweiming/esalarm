<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<div style="margin:0 auto;width:400px;">
	<form name="form1" action="/esalarm/login" method="post">
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<input type="hidden" name="action" value="loginAction" /> 
		<input
			type="hidden" name="goto"
			value="${rundata.parameters.getString('goto')}" />
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" >
			<tr>
				<td align="center">
					<h1 style="line-height: 55px; padding-top: 130px;">日志监控系统</h1>
					<c:if test="${param.error != null}">
					<div class="board errorB">
						<ol>
							用户名、密码错误。
						</ol>
					</div>
					</c:if>
					<c:if test="${param.logout != null}">
					<div class="board successB">
						<ol>
							您已经成功退出系统。				
						</ol>
					</div>
					</c:if> 					 
				</td>
			</tr>
			<tr>
				<td align="center"></td>
			</tr>
			<tr>
				<td align="center"><br />
					<P>
						<b>User Name:</b> <INPUT TYPE="text" id="username"
							NAME="ssoId" placeholder="Enter Username" 
							SIZE="15" required> <br />
						<br /></td>
			</tr>
			<tr>
				<td align="center">
					<P>
						<b>Password:</b> <INPUT TYPE="password" id="password"
							NAME="password"  placeholder="Enter Password" 							
							SIZE="15" required> <br />
						<br /> <INPUT TYPE="submit" VALUE="登录"
							NAME="event_submit_do_signIn">
				</td>
			</tr>
			<tr>
				<td align="center"><br />
				<br />
				<br />
					<p>
						<font color="#000000" face="Arial, Helvetica" size="1"> 
						<i>
							日志监控系统 <br /> 
							Version 1.0 Copyright 2017
						</i>
						</font>
					</p></td>
			</tr>
		</table>
	</form>
</div>
