package com.all_union.es.esalarm.service.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroup;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroupQuery;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;


public interface AccountMenuGroupService {

	public AccountMenuGroup getById(String id);

	public List<AccountMenuGroup> list(AccountMenuGroup searchEntity);

	public Pagination<AccountMenuGroup> paginationEntity(AccountMenuGroup searchEntity ,Pagination<AccountMenuGroup> pagination);

	public void add(AccountMenuGroup entity);

	public void update(AccountMenuGroup entity);

	public void delete(AccountMenuGroup entity);
	
	/**
	 * 查询符合条件的菜单组
	 * @param query
	 * @return
	 */
	public List<AccountMenuGroup> listMenuGroupByQuery(AccountMenuGroupQuery query);
	/**
	 * 统计符合条件的菜单组数量
	 * @param query
	 * @return
	 */
	public int countMenuGroupByQuery(AccountMenuGroupQuery query);	



}

