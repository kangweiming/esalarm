/**
 * 
 */
package com.all_union.es.esalarm.pojo.wxcms;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 图文消息查询对象
 * @author kwm
 * @date 2017年4月1日 下午3:24:28 
 * @version V1.0 
 * 
*/
public class MsgNewsQuery extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1684004845868130223L;
	/**
	 * 基本图文消息对象
	 */
	private MsgNews baseDo;
	
	public MsgNews getBaseDo() {
		return baseDo;
	}
	public void setBaseDo(MsgNews baseDo) {
		this.baseDo = baseDo;
	}

}
