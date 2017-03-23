package com.all_union.es.esalarm.dao.flow;

import java.util.List;

import com.all_union.es.esalarm.pojo.flow.FlowChannelDo;
import com.all_union.es.esalarm.pojo.flow.FlowChannelQuery;

public interface FlowChannelDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowChannelDo record);

    int insertSelective(FlowChannelDo record);

    FlowChannelDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowChannelDo record);

    int updateByPrimaryKey(FlowChannelDo record);
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