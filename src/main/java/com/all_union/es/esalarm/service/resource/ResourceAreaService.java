/**
 * 
 */
package com.all_union.es.esalarm.service.resource;

import java.util.List;

import com.all_union.es.esalarm.pojo.resource.ResourceAreaDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月19日 下午7:11:03 
 * @version V1.0 
 * 
*/
public interface ResourceAreaService {
	/**
     * 查询所有升级行政区域信息
     * @return
     */
    List<ResourceAreaDo> listAllProvince();
}
