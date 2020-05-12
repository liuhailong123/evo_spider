package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.app.App;
import cn.com.evo.cms.service.app.AppService;
import cn.com.evo.cms.web.api.vo.AppApiVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/app")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppApi extends BaseController {
    @Autowired
    private AppService appService;

    @Autowired
    private Mapper mapper;

    /**
     * 根据包名获取应用信息
     *
     * @param packageName
     * @return
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI info(@RequestParam("packageName") String packageName) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            App app = appService.getByPackageName(packageName);
            AppApiVo vo = mapper.map(app, AppApiVo.class);
            vo.setVersionInfos(getVersionInfos(app));

            JSONObject result = new JSONObject();
            result.put("app", vo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据包名获取应用信息，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 转换版本说明，String --> List<String>
     *
     * @param app
     * @return
     */
    private JSONArray getVersionInfos(App app) {
        JSONArray jsonArray = new JSONArray();
        String versionInfo = app.getVersionInfo();
        String[] strs = versionInfo.split("\\r");
        for (String str : strs) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("versionInfo", str.replaceAll("\\n", ""));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
