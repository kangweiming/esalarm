/**
 * 
 */
package com.all_union.es.esalarm.pojo.wxcms;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 菜单查询对象
 * @author kwm
 * @date 2017年4月6日 下午3:11:53 
 * @version V1.0 
 * 
*/
public class AccountMenuQuery extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5725395609887286440L;
	/**
	 * 基本菜单对象
	 */
	private AccountMenu baseDo;
	public AccountMenu getBaseDo() {
		return baseDo;
	}
	public void setBaseDo(AccountMenu baseDo) {
		this.baseDo = baseDo;
	}
	
	

}
