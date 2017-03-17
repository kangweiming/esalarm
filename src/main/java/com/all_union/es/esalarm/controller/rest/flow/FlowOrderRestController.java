/**
 * 
 */
package com.all_union.es.esalarm.controller.rest.flow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.all_union.es.esalarm.common.WebConstants;
import com.all_union.es.esalarm.pojo.flow.FlowGoodsDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderDoKey;
import com.all_union.es.esalarm.pojo.flow.FlowOrderGoodsDo;
import com.all_union.es.esalarm.pojo.flow.FlowOrderState;
import com.all_union.es.esalarm.pojo.flow.FlowOrderTransDo;
import com.all_union.es.esalarm.pojo.flow.FlowRechargeRecordDo;
import com.all_union.es.esalarm.pojo.flow.FlowUserDo;
import com.all_union.es.esalarm.service.flow.FlowOrderGoodsService;
import com.all_union.es.esalarm.service.flow.FlowOrderService;
import com.all_union.es.esalarm.service.flow.FlowOrderTransService;
import com.all_union.es.esalarm.service.flow.FlowUserService;
import com.kwm.common.lang.DateUtil;
import com.kwm.common.lang.StringUtil;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月14日 下午9:31:20 
 * @version V1.0 
 * 
*/
@RestController
@RequestMapping("/rest/flow/")
public class FlowOrderRestController {
	private static Logger logger = LogManager.getLogger(FlowOrderRestController.class);
	
	@Autowired
	private FlowOrderService flowOrderService;
	
	@Autowired
	private FlowUserService flowUserService;
	
//	@Autowired
//	private FlowGoodsService flowGoodsService;
	
	@Autowired
	private FlowOrderGoodsService flowOrderGoodsService;
	
	@Autowired
	private FlowOrderTransService flowOrderTransService;
	
	/**
	 * 根据open_id查询充值记录
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "order/record/", method = RequestMethod.POST)
	public ResponseEntity<List<FlowRechargeRecordDo>> listRechargeRecord_POST(
			@RequestBody JSONObject json
			){
		logger.debug("request /rest/flow/order/record/ with json body");
		
		// 获得参数部分json
		JSONObject jsonStr = json.getJSONObject("jsonStr");
		
		// 解析并获得参数
		String open_id = jsonStr.getString("open_id");
		int idx = jsonStr.getIntValue("idx");
		int pageSize = jsonStr.getIntValue("pageSize");
		
		// 参数检查
		if(StringUtil.isBlank(open_id))
			return new ResponseEntity<List<FlowRechargeRecordDo>>(HttpStatus.BAD_REQUEST);
		
		// 设置查询参数
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("open_id", open_id);
		map.put("idx", idx);
		map.put("pageSize", pageSize);
    	// 查询充值记录
		List<FlowRechargeRecordDo> list = flowOrderService.listOrderByOpenIdWithGoodsInfo(map);
    	
    	if(null == list || list.size() == 0)
    		return new ResponseEntity<List<FlowRechargeRecordDo>>(HttpStatus.NO_CONTENT);
    	   	
		return new ResponseEntity<List<FlowRechargeRecordDo>>(list,HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "order/update/", method = RequestMethod.POST)
	public ResponseEntity<FlowOrderDo> updateOrder_POST(
			@RequestBody JSONObject json
			){
		logger.debug("request /rest/flow/order/update/ with json body");
		
		// 获得参数部分json
		JSONObject jsonStr = json.getJSONObject("jsonStr");
		
		// 解析并获得参数
		Long orderNo = jsonStr.getLong("orderNo");
		String open_id = jsonStr.getString("open_id");
		
		// 参数检查
		if(orderNo < 0 || StringUtil.isBlank(open_id))
			return new ResponseEntity<FlowOrderDo>(HttpStatus.BAD_REQUEST);
		
		// 获取当前时间
    	Date now = new Date();
				
    	FlowOrderDoKey key = new FlowOrderDoKey();
    	// 设置订单号，查询时ID忽略
    	key.setOrderNo(orderNo);
    	
    	// 查询订单信息
    	FlowOrderDo order = flowOrderService.selectByOrderNo(key);
    	
    	if(null == order)
    		return new ResponseEntity<FlowOrderDo>(HttpStatus.NO_CONTENT);
    	
    	// 更改订单状态
    	order.setState(FlowOrderState.SUCCESS.getState());
    	order.setStateDesc(FlowOrderState.SUCCESS.getDesc()); 
    	
    	// gmtupdate
    	order.setGmtUpdate(now);
    	// 更新订单记录
    	flowOrderService.updateByOrderNoSelective(order);
    	
		// 插入订单流水记录
		insertOrderTrans(order,now,open_id);
    	
		return new ResponseEntity<FlowOrderDo>(order,HttpStatus.OK);
	}
	/**
	 * 创建订单
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "order/create/", method = RequestMethod.POST)
	public ResponseEntity<FlowOrderDo> createOrder_POST(
			@RequestBody JSONObject json
			){
		logger.debug("request /rest/flow/order/create/ with json body");
		
    	logger.info("==============================================================");
    	logger.info("param :" + json.toJSONString());
    	logger.info("==============================================================");		
		
		// 获得参数部分json
		JSONObject jsonStr = json.getJSONObject("jsonStr");
		
		// 解析并获得参数
		String open_id = jsonStr.getString("open_id");
		String mobile = jsonStr.getString("mobile");
		// 商品ID数组 流量商品只有一个
		JSONArray goodslist = jsonStr.getJSONArray("goodslist");
		
		// 参数检查
		if(StringUtil.isBlank(open_id) || StringUtil.isBlank(mobile) || goodslist ==  null || goodslist.isEmpty())
			return new ResponseEntity<FlowOrderDo>(HttpStatus.BAD_REQUEST);

		
		// 获取当前时间
    	Date now = new Date();
		
		// 订单对象
    	FlowOrderDo order = new FlowOrderDo();
    	// 订单号
    	Long id = Long.valueOf(WebConstants.ID_MAKER.nextId());
    	order.setOrderNo(id);
    	// 订单类型
    	order.setType(WebConstants.ORDER_TYPE_FLOW);
    	// 订单状态
    	order.setState(FlowOrderState.NOTPAY.getState());
    	order.setStateDesc(FlowOrderState.NOTPAY.getDesc());    	
    	
    	// 有效期
    	order.setExpireTime(DateUtil.addMinute(now, WebConstants.ORDER_EXPIRE_MINUTES));
    	// 根据open_id获得userid
    	FlowUserDo user = flowUserService.selectByOpenId(open_id);
    	order.setUserId(user.getId());
    	
    	// 充值电话号码
    	order.setMobile(mobile);
    	
    	// 总价
    	BigDecimal totalPrice = BigDecimal.valueOf(0L);
    	// 实付金额
    	BigDecimal actuallyPaid = BigDecimal.valueOf(0L);
    	// 数量
    	int amount = 0;
    	// 折扣率
    	BigDecimal discount = BigDecimal.valueOf(0L);
    	
    	// 根据商品id列表 插入订单商品表
    	FlowGoodsDo goods = null;
		for(Object obj : goodslist){
			JSONObject goodsinfo = JSON.parseObject(obj.toString());
			
			// goods对象			
			goods = JSON.parseObject(goodsinfo.getString("goods"),FlowGoodsDo.class);
			// 数量
			amount = goodsinfo.getIntValue("amount");
			// 折扣率
			discount = goodsinfo.getBigDecimal("discount");
			
			// 插入订单商品表
			FlowOrderGoodsDo flowOrderGoods = new FlowOrderGoodsDo();
			// 订单号
			flowOrderGoods.setOrderNo(order.getOrderNo());
			// 商品ID
			flowOrderGoods.setGoodsId(goods.getId());
			// 商品数量
			flowOrderGoods.setAmount(amount);
			// 商品折扣
			flowOrderGoods.setDiscout(discount);
			// gmtcreate gmtupdate
			flowOrderGoods.setGmtCreate(now);
			flowOrderGoods.setGmtUpdate(now);
			
			// 插入订单商品表
			flowOrderGoodsService.insertSelective(flowOrderGoods);
			
			// 累计总价 total += total * amount
			totalPrice = totalPrice.add(goods.getGoodsPrice().multiply(BigDecimal.valueOf(amount)));
		}
		// 总价
		order.setTotalPrice(totalPrice);
		
		// 累计实付金额
		actuallyPaid = totalPrice.multiply(discount);
		order.setActuallyPaid(actuallyPaid);
		
		// gmtcreate gmtupdate
		order.setGmtCreate(now);
		order.setGmtUpdate(now);
				
		// 写入订单表
		flowOrderService.insertSelective(order);
		
//		// flowOrderTransService
//		// 订单流水对象
//		FlowOrderTransDo trans = new FlowOrderTransDo();
//		// 订单号
//		trans.setOrderNo(order.getOrderNo());
//		// 商户ID
//		trans.setMerchId(WebConstants.ORDER_TRANS_DEFAULT_MERCH_ID);
//		// 店铺ID
//		trans.setShopId(WebConstants.ORDER_TRANS_DEFAULT_SHOP_ID);
//		// 交易代码
//		trans.setTransCode(WebConstants.ORDER_TRANS_TRANS_CODE_OPEN);
//		// 交易名称
//		trans.setTransName(WebConstants.ORDER_TRANS_TRANS_NAME_OPEN);
//		// 订单状态
//		trans.setState(order.getState());
//		// 订单状态描述
//		trans.setStateDesc(order.getStateDesc());
//		// 总价
//		trans.setTotalPrice(order.getTotalPrice());
//		// 实付(折扣后)
//		trans.setActuallyPaid(order.getActuallyPaid());
//		
//		// gmtcreate gmtupdate
//		trans.setGmtCreate(now);
//		trans.setGmtUpdate(now);
//		
//		// open_id
//		trans.setOpenId(open_id);
//		// nickname
//		trans.setNickName(null);
//		// PREPAY_ID
//		trans.setPrepayId(null);
//		// BANK_TYPE
//		trans.setBankType(null);
//		
//		// 插入订单流水记录
//		flowOrderTransService.insertSelective(trans);
    	
		// 插入订单流水记录
		insertOrderTrans(order,now,open_id);
		
		return new ResponseEntity<FlowOrderDo>(order,HttpStatus.CREATED);
	}
	
	/**
	 * 向订单流水表中插入一条记录
	 * @param order 订单信息
	 * @param now 统一时间
	 * @param open_id open_id
	 */
	private void insertOrderTrans(FlowOrderDo order,Date now,String open_id){
		// flowOrderTransService
		// 订单流水对象
		FlowOrderTransDo trans = new FlowOrderTransDo();
		// 订单号
		trans.setOrderNo(order.getOrderNo());
		// 商户ID
		trans.setMerchId(WebConstants.ORDER_TRANS_DEFAULT_MERCH_ID);
		// 店铺ID
		trans.setShopId(WebConstants.ORDER_TRANS_DEFAULT_SHOP_ID);
		// 交易代码
		trans.setTransCode(WebConstants.ORDER_TRANS_TRANS_CODE_OPEN);
		// 交易名称
		trans.setTransName(WebConstants.ORDER_TRANS_TRANS_NAME_OPEN);
		// 订单状态
		trans.setState(order.getState());
		// 订单状态描述
		trans.setStateDesc(order.getStateDesc());
		// 总价
		trans.setTotalPrice(order.getTotalPrice());
		// 实付(折扣后)
		trans.setActuallyPaid(order.getActuallyPaid());
		
		// gmtcreate gmtupdate
		trans.setGmtCreate(now);
		trans.setGmtUpdate(now);
		
		// open_id
		trans.setOpenId(open_id);
		// nickname
		trans.setNickName(null);
		// PREPAY_ID
		trans.setPrepayId(null);
		// BANK_TYPE
		trans.setBankType(null);
		
		// 插入订单流水记录
		flowOrderTransService.insertSelective(trans);		
	}
}
