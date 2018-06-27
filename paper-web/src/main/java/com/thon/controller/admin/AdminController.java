package com.thon.controller.admin;

import com.google.common.collect.Maps;
import com.thon.controller.util.BaseController;
import com.thon.entity.audit.TaskInstance;
import com.thon.entity.post.Advertise;
import com.thon.entity.relation.InterRelation;
import com.thon.entity.schedule.Schedule;
import com.thon.entity.system.Dict;
import com.thon.entity.system.User;
import com.thon.service.audit.TaskInstanceService;
import com.thon.service.post.AdvertiseService;
import com.thon.service.relation.InterRelationService;
import com.thon.service.schedule.ScheduleService;
import com.thon.service.system.DictService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 57
 * @description 系统管理相关控制
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
	private static final Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private DictService dictService;
    @Autowired
    private InterRelationService relationService;
    @Autowired
    private AdvertiseService advertiseService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TaskInstanceService instanceService;

	@RequestMapping(method = RequestMethod.GET)
	public String admin() {
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return "login";
		}
		return "admin";
	}


    @RequestMapping(value="/advertise/form", method = RequestMethod.GET)
    public String advertiseForm(@RequestParam(value="id",required=false) Integer id,Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        Advertise advertise = new Advertise();
        if(id!=null){
            advertise = advertiseService.getAdvertise(id);
        }
        model.addAttribute("advertise",advertise);
        return "admin-advertise-form";
    }

    @RequestMapping(value="/advertise/manage", method = RequestMethod.GET)
    public String advertiseManage() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-advertise-manage";
    }

    //跳转到查看所有用户页面
	@RequestMapping(value="/user/manage", method = RequestMethod.GET)
	public String userManage(@RequestParam(value="p", defaultValue="1") Integer pageNo,
			@RequestParam(value="s", defaultValue="20") Integer pageSize,Model model) {
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return "login";
		}
		return "admin-user-manage";
	}
	
	@RequestMapping(value="/user/form", method = RequestMethod.GET)
    public String userForm(@RequestParam(value="id",required=false) Integer id,Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        User user = new User();
        if (id != null) {
            user = systemService.getUser(id);
        }

        model.addAttribute("user", user);
        model.addAttribute("allRoles", systemService.findAllRole());
        return "admin-user-form";
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String adminProfile(Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        int userId = getCurrentUserId();
        model.addAttribute("user", systemService.getUser(userId));
        return "admin-profile";
    }

    @RequestMapping(value="/profile/avatar", method = RequestMethod.GET)
    public String adminProfileAvatar(Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        int userId = getCurrentUserId();
        model.addAttribute("user", systemService.getUser(userId));
        return "admin-profile-avatar";
    }
	
	@RequestMapping(value="/dict/manage", method = RequestMethod.GET)
	public String dictManage(Model model) {

		List<String> dictTypes = dictService.findTypeList();
		
        model.addAttribute("dictTypes", dictTypes);
		return "admin-dict-manage";
	}
	
	@RequestMapping(value="/dict/form", method = RequestMethod.GET)
	public String dictForm(@RequestParam(value="id",required=false) Integer id, Model model) {
		
		Dict dict = new Dict();
		if (id != null){
			dict = dictService.getDict(id);
		}
		model.addAttribute("dict", dict);
		return "admin-dict-form";
	}

    @RequestMapping(value="/paper/manage", method = RequestMethod.GET)
    public String paperManage() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-paper-manage";
    }

    @RequestMapping(value="/personal/manage", method = RequestMethod.GET)
    public String relationManager(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-relation-manage";
    }


    @RequestMapping(value="/relation/form", method = RequestMethod.GET)
    public String relationPeopleManager(@RequestParam(value="id",required=false) Integer id, Model model){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        InterRelation interRelation = new InterRelation();
        if(id!=null){
            interRelation = relationService.findByIdRelation(id);
        }
        model.addAttribute("relation", interRelation);
        return "admin-relation-form";
    }

    @RequestMapping(value="/record/manage", method = RequestMethod.GET)
    public String recordManage(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-record-manage";
    }

    @RequestMapping(value="/schedule/manage", method = RequestMethod.GET)
    public String scheduleManager(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-schedule-manage";
    }
//添加日程
    @RequestMapping(value="/schedule/form", method = RequestMethod.GET)
    public String scheduleAdd(@RequestParam(value="id",required=false) Integer id, Model model){ //model可以值专递，传给前台jsp页面的
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        Schedule schedule = new Schedule();
        if(id!=null){
            schedule = scheduleService.findByIdSchedule(id);
        }
        model.addAttribute("schedule", schedule);
        return "admin-schedule-form";
    }

    @RequestMapping(value="/log/manage", method = RequestMethod.GET)
    public String logManage(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-log-manage";
    }


    @RequestMapping(value="/task/manage", method = RequestMethod.GET)
    public String taskManager(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-task-manage";
    }
    @RequestMapping(value="/task/return", method = RequestMethod.GET)
    public String taskReturn(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-task-return";
    }
    @RequestMapping(value="/task/finish", method = RequestMethod.GET)
    public String taskFinish(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-task-finish";
    }
    @RequestMapping(value="/task/form", method = RequestMethod.GET)
    public String taskAdd(@RequestParam(value="id",required=false) Integer id, Model model){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }
        return "admin-task-start";
    }
    @RequestMapping(value="/task/process/deal", method = RequestMethod.GET)
    public String taskTaskDeal(@RequestParam(value="id",required=false) Integer id, Model model){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        model.addAttribute("id", id);
        return "admin-process-deal";
    }
    @RequestMapping(value="/desktop", method = RequestMethod.GET)
    public String desktop(){
        if(!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        return "admin-desktop-manage";
    }
}
