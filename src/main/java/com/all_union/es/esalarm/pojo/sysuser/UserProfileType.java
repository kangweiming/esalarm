/**
 * 
 */
package com.all_union.es.esalarm.pojo.sysuser;

/** 
 * @Description: 用户角色枚举
 * @author kwm
 * @date 2017年2月17日 下午1:33:17 
 * @version V1.0 
 * 
*/
public enum UserProfileType {
	
    USER("USER"),  
    DBA("DBA"),  
    ADMIN("ADMIN");  
       
    String userProfileType;  
       
    private UserProfileType(String userProfileType){  
        this.userProfileType = userProfileType;  
    }  
       
    public String getUserProfileType(){  
        return userProfileType;  
    } 
}
