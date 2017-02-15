/**
 * 
 */
package com.all_union.es.esalarm.security.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/** 
 * @Description: AuthenticationSuccessHandle,身份认证成功后，处理方法
 * 重写了handle()方法，
 * 简单的调用重定向使用配置的RedirectStrategy，其中通过determineTargetUrl方法返回对应的url 。
 * 此方法从Authentication 对象中提取角色然后根据 角色构建 对应的url.
 * 最后在 Spring Security 负责所有重定向事务的RedirectStrategy （重定向策略）来重定向请求到指定的url
 * @author kwm
 * @date 2017年2月13日 下午3:11:04 
 * @version V1.0 
 * 
*/
@Component  
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {  
   
	private static Logger logger = LogManager.getLogger(CustomSuccessHandler.class);
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();  
   
    @Override  
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  
            throws IOException {  
        String targetUrl = determineTargetUrl(authentication);  
        logger.debug("do method handle");
        if (response.isCommitted()) {  
        	logger.debug("Can't redirect");
            return;  
        }  
   
        redirectStrategy.sendRedirect(request, response, targetUrl);  
    }  
   
    /* 
     * This method extracts the roles of currently logged-in user and returns 
     * appropriate URL according to his/her role. 
     */  
    protected String determineTargetUrl(Authentication authentication) {  
        String url = "";  
   
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();  
   
        List<String> roles = new ArrayList<String>();  
   
        for (GrantedAuthority a : authorities) {  
            roles.add(a.getAuthority());  
        }  
   
        if (isDba(roles)) {  
            url = "/dba";  
        } else if (isAdmin(roles)) {  
            //url = "/admin";  
        	url = "/userList";
        } else if (isUser(roles)) {  
            url = "/home";  
        } else {  
            url = "/accessDenied";  
        }  
   
        return url;  
    }  
   
    private boolean isUser(List<String> roles) {  
        if (roles.contains("ROLE_USER")) {  
            return true;  
        }  
        return false;  
    }  
   
    private boolean isAdmin(List<String> roles) {  
        if (roles.contains("ROLE_ADMIN")) {  
            return true;  
        }  
        return false;  
    }  
   
    private boolean isDba(List<String> roles) {  
        if (roles.contains("ROLE_DBA")) {  
            return true;  
        }  
        return false;  
    }  
   
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {  
        this.redirectStrategy = redirectStrategy;  
    }  
   
    protected RedirectStrategy getRedirectStrategy() {  
        return redirectStrategy;  
    }  
   
}  
