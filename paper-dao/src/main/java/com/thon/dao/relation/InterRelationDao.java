package com.thon.dao.relation;

import com.thon.commons.persistence.BaseDao;
import com.thon.commons.persistence.Page;
import com.thon.entity.relation.InterRelation;
import com.thon.entity.system.Activity;

/**
 * Created by 57 on 2017/3/7.
 */
public interface InterRelationDao  extends BaseDao<InterRelation> {

    public Page<InterRelation> findInterAllPage(Page<InterRelation> page,int userId);
}
