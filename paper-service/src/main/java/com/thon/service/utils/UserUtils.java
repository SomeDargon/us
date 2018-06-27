
package com.thon.service.utils;

import com.google.common.collect.Maps;
import com.thon.commons.security.utils.ShiroUser;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.SpringContextHolder;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * 用户工具类
 * @author 57
 */
public class UserUtils extends BaseService{
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);


	public static final String CACHE_USER = "user";
	public static final String CACHE_USER_OFFICE = "userOffice";
	public static final String CACHE_PAGE_SIZE = "userPageSize";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_LIST_CODE = "officeListCode";
	
	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			ShiroUser principal = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userDao.get(principal.getId());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}


	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			Subject subject = SecurityUtils.getSubject();
			ShiroUser principal = (ShiroUser)subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}
	
	public static String getPageSize(){
		String pageSize = (String)CacheUtils.get(CACHE_PAGE_SIZE);
		if (StringUtils.isBlank(pageSize)){

			CacheUtils.put(CACHE_PAGE_SIZE, "50");
		}
		return pageSize;
	}
}
