package com.all_union.es.esalarm.dao.flow;

import com.all_union.es.esalarm.pojo.flow.FlowUserDo;

public interface FlowUserDoMapper {
    int deleteByOpenId(String openId);

    int insert(FlowUserDo record);

    int insertSelective(FlowUserDo record);

    FlowUserDo selectByOpenId(String openId);

    int updateByOpenIdSelective(FlowUserDo record);

    int updateByOpenId(FlowUserDo record);
}