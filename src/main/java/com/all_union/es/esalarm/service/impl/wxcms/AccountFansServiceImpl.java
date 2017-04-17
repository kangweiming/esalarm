package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.AccountFansDao;
import com.all_union.es.esalarm.pojo.wxcms.AccountFans;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;
import com.all_union.es.esalarm.service.wxcms.AccountFansService;


@Service("accountFansService")
@Transactional
public class AccountFansServiceImpl implements AccountFansService{

	private static Logger logger = LogManager.getLogger(AccountFansServiceImpl.class);
	@Autowired
	private AccountFansDao entityDao;

	public AccountFans getById(String id){
		logger.debug("excute...");
		return entityDao.getById(id);
	}

	public AccountFans getByOpenId(String openId){
		logger.debug("excute...");
		return entityDao.getByOpenId(openId);
	}
	
	public List<AccountFans> list(AccountFans searchEntity){
		logger.debug("excute...");
		return entityDao.list(searchEntity);
	}

	public Pagination<AccountFans> paginationEntity(AccountFans searchEntity,Pagination<AccountFans> pagination){
		logger.debug("excute...");
		Integer totalItemsCount = entityDao.getTotalItemsCount(searchEntity);
		List<AccountFans> items = entityDao.paginationEntity(searchEntity,pagination);
		pagination.setTotalItemsCount(totalItemsCount);
		pagination.setItems(items);
		return pagination;
	}
	
	public AccountFans getLastOpenId(){
		logger.debug("excute...");
		return entityDao.getLastOpenId();
	}
	
	public void sync(AccountFans searchEntity){
		logger.debug("excute...");
		AccountFans lastFans = entityDao.getLastOpenId();
		String lastOpenId = "";
		if(lastFans != null){
			lastOpenId = lastFans.getOpenId();
		}		
	}

	public void add(AccountFans entity){
		logger.debug("excute...");
		entityDao.add(entity);
	}

	public void update(AccountFans entity){
		logger.debug("excute...");
		entityDao.update(entity);
	}

	public void delete(AccountFans entity){
		logger.debug("excute...");
		entityDao.delete(entity);
	}

	public void deleteByOpenId(String openId){
		logger.debug("excute...");
		entityDao.deleteByOpenId(openId);
	}

}