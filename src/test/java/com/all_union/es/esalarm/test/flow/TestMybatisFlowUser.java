/**
 * 
 */
package com.all_union.es.esalarm.test.flow;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.pojo.flow.FlowUserDo;
import com.all_union.es.esalarm.service.flow.FlowUserService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午2:45:26 
 * @version V1.0 
 * 
*/
//表示继承了SpringJUnit4ClassRunner类  
@RunWith(SpringJUnit4ClassRunner.class)   
//指定配置文件
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestMybatisFlowUser {

	private static Logger logger = LogManager.getLogger(TestMybatisFlowUser.class);
	
	@Resource
	private FlowUserService flowUserService;
	
	@Test  
	public void test(){
		FlowUserDo flowUser = new FlowUserDo();
		// open_id
		flowUser.setOpenId(WebConstants.OPEN_ID_TEST);
		// nick_name
		flowUser.setNickName("月下独酌");
		// sex
		flowUser.setSex("1");
		// gmt_create and gmt_update
		// 获取当前时间
    	Date now = new Date();
    	flowUser.setGmtCreate(now);
    	flowUser.setGmtUpdate(now);
		
    	// 插入记录
    	int flag = -1;
    	
    	try{
    		flag = flowUserService.insertSelective(flowUser);
    	}
    	catch(Exception ex){
    		logger.error(ex.getMessage(),ex);
    	}
    	
    	if(flag != 1)
    		logger.info("insert flow_user failed");
    	else
    		logger.info("insert flow_user success");
	}
}
