<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
	
<script type="text/javascript" src="${basePath}/js/flow/flowgoodslist.js"></script>

<form name="flowGoodsListForm" id="flowGoodsListForm" action="flowGoodsList" 
	method="post">
	<!-- spring security -->
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
	<input type="hidden" id="goPage" name="goPage" value="${goPage}" /> 
	<input type="hidden" id="flag" name="flag" value="${flag}" />
	<input type="hidden" id="goodsID" name="goodsID" value="" />
	<div class="right">
		<div id = "msg" style="display: none">${msg}</div>
		<div class="board errorB" id="dateFormat"
			style="display: none"></div>
		<h2>流量商品列表</h2>
		<div class="formA">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>商品名称：</th>
					<td colspan="2"><input type="text" id="goodsName"
						name="goodsName" size="55" value="${query.goodsName}" /></td>
					<th>商品描述：</th>
					<td colspan="2"><input type="text" id="goodsDesc"
						name="goodsDesc" size="55" value="${query.goodsDesc}" /></td>
				</tr>
				<tr>
					<th>运营商：</th>
					<td colspan="2">
						<select id="goodsTpId" name="goodsTpId" value="${query.goodsTpId}">
						<option value="">请选择</option>
						<c:forEach var="tp" items="${tpList}" varStatus="status">
							<c:choose>
								<c:when test="${tp.id == query.goodsTpId}">
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
						<select id="goodsProvinceId" name="goodsProvinceId" value="${query.goodsProvinceId}">
						<option value="">请选择</option>
						<option value="0" <c:if test="${query.goodsProvinceId == 0}">selected</c:if> >全国</option>
						<c:forEach var="province" items="${provinceList}" varStatus="status">
							<c:choose>
								<c:when test="${province.id == query.goodsProvinceId}">
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
					<th>所属渠道：</th>
					<td colspan="5">
						<select id="goodsChannelId" name="goodsChannelId" value="${query.goodsChannelId}">
						<option value="">请选择</option>	 
						<c:forEach var="channel" items="${channelList}" varStatus="status">
							<c:choose>
								<c:when test="${channel.id == query.goodsChannelId}">
									 <option value="${channel.id}"  selected>${channel.channelName}</option>
								</c:when>
								<c:otherwise>
									<option value="${channel.id}">${channel.channelName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>  						
						</select>					
					</td>
				</tr>				
				<tr>
					<th>是否上架：</th>
					<td colspan="2">
						<select id="goodsState" name="goodsState" value="${query.goodsState}">
						<option value="">请选择</option>	 
						<option value="1" <c:if  test="${'1' == query.goodsState}">selected</c:if>>已上架</option>
						<option value="0" <c:if  test="${'0' == query.goodsState}">selected</c:if>>已下架</option>
						</select>					
					</td>
					<th>价格范围：</th>
					<td>
						从&nbsp;<input type="text" id="startPrice" name="startPrice" size="20" value="${query.startPrice}" />
					</td>
					<td>
						到&nbsp;<input type="text" id="endPrice" name="endPrice" size="20" value="${query.endPrice}" />
					</td>
				</tr>								
				<tr>
					<th width="15%">过期时间：</th>
					<td>开始</td>
					<td><input type="text" id="gmtStartExpire" name="gmtStartExpire"
						size="12" value="${query.gmtStartExpire}" class="date-pick" /></td>
					<td width="15%">结束</td>
					<td><input type="text" id="gmtEndExpire" name="gmtEndExpire" size="12"
						value="${query.gmtEndExpire}" class="date-pick" /></td>
					<td><span class="remark">日期格式：YYYY-MM-DD</span></td>
				</tr>
				<tr>
					<th width="15%">创建时间：</th>
					<td>开始</td>
					<td><input type="text" id="gmtStartCreate" name="gmtStartCreate"
						size="12" value="${query.gmtStartCreate}" class="date-pick" /></td>
					<td width="15%">结束</td>
					<td><input type="text" id="gmtEndCreate" name="gmtEndCreate" size="12"
						value="${query.gmtEndCreate}" class="date-pick" /></td>
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
				href="flowGoodsModify?timeStamp=${current_time}"
				target="_self">[ 添加商品 ]</a>
		</div>
		<c:choose>
			<c:when test="${query != null && query.totalItem > 0}">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"
					style="table-layout: fixed;" id="showListTable"
					name="showListTable">
					<tr style="background: #CCCCCC; line-height: 20px; height: 20px; text-align: left;">
						<th width="40px">ID</th>
						<th width="60px">品名</th>
						<th>描述</th>
						<th width="80px">所属渠道</th>
						<th width="40px">运营商</th>
						<th width="40px">范围</th>
						<th width="50px">价格</th>
						<th width="40px">数量</th>
						<th width="40px">状态</th>
						<th width="115px">过期时间</th>
						<th width="115px">创建时间</th>
						<th width="80px">操作</th>
					</tr>
					<c:forEach var="goods" items="${goodsList}" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr style="background: #ECF6FC; height: 30px;">
							</c:when>
							<c:otherwise>
								<tr style="height: 30px;">
							</c:otherwise>
						</c:choose>
						<td>${goods.id}</td>
						<td>${goods.goodsName}</td>
						<c:choose>
							<c:when test="${fn:length(goods.goodsDesc) > 20}">
								<td class="show-tooltip" title="${goods.goodsDesc}">${fn:substring(goods.goodsDesc, 0, 20)} ...</td>
							</c:when>
							<c:otherwise>
								<td class="show-tooltip" title="${goods.goodsDesc}">${goods.goodsDesc}</td>
							</c:otherwise>
						</c:choose>
						
						<c:forEach var="channel" items="${channelList}" varStatus="status">
							<c:choose>
								<c:when test="${channel.id == goods.goodsChannelId}">
									 <td>${channel.channelName}</td>
								</c:when>
							</c:choose>
						</c:forEach> 
						
						<c:forEach var="tp" items="${tpList}" varStatus="status">
							<c:choose>
								<c:when test="${tp.id == goods.goodsTpId}">
									 <td>${tp.tpName}</td>
								</c:when>
							</c:choose>
						</c:forEach> 
						
						<c:choose>
							<c:when test="${goods.goodsProvinceId == 0}">
								<td>全国</td>
							</c:when>
							<c:otherwise>
								<c:forEach var="province" items="${provinceList}" varStatus="status">
									<c:choose>
										<c:when test="${province.id == goods.goodsProvinceId}">
											 <td>${province.shortname}</td>
										</c:when>
									</c:choose>
								</c:forEach> 						
							</c:otherwise>
						</c:choose>
												
						<td>${goods.goodsPrice}</td>
						<c:choose>
							<c:when test="${goods.goodsAmount == null or goods.goodsAmount == -1}">
								<td>无限</td>
							</c:when>
							<c:otherwise>
								<td>${goods.goodsAmount}</td>
							</c:otherwise>
						</c:choose>						

						<c:choose>
							<c:when test="${goods.goodsState == 1}">
								<td>已上架</td>
							</c:when>
							<c:otherwise>
								<td>已下架</td>
							</c:otherwise>
						</c:choose>	
						<td><fmt:formatDate value="${goods.goodsExpire}" pattern="yyyy-MM-dd HH:mm:ss"/></td>					
						<td><fmt:formatDate value="${goods.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>					
						<td>
							<a href="flowGoodsModify?id=${goods.id}">编辑</a>
							<a href="javascript:void(0)" id="deleteFlowGoods"  onclick="deleteFlowGoods('${goods.id}');return false;">删除</a>
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
				href="flowGoodsModify?timeStamp=${current_time}"
				target="_self">[ 添加商品 ]</a>
		</div>
	</div>
</form>

