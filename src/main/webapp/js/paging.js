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
	$('form:first').submit();
}	

function onchagePage(pagenum){
	$('#goPage').val(pagenum);
	$('form:first').submit();
}

