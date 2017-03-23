package com.all_union.es.esalarm.dao.resource;

import java.util.List;

import com.all_union.es.esalarm.pojo.resource.ResourceAreaDo;

public interface ResourceAreaDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceAreaDo record);

    int insertSelective(ResourceAreaDo record);

    ResourceAreaDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceAreaDo record);

    int updateByPrimaryKey(ResourceAreaDo record);
    /**
     * 查询所有升级行政区域信息
     * @return
     */
    List<ResourceAreaDo> listAllProvince();
}