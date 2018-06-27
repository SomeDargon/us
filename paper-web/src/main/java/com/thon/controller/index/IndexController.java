package com.thon.controller.index;

import com.thon.controller.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = {"/","/paper/index"}, method = RequestMethod.GET)
    public String paperIndex(Model model) {

        return "login";
    }



}