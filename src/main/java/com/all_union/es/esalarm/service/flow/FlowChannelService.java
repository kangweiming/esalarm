/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import java.util.List;

import com.all_union.es.esalarm.pojo.flow.FlowChannelDo;
import com.all_union.es.esalarm.pojo.flow.FlowChannelQuery;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月21日 下午1:30:14 
 * @version V1.0 
 * 
*/
public interface FlowChannelService {
    /**
     * 根据query条件查询记录
     * @param query
     * @return
     */
    List<FlowChannelDo> listChannelByQuery(FlowChannelQuery query);
    /**
     * 根据query条件统计记录总数
     * @param query
     * @return
     */
    int countChannelByQuery(FlowChannelQuery query);
}
