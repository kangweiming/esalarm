<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/menumodify.js"></script>

<form  id="form-modify" action="modifyAction" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="id" name="id" value="${menu.id}" />
	<input type="hidden" id="gid" name="gid" value="${gid}" />
	<input type="hidden" name="token" value="${token}">
	<div class="right">
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<div class="board noticeB" id="noticeinfo">
			<span>微信公众账号的菜单管理：可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单</span>		
		</div>			
		<h2>${bodyTitle}</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">菜单名称：</th>
					<td>
						<input id="name" name="name" type="text" value="${menu.name}"/>
						<span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<th>一级菜单：</th>
					<td>
						<select id="parentid" name="parentid">
						<option value="0">一级菜单</option>
						<c:forEach var="parentMenu" items="${parentMenuList}" varStatus="status">
							<c:choose>
								<c:when test="${parentMenu.id == menu.parentid}">
									 <option value="${parentMenu.id}"  selected>${parentMenu.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${parentMenu.id}">${parentMenu.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>  							
						</select>					
					</td>
				</tr>	
				<tr>
					<th>顺序：</th>
					<td>
						<select id="sort" name="sort" >
							<option value="1" <c:if test="${menu.sort == 1}">selected</c:if>>1</option>
							<option value="2" <c:if test="${menu.sort == 2}">selected</c:if>>2</option>
							<option value="3" <c:if test="${menu.sort == 3}">selected</c:if>>3</option>
							<option value="4" <c:if test="${menu.sort == 4}">selected</c:if>>4</option>
							<option value="5" <c:if test="${menu.sort == 5}">selected</c:if>>5</option>
							<option value="6" <c:if test="${menu.sort == 6}">selected</c:if>>6</option>
							<option value="7" <c:if test="${menu.sort == 7}">selected</c:if>>7</option>
							<option value="8" <c:if test="${menu.sort == 8}">selected</c:if>>8</option>
						</select>
					</td>
				</tr>							
				<tr>
					<th>菜单类型：</th>
					<td>
						<select id="mtype" name="mtype" >
							<option value="click" <c:if test="${menu.mtype == 'click'}">selected</c:if>>消息</option>
							<option value="view" <c:if test="${menu.mtype == 'view'}">selected</c:if>>链接</option>
						</select>
						<span class="remark">消息：点击菜单时回复消息；链接：点击菜单打开链接</span>
					</td>
				</tr>
				<tr class="id_msg" id="id_msg_type">
					<th>消息类型：</th>
					<td>
						<select id="eventType" name="eventType" >
							<option value="key" <c:if test="${menu.eventType == 'key'}">selected</c:if>>关键字</option>
							<option value="fix" <c:if test="${menu.eventType == 'fix'}">selected</c:if>>指定</option>
						</select>
					</td>
				</tr>
				<tr class="id_msg" id="id_keymsg">
					<th>关键字：</th>
					<td>
						<input id="inputcode" name="inputcode" type="text" value="${menu.inputcode}"/>
						<span class="remark">消息的关键字</span>
					</td>
				</tr>
				<tr class="id_msg" id="id_fixmsg" >
					<th>指定消息：</th>
					<td>
						<input id="msgId" name="msgId" type="text" value="${menu.msgId}" readonly/>
						<input id="selectMsg" type="button" value="选择">
					</td>
				</tr>
				<tr class="id_view">
					<th>链接URL：</th>
					<td>
						<input id="url" name="url" type="text" size="80" value="${menu.url}"/>
						<span style="color:red">*</span>
					</td>
				</tr>
											
				<tr>
					<td colspan="2">
						<div class="formActionB buttomBlue">
							<c:choose>
								<c:when test="${menu.id != null}">
									 <a href="#" id="submit">更新</a><a href="#" id="refresh" >刷新</a>
								</c:when>
								<c:otherwise>
									<a href="#" id="submit">添加</a><a href="#" id="reset" >重置</a>
								</c:otherwise>
							</c:choose>																			
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<!-- 选择消息承载面板 -->
<div id="id_msgs" style="display:none">
	<iframe id="id_msgs_frame" style="width:100%;height:100%;border:none;" src="${basePath}/background/wxcms/msgbase/menuMsgs">
   	</iframe>
</div>