package com.all_union.es.esalarm.dao.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.pojo.wxcms.MsgTextQuery;


public interface MsgTextDao {

	public MsgText getById(String id);

	public List<MsgText> listForPage(MsgText searchEntity);

	public void add(MsgText entity);

	public void update(MsgText entity);

	public void delete(MsgText entity);

	public MsgText getRandomMsg(String inputCode);
	
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