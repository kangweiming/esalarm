/**
 * 
 */
package com.all_union.es.esalarm.service.impl.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.controller.user.UserController;
import com.all_union.es.esalarm.dao.user.UserDoMapper;
import com.all_union.es.esalarm.dao.user.UserQuery;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.user.UserService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年1月18日 下午5:39:53 
 * @version V1.0 
 * 
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDoMapper userDao;
	
	@Override
	public UserDo getUserById(int userId) {
		logger.debug("excute ...");
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int countUserByUserName(UserQuery query) {
		logger.debug("excute ...");
		return this.userDao.countUserByUserName(query);
	}

	@Override
	public List<UserDo> listUserByUserName(UserQuery query) {
		logger.debug("excute ...");
		
		//获得总数
		int count = this.userDao.countUserByUserName(query);
		
		if(count > 0){
			query.setTotalItem(count);
			return this.userDao.listUserByUserName(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}
				
	}

}
