/**
 * 
 */
package com.all_union.es.esalarm.common;

/** 
 * @Description: 常量定义
 * @author kwm
 * @date 2017年2月19日 下午2:24:03 
 * @version V1.0 
 * 
*/
public class WebConstants {

	// =============================================================================
	/**
	 * 用户loginID
	 */
	public static final String LoginID_Cookie = "loginID";	
	// =============================================================================
	/**
	 * SnowflakeIdWorker 数据中心ID 0-31 可以理解为组ID
	 */
	public static final long ID_WORKER_DC_ID = 1L;
	/**
	 * SnowflakeIdWorker 服务ID 0-31 可以理解为机器ID
	 */
	public static final long ID_WORKER_WORKER_ID = 1L;
	/**
	 * 唯一 SnowflakeIdWorker 实例,用于生成不重复的long型ID
	 */
	public static final SnowflakeIdWorker ID_MAKER = new SnowflakeIdWorker(ID_WORKER_WORKER_ID,ID_WORKER_DC_ID);
	// =============================================================================
	/**
	 * 订单类型 流量订单
	 */
	public static final String ORDER_TYPE_FLOW = "01";
	/**
	 * 订单有效期时常 从创建的时刻算起，单位分钟
	 */
	public static final int ORDER_EXPIRE_MINUTES = 45;
	// =============================================================================
	/**
	 * 默认商户ID
	 */
	public static final long ORDER_TRANS_DEFAULT_MERCH_ID = 8000L;
	/**
	 * 默认的店铺ID
	 */
	public static final long ORDER_TRANS_DEFAULT_SHOP_ID = 80001001L;
	/**
	 * 公众号支付类型码
	 */
	public static final String ORDER_TRANS_TRANS_CODE_OPEN = "1001";
	/**
	 * 公众号支付类型名
	 */
	public static final String ORDER_TRANS_TRANS_NAME_OPEN = "微信公众号支付";
	
	// =============================================================================
	/**
	 *  测试用open_id
	 */
	public static final String OPEN_ID_TEST = "1234567890AABBCCDD1234567890";
}
