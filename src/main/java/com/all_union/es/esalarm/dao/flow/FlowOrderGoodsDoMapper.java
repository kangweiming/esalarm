package com.all_union.es.esalarm.dao.flow;

import com.all_union.es.esalarm.pojo.flow.FlowOrderGoodsDo;

public interface FlowOrderGoodsDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowOrderGoodsDo record);

    int insertSelective(FlowOrderGoodsDo record);

    FlowOrderGoodsDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowOrderGoodsDo record);

    int updateByPrimaryKey(FlowOrderGoodsDo record);
}