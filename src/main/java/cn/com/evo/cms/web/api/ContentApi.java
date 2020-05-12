package cn.com.evo.cms.web.api;

import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.entity.total.ContentTotal;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.UserActionTypeEnum;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.SpService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.spider.SpiderContentService;
import cn.com.evo.cms.service.total.ContentTotalService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.utils.QrCodeUtils;
import cn.com.evo.cms.web.api.vo.*;
import cn.com.evo.cms.web.voService.BookInfoVoService;
import cn.com.evo.cms.web.voService.ContentVoService;
import cn.com.evo.cms.web.voService.PictureVoService;
import cn.com.evo.cms.web.voService.VideoVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResultForAPI;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.search.SearchFilter;
import com.frameworks.core.web.search.SearchFilter.Operator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 内容接口
 *
 * @author shilinxiao
 */
@Controller
@RequestMapping("/api/content")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContentApi extends BaseController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ContentTotalService contentTotalService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private PictureVoService pictureVoService;
    @Autowired
    private VideoVoService videoVoService;
    @Autowired
    private ContentVoService contentVoService;
    @Autowired
    private BookInfoVoService bookInfoVoService;
    @Autowired
    private SpiderContentService spiderContentService;
    @Autowired
    private SpService spService;

    @RequestMapping(value = "/bookInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI bookInfo(@RequestParam("bookId") String bookId,
                                     @RequestParam("width") Integer width,
                                     @RequestParam("height") Integer height,
                                     @RequestParam(name = "cardNo", required = false) String cardNo,
                                     @RequestParam(name = "appId", required = false) String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(bookId);
            BookInfo bookInfo = bookInfoService.findById(catalogueRelation.getBId());
            // 获取图书海报
            List<PictureApiVo> pictures = pictureVoService.findPictureApiVosByContentIdAndBusinessType(bookInfo.getId(), BusinessTypeEnum.cover.getIndex());
            BookApiVo vo = new BookApiVo(bookInfo, catalogueRelation, pictures);

            // 处理图书二维码
            JSONObject params = new JSONObject();
            params.put("bookId", bookInfo.getId());
            params.put("bookNumber", bookInfo.getNumber());

            //图书二维码 code = 003  代表sp套餐绑定并跳转图书详情页的情况
            String qrCodeUrl = Constant.WXxcxPath + "redirect?code=003&params=" + URLEncoder.encode(params.toJSONString(), "utf-8");
            String qrCode = QrCodeUtils.base64EncodeForQR(qrCodeUrl, width, height);
            vo.setQrCode(qrCode);
            vo.setQrCodeUrl(qrCodeUrl);

            JSONObject result = new JSONObject();
            result.put("book", vo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据id获取图书详情时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 根据id获取电影详情
     *
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI info(@RequestParam("contentId") String contentId,
                                 @RequestParam(value = "cardNo", required = false) String cardNo) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(contentId);
            Content content = contentService.findById(catalogueRelation.getBId());
            List<PictureApiVo> pictures = pictureVoService.findPictureApiVosByContentIdAndBusinessType(content.getId(),
                    BusinessTypeEnum.cover.getIndex());

            ContentApiVo vo = new ContentApiVo(content, catalogueRelation, pictures);

            JSONObject result = new JSONObject();
            result.put("content", vo);
            if (cardNo != null) {
                result.put("user", getUser(contentId, cardNo));
            }
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id获取内容详情时，发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 根据id获取剧集详情 - 子集信息为分页返回
     *
     * @param episodeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/episode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI episode(@RequestParam("episodeId") String episodeId,
                                    @RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam(value = "cardNo", required = false) String cardNo,
                                    @RequestParam(value = "appId", required = false) String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            // 获取剧集信息
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(episodeId);
            Content episode = contentService.findById(catalogueRelation.getBId());

            // 获取剧集海报
            List<PictureApiVo> pictures = pictureVoService.findPictureApiVosByContentIdAndBusinessType(episode.getId(), BusinessTypeEnum.cover.getIndex());

            // 获取剧集子集List
            Integer start = (pageNum - 1) * pageSize;
            List<EpisodeChildApiVo> childApiVos = contentVoService.findByPIdByPage(episode.getId(), pageNum, pageSize);

            // 获取子集总集数
            Long total = contentService.findByPIdTotal(episode.getId());

            // 获取栏目是否限免
            boolean isLimitFree = limitFreeService.isFree(catalogueRelation.getAId(), appId);
            EpisodeApiVo apiVo = new EpisodeApiVo(episode, catalogueRelation, pictures, total, isLimitFree);
            apiVo.setChilds(childApiVos);

            JSONObject result = new JSONObject();
            result.put("episode", apiVo);

            if (cardNo != null) {
                result.put("user", getUser(episodeId, cardNo));
            }

            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据剧集id获取剧集详情时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 根据id获取剧集详情 - 子集信息为非分页返回
     *
     * @param episodeId
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/episodeNoPage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI episodeNoPage(@RequestParam("episodeId") String episodeId,
                                          @RequestParam(value = "cardNo", required = false) String cardNo,
                                          @RequestParam(value = "appId", required = false) String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            // 获取剧集信息
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(episodeId);
            Content episode = contentService.findById(catalogueRelation.getBId());

            // 获取剧集海报
            List<PictureApiVo> pictures = pictureVoService.findPictureApiVosByContentIdAndBusinessType(episode.getId(), BusinessTypeEnum.cover.getIndex());

            // 根据总集id获取剧集子集列表
            List<EpisodeChildApiVo> childApiVos = contentVoService.findByPId(episode.getId());

            // 获取栏目是否限免
            boolean isLimitFree = limitFreeService.isFree(catalogueRelation.getAId(), appId);
            EpisodeApiVo apiVo = new EpisodeApiVo(episode, catalogueRelation, pictures, Long.valueOf(childApiVos.size()), isLimitFree);
            apiVo.setChilds(childApiVos);

            JSONObject result = new JSONObject();
            result.put("episode", apiVo);

            if (cardNo != null) {
                result.put("user", getUser(episodeId, cardNo));
            }

            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据剧集id获取剧集详情时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 获取内容下视频
     * 剧集获取内容详情时会出现没有CatalogueRelation对象的情况
     *
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/videos", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI videos(@RequestParam("contentId") String contentId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(contentId);
            if (catalogueRelation != null) {
                contentId = catalogueRelation.getBId();
            }
            result.put("videos", videoVoService.findByContentId(contentId));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取内容下视频时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 根据目录ID获取目录下内容列表
     *
     * @param columnId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/listByColumnId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI listByColumnId(@RequestParam("columnId") String columnId,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("pageNum") Integer pageNum) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                columnId = catalogueRelation.getBId();
            }

            List<ShowContentApiVo> apiVos = Lists.newArrayList();
            Pager page = new Pager();
            page.setPageNumber(pageNum);
            page.setPageSize(pageSize);
            page.setSortName("sort");
            page.setSortOrder("asc");
            List<SearchFilter> filters = Lists.newArrayList();
            filters.add(new SearchFilter("aId", Operator.EQ, columnId));
            filters.add(new SearchFilter("publish", Operator.EQ, 1));
            Specification<CatalogueRelation> specification = DynamicSpecifications
                    .bySearchFilter(CatalogueRelation.class, filters);
            List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByCondition(specification, page);

            // vo转换
            apiVos = contentVoService.catalogueRelationsToShowContentApiVos(catalogueRelations, BusinessTypeEnum.cover.getIndex());

            JSONObject result = new JSONObject();
            Column column = columnService.findById(columnId);
            result.put("name", column.getName());
            result.put("contents", apiVos);
            result.put("total", page.getTotalCount());
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据目录id获取子目录数据时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 搜索
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param appId
     * @return
     */
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI search(@RequestParam("keyword") String keyword,
                                   @RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            List<ShowContentApiVo> vos = contentVoService.searchByKeyword(keyword, pageNum, pageSize, appId);
            if (vos != null) {
                if (vos.size() > 0) {
                    //搜索关键字记录
                    contentTotalService.saveKeyword(keyword, appId);
                }
            }
            result.put("contents", vos);
            result.put("total", contentVoService.searchByKeywordTotal(keyword, appId));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据keyword搜索时，发生异常！", e);
        }

        return dataRet;
    }


    /**
     * 获取用户播放次数最多的 内容列表
     *
     * @param appId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/likeLook", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI videos(@RequestParam("appId") String appId,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("pageNum") Integer pageNum) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            result.put("contents", contentVoService.findBylikeLook(appId, pageSize, pageNum));
            result.put("total", contentVoService.findBylikeLookTotal(appId));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取播放次数最多的内容列表接口时，发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 根据内容id 获取推荐内容
     * （基于mysql全文检索）
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param appId
     * @return
     */
    @RequestMapping(value = "/recommend", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI recommend(@RequestParam("id") String id,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            Content content = contentService.findById(catalogueRelation.getBId());

            JSONObject result = new JSONObject();
            result.put("recommend", contentVoService.getRecommend(content, pageNum, pageSize, appId));
            result.put("total", contentVoService.getRecommendTotal(content, appId));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取推荐内容时，发生异常！", e);
        }

        return dataRet;
    }

    /**
     * 根据内容id，获取随机数量的推荐内容(视频)
     *
     * @param id
     * @param dataSize
     * @param appId
     * @return
     */
    @RequestMapping(value = "/recommendRandom", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI recommendRandom(@RequestParam("id") String id,
                                            @RequestParam("dataSize") Integer dataSize,
                                            @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            JSONObject result = new JSONObject();
            result.put("recommend", contentVoService.getRecommendRandom(catalogueRelation.getBId(), appId, dataSize));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取随机推荐内容时，发生异常！", e);
        }

        return dataRet;
    }


    /**
     * 根据图书id 获取推荐图书
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param appId
     * @return
     */
    @RequestMapping(value = "/bookRecommend", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI bookRecommend(@RequestParam("id") String id,
                                          @RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            JSONObject result = new JSONObject();
            result.put("recommend", bookInfoVoService.getRecommend(catalogueRelation.getBId(), pageNum, pageSize, appId));
            result.put("total", bookInfoVoService.getRecommendTotal(catalogueRelation.getBId(), appId));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取推荐内容时，发生异常！", e);
        }

        return dataRet;
    }

    /**
     * 根据图书id获取随机推荐图书
     *
     * @param id
     * @param dataSize
     * @param appId
     * @return
     */
    @RequestMapping(value = "/bookRecommendRandom", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI bookRecommendRandom(@RequestParam("id") String id,
                                                @RequestParam("dataSize") Integer dataSize,
                                                @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            JSONObject result = new JSONObject();
            result.put("recommend", bookInfoVoService.getBookRecommendRandom(catalogueRelation.getBId(), appId, dataSize));
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取随机图书推荐内容时，发生异常！", e);
        }

        return dataRet;
    }

    /**
     * 根据栏目id 获取推荐内容
     *
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/recommendByColumnId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI recommendByColumnId(@RequestParam("columnId") String columnId,
                                                @RequestParam("pageNum") Integer pageNum,
                                                @RequestParam("pageSize") Integer pageSize) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            Column column = columnService.findById(catalogueRelation.getAId());
            if (column == null) {
                dataRet.pushError("栏目不存在");
                return dataRet;
            }
            // 获取推荐内容
            List<CatalogueRelation> list = catalogueRelationService.findLikeByColumnCode(column.getColumnCode(), pageSize, pageNum);
            // vo转化
            List<ShowContentApiVo> apiVos = contentVoService.catalogueRelationsToShowContentApiVos(list, BusinessTypeEnum.cover.getIndex());

            JSONObject result = new JSONObject();
            result.put("recommendContents", apiVos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据内容id 获取推荐内容时，发生异常！", e);
        }

        return dataRet;
    }

    /**
     * 退出推荐接口
     * 根据目录ID获取目录下内容列表
     *
     * @param columnId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/outRecommend", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI outRecommend(@RequestParam("columnId") String columnId,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam("pageNum") Integer pageNum) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(columnId);
            if (catalogueRelation != null) {
                columnId = catalogueRelation.getBId();
            }
            Column column = columnService.findById(columnId);

            Pager page = new Pager();
            page.setPageNumber(pageNum);
            page.setPageSize(pageSize);
            page.setSortName("sort");
            page.setSortOrder("asc");
            List<SearchFilter> filters = Lists.newArrayList();
            filters.add(new SearchFilter("aId", Operator.EQ, column.getId()));
            filters.add(new SearchFilter("publish", Operator.EQ, 1));
            Specification<CatalogueRelation> specification = DynamicSpecifications
                    .bySearchFilter(CatalogueRelation.class, filters);
            List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByCondition(specification, page);

            // vo转换
            List<ShowContentApiVo> apiVos = contentVoService.catalogueRelationsToShowContentApiVos(catalogueRelations, BusinessTypeEnum.cover.getIndex());

            JSONObject result = new JSONObject();
            result.put("name", column.getName());
            result.put("contents", apiVos);
            result.put("total", page.getTotalCount());
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据目录id获取退出推荐数据时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 获取播放地址
     *
     * @param contentId 内容id
     * @param number    集数
     * @param appId     应用id
     * @param platform  平台来源
     * @param cardNo    智能卡号
     * @return
     */
    @RequestMapping(value = "/playUrl", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI playUrl(@RequestParam("contentId") String contentId,
                                    @RequestParam("number") Integer number,
                                    @RequestParam("appId") String appId,
                                    @RequestParam("platform") String platform,
                                    @RequestParam("cardNo") String cardNo) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            // 获取播放地址
            Video video = spService.getPlayUrl(contentId, number, null, appId, platform);
            String playUrl = "";
            String name = "";
            if (video != null) {
                playUrl = video.getUrl();
                name = video.getSource().getName();
            }
            JSONObject result = new JSONObject();

            result.put("playUrl", playUrl);
            result.put("name", name);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取播放地址，发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 获取用户对当前内容的行为数据
     *
     * @param contentId
     * @param cardNo
     * @return
     */
    private Object getUser(String contentId, String cardNo) {
        JSONObject user = new JSONObject();
        UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
        if (userAccount != null) {
            //播放集数 时间点
            Map<String, Integer> userPlay = getUserPlay(userAccount.getUserId(), contentId);
            user.put("number", userPlay.get("number"));
            user.put("duration", userPlay.get("duration"));
            //是否收藏
            user.put("isCollect", getIsCollect(userAccount.getUserId(), contentId));
            //是否加入黑名单
            user.put("isBlack", getIsBlack(userAccount.getUserId(), contentId));
        }
        return user;
    }

    /**
     * 根据内容id、用户id获取 内容播放集数和时间点
     *
     * @param userId
     * @param contentId
     * @return
     */
    private Map<String, Integer> getUserPlay(String userId, String contentId) {
        //集数
        Integer number = 1;
        //时长
        Integer duration = 0;

        ContentTotal contentTotal = contentTotalService.getByUserIdAndContentId(userId, contentId, UserActionTypeEnum.playRecord.getIndex());
        if (contentTotal != null) {
            number = contentTotal.getNumber();
            duration = Integer.valueOf(contentTotal.getDuration());
        }

        Map<String, Integer> playRecord = Maps.newHashMap();
        playRecord.put("number", number);
        playRecord.put("duration", duration);
        return playRecord;
    }

    /**
     * 根据内容id、用户id获取 内容是否加入收藏
     *
     * @param userId
     * @return
     */
    private Integer getIsCollect(String userId, String contentId) {
        Integer isCollect = 0;
        ContentTotal contentTotal = contentTotalService.getByUserIdAndContentId(userId, contentId, UserActionTypeEnum.likeRecord.getIndex());
        if (contentTotal != null) {
            isCollect = 1;
        }
        return isCollect;
    }

    /**
     * 根据内容id、用户id获取 内容是否加入黑名单
     *
     * @param userId
     * @param contentId
     * @return
     */
    private Integer getIsBlack(String userId, String contentId) {
        Integer isCollect = 0;
        ContentTotal contentTotal = contentTotalService.getByUserIdAndContentId(userId, contentId, UserActionTypeEnum.blackRecord.getIndex());
        if (contentTotal != null) {
            isCollect = 1;
        }
        return isCollect;
    }
}
