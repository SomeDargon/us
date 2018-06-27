package com.thon.controller.schedule;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.controller.util.BaseController;
import com.thon.entity.relation.VisitRecord;
import com.thon.entity.schedule.Schedule;
import com.thon.entity.system.User;
import com.thon.service.schedule.ScheduleService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 57 on 2017/3/24.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SystemService systemService;
    //unchecked去掉警告
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/find/list", method = RequestMethod.GET)
    public ResponseEntity<HttpEntity> listRecord( Schedule schedule,
                                                 @RequestParam(value = "p", defaultValue = "1") int pageNo,
                                                 @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize ){
        Page<Schedule> page = new Page<Schedule>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page = scheduleService.findScheduleAllPage(page, schedule);
        return new ResponseEntity(page, HttpStatus.OK);
    }
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserAdd(@Valid Schedule schedule) {
        schedule.setUser(UserUtils.getUser());
        schedule.setInsertTime(new Date());
        scheduleService.saveSchedule(schedule);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserDel(@RequestParam(value = "id", required = true) int id) {
        Schedule schedule = scheduleService.findByIdSchedule(id);
        scheduleService.delSchedule(schedule);
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
