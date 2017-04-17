package com.all_union.es.esalarm.service.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsQuery;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsVO;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;


public interface MsgNewsService {

	public MsgNews getById(String id);

	public List<MsgNews> listForPage(MsgNews searchEntity);
	
	public List<MsgNewsVO> pageWebNewsList(MsgNews searchEntity,Pagination<MsgNews> page);

	public void add(MsgNews entity);

	public void update(MsgNews entity);

	public void delete(MsgNews entity);

	//根据用户发送的文本消息，随机获取 num 条文本消息
	public List<MsgNews> getRandomMsg(String inputcode,Integer num);
	
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