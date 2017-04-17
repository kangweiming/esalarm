package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.AccountMenuDao;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenu;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuQuery;
import com.all_union.es.esalarm.service.wxcms.AccountMenuService;


@Service("accountMenuService")
@Transactional
public class AccountMenuServiceImpl implements AccountMenuService{

	private static Logger logger = LogManager.getLogger(AccountMenuServiceImpl.class);
	
	@Autowired
	private AccountMenuDao accountMenuDao;

	public AccountMenu getById(String id){
		logger.debug("excute...");
		return accountMenuDao.getById(id);
	}

	public List<AccountMenu> listForPage(AccountMenu searchEntity){
		logger.debug("excute...");
		return accountMenuDao.listForPage(searchEntity);
	}

	public List<AccountMenu> listParentMenu(){
		logger.debug("excute...");
		return accountMenuDao.listParentMenu();
	}
	
	public void add(AccountMenu entity){
		logger.debug("excute...");
		accountMenuDao.add(entity);
	}

	public void update(AccountMenu entity){
		logger.debug("excute...");
		accountMenuDao.update(entity);
	}

	public void delete(AccountMenu entity){
		logger.debug("excute...");
		accountMenuDao.delete(entity);
	}

	@Override
	public List<AccountMenu> listMenuByQuery(AccountMenuQuery query) {
		logger.debug("excute...");
		
		int count = this.accountMenuDao.countMenuByQuery(query);
		if(count > 0){
			query.setTotalItem(count);
			return this.accountMenuDao.listMenuByQuery(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}
	}

	@Override
	public int countMenuByQuery(AccountMenuQuery query) {
		logger.debug("excute...");
		return this.accountMenuDao.countMenuByQuery(query);
	}

	@Override
	public List<AccountMenu> listParentMenuByGid(String gid) {
		logger.debug("excute...");
		return this.accountMenuDao.listParentMenuByGid(gid);
	}

	@Override
	public void updateByPrimaryKeySelective(AccountMenu menu) {
		logger.debug("excute...");
		this.accountMenuDao.updateByPrimaryKeySelective(menu);
	}



}