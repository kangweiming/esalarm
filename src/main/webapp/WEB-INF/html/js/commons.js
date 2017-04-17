//--------------------
//var myDomain = "http://localhost:8080";
//var myDomain = "http://www.kkkkkk.tk";
var myDomain = window.location.protocol + "//" + window.location.host;
//var myDomain = document.domain;

// 定义rest接口地址
var resUserUrl = myDomain + "/esalarm/rest/user/";
var resFlowUrl = myDomain + "/esalarm/rest/flow/";
// 获取js-ticket的地址
var resJSTicket = myDomain + "/esalarm/wxapi/jsTicket";

// 定义ajax调用时的全局变量
var key;


// 淘宝查询手机号归属地接口 JSONP GET 参数是手机号码
var restTaobaoMobile = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=";
// 手机号码正则表达式 不支持170
var moblieExp = /^(13|15|18)[0-9]{9}$/;
//var moblieExp =	/^[1][35678][0-9]{9}$/;

$(function() {
	/**
	 * ajax封装
	 * Jsonp格式，用于跨域调用
	 * url 发送请求的地址
	 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
	 * successfn 成功回调函数
	 */
	jQuery.axjsonp = function(url, data, successfn, config) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({

			//提交数据的类型 POST GET
			type: "POST",
			//async:false,  
			//提交的网址
			url: url,
			//提交的数据
			data: data,
			//返回数据的格式
			dataType: "jsonp", //"xml", "html", "script", "json", "jsonp", "text".
			jsonp: "callbackparam", //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
			jsonpCallback: "success_jsonpCallback", //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
			//在请求之前调用的函数
			beforeSend: function() {
				$("#btn_loading").css('display', '');
			},
			//成功返回之后调用的函数             
			success: function(data) {
				if(config != null && config != undefined && config != '') {
					successfn(data, config);
				} else {
					successfn(data);
				}
			},
			//调用执行后调用的函数
			complete: function(XMLHttpRequest, textStatus) {
				$("#btn_loading").css('display', 'none');
			},
			//调用出错执行的函数
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(textStatus);
				//				console.log(textStatus);
				if(XMLHttpRequest.status != '200') {
					alert("AJAX请求错误：请求状态码：" + XMLHttpRequest.status + " readyState:" + XMLHttpRequest.readyState + "错误：" + textStatus);
				}
			}
		});
	};

	jQuery.axjsonpPost = function(url, data, successfn) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({
			crossDomain: true,
			//提交数据的类型 POST GET
			type: "POST",
			//async:false,  
			//提交的网址
			url: url,
			//提交的数据
			data: data,
			//返回数据的格式
			dataType: "json", //"xml", "html", "script", "json", "jsonp", "text".
			//jsonp: "callbackparam", //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
			//jsonpCallback: "success_jsonpCallback", //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
			//在请求之前调用的函数
			beforeSend: function() {
				$("#btn_loading").css('display', '');
			},
			//成功返回之后调用的函数             
			success: function(data) {

				successfn(data);
			},
			//调用执行后调用的函数
			complete: function(XMLHttpRequest, textStatus) {
				$("#btn_loading").css('display', 'none');
			},
			//调用出错执行的函数
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(textStatus);
				//				console.log(textStatus);
				if(XMLHttpRequest.status != '200') {
					alert("AJAX请求错误：请求状态码：" + XMLHttpRequest.status + " readyState:" + XMLHttpRequest.readyState + "错误：" + textStatus);
				}
			}
		});
	};

	// 发送给SpringMvc controller，数据转换成json字符串，controller以String(或JSONObject)类型接收，然后再进行处理
	jQuery.axjsonpPostMvc = function(url, data, successfn, config, key, isAsync) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		$.ajax({
			type: "POST",
			url: url,
			data: JSON.stringify(data),
			//data:data,
			headers: {
				'Content-Type': 'application/json',
				'AuthKey': key
			},
			async: isAsync,
			//在请求之前调用的函数
			beforeSend: function() {
				$("#btn_loading").css('display', '');
			},
			//成功返回之后调用的函数             
			success: function(data) {
				if(config != null && config != undefined && config != '') {
					successfn(data, config);
				} else {
					successfn(data);
				}
			},
			//调用执行后调用的函数
			complete: function(XMLHttpRequest, textStatus) {
				$("#btn_loading").css('display', 'none');
			},
			//调用出错执行的函数
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(textStatus);
				//				console.log(textStatus);
				if(XMLHttpRequest.status != '200') {
					alert("AJAX请求错误：请求状态码：" + XMLHttpRequest.status + " readyState:" + XMLHttpRequest.readyState + "错误：" + textStatus);
				}
			}
		});
	};
	
	// 获取手机号码归属地
    $.axJsonpMoblieSegment = function(tel, successfn, failurefn) {
        $.ajax({
            type: "get",
            url: restTaobaoMobile + tel,
            dataType: "jsonp",
            jsonp: "callback",
            success: function(data) {
                successfn(data);
            },
            error: function() {
                failurefn();
            }
        });
    }
    
    // 获取js-ticket 发送给SpringMvc controller，返回为json对象
    // configUrl:需注入配置信息的URL
	jQuery.axjsonJSTicket = function(data,successfn, config, key) {
		data = (data == null || data == "" || typeof(data) == "undefined") ? {
			"date": new Date().getTime()
		} : data;
		
		$.ajax({
			type: "POST",
			url : resJSTicket,//与微信交互获得jsticket的服务地址
			//data: JSON.stringify({url:configUrl}),
			data: JSON.stringify(data),
			dataType:'json',
			headers: {
				'Content-Type': 'application/json',
				'AuthKey': key
			},
			//成功返回之后调用的函数             
			success: function(data) {
				if(config != null && config != undefined && config != '') {
					successfn(data, config);
				} else {
					successfn(data);
				}
			},
			//调用出错执行的函数
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(textStatus);
				//console.log(textStatus);
				if(XMLHttpRequest.status != '200') {
					alert("AJAX请求错误：请求状态码：" + XMLHttpRequest.status + " readyState:" + XMLHttpRequest.readyState + "错误：" + textStatus);
				}
			}
		});
	};    

});