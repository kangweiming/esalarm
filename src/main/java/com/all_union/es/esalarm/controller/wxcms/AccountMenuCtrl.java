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
import com.all_union.es.esalarm.pojo.wxcms.AccountMenu;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroup;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.wxcms.AccountMenuGroupService;
import com.all_union.es.esalarm.service.wxcms.AccountMenuService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;

/**
 * 
 * @Description: 菜单管理
 * @author kwm
 * @date 2017年4月6日 上午11:10:51 
 * @version V1.0 
 *
 */
@Controller
@RequestMapping("/background/wxcms/accountmenu")
public class AccountMenuCtrl extends BaseController{

	private static Logger logger = LogManager.getLogger(AccountMenuCtrl.class);
	
	@Autowired
	private AccountMenuService accountMenuService;
	
	@Autowired
	private AccountMenuGroupService accountMenuGroupService;

	@RequestMapping(value = "/getById")
	public ModelAndView getById(String id){
		accountMenuService.getById(id);
		return new ModelAndView();
	}

	@RequestMapping(value = "/list")
	public String list(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") AccountMenuQuery query){
		
		logger.debug("do method");
		
		// 转到的页数变量
		String strGoPage = null;
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){
			
			//处理参数 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
			if(query.getBaseDo() != null)
				ReflectUtil.replaceFieldString(query.getBaseDo(),"",null);
			else
				query.setBaseDo(new AccountMenu());
			
		}
		else{
			// 设置查询条件为全部
			query = new AccountMenuQuery();
			query.setBaseDo(new AccountMenu());
			
		}		
		// 每页记录数 test
		//query.setPageSize(2); 	
		
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
		
		List<AccountMenu> menulist = accountMenuService.listMenuByQuery(query);
		AccountMenuGroup menuGroup = accountMenuGroupService.getById(query.getBaseDo().getGid().toString());
		
		model.addAttribute("menulist", menulist);		
		model.addAttribute("menuGroup", menuGroup);
		
		model.addAttribute("query", query);		
		model.addAttribute("curPage", query.getCurrentPage());	
		
		return "tiles-menuList";
	}
	
	@RequestMapping(value = "/modify")
	public String toMerge(Model model,@ModelAttribute("id") String id,@ModelAttribute("gid") String gid){
		
		logger.debug("do method");
				
		// 一级菜单
		List<AccountMenu> parentMenuList = accountMenuService.listParentMenuByGid(gid);
		model.addAttribute("parentMenuList", parentMenuList);
		
		// 组id
		model.addAttribute("gid", gid);
		
		if(StringUtil.isBlank(id)){
			// 增加页面
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "菜单新增");
			
		}
		else{
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "菜单编辑");
			
			AccountMenu menu = accountMenuService.getById(id);
			
			model.addAttribute("menu", menu);
		}		

		return "tiles-menuModify";
	}

	@RequestMapping(value = "/modifyAction")
	public String doMerge(RedirectAttributes attr,@ModelAttribute("menu") AccountMenu menu){
		logger.debug("do method");
		
		// 针对 inputCode url msgId这三个字段进行判断
		// mtype：菜单类型 为view 仅url有效
		// mtype：菜单类型 为click 且 eventType为 key，则仅inputCode有效
		// mtype：菜单类型 为click 且 eventType为 fix，则仅msgId有效
		if("view".equals(menu.getMtype())){
			menu.setInputcode("");
			menu.setMsgId(null);
		}
		else if("click".equals(menu.getMtype()) && "key".equals(menu.getEventType())){
			menu.setUrl(null);
			menu.setMsgId(null);
		}
		else if("click".equals(menu.getMtype()) && "fix".equals(menu.getEventType())){
			menu.setUrl(null);
			menu.setInputcode("");
		}
		
		if(menu.getGid() != null){
			String successMsg;
			if(menu.getId() == null){
				
				successMsg = "添加菜单成功！";
				accountMenuService.add(menu);
			}else{
				
				successMsg = "更新菜单成功！";
				//accountMenuService.update(menu);
				accountMenuService.updateByPrimaryKeySelective(menu);
				
			}
			attr.addFlashAttribute("msg",successMsg);			
		}
		
		return "redirect:list?baseDo.gid="+ menu.getGid();
	}

	@RequestMapping(value = "/delete")
	public String delete(RedirectAttributes attr,
			@ModelAttribute("menu") AccountMenu menu,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") AccountMenuQuery query
    		){
		
		logger.debug("do method");
		logger.debug("============" + menu.getId());
		accountMenuService.delete(menu);
		
    	// 重定向时带上查询参数，使查询条件不丢失
    	attr.addFlashAttribute("init",init);
		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);
		attr.addFlashAttribute("flag",flag);
		attr.addFlashAttribute("query",query);		
		
		return "redirect:list?baseDo.gid=" + query.getBaseDo().getGid();
	}

}
