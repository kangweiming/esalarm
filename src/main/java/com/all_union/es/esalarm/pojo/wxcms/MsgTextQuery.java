/**
 * 
 */
package com.all_union.es.esalarm.pojo.wxcms;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 文本消息查询对象
 * @author kwm
 * @date 2017年3月30日 下午3:03:05 
 * @version V1.0 
 * 
*/
public class MsgTextQuery extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4392356834971835752L;
	
	/**
	 * 基本文本消息对象
	 */
	private MsgText baseDo;

	public MsgText getBaseDo() {
		return baseDo;
	}

	public void setBaseDo(MsgText baseDo) {
		this.baseDo = baseDo;
	}
	
	
	
}
