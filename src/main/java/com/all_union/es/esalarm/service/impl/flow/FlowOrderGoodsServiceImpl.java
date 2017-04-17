/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.flow.FlowOrderGoodsDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowOrderGoodsDo;
import com.all_union.es.esalarm.service.flow.FlowOrderGoodsService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午4:32:02 
 * @version V1.0 
 * 
*/
@Service("flowOrderGoodsService")
//标注@Transactional的类和方法将具有事务性 
@Transactional
public class FlowOrderGoodsServiceImpl implements FlowOrderGoodsService {

	private static Logger logger = LogManager.getLogger(FlowOrderGoodsServiceImpl.class);
	
	@Resource
	private FlowOrderGoodsDoMapper flowOrderGoodsDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowOrderGoodsService#insertSelective(com.all_union.es.esalarm.pojo.flow.FlowOrderGoodsDo)
	 */
	@Override
	public int insertSelective(FlowOrderGoodsDo record) {
		logger.debug("excute...");
		return this.flowOrderGoodsDao.insertSelective(record);
	}

}
