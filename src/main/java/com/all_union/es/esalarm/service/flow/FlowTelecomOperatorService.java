/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import java.util.List;

import com.all_union.es.esalarm.pojo.flow.FlowTelecomOperatorDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月19日 下午6:43:09 
 * @version V1.0 
 * 
*/
public interface FlowTelecomOperatorService {

	/**
     * 查询所有记录
     * @return
     */
    List<FlowTelecomOperatorDo> listAll();
}
