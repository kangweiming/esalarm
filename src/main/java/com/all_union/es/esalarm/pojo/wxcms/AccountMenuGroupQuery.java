/**
 * 
 */
package com.all_union.es.esalarm.pojo.wxcms;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 菜单组查询对象
 * @author kwm
 * @date 2017年4月6日 上午11:27:51 
 * @version V1.0 
 * 
*/
public class AccountMenuGroupQuery extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4880250125007456130L;
	/**
	 * 基本菜单组对象
	 */
	private AccountMenuGroup baseDo;

	public AccountMenuGroup getBaseDo() {
		return baseDo;
	}

	public void setBaseDo(AccountMenuGroup baseDo) {
		this.baseDo = baseDo;
	}
	

}
