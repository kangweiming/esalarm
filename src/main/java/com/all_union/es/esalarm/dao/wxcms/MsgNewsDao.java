package com.all_union.es.esalarm.dao.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsQuery;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;


public interface MsgNewsDao {

	public MsgNews getById(String id);

	public List<MsgNews> listForPage(MsgNews searchEntity);
	
	public List<MsgNews> pageWebNewsList(MsgNews searchEntity,Pagination<MsgNews> page);

	public void add(MsgNews entity);

	public void update(MsgNews entity);
	
	public void updateUrl(MsgNews entity);

	public void delete(MsgNews entity);

	public List<MsgNews> getRandomMsg(Integer num);

	public List<MsgNews> getRandomMsgByContent(String inputcode ,Integer num);
	
	public List<MsgNews> getMsgNewsByIds(String[] array);
	/**
	 * 根据条件查询图文消息
	 * @param query
	 * @return
	 */
	public List<MsgNews> listMsgNewsByQuery(MsgNewsQuery query);
	
	/**
	 * 统计符合条件的图文消息总数
	 * @param query
	 * @return
	 */
	public Integer countMsgNewsByQuery(MsgNewsQuery query);

}