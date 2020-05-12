package cn.com.evo.admin.manage.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.DictData;
import cn.com.evo.admin.manage.domain.vo.DictDataVo;
import cn.com.evo.admin.manage.service.DictDataService;

@Controller
@RequestMapping("/manage/dictionary/data")
public class DictDataController extends BaseController {
    private static final String VIEW_PAGE = "manage/dictionary/data/view";
    private static final String FORM_PAGE = "manage/dictionary/data/form";

    @Autowired
    private DictDataService dictClassifyService;

    @Autowired
    private Mapper mapper;

    protected DictDataService getService() {
        return dictClassifyService;
    }

    @RequiresPermissions(value = { "Manage:Dictionary:DictData:show" })
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = { "Manage:Dictionary:DictData:search" })
    @RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<DictData> specification = DynamicSpecifications.bySearchFilter(request, DictData.class, null);
            List<DictData> dictDatas = getService().findByCondition(specification, webPage);
            List<DictDataVo> lstVo = Lists.newArrayList();
            for (DictData dictData : dictDatas) {
                DictDataVo vo = mapper.map(dictData, DictDataVo.class);
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

    @RequiresPermissions(value = { "Manage:Dictionary:DictData:show" })
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加字典数据", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Dictionary:DictData:add" })
    @RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult store(DictData entity) {
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

    @RequiresPermissions(value = { "Manage:Dictionary:DictData:show" })
    @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        DictData data = getService().findById(id);
        mav.addObject("data", data);
        return mav;
    }

    @RunLogger(value = "编辑字典数据", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Dictionary:DictData:modify" })
    @RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult modify(DictData entity) {
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

    @RunLogger(value = "删除字典数据", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Dictionary:DictData:remove" })
    @RequestMapping(value = "/remove/{id}", method = { RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
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

    @RunLogger(value = "批量字典数据", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Dictionary:DictData:remove" })
    @RequestMapping(value = "/remove", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
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
}
