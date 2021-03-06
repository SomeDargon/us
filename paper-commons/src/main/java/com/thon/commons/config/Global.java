package com.thon.commons.config;

import com.google.common.collect.Maps;
import com.thon.commons.utils.PropertiesLoader;

import java.util.Map;

/**
 * Global.java
 * @author 57
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("application.properties");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key, String defaultValue) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key, defaultValue);
			map.put(key, value);
		}
		return value;
	}

	/////////////////////////////////////////////////////////
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
}
