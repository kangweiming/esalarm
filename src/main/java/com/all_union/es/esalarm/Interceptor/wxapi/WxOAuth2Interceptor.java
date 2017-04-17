package com.all_union.es.esalarm.Interceptor.wxapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.all_union.es.esalarm.common.util.HttpUtil;
import com.all_union.es.esalarm.common.wxapi.WxApi;
import com.all_union.es.esalarm.common.wxapi.WxApiClient;
import com.all_union.es.esalarm.common.wxapi.WxMemoryCacheClient;
import com.all_union.es.esalarm.pojo.wxapi.MpAccount;
import com.all_union.es.esalarm.pojo.wxapi.OAuthScope;
import com.kwm.common.lang.StringUtil;

/**
 * 微信客户端用户请求验证拦截器
 */
public class WxOAuth2Interceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LogManager.getLogger(WxOAuth2Interceptor.class);
	
	/**
	 * 开发者自行处理拦截逻辑，
	 * 方便起见，此处只处理includes
	 */
	private String[] excludes;//不需要拦截的
	private String[] includes;//需要拦截的
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		boolean oauthFlag = false;//为方便展示的参数，开发者自行处理
		for(String s : includes){
			if(uri.contains(s)){//如果包含，就拦截
				oauthFlag = true;
				break;
			}
		}
		if(!oauthFlag){//如果不需要oauth认证
			return true;
		}
		
		String sessionid = request.getSession().getId();
		String openid = WxMemoryCacheClient.getOpenid(sessionid);//先从缓存中获取openid
		
		logger.debug("#### WxOAuth2Interceptor request.url = " + request.getRequestURL());
		
		if(StringUtil.isBlank(openid)){//没有，通过微信页面授权获取
			logger.debug("#### WxOAuth2Interceptor 缓存中不存在openid");
			String code = request.getParameter("code");
			if(!StringUtil.isBlank(code)){//如果request中包括code，则是微信回调
				try {
					logger.debug("#### WxOAuth2Interceptor 参数中存在code，是微信回调 code = " + code);
					openid = WxApiClient.getOAuthOpenId(WxMemoryCacheClient.getSingleMpAccount(), code);
					if(!StringUtil.isBlank(openid)){
						WxMemoryCacheClient.setOpenid(sessionid, openid);//缓存openid
						logger.debug("#### WxOAuth2Interceptor 将openid存入缓存 openid = " + openid);
						return true;
					}
				} catch (Exception e) {
					logger.debug("#### WxOAuth2Interceptor error" , e);
				}
			}else{//oauth获取code
				MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
				//String redirectUrl = HttpUtil.getRequestFullUriNoContextPath(request);//请求code的回调url
				
				String redirectUrl = HttpUtil.getRequestFullUri(request);//请求code的回调url
				logger.debug("#### WxOAuth2Interceptor 请求code的回调url = " + redirectUrl);
				
				//String state = OAuth2RequestParamHelper.prepareState(request);
				String state = "kwm";
				logger.debug("#### WxOAuth2Interceptor state = " + state);
				
				// 引导用户打开这个界面，根据scope不同，后续行为不同，如果是base，则静默处理
				//String url = WxApi.getOAuthCodeUrl(mpAccount.getAppid(), redirectUrl, OAuthScope.Base.toString(), state);
				String url = WxApi.getOAuthCodeUrl(mpAccount.getAppid(), redirectUrl, OAuthScope.Userinfo.toString(), state);
				logger.debug("#### WxOAuth2Interceptor oauthcodeurl = " + url);
				
				HttpUtil.redirectHttpUrl(request, response, url);
				logger.debug("#### WxOAuth2Interceptor 进行重定向");
				
				return false;
			}
		}
		else{
			logger.debug("#### WxOAuth2Interceptor 缓存中 openid = " + openid);
			return true;
		}
		//HttpUtil.redirectUrl(request, response, "/error.jsp");
		return false;
	}
	
	
	public String[] getExcludes() {
		return excludes;
	}

	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}

	public String[] getIncludes() {
		return includes;
	}

	public void setIncludes(String[] includes) {
		this.includes = includes;
	}
	
	
}

