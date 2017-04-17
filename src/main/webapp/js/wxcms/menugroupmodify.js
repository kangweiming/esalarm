$().ready(function(){	
		
	$("#submit").click(function(){
		
		var name = $("#name").val().trim();
		if(isNull(name) || name.length > 10){
			showErrMsg("菜单组名不能为空，且长度不大于10");
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


