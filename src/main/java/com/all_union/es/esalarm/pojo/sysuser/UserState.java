/**
 * 
 */
package com.all_union.es.esalarm.pojo.sysuser;

/** 
 * @Description: 用户状态枚举
 * @author kwm
 * @date 2017年2月17日 下午1:27:46 
 * @version V1.0 
 * 
*/
public enum  UserState {
	
    ACTIVE("Active"),  
    INACTIVE("Inactive"),  
    DELETED("Deleted"),  
    LOCKED("Locked");  
       
    private String state;  
       
    private UserState(final String state){  
        this.state = state;  
    }  
       
    public String getState(){  
        return this.state;  
    }  
   
    @Override  
    public String toString(){  
        return this.state;  
    }  
   
    public String getName(){  
        return this.name();  
    } 
}
