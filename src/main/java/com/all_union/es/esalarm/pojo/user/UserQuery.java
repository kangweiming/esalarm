/**
 * 
 */
package com.all_union.es.esalarm.pojo.user;

import java.io.Serializable;

import com.all_union.es.esalarm.pojo.Query;

/** 
 * @Description: 继承了Query.java，自带分页信息
 * @author kwm
 * @date 2017年2月8日 上午11:12:28 
 * @version V1.0 
 * 
*/
public class UserQuery extends Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190870522091459276L;

    private Integer id;

    private String userName;

    private String password;

    private Integer age;
	// 开始时间段的起始时间
	private String gmtStart1;

	// 开始时间段的结束时间
	private String gmtEnd1;
	
	// 结束时间段的起始时间
	private String gmtStart2;

	// 结束时间段的结束时间
	private String gmtEnd2;
	
	public String getGmtStart1() {
		return gmtStart1;
	}

	public void setGmtStart1(String gmtStart1) {
		this.gmtStart1 = gmtStart1;
	}

	public String getGmtEnd1() {
		return gmtEnd1;
	}

	public void setGmtEnd1(String gmtEnd1) {
		this.gmtEnd1 = gmtEnd1;
	}

	public String getGmtStart2() {
		return gmtStart2;
	}

	public void setGmtStart2(String gmtStart2) {
		this.gmtStart2 = gmtStart2;
	}

	public String getGmtEnd2() {
		return gmtEnd2;
	}

	public void setGmtEnd2(String gmtEnd2) {
		this.gmtEnd2 = gmtEnd2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
