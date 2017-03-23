<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/flow/flowgoodsmodify.js"></script>

<form name="flowGoodsModifyForm" id="flowGoodsModifyForm" action="flowGoodsModify" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" name="action"  value="${formaction}" />
	<input type="hidden" name="id"  value="${goods.id}" />
	<input type="hidden" name="token" value="${token}">
	<div class="right">
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<h2>${bodyTitle}</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>商品名称：</th>
					<td colspan="2"><input type="text" id="goodsName"
						name="goodsName" size="20" value="${goods.goodsName}" required autofocus />
						<span class="remark">不超过20个字符</span>
					</td>
					<th>商品描述：</th>
					<td colspan="2"><input type="text" id="goodsDesc"
						name="goodsDesc" size="50" value="${goods.goodsDesc}" />
						<span class="remark">不超过50个字符</span>
					</td>
				</tr>
				<tr>
					<th>所属渠道：</th>
					<td colspan="2">
						<select id="goodsChannelId" name="goodsChannelId" value="${goods.goodsChannelId}">
						<c:forEach var="channel" items="${channelList}" varStatus="status">
							<c:choose>
								<c:when test="${channel.id == goods.goodsChannelId}">
									 <option value="${channel.id}"  selected>${channel.channelName}</option>
								</c:when>
								<c:otherwise>
									<option value="${channel.id}">${channel.channelName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>  						
						</select>					
					</td>
					<th>商品数量：</th>
					<td colspan="2"><input type="text" id="goodsAmount"
						name="goodsAmount" size="12" value="${goods.goodsAmount}" required />
						<span class="remark">商品数量不填写或填写'-1',表示无限</span>
					</td>
					
				</tr>					
				<tr>
					<th>运营商：</th>
					<td colspan="2">
						<select id="goodsTpId" name="goodsTpId" value="${goods.goodsTpId}">
						<c:forEach var="tp" items="${tpList}" varStatus="status">
							<c:choose>
								<c:when test="${tp.id == goods.goodsTpId}">
									 <option value="${tp.id}"  selected>${tp.tpName}</option>
								</c:when>
								<c:otherwise>
									<option value="${tp.id}">${tp.tpName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>  							
						</select>
					</td>
					<th>应用省份：</th>
					<td colspan="2">
						<select id="goodsProvinceId" name="goodsProvinceId" value="${goods.goodsProvinceId}">
						<option value="0" <c:if test="${goods.goodsProvinceId == 0}">selected</c:if> >全国</option>
						<c:forEach var="province" items="${provinceList}" varStatus="status">
							<c:choose>
								<c:when test="${province.id == goods.goodsProvinceId}">
									 <option value="${province.id}"  selected>${province.shortname}</option>
								</c:when>
								<c:otherwise>
									<option value="${province.id}">${province.shortname}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>  							
						</select>
					
					</td>
				</tr>
				<tr>
					<th>是否上架：</th>
					<td colspan="2">
						<select id="goodsState" name="goodsState" value="${goods.goodsState}">
						<option value="1" <c:if  test="${1 == goods.goodsState}">selected</c:if>>已上架</option>
						<option value="0" <c:if  test="${0 == goods.goodsState}">selected</c:if>>已下架</option>
						</select>					
					</td>
					<th>价格：</th>
					<td colspan="2">
						<input type="text" id="goodsPrice" name="goodsPrice" size="12" value="${goods.goodsPrice}" />
						<span class="remark">填写大于0的数字，可以带两位小数</span>
					</td>
					
				</tr>								
				<tr>
					<th width="15%">过期时间：</th>
					<td colspan="5">					
					<input type="text" id="goodsExpire" name="goodsExpire"
						size="12" value="<fmt:formatDate value="${goods.goodsExpire}" pattern='yyyy-MM-dd'/>" class="date-pick" />
						<span class="remark">日期格式：YYYY-MM-DD，可以不填写，不填写表示永久有效</span>
					</td>
					
				</tr>
				<tr>
					<td colspan="6">
						<div class="formActionB buttomBlue">
							<c:choose>
								<c:when test="${formaction == 'update'}">
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
