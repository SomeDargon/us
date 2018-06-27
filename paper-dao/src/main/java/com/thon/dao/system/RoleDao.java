/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.system.Role;

/**
 * @author 57
 */
public interface RoleDao extends BaseDao<Role> {
	
	void deleteById(int id);
	
	Role findByName(String name);
}
