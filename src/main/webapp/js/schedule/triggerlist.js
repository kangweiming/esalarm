$(document).ready(function(){
	
	$("#search").click(function(){
		if(!dateValid()){
			$("#dateFormat").text("日期只能为数字和“-”，且格式应为：yyyy-mm-dd。");
			$("#dateFormat").show();
			return;
		}
		$("#triggerListForm").submit();
	});
	
	$("#reset").click(function(){
		$("#triggerName").val("");
		$("#triggerGroup").val("");
		$("#triggerStatus").val("");
		$("#gmtStart1").val("");
		$("#gmtEnd1").val("");
		$("#gmtStart2").val("");
		$("#gmtEnd2").val("");
		$("#dateFormat").hide();
	});

	
});

function dateValid(){
	var boolValid = true;
	var gmtStart1 = $("#gmtStart1").val().trim();
	var gmtEnd1   = $("#gmtEnd1").val().trim();
	var gmtStart2 = $("#gmtStart2").val().trim();
	var gmtEnd2   = $("#gmtEnd2").val().trim();
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
	
	var goToPagetext = $("#"+goToPage).val().trim();
	var page = $('#goPage');
	
	if(isNull(goToPagetext)){
		alert("请输入你要跳转的页面！");
		return false;
	}
	if(!isNumber(goToPagetext)){
		alert("请输入正整数！");
		return false;
	}
	
	if(goToPagetext < 1){
		alert("请输入大于0的数！");
		return false;
	}
	page.val(goToPagetext);
	$('#flag').val("GO");
	$('#triggerListForm').submit();
}

function onchagePage(pagenum){
	$('#goPage').val(pagenum);
	$('#triggerListForm').submit();
}

function pauseTrigger(optName,optGroup){
	$('#triggerListForm').attr("action", "triggerOpt");
	$('#opt').val("pauseTrigger");
	$('#optName').val(optName);
	$('#optGroup').val(optGroup);
	$('#triggerListForm').submit();
}

function resumeTrigger(optName,optGroup){
	$('#triggerListForm').attr("action", "triggerOpt");
	$('#opt').val("resumeTrigger");
	$('#optName').val(optName);
	$('#optGroup').val(optGroup);
	$('#triggerListForm').submit();
}

function runTrigger(optName,optGroup){
	$('#triggerListForm').attr("action", "triggerOpt");
	$('#opt').val("runTrigger");
	$('#optName').val(optName);
	$('#optGroup').val(optGroup);
	$('#triggerListForm').submit();
}

function deleteTrigger(optName,optGroup){
	$('#triggerListForm').attr("action", "triggerOpt");
	$('#opt').val("deleteTrigger");
	$('#optName').val(optName);
	$('#optGroup').val(optGroup);
	$('#triggerListForm').submit();
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

