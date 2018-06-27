/**
 * 
 */
package com.thon.controller.rest;

import com.google.common.collect.Maps;
import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;

import com.thon.entity.system.User;
import com.thon.service.system.LogService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author 57
 */
@Controller
@RequestMapping("/api/user")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserRestController extends BaseController{
	
	@Autowired
	private SystemService systemService;
    @Autowired
    private LogService logService;
	
	private static final Log log = LogFactory.getLog(UserRestController.class);


    @RequestMapping(value="/find/list", method = RequestMethod.GET)
    public ResponseEntity<?> findList(@RequestParam(value = "office_id", defaultValue = "3") int id,
                                      @RequestParam(value = "type", defaultValue = "2") int type
                                    ){
        List<User> list = systemService.findList(id, type);//调用service查询数据库
        return new ResponseEntity(list, HttpStatus.OK);//返回数据及返回页面状态
    }

    //查看所有用户的接口（业务逻辑）
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                  User user) {

        Page<User> page = new Page<User>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = systemService.findUsers(page, user);

        return new ResponseEntity(page, HttpStatus.OK);
    }

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> apiUserAdd(@Valid User user) {
		systemService.saveUser(user);
		return new ResponseEntity(true, HttpStatus.OK);
	}

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserDel(@RequestParam("id") int id) {
        if(UserUtils.getUser().isAdmin()){
            User user = systemService.getUser(id);
            systemService.deleteUser(user);

            return new ResponseEntity("true", HttpStatus.OK);
        }else {
            return new ResponseEntity("false", HttpStatus.FORBIDDEN);
        }
    }





    @RequestMapping(value="/profile/avatar/update", method = RequestMethod.POST)
    public ResponseEntity<?> apiProfileAvatarUpdate(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getParameter("id"));
        Integer avatar = Integer.valueOf(request.getParameter("avatar"));

        User user = systemService.getUser(userId);
        user.setAvatar(avatar);
        systemService.saveUser(user);

        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/check", method = RequestMethod.GET)
    public ResponseEntity<?> apiUserAccountCheck(@RequestParam("loginName") String loginName,
                                                 @RequestParam(value = "uid", defaultValue = "0") int userId) {
        User user = systemService.findUser(loginName, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
        } else {
            return new ResponseEntity("false", HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/email/check", method = RequestMethod.GET)
	public ResponseEntity<?> apiUserEmailCheck(@RequestParam("email") String email,
                                               @RequestParam(value = "uid", defaultValue = "0") int userId) {

        User user = systemService.findUserByEmail(email, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
		} else {
            return new ResponseEntity("false", HttpStatus.OK);
		}
	}

    @RequestMapping(value = "/phone/check", method = RequestMethod.GET)
    public ResponseEntity<?> apiUserPhoneCheck(@RequestParam("phone") String phone,
                                               @RequestParam(value = "uid", defaultValue = "0") int userId) {

        User user = systemService.findUserByPhone(phone, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
        } else {
            return new ResponseEntity("false", HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/pwd/check", method = RequestMethod.GET)
	public ResponseEntity<?> apiUserPasswordCheck(@RequestParam("uid") int userId,
			@RequestParam("pwd") String password) {
		User user = systemService.getUser(userId);
		String hashPwd = systemService.entryptPassword(password, user.getSalt());
		if (StringUtils.equals(hashPwd, user.getPassword())) {
            return new ResponseEntity("true", HttpStatus.OK);
		}else {
            return new ResponseEntity("false", HttpStatus.OK);
		}	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/pwd/update", method = RequestMethod.POST)
	public ResponseEntity<?> apiProfilePwdUpdate(HttpServletRequest request) {
		
		Integer userId = Integer.valueOf(request.getParameter("uid"));
		String password = request.getParameter("plainPassword");
		
		User user = systemService.getUser(userId);
		String hashPwd = systemService.entryptPassword(password, user.getSalt());
		user.setPassword(hashPwd);
		systemService.saveUser(user);

		return new ResponseEntity(true, HttpStatus.CREATED);
	}
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    public ResponseEntity<?> logList(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                     @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize, Map map) {

        Page<com.thon.entity.system.Log> page = new Page<com.thon.entity.system.Log>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = logService.find(page, map);

        return new ResponseEntity(page, HttpStatus.OK);
    }
}
