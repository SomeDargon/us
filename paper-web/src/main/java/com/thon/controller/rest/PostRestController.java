/**
 *
 */
package com.thon.controller.rest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thon.commons.mapper.BeanUtils;
import com.thon.commons.persistence.Page;
import com.thon.commons.utils.StringUtils;
import com.thon.controller.util.BaseController;
import com.thon.entity.post.Advertise;
import com.thon.security.CaptchaServlet;
import com.thon.service.post.AdvertiseService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/post")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PostRestController extends BaseController {
    @Autowired
    private AdvertiseService advertiseService;
    private static final Log log = LogFactory.getLog(PostRestController.class);

    @RequestMapping(value = "/advertise/list", method = RequestMethod.GET)
    public ResponseEntity<?> getAdvertiseList(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                              @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                              Advertise advertise) {
        Page<Advertise> page = new Page<Advertise>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = advertiseService.findAdvertises(page, advertise);
        return new ResponseEntity(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/advertise/del", method = RequestMethod.POST)
    public ResponseEntity<?> deleteAdvertiseTerm(@RequestParam(value = "id",required = true)int id){
        Advertise advertise = advertiseService.getAdvertise(id);
        advertiseService.deleteAdvertise(advertise);
        return new ResponseEntity("true", HttpStatus.OK);
    }

    @RequestMapping(value = "/advertise/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveAdvertiseTerm(@Valid Advertise advertise){
        advertise.setCreateDate(new Date());
        advertiseService.saveAdvertise(advertise);
        return new ResponseEntity("true",HttpStatus.OK);
    }

    //添加标题图片
    @RequestMapping(value="/advertise/profile/avatar/update", method = RequestMethod.POST)
    public ResponseEntity<?> apiProfileAvatarUpdate(HttpServletRequest request) {

        Integer advertiseId = Integer.valueOf(request.getParameter("id"));
        Integer avatar = Integer.valueOf(request.getParameter("attachment"));

        Advertise advertise = advertiseService.getAdvertise(advertiseId);
        advertise.setAttachment(avatar);
        advertiseService.saveAdvertise(advertise);

        return new ResponseEntity(true, HttpStatus.OK);
    }

//    @RequestMapping(value="/paper/profile/avatar/update", method = RequestMethod.POST)
//    public ResponseEntity<?> apiPaperProfileAvatarUpdate(HttpServletRequest request) {
//
//        Integer advertiseId = Integer.valueOf(request.getParameter("id"));
//        Integer avatar = Integer.valueOf(request.getParameter("attachment"));
//
//        Advertise advertise = advertiseService.getAdvertise(advertiseId);
//        advertise.setAttachment(avatar);
//        advertiseService.saveAdvertise(advertise);
//
//        return new ResponseEntity(true, HttpStatus.OK);
//    }



}
