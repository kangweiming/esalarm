<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<c:if test="${query != null && query.totalItem > 0}">
	<div class="pagebar">
				<span class="floatr" style="padding-top: 4px;">显示第
					${query.pageFristItem} - ${query.pageLastItem} 条记录，共
					${query.totalItem}条记录</span>
		<c:choose>
			<c:when
				test="${query.isFirstPage()}">
					[首页]&nbsp;&nbsp;
  						[上一页]&nbsp;&nbsp;	
				
			</c:when>
			<c:otherwise>
				<a href="#" onclick="onchagePage(1);">[首页]</a>
				<a href="#" onclick="onchagePage(${query.getPreviousPage()});">[上一页]</a>&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${query.isLastPage()}">
				    [下一页]&nbsp;&nbsp;
  						[末页]
			</c:when>
			<c:otherwise>
				<a href="#" onclick="onchagePage(${query.getNextPage()});">[下一页]</a>&nbsp;&nbsp;
				<a href="#" onclick="onchagePage(${query.getTotalPage()});">[末页]</a>
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
