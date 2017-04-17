

// 常用工具方法
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

// 是否是数字和字母组合
function isWordandNumber( s ){   
	  var regu = "^[0-9a-zA-Z]+$";
	  var re = new RegExp(regu);
	  if (s.search(re) != -1) {
	       return true;
	  } else {
	       return false;
	  }
}

/**
 * RF工具类
 */
RfUtil = function() {};
RfUtil.prototype = {
	//判断是否为空,如果为空返回true，否则返回false
	isEmpty : function(text){
		if(text == undefined || text == null || text == '' || text == 'null' || text == 'undefined'){
			return true;
		}else{
			text = text.replace(/(\s*$)/g, '');
			if(text == ''){
				return true;
			}
		}
		return false;
	},
	//英文、数字正则表达式，验证通过返回 true；
	numValid : function(text){
		var patten = new RegExp(/^[0-9]+$/);
		return patten.test(text);
	},
	//英文、数字正则表达式
	enNumValid : function(text){
		var patten = new RegExp(/^[a-zA-Z0-9]+$/);
		return patten.test(text);
	},
	//英文、数字、-、_验证
	cValid : function(text){
		//var patten = new RegExp(/^[a-zA-Z][\w-_]{3,19}$/);
		var patten = new RegExp(/^[a-zA-Z\w-_]{3,19}$/);
		return patten.test(text);
	},
	//中文、英文、数字、-、_验证
	zcValid : function(text){
		var patten = RegExp(/^[\u4E00-\u9FA5A-Za-z0-9_-]+$/);
		return patten.test(text);
	},
	//email
	emailValid : function(text){
		var patten = RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
		return patten.test(text);
	},
	//微信
	wx_hide_footbar : function(){
		try{
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
				WeixinJSBridge.call('hideToolbar');
			});
		}catch(e){}
	},
};
rf = new RfUtil();

