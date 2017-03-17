package com.all_union.es.esalarm.dao.flow;

import com.all_union.es.esalarm.pojo.flow.FlowOrderTransDo;

public interface FlowOrderTransDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowOrderTransDo record);

    int insertSelective(FlowOrderTransDo record);

    FlowOrderTransDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowOrderTransDo record);

    int updateByPrimaryKey(FlowOrderTransDo record);
}