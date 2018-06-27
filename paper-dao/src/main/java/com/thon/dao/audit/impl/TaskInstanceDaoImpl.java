package com.thon.dao.audit.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.audit.FlowInfoDao;
import com.thon.dao.audit.TaskInstanceDao;
import com.thon.entity.audit.FlowInfo;
import com.thon.entity.audit.TaskInstance;
import org.springframework.stereotype.Repository;

/**
 * Created by somedargon on 4/10/2017.
 */
@Repository
public class TaskInstanceDaoImpl extends BaseDaoImpl<TaskInstance>  implements TaskInstanceDao{
    public TaskInstanceDaoImpl() {
        super(TaskInstance.class);
    }

    @Override
    public long getMaxId() {
        String sql = "select max(id) from TaskInstance";
        return count(sql);
    }
}
