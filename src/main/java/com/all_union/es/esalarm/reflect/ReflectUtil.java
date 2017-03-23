/**
 * 
 */
package com.all_union.es.esalarm.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.all_union.es.esalarm.pojo.flow.FlowGoodsQuery;

/** 
 * @Description: java 反射工具
 * @author kwm
 * @date 2017年3月22日 上午11:32:33 
 * @version V1.0 
 * 
*/
public class ReflectUtil {

	/**
	 * 处理bean属性 对于String类型的属性，将空字符串（不是NULL）转成NULL,便于mybatis判断
	 * 如果是非标准pojo，可能处理不成功，打印异常，但不做特殊处理
	 * @param obj
	 * @param initValue 查找的原值
	 * @param newValue 替换后的新值
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Object replaceFieldString(Object obj,String initValue,String newValue) {
		
		// 属性名
		String name;
		
		// 属性的类型
		String type;
		
		// 属性的值
		String value;
		
		// 属性的getter方法
		Method methodGet;
		
		// 属性的setter方法
		Method methodSet;
		
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = obj.getClass().getDeclaredFields(); 
		
		try{
			for(Field f : fields){
				// 获得属性的名字
				name = f.getName();
				// 将属性的首字符大写，方便构造get，set方法
				name = name.substring(0, 1).toUpperCase() + name.substring(1); 
				
				// 获得属性的类型
				type = f.getGenericType().getTypeName();
				
				// 字符串类型的属性
				if(type.equals("java.lang.String")){
					// 获得属性的getter方法
					methodGet = obj.getClass().getMethod("get" + name);
					
					// 获取属性的值
					value = (String) methodGet.invoke(obj);
					
					if(null == value)
						continue;
					
					// 如果是空，统一设置成null				
					if(initValue.equals(value.trim())){
						// 获得属性setter方法
						methodSet = obj.getClass().getMethod("set" + name,String.class);
						//obj.getClass().getDeclaredMethod(name, parameterTypes)
						// 执行setter方法，设置属性的值为NULL,这样写是错误的 methodSet.invoke(obj,null);
						
						methodSet.invoke(obj,newValue);						
					}
				}
			}			
		}
		catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally{
			return obj;
		}
	}
	
	public static void main(String[] args){
		
		FlowGoodsQuery query = new FlowGoodsQuery();
		
		query.setGoodsName("");
		
		ReflectUtil.replaceFieldString(query,"",null);
		
		System.out.println(query.getGoodsName());
		
		
	}
}
