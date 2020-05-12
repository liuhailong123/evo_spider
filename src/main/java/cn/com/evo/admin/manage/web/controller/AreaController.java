package cn.com.evo.admin.manage.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.shiro.ShiroConsts;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.widget.TreeView;
import com.frameworks.core.web.widget.TreeView.NodeType;
import com.frameworks.core.web.widget.TreeView.State;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Area;
import cn.com.evo.admin.manage.domain.vo.AreaVo;
import cn.com.evo.admin.manage.service.AreaService;

@Controller
@RequestMapping("/manage/area")
public class AreaController extends BaseController {

    private static final String VIEW_PAGE = "manage/area/view";
    private static final String FORM_PAGE = "manage/area/form";
    private static final String SELECT_PAGE = "manage/area/select";

    @Autowired
    private AreaService areaService;

    @Autowired
    private Mapper mapper;

    protected AreaService getService() {
        return areaService;
    }

    @RequiresPermissions(value = {"Manage:Area:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:Area:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Area> specification = DynamicSpecifications.bySearchFilter(request, Area.class, null);
            List<Area> areas = getService().findByCondition(specification, webPage);
            List<AreaVo> lstVo = Lists.newArrayList();
            for (Area area : areas) {
                AreaVo vo = mapper.map(area, AreaVo.class);
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

//    @RequiresPermissions(value = {"Manage:Area:search"})
    @RequestMapping(value = "select", method = {RequestMethod.GET})
    public ModelAndView select(HttpServletRequest request) {
        return new ModelAndView(SELECT_PAGE);
    }
    
    @RequiresPermissions(value = {"Manage:Area:search"})
    @RequestMapping(value = "/tree", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<TreeView> tree(String id) {
        List<TreeView> treeViews = Lists.newArrayList();
        try {
            Account account = this.getLoginUser();
            boolean isSuperman = account.getSuperman() == ShiroConsts.SUPER_MAN;
            if (isSuperman) {
                if ("".equals(id)) {
                    TreeView rootTree = new TreeView();
                    rootTree.setId("0");
                    rootTree.setParent("#");
                    rootTree.setText("所有区域");
                    rootTree.setChildren(true);
                    rootTree.setType(NodeType.folder);
                    rootTree.setIcon(TreeView.ICON_FOLDER);
                    rootTree.setLevel("-1");
                    State rootState = new State();
                    rootState.setOpened(true);
                    rootState.setSelected(false);
                    rootTree.setState(rootState);
                    treeViews.add(rootTree);
                } else {
                    if ("0".equals(id)) {
                        id = null;
                    }
                    List<Area> areas = getService().findByParentId(id);
                    for (Area area : areas) {
                        TreeView treeView = new TreeView();
                        treeView.setId(area.getId().toString());
                        Area parent = area.getParent();
                        treeView.setParent(parent == null ? "0" : parent.getId().toString());
                        treeView.setText(area.getName());
                        treeView.setChildren(area.getChildren().isEmpty() ? false : true);
                        treeView.setType(treeView.getChildren() ? NodeType.folder : NodeType.item);
                        switch (treeView.getType()) {
                            case folder:
                                treeView.setIcon(TreeView.ICON_FOLDER);
                                break;
                            case item:
                                treeView.setIcon(TreeView.ICON_ITEM);
                                break;
                            default:
                                break;
                        }
                        treeView.setLevel(String.valueOf(area.getLevel()));
                        State state = new State();
                        if ("1".equals(area.getId())) {
                            state.setOpened(true);
                            state.setSelected(true);
                        }
                        treeView.setState(state);
                        treeViews.add(treeView);
                    }
                }
            } else {
                Area rootArea = account.getOrganization().getArea();
                if ("".equals(id)) {
                    TreeView treeView = new TreeView();
                    treeView.setId(rootArea.getId().toString());
                    treeView.setParent("#");
                    treeView.setText(rootArea.getName());
                    List<Area> children = this.areaService.findByParentId(rootArea.getId());
                    treeView.setChildren(children.isEmpty() ? false : true);
                    treeView.setType(treeView.getChildren() ? NodeType.folder : NodeType.item);
                    switch (treeView.getType()) {
                        case folder:
                            treeView.setIcon(TreeView.ICON_FOLDER);
                            break;
                        case item:
                            treeView.setIcon(TreeView.ICON_ITEM);
                            break;
                        default:
                            break;
                    }
                    treeView.setLevel(String.valueOf(rootArea.getLevel()));
                    State state = new State();
                    state.setOpened(true);
                    state.setSelected(false);
                    treeView.setState(state);
                    treeViews.add(treeView);
                } else {
                    List<Area> areas = getService().findByParentId(id);
                    for (Area area : areas) {
                        TreeView treeView = new TreeView();
                        treeView.setId(area.getId().toString());
                        Area parent = area.getParent();
                        treeView.setParent(parent.getId().toString());
                        treeView.setText(area.getName());
                        treeView.setChildren(area.getChildren().isEmpty() ? false : true);
                        treeView.setType(treeView.getChildren() ? NodeType.folder : NodeType.item);
                        switch (treeView.getType()) {
                            case folder:
                                treeView.setIcon(TreeView.ICON_FOLDER);
                                break;
                            case item:
                                treeView.setIcon(TreeView.ICON_ITEM);
                                break;
                            default:
                                break;
                        }
                        treeView.setLevel(String.valueOf(area.getLevel()));
                        State state = new State();
                        if ("1".equals(area.getId())) {
                            state.setOpened(true);
                            state.setSelected(true);
                        }
                        treeView.setState(state);
                        treeViews.add(treeView);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取区域树异常！", e);
        }
        return treeViews;
    }

    @RequiresPermissions(value = {"Manage:Area:search"})
    @RequestMapping(value = "/areaTree", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<TreeView> areaTree(String id) {
        List<TreeView> treeViews = Lists.newArrayList();
        try {
            if ("".equals(id)) {
                TreeView rootTree = new TreeView();
                rootTree.setId("0");
                rootTree.setParent("#");
                rootTree.setText("所有区域");
                rootTree.setChildren(true);
                rootTree.setType(NodeType.folder);
                rootTree.setIcon(TreeView.ICON_FOLDER);
                rootTree.setLevel("-1");
                State rootState = new State();
                rootState.setOpened(true);
                rootState.setSelected(false);
                rootTree.setState(rootState);
                treeViews.add(rootTree);
            } else {
                if ("0".equals(id)) {
                    id = null;
                }
                List<Area> areas = getService().findByParentId(id);
                for (Area area : areas) {
                    TreeView treeView = new TreeView();
                    treeView.setId(area.getId().toString());
                    Area parent = area.getParent();
                    treeView.setParent(parent == null ? "0" : parent.getId().toString());
                    treeView.setText(area.getName());
                    treeView.setChildren(area.getChildren().isEmpty() ? false : true);
                    treeView.setType(treeView.getChildren() ? NodeType.folder : NodeType.item);
                    switch (treeView.getType()) {
                        case folder:
                            treeView.setIcon(TreeView.ICON_FOLDER);
                            break;
                        case item:
                            treeView.setIcon(TreeView.ICON_ITEM);
                            break;
                        default:
                            break;
                    }
                    treeView.setLevel(String.valueOf(area.getLevel()));
                    State state = new State();
                    if ("1".equals(area.getId())) {
                        state.setOpened(true);
                        state.setSelected(true);
                    }
                    treeView.setState(state);
                    treeViews.add(treeView);
                }
            }
        } catch (Exception e) {
            logger.error("获取区域树异常！", e);
        }
        return treeViews;
    }

    @RequiresPermissions(value = {"Manage:Area:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("area", new Area());
        return mav;
    }

    @RunLogger(value = "添加区域信息", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Area:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Area entity) {
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

    @RequiresPermissions(value = {"Manage:Area:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Area area = getService().findById(id);
        mav.addObject("area", area);
        return mav;
    }

    @RunLogger(value = "编辑区域信息", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Area:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Area entity) {
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

    @RunLogger(value = "删除区域信息", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Area:remove"})
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

    @RunLogger(value = "批量删除区域信息", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Area:remove"})
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

    // 数据初始化
    @RequestMapping(value = "/init", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult init() {
        MsgResult msgRet = new MsgResult();
        // 打开文件
        File file = new File("E:\\address2.js");
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        Area china = areaService.findById("1");
        try {

            JSONArray allProvince = JSONArray.parseArray(buffer.toString());
            for (int i = 0; i < allProvince.size(); i++) {
                JSONObject province = (JSONObject) allProvince.get(i);
                Area p = new Area();
                p.setName(province.getString("name"));
                p.setParent(china);
                p.setLevel(2);
                p.setPriority(i + 1);
                areaService.save(p);

                JSONArray allCity = province.getJSONArray("children");
                for (int j = 0; j < allCity.size(); j++) {
                    JSONObject city = (JSONObject) allCity.get(j);
                    Area c = new Area();
                    c.setName(city.getString("name"));
                    c.setParent(p);
                    c.setLevel(2);
                    c.setPriority(j + 1);
                    areaService.save(c);

                    JSONArray allDistrict = city.getJSONArray("children");
                    for (int k = 0; k < allDistrict.size(); k++) {
                        JSONObject district = (JSONObject) allDistrict.get(k);
                        Area d = new Area();
                        d.setName(district.getString("name"));
                        d.setParent(c);
                        d.setLevel(3);
                        d.setPriority(k + 1);
                        areaService.save(d);

                        JSONArray allCounty = district.getJSONArray("children");
                        for (int l = 0; l < allCounty.size(); l++) {
                            JSONObject county = (JSONObject) allCounty.get(l);
                            Area c1 = new Area();
                            c1.setName(county.getString("name"));
                            c1.setParent(d);
                            c1.setLevel(4);
                            c1.setPriority(l + 1);
                            areaService.save(d);
                        }
                    }
                }
            }
            msgRet.pushOk("ok");
        } catch (Exception e) {
            // TODO: handle exception
            msgRet.pushError("error");
        }
        return msgRet;

    }
}
