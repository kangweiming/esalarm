wx.ready(function () {
	  // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
	  $('#checkJsApi').click(function(){
	    wx.checkJsApi({
		      jsApiList: [
		        'getNetworkType',
		        'previewImage',
		        'chooseWXPay'
		      ],
		      success: function (res) {
		        alert(JSON.stringify(res));
		      }
		});		  
	  }); 
	  // 2.分享接口
	  var shareTitle = "微信JS-SDK测试";
	  var shareDesc = "这是测试用的描述信息";
	  var shareLink = myDomain + "/esalarm/html/test.html";
	  // 2.1分享给朋友
	  $('#onMenuShareAppMessage').click(function(){
	    wx.onMenuShareAppMessage({
	        title: shareTitle,
	        desc: shareDesc,
	        link: shareLink,
	        imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',
	        type: '', // 分享类型,music、video或link，不填默认为link
	        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空        
	        trigger: function (res) {
	          // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
	          alert('用户点击发送给朋友');
	        },
	        success: function (res) {
	          alert('已分享');
	        },
	        cancel: function (res) {
	          alert('已取消');
	        },
	        fail: function (res) {
	          alert(JSON.stringify(res));
	        }
	      });
	      alert('已注册获取“发送给朋友”状态事件');		  
		}); 
	  	// 3.界面操作接口
	  	// 3.1隐藏右上角菜单
	  $('#hideOptionMenu').click(function(){
		  wx.hideOptionMenu();
	  });
		// 3.2显示右上角菜单
	  $('#showOptionMenu').click(function(){
		  wx.showOptionMenu();
	  });
	  
	  	
});