/**
 * 
 */
package com.all_union.es.esalarm.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.service.sysuser.SysUserService;

/** 
 * @Description: 身份认证成功后，处理方法,会自动跳转到之前请求的request
 * @author kwm
 * @date 2017年2月19日 上午10:38:48 
 * @version V1.0 
 * 
*/
@Component 
public class CustomSaveReuestAwareSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private static Logger logger = LogManager.getLogger(CustomSaveReuestAwareSuccessHandler.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Autowired  
    private SysUserService sysUserService;  
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		// add by kwm begin
		// 获取当前登陆用户
		User authUser = null;
		authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("authUser : " + authUser.toString());
		
		// 修改登陆用户的最后登录时间
		sysUserService.updateLastLoginByUserName(authUser.getUsername());
		
		// 将登陆名保存到cookie		
		Cookie cookie = new Cookie(WebConstants.LoginID_Cookie,authUser.getUsername());
		
		// 设置cookie生命周期
		cookie.setMaxAge(86400);		 
		// 设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问		 
		cookie.setPath("/");
		response.addCookie(cookie);		
		// add by kwm end		
		
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		
		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);

		clearAuthenticationAttributes(request);
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
}
