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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.all_union.es.esalarm.common.util.PropertiesConfigUtil;
import com.all_union.es.esalarm.common.util.UploadUtil;
import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.wxcms.MsgBase;
import com.all_union.es.esalarm.pojo.wxcms.MsgNews;
import com.all_union.es.esalarm.pojo.wxcms.MsgNewsQuery;
import com.all_union.es.esalarm.pojo.wxcms.MsgText;
import com.all_union.es.esalarm.pojo.wxcms.MsgTextQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.wxcms.MsgBaseService;
import com.all_union.es.esalarm.service.wxcms.MsgNewsService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;
/**
 * 
 */

@Controller
@RequestMapping("/background/wxcms/msgnews")
public class MsgNewsCtrl extends BaseController{

	private static Logger logger = LogManager.getLogger(MsgNewsCtrl.class);
	
	@Autowired
	private MsgNewsService msgNewsService;
	
	@Autowired
	private MsgBaseService msgBaseService;
	
//	@RequestMapping(value = "/getById")
//	public ModelAndView getById(String id){
//		msgNewsService.getById(id);
//		return new ModelAndView();
//	}

	// 图文消息列表
	@RequestMapping(value = "/list")
	public  String list(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") MsgNewsQuery query
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
				query.setBaseDo(new MsgNews());
			
		}
		else{
			// 设置查询条件为全部
			query = new MsgNewsQuery();
			query.setBaseDo(new MsgNews());
			
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
		
		List<MsgNews> msgNewsList = msgNewsService.listMsgNewsByQuery(query);
		
		model.addAttribute("msgNewsList", msgNewsList);
		
		model.addAttribute("query", query);		
		model.addAttribute("curPage", query.getCurrentPage());
		
		return "tiles-msgnewsList";

	}

	@RequestMapping(value = "/modify")
	public String modify(Model model,@ModelAttribute("id") String id){
		logger.debug("do method");
		
		if(StringUtil.isBlank(id)){
			// 增加页面
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "图文消息新增");
		}
		else{
			// 设置页面标题栏
			model.addAttribute("bodyTitle", "图文消息编辑");
			
			MsgNews msgNews = msgNewsService.getById(id);
			MsgBase msgBase = msgBaseService.getById(msgNews.getBaseId().toString());
			
			model.addAttribute("msgNews", msgNews);
			model.addAttribute("msgBase", msgBase);
		}		
		
		return "tiles-msgnewsModify";

	}

	@RequestMapping(value = "/modifyAction")
	public ModelAndView modifyAction(HttpServletRequest request,MsgNews msgNews,@RequestParam(value="imageFile",required=false)MultipartFile file){
//		String contextPath = SpringFreemarkerContextPathUtil.getBasePath(request);
//		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
		
		String url = this.basePath;
		String realPath = request.getSession().getServletContext().getRealPath("/");
		
		//读取配置文上传件的路径 若有nginx代理，或需要配置upload.properties
		if(PropertiesConfigUtil.getProperty("upload.properties","upload.path") != null){
			realPath = PropertiesConfigUtil.getProperty("upload.properties","upload.path").toString();
		}
		
		if(file != null && file.getSize() > 0){
			String tmpPath = UploadUtil.doUpload(realPath,file);//上传文件，上传文件到 /res/upload/ 下
			msgNews.setPicpath(url + tmpPath);
		}else{
			if(msgNews.getId() != null){//更新
				msgNews.setPicpath(msgNewsService.getById(msgNews.getId().toString()).getPicpath());
			}
		}
		
		if(!StringUtil.isBlank(msgNews.getFromurl())){
			String fromUrl = msgNews.getFromurl();
			if(!fromUrl.startsWith("http://")){
				msgNews.setFromurl("http://" + fromUrl);
			}
		}else{
			msgNews.setUrl(url + "/wxweb/msg/newsread");//设置微信访问的url
		}
		
		if(msgNews.getId() != null){//更新
			msgNewsService.update(msgNews);
		}else{
			msgNewsService.add(msgNews);
		}
		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(RedirectAttributes attr,
			@ModelAttribute("entity") MsgNews entity,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") MsgNewsQuery query
			
			){
		
		logger.debug("do method");
		
		// 目前只删除数据库记录，未删除对应的上传文件
		msgNewsService.delete(entity);
		
    	// 重定向时带上查询参数，使查询条件不丢失
    	attr.addFlashAttribute("init",init);
		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);
		attr.addFlashAttribute("flag",flag);
		attr.addFlashAttribute("query",query);		
		
		return new ModelAndView("redirect:list");
	}



}