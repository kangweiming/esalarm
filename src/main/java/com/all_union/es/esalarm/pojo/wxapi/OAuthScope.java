package com.all_union.es.esalarm.pojo.wxapi;

/**
 * 消息类型
 * 
 */

public enum OAuthScope {

	Base("snsapi_base"),//用户openid
	Userinfo("snsapi_userinfo");//用户信息
	
	private String name;
	
	private OAuthScope(String name) {
	     this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
