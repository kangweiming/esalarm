/**
 * 
 */
package com.all_union.es.esalarm.pojo.flow;

import java.util.Date;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 
 * @author kwm
 * @date 2017年3月21日 下午1:17:51 
 * @version V1.0 
 * 
*/
public class FlowChannelQuery extends Query {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8640951984347823180L;

	private Long id;

    private String channelName;

    private String channelDesc;

    private Date gmtCreate;

    private Date gmtUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
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
