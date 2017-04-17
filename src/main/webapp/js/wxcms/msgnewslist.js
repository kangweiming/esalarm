$().ready(function(){
	
	var msg = $("#msg").text().trim();
	if(!isNull(msg))
		alert(msg);
	
	$("#search").click(function(){
		
		$('form:first').submit();
	});
	
	$("#reset").click(function(){
		$("#inputcode").val("");
		$("#title").val("");
		$("#author").val("");
		$("#brief").val("");

		$("#dateFormat").hide();
	});
	
});

function deleteMsgText(id,baseId){
	
	if(!confirm('确实要删除该消息吗?'))
		return;
	
	$('form:first').attr("action", "delete");
	$('#id').val(id);
	$('#baseId').val(baseId);
	
	$('form:first').submit();
}

