package com.all_union.es.esalarm.dao.sysuser;

import com.all_union.es.esalarm.pojo.sysuser.SysUserDo;

public interface SysUserDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserDo record);

    int insertSelective(SysUserDo record);

    SysUserDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserDo record);

    int updateByPrimaryKey(SysUserDo record);
    
    // not auto    
    SysUserDo selectByUserName(String userName);
    
    int updateLastLoginByUserName(String userName);
}