package com.thon.dao.relation.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.commons.persistence.Page;
import com.thon.dao.relation.VisitRecordDao;
import com.thon.entity.relation.VisitRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by somedargon on 2017/3/21.
 */
@Repository
public class VisitRecordDaoImpl extends BaseDaoImpl<VisitRecord> implements VisitRecordDao {
    public VisitRecordDaoImpl() {
        super(VisitRecord.class);
    }
    @Override
    public Page<VisitRecord> findRecordAllPage(Page<VisitRecord> page, int userId){
        String queryString = "FROM VisitRecord t WHERE t.id =? ";
        return findPage(page, queryString, userId);
    }
}
