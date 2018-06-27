package com.thon.service.audit;

import com.thon.dao.audit.FlowInfoDao;
import com.thon.entity.audit.FlowInfo;
import com.thon.entity.audit.TaskInstance;
import com.thon.entity.system.User;
import com.thon.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by 57 on 4/11/2017.
 */
@Service
@Transactional
public class FlowInfoService {
    @Autowired
    private FlowInfoDao flowInfoDao;

    public void save(FlowInfo flowInfo){
        flowInfoDao.save(flowInfo);
    }

    public void saveFlowInfo(FlowInfo flowInfo, TaskInstance taskInstance, long id, User user, Date nowTime){
        flowInfo.setInstance(taskInstance);
        flowInfo.setDealPeople(UserUtils.getUser());
        flowInfo.setNextActor(user);
        flowInfo.setInsertTime(nowTime);
        flowInfo.setTaskName(0);
        flowInfoDao.save(flowInfo);
    }
}
