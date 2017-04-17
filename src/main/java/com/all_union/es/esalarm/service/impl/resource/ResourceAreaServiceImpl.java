/**
 * 
 */
package com.all_union.es.esalarm.service.impl.resource;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.all_union.es.esalarm.dao.resource.ResourceAreaDoMapper;
import com.all_union.es.esalarm.pojo.resource.ResourceAreaDo;
import com.all_union.es.esalarm.service.resource.ResourceAreaService;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月19日 下午7:11:42 
 * @version V1.0 
 * 
*/
@Service("resourceAreaService")
//标注@Transactional的类和方法将具有事务性 
@Transactional
public class ResourceAreaServiceImpl implements ResourceAreaService {

	private static Logger logger = LogManager.getLogger(ResourceAreaServiceImpl.class);
	
	@Resource
	private ResourceAreaDoMapper resourceAreaDao;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.resource.ResourceAreaService#listAllProvince()
	 */
	@Override
	public List<ResourceAreaDo> listAllProvince() {
		logger.debug("excute...");
		return this.resourceAreaDao.listAllProvince();
		
	}

}
