package cn.com.evo.cms.service.cms;

import cn.com.evo.cms.domain.entity.cms.Picture;
import com.frameworks.core.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService extends BaseService<Picture, String> {
    /**
     * 根据资源id 删除 图片对象List
     *
     * @param sourceId
     */
    void deleteBySourceId(String sourceId);

    /**
     * 根据资源表id获取图片list
     *
     * @param sourceId
     * @return
     */
    List<Picture> findBySourceId(String sourceId);

    /**
     * 根据栏目Id 获取图片对象List
     *
     * @param columnId
     * @return
     */
    List<Picture> findByColumnId(String columnId);

    /**
     * 根据栏目id和业务类型 获取图片对象list
     *
     * @param columnId
     * @param BusinessType
     * @return
     */
    List<Picture> findByColumnIdAndBusinessType(String columnId, int BusinessType);

    /**
     * 根据内容Id 获取图片对象List
     *
     * @param contentId
     * @return
     */
    List<Picture> findByContentId(String contentId);

    /**
     * 根据内容id和业务类型 获取图片对象list
     *
     * @param contentId
     * @param BusinessType
     * @return
     */
    List<Picture> findByContentIdAndBusinessType(String contentId, int BusinessType);

    /**
     * 根据资源名称 获取图片对象List
     *
     * @param name
     * @return
     */
    List<Picture> findByName(String name);

    /**
     * 获取图片url
     *
     * @param id 图片id
     * @return 图片地址
     */
    String getImageUrl(String id);

    /**
     * 图片资源excel导入
     *
     * @param files
     */
    void importFile(MultipartFile[] files);

    /**
     * 图片上传
     *
     * @param files   图片文件
     * @param picture 图片对象
     */
    void imageUpload(MultipartFile[] files, Picture picture);

    /**
     * 图片上传
     *
     * @param file 图片文件
     * @param type  类型
     */
    void imageUpload(MultipartFile file, Integer type);
}
