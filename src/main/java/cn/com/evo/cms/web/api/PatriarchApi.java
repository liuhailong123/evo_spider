package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.vip.UserLockRecord;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.vip.UserLockRecordService;
import cn.com.evo.cms.web.api.vo.AppLockApiVo;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.google.common.collect.Lists;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家长相关接口
 */
@Controller
@RequestMapping("/api/patriarch")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatriarchApi extends BaseController {

    @Autowired
    private AppLockConfService appLockConfService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private UserLockRecordService userLockRecordService;

    @Autowired
    private Mapper mapper;

    /**
     * 应用锁列表
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "/appLockList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI info(@RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            List<AppLockConf> entities = appLockConfService.findByAppId(appId);
            List<AppLockApiVo> lstVo = Lists.newArrayList();
            for (AppLockConf entity : entities) {
                AppLockApiVo vo = mapper.map(entity, AppLockApiVo.class);
                String contentName = "";
                if (entity.getContentType() == 1) {
                    Column column = columnService.findById(entity.getContentId());
                    contentName = column.getName();
                }
                vo.setContentName(contentName);
                lstVo.add(vo);
            }
            JSONObject result = new JSONObject();
            result.put("appLockList", lstVo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取应用加锁配置时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 用户加锁
     *
     * @param userId
     * @param appLockConfIds
     * @return
     */
    @RequestMapping(value = "/userAppLockSend", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI userAppLockSend(@RequestParam("userId") String userId,
                                            @RequestParam("appLockConfIds") String appLockConfIds) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            String[] appLockConfIdsArray = appLockConfIds.split(",");
            for (int i = 0; i < appLockConfIdsArray.length; i++) {
                UserLockRecord userLockRecord = userLockRecordService.findByUserIdAndAppLockConfId(userId, appLockConfIdsArray[i]);
                if (userLockRecord == null) {
                    userLockRecord = new UserLockRecord();
                    userLockRecord.setUserId(userId);
                    userLockRecord.setAppLockConfId(appLockConfIdsArray[i]);
                    userLockRecordService.save(userLockRecord);
                }
            }
            JSONObject result = new JSONObject();
            result.put("sendResult", "OK");
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据用户应用加锁上报时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 用户加锁列表
     *
     * @param userId
     * @param appId
     * @return
     */
    @RequestMapping(value = "/userAppLockList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI userAppLockList(@RequestParam("userId") String userId,
                                            @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            List<AppLockConf> entities = appLockConfService.findByAppId(appId);
            List<UserLockRecord> userLockRecords = userLockRecordService.findByUserId(userId);
            List<AppLockApiVo> lstVo = Lists.newArrayList();
            for (AppLockConf appLockConf : entities) {
                AppLockApiVo vo = mapper.map(appLockConf, AppLockApiVo.class);
                String contentName = "";
                if (appLockConf.getContentType() == 1) {
                    Column column = columnService.findById(appLockConf.getContentId());
                    contentName = column.getName();
                }
                vo.setContentName(contentName);
                vo.setStatus(0);
                for (UserLockRecord userLockRecord : userLockRecords) {
                    if (userLockRecord.getAppLockConfId().equals(appLockConf.getId())) {
                        vo.setStatus(1);
                        break;
                    }
                }
                lstVo.add(vo);
            }
            JSONObject result = new JSONObject();
            result.put("userAppLockList", lstVo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取用户应用加锁记录时，发生异常！", e);
        }
        return dataRet;
    }

}
