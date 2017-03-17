package com.all_union.es.esalarm.dao.flow;

import java.util.List;
import java.util.Map;

import com.all_union.es.esalarm.pojo.flow.FlowOrderDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDoKey;
import com.all_union.es.esalarm.pojo.flow.FlowRechargeRecordDo;

public interface FlowOrderDoMapper {
    int deleteByPrimaryKey(FlowOrderDoKey key);

    int insert(FlowOrderDo record);

    int insertSelective(FlowOrderDo record);

    FlowOrderDo selectByOrderNo(FlowOrderDoKey key);

    int updateByOrderNoSelective(FlowOrderDo record);

    int updateByOrderNo(FlowOrderDo record);
    
    /**
     * 根据open_id查询流量充值记录
     * 充值记录中带商品信息
     * @param map 参数信息，至少要包含一个open_id;其他两个是idx 和 pageSize
     * @return
     */
    List<FlowRechargeRecordDo> listOrderByOpenIdWithGoodsInfo(Map<String,Object> map);
}