/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.flow.FlowOrderDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDoKey;
import com.all_union.es.esalarm.pojo.flow.FlowRechargeRecordDo;
import com.all_union.es.esalarm.service.flow.FlowOrderService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午1:40:17 
 * @version V1.0 
 * 
*/
@Service("flowOrderService")
//标注@Transactional的类和方法将具有事务性 
@Transactional
public class FlowOrderServiceImpl implements FlowOrderService {

	private static Logger logger = LogManager.getLogger(FlowOrderServiceImpl.class);
	
	@Resource
	private FlowOrderDoMapper flowOrderDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowOrderService#insertSelective(com.all_union.es.esalarm.pojo.flow.FlowOrderDo)
	 */
	@Override
	public int insertSelective(FlowOrderDo record) {
		logger.debug("excute...");
		
		return this.flowOrderDao.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowOrderService#selectByOrderNo(com.all_union.es.esalarm.pojo.flow.FlowOrderDoKey)
	 */
	@Override
	public FlowOrderDo selectByOrderNo(FlowOrderDoKey key) {
		logger.debug("excute...");
		return this.flowOrderDao.selectByOrderNo(key);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowOrderService#updateByOrderNoSelective(com.all_union.es.esalarm.pojo.flow.FlowOrderDo)
	 */
	@Override
	public int updateByOrderNoSelective(FlowOrderDo record) {
		logger.debug("excute...");
		return this.flowOrderDao.updateByOrderNoSelective(record);
	}

	@Override
	public List<FlowRechargeRecordDo> listOrderByOpenIdWithGoodsInfo(Map<String, Object> map) {
		logger.debug("excute...");
		return this.flowOrderDao.listOrderByOpenIdWithGoodsInfo(map);
	}

}
