package com.all_union.es.esalarm.dao.flow;

import java.util.List;

import com.all_union.es.esalarm.pojo.flow.FlowTelecomOperatorDo;

public interface FlowTelecomOperatorDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTelecomOperatorDo record);

    int insertSelective(FlowTelecomOperatorDo record);

    FlowTelecomOperatorDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTelecomOperatorDo record);

    int updateByPrimaryKey(FlowTelecomOperatorDo record);
    /**
     * 查询所有记录
     * @return
     */
    List<FlowTelecomOperatorDo> listAll();
}