package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.integration.xjdx.content.model.Program;
import com.frameworks.core.service.BaseService;
import com.github.stuxuhai.jpinyin.PinyinException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService extends BaseService<Content, String> {

    /**
     * 内容发布时 直接保存内容
     *
     * @param entity
     * @param columnId
     * @param publishSort
     */
    void save(Content entity, String columnId, int publishSort);

    /**
     * 内容发布时 更新内容
     *
     * @param entity
     * @param contentVideo
     * @param contentMusic
     * @param contentImage
     * @param contentPicture
     * @param columnId
     * @param publishSort
     */
    void update(Content entity, String contentVideo, String contentMusic, String contentImage, String contentPicture,
                String columnId, int publishSort);


    void update(Content entity, String contentImage, String contentVideo);

    /**
     * 数据迁移更新id
     *
     * @param contentId
     * @param oldContentId
     */
    void dataMoveUpdate(String contentId, String oldContentId);

    /**
     * 根据栏目id获取内容list
     *
     * @param columnId
     * @return
     */
    List<Content> findByColumnId(String columnId);

    Content getByNamePublish(String name);

    Content getByName(String name);

    List<Content> findByName(String name);

    /**
     * 根据父Id获取子集内容总条数
     *
     * @param episodeId
     * @return
     */
    Long findByPIdTotal(String episodeId);

    List<Content> findByPIdOrderBySortAsc(String contentId);

    List<Content> findByPIdAndClassifyOrderBySortAsc(String contentId, Integer classify);

    List<Content> findByCode(String code);

    List<Content> findByNameAndClassify(String name, Integer classify);

    /**
     * 内容数据导入
     *
     * @param files
     */
    void importFile(MultipartFile[] files);

    /**
     * 内容数据导入
     *
     * @param files
     */
    List<Program> xjdxImportFile(MultipartFile[] files);

    /**
     * 获取某集内容对象(对于剧集)
     *
     * @param catalogueRelationId 当前内容id
     * @param number              当前集数
     * @return
     */
    Content findContentByNumber(String catalogueRelationId, Integer number);

    void setPinYin(Content entity) throws PinyinException;

    /**
     * 自动绑定视频资源和图片资源关系
     *
     * @param content
     */
    void autoBindVideoAndImageRel(Content content);

    /**
     * 刷新整个内容库的检索字段
     */
    void refresh();

    /**
     * 通过父id和注入状态获取集合
     * @param pId
     * @param synType
     * @return
     */
    List<Content> findByPIdAndSynType(String pId, Integer synType);

    Content findByVideoId(String assetId);
}
