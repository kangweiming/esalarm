$().ready(function(){
	
	$("#submit").click(function(){
		
		var inputcode = $("#inputcode").val().trim();
		if(isNull(inputcode) || inputcode.length > 10){
			showErrMsg("关键字不能为空，且长度不大于10");
			return;
		}
		
		var content = $("#content").val().trim();
		if(isNull(content) || content.length > 100){
			showErrMsg("消息不能为空，且消息长度不大于100");
			return;
		}
		
		$('form:first').submit();
	});
	
	$("#refresh").click(function(){
		window.location.reload();
	});
	
	$("#reset").click(function(){
		$("#inputcode").val("");
		$("#content").val("");

		$("#dateFormat").hide();
	});
	
});

function showErrMsg(msg){
	$("#dateFormat").text(msg);
	$("#dateFormat").show();
}


