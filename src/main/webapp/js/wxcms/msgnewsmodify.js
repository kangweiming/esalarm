$().ready(function(){	
		
	$("#submit").click(function(){
		
		var inputcode = $("#inputcode").val().trim();
		if(isNull(inputcode) || inputcode.length > 10){
			showErrMsg("关键字不能为空，且长度不大于10");
			return;
		}
		
		var title = $("#title").val().trim();
		if(isNull(title) || title.length > 20){
			showErrMsg("标题不能为空，且长度不大于20");
			return;
		}
		var author = $("#author").val().trim();
		if(isNull(author) || author.length > 20){
			showErrMsg("作者不能为空，且长度不大于20");
			return;
		}
		var brief = $("#brief").val().trim();
		if(brief.length > 100){
			showErrMsg("简介长度不能大于100");
			return;
		}
		
		var fromurl = $("#fromurl").val().trim();
		var description = $("#description").val().trim();
		if(isNull(fromurl) && isNull(description)){
			showErrMsg("描述和原文链接至少填写一项");
			return;
		}		
		else if(!isNull(fromurl) && fromurl.length > 100){
			showErrMsg("原文链接长度不能大于100");
			return;			
		}
		else if(!isNull(description) && description.length > 500){
			showErrMsg("描述长度不能大于500");
			return;			
		}
		
		
		$('form:first').submit();
	});
	
	$("#refresh").click(function(){
		window.location.reload();
	});
	
	$("#reset").click(function(){
		document.getElementById("form-modify").reset();

		$("#dateFormat").hide();
	});
	
});

function showErrMsg(msg){
	$("#dateFormat").text(msg);
	$("#dateFormat").show();
}


