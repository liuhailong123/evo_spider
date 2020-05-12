package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Picture;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends BaseRepository<Picture, String> {

    List<Picture> findBySourceId(String sourceId);

    @Query(value = "select * from cms_source_picture where id in (SELECT sourceId from cms_source_rel WHERE fId=?1 and relType=?2 and sourcetype=?3 and businessType=?4)", nativeQuery = true)
    List<Picture> findByContentIdAndRelTypeAndSourcetypeAndBusinessType(String contentId, int relType, int sourcetype, int businessType);

    @Query(value = "select * from cms_source_picture where id in (SELECT sourceId from cms_source_rel WHERE fId=?1 and relType=?2 and sourcetype=?3)", nativeQuery = true)
    List<Picture> findByContentIdAndRelTypeAndSourcetype(String contentId, int relType, int sourcetype);

    @Modifying
    @Query(value = "insert into cms_source_picture(id,sourceId,size,ext,cloudPath,fileName,resolution,type,createDate,modifyDate) values(?1,?2,?3,?4,?5,?6,?7,?8,now(),now())", nativeQuery = true)
    void insertEntity(String id, String sourceId, String size, String ext, String cloudPath, String fileName, String resolution, int type);
}
