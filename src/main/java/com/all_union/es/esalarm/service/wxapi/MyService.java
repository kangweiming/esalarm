package com.all_union.es.esalarm.service.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.all_union.es.esalarm.pojo.wxapi.MpAccount;
import com.all_union.es.esalarm.pojo.wxapi.vo.MsgRequest;
import com.all_union.es.esalarm.pojo.wxcms.AccountFans;

/**
 * 微信服务接口，主要用于结合自己的业务和微信接口
 */
public interface MyService {
	
	/**
	 * 消息处理
	 * @param msgRequest
	 * @param mpAccount
	 * @return
	 */
	public String processMsg(MsgRequest msgRequest,MpAccount mpAccount);

	/**
	 * 发布菜单
	 * @param gid
	 * @param mpAccount
	 * @return
	 */
	public JSONObject publishMenu(String gid,MpAccount mpAccount);
	
	/**
	 * 删除菜单
	 * @param mpAccount
	 * @return
	 */
	public JSONObject deleteMenu(MpAccount mpAccount);
	
	//获取用户列表
	public boolean syncAccountFansList(MpAccount mpAccount);
	
	/**
	 * 获取单个用户信息
	 * @param openId
	 * @param mpAccount
	 * @param merge
	 * @return
	 */
	public AccountFans syncAccountFans(String openId, MpAccount mpAccount, boolean merge);
	
	/**
	 * 根据openid 获取粉丝，如果没有，同步粉丝
	 * @param openid
	 * @param mpAccount
	 * @return
	 */
	public AccountFans getFansByOpenId(String openid,MpAccount mpAccount);
	
}



