package com.all_union.es.esalarm.dao.user;

import java.util.List;

import com.all_union.es.esalarm.pojo.user.UserDo;

public interface UserDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    UserDo selectByPrimaryKey(Integer id);
   
    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
    
    // 查询总数
    int countUserByUserName(UserQuery query);
    
    //查询所有记录-分页
    List<UserDo> listUserByUserName(UserQuery query);
    
}