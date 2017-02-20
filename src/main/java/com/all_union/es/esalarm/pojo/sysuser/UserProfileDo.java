package com.all_union.es.esalarm.pojo.sysuser;

import java.io.Serializable;
import java.util.Date;

public class UserProfileDo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5635882186244894983L;

	private Long id;

    private String type;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}