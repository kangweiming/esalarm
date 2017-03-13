/**
 * 
 */
package com.all_union.es.esalarm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;  
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;  
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;  
/** 
 * @Description: Spring Security Java 配置类
 * 这个配置创建一个叫springSecurityFilterChain的Servlet过滤器，
 * 来对我们应用中所有的安全相关的事项（保护应用的所有url，验证用户名密码，表单重定向等）负责。
 * 
 * 这个类和spring-security-db.xml的配置存在一个即可，作用都是一样的，但是必须都得在web.xml里配置filter
 * 如果屏蔽这个类，注释掉注解即可
 * 
 * //@Configuration  
 * //@EnableWebSecurity

 * 此类中用户及密码从DB中获取
 * 
 * @author kwm
 * @date 2017年2月15日 下午18:23:13 
 * @version V1.0 
 * 
*/
//@Configuration  
//@EnableWebSecurity  
public class SecurityConfigurationDB extends WebSecurityConfigurerAdapter {  
   
	// 登录成功处理handler
    @Autowired  
    CustomSuccessHandler customSuccessHandler;  
    
    @Autowired  
    @Qualifier("customUserDetailsService")  
    UserDetailsService userDetailsService;  
       
    @Autowired  
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {  
        auth.userDetailsService(userDetailsService);  
    }  
   
    @Override  
    protected void configure(HttpSecurity http) throws Exception {  
    	//访问授权
      http.authorizeRequests()  
      	.antMatchers("/css/**").permitAll()
      	.antMatchers("/images/**").permitAll()
      	.antMatchers("/js/**").permitAll()
      	.antMatchers("/login").permitAll() // 将login页面不拦截，否则将出现重定向循环的问题
        .antMatchers("/", "/home").access("hasRole('USER')")
        .antMatchers("/admin/**").access("hasRole('ADMIN') and hasRole('DBA')")  
        .antMatchers("/dba/**").access("hasRole('DBA')")
        // Any URL that has not already been matched on only requires that the user be authenticated
      	.anyRequest().authenticated()
        .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)  
        .usernameParameter("userName").passwordParameter("password")  
        .and().csrf()  
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");  
      
      
    }   
     
}  
