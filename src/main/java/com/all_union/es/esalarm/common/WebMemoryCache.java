/**
 * 
 */
package com.all_union.es.esalarm.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 
 * @Description: 这里使用ConcurrentHashMap做一个简单的内存缓存
 * 若缓存的数据量级增加，可以考虑使用spring-ehcache、redis等缓存方案
 * 这里的缓存不包括微信相关的cache
 * @author kwm
 * @date 2017年3月27日 下午3:40:23 
 * @version V1.0 
 * 
*/
public class WebMemoryCache {
	/**
	 * 缓存
	 */
	private static Map<String,Object> cache = new ConcurrentHashMap<String,Object>();
	
	public static Object getCache(String key){

		return cache.get(key);
	}
	
	public static void putCache(String key,Object obj){
		cache.put(key, obj);
	}
}
