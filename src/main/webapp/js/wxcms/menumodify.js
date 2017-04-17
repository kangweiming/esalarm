$().ready(function(){	
	
	// -------------------
	// 消息类型更改事件
	$("#id_msg_type").change(function(){
		var value = $("#eventType").val();
		if(value == 'key'){
			$("#id_keymsg").css("display","")
			$("#id_fixmsg").css("display","none")
		}else{
			$("#id_keymsg").css("display","none")
			$("#id_fixmsg").css("display","")
		}
		
	});
	
	// 菜单类型更改事件
	$("#mtype").change(function(){
		var value = $("#mtype").val();
		if(value == 'click'){
			$(".id_view").css("display","none")
			$(".id_msg").css("display","")
			// 处理类型为消息的UI
			$("#eventType").change();
		}
		else{
			$(".id_view").css("display","")
			$(".id_msg").css("display","none")
			
		}
	});	
	
	$("#mtype").change();
	
	// 选择消息
	$('#selectMsg').click(function(){
		// jquery dialog
		$('#id_msgs').dialog({
			title:'选择消息 -- 图文消息可多选、文本消息单选',
	        width: 600,
	        height:400,
	        modal: true,
	        buttons: {
	            "确定": function() {
	            	var msgtype = $("#id_msgs_frame").contents().find('input[name="msgtype"]').val();
	            	if(msgtype == 'news'){
	            		var val = [];
	            		$("#id_msgs_frame").contents().find('input[name="checkname"]:checked').each(function(){ 
	            			val.push($(this).val())
	                    })
	            		if(val.length > 0){
		            		$("#msgId").val(val.join(','));
		            	}
	            	}else{
	            		var val = $("#id_msgs_frame").contents().find('input[name="radioname"]:checked').val();
	            		if(val != undefined){
		            		$("#msgId").val(val);
		            	}
	            	}
	                $(this).dialog('close');
	            }
	        }
	    });			
	});
	

	// -------------------
		
	$("#submit").click(function(){
		
		var name = $("#name").val().trim();
		if(isNull(name) || name.length > 10){
			showErrMsg("菜单名不能为空，且长度不大于10");
			return;
		}
		
		// 校验还需更加详细
		
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


