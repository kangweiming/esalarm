/**
 * 
 */
package com.all_union.es.esalarm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Description: 
 * <p>
 * 防止重复提交注解，用于方法上<br/>
 * 在新建页面方法上，设置needSaveToken()为true，此时拦截器会在Session中保存一个token，
 * 同时需要在新建的页面中添加
 * <input type="hidden" name="token" value="${token}">
 * <br/>
 * 在需要生成token的controller方法上增加@TokenAnnotation(needSaveToken = true)，
 * 在需要检查重复提交的controller上添加@TokenAnnotation(needRemoveToken = true)。
 * <br/>
 * 保存方法需要验证重复提交的，设置needRemoveToken为true
 * 此时会在拦截器中验证是否重复提交
 * 比如edit方法设置保存，save方法设置删除
 * </p> 
 * @author kwm
 * @date 2017年2月25日 下午11:44:55 
 * @version V1.0 
 * 
*/
// ---------------
// 次注解还没经过严格测试 2017.02.26
// ---------------
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAnnotation {
	
	boolean needSaveToken() default false;
    boolean needRemoveToken() default false;
}
