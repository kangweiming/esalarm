package com.all_union.es.esalarm.controller.wxcms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.all_union.es.esalarm.common.wxapi.WxMemoryCacheClient;
import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.wxapi.ErrCode;
import com.all_union.es.esalarm.pojo.wxapi.MpAccount;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroup;
import com.all_union.es.esalarm.pojo.wxcms.AccountMenuGroupQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.wxapi.MyService;
import com.all_union.es.esalarm.service.wxcms.AccountMenuGroupService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;

/**
 * 
 * @Description: 菜单组管理
 * @author kwm
 * @date 2017年4月6日 上午11:10:30 
 * @version V1.0 
 *
 */
@Controller
@RequestMapping("/background/wxcms/accountMenuGroup")
public class AccountMenuGroupCtrl extends BaseController{

	private static Logger logger = LogManager.getLogger(AccountMenuGroupCtrl.class);
	
	@Autowired
	private AccountMenuGroupService accountMenuGroupService;
	
	@Autowired
	private MyService myService;
	
	@RequestMapping(value = "/getById")
	public ModelAndView getById(String id){
		ModelAndView mv = new ModelAndView("wxcms/accountMenuGroup");
		mv.addObject("entity",accountMenuGroupService.getById(id));
		return mv;
	}

	@RequestMapping(value = "/list")
	public String list(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") AccountMenuGroupQuery query
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
				query.setBaseDo(new AccountMenuGroup());
			
		}
		else{
			// 设置查询条件为全部
			query = new AccountMenuGroupQuery();
			query.setBaseDo(new AccountMenuGroup());
			
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
		
		List<AccountMenuGroup> menuGroupList = this.accountMenuGroupService.listMenuGroupByQuery(query);
				
		model.addAttribute("menuGroupList", menuGroupList);
		
		model.addAttribute("query", query);		
		model.addAttribute("curPage", query.getCurrentPage());		
				
		return "tiles-menuGroupList";		
		
	}

//	@RequestMapping(value = "/paginationEntity")
//	public  ModelAndView paginationEntity(AccountMenuGroup searchEntity , Pagination<AccountMenuGroup> pagination){
//		ModelAndView mv = new ModelAndView("wxcms/accountMenuGroupPagination");
//		pagination = accountMenuGroupService.paginationEntity(searchEntity,pagination);
//		mv.addObject("pagination",pagination);
//		mv.addObject("searchEntity",searchEntity);
//		mv.addObject("cur_nav", "menu");
//		return mv;
//	}

	@RequestMapping(value = "/modify")
	public String toMerge(Model model,@ModelAttribute("id") String id){
		
		logger.debug("do method");
		
		if(StringUtil.isBlank(id)){
			// 增加页面
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "菜单组新增");
		}
		else{
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "菜单组编辑");
			
			AccountMenuGroup menuGroup = accountMenuGroupService.getById(id);
			model.addAttribute("menuGroup", menuGroup);
		}		
		
		return "tiles-menuGroupModify";
	}

	@RequestMapping(value = "/modifyAction")
	public ModelAndView doMerge(RedirectAttributes attr,@ModelAttribute("entity") AccountMenuGroup entity){
		
		logger.debug("do method");
		
		String successMsg;
		if(entity.getId() == null){
			entity.setEnable(0);
			successMsg = "添加菜单组成功！";
			accountMenuGroupService.add(entity);
		}else{
			accountMenuGroupService.update(entity);
			successMsg = "更新菜单组成功！";
			
		}
		attr.addFlashAttribute("msg",successMsg);
		
		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(RedirectAttributes attr,
			@ModelAttribute("entity") AccountMenuGroup entity,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") AccountMenuGroupQuery query
			){
		
		logger.debug("do method");
		
		accountMenuGroupService.delete(entity);
		
    	// 重定向时带上查询参数，使查询条件不丢失
    	attr.addFlashAttribute("init",init);
		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);
		attr.addFlashAttribute("flag",flag);
		attr.addFlashAttribute("query",query);		
		
		return new ModelAndView("redirect:list");
	}

	/**
	 * 创建微信公众账号菜单
	 * @param request
	 * @param gid 菜单组id
	 * @return
	 */
	@RequestMapping(value = "/publishMenu")
	public String publishMenu(RedirectAttributes attr,HttpServletRequest request,String gid) {
		
		JSONObject rstObj = null;
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();
		if(mpAccount != null){
			rstObj = myService.publishMenu(gid,mpAccount);
			if(rstObj != null){//成功，更新菜单组
				if(rstObj.containsKey("menu_id")){
					attr.addFlashAttribute("msg","公众号菜单发布成功");
					return "redirect:list";
				}else if(rstObj.containsKey("errcode") && rstObj.getIntValue("errcode") == 0){
					attr.addFlashAttribute("msg","公众号菜单发布成功");
					return "redirect:list";
				}
			}
		}
		
		String msg = "创建菜单失败，请检查菜单：可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。";
		if(rstObj != null){
			msg += ErrCode.errMsg(rstObj.getIntValue("errcode"));
		}
		attr.addFlashAttribute("msg",msg);
		
		return "redirect:list";
	}
	
	/**
	 * 删除微信公众账号菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu")
	public String deleteMenu(RedirectAttributes attr,HttpServletRequest request) {
		JSONObject rstObj = null;
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
		if(mpAccount != null){
			rstObj = myService.deleteMenu(mpAccount);
			if(rstObj != null && rstObj.getIntValue("errcode") == 0){
				attr.addFlashAttribute("msg","删除公众号菜单成功");
				return "redirect:list";
			}
		}
		
		String msg = "删除菜单失败";
		if(rstObj != null){
			msg += ErrCode.errMsg(rstObj.getIntValue("errcode"));
		}
		attr.addFlashAttribute("msg",msg);
		return "redirect:list";
	}

}

