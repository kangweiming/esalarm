$().ready(function(){
	
	var msg = $("#msg").text();
	if(!isNull(msg))
		alert(msg);
	$("#search").click(function(){
		if(!dateValid()){
			$("#dateFormat").text("日期只能为数字和“-”，且格式应为：yyyy-mm-dd。");
			$("#dateFormat").show();
			return;
		}
		
		if(!priceRangeValid()){
			$("#dateFormat").text("价格范围只能是数字。");
			$("#dateFormat").show();
			return;			
		}
		
		$('form:first').submit();
	});
	
	$("#reset").click(function(){
		$("#goodsName").val("");
		$("#goodsDesc").val("");
		$("#goodsTpId").val("");
		$("#goodsProvinceId").val("");
		
		$("#goodsChannelId").val("");
		
		$("#goodsState").val("");
		$("#startPrice").val("");
		$("#endPrice").val("");
		
		$("#gmtStartExpire").val("");
		$("#gmtEndExpire").val("");
		$("#gmtStartCreate").val("");
		$("#gmtEndCreate").val("");
		$("#dateFormat").hide();
	});
	
});

// 价格区间校验
function priceRangeValid(){
	
	var boolValid = true;
	var startPrice = $("#startPrice").val().trim();
	var endPrice = $("#endPrice").val().trim();
	
	if(!isNull(startPrice)){
		if(!$.isNumeric(startPrice))
			boolValid = false;
	}
	if(!isNull(endPrice)){
		if(!$.isNumeric(endPrice))
			boolValid = false;
	}
	
	return boolValid;
}

// 日期校验
function dateValid(){
	var boolValid = true;
	var gmtStartExpire = $("#gmtStartExpire").val().trim();
	var gmtEndExpire   = $("#gmtEndExpire").val().trim();
	var gmtStartCreate = $("#gmtStartCreate").val().trim();
	var gmtEndCreate   = $("#gmtEndCreate").val().trim();
	var regu      = /^([1-9]\d{3})-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2]\d)|(3[0-1]))$/;
	var reg       = new RegExp(regu);
	
	if(!isNull(gmtStartExpire)){
		if(!reg.test(gmtStartExpire)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtEndExpire)){
		if(!reg.test(gmtEndExpire)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtStartCreate)){
		if(!reg.test(gmtStartCreate)){
			boolValid = false;
		}
	}
	
	if(!isNull(gmtEndCreate)){
		if(!reg.test(gmtEndCreate)){
			boolValid = false;
		}
	}
	
	return boolValid;
}

function deleteFlowGoods(goodsID){
	
	if(!confirm('确实要删除该商品吗?'))
		return;
	
	$('form:first').attr("action", "flowGoodsDelete");
	$('#goodsID').val(goodsID);
	$('form:first').submit();
}

