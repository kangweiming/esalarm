package com.all_union.es.esalarm.controller.wxcms;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.all_union.es.esalarm.common.util.PropertiesConfigUtil;
import com.all_union.es.esalarm.common.util.UploadUtil;
import com.all_union.es.esalarm.common.wxapi.WxMemoryCacheClient;
import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.dao.wxcms.AccountDao;
import com.all_union.es.esalarm.dao.wxcms.MsgNewsDao;
import com.all_union.es.esalarm.pojo.wxcms.Account;
import com.kwm.common.lang.StringUtil;

/**
 * 后台管理页面
 */

@Controller
@RequestMapping("/background/wxcms")
public class WxCmsCtrl extends BaseController{

	private static Logger logger = LogManager.getLogger(WxCmsCtrl.class);
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	MsgNewsDao msgNewsDao;
		
	@RequestMapping(value = "/urltoken")
	public ModelAndView urltoken(String save){
		ModelAndView mv = new ModelAndView("tiles-urltoken");
		List<Account> accounts = accountDao.listForPage(null);
		if(!CollectionUtils.isEmpty(accounts)){
			mv.addObject("account",accounts.get(0));
		}else{
			mv.addObject("account",new Account());
		}
//		mv.addObject("cur_nav", "urltoken");
		if(save != null){
			mv.addObject("successflag",true);
		}
		return mv;
	}
	
	@RequestMapping(value = "/getUrl")
	public ModelAndView getUrl(Model model,HttpServletRequest request ,@ModelAttribute Account account){
//		String path = SpringFreemarkerContextPathUtil.getBasePath(request);
//		String url = request.getScheme() + "://" + request.getServerName() + path + "/wxapi/" + account.getAccount()+"/message";
		String url = this.basePath + "/wxapi/" + account.getAccount()+"/message";
		
		if(account.getId() == null){//新增
			account.setUrl(url);
			account.setToken(UUID.randomUUID().toString().replace("-", ""));
			account.setCreatetime(new Date());
			accountDao.add(account);
		}else{//更新
			Account tmpAccount = accountDao.getById(account.getId().toString());
			tmpAccount.setUrl(url);
			tmpAccount.setAccount(account.getAccount());
			tmpAccount.setAppid(account.getAppid());
			tmpAccount.setAppsecret(account.getAppsecret());
			tmpAccount.setMsgcount(account.getMsgcount());
			accountDao.update(tmpAccount);
		}
		WxMemoryCacheClient.addMpAccount(account);
		return new ModelAndView("redirect:urltoken?save=true");
	}
	// kindeditor上传时调用
	@RequestMapping(value = "/ckeditorImage")
	public void ckeditorImage(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="imgFile",required=false)MultipartFile file){
//		String contextPath = SpringFreemarkerContextPathUtil.getBasePath(request);
//		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
		
		String url = this.basePath;
		String realPath = request.getSession().getServletContext().getRealPath("/");	
		// 返回信息json对象
		JSONObject obj = new JSONObject();
        
		//读取配置文上传件的路径 nginx代理或许需要下面这样判断
		if(PropertiesConfigUtil.getProperty("upload.properties","upload.path") != null){
			realPath = PropertiesConfigUtil.getProperty("upload.properties","upload.path").toString();
		}
		
		if(file != null && file.getSize() > 0){
			//上传文件，上传文件到WxConstants.java定义的目录下
			String tmpPath = UploadUtil.doUpload(realPath,file);
			obj.put("error", 0);
			obj.put("url", url + tmpPath);
		}
		
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			logger.debug("kindeditor上传文件出错",e);		
		}
	}
	
	
	//上传永久素材，这里以图文消息为例子
	@RequestMapping(value = "/toUploadMaterial")
	public  ModelAndView toUploadMaterial(String[] newIds){
		ModelAndView mv = new ModelAndView("wxcms/materialUpload");
		mv.addObject("cur_nav", "material");
		return mv;
	}
	
	//到生成二维码页面
	@RequestMapping(value = "/qrcode")
	public ModelAndView qrcode(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxcms/qrcode");
		mv.addObject("cur_nav", "qrcode");
		return mv;
	}
	
	//发送消息页面
	@RequestMapping(value = "/sendMsg")
	public ModelAndView sendMsg(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxcms/sendmsg");
		mv.addObject("cur_nav", "sendmsg");
		
		return mv;
	}
	
	//通过interceptor处理OAuth认证
	@RequestMapping(value = "/oauthInterceptor")
	public ModelAndView oauthInterceptor(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxcms/oauthInterceptor");
		mv.addObject("cur_nav", "oauthInterceptor");
		
		return mv;
	}
	
	//jssdk
	@RequestMapping(value = "/jssdk")
	public ModelAndView jssdk(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxcms/jssdk");
		mv.addObject("cur_nav", "jssdk");
		
		return mv;
	}
	
	//weui 微信网页开发样式库
	@RequestMapping(value = "/weui")
	public ModelAndView weui(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxcms/weui");
		mv.addObject("cur_nav", "weui");
		return mv;
	}
	
}
