/**
 * 
 */
package com.all_union.es.esalarm.service.impl.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.common.WebMemoryCache;
import com.all_union.es.esalarm.common.wxapi.WxMemoryCacheClient;
import com.all_union.es.esalarm.dao.flow.FlowTelecomOperatorDoMapper;
import com.all_union.es.esalarm.dao.resource.ResourceAreaDoMapper;
import com.all_union.es.esalarm.dao.wxcms.AccountDao;
import com.all_union.es.esalarm.pojo.flow.FlowTelecomOperatorDo;
import com.all_union.es.esalarm.pojo.resource.ResourceAreaDo;
import com.all_union.es.esalarm.pojo.wxcms.Account;
import com.all_union.es.esalarm.service.cache.CacheService;

/** 
 * @Description: 缓存预读
 * @author kwm
 * @date 2017年3月21日 上午11:06:14 
 * @version V1.0 
 * 
*/
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

	private static Logger logger = LogManager.getLogger(CacheServiceImpl.class);
	
	@Resource
	private FlowTelecomOperatorDoMapper FlowTelecomOperatorDao;
	
	@Resource
	private ResourceAreaDoMapper resourceAreaDao;
	
	@Resource
	private AccountDao accountDao;
	/* (non-Javadoc)
	 * @see com.all_union.es.esalarm.service.cache.CacheService#loadCache()
	 */
	@Override
	@PostConstruct //初始化
	public void loadCache() {
		
		logger.debug("excute...");		
		
		// 缓存运营商
		List<FlowTelecomOperatorDo> tpList = this.FlowTelecomOperatorDao.listAll();		
		WebMemoryCache.putCache(WebConstants.CACHE_TP_LIST, tpList);
		
		// 缓存省级行政区域
		List<ResourceAreaDo> provinceList = this.resourceAreaDao.listAllProvince();
		WebMemoryCache.putCache(WebConstants.CACHE_PROVINCE_LIST, provinceList);	
		
		// 微信相关缓存预读
		Account account = accountDao.getSingleAccount();
		WxMemoryCacheClient.addMpAccount(account);
	
		
	}


}
