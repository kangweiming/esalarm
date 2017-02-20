/**
 * 
 */
package com.all_union.es.esalarm.service.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.pojo.sysuser.SysUserDo;
import com.all_union.es.esalarm.pojo.sysuser.UserProfileDo;
import com.all_union.es.esalarm.pojo.sysuser.UserState;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.impl.user.UserServiceImpl;
import com.all_union.es.esalarm.service.sysuser.SysUserService;
import com.all_union.es.esalarm.service.user.UserService;

/** 
 * @Description: spring security user service
 * @author kwm
 * @date 2017年2月15日 下午6:33:57 
 * @version V1.0 
 * 
*/
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	private static Logger logger = LogManager.getLogger(CustomUserDetailsService.class);
	
    @Autowired  
    private SysUserService sysUserService;  
       
    @Transactional(readOnly=true)  
    public UserDetails loadUserByUsername(String userName)  
            throws UsernameNotFoundException {  
    	
        SysUserDo sysUser = sysUserService.selectByUserName(userName); 
        
        logger.debug("system User : " + sysUser);
        
        if(sysUser==null){  
        	logger.debug("sysUser not found");  
            throw new UsernameNotFoundException("sysUser not found");  
        }  
        
            return new org.springframework.security.core.userdetails.User(sysUser.getUserName(), sysUser.getPassword(),   
            		sysUser.getState().equals(UserState.ACTIVE.toString()), true, true, true, getGrantedAuthorities(sysUser));  
    }  
   
    /**
     * 获得系统用户的全部权限   
     * @param sysUser
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(SysUserDo sysUser){  
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
           
        for(UserProfileDo sysUserProfile : sysUser.getUserProfiles()){  
        	logger.debug("SysUserProfile : " + sysUserProfile);  
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysUserProfile.getType()));
            //authorities.add(new SimpleGrantedAuthority(sysUserProfile.getType()));
        }  
        logger.debug("authorities :"+authorities);  
        return authorities;  
    }  

}
