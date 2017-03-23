package com.all_union.es.esalarm.pojo.flow;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 在date类型变量的get方法上增加此注解：@DateTimeFormat(pattern="yyyy-MM-dd")
 * 有助于以pojo方式向controller传参(绑定数据)，如果不加此注解，则会出现转换错误
 * org.springframework.web日志级别调整到debug，才看到出错信息
 * @Description: 
 * @author kwm
 * @date 2017年3月21日 下午5:34:22 
 * @version V1.0 
 *
 */
public class FlowGoodsDo {
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
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
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
        this.goodsState = goodsState == null ? null : goodsState.trim();
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
}