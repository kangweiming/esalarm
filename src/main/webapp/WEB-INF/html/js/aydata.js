$(document).ready(function() {

    // 手机号码查询成功后调用
    function findMobileSuccess(data) {
        //console.log(data);
        if (data != undefined && data != null) {
            $('.alert-warning').css('display', 'none');
            $('.text-muted').css('display', 'block');
            $('.text-muted').html(data.carrier);
            
            // 查询商品
            searchGoods(data);
        }
    };

    // 手机号码查询失败后调用
    function findMobileFailure() {
        $('.text-muted').css('display', 'none');
        $('.alert-warning').css('display', 'block');
        
        // 清理显示区域
        
    };

    //键盘输入查询
    $("#mobilenum").keyup(function(event) {
    	
    	// 非数字键和退格键输入忽略
    	if(event.keyCode != 8 && (event.keyCode < 48 || event.keyCode >57) ){
    		return;
    	}
        var tel = $('#mobilenum').val();
        if (tel && tel.length == 11) {
            if (moblieExp.test(tel)) {
                $.axJsonpMoblieSegment(tel, findMobileSuccess, findMobileFailure);
            } else {
                findMobileFailure();
            }
        } else {
            // 不显示相关提示
            $('.text-muted').css('display', 'none');
            $('.alert-warning').css('display', 'none');
        }
    });
    
    //test begin
    
    function searchGoods(mobileinfo) {
//        var jsonParam = {
//            "marked": "login",
//            "code": "10002",
//            "version": "1.0",
//            "jsonStr": {}
//        };
    	// carrier格式: "辽宁移动"
    	var carrier = mobileinfo.carrier;
    	
    	var provinceShortName = carrier.substr(0,2);
    	var tpName = carrier.substr(2,2);
    	
        var jsonstr = dataHelper.setJson(null, 'tpName', tpName);
        jsonstr = dataHelper.setJson(jsonstr, 'provinceShortName', provinceShortName);
        //jsonParam.jsonStr = jsonstr;
        //console.log(jsonstr);
        var config;
        var key;
        jQuery.axjsonpPostMvc(resFlowtUrl, jsonstr, searchGoodsSuccess,config,key);
    };
    
    // 查询商品成功后调用
    function searchGoodsSuccess(data){
    	//console.log(data);
    	
    	// 清空商品区域
    	$("#goodsContent").empty();
    	
    	var r = 1;
    	$.each(data,function(k,v){
    		// 解析第一级，goodsName分组
    		//console.log("key : " + k);
    		//console.log("Math.ceil(r/2) : " + Math.ceil(r/2));
    		// 三个radio为一组
    		var group = "<div id='goodsGroup" + Math.ceil(r/3) + "' class='btn-group-lg btn-group-justified'></div>";

    		//第一行
    		if(r % 3 == 1){
        		$("#goodsContent").append(group);          		
    		}
    		    		
    		var radio = "<label class='btn btn-primary'> <input type='radio' name='goodsname' id='" + k + "' value='" + k + "'> " + k + " </label>";
    		// 添加radio
    		$("#goodsGroup"+Math.ceil(r/3)).append(radio);
    		
    		r ++;
    		
    	});

    	// button上添加click方法	
    	$('#goodsContent input[type="radio"]').click(function(){
    		//console.log($(this).val());
    		
    		// 清空购买区域
        	$('#buyTable tbody').empty();
        	
        	var list = data[$(this).val()];
    		$.each(list,function(i,item){
    			// 解析第二级,商品list
    			//console.log(i);
    			//console.log(item);
    			var tr;
    			
    			tr = "<tr>";
    			tr += "<td>";
    			tr += "<h2>"+ item.goodsPrice + "元</h2>";
    			if(item.goodsProvinceId != 0)
    				tr += "<span class='label label-primary'>省内流量</span>";
    			else
    				tr += "<span class='label label-primary'>全国流量</span>";
    			tr += "<small>即时生效，当月有效</small>";
				tr += "</td>";
				tr += "<td class='text-right' style='vertical-align: middle;'>";
				if(item.goodsProvinceId != 0)
					tr += "<button type='button' id='buyLocal' name='buyLocal' class='btn btn-primary'>购买</button>";
				else
					tr += "<button type='button' id='buyTop' name='buyTop' class='btn btn-primary'>购买</button>";
				tr += "</td>";
				tr += "</tr>";
    			
    			// 添加一行购买组件
    			$('#buyTable tbody').append(tr);
    			
    			// 为购买按钮添加事件
    			if(item.goodsProvinceId != 0){
        			$('#buyLocal').click(function(){
        				console.log("本地流量商品 ： [" + item.id + "] - " + item);
        			});    				
    			}
    			else{
        			$('#buyTop').click(function(){
        				console.log("全国流量商品 ： [" + item.id + "] - " + item);
        			});
    				
    			}
    			
    		});        	
    		 
    	}); 
    	// 默认选中第一个
    	$('#goodsContent input[type="radio"]:first').click();
      	
    };

    
});