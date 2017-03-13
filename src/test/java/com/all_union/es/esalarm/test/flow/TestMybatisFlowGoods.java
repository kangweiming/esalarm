/**
 * 
 */
package com.all_union.es.esalarm.test.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.service.flow.FlowGoodsService;
import com.kwm.common.collection.Collections;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月5日 上午11:42:27 
 * @version V1.0 
 * 
*/
//表示继承了SpringJUnit4ClassRunner类  
@RunWith(SpringJUnit4ClassRunner.class)   
//指定配置文件
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMybatisFlowGoods {
	private static Logger logger = LogManager.getLogger(TestMybatisFlowGoods.class); 
	
	@Resource
	private FlowGoodsService flowGoodsService;
	
    @Test  
    public void test1() { 
    	
    	String tpName="移动";
    	String provinceShortName = "辽宁";
    	
    	List<FlowGoodsDo> list = flowGoodsService.listGoodsByTPNameAndProvinceName(tpName, provinceShortName);
    	
    	
    	
    	// 按照goodsName进行分组存储
    	Map<String,List<FlowGoodsDo>> map = new HashMap<String,List<FlowGoodsDo>>();
    	
    	try {
			Collections.listGroup2Map(list, map, FlowGoodsDo.class.getDeclaredMethod("getGoodsName"));
		}
    	catch (Exception e) {
			logger.error(e);
		}
    	logger.info(JSON.toJSONString(map));
    	
    } 	

}
