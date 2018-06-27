/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.LogDao;
import com.thon.entity.system.Log;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class LogService{
	
	@Autowired
	private LogDao logDao;

	public void addLog(Log log) {
		logDao.save(log);
	}

	public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
		DetachedCriteria dc = logDao.createDetachedCriteria();
		return logDao.find(page, dc);
	}

}
