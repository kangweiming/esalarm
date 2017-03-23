/**
 * 
 */
package com.all_union.es.esalarm.pojo.flow;

import java.math.BigDecimal;
import java.util.Date;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 流浪商品查询参数对象
 * @author kwm
 * @date 2017年3月20日 上午10:33:50 
 * @version V1.0 
 * 
*/
public class FlowGoodsQuery extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1214575563229165502L;
	
    private Long id;

    private Long goodsChannelId;

    private String goodsName;

    private String goodsDesc;

    private Long goodsTpId;

    private Integer goodsProvinceId;

    private BigDecimal goodsPrice;

    private Integer goodsAmount;

    private String goodsState;

    private Date goodsExpire;

    private Date gmtCreate;

    private Date gmtUpdate;
    
    /**
     * 价格区间开始
     */
    private BigDecimal startPrice;
    /**
     * 价格区间结束
     */
    private BigDecimal endPrice;
    /**
     * 过期时间开始
     */
    private String gmtStartExpire;
    /**
     * 过期时间结束
     */
    private String gmtEndExpire;
    /**
     * 创建时间开始
     */
    private String gmtStartCreate;
    /**
     * 创建时间结束
     */
    private String gmtEndCreate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsChannelId() {
		return goodsChannelId;
	}
	public void setGoodsChannelId(Long goodsChannelId) {
		this.goodsChannelId = goodsChannelId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Long getGoodsTpId() {
		return goodsTpId;
	}
	public void setGoodsTpId(Long goodsTpId) {
		this.goodsTpId = goodsTpId;
	}
	public Integer getGoodsProvinceId() {
		return goodsProvinceId;
	}
	public void setGoodsProvinceId(Integer goodsProvinceId) {
		this.goodsProvinceId = goodsProvinceId;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public String getGoodsState() {
		return goodsState;
	}
	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
	public Date getGoodsExpire() {
		return goodsExpire;
	}
	public void setGoodsExpire(Date goodsExpire) {
		this.goodsExpire = goodsExpire;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtUpdate() {
		return gmtUpdate;
	}
	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	public String getGmtStartExpire() {
		return gmtStartExpire;
	}
	public void setGmtStartExpire(String gmtStartExpire) {
		this.gmtStartExpire = gmtStartExpire;
	}
	public String getGmtEndExpire() {
		return gmtEndExpire;
	}
	public void setGmtEndExpire(String gmtEndExpire) {
		this.gmtEndExpire = gmtEndExpire;
	}
	public String getGmtStartCreate() {
		return gmtStartCreate;
	}
	public void setGmtStartCreate(String gmtStartCreate) {
		this.gmtStartCreate = gmtStartCreate;
	}
	public String getGmtEndCreate() {
		return gmtEndCreate;
	}
	public void setGmtEndCreate(String gmtEndCreate) {
		this.gmtEndCreate = gmtEndCreate;
	}


}
