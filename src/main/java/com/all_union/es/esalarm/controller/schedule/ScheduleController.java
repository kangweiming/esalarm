/**
 * 
 */
package com.all_union.es.esalarm.controller.schedule;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Trigger.TriggerState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.all_union.es.esalarm.controller.ControllerBase;
import com.all_union.es.esalarm.pojo.schedule.ScheduleQuery;
import com.all_union.es.esalarm.pojo.schedule.ScheduleTask;
import com.all_union.es.esalarm.service.schedule.ScheduleService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.DateUtil;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: quartz2 sechedule
 * @author kwm
 * @date 2017年2月21日 下午9:56:37 
 * @version V1.0 
 * 
*/
@Controller
public class ScheduleController extends ControllerBase{
	private static Logger logger = LogManager.getLogger(ScheduleController.class);
	
	@Resource  
    private ScheduleService scheduleService; 
	
	// trigger opt：paused resume runimmediately
	@RequestMapping("/triggerOpt")
	public String triggerOpt(RedirectAttributes attr,
			@ModelAttribute("triggerName") String  triggerName,
			@ModelAttribute("triggerGroup") String  triggerGroup,
			@ModelAttribute("triggerStatus") String  triggerStatus,
			@ModelAttribute("gmtStart1") String  gmtStart1,
			@ModelAttribute("gmtStart2") String  gmtStart2,
			@ModelAttribute("gmtEnd1") String  gmtEnd1,
			@ModelAttribute("gmtEnd2") String  gmtEnd2,
			@ModelAttribute("goPage") String  goPage,
			@ModelAttribute("curPage") String  curPage,
			@ModelAttribute("opt") String  opt,
			@ModelAttribute("optName") String  optName,
			@ModelAttribute("optGroup") String  optGroup
			){
		
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
		attr.addFlashAttribute("triggerName",triggerName);
		attr.addFlashAttribute("triggerGroup",triggerGroup);
		attr.addFlashAttribute("triggerStatus",triggerStatus);

		attr.addFlashAttribute("gmtStart1",gmtStart1);
		attr.addFlashAttribute("gmtStart2",gmtStart2);
		attr.addFlashAttribute("gmtEnd1",gmtEnd1);
		attr.addFlashAttribute("gmtEnd2",gmtEnd2);

		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);

		return "redirect:/triggerList";
	}
	
	//使用@ModelAttribute，保证重定向时参数不丢失 
	@RequestMapping("/triggerList")
	public String showTirggersTiles(Model model,
		@ModelAttribute("init") String  init,
		@ModelAttribute("triggerName") String  triggerName,
		@ModelAttribute("triggerGroup") String  triggerGroup,
		@ModelAttribute("triggerStatus") String  triggerStatus,
		@ModelAttribute("gmtStart1") String  gmtStart1,
		@ModelAttribute("gmtStart2") String  gmtStart2,
		@ModelAttribute("gmtEnd1") String  gmtEnd1,
		@ModelAttribute("gmtEnd2") String  gmtEnd2,
		@ModelAttribute("goPage") String  goPage,
		@ModelAttribute("curPage") String  curPage,
		@ModelAttribute("flag") String  flag
		){
		
		logger.debug("do method");
				
		// 需要判断是否是重复提交 使用拦截器		
		
    	// 设置查询条件
		ScheduleQuery query = new ScheduleQuery();
		// 转到的页数变量
		String strGoPage = null;
		
		// for test
		query.setPageSize(10);
		
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){

			query.setTriggerName(StringUtil.trim(triggerName));
			query.setTriggerGroup(StringUtil.trim(triggerGroup));
			query.setTriggerStatus(StringUtil.trim(triggerStatus));
									
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
