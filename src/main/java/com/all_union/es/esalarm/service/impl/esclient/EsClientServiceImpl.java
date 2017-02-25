/**
 * 
 */
package com.all_union.es.esalarm.service.impl.esclient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.service.esclient.EsClientService;
import com.all_union.es.esclient.Registory;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年2月7日 下午4:03:22 
 * @version V1.0 
 * 
*/
@Service("esClientService")
public class EsClientServiceImpl implements EsClientService {

	/**
	 * 
	 */
	public EsClientServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.EsClientInitService#EsClientInit()
	 */
	@Override
	@PostConstruct //初始化
	public void init() {
		// 执行esclient.jar中的初始化流程
		Registory.init();
	}
	
	@Override  
    @PreDestroy  
    public void  destory(){  
        
    }	

}
