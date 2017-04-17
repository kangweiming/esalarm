package com.all_union.es.esalarm.Interceptor.wxcms;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
/**
 * 若URL地址中 包含jsessionid的信息，则让session失效
 * @Description: 
 * @author kwm
 * @date 2017年3月27日 下午4:17:35 
 * @version V1.0 
 *
 */
public class DisableUrlSessionFilter implements Filter {  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        if (!(request instanceof HttpServletRequest)) {  
            chain.doFilter(request, response);  
            return;  
        }  
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
  
        if (httpRequest.isRequestedSessionIdFromURL()) {  
            HttpSession session = httpRequest.getSession();  
            if (session != null)  
                session.invalidate();  
        }  
        
        // wrap response to remove URL encoding  
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {  
            @Override  
            public String encodeRedirectUrl(String url) {  
                return url;  
            }  
  
            public String encodeRedirectURL(String url) {  
                return url;  
            }  
  
            public String encodeUrl(String url) {  
                return url;  
            }  
  
            public String encodeURL(String url) {  
                return url;  
            }  
        };  
        chain.doFilter(request, wrappedResponse);  
    }  
  
    public void init(FilterConfig config) throws ServletException {  
    }  
  
    public void destroy() {  
    }  
}  