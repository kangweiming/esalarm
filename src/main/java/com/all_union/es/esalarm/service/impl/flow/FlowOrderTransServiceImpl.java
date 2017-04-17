/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.flow.FlowOrderTransDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowOrderTransDo;
import com.all_union.es.esalarm.service.flow.FlowOrderTransService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午5:59:17 
 * @version V1.0 
 * 
*/
@Service("flowOrderTransService")
//标注@Transactional的类和方法将具有事务性 
@Transactional
public class FlowOrderTransServiceImpl implements FlowOrderTransService {

	private static Logger logger = LogManager.getLogger(FlowOrderTransServiceImpl.class);
	
	@Resource
	private FlowOrderTransDoMapper flowOrderTransDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowOrderTransService#insertSelective(com.all_union.es.esalarm.pojo.flow.FlowOrderTransDo)
	 */
	@Override
	public int insertSelective(FlowOrderTransDo record) {
		logger.debug("excute...");
		return this.flowOrderTransDao.insertSelective(record);
	}

}
