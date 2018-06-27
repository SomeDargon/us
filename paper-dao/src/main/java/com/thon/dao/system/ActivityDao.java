/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.commons.persistence.Page;
import com.thon.entity.system.Activity;

/**
 * @file TrendDao.java
 * @author 57
 */
public interface ActivityDao extends BaseDao<Activity> {

	Page<Activity> findUserActivityPage(Page<Activity> page, int userId);
	
	Page<Activity> findTeachActivityPage(Page<Activity> page, int userId);

    Page<Activity> findActivityPage(Page<Activity> page, int courseId, int userId, String action);
	
	Activity getLastActivity(int fromId, String action);
	
	long sumMessageCount(int userId);
}
