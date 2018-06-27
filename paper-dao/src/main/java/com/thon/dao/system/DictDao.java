/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.system.Dict;

import java.util.List;

/**
 * @author 57
 */
public interface DictDao extends BaseDao<Dict> {
	
	List<Dict> findAllList();

    List<Dict> findByType(String type);
	
	public void deleteById(int id);

	public List<Object> findTypeList();
}
