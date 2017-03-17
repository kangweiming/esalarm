$(document).ready(function() {
	
	//页面加载完马上执行
	test("do it");
	
	function test(msg){
		console.log("msg : " + msg);
	};
	
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
    
    // 全局变量
    var tel;
    //键盘输入查询
    $("#mobilenum").keyup(function(event) {
    	
    	// 非数字键和退格键输入忽略
    	if(event.keyCode != 8 && (event.keyCode < 48 || event.keyCode >57) ){
    		return;
    	}
        tel = $('#mobilenum').val();
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
    
    // 根据手机号查询商品信息
    function searchGoods(mobileinfo) {
        var jsonParam = {
            "marked": "flowgoods",
            "code": "10001",
            "version": "1.0",
            "jsonStr": {}
        };
        
    	// carrier格式: "辽宁移动"
    	var carrier = mobileinfo.carrier;
    	
    	var provinceShortName = carrier.substr(0,2);
    	var tpName = carrier.substr(2,2);
    	
    	// 设置json参数
        var jsonstr = dataHelper.setJson(null, 'tpName', tpName);
        jsonstr = dataHelper.setJson(jsonstr, 'provinceShortName', provinceShortName);
        jsonParam.jsonStr = jsonstr;
//        console.log("jsonstr: " + jsonstr);
//        console.log("jsonParam : " + jsonParam);
        
		var config;
        // resFlowUrl key 请查看commons.js中的定义
        jQuery.axjsonpPostMvc(resFlowUrl+'/goods/', jsonParam, searchGoodsSuccess,config,key,true);
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
        				
        				// 执行购买方法
        				buyFlow(item,tel);
        			});    				
    			}
    			else{
        			$('#buyTop').click(function(){
        				console.log("全国流量商品 ： [" + item.id + "] - " + item);

        				// 执行购买方法
        				buyFlow(item,tel);
        			});
    				
    			}
    			
    		});        	
    		 
    	}); 
    	// 默认选中第一个
    	$('#goodsContent input[type="radio"]:first').click();
    	
    	// 购买方法
    	function buyFlow(goods,tel){
			// 1.创建订单 同步
    		createOrder(goods,tel);
    		
			// 2.调用公众号支付
			console.log("调用支付");
			alert("调用支付");
			alert("支付成功");
			
			// 3.更新订单状态到已支付 同步
			updateOrder(order);
			
			// 4.跳转到支付成功页面-充值记录
    		
    	};
    	
    	// 全局变量 用于保存订单信息
    	var order;
    	
    	// 更新订单状态
    	function updateOrder(order){
    		
    		// 将json数组及其他参数放入json参数中
    		var jsonstr = dataHelper.setJson(null, 'orderNo', order.orderNoStr);
    		jsonstr = dataHelper.setJson(jsonstr, 'open_id', '1234567890AABBCCDD1234567890');
    		
    		//console.log("jsonstr: " + jsonstr);
    		
    		//参数体
			var jsonParam = {
				"marked": "floworder",
				"code": "20002",
				"version": "1.0",
				"jsonStr": {}
			};
			// 组合成最终的参数结构
			jsonParam.jsonStr = jsonstr;
			
			//console.log("jsonParam : " + jsonParam);   
			var config;
	        // resFlowUrl key 请查看commons.js中的定义
	        jQuery.axjsonpPostMvc(resFlowUrl+'/order/update/', jsonParam, updateOrderSuccess,config,key,false);
			
    	};
    	
    	// 订单更新成功回调
    	function updateOrderSuccess(data){
    		if(null == data){
    			console.log("update order failed");
    			return;
    		}
    		console.log("update order success");
    		// 将订单信息赋值给全局变量
    		order = data;
    		//console.log("order.orderNoStr : " + order.orderNoStr +" order.stateDesc :" + order.stateDesc);
    	};
    	
    	// 创建订单
    	function createOrder(goods,tel){
    		
    		// 商品信息
    		var goodsinfo = dataHelper.setJson(null, 'goods', goods);
    		// 商品数量 固定为1
    		goodsinfo = dataHelper.setJson(goodsinfo, 'amount', 1);    		
    		// 商品折扣 扩展：可以单独查询 商品id-折扣率对照表 这里固定为1.0
    		goodsinfo = dataHelper.setJson(goodsinfo, 'discount', 1.0);
    		
    		// 创建json数组并赋值
    		var goodslist = []; 
    		goodslist.push(goodsinfo);
    		
    		//console.log(goodslist);
    		
    		// 将json数组及其他参数放入json参数中
    		var jsonstr = dataHelper.setJson(null, 'open_id', '1234567890AABBCCDD1234567890');
    		jsonstr = dataHelper.setJson(jsonstr, 'goodslist', goodslist);
    		jsonstr = dataHelper.setJson(jsonstr, 'mobile', tel);
    		
    		//console.log("jsonstr: " + jsonstr);
    		
    		//参数体
			var jsonParam = {
				"marked": "floworder",
				"code": "20001",
				"version": "1.0",
				"jsonStr": {}
			};
			// 组合成最终的参数结构
			jsonParam.jsonStr = jsonstr;
			
			//console.log("jsonParam : " + jsonParam);
			
			var config;
	        // resFlowUrl key 请查看commons.js中的定义
	        jQuery.axjsonpPostMvc(resFlowUrl+'/order/create/', jsonParam, createOrderSuccess,config,key,false);
			   		
    	};
    	
    	// 订单创建成功回调
    	function createOrderSuccess(data){
    		if(null == data){
    			console.log("create order failed");
    			return;
    		}
    		console.log("create order success");
    		// 将订单信息赋值给全局变量
    		order = data;
    		//console.log("order.orderNoStr : " + order.orderNoStr +" order.stateDesc :" + order.stateDesc);
    	};
      	
    };

    
});