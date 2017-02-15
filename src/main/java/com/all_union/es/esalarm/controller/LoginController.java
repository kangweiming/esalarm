/**
 * 
 */
package com.all_union.es.esalarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年2月13日 下午3:39:49 
 * @version V1.0 
 * 
*/
@Controller
public class LoginController {

	private static Logger logger = LogManager.getLogger(LoginController.class);
	
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)  
    public String homePage(ModelMap model) {
    	
    	logger.debug("request / or /home");
        model.addAttribute("user", getPrincipal());  
        return "tiles-welcome";  
    }  
   
    @RequestMapping(value = "/admin", method = RequestMethod.GET)  
    public String adminPage(ModelMap model) {  
    	logger.debug("/admin");
        model.addAttribute("user", getPrincipal());  
        return "tiles-admin";  
    }  
       
    @RequestMapping(value = "/dba", method = RequestMethod.GET)  
    public String dbaPage(ModelMap model) {  
    	logger.debug("/dba");
        model.addAttribute("user", getPrincipal());  
        return "tiles-dba";  
    }  
   
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)  
    public String accessDeniedPage(ModelMap model) {  
    	logger.debug("/Access_Denied");
        model.addAttribute("user", getPrincipal());  
        return "tiles-accessDenied";  
    }  
   
    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})  
    public String loginPage() { 
    	logger.debug("/login");
        //return "login";  
    	return "tiles-login";
    }  
   
    @RequestMapping(value="/logout", method = RequestMethod.GET)  
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("/logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
            new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
        return "redirect:/login?logout";  
    }  
   
    private String getPrincipal(){  
        String userName = null;  
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
   
        if (principal instanceof UserDetails) {  
            userName = ((UserDetails)principal).getUsername();  
        } else {  
            userName = principal.toString();  
        }  
        return userName;  
    } 
}
