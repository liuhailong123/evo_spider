package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.LliveBroadcast;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.LliveBroadcastService;
import cn.com.evo.cms.web.api.vo.LiveApiVo;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 直播接口类
 */
@Controller
@RequestMapping("/api/live")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiveApi extends BaseController {
    @Autowired
    private LliveBroadcastService lliveBroadcastService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI info(@RequestParam("id") String id) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            LliveBroadcast entity = lliveBroadcastService.findById(catalogueRelation.getBId());
            if (entity != null) {
                LiveApiVo live = new LiveApiVo(entity, catalogueRelation);
                result.put("info", live);
                dataRet.setData(result);
                dataRet.pushOk("成功！");
            } else {
                dataRet.pushError("没有查到相关数据");
            }
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据id获取活动详情时，发生异常！", e);
        }
        return dataRet;
    }

}
