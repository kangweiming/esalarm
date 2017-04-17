/**
 * 
 */
package com.all_union.es.esalarm.controller.user;

import java.net.UnknownHostException;
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

import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.pojo.user.UserQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.user.UserService;
import com.all_union.es.esclient.test.GetData;
import com.kwm.common.convert.Convert;
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
@RequestMapping("/background/user")
public class UserController extends BaseController{

	private static Logger logger = LogManager.getLogger(UserController.class);
	
	// 使用注解实现自动装配 不需要再写get set方法以及在context中配置bean
    @Resource  
    private UserService userService;  

    // 处理请求地址映射的注解,访问/userList这个URI时，调用此方法 
    @RequestMapping("/userList")  
    //@RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String listUserTiles(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String  goPage,
    		@ModelAttribute("curPage") String  curPage,
    		@ModelAttribute("flag") String  flag,
    		@ModelAttribute("query") UserQuery  query
    		){  
    	
    	logger.debug("do listUserTiles method");  
    	    	    	
		// 转到的页数变量
		String strGoPage = null;
		
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){
			//处理参数 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
			ReflectUtil.replaceFieldString(query,"",null);						
			
		}
		else{
			// 设置查询条件为全部
			query =  new UserQuery();
		}
		// for test
		query.setPageSize(2);
		
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
        
        // 映射全路径为 /WEB-INF/jsp/user/showUser.jsp 详见spring-mvc.xml视图配置部分
        return "/user/showUser"; 
        
    }        
}
