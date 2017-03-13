<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bops.css" />
<script type="text/javascript" src="${basePath}/js/base.js"></script>
<script type="text/javascript" src="${basePath}/js/prototype.js"></script>

</head>
<body style="margin:10px auto;background:#dcdcdc;" >
		<tiles:insertAttribute name="body" />
</body>
</html>