package com.thon.service.utils;

import com.google.common.collect.Lists;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.Role;
import com.thon.entity.system.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 * @author 57
 */
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private static UserDao userDao;
	/**
	 * 数据范围过滤
	 * @param user 当前用户对象，通过“UserUtils.getUser()”获取
	 * @param officeAlias 机构表别名，例如：dc.createAlias("office", "office");
	 * @param userAlias 用户表别名，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	protected static Junction dataScopeFilter(User user, String officeAlias, String userAlias) {

		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		Junction junction = Restrictions.disjunction();

		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			Role r = user.getRole();

			if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(officeAlias)){
				boolean isDataScopeAll = false;
				if (Role.DATA_SCOPE_ALL.equals(r.getDataScope())){
					isDataScopeAll = true;
				}

				if (!isDataScopeAll){
					if (StringUtils.isNotBlank(userAlias)){
						junction.add(Restrictions.eq(userAlias+".id", user.getId()));
					}else {
						junction.add(Restrictions.isNull(officeAlias+".id"));
					}
				}else{
					// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
					junction = Restrictions.disjunction();
				}
				dataScope.add(r.getDataScope());
			}
		}
		return junction;
	}
}
