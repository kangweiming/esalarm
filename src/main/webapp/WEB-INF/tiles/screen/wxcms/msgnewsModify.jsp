<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/wxcms/msgnewsmodify.js"></script>

<form  id="form-modify" action="modifyAction?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="id" name="id" value="${msgNews.id}" />
	<input type="hidden" id="baseId" name="baseId" value="${msgNews.baseId}" />
	<input type="hidden" name="token" value="${token}">
	<div class="right">
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<div class="board noticeB" id="noticeinfo">
			如果 <span style="color:#555;">关键字 </span> 
			为 <span style="color:#555;">subscribe</span> ，那么此消息为订阅消息		
		</div>			
		<h2>${bodyTitle}</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">关键字：</th>
					<td><input id="inputcode" name="inputcode" type="text" value="${msgBase.inputcode}"/>
					</td>
				</tr>
				<tr>
					<th>标题：</th>
					<td><input id="title" name="title" type="text" value="${msgNews.title}"/>
					</td>
				</tr>
				<tr>
					<th>作者：</th>
					<td><input id="author" name="author" type="text" value="${msgNews.author}"/>
					</td>
				</tr>
				<tr>
					<th>简介：</th>
					<td>
						<textarea id="brief" name="brief" rows="3" cols="60" style="width:452px;">${msgNews.brief}</textarea>
						<span class="remark">长度 &lt; 100 字</span>
					</td>
				</tr>
				<tr>
					<th rowspan="2">封面图片：</th>
					<td><input type="file" id="imageFile" name="imageFile"/>
						<c:choose>
							<c:when test="${msgNews.showpic == 1}">
								 <input id="showpic" name="showpic" checked="checked"  value="1" type="checkbox" /><span>显示图片</span>
							</c:when>
							<c:otherwise>
								<input id="showpic" name="showpic" value="1" type="checkbox" /><span>显示图片</span>
							</c:otherwise>
						</c:choose>
																													
					</td>
				</tr>
				<tr>
					<td>
						<c:if  test="${msgNews.picpath != null}">
							<img src="${msgNews.picpath}" alt="${msgNews.picpath}" style="border: none;max-width:300px;height:100px;"/>
						</c:if>	
					</td>
				</tr>				
				<tr>
					<th>描述：</th>
					<td>
						<textarea class="show-kindeditor" id="description" name="description" style="width:452px;height:300px;visibility:hidden;">${msgNews.description}</textarea>
						<span class="remark">如果填写了下方的 原文链接，描述可以不填写，微信中点击消息，自动跳转到原文链接</span>
					</td>
				</tr>
				<tr>
					<th>原文链接：</th>
					<td><input id="fromurl" name="fromurl" size="80" type="text" value="${msgNews.fromurl}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="formActionB buttomBlue">
							<c:choose>
								<c:when test="${msgNews.id != null}">
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

