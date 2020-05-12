package cn.com.evo.admin.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {

    /**
     * 首页跳转，备留门户
     * 
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "redirect:/login";
    }
}
