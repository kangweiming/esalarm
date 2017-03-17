/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import com.all_union.es.esalarm.pojo.flow.FlowOrderGoodsDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午4:31:02 
 * @version V1.0 
 * 
*/
public interface FlowOrderGoodsService {

	int insertSelective(FlowOrderGoodsDo record);
}
