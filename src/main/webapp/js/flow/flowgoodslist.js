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
		
		$("#flowGoodsListForm").submit();
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
	
	$('#flowGoodsListForm').attr("action", "flowGoodsDelete");
	$('#goodsID').val(goodsID);
	$('#flowGoodsListForm').submit();
}

function goToPageDirectory(goToPage){
	var goToPagetext = $("#"+goToPage).val().trim();
	var page = $('#goPage');
	
	if(isNull(goToPagetext)){
		alert("请输入你要跳转的页面！");
		return false;
	}
	if(!isNumber(goToPagetext)){
		alert("请输入正整数！");
		return false;
	}
	
	if(goToPagetext < 1){
		alert("请输入大于0的数！");
		return false;
	}
	page.val(goToPagetext);
	$('#flag').val("GO");
	$('#flowGoodsListForm').submit();
}	

function onchagePage(pagenum){
	$('#goPage').val(pagenum);
	$('#flowGoodsListForm').submit();
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

