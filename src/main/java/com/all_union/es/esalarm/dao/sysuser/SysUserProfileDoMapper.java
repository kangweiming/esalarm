package com.all_union.es.esalarm.dao.sysuser;

import com.all_union.es.esalarm.pojo.sysuser.SysUserProfileDo;

public interface SysUserProfileDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserProfileDo record);

    int insertSelective(SysUserProfileDo record);

    SysUserProfileDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserProfileDo record);

    int updateByPrimaryKey(SysUserProfileDo record);
}