package com.thon.service.relation;

import com.thon.commons.persistence.Page;
import com.thon.dao.relation.VisitRecordDao;
import com.thon.entity.relation.VisitRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 57 on 2017/3/21.
 */
@Service
@Transactional
public class VisitRecordService {
    @Autowired
    private VisitRecordDao visitRecordDao;

    public Page<VisitRecord>  findRecordAllPage(int userId, int pageNo, int pageSize){
        Page<VisitRecord> page = new Page<VisitRecord>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return visitRecordDao.findRecordAllPage(page, userId);
    }
}
