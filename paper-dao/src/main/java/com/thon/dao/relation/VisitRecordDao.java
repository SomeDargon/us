package com.thon.dao.relation;

import com.thon.commons.persistence.BaseDao;
import com.thon.commons.persistence.Page;
import com.thon.entity.relation.VisitRecord;

/**
 * Created by 57 on 2017/3/21.
 */
public interface VisitRecordDao extends BaseDao<VisitRecord> {
    public Page<VisitRecord> findRecordAllPage(Page<VisitRecord> page, int userId);
}
