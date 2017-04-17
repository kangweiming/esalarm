package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.MsgBaseDao;
import com.all_union.es.esalarm.dao.wxcms.MsgNewsDao;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsQuery;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsVO;
import com.all_union.es.esalarm.pojo.wxcms.MsgType;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;
import com.all_union.es.esalarm.service.wxcms.MsgNewsService;
import com.kwm.common.lang.StringUtil;


@Service("msgNewsService")
@Transactional
public class MsgNewsServiceImpl implements MsgNewsService{

	private static Logger logger = LogManager.getLogger(MsgNewsServiceImpl.class);
	
	@Autowired
	private MsgBaseDao baseDao;
	
	@Autowired
	private MsgNewsDao entityDao;

	public MsgNews getById(String id){
		logger.debug("excute...");
		return entityDao.getById(id);
	}

	public List<MsgNews> listForPage(MsgNews searchEntity){
		logger.debug("excute...");
		return entityDao.listForPage(searchEntity);
	}
	
	public List<MsgNewsVO> pageWebNewsList(MsgNews searchEntity,Pagination<MsgNews> page){
		logger.debug("excute...");
		List<MsgNews> list = entityDao.pageWebNewsList(searchEntity,page);
		List<MsgNewsVO> pageList = new ArrayList<MsgNewsVO>();
		for(MsgNews msg : list){
			if(pageList.size() == 0){
				MsgNewsVO vo = new MsgNewsVO();
				vo.setCreateTimeStr(msg.getCreateTimeStr());
				vo.getMsgNewsList().add(msg);
				pageList.add(vo);
			}else{
				MsgNewsVO tmpMsgNewsVO = pageList.get(pageList.size() - 1);
				if(tmpMsgNewsVO.getCreateTimeStr().equals(msg.getCreateTimeStr())){
					tmpMsgNewsVO.getMsgNewsList().add(msg);
				}else{
					MsgNewsVO vo = new MsgNewsVO();
					vo.setCreateTimeStr(msg.getCreateTimeStr());
					vo.getMsgNewsList().add(msg);
					pageList.add(vo);
				}
			}
		}
		return pageList;
	}

	
	public void add(MsgNews entity){
		logger.debug("excute...");
		MsgBase base = new MsgBase();
		base.setInputcode(entity.getInputcode());
		base.setCreatetime(new Date());
		base.setMsgtype(MsgType.News.toString());
		baseDao.add(base);
		
		entity.setBaseId(base.getId());
		entityDao.add(entity);
		
		if(StringUtil.isBlank(entity.getFromurl())){
			entity.setUrl(entity.getUrl()+"?id="+entity.getId());
		}else{
			entity.setUrl("");
		}
		
		entityDao.updateUrl(entity);
	}

	public void update(MsgNews entity){
		logger.debug("excute...");
		MsgBase base = baseDao.getById(entity.getBaseId().toString());
		base.setInputcode(entity.getInputcode());
		baseDao.updateInputcode(base);
		
		if(StringUtil.isBlank(entity.getFromurl())){
			entity.setUrl(entity.getUrl()+"?id="+entity.getId());
		}else{
			entity.setUrl("");
		}
		
		entityDao.update(entity);
	}

	public void delete(MsgNews entity){
		logger.debug("excute...");
		MsgBase base = new MsgBase();
		base.setId(entity.getBaseId());
		entityDao.delete(entity);
		baseDao.delete(base);
	}

	public List<MsgNews> getRandomMsg(String inputCode,Integer num){
		logger.debug("excute...");
		return entityDao.getRandomMsgByContent(inputCode,num);
	}

	@Override
	public List<MsgNews> listMsgNewsByQuery(MsgNewsQuery query) {
		logger.debug("excute...");
		
		int count = this.entityDao.countMsgNewsByQuery(query);
		
		if(count > 0){
			query.setTotalItem(count);
			return this.entityDao.listMsgNewsByQuery(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}
		
	}

	@Override
	public Integer countMsgNewsByQuery(MsgNewsQuery query) {
		logger.debug("excute...");
		return this.entityDao.countMsgNewsByQuery(query);
	}

}
