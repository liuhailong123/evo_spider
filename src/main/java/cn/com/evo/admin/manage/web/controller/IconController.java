package cn.com.evo.admin.manage.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.frameworks.core.web.controller.BaseController;

@Controller
@RequestMapping("/manage/icon")
public class IconController extends BaseController {

    private static final String VIEW_PAGE = "manage/icon/view";

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }
}
