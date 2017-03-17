/**
 * 
 */
package com.all_union.es.esalarm.controller.rest.flow;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.service.flow.FlowGoodsService;
import com.kwm.common.collection.Collections;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: restful api controller for flow goods
 * 同样效果的三种不同的实现方式：
 * 1.GET 参数在url中 @RequestParam
 * 2.POST 参数在body中 @RequestBody String
 * 3.POST 参数在body中 @RequestBody JSONObject
 * @author kwm
 * @date 2017年3月5日 下午12:23:40 
 * @version V1.0 
 * 
*/
@RestController
@RequestMapping("/rest/flow/")
public class FlowGoodsRestController {

	private static Logger logger = LogManager.getLogger(FlowGoodsRestController.class);
	
	@Autowired
	private FlowGoodsService flowGoodsService;
	
	/**
	 * http://localhost:8080/esalarm/rest/flow/goods/ 
	 * 
	 * @param json 参数为嵌套型json对象
	 * {"marked":"flowgoods","code":"10001","jsonStr":"{\"tpName\":\"移动\",\"provinceShortName\":\"辽宁\"}","version":"1.0"}
	 * @return
	 */
	@RequestMapping(value = "goods/", method = RequestMethod.POST)
	public ResponseEntity<Map<String,List<FlowGoodsDo>>> listFlowGoods_POST(
			@RequestBody JSONObject json
			){
		logger.debug("request /rest/flow/goods/ with json body");
		
		// 获得参数部分json
		JSONObject jsonparam = json.getJSONObject("jsonStr");
		
		String tpName = jsonparam.getString("tpName");
		String provinceShortName = jsonparam.getString("provinceShortName");
		
		// 参数检查
		if(StringUtil.isBlank(tpName) || StringUtil.isBlank(provinceShortName))
			return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(HttpStatus.BAD_REQUEST);
		
    	List<FlowGoodsDo> list = flowGoodsService.listGoodsByTPNameAndProvinceName(tpName, provinceShortName);
    	
    	if (list.isEmpty()) {
			return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(HttpStatus.NOT_FOUND);
		}
    	
    	// 按照goodsName进行分组存储
    	Map<String,List<FlowGoodsDo>> map = new LinkedHashMap<String,List<FlowGoodsDo>>();
    	
    	try {
    		// 按照goodsName属性进行分组
			Collections.listGroup2Map(list, map, FlowGoodsDo.class.getDeclaredMethod("getGoodsName"));
		}
    	catch (Exception e) {
			logger.error("list分组错误",e);
		}
    	logger.info(JSON.toJSONString(map));
    	
		return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(map, HttpStatus.OK);
	}
	
	// http://localhost:8080/esalarm/rest/flow/goods/?tpName=移动&provinceShortName=辽宁
	@RequestMapping(value = "goods/", method = RequestMethod.GET)
	public ResponseEntity<Map<String,List<FlowGoodsDo>>> listFlowGoods_GET(
			@RequestParam(value = "tpName",required = true) String tpName,
			@RequestParam(value = "provinceShortName",required = true) String provinceShortName
			){
		    	
		logger.debug("request /rest/flow/goods/ with parameters");
		
		// 参数检查
		if(StringUtil.isBlank(tpName) || StringUtil.isBlank(provinceShortName))
			return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(HttpStatus.BAD_REQUEST);
		
    	List<FlowGoodsDo> list = flowGoodsService.listGoodsByTPNameAndProvinceName(tpName, provinceShortName);
    	
    	if (list.isEmpty()) {
			return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
    	
    	// 按照goodsName进行分组存储,使用有序的Map
    	Map<String,List<FlowGoodsDo>> map = new LinkedHashMap<String,List<FlowGoodsDo>>();
    	
    	try {
    		// 按照goodsName属性进行分组
			Collections.listGroup2Map(list, map, FlowGoodsDo.class.getDeclaredMethod("getGoodsName"));
		}
    	catch (Exception e) {
			logger.error("list分组错误",e);
		}
    	
		return new ResponseEntity<Map<String,List<FlowGoodsDo>>>(map, HttpStatus.OK);
	}
}
