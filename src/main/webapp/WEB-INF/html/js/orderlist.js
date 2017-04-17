$(document).ready(function() {

	
	// 当前页
	var currentPage = 1;
	// 每页记录数
	var pageSize = 10;
	// 记录开始的游标 mysql从0开始
	var idx = 0;
	// 是否已经读到最后一条记录
	var isEnd = false;
	
	// 页面加载完马上执行
	listRechargeRecord(true,idx,pageSize);
	 
	/**
	 * 查询充值记录
	 * @param isClear 是否清空显示区域，true为清空
	 * @param idx 记录开始游标
	 * @param pageSize 每次查询显示的记录数
	 * @returns
	 */
	function listRechargeRecord(isClear,idx,pageSize){
		
		if(isEnd)
			return;
		// 将json数组及其他参数放入json参数中
		//var jsonstr = dataHelper.setJson(null, 'open_id', '1234567890AABBCCDD1234567890');
		var jsonstr = dataHelper.setJson(null, 'idx', idx);
		jsonstr = dataHelper.setJson(jsonstr, 'pageSize', pageSize);
		//console.log("jsonstr: " + jsonstr);
		
		//参数体
		var jsonParam = {
			"marked": "floworder",
			"code": "20003",
			"version": "1.0",
			"jsonStr": {}
		};
		// 组合成最终的参数结构
		jsonParam.jsonStr = jsonstr;
		
		//console.log("jsonParam : " + jsonParam);   
		
		var config = dataHelper.setJson(null, 'isClear', isClear);
		
        // resFlowUrl key 请查看commons.js中的定义
        jQuery.axjsonpPostMvc(resFlowUrl+'/order/record/', jsonParam, listRechargeRecordSuccess,config,key,true);
		
	};
	
	// 充值记录查询成功回调
	function listRechargeRecordSuccess(data,config){
		
		// 将config参数具象化为JSON对象
		config = JSON.parse(config);
		
		// 清空记录
		if(config.isClear == true)
			$('#RechargeRecord tbody').empty();
		
		if(null == data){
			console.log("list recharge record failed");
			$("#loadMore").text("没有充值记录了");
			isEnd = true;
			return;
		}
		console.log("list recharge record 。。。");
				
		// 写入记录		
    	$.each(data,function(i,item){
    		
    		var tr;
    		if(item.state == 'SUCCESS')
    			tr += "<tr class='success'>";
    		else
    			tr += "<tr class='warning'>";
    		
    		tr += "<td>";
    		tr += item.mobile;
    		tr += "</td>";

    		tr += "<td>";
    		tr += item.goodsNames;
    		tr += "</td>";    		
    		
    		tr += "<td>";
    		//tr += getMyDate(item.gmtUpdate);
    		tr += item.gmtUpdate;
    		tr += "</td>";    		

    		tr += "<td>";
    		tr += item.stateDesc;
    		tr += "</td>";    		

    		tr += "</tr>";
    		// 添加一行记录
    		$('#RechargeRecord tbody').append(tr);
    		
    	});
	};	
	
	//获得年月日      得到日期oTime  
    function getMyDate(str){  
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oHour = oDate.getHours(),  
        oMin = oDate.getMinutes(),  
        oSen = oDate.getSeconds(),  
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
        return oTime;  
    };  
    //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    } 	
	
	//-------------------------------------------------------
	// 滚动事件
	$(window).scroll(function() {
		
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		
		if (scrollTop + windowHeight == scrollHeight) {

			// 此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
			currentPage ++;
			idx = pageSize * (currentPage - 1);
			
			// 添加数据
			listRechargeRecord(false,idx,pageSize);
		}
	});
    
	//-------------------------------------------------------
    // 点击加载更多
    $("#loadMore").click(function(){
    	
		currentPage ++;
		idx = pageSize * (currentPage - 1);
		
		// 添加数据
		listRechargeRecord(false,idx,pageSize);
		
    });

});