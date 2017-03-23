/**
 * 
 */
package com.all_union.es.esalarm.controller.flow;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.all_union.es.esalarm.annotation.TokenAnnotation;
import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.controller.BaseController;
import com.all_union.es.esalarm.pojo.flow.FlowChannelDo;
import com.all_union.es.esalarm.pojo.flow.FlowChannelQuery;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsQuery;
import com.all_union.es.esalarm.reflect.ReflectUtil;
import com.all_union.es.esalarm.service.cache.CacheService;
import com.all_union.es.esalarm.service.flow.FlowChannelService;
import com.all_union.es.esalarm.service.flow.FlowGoodsService;
import com.all_union.es.esalarm.service.flow.FlowTelecomOperatorService;
import com.all_union.es.esalarm.service.resource.ResourceAreaService;
import com.kwm.common.convert.Convert;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: 流量商品
 * 使用@ModelAttribute标注参数，scope是session，不标注，scope是request
 * @ModelAttribute("goods") FlowGoodsDo goods：相当于在model中加入了goods对象，页面可以直接使用
 * @author kwm
 * @date 2017年3月19日 下午12:45:30 
 * @version V1.0 
 * 
*/
@Controller
@RequestMapping("/background/flow")
public class FlowGoodsController extends BaseController {

	private static Logger logger = LogManager.getLogger(FlowGoodsController.class);
	
	@Resource  
    private FlowGoodsService flowGoodsService;  
	
	@Resource 
	private FlowTelecomOperatorService flowTelecomOperatorService;
	
	@Resource
	private ResourceAreaService resourceAreaService;
	
	@Resource
	private CacheService cacheService;
	
	@Resource
	private FlowChannelService flowChannelService;
	
	
	// 自定义注解，需要拦截重复提交的方法上如此设置
	@TokenAnnotation(needSaveToken = true) 
	@RequestMapping(value = "/flowGoodsModify")
	//public String modifyFlowGoods(@ModelAttribute("goods") FlowGoodsDo goods,@ModelAttribute("action") String action){
	//public String modifyFlowGoods(@ModelAttribute FlowGoodsDo goods,@ModelAttribute String action){
	public String modifyFlowGoods(RedirectAttributes attr,FlowGoodsDo goods,String action) throws Throwable{
		
		if(StringUtil.isBlank(action)){
			throw new Exception("参数错误");
		}
		// 成功消息
		String successMsg = "";
		// 替换""为null
		ReflectUtil.replaceFieldString(goods,"",null);

		// 当前时间
		Date now = new Date();
		if(action.equals(WebConstants.FORM_ACTION_ADD)){
			// 增加 默认有效期是null，即不限时间						
			goods.setGmtCreate(now);
			goods.setGmtUpdate(now);
			
			flowGoodsService.insertSelective(goods);
			
			successMsg = "添加商品成功！";
		}
		else{
			// 更新 默认有效期是null，即不限时间
			goods.setGmtUpdate(now);
			
			flowGoodsService.updateByPrimaryKeySelective(goods);
			
			successMsg = "更新商品成功！";
		}
		
		attr.addFlashAttribute("msg",successMsg);
		return "redirect:flowGoodsList";
		
	}
	
	@RequestMapping(value = "/flowGoodsAdd")
	public String addFlowGoodsTiles(Model model){
		logger.debug("do addFlowGoodsTiles method");
		
		// 设置页面标题栏(与update过程共用同一页面)
		model.addAttribute("bodyTitle", "流量商品新增");
		// 设置action类型
		model.addAttribute("formaction", WebConstants.FORM_ACTION_ADD);
		//-----------------------------------
    	// 运营商信息
    	model.addAttribute(WebConstants.CACHE_TP_LIST, cacheService.getCache("tpList"));
		    	
    	// 省级行政区域信息
    	model.addAttribute(WebConstants.CACHE_PROVINCE_LIST, cacheService.getCache("provinceList"));
		
    	//-----------------------------------
    	// 渠道信息 - 所有渠道
    	FlowChannelQuery channelQuery = new FlowChannelQuery();
    	// 查询所有记录时，将pageSize设置到最大
    	channelQuery.setPageSize(Integer.MAX_VALUE);
    	List<FlowChannelDo> channelList = flowChannelService.listChannelByQuery(new FlowChannelQuery());
    	model.addAttribute("channelList",channelList);		
		
		return "tiles-flowGoodsAdd"; 
	}
	
	@RequestMapping(value = "/flowGoodsUpdate")
	public String updateFlowGoodsTiles(Model model,@ModelAttribute("id") String id){
		logger.debug("do updateFlowGoodsTiles method");
		
		// 设置页面标题栏
		model.addAttribute("bodyTitle", "流量商品编辑");
		// 设置action类型
		model.addAttribute("formaction", WebConstants.FORM_ACTION_UPDATE);
		
		// 根据ID查询出商品信息
		FlowGoodsDo goods = flowGoodsService.selectByPrimaryKey(Convert.asLong(StringUtil.trim(id)));
		
		model.addAttribute("goods", goods);

		//-----------------------------------
    	// 运营商信息
    	model.addAttribute(WebConstants.CACHE_TP_LIST, cacheService.getCache("tpList"));
		    	
    	// 省级行政区域信息
    	model.addAttribute(WebConstants.CACHE_PROVINCE_LIST, cacheService.getCache("provinceList"));
		
    	//-----------------------------------
    	// 渠道信息 - 所有渠道
    	FlowChannelQuery channelQuery = new FlowChannelQuery();
    	// 查询所有记录时，将pageSize设置到最大
    	channelQuery.setPageSize(Integer.MAX_VALUE);
    	List<FlowChannelDo> channelList = flowChannelService.listChannelByQuery(new FlowChannelQuery());
    	model.addAttribute("channelList",channelList);
    	
		return "tiles-flowGoodsUpdate"; 
	}	
	
    //@RequestMapping(value = "/userList", method = RequestMethod.POST)
    @RequestMapping(value = "/flowGoodsDelete")
    public String deleteFlowGoods(RedirectAttributes attr,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") FlowGoodsQuery query,
    		@ModelAttribute("goodsID") Long goodsID
    		){  
    	
    	logger.debug("do listFlowGoodsTiles method"); 
    	
    	// 删除记录
    	flowGoodsService.deleteByPrimaryKey(goodsID);
    	
    	// 重定向时带上查询参数，使查询条件不丢失
    	attr.addFlashAttribute("init",init);
		attr.addFlashAttribute("goPage",goPage);
		attr.addFlashAttribute("curPage",curPage);
		attr.addFlashAttribute("flag",flag);
		attr.addFlashAttribute("query",query);
    	   	
    	return "redirect:flowGoodsList";
    }	

	
    //@RequestMapping(value = "/userList", method = RequestMethod.POST)
    @RequestMapping(value = "/flowGoodsList")
    public String listFlowGoodsTiles(Model model,
    		@ModelAttribute("init") String  init,
    		@ModelAttribute("goPage") String goPage,
    		@ModelAttribute("curPage") String curPage,
    		@ModelAttribute("flag") String flag,
    		@ModelAttribute("query") FlowGoodsQuery query
    		){  
    	
    	logger.debug("do listFlowGoodsTiles method"); 
    	
		// 转到的页数变量
		String strGoPage = null;
		// 非init标识，则获得查询条件
		if(!Convert.asBoolean(init)){
			
			//处理参数 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
			ReflectUtil.replaceFieldString(query,"",null);
			
		}
		else{
			// 设置查询条件为全部
			query = new FlowGoodsQuery();
			
		}		
		// 每页记录数
		//query.setPageSize(5); 
		
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
		
		List<FlowGoodsDo> goodsList = this.flowGoodsService.listGoodsByQuery(query);
		model.addAttribute("goodsList", goodsList);
		
		model.addAttribute("query", query);		
		model.addAttribute("curPage", query.getCurrentPage());
		
    	//-----------------------------------
    	// 运营商信息
    	model.addAttribute(WebConstants.CACHE_TP_LIST, cacheService.getCache("tpList"));
		    	
    	// 省级行政区域信息
    	model.addAttribute(WebConstants.CACHE_PROVINCE_LIST, cacheService.getCache("provinceList"));
    	
    	//-----------------------------------
    	// 渠道信息 - 所有渠道
    	FlowChannelQuery channelQuery = new FlowChannelQuery();
    	// 查询所有记录时，将pageSize设置到最大
    	channelQuery.setPageSize(Integer.MAX_VALUE);    	
    	List<FlowChannelDo> channelList = flowChannelService.listChannelByQuery(channelQuery);
    	model.addAttribute("channelList",channelList);
    	
    	return "tiles-flowGoodsList"; 
    }	
    
}
