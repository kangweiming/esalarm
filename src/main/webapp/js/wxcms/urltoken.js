$().ready(function(){
	
	
	var flag = $("#successflag").text();
	if(!isNull(flag)){
		$("#successflag").text("已成功获取URL 和 Token，请填写到微信平台中");
		$("#successflag").show();
		
	}
	
	$("#submit").click(function(){
		var account = $('#id_account').val();
		
		if(isNull(account)){
			showErrMsg("公众号Id不能为空。");
			return;
		}
		
		if(!rf.cValid(account)){
			showErrMsg("公众号Id必须由字母、数字、'-'、'_'组成,且长度在3-15之间。");
			return;			
		}
		
		$('form:first').submit();
	});
	
});

function showErrMsg(msg){
	$("#errorinfo").text(msg);
	$("#errorinfo").show();
}



