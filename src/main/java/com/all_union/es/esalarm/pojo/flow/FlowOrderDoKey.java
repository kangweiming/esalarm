package com.all_union.es.esalarm.pojo.flow;

public class FlowOrderDoKey {
    private Long id;

    private Long orderNo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    
    /**
     *  解决Long类型，传到js时，精度丢失的问题，使用字符串传递
     *  理论上所有的long型都应该如此处理
     * @return
     */
    public String getOrderNoStr() {
        return String.valueOf(orderNo);
    }
    
}