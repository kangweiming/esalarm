$(document).ready(function() {
	var config;
	
	// 获得当前页面的URL，注意不能直接写 如 http://www.kkkkkk.tk/esalarm/html/test.html，因为微信会自动在增加nsukey参数
	// 请使用下面的方法取得URL 
	var localUrl = location.href.split('#')[0];
	// 无需encodeURIComponent
	//localUrl = encodeURIComponent(localUrl);
	
	// 获取js-sdk需要的数据及签名
    var jsonParam = {
        "marked": "jsticket",
        "code": "40001",
        "version": "1.0",
        "jsonStr": {}
    };	
	// 设置json参数
    var jsonstr = dataHelper.setJson(null, 'url', localUrl);
    jsonParam.jsonStr = jsonstr;
	
	jQuery.axjsonJSTicket(jsonParam,getJSTicketSuccess,config,key);
	
	// 获取jsticket接口调用成功后执行
	function getJSTicketSuccess(data){

		var errcode = data.errcode;
    	var sign = data.data;
    	
    	//console.log("sign.appId : " + sign.appId);
    	//console.log("sign.nonceStr : " + sign.nonceStr);
    	//console.log("sign.timestamp : " + sign.timestamp);
    	//console.log("sign.signature : " + sign.signature);
    	
    	if(errcode == 0){//没有错误
    		//通过config接口注入权限验证配置
    		wx.config({
    		    debug: false,//true的时候可以alert信息 
    		    appId: sign.appId,
    		    timestamp: sign.timestamp,
    		    nonceStr: sign.nonceStr, 
    		    signature: sign.signature,
    		  	//使用接口时，这里必须先声明
    		    jsApiList: [
    		        'checkJsApi',
    		        'onMenuShareTimeline',
    		        'onMenuShareAppMessage',
    		        'onMenuShareQQ',
    		        'onMenuShareWeibo',
    		        'onMenuShareQZone',
    		        'hideMenuItems',
    		        'showMenuItems',
    		        'hideAllNonBaseMenuItem',
    		        'showAllNonBaseMenuItem',
    		        'translateVoice',
    		        'startRecord',
    		        'stopRecord',
    		        'onVoiceRecordEnd',
    		        'playVoice',
    		        'onVoicePlayEnd',
    		        'pauseVoice',
    		        'stopVoice',
    		        'uploadVoice',
    		        'downloadVoice',
    		        'chooseImage',
    		        'previewImage',
    		        'uploadImage',
    		        'downloadImage',
    		        'getNetworkType',
    		        'openLocation',
    		        'getLocation',
    		        'hideOptionMenu',
    		        'showOptionMenu',
    		        'closeWindow',
    		        'scanQRCode',
    		        'chooseWXPay',
    		        'openProductSpecificView',
    		        'addCard',
    		        'chooseCard',
    		        'openCard'
    		      ]  
    		});
    	}		
	};
	
});

wx.error(function (res) {
  alert("wx.error : " + res.errMsg);
});