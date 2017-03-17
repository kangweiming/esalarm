/**
 * 
 */
package com.all_union.es.esalarm.service.impl.flow;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.dao.flow.FlowUserDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowUserDo;
import com.all_union.es.esalarm.service.flow.FlowUserService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月15日 下午2:36:54 
 * @version V1.0 
 * 
*/
@Service("flowUserService")
public class FlowUserServiceImpl implements FlowUserService {

	private static Logger logger = LogManager.getLogger(FlowUserServiceImpl.class);
	
	@Resource
	private FlowUserDoMapper flowUserDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#deleteByOpenId(java.lang.String)
	 */
	@Override
	public int deleteByOpenId(String openId) {
		logger.debug("excute...");
		return this.flowUserDao.deleteByOpenId(openId);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#insert(com.all_union.es.esalarm.pojo.flow.FlowUserDo)
	 */
	@Override
	public int insert(FlowUserDo record) {
		logger.debug("excute...");
		return this.flowUserDao.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#insertSelective(com.all_union.es.esalarm.pojo.flow.FlowUserDo)
	 */
	@Override
	public int insertSelective(FlowUserDo record) {
		logger.debug("excute...");
		return this.flowUserDao.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#selectByOpenId(java.lang.String)
	 */
	@Override
	public FlowUserDo selectByOpenId(String openId) {
		logger.debug("excute...");
		return this.flowUserDao.selectByOpenId(openId);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#updateByOpenIdSelective(com.all_union.es.esalarm.pojo.flow.FlowUserDo)
	 */
	@Override
	public int updateByOpenIdSelective(FlowUserDo record) {
		logger.debug("excute...");
		return this.flowUserDao.updateByOpenIdSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.flow.FlowUserService#updateByOpenId(com.all_union.es.esalarm.pojo.flow.FlowUserDo)
	 */
	@Override
	public int updateByOpenId(FlowUserDo record) {
		logger.debug("excute...");
		return this.flowUserDao.updateByOpenId(record);
	}

}
