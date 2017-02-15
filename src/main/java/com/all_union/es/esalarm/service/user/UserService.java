/**
 * 
 */
package com.all_union.es.esalarm.service.user;

import java.util.List;

import com.all_union.es.esalarm.dao.user.UserQuery;
import com.all_union.es.esalarm.pojo.user.UserDo;
/** 
 * @Description: 
 * @author kwm
 * @date 2017年1月18日 下午5:36:43 
 * @version V1.0 
 * 
*/
public interface UserService {

	public UserDo getUserById(int userId);
	
	// 查询总数
    int countUserByUserName(UserQuery query);
    
    //查询所有记录-分页
    List<UserDo> listUserByUserName(UserQuery query);

}
