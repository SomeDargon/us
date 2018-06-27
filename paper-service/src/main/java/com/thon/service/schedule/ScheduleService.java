package com.thon.service.schedule;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.schedule.ScheduleDao;
import com.thon.entity.schedule.Schedule;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 57 on 2017/3/24.
 */
@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;

    public Page<Schedule> findScheduleAllPage(Page<Schedule> page, Schedule schedule){
        DetachedCriteria dc = scheduleDao.createDetachedCriteria();
        dc.add(Restrictions.eq(schedule.DEL_FLAG, schedule.DEL_FLAG_NORMAL));
        if(schedule.getStartTime()!=null){
            Date beginDate = DateUtils.truncate(schedule.getStartTime(), Calendar.MONTH);
            Date endDate = DateUtils.ceiling(schedule.getStartTime(), Calendar.MONTH);
            dc.add(Restrictions.between("startTime", beginDate, endDate));
        }
        dc.addOrder(Order.desc("insertTime"));
        return scheduleDao.find(page, dc);
    }
    public Schedule findByIdSchedule(int id){
        return scheduleDao.get(id);
    }

    public void saveSchedule(Schedule schedule){
        scheduleDao.save(schedule);
    }
    public void delSchedule(Schedule schedule){
        scheduleDao.delete(schedule);
    }
}
