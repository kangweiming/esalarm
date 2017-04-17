/**
 * 
 */
package com.all_union.es.esalarm.controller.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Trigger.TriggerState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.schedule.ScheduleQuery;
import com.all_union.es.esalarm.pojo.schedule.ScheduleTask;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.schedule.ScheduleService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: quartz2 sechedule
 * @author kwm
 * @date 2017年2月21日 下午9:56:37 
 * @version V1.0 
 * 
*/
@Controller
@RequestMapping("/background/schedule")
public class ScheduleController extends BaseController{
	private static Logger logger = LogManager.getLogger(ScheduleController.class);
	
	@Resource  
    private ScheduleService scheduleService; 
	
	// 自定义注解，需要拦截重复提交的方法上如此设置
	//@TokenAnnotation(needSaveToken = true) 
	@RequestMapping("/triggerOpt")
	public String optTriggerTiles(RedirectAttributes attr,
			@ModelAttribute("goPage") String  goPage,
			@ModelAttribute("curPage") String  curPage,
			@ModelAttribute("opt") String  opt,
			@ModelAttribute("optName") String  optName,
			@ModelAttribute("optGroup") String  optGroup,
			@ModelAttribute("query") ScheduleQuery query
			){
		
		logger.debug("do optTriggerTiles method");  
		// 操作类型
		if(StringUtil.isNotEmpty(opt)){
			if(StringUtil.isNotEmpty(optName) && StringUtil.isNotEmpty(optGroup)){
				// 暂停trigger
				if("pauseTrigger".equals(opt)){
					this.scheduleService.pauseTrigger(optName, optGroup);
				}// 恢复trigger
				else if("resumeTrigger".equals(opt)){
					this.scheduleService.resumeTrigger(optName, optGroup);
				}// 立即运行一次
				else if("runTrigger".equals(opt)){
					this.scheduleService.runTrigger(optName, optGroup);
				}// 删除trigger 如果job没有关联的trigger，并且job是非持久化的，job也会被删除
				else if("deleteTrigger".equals(opt))
					this.scheduleService.deleteTrigger(optName, optGroup);
				
			}
		}
		
		// 重定向时带上查询参数，使查询条件不丢失	
		attr.addFlashAttribute("query",query);

		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);

		//return "redirect:/background/schedule/triggerList";
		return "redirect:triggerList";
	}
	
	//使用@ModelAttribute，保证重定向时参数不丢失 
	@RequestMapping("/triggerList")
	// 自定义注解，需要拦截重复提交的方法上如此设置
	//@TokenAnnotation(needSaveToken = true) 
	public String listTirggersTiles(Model model,
		@ModelAttribute("init") String  init,
		@ModelAttribute("goPage") String  goPage,
		@ModelAttribute("curPage") String  curPage,
		@ModelAttribute("flag") String  flag,
		@ModelAttribute("query") ScheduleQuery query
		){
		
		logger.debug("do listTirggersTiles method");
				
		// 需要判断是否是重复提交 使用拦截器		
		
		// 转到的页数变量
		String strGoPage = null;
		
		// for test
		//query.setPageSize(1);
		
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){

			//处理参数 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
			ReflectUtil.replaceFieldString(query,"",null);					
			
		}
		else{
			// 设置查询条件为全部
			query = new ScheduleQuery();
			
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
								
		List<ScheduleTask> taskList = this.scheduleService.listTasks(query);
				
		if (StringUtil.isNotBlank(flag)) {
			model.addAttribute("toPage1", query.getCurrentPage());
			model.addAttribute("toPage", query.getCurrentPage());
		}	
		
		model.addAttribute("curPage", query.getCurrentPage());
		
    	model.addAttribute("taskList", taskList);
    	model.addAttribute("query", query);

    	// state列表
    	model.addAttribute("triggerState", TriggerState.values());
		
		        
		return "tiles-triggerList"; 		
	}
}
