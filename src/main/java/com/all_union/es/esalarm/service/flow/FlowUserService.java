/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import com.all_union.es.esalarm.pojo.flow.FlowUserDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午2:31:15 
 * @version V1.0 
 * 
*/
public interface FlowUserService {
	
    int deleteByOpenId(String openId);

    int insert(FlowUserDo record);

    int insertSelective(FlowUserDo record);

    FlowUserDo selectByOpenId(String openId);

    int updateByOpenIdSelective(FlowUserDo record);

    int updateByOpenId(FlowUserDo record);

}
