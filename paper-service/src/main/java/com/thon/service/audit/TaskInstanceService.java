package com.thon.service.audit;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.audit.TaskInstanceDao;
import com.thon.entity.audit.FlowInfo;
import com.thon.entity.audit.TaskInstance;
import com.thon.entity.system.User;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 57 on 4/11/2017.
 */
@Service
@Transactional
public class TaskInstanceService {
    @Autowired
    private TaskInstanceDao instanceDao;
    @Autowired
    private FlowInfoService flowInfoService;
    public void save(TaskInstance taskInstance){
        instanceDao.save(taskInstance);
    }

    public void startTask( FlowInfo flowInfo, User user, Date nowTime){
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setNextPeople(user);
        taskInstance.setInitiator(UserUtils.getUser());
        taskInstance.setStatus(0);
        taskInstance.setTaskStatus(0);
        taskInstance.setInsertTime(nowTime);
        save(taskInstance);
    }

    public Page<TaskInstance> findInstances(Page<TaskInstance> page, TaskInstance instance) {
        DetachedCriteria dc = instanceDao.createDetachedCriteria();
//        if (instance.getNextPeople() != null){
//            dc.add(Restrictions.eq("nextPeople.id", instance.getNextPeople().getId()));
//        }
//        if (instance.getInitiator()!= null){
//            dc.add(Restrictions.eq("initiator.id", instance.getInitiator().getId()));
//        }
        if(instance.getInsertTime()!=null){
            Date beginDate = DateUtils.truncate(instance.getInsertTime(), Calendar.DATE);
            Date endDate = DateUtils.ceiling(instance.getInsertTime(), Calendar.DATE);
            dc.add(Restrictions.between("insertTime", beginDate, endDate));
        }
        if (instance.getStatus()!= null){
             dc.add(Restrictions.eq("status", instance.getStatus()));
        }
        return instanceDao.find(page, dc);
    }
    public long getMaxId(){
        return instanceDao.getMaxId();
    }

    public TaskInstance getById(int id){
        return instanceDao.get(id);
    }

}
