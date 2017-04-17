/**
 * 
 */
package com.all_union.es.esalarm.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.common.wxapi.WxApiClient;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年2月13日 下午3:39:49 
 * @version V1.0 
 * 
*/
@Controller
public class LoginController extends BaseController{

	private static Logger logger = LogManager.getLogger(LoginController.class);
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)  
    public String defaultPage(ModelMap model) {
    	
    	logger.debug("request /default");
    	    	    	
        return "tiles-default";  
    } 
    
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)  
    public String homePage(ModelMap model) {
    	
    	logger.debug("request / or /home");
    	
        return "tiles-welcome";  
    }  
   
    @RequestMapping(value = "/admin", method = RequestMethod.GET)  
    public String adminPage(ModelMap model) {  
    	logger.debug("request /admin");
    	    	
        return "tiles-admin";  
    }  
       
    @RequestMapping(value = "/dba", method = RequestMethod.GET)  
    public String dbaPage(ModelMap model) {  
    	logger.debug("request /dba");
    	 
        return "tiles-dba";  
    }  
   
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)  
    public String accessDeniedPage(ModelMap model) {  
    	logger.debug("request /Access_Denied");
    	  
        return "tiles-accessDenied";  
    }  
   
    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})  
    public String loginPage(HttpServletRequest request,ModelMap model) { 
    	logger.debug("request /login");
    	
    	// 读取cookie，取得保存的loginid
    	Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie : cookies){
            	if(cookie.getName().equals(WebConstants.LoginID_Cookie)){
            		model.addAttribute("loginID", cookie.getValue());
            		break;
            	}              
            }
        }
    	
    	return "tiles-login";
    }  
   
    @RequestMapping(value="/logout", method = RequestMethod.GET)  
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("request /logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
            new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
        
        return "redirect:/login?logout";  
    }  
  
}
