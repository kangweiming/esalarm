/**
 * 
 */
package com.all_union.es.esalarm.service.flow;

import java.util.List;
import java.util.Map;

import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月5日 上午11:21:29 
 * @version V1.0 
 * 
*/
public interface FlowGoodsService {


	/**
	 * 根据运营商名称和所属省的简称查询价格最低的流量包，按流量包名称和省代码group by
	 * @param tpName 运营商名称 如 tpName = 移动
	 * @param provinceShortName 省简称 如provinceShortName = 辽宁
	 * @return 返回指定省市和运营商的价格最低的流量包list，每种流量包做多包含
     * 一组（本地+全国）流量包，如30M规格的流量包，最多有两个（本地+全国）。
     * 如果流量包规格不存在，那不会返回
	 */
    List<FlowGoodsDo> listGoodsByTPNameAndProvinceName(String tpName,String provinceShortName);
}
