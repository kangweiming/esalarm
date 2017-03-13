/**
 * 
 */
package com.all_union.es.esalarm.service.sysuser;

import com.all_union.es.esalarm.pojo.sysuser.SysUserDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年2月17日 下午1:19:16 
 * @version V1.0 
 * 
*/
public interface SysUserService {
	
	SysUserDo selectByUserName(String userName);
	
	int updateLastLoginByUserName(String userName);
	
}
