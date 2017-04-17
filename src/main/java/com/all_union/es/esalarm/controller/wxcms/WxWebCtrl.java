package com.all_union.es.esalarm.controller.wxcms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.all_union.es.esalarm.common.util.HttpRequestDeviceUtils;
import com.all_union.es.esalarm.common.wxapi.WxMemoryCacheClient;
import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsVO;
import com.all_union.es.esalarm.pojo.wxcms.page.Pagination;
import com.all_union.es.esalarm.service.wxcms.MsgNewsService;
import com.kwm.common.lang.StringUtil;

/**
 * 手机微信页面
 */
@Controller
@RequestMapping("/wxweb")
public class WxWebCtrl extends BaseController{
	
	@Autowired
	private MsgNewsService msgNewsService;
		
	@RequestMapping(value = "/msg/newsread")
	public ModelAndView newsread(HttpServletRequest request,String id){
		ModelAndView mv = new ModelAndView("wxweb/mobileNewsRead");
		MsgNews news = msgNewsService.getById(id);
		mv.addObject("news",news);
		
		if(!HttpRequestDeviceUtils.isMobileDevice(request)){
			mv.setViewName("/wxweb/pcNewsRead");
		}
		return mv;
	}
	
	@RequestMapping(value = "/msg/newsList")
	public ModelAndView pageWebNewsList(HttpServletRequest request,MsgNews searchEntity,Pagination<MsgNews> page){
		ModelAndView mv = new ModelAndView("wxweb/mobileNewsList");
		List<MsgNewsVO> pageList = msgNewsService.pageWebNewsList(searchEntity,page);
		mv.addObject("pageList", pageList);
		return mv;
	}
	
	@RequestMapping(value = "/jssdk")
	public ModelAndView jssdk(HttpServletRequest request,String api){
		ModelAndView mv = new ModelAndView("wxweb/jssdk");
		if(!StringUtil.isBlank(api)){
			mv.addObject("api", api);
		}
		return mv;
	}
	
	@RequestMapping(value = "/sendmsg")
	public ModelAndView sendmsg(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxweb/sendmsg");
		//拦截器已经处理了缓存,这里直接取
		String openid = WxMemoryCacheClient.getOpenid(request.getSession().getId());
		mv.addObject("openid", openid);
		return mv;
	}
	
	@RequestMapping(value = "/weui")
	public ModelAndView weui(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxweb/weui");
		//拦截器已经处理了缓存,这里直接取
		String openid = WxMemoryCacheClient.getOpenid(request.getSession().getId());
		mv.addObject("openid", openid);
		return mv;
	}
	
}

