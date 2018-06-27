package com.thon.dao.schedule.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.schedule.ScheduleDao;
import com.thon.entity.schedule.Schedule;
import org.springframework.stereotype.Repository;

/**
 * Created by somedargon on 2017/3/24.
 */
@Repository
public class ScheduleDaoImpl extends BaseDaoImpl<Schedule> implements ScheduleDao {
    public ScheduleDaoImpl(){
        super(Schedule.class);
    }
}
