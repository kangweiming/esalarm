/**
 * 
 */
package com.all_union.es.esalarm.pojo.flow;

import java.util.List;

/** 
 * @Description: 流量充值记录
 * @author kwm
 * @date 2017年3月16日 下午5:49:27 
 * @version V1.0 
 * 
*/
public class FlowRechargeRecordDo extends FlowOrderDo {

	/**
	 * 订单商品列表
	 */
	private List<FlowGoodsDo> goodsList;


	public List<FlowGoodsDo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<FlowGoodsDo> goodsList) {
		this.goodsList = goodsList;
	}

	/**
	 * 组合流量商品名
	 * @return  全国30M 本地100M
	 */
	public String getGoodsNames() {
		if(null == goodsList || goodsList.size() ==0)
			return null;
		
		StringBuffer desc = new StringBuffer();
		
		for(FlowGoodsDo goods : goodsList){
			
			if(goods.getGoodsProvinceId() == 0)
				desc.append("全国");
			else
				desc.append("本地");
			desc.append(goods.getGoodsName());
			
			desc.append(" ");
			
		}
		return desc.toString().trim();
	}

	
	
}
