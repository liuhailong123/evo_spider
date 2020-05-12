package cn.com.evo.cms.repository.app;

import org.springframework.data.jpa.repository.Query;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.app.Announcement;

import java.util.List;

public interface AnnouncementRepository extends BaseRepository<Announcement, String> {

	@Query(value="select * from cms_announcement where startTime < ? and endTime > ? and status=1",nativeQuery=true)
    List<Announcement> findByStartTimeAndEndTimeAndStatus(String thisTime1,String thisTime2);
}
