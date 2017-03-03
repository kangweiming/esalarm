package com.all_union.es.esalarm.pojo.sysuser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysUserDo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -763620027099830192L;

	private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String nickName;

    private String email;

    private String state;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtLastLogin;

    private List<SysUserProfileDo> sysUserProfiles = new ArrayList<SysUserProfileDo>();
    
    public Long getId() {
        return id;
    }

	public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }
    
    public List<SysUserProfileDo> getSysUserProfiles() {
		return sysUserProfiles;
	}

	public void setSysUserProfiles(List<SysUserProfileDo> sysUserProfiles) {
		this.sysUserProfiles = sysUserProfiles;
	}
    
}