package com.thon.dao.relation.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.commons.persistence.Page;
import com.thon.dao.relation.InterRelationDao;
import com.thon.entity.relation.InterRelation;
import org.springframework.stereotype.Repository;

/**
 * Created by somedargon on 2017/3/7.
 */
@Repository
public class InterRelationDaoImpl extends BaseDaoImpl<InterRelation> implements InterRelationDao{
    public InterRelationDaoImpl() {
        super(InterRelation.class);
    }

    @Override
    public Page<InterRelation> findInterAllPage(Page<InterRelation> page, int userId) {
        String queryString = "FROM InterRelation t WHERE t.uId =? ";
        return findPage(page, queryString, userId);
    }
}
