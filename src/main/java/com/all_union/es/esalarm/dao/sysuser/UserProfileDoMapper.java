package com.all_union.es.esalarm.dao.sysuser;

import com.all_union.es.esalarm.pojo.sysuser.UserProfileDo;

public interface UserProfileDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserProfileDo record);

    int insertSelective(UserProfileDo record);

    UserProfileDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserProfileDo record);

    int updateByPrimaryKey(UserProfileDo record);
}