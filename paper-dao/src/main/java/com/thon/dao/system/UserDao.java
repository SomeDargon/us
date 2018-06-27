package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.commons.persistence.Page;
import com.thon.entity.system.User;

import java.util.Date;
import java.util.List;


/**
 * @author 57
 */
public interface UserDao extends BaseDao<User> {
	
	void deleteById(int id);
	
	User findByLoginName(String loginName, Integer status);

    User findByEmail(String email, Integer status);

    User findByPhone(String phone, Integer status);

	List<User> listAllUsers();
	
	Page<User> findUserPage(Page<User> page,String role);
	
	Page<User> findUserActorPage(Page<User> page, int courseId, int flag);
	
	Page<User> findUserActorAvailablePage(Page<User> page, int courseId);
	
	void updateLoginInfo(String host, Date loginDate, int id);
	
	Page<User> findUserPage(Page<User> page);
	
	List<User> listUser(int did);

	User getUserTypeById(int curUserId);

	List<User> listFindUser(int did, int type);
}
