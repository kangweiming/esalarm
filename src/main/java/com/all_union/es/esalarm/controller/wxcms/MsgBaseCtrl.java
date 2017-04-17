package com.all_union.es.esalarm.controller.wxcms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.service.wxcms.MsgBaseService;
import com.all_union.es.esalarm.service.wxcms.MsgNewsService;
import com.all_union.es.esalarm.service.wxcms.MsgTextService;

/**
 * 
 */

@Controller
@RequestMapping("/background/wxcms/msgbase")
public class MsgBaseCtrl extends BaseController{

	@Autowired
	private MsgBaseService msgBaseService;
	
	@Autowired
	private MsgNewsService newsService;
	
	@Autowired
	private MsgTextService textService;

	@RequestMapping(value = "/getById")
	public ModelAndView getById(String id){
		msgBaseService.getById(id);
		return new ModelAndView();
	}

	@RequestMapping(value = "/listForPage")
	public  ModelAndView listForPage(@ModelAttribute MsgBase searchEntity){
		return new ModelAndView();
	}

	@RequestMapping(value = "/toMerge")
	public ModelAndView toMerge(MsgBase entity){
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/menuMsgs")
	public ModelAndView menuMsgs(){
		ModelAndView mv = new ModelAndView("wxcms/menuMsgs");
		//获取所有的图文消息;
		List<MsgNews> newsList = newsService.listForPage(new MsgNews());
		//获取所有的文本消息;
		List<MsgText> textList = textService.listForPage(new MsgText());
		
		mv.addObject("newsList", newsList);
		mv.addObject("textList", textList);
		
		return mv;
	}

	@RequestMapping(value = "/doMerge")
	public ModelAndView doMerge(MsgBase entity){
		msgBaseService.add(entity);
		return new ModelAndView();
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(MsgBase entity){
		msgBaseService.delete(entity);
		return new ModelAndView();
	}



}