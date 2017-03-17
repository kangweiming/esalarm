/**
 * 
 */
package com.all_union.es.esalarm.pojo.flow;

/** 
 * @Description: 订单状态枚举类
 * @author kwm
 * @date 2017年3月14日 下午5:32:11 
 * @version V1.0 
 * 
*/
public enum FlowOrderState {
	
	SUCCESS		("SUCCESS","支付成功"),
	REFUND		("REFUND","退款成功"),
	NOTPAY		("NOTPAY","未支付"),
	CLOSED		("CLOSED","已关闭"),
	REVOKED		("REVOKED","已撤销"),
	USERPAYING	("USERPAYING","支付中"),
	PAYERROR	("PAYERROR","支付失败"),
	REFUND_FAIL	("REFUND_FAIL","退款失败"),
	REVERSAL	("REVERSAL","冲正");
	
	private String state;
	private String desc;
	
	private FlowOrderState(String state,String desc){
		this.state = state;
		this.desc = desc;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}	
}
