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
import com.all_union.es.esalarm.pojo.flow.FlowRechargeRecordDo;
import com.all_union.es.esalarm.service.flow.FlowGoodsService;
import com.all_union.es.esalarm.service.flow.FlowOrderService;
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
public class TestMybatisFlowOrder {
	private static Logger logger = LogManager.getLogger(TestMybatisFlowOrder.class); 
	
	@Resource
	private FlowOrderService flowOrderService;
	
    @Test  
    public void test() { 
    	
    	String open_id = "1234567890AABBCCDD1234567890";
    	int idx = 10;
    	int pageSize = 10;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("open_id", open_id);
		map.put("idx", idx);
		map.put("pageSize", pageSize);
    	
    	List<FlowRechargeRecordDo> list = flowOrderService.listOrderByOpenIdWithGoodsInfo(map);
    	
    	for(FlowRechargeRecordDo rcd : list){
    		logger.info(rcd.getMobile() + "|" + rcd.getGoodsNames() + "|" + rcd.getGmtUpdate() + "|" +rcd.getStateDesc());
    	}
    	
    } 	

}
