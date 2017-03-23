/**
 * 
 */
package com.all_union.es.esalarm.service.impl.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.dao.flow.FlowTelecomOperatorDoMapper;
import com.all_union.es.esalarm.dao.resource.ResourceAreaDoMapper;
import com.all_union.es.esalarm.pojo.flow.FlowTelecomOperatorDo;
import com.all_union.es.esalarm.pojo.resource.ResourceAreaDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月21日 上午11:06:14 
 * @version V1.0 
 * 
*/
@Service("cacheService")
public class CacheServiceImpl implements com.all_union.es.esalarm.service.cache.CacheService {

	private static Logger logger = LogManager.getLogger(CacheServiceImpl.class);
	
	@Resource
	private FlowTelecomOperatorDoMapper FlowTelecomOperatorDao;
	
	@Resource
	private ResourceAreaDoMapper resourceAreaDao;
	
	/**
	 * 缓存
	 */
	private Map<String,Object> cache;
	
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.cache.CacheService#loadCache()
	 */
	@Override
	@PostConstruct //初始化
	public void loadCache() {
		
		logger.debug("excute...");
		
		if(null == cache)
			cache = new ConcurrentHashMap<String,Object>();
		
		// 缓存运营商
		List<FlowTelecomOperatorDo> tpList = this.FlowTelecomOperatorDao.listAll();		
		cache.put(WebConstants.CACHE_TP_LIST, tpList);
		
		// 缓存省级行政区域
		List<ResourceAreaDo> provinceList = this.resourceAreaDao.listAllProvince();
		cache.put(WebConstants.CACHE_PROVINCE_LIST, provinceList);		
		
	}

	@Override
	public Object getCache(String key){
		logger.debug("excute...");
		return cache.get(key);
	}
}
