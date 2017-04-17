/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.flow.FlowChannelDoMapper;
import com.all_union.es.esalarm.dao.flow.FlowGoodsDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowChannelDo;
import com.all_union.es.esalarm.pojo.flow.FlowChannelQuery;
import com.all_union.es.esalarm.service.flow.FlowChannelService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月21日 下午1:30:50 
 * @version V1.0 
 * 
*/
@Service("flowChannelService")
//标注@Transactional的类和方法将具有事务性 
@Transactional
public class FlowChannelServiceImpl implements FlowChannelService {

	private static Logger logger = LogManager.getLogger(FlowChannelServiceImpl.class);
	
	@Resource
	private FlowChannelDoMapper flowChannelDao;
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowChannelService#listChannelByQuery(com.all_union.es.esalarm.pojo.flow.FlowChannelQuery)
	 */
	@Override
	public List<FlowChannelDo> listChannelByQuery(FlowChannelQuery query) {
		logger.debug("excute...");
		
		int count = this.flowChannelDao.countChannelByQuery(query);
		
		if(count > 0){
			query.setTotalItem(count);
			return this.flowChannelDao.listChannelByQuery(query);
		}
		else{
			query.setTotalItem(0);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowChannelService#countChannelByQuery(com.all_union.es.esalarm.pojo.flow.FlowChannelQuery)
	 */
	@Override
	public int countChannelByQuery(FlowChannelQuery query) {
		logger.debug("excute...");
		return this.flowChannelDao.countChannelByQuery(query);
	}

}
