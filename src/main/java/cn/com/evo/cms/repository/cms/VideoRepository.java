package cn.com.evo.cms.repository.cms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.Video;

public interface VideoRepository extends BaseRepository<Video, String> {

    List<Video> findBySourceId(String sourceId);

    @Query(value = "select count(*) from cms_source_video", nativeQuery = true)
    Long queryVideoCount();

    @Query(value = "select * from cms_source_video where size is null and time is null ORDER BY createDate desc LIMIT 0,?", nativeQuery = true)
    List<Video> getListWhereSizeAndTimeIsnull(int count);

    @Query(value = "select * from cms_source_video where id in (SELECT sourceId from cms_source_rel WHERE fId=? and relType=1 and sourcetype=1)", nativeQuery = true)
    List<Video> findByContentId(String contentId);

    Video getByUrl(String url);

    Video getByUrlAndSourceId(String url, String sourceId);

    Video getBySourceId(String sourceId);
}
