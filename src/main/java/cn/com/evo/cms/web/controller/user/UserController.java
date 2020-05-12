package cn.com.evo.cms.web.controller.user;

import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.vo.cms.UserServerOpenProgress;
import cn.com.evo.cms.domain.vo.vip.UserVo;
import cn.com.evo.cms.service.vip.UserService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user/userInfo")
public class UserController extends BaseController {
    private static final String VIEW_PAGE = "cms/user/userInfo/view";
    private static final String FORM_PAGE = "cms/user/userInfo/form";
    private static final String IMPORT_PAGE = "cms/user/userInfo/import";
    private static final String SERVER_OPEN_PAGE = "cms/user/userInfo/serverOpen";

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    protected UserService getService() {
        return userService;
    }

    @RequiresPermissions(value = {"User:UserInfo:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"User:UserInfo:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<User> specification = DynamicSpecifications.bySearchFilter(request, User.class, null);
            List<User> entities = getService().findByCondition(specification, webPage);
            List<UserVo> lstVo = Lists.newArrayList();
            for (User entity : entities) {
                UserVo vo = mapper.map(entity, UserVo.class);
                lstVo.add(vo);
            }
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());
            dataRet.setRows(lstVo);
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"User:UserInfo:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(User entity) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(entity);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"User:UserInfo:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        User entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(User entity) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().update(entity);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:remove"})
    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteById(id);
            msgRet.pushOk("删除成功！");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量删除", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:remove"})
    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteByIds(ids);
            msgRet.pushOk("批量删除成功!");
        } catch (Exception e) {
            msgRet.pushError("批量删除失败：" + e.getMessage());
            logger.error("批量删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"User:UserInfo:show"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.GET})
    public ModelAndView dataImportPage() {
        ModelAndView mav = new ModelAndView(IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "会员信息导入", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:modify"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult dataImport(@RequestParam("files") MultipartFile[] files, @RequestParam("type") Integer type) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length == 0) {
                msgRet.pushError("模板文件不能为空");
                return msgRet;
            }
            userService.dataImport(files, type);

            msgRet.pushOk("会员信息导入成功！");
        } catch (Exception e) {
            msgRet.pushError("会员信息导入失败：" + e.getMessage());
            logger.error("会员信息导入时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"User:UserInfo:show"})
    @RequestMapping(value = "/serverOpen", method = {RequestMethod.GET})
    public ModelAndView serverOpenPage() {
        ModelAndView mav = new ModelAndView(SERVER_OPEN_PAGE);
        return mav;
    }

    @RunLogger(value = "服务开通", isSaveRequest = true)
    @RequiresPermissions(value = {"User:UserInfo:modify"})
    @RequestMapping(value = "/serverOpen", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult serverOpen(@RequestParam("files") MultipartFile[] files) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length == 0) {
                msgRet.pushError("模板文件不能为空");
                return msgRet;
            }
            userService.serverOpen(files);
            msgRet.pushOk("会员服务开通成功！");
        } catch (Exception e) {
            msgRet.pushError("会员服务开通失败：" + e.getMessage());
            logger.error("会员服务开通时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "读取服务开通进度", isSaveRequest = true)
    @RequestMapping(value = "/serverOpenProgress", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult serverOpenProgress() {
        DataResult dataResult = new DataResult();
        try {
            JSONObject obj = new JSONObject();
            obj.put("total", UserServerOpenProgress.getTotal());
            obj.put("success", UserServerOpenProgress.getSuccess());
            obj.put("fail", UserServerOpenProgress.getFail());
            dataResult.setRows(obj);
            dataResult.pushOk("读取服务开通进度成功！");
        } catch (Exception e) {
            dataResult.pushError("读取服务开通进度失败：" + e.getMessage());
            logger.error("读取服务开通进度时，发生异常！", e);
        }
        return dataResult;
    }

}
