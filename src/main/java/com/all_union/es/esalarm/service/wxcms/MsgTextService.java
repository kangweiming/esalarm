package com.all_union.es.esalarm.service.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.pojo.wxcms.MsgTextQuery;

public interface MsgTextService {

	public MsgText getById(String id);

	public List<MsgText> listForPage(MsgText searchEntity);

	public void add(MsgText entity);

	public void update(MsgText entity);

	public void delete(MsgText entity);
	
	//根据用户发送的文本消息，随机获取一条文本消息
	public MsgText getRandomMsg(String inputcode);
	public MsgText getRandomMsg2();
	
	/**
	 * 根据条件查询文本消息
	 * @param query
	 * @return
	 */
	public List<MsgText> listMsgTextByQuery(MsgTextQuery query);
	
	/**
	 * 统计符合条件的记录总数
	 * @param query
	 * @return
	 */
	public Integer countMsgTextByQuery(MsgTextQuery query);	
	
}
