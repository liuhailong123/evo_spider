package cn.com.evo.cms.web.api;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.SourceRelTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.web.api.vo.PictureApiVo;
import cn.com.evo.cms.web.api.vo.ShowContentApiVo;
import cn.com.evo.cms.web.voService.PictureVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专题接口
 *
 * @author shilinxiao
 */
@Controller
@RequestMapping("/api/section")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SectionApi extends BaseController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private PictureVoService pictureVoService;

    /**
     * 根据专题id获取专题下内容
     *
     * @return
     */
    @RequestMapping(value = "/contentList", method = {RequestMethod.GET, RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResultForAPI contentList(@RequestParam("appId") String appId,
                                        @RequestParam("sectionId") String sectionId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(sectionId);
            Section section = sectionService.findById(catalogueRelation.getBId());
            // 获取该专题背景图
            String bgPicture = getBgPicture(section.getId());

            // 获取该专题下内容List
            List<ShowContentApiVo> apiVos = Lists.newArrayList();
            List<Content> contents = contentService.findByColumnId(section.getId());
            for (Content content : contents) {
                List<PictureApiVo> pictures = pictureVoService.findPictureApiVosByContentIdAndBusinessType(content.getId(), BusinessTypeEnum.cover.getIndex());
                ShowContentApiVo apiVo = new ShowContentApiVo(content, pictures, catalogueRelation);
                apiVos.add(apiVo);
            }
            JSONObject result = new JSONObject();
            result.put("bgPicture", bgPicture);
            result.put("contents", apiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据专题id获取专题下内容时，发生异常！", e);
        }
        return dataRet;
    }

    private String getBgPicture(String sectionId) {
        //获取省网资源服务器配置
        Province provice = provinceService.getByEnable(1);
        String bgPicture = "";
        List<SourceRel> sourceRels = sourceRelService.findByFIdAndRelTypeAndSourcetypeAndBusinessType(sectionId,
                SourceRelTypeEnum.columnRel.getIndex(), SourceTypeEnum.image.getIndex(),
                BusinessTypeEnum.background.getIndex());
        if (sourceRels.size() > 0) {
            Picture picture = pictureService.findById(sourceRels.get(0).getSourceId());
            if (picture != null) {
                bgPicture = provice.getImageHost() + picture.getFileName();
            }
        }
        return bgPicture;
    }

}
