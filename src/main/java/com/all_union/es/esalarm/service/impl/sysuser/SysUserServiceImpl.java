/**
 * 
 */
package com.all_union.es.esalarm.service.impl.sysuser;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.dao.sysuser.SysUserDoMapper;
import com.all_union.es.esalarm.pojo.sysuser.SysUserDo;
import com.all_union.es.esalarm.service.sysuser.SysUserService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年2月17日 下午1:22:49 
 * @version V1.0 
 * 
*/
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	private static Logger logger = LogManager.getLogger(SysUserServiceImpl.class);

	@Resource
	SysUserDoMapper sysUserDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.sysuser.SysUserService#selectByUserName(java.lang.String)
	 */
	@Override
	public SysUserDo selectByUserName(String userName) {
		logger.debug("excute ...");
		
		return this.sysUserDao.selectByUserName(userName);
		
	}

	@Override
	public int updateLastLoginByUserName(String userName) {
		logger.debug("excute ...");
		return this.sysUserDao.updateLastLoginByUserName(userName);
	}

}
