$().ready(function(){
	
	var msg = $("#msg").text().trim();
	if(!isNull(msg))
		alert(msg);
	
	$("#search").click(function(){
		
		$('form:first').submit();
	});
	
	$("#reset").click(function(){
		$("#name").val("");
		$("#inputcode").val("");

		$("#dateFormat").hide();
	});
	
});

function deleteMenu(id){
	
	if(!confirm('确实要删除该菜单吗?'))
		return;
	
	$('form:first').attr("action", "delete");
	$('#id').val(id);
	$('form:first').submit();
}
