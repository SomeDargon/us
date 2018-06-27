package com.thon.controller.relation;

import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.relation.InterRelation;
import com.thon.entity.system.Dict;
import com.thon.service.relation.InterRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 人脉管理
 * Created by 57 on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/inter")
public class InterRelationController extends BaseController {
    @Autowired
    private InterRelationService interRelationService;
    private static final Log log = LogFactory.getLog(InterRelationController.class);
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/find/relation/list", method = RequestMethod.GET)
    public ResponseEntity<HttpEntity> listPersonRelation( @RequestParam(value = "p", defaultValue = "1") int pageNo,
                                                          @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize ,
                                                          InterRelation interRelation){
        Page<InterRelation> page = new Page<InterRelation>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page = interRelationService.find(page, interRelation);
        return new ResponseEntity(page, HttpStatus.OK);
    }
    @RequestMapping(value="/relation/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserAdd(@Valid InterRelation relation) {
        int userId = getCurrentUserId();
        relation.setDelFlag(0);
        relation.setuId(userId);
        interRelationService.saveRelation(relation);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value="/relation/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserDel(@RequestParam(value = "id", required = true) int id) {
        InterRelation relation = interRelationService.findByIdRelation(id);
        interRelationService.delRelation(relation);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
