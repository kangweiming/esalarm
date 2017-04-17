package com.all_union.es.esalarm.dao.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.Account;


public interface AccountDao {

	public Account getById(String id);
	
	public Account getByAccount(String account);
	
	public Account getSingleAccount();

	public List<Account> listForPage(Account searchEntity);

	public void add(Account entity);

	public void update(Account entity);

	public void delete(Account entity);



}