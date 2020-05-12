package cn.com.evo.admin.manage.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.frameworks.core.logger.LoggerType;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.shiro.ShiroConsts;
import com.frameworks.core.web.constants.WebConsts;
import com.frameworks.core.web.controller.BaseController;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Module;
import cn.com.evo.admin.manage.service.ModuleService;

@Controller
@RequestMapping("/manage")
public class MainController extends BaseController {

    private static final String MAIN = "manage/main";
    private static final String HOME = "manage/home";

    @Autowired
    private ModuleService moduleService;

    /**
     * TODO 临时解决刷新页面导致错误的增加一条登录日志.
     * 
     * @return
     */
    @RunLogger(value = "用户登陆成功，进入系统", opType = LoggerType.LOGIN)
    @RequiresUser
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String jump() {
        return "redirect:/manage/main";
    }

    @RequiresUser
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        Subject subject = SecurityUtils.getSubject();
        ModelAndView mav = new ModelAndView(MAIN);
        Module menuModule = findAuthorityModule(subject);
        mav.addObject("menuModule", menuModule);
        mav.addObject(ShiroConsts.LOGIN_USER, getLoginUser());
        return mav;
    }

    @RequiresUser
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return HOME;
    }

    private Module findAuthorityModule(Subject subject) {
        Module root = null;
        List<Module> modules = moduleService.findByParentId(null);
        if (null != modules && !modules.isEmpty()) {
            root = modules.get(0);
        }
        hasAuthorityModule(root, subject);
        return root;
    }

    private void hasAuthorityModule(Module module, Subject subject) {
        if (null == module) {
            return;
        }
        List<Module> list = Lists.newArrayList();
        for (Module menu : module.getChildren()) {
            // 菜单未启用，不显示
            if (menu.getStatus() == 0) {
                continue;
            }
            // 只加入拥有show权限的Module
            String resource = getResource(menu, ShiroConsts.PERMISSION_SHOW_DEFAULT_CODE);
            if (subject.isPermitted(resource)) {
                hasAuthorityModule(menu, subject);
                list.add(menu);
            }
        }
        module.getChildren().clear();
        module.getChildren().addAll(list);
    }

    private String getResource(Module module, String resource) {
        if (module.getLevel() > (WebConsts.DEFAULT_ROOT_LEVEL + 1)) {
            resource = module.getCode() + ":" + resource;
            return getResource(module.getParent(), resource);
        } else {
            return module.getCode() + ":" + resource;
        }
    }
}
