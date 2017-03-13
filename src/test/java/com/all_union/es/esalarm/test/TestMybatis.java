/**
 * 
 */
package com.all_union.es.esalarm.test;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.user.UserService;

//表示继承了SpringJUnit4ClassRunner类  
@RunWith(SpringJUnit4ClassRunner.class)   
//指定配置文件
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
/** 
 * @Description: 
 * @author kwm
 * @date 2017年1月19日 上午9:00:42 
 * @version V1.0 
 * 
*/
public class TestMybatis {

	private static Logger logger = LogManager.getLogger(TestMybatis.class);  

//	private ApplicationContext ac = null;  
    @Resource  
    private UserService userService = null;  
  
    // 直接读文件的方式还有问题，待调试
//	@Before  
//	public void before() {  
//		ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");  
//		userService = (UserService) ac.getBean("userService");  
//	}  
  
    @Test  
    public void test1() {  
        UserDo user = userService.getUserById(1);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    } 

}
