package com.thon.controller.relation;


import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.relation.VisitRecord;
import com.thon.service.relation.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 访问记录管理
 * Created by 57 on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/visit")
public class VisitRecordController extends BaseController {
    @Autowired
    private VisitRecordService recordService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/find/record/list", method = RequestMethod.GET)
    public ResponseEntity<HttpEntity> listRecord(@RequestParam(value = "userId", required = true) int userId,
                                                         @RequestParam(value = "p", defaultValue = "1") int pageNo,
                                                         @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize ){
        Page<VisitRecord> visitRecordPage = recordService.findRecordAllPage(userId, pageNo, pageSize);
        return new ResponseEntity(visitRecordPage, HttpStatus.OK);
    }
}
