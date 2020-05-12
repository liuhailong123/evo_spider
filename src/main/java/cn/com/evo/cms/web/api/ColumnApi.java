package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.web.api.vo.ColumnApiVo;
import cn.com.evo.cms.web.api.vo.PictureApiVo;
import cn.com.evo.cms.web.voService.PictureVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResultForAPI;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.search.SearchFilter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 栏目接口
 *
 * @author shilinxiao
 */
@Controller
@RequestMapping("/api/column")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ColumnApi extends BaseController {

    @Autowired
    private ColumnService columnService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private PictureVoService pictureVoService;

    /**
     * 根据目录id获取子目录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/childs", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI list(@RequestParam("id") String id) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            String columnId;
            if (catalogueRelation != null) {
                // 目录id
                columnId = catalogueRelation.getBId();
            } else {
                // 目录id
                columnId = id;
            }

            List<Column> entitys = columnService.findByPId(columnId);
            List<ColumnApiVo> columnApiVos = Lists.newArrayList();
            Boolean haveChild = false;
            if (entitys != null) {
                if (entitys.size() > 0) {
                    haveChild = true;
                    for (Column column : entitys) {
                        List<PictureApiVo> pictureApiVos = pictureVoService.getPictureApiVos(column.getId());
                        ColumnApiVo vo = new ColumnApiVo(column, pictureApiVos);
                        columnApiVos.add(vo);
                    }
                }
            }
            JSONObject result = new JSONObject();
            Column column = columnService.findById(id);
            result.put("name", column.getName());
            result.put("hasChild", haveChild);
            result.put("columns", columnApiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据目录id获取子目录数据时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 根据栏目id获取推荐栏目（分页）
     *
     * @param columnId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/recommendByColumnId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI recommendByColumnId(@RequestParam("columnId") String columnId,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam("pageNum") Integer pageNum) {
        DataResultForAPI dataRet = new DataResultForAPI();
        List<ColumnApiVo> columnApiVos = Lists.newArrayList();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                // 目录id
                columnId = catalogueRelation.getBId();
            } else {
                // 目录id
                columnId = columnId;
            }

            Column column = columnService.findById(columnId);
            if (column == null) {
                dataRet.pushError("栏目不存在");
                return dataRet;
            }

            Pager page = new Pager();
            page.setPageNumber(pageNum);
            page.setPageSize(pageSize);
            page.setSortName("sort");
            page.setSortOrder("asc");

            List<SearchFilter> filters = Lists.newArrayList();
            filters.add(new SearchFilter("parent.id", SearchFilter.Operator.EQ, columnId));
            filters.add(new SearchFilter("isRecommend", SearchFilter.Operator.EQ, 1));
            filters.add(new SearchFilter("enable", SearchFilter.Operator.EQ, 1));
            Specification<Column> specification = DynamicSpecifications.bySearchFilter(Column.class, filters);

            List<Column> columns = columnService.findByCondition(specification, page);

            for (Column entity : columns) {
                List<PictureApiVo> pictureApiVos = pictureVoService.getPictureApiVos(column.getId());
                ColumnApiVo vo = new ColumnApiVo(entity, pictureApiVos);
                columnApiVos.add(vo);
            }

            JSONObject result = new JSONObject();
            result.put("recommendColumns", columnApiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据栏目id 获取推荐栏目时，发生异常！", e);
        }
        return dataRet;
    }
}
