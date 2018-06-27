package com.thon.dao.audit.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.audit.FlowInfoDao;
import com.thon.entity.audit.FlowInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by somedargon on 4/10/2017.
 */
@Repository
public class FlowInfoDaoImpl extends BaseDaoImpl<FlowInfo>  implements FlowInfoDao{
    public FlowInfoDaoImpl() {
        super(FlowInfo.class);
    }
}
