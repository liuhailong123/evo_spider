package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.app.Announcement;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.vip.NoticeConf;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.ContentTypeEnum;
import cn.com.evo.cms.service.app.AnnouncementService;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.service.vip.NoticeConfService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.web.api.vo.IndexApiVo;
import cn.com.evo.cms.web.api.vo.Notice;
import cn.com.evo.cms.web.api.vo.ShowContentApiVo;
import cn.com.evo.cms.web.voService.ContentVoService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页接口
 *
 * @author shilinxiao
 */
@Controller
@RequestMapping("/api/index")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IndexApi extends BaseController {
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private NoticeConfService noticeConfService;
    @Autowired
    private IndexConfService indexConfService;
    @Autowired
    private IndexConfChildService indexConfChildService;
    @Autowired
    private LliveBroadcastService lliveBroadcastService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ContentVoService contentVoService;
    @Autowired
    private SpService spService;

    /**
     * 获取应用首页内容(linux)
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    @ResponseBody
    public DataResultForAPI linuxIndex(@RequestParam("appId") String appId,
                                       @RequestParam(value = "userCardNo", required = false) String userCardNo,
                                       @RequestParam(value = "accesstoken", required = false) String accesstoken
    ) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            //公告
            result.put("notice", getNotice(userCardNo));
            //直播初始化
            JSONObject live = new JSONObject();
            live.put("id", "0");
            live.put("url", "");
            live.put("episodeNumber", "");

            List<IndexApiVo> indexApiVos = Lists.newArrayList();
            IndexConf indexConf = indexConfService.getByAppId(appId);
            List<IndexConfChild> indexConfChildList = indexConfChildService.findByIndexConfId(indexConf.getId());
            for (int i = 0; i < indexConfChildList.size(); i++) {
                IndexApiVo indexApiVo = new IndexApiVo(indexConfChildList.get(i));
                indexApiVos.add(indexApiVo);

                if (indexConfChildList.get(i).getType() == 1) {
                    //直播
                    LliveBroadcast lliveBroadcast = lliveBroadcastService.findById(indexConfChildList.get(i).getContentId());
                    if (lliveBroadcast != null) {
                        /**
                         * 添加媒资类型
                         */

                        live.put("contentType", "");
                        live.put("id", lliveBroadcast.getContentId());
                        /**
                         * 2019年06月03日11:21:31
                         * 增加剧集集数返回
                         */
                        live.put("episodeNumber", lliveBroadcast.getEpisodeNumber());
                        /**
                         * 2019年04月18日13:42:29
                         * 修改直播频道地址获取逻辑
                         * lu.xin
                         */
                        Video video = spService.getPlayUrl(lliveBroadcast.getContentId(), lliveBroadcast.getEpisodeNumber(), accesstoken, appId, null);
                        live.put("url", video.getUrl());
                        /**
                         * 2019年05月10日17:43:16
                         * 增加cdn1Url/cdn2Url/cdn3Url的返回（四川移动）
                         * lu.xin
                         */
                        JSONArray jsonArray = new JSONArray();
                        JSONObject json1 = new JSONObject();
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        json1.put("url", StringUtils.isBlank(video.getCdn1Url()) ? "" : video.getCdn1Url());
                        json2.put("url", StringUtils.isBlank(video.getCdn1Url()) ? "" : video.getCdn2Url());
                        json3.put("url", StringUtils.isBlank(video.getCdn1Url()) ? "" : video.getCdn3Url());
                        jsonArray.add(json1);
                        jsonArray.add(json2);
                        jsonArray.add(json3);
                        live.put("cdn", jsonArray);
                    }
                }
            }
            //首页各位置内容List
            result.put("positionList", indexApiVos);
            result.put("live", live);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("linux获取首页数据，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * ott 首页直播频道接口
     *
     * @param appId    应用id
     * @param columnId 栏目id
     * @return
     */
    @RequestMapping(value = "/ott/live", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI ottIndexLive(@RequestParam("appId") String appId,
                                         @RequestParam(value = "columnId") String columnId,
                                         @RequestParam(value = "accesstoken", required = false) String accesstoken) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                columnId = catalogueRelation.getBId();
            }
            Column entity = columnService.findById(columnId);
            // 根据栏目id获取直播频道数据
            List<CatalogueRelation> list = catalogueRelationService.findByAIdAndContentTypeAndPublishOrderBySortAsc(entity.getId(), ContentTypeEnum.live.getIndex());

            // vo转换
            List<ShowContentApiVo> apiVos = contentVoService.catalogueRelationsToShowContentApiVos(list, BusinessTypeEnum.recommend.getIndex());
            //token不为空，则调用省网获取播放串接口
            if(StringUtils.isNotBlank(accesstoken)){
                for (ShowContentApiVo apiVo : apiVos) {
                    String url = contentVoService.getLiveUrlByAccessToken(apiVo.getContentId(), accesstoken);
                    apiVo.setLiveUrl(url);
                }
            }
            result.put("live", apiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("ott获取首页直播频道，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * ott 首页栏目接口
     *
     * @param appId
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/ott/column", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI ottIndexColumn(@RequestParam("appId") String appId,
                                           @RequestParam(value = "columnId") String columnId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                columnId = catalogueRelation.getBId();
            }
            Column entity = columnService.findById(columnId);

            // 根据栏目id获取栏目数据
            List<CatalogueRelation> list = catalogueRelationService.findByAIdAndContentTypeAndPublishOrderBySortAsc(entity.getId(), ContentTypeEnum.column.getIndex());

            // vo转换
            List<ShowContentApiVo> apiVos = contentVoService.catalogueRelationsToShowContentApiVos(list, BusinessTypeEnum.cover.getIndex());

            result.put("column", apiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("ott获取首页栏目数据，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * ott 首页内容接口
     *
     * @param appId
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/ott/content", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI ottIndexContent(@RequestParam("appId") String appId,
                                            @RequestParam(value = "columnId") String columnId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                columnId = catalogueRelation.getBId();
            }
            Column entity = columnService.findById(columnId);

            List<CatalogueRelation> temps = Lists.newArrayList();
            // 根据栏目id获取内容数据
            List<CatalogueRelation> list = catalogueRelationService.findByAIdAndTypeAndIsPublishOrderBySortAsc(entity.getId(), 2);
            for (CatalogueRelation rel : list) {
                if (rel.getContentType() == ContentTypeEnum.live.getIndex() ||
                        rel.getContentType() == ContentTypeEnum.column.getIndex()) {
                    // 排除 直播和栏目内容
                } else {
                    temps.add(rel);
                }
            }

            // vo转换
            List<ShowContentApiVo> apiVos = contentVoService.catalogueRelationsToShowContentApiVos(temps, BusinessTypeEnum.recommend.getIndex());

            result.put("content", apiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("ott获取首页栏目数据，发生异常！", e);
        }
        return dataRet;
    }

    private List<Notice> getNotice(String userCardNo) {
        List<Announcement> announcements = announcementService.findByStartTimeAndEndTimeAndStatus();
        List<Notice> notices = Lists.newArrayList();
        if (announcements != null) {
            if (announcements.size() > 0) {
                for (int i = 0; i < announcements.size(); i++) {
                    notices.add(new Notice(announcements.get(i)));
                }
            }
        }


        if (StringUtils.isNotBlank(userCardNo)) {
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(userCardNo, 3, null);

            List<NoticeConf> noticeConfs = noticeConfService.findByStatus(1);
            for (NoticeConf noticeConf : noticeConfs) {
                Notice notice = new Notice();
                switch (noticeConf.getType()) {
                    case 1://缴费通知 2001
                        break;
                    case 2://服务次数查询通知 2002
                        //获取去用户借书剩余服务次数 待实现
                        notice.setId(noticeConf.getId());
                        notice.setContent(noticeConf.getInfo().replace("##", "20"));
                        notice.setType(2002);
                        notice.setCount(notice.getContent().length());
                        notice.setBlankUrl("");
                        break;
                    case 3://套餐预到期提醒通知 2003
                        break;
                    default:
                        break;
                }
                notices.add(notice);
            }
        }
        return notices;
    }
}
