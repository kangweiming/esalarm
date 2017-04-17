package com.all_union.es.esalarm.service.impl.wxcms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.wxcms.AccountMenuGroupDao;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroup;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroupQuery;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;
import com.all_union.es.esalarm.service.wxcms.AccountMenuGroupService;


@Service("accountMenuGroupService")
@Transactional
public class AccountMenuGroupServiceImpl implements AccountMenuGroupService{

	private static Logger logger = LogManager.getLogger(AccountMenuGroupServiceImpl.class);
	
	@Autowired
	private AccountMenuGroupDao accountMenuGroupDao;

	public AccountMenuGroup getById(String id){
		logger.debug("excute...");
		return accountMenuGroupDao.getById(id);
	}

	public List<AccountMenuGroup> list(AccountMenuGroup searchEntity){
		logger.debug("excute...");
		return accountMenuGroupDao.list(searchEntity);
	}

	public Pagination<AccountMenuGroup> paginationEntity(AccountMenuGroup searchEntity ,Pagination<AccountMenuGroup> pagination){
		logger.debug("excute...");
		Integer totalItemsCount = accountMenuGroupDao.getTotalItemsCount(searchEntity);
		List<AccountMenuGroup> items = accountMenuGroupDao.paginationEntity(searchEntity,pagination);
		pagination.setTotalItemsCount(totalItemsCount);
		pagination.setItems(items);
		return pagination;
	}

	public void add(AccountMenuGroup entity){
		logger.debug("excute...");
		accountMenuGroupDao.add(entity);
	}

	public void update(AccountMenuGroup entity){
		logger.debug("excute...");
		accountMenuGroupDao.update(entity);
	}

	public void delete(AccountMenuGroup entity){
		logger.debug("excute...");
		accountMenuGroupDao.deleteAllMenu(entity);
		accountMenuGroupDao.delete(entity);
	}

	@Override
	public List<AccountMenuGroup> listMenuGroupByQuery(AccountMenuGroupQuery query) {
		logger.debug("excute...");
		
		int count = this.accountMenuGroupDao.countMenuGroupByQuery(query);
		
		if(count > 0){
			query.setTotalItem(count);
			return this.accountMenuGroupDao.listMenuGroupByQuery(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}		
	}

	@Override
	public int countMenuGroupByQuery(AccountMenuGroupQuery query) {
		logger.debug("excute...");
		return this.accountMenuGroupDao.countMenuGroupByQuery(query);
	}



}

