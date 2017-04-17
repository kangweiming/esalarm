<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bops.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery-ui-1.12.1.css">
<script type="text/javascript" src="${basePath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-ui-1.12.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript" src="${basePath}/js/paging.js"></script>

<!-- kindeditor -->
<link 	type="text/css"	href="${basePath}/kindeditor/themes/default/default.css" rel="stylesheet">
<script type="text/javascript" src="${basePath}/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${basePath}/kindeditor/zh_CN.js"></script>

<script type="text/javascript">

	// kindeditor
	KindEditor.ready(function(K) {
		// 必须增加spring security的csrf参数，extraFileUploadParams参数不好用
		_uploadJson = '${basePath}/background/wxcms/ckeditorImage?${_csrf.parameterName}=${_csrf.token}';
		editor = K.create('.show-kindeditor', {
			
			resizeType:1,
			minWidth:300,
			allowPreviewEmoticons:false,
			allowImageUpload : true,
			uploadJson:_uploadJson,// 上传功能的实现页面
			afterUpload: function(){this.sync();}, //图片上传后，将上传内容同步到textarea中
            afterBlur: function(){this.sync();},   ////失去焦点时，将上传内容同步到textarea中	
			items : ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			         'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			         'insertunorderedlist','|','image', 'link','source','about']
		});
	});	
	
	// datepicker
	$(function() {
		$(".date-pick").datepicker({
			dateFormat : "yy-mm-dd",
			showOn : "button",
			buttonImage : "${basePath}/images/icon_calendar.gif",
			buttonImageOnly : true,
			changeMonth: true,
			changeYear: true,
			buttonText : "Select date"
		});
		
		// tooltip
		$(".show-tooltip").tooltip({
		      position: {
		          my: "center bottom-20",
		          at: "center top",
		          using: function( position, feedback ) {
		            $( this ).css( position );
		            $( "<div>" )
		              .addClass( "arrow" )
		              .addClass( feedback.vertical )
		              .addClass( feedback.horizontal )
		              .appendTo( this );
		          }
		        }
		});		
	});
	

</script>
<style>
  .ui-tooltip, .arrow:after {
    background: black;
    border: 2px solid white;
  }
  .ui-tooltip {
    padding: 10px 20px;
    color: white;
    border-radius: 20px;
    font: bold 14px "Helvetica Neue", Sans-Serif;
    text-transform: none;
    box-shadow: 0 0 7px black;
  }
  .arrow {
    width: 70px;
    height: 16px;
    overflow: hidden;
    position: absolute;
    left: 50%;
    margin-left: -35px;
    bottom: -16px;
  }
  .arrow.top {
    top: -16px;
    bottom: auto;
  }
  .arrow.left {
    left: 20%;
  }
  .arrow:after {
    content: "";
    position: absolute;
    left: 20px;
    top: -20px;
    width: 25px;
    height: 25px;
    box-shadow: 6px 5px 9px -9px black;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
  }
  .arrow.top:after {
    bottom: -20px;
    top: auto;
  }
  </style>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="topbottom"></div>
	<div class="pageB">
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>