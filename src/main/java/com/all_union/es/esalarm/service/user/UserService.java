/**
 * 
 */
package com.all_union.es.esalarm.service.user;

import java.util.List;

import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.pojo.user.UserQuery;
/** 
 * @Description: 
 * @author kwm
 * @date 2017年1月18日 下午5:36:43 
 * @version V1.0 
 * 
*/
public interface UserService {

	public UserDo getUserById(int userId);
	
	/**
	 * 查询总数
	 * @param query
	 * @return
	 */
    int countUserByUserName(UserQuery query);
    
    /**
     * 查询所有记录-分页
     * @param query
     * @return
     */
    List<UserDo> listUserByUserName(UserQuery query);
    /**
     * 新增一个user
     * @param record
     * @return
     */
    int insertSelective(UserDo record);
    /**
     * 根据主键更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserDo record);
    /**
     * 根据主键删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
}
