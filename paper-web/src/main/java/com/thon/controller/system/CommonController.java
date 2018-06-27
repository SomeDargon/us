/**
 * 
 */
package com.thon.controller.system;

import com.thon.controller.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 57
 */
@Controller
public class CommonController extends BaseController{


    @RequestMapping(value={"/bill/sum"}, method = RequestMethod.GET)
    public String sumBill(Model model) {

        return "bill-sum";
    }

    @RequestMapping(value={"/bill/search"}, method = RequestMethod.GET)
    public String search(Model model) {

        return "bill-search";
    }
}
