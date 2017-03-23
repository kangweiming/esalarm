/**
 * 
 */
package com.all_union.es.esalarm.service.cache;

/** 
 * @Description: 这里使用ConcurrentHashMap做一个简单的内存缓存
 * 若缓存的数据量级增加，可以考虑使用spring-ehcache、redis等缓存方案
 * @author kwm
 * @date 2017年3月21日 上午11:04:09 
 * @version V1.0 
 * 
*/
public interface CacheService {
	
	public void loadCache();
	
	public Object getCache(String key);
}
