jQuery().ready(function(){
	
	jQuery("#search").click(function(){
		if(!dateValid()){
			jQuery("#dateFormat").text("日期只能为数字和“-”，且格式应为：yyyy-mm-dd。");
			jQuery("#dateFormat").show();
			return;
		}
		jQuery("#userListForm").submit();
	});
	
	jQuery("#reset").click(function(){
		jQuery("#userName").val("");
		jQuery("#password").val("");
		jQuery("#age").val("");
		jQuery("#gmtStart1").val("");
		jQuery("#gmtEnd1").val("");
		jQuery("#gmtStart2").val("");
		jQuery("#gmtEnd2").val("");
		jQuery("#dateFormat").hide();
	});
	
});

function dateValid(){
	var boolValid = true;
	var gmtStart1 = trim(jQuery("#gmtStart1").val());
	var gmtEnd1   = trim(jQuery("#gmtEnd1").val());
	var gmtStart2 = trim(jQuery("#gmtStart2").val());
	var gmtEnd2   = trim(jQuery("#gmtEnd2").val());
	var regu      = /^([1-9]\d{3})-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2]\d)|(3[0-1]))$/;
	var reg       = new RegExp(regu);
	
	if(!isNull(gmtStart1)){
		if(!reg.test(gmtStart1)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtEnd1)){
		if(!reg.test(gmtEnd1)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtStart2)){
		if(!reg.test(gmtStart2)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtEnd2)){
		if(!reg.test(gmtEnd2)){
			boolValid = false;
		}
	}
	
	return boolValid;
}

function goToPageDirectory(goToPage){
	var goToPagetext = document.getElementById(goToPage);
	var page = jQuery('#goPage');
	goToPagetext.value = trim(goToPagetext.value);
	if(isNull(goToPagetext.value)){
	 alert("请输入你要跳转的页面！");
	 return false;
	}
	if(!isNumber(goToPagetext.value)){
	 alert("请输入整数！");
	 return false;
	}
	
	if(goToPagetext.value < 1){
	 alert("请输入大于0的数！");
	 return false;
	}
	page.val(goToPagetext.value);
	jQuery('#flag').val("GO");
	jQuery('#userListForm').submit();
}	

function onchagePage(pagenum){
	jQuery('#goPage').val(pagenum);
	jQuery('#userListForm').submit();
}

function isNumber( s ){   
  var regu = "^[0-9]+$";
  var re = new RegExp(regu);
  if (s.search(re) != -1) {
       return true;
  } else {
       return false;
  }
}

function isNull( str ){
   if (str=="") return true;
   var regu = "^[ ]+$";
   var re = new RegExp(regu);
   return re.test(str);
}

function trim(stringToTrim) {
	return stringToTrim.replace(/(^\s*)|(\s*$)/g, "")
}
