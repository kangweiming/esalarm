/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import java.util.List;
import java.util.Map;

import com.all_union.es.esalarm.pojo.flow.FlowOrderDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDoKey;
import com.all_union.es.esalarm.pojo.flow.FlowRechargeRecordDo;

/** 
 * @Description: 流量订单处理服务
 * @author kwm
 * @date 2017年3月15日 下午1:35:55 
 * @version V1.0 
 * 
*/
public interface FlowOrderService {

    int insertSelective(FlowOrderDo record);

    FlowOrderDo selectByOrderNo(FlowOrderDoKey key);
    
    int updateByOrderNoSelective(FlowOrderDo record);
    
    /**
     * 根据open_id查询流量充值记录
     * 充值记录中带商品信息
     * @param map 参数信息，至少要包含一个open_id;其他两个是idx 和 pageSize
     * @return
     */
    List<FlowRechargeRecordDo> listOrderByOpenIdWithGoodsInfo(Map<String,Object> map);

}
