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
<script type="text/javascript">
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