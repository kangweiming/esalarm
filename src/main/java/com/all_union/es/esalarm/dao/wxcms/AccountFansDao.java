package com.all_union.es.esalarm.dao.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.AccountFans;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;


public interface AccountFansDao {

	public AccountFans getById(String id);

	public AccountFans getByOpenId(String openId);
	
	public List<AccountFans> list(AccountFans searchEntity);

	public Integer getTotalItemsCount(AccountFans searchEntity);
	
	public List<AccountFans> paginationEntity(AccountFans searchEntity ,Pagination<AccountFans> pagination);

	public AccountFans getLastOpenId();
	
	public void add(AccountFans entity);
	
	public void addList(List<AccountFans> list);

	public void update(AccountFans entity);

	public void delete(AccountFans entity);

	public void deleteByOpenId(String openId);

}