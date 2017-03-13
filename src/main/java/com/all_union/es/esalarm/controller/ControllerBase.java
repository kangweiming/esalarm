/**
 * 
 */
package com.all_union.es.esalarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/** 
 * @Description: controller基类
 * @author kwm
 * @date 2017年2月18日 下午8:40:08 
 * @version V1.0 
 * 
*/
public class ControllerBase {
	
	/**
	 * 获得spring security登录的user对象
	 * @return
	 */
	protected User getLoginUser(){
		
		User authUser = null;
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof User){
			authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		else{
			return null;
		}
				
		return authUser;
	}
	
	// 在所有的handler method之前执行
	@ModelAttribute
	public void saveToSession(HttpSession session,HttpServletRequest request){
		// contrller执行时间
		session.setAttribute("current_time", System.currentTimeMillis());
		
		// 当前登陆用户
		User authUser = getLoginUser();
		if(null != authUser)
			session.setAttribute("user", authUser);
				
		// http://localhost:8080/esalarm/
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	    session.setAttribute("basePath", basePath);
				
	}
}
