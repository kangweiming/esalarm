$().ready(function(){
	
	$("#submit").click(function(){
		
		var goodsName = $("#goodsName").val().trim();
		if(isNull(goodsName) || goodsName.length > 20){
			showErrMsg("商品名不能为空，且长度不大于20");
			return;
		}
		
		var goodsDesc = $("#goodsDesc").val().trim();
		if(!isNull(goodsDesc) && goodsDesc.length > 50){
			showErrMsg("商品描述长度不大于50");
			return;
		}
		// 
		var goodsAmount = $("#goodsAmount").val().trim();
		if(isNull(goodsAmount) || goodsAmount == -1){
			$("#goodsAmount").val("-1");
		}
		else{
			if(!isNumber(goodsAmount)){
				showErrMsg("商品数量可以不输入，也可以输入-1或大于0的整数");
				return;
			}
			
			if(goodsAmount < 1){
				showErrMsg("商品数量可以不输入，也可以输入-1或大于0的整数");
				return;
			}			
		}
		
		var goodsPrice = $("#goodsPrice").val().trim();
		if(isNull(goodsPrice) || !$.isNumeric(goodsPrice) || goodsPrice <= 0){
			showErrMsg("商品价格不能为空且必须是数字，可以带两位小数");
			return;
		}

		
		if(!dateValid()){
			showErrMsg("日期只能为数字和“-”，且格式应为：yyyy-mm-dd。");
			$("#dateFormat").show();
			return;
		}
		
		$("#flowGoodsModifyForm").submit();
	});
	
	$("#refresh").click(function(){
		window.location.reload();
	});
	
	$("#reset").click(function(){
		$("#goodsName").val("");
		$("#goodsDesc").val("");
		
		$("#goodsChannelId").val("");
		$("#goodsAmount").val("");
		
		$("#goodsTpId").val("");
		$("#goodsProvinceId").val("");
		
		$("#goodsState").val("");
		$("#goodsPrice").val("");
		
		$("#goodsExpire").val("");

		$("#dateFormat").hide();
	});
	
});

function showErrMsg(msg){
	$("#dateFormat").text(msg);
	$("#dateFormat").show();
}

// 日期校验
function dateValid(){
	var boolValid = true;
	var goodsExpire = $("#goodsExpire").val().trim();
	var regu      = /^([1-9]\d{3})-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2]\d)|(3[0-1]))$/;
	var reg       = new RegExp(regu);
	
	if(!isNull(goodsExpire)){
		if(!reg.test(goodsExpire)){
			boolValid = false;
		}
	}
	
	return boolValid;
}

function isNumber( s ){   
  var regu = "^[0-9]+$";
  var re = new RegExp(regu);
  if (s.search(re) != -1) {
       return true;
  } else {
       return false;
  }
}

function isNull( str ){
   if (str=="") return true;
   var regu = "^[ ]+$";
   var re = new RegExp(regu);
   return re.test(str);
}

