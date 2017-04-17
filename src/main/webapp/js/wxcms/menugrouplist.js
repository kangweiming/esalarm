$().ready(function(){
	
	var msg = $("#msg").text().trim();
	if(!isNull(msg))
		alert(msg);
	
	$("#search").click(function(){
		
		$('form:first').submit();
	});
	
	$("#reset").click(function(){
		$("#name").val("");
		$("#enable").attr("checked", false);

		$("#dateFormat").hide();
	});
	
});

function deleteMenuGroup(id){
	
	if(!confirm('确实要删除该菜单组吗?'))
		return;
	
	$('form:first').attr("action", "delete");
	$('#id').val(id);
	
	$('form:first').submit();
}

// 发布到微信公众号
function doPublish(){
	var gid = $("input[name='groupId']:checked").val();
	if(gid != null && gid != 'undefined'){
		if(confirm("确定发布微信公众号菜单?")){
			//alert(window.location.href);
			window.location.href='publishMenu?gid='+gid;
		}
	}else{
		alert("请选择菜单组");
	}
}
// 从微信删除
function doCancel(){
	if(confirm("确定删除微信公众号菜单?")){
		window.location.href='deleteMenu';
	}
}
