/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.dao.flow.FlowTelecomOperatorDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowTelecomOperatorDo;
import com.all_union.es.esalarm.service.flow.FlowTelecomOperatorService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月19日 下午6:44:06 
 * @version V1.0 
 * 
*/
@Service("flowTelecomOperatorService")
public class FlowTelecomOperatorServiceImpl implements FlowTelecomOperatorService {

	private static Logger logger = LogManager.getLogger(FlowTelecomOperatorServiceImpl.class);
	
	@Resource
	private FlowTelecomOperatorDoMapper FlowTelecomOperatorDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowTelecomOperatorService#listAll()
	 */
	@Override
	public List<FlowTelecomOperatorDo> listAll() {
		logger.debug("excute...");
		
		return this.FlowTelecomOperatorDao.listAll();

	}

}
