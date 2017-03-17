/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import com.all_union.es.esalarm.pojo.flow.FlowOrderTransDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午5:58:39 
 * @version V1.0 
 * 
*/
public interface FlowOrderTransService {

	int insertSelective(FlowOrderTransDo record);
}
