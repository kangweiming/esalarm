package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.MsgBaseDao;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.service.wxcms.MsgBaseService;


@Service("msgBaseService")
@Transactional
public class MsgBaseServiceImpl implements MsgBaseService{

	@Autowired
	private MsgBaseDao entityDao;

	public MsgBase getById(String id){
		return entityDao.getById(id);
	}

	public List<MsgBase> listForPage(MsgBase searchEntity){
		return entityDao.listForPage(searchEntity);
	}

	public void add(MsgBase entity){
		entityDao.add(entity);
	}

	public void update(MsgBase entity){
		entityDao.update(entity);
	}

	public void delete(MsgBase entity){
		entityDao.delete(entity);
	}



}