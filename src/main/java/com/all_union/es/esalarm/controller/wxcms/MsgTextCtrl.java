package com.all_union.es.esalarm.controller.wxcms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.controller.flow.FlowGoodsController;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsQuery;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.pojo.wxcms.MsgTextQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.wxcms.MsgBaseService;
import com.all_union.es.esalarm.service.wxcms.MsgTextService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;

/**
 * 
 */

@Controller
@RequestMapping("/background/wxcms/msgtext")
public class MsgTextCtrl extends BaseController{

	private static Logger logger = LogManager.getLogger(MsgTextCtrl.class);
	
	@Autowired
	private MsgTextService msgTextService;
	
	@Autowired
	private MsgBaseService msgBaseService;

//	@RequestMapping(value = "/getById")
//	public ModelAndView getById(String id){
//		msgTextService.getById(id);
//		return new ModelAndView();
//	}
	
	// 文本消息列表
	@RequestMapping(value = "/list")
	public String list(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") MsgTextQuery query
			){
		
		logger.debug("do method");
		
		// 转到的页数变量
		String strGoPage = null;
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){
			
			//处理参数 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
			if(query.getBaseDo() != null)
				ReflectUtil.replaceFieldString(query.getBaseDo(),"",null);
			else
				query.setBaseDo(new MsgText());
			
		}
		else{
			// 设置查询条件为全部
			query = new MsgTextQuery();
			query.setBaseDo(new MsgText());
			
		}		
		// 每页记录数 test
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
		
		List<MsgText> msgTextList = this.msgTextService.listMsgTextByQuery(query);
		model.addAttribute("msgTextList", msgTextList);
		
		model.addAttribute("query", query);		
		model.addAttribute("curPage", query.getCurrentPage());
		
		return "tiles-msgtextList";
	}

	@RequestMapping(value = "/modify")
	public String toMerge(Model model,@ModelAttribute("id") String id){
		logger.debug("do method");
		
		if(StringUtil.isBlank(id)){
			// 增加页面
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "文本消息新增");
		}
		else{
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "文本消息编辑");
			
			MsgText msgText = msgTextService.getById(id);
			MsgBase msgBase = msgBaseService.getById(msgText.getBaseId().toString());
			
			model.addAttribute("msgText", msgText);
			model.addAttribute("msgBase", msgBase);
		}		
		
		return "tiles-msgtextModify";
	}
	
	@RequestMapping(value = "/modifyAction")
	public ModelAndView doMerge(RedirectAttributes attr,@ModelAttribute("entity") MsgText entity){
		
		logger.debug("do method");
		
		String successMsg;
		if(entity.getId() != null){
			msgTextService.update(entity);
			successMsg = "更新文本信息成功！";
		}else{
			msgTextService.add(entity);
			successMsg = "添加文本信息成功！";
		}
		
		attr.addFlashAttribute("msg",successMsg);
		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(RedirectAttributes attr,
			@ModelAttribute("entity") MsgText entity,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") MsgTextQuery query
			){
		
		logger.debug("do method");
		
		msgTextService.delete(entity);
		
    	// 重定向时带上查询参数，使查询条件不丢失
    	attr.addFlashAttribute("init",init);
		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);
		attr.addFlashAttribute("flag",flag);
		attr.addFlashAttribute("query",query);
			
		return new ModelAndView("redirect:list");
	}

}

