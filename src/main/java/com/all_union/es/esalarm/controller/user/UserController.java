/**
 * 
 */
package com.all_union.es.esalarm.controller.user;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.all_union.es.esalarm.controller.ControllerBase;
import com.all_union.es.esalarm.dao.user.UserQuery;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.user.UserService;
import com.all_union.es.esclient.test.GetData;
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
public class UserController extends ControllerBase{

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	// 使用注解实现自动装配 不需要再写get set方法以及在context中配置bean
    @Resource  
    private UserService userService;  

    // 处理请求地址映射的注解,访问/userList这个URI时，调用此方法 
    @RequestMapping("/userList")  
    //@RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String showInfoTiles(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("userName") String  userName,
    		@ModelAttribute("password") String  password,
    		@ModelAttribute("age") String  age,
    		@ModelAttribute("gmtStart1") String  gmtStart1,
    		@ModelAttribute("gmtStart2") String  gmtStart2,
    		@ModelAttribute("gmtEnd1") String  gmtEnd1,
    		@ModelAttribute("gmtEnd2") String  gmtEnd2,
    		@ModelAttribute("goPage") String  goPage,
    		@ModelAttribute("curPage") String  curPage,
    		@ModelAttribute("flag") String  flag	
    		){  
    	
    	logger.debug("do showInfoTiles method");  
    	    	    	
    	// 设置查询条件
    	UserQuery query = new UserQuery();
		// 转到的页数变量
		String strGoPage = null;
		// 列表下面显示的分页数开始页
		//int iStartPage = 0;
		// 列表下面显示的分页数结束页
		//int iEndPage = 0;
		
		// for test
		query.setPageSize(2);
		
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){
			query.setUserName(StringUtil.trim(userName));
			query.setPassword(StringUtil.trim(password));	
			
			if(StringUtil.isNotBlank(age))
				query.setAge(Convert.asInt(StringUtil.trim(age)));		
									
			query.setGmtStart1(StringUtil.trim(gmtStart1));
			query.setGmtEnd1(StringUtil.trim(gmtEnd1));
			query.setGmtStart2(StringUtil.trim(gmtStart2));
			query.setGmtEnd2(StringUtil.trim(gmtEnd2));						
			
		}
		else{
			// 设置查询条件为全部
			
		}
		
		// 跳转页面号参数
		strGoPage = StringUtil.trim(goPage);
		
		if (null == strGoPage) {
			
			if(!model.containsAttribute("curPage"))
				strGoPage = "1";
			else
				strGoPage = StringUtil.trim(curPage);
		}
		query.setCurrentPageString(strGoPage);
		
		
		if (StringUtil.isNotBlank(flag)) {
			model.addAttribute("toPage1", query.getCurrentPage());
			model.addAttribute("toPage", query.getCurrentPage());
		}		    	

		List<UserDo> userList = this.userService.listUserByUserName(query);
    	
		model.addAttribute("userList", userList);
    	model.addAttribute("query", query);
    	
		model.addAttribute("curPage", query.getCurrentPage());
		        
        return "tiles-userList"; 
        
    }   
    
    // 不能以“.jsp”结尾，会提示404
    @RequestMapping("/showUser.kkk")  
    public String showInfoJSP(HttpServletRequest request,Model model){  
    	
    	logger.debug("do showInfoJSP method");
    	    	
        logger.debug("==== es查询结果");
        List<Map> esList ;
        try {
			esList = GetData.ESDataTest2();
			model.addAttribute("ls", esList);
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(),e); 
		}
        
        return "/user/showUser"; 
        
    }        
}
