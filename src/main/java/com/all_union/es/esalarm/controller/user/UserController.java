/**
 * 
 */
package com.all_union.es.esalarm.controller.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.all_union.es.esalarm.dao.user.UserQuery;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.user.UserService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.DateUtil;
import com.kwm.common.lang.StringUtil;;
/** 
 * @Description: 
 * @author kwm
 * @date 2017年1月19日 上午10:57:00 
 * @version V1.0 
 * 
*/
//处理请求地址映射的注解,表示类中的所有响应请求的方法都是以该地址作为父路径 
@Controller
//@RequestMapping("/user")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	// 使用注解实现自动装配 不需要再写get set方法以及在context中配置bean
    @Resource  
    private UserService userService;  

    // 处理请求地址映射的注解,访问/userList这个URI时，调用此方法 
    @RequestMapping("/userList")  
    //@RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String showInfoTiles(HttpServletRequest request,Model model){  
    	
    	logger.debug("do showInfoTiles method");    	
    	
    	// 获得init参数
    	//Boolean init = Boolean.valueOf(request.getParameter("init").trim());
    	
    	Boolean init = Convert.asBoolean(StringUtil.trim(request.getParameter("init")));
    	
    	// 设置查询条件
    	UserQuery query = new UserQuery();
		// 转到的页数变量
		String strGoPage = null;
		// 列表下面显示的分页数开始页
		int iStartPage = 0;
		// 列表下面显示的分页数结束页
		int iEndPage = 0;
		
		// for test
		query.setPageSize(2);
		
		// 非init标识，则获得查询条件
		if(!init){
			query.setUserName(StringUtil.trim(request.getParameter("userName")));
			query.setPassword(StringUtil.trim(request.getParameter("password")));	
			
			String age = request.getParameter("age");
			if(StringUtil.isNotBlank(age))
				query.setAge(Convert.asInt(StringUtil.trim(age)));		
			
						
			Date gmtStart1 = DateUtil.getYmdDate(StringUtil.trim(request.getParameter("gmtStart1")));
			Date gmtStart2 = DateUtil.getYmdDate(StringUtil.trim(request.getParameter("gmtStart2")));
			Date gmtEnd1   = DateUtil.getYmdDate(StringUtil.trim(request.getParameter("gmtEnd1")));
			Date gmtEnd2   = DateUtil.getYmdDate(StringUtil.trim(request.getParameter("gmtEnd2")));
			
			query.setGmtStart1(DateUtil.getYmdDateString(gmtStart1));
			query.setGmtEnd1(DateUtil.getYmdDateString(gmtEnd1));
			query.setGmtStart2(DateUtil.getYmdDateString(gmtStart2));
			query.setGmtEnd2(DateUtil.getYmdDateString(gmtEnd2));						
			
		}
		else{
			// 设置查询条件为全部
			
		}
		
		// 跳转页面号参数
		strGoPage = StringUtil.trim(request.getParameter("goPage"));
		
		if (null == strGoPage) {
			
			if(!model.containsAttribute("curPage"))
				strGoPage = "1";
			else
				strGoPage = (String) model.asMap().get("curPage");
		}
		query.setCurrentPageString(strGoPage);
		
		iEndPage = query.getTotalPage();
		
		String flag = StringUtil.trim(request.getParameter("flag"));
		
		List<UserDo> userList = this.userService.listUserByUserName(query);
		
		if (StringUtil.isNotBlank(flag)) {
			model.addAttribute("toPage1", query.getCurrentPage());
			model.addAttribute("toPage", query.getCurrentPage());
		}		    	
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("query", query);
    	
		model.addAttribute("curPage", query.getCurrentPage());
		model.addAttribute("endPage", iEndPage);
		model.addAttribute("startPage", iStartPage);
		model.addAttribute("current_time", System.currentTimeMillis());
        
        return "tiles-userList"; 
        
    }   
    
    // 不能以“.jsp”结尾，会提示404
    @RequestMapping("/showUser.kkk")  
    public String showInfoJSP(HttpServletRequest request,Model model){  
    	
    	// 1. 查询条件可以暂存在query中
    	logger.debug("do showInfoJSP method");
    	
    	// 设置查询条件
    	UserQuery query = new UserQuery();
    	query.setUserName("测试");
    	
    	List<UserDo> userList = this.userService.listUserByUserName(query);
    	
    	model.addAttribute("userList", userList);
    	model.addAttribute("query", query);
    	
//        logger.info("==== es查询结果");
//        List<Map> esList ;
//        try {
//			esList = GetData.ESDataTest2();
//			model.addAttribute("ls", esList);
//		} catch (UnknownHostException e) {
//			logger.error(e.getMessage(),e); 
//		}
        
        return "/user/showUser"; 
        
    }        
}
