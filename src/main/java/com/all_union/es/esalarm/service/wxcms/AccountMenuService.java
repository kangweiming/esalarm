package com.all_union.es.esalarm.service.wxcms;

import java.util.List;

import com.all_union.es.esalarm.pojo.wxcms.AccountMenu;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuQuery;


public interface AccountMenuService {

	public AccountMenu getById(String id);

	public List<AccountMenu> listForPage(AccountMenu searchEntity);

	public List<AccountMenu> listParentMenu();
	
	public void add(AccountMenu entity);

	public void update(AccountMenu entity);

	public void delete(AccountMenu entity);
	
	/**
	 * 根据id更新AccountMenu全字段
	 * @param menu
	 */
	public void updateByPrimaryKeySelective(AccountMenu menu);
	/**
	 * 查询某个菜单组下的所有一级菜单
	 * @param gid
	 * @return
	 */
	public List<AccountMenu> listParentMenuByGid(String gid);	
	/**
	 * 根据条件查询菜单
	 * @param query
	 * @return
	 */
	public List<AccountMenu> listMenuByQuery(AccountMenuQuery query);
	/**
	 * 根据条件统计记录总数
	 * @param query
	 * @return
	 */
	public int countMenuByQuery(AccountMenuQuery query);

}