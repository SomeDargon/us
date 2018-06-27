package com.thon.dao.audit;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.audit.FlowInfo;
import com.thon.entity.audit.TaskInstance;

/**
 * Created by 57 on 4/10/2017.
 */
public interface TaskInstanceDao extends BaseDao<TaskInstance> {
    public long getMaxId();
}
