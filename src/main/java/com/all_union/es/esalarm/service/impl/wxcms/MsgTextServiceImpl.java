package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.MsgBaseDao;
import com.all_union.es.esalarm.dao.wxcms.MsgTextDao;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.pojo.wxcms.MsgTextQuery;
import com.all_union.es.esalarm.pojo.wxcms.MsgType;
import com.all_union.es.esalarm.service.wxcms.MsgTextService;


@Service("msgTextService")
@Transactional
public class MsgTextServiceImpl implements MsgTextService{

	private static Logger logger = LogManager.getLogger(MsgTextServiceImpl.class);
	
	@Autowired
	private MsgTextDao msgTextDao;
	
	@Autowired
	private MsgBaseDao baseDao;

	public MsgText getById(String id){
		logger.debug("excute...");
		return msgTextDao.getById(id);
	}

	public List<MsgText> listForPage(MsgText searchEntity){
		logger.debug("excute...");
		return msgTextDao.listForPage(searchEntity);
	}

	public void add(MsgText entity){
		logger.debug("excute...");
		MsgBase base = new MsgBase();
		base.setInputcode(entity.getInputcode());
		base.setCreatetime(new Date());
		base.setMsgtype(MsgType.Text.toString());
		baseDao.add(base);
		
		entity.setBaseId(base.getId());
		msgTextDao.add(entity);
	}

	public void update(MsgText entity){
		logger.debug("excute...");
		MsgBase base = baseDao.getById(entity.getBaseId().toString());
		base.setInputcode(entity.getInputcode());
		baseDao.updateInputcode(base);
		msgTextDao.update(entity);
	}

	public void delete(MsgText entity){
		logger.debug("excute...");
		MsgBase base = new MsgBase();
		base.setId(entity.getBaseId());
		msgTextDao.delete(entity);
		baseDao.delete(base);
	}

	//根据用户发送的文本消息，随机获取一条文本消息
	public MsgText getRandomMsg(String inputCode){
		logger.debug("excute...");
		return msgTextDao.getRandomMsg(inputCode);
	}
	public MsgText getRandomMsg2(){
		logger.debug("excute...");
		return msgTextDao.getRandomMsg2();
	}

	@Override
	public List<MsgText> listMsgTextByQuery(MsgTextQuery query) {
		logger.debug("excute...");
		
		int count = this.msgTextDao.countMsgTextByQuery(query);
		
		if(count > 0){
			query.setTotalItem(count);
			return this.msgTextDao.listMsgTextByQuery(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}		
	}

	@Override
	public Integer countMsgTextByQuery(MsgTextQuery query) {
		logger.debug("excute...");
		return this.msgTextDao.countMsgTextByQuery(query);
	}
}

