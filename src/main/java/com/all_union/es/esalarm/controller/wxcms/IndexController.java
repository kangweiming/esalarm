package com.all_union.es.esalarm.controller.wxcms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.all_union.es.esalarm.controller.BaseController;

@Controller
@RequestMapping()
public class IndexController extends BaseController{
	@RequestMapping(value = "/index")
	public ModelAndView index(){
		return new ModelAndView("index");
//		return new ModelAndView("redirect:/wxcms/urltoken.html");
	}
}
