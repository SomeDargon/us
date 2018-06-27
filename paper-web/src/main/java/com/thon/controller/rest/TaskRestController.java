package com.thon.controller.rest;

import com.google.common.collect.Maps;
import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.audit.FlowInfo;
import com.thon.entity.audit.TaskInstance;
import com.thon.entity.system.User;
import com.thon.service.audit.FlowInfoService;
import com.thon.service.audit.TaskInstanceService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by 57 on 4/11/2017.
 */
@Controller
@RequestMapping("/api/task")
public class TaskRestController extends BaseController {
    @Autowired
    private TaskInstanceService instanceService;
    @Autowired
    private FlowInfoService flowInfoService;

    @Autowired
    private SystemService systemService;

    /**
     * 创建流程，
     * FlowInfo 流转日志表，不唯一，同样的工单可能会来回操作
     * TaskInstance 流程表，唯一
     * @param flowInfo
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiSave(@Valid FlowInfo flowInfo) throws InvocationTargetException, IllegalAccessException {
        Date nowTime = new Date();
        //管理员用户
        User user = systemService.getUser(1);
        instanceService.startTask(flowInfo, user, nowTime);
        TaskInstance taskInstance = instanceService.getById((int)instanceService.getMaxId());
        flowInfoService.saveFlowInfo(flowInfo, taskInstance, instanceService.getMaxId(), user, nowTime);
        return new ResponseEntity(true, HttpStatus.OK);

    }

    /**
     * 数据查询，paper
     * @param pageNo
     * @param pageSize
     * @param taskInstance
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                  TaskInstance taskInstance) {

        Page<TaskInstance> page = new Page<TaskInstance>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page = instanceService.findInstances(page, taskInstance);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * 查询通过id查询流程数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/id", method = RequestMethod.POST)
    public ResponseEntity<?> getTask(@RequestParam(value = "id", required = true) int id) {
        Map<String, Object> map = Maps.newHashMap();
        TaskInstance instance = instanceService.getById(id);
        map.put("data", instance);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 驳回或者确认
     * @param flowInfo
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value="/pro/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiProSave(@Valid FlowInfo flowInfo) throws InvocationTargetException, IllegalAccessException {
        Date nowTime = new Date();
        //管理员用户
        User user = systemService.getUser(1);
        TaskInstance instance = instanceService.getById(flowInfo.getInstance().getId());
        instance.setNextPeople(instance.getInitiator());
        if(flowInfo.getX1().equals("0")){
            instance.setStatus(2); //状态 2表示结束
            instance.setTaskStatus(2);
            flowInfo.setType(1);
            flowInfo.setTaskName(2);
        }else {
            instance.setStatus(1); //驳回
            instance.setTaskStatus(1);
            flowInfo.setType(1);
            flowInfo.setTaskName(1);
        }
        instanceService.save(instance);
        flowInfo.setNextActor(instance.getNextPeople());
        flowInfo.setDealPeople(UserUtils.getUser());
        flowInfo.setInsertTime(new Date());
        flowInfo.setInstance(instance);
        flowInfoService.save(flowInfo);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    /**
     * 驳回之后重新发起
     * @param flowInfo
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value="/return/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiReturnSave(@Valid FlowInfo flowInfo) throws InvocationTargetException, IllegalAccessException {
        Date nowTime = new Date();
        //管理员用户
        User user = systemService.getUser(1);
        TaskInstance instance = instanceService.getById(flowInfo.getInstance().getId());
        instance.setNextPeople(instance.getInitiator());
        instance.setStatus(0);
        instance.setTaskStatus(1);
        flowInfo.setType(1);
        flowInfo.setTaskName(2);
        instanceService.save(instance);
        flowInfo.setNextActor(instance.getNextPeople());
        flowInfo.setDealPeople(UserUtils.getUser());
        flowInfo.setInsertTime(new Date());
        flowInfo.setInstance(instance);
        flowInfoService.save(flowInfo);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
