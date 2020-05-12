package cn.com.evo.cms.service.app;

import cn.com.evo.cms.domain.entity.app.Announcement;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface AnnouncementService extends BaseService<Announcement, String> {
    List<Announcement> findByStartTimeAndEndTimeAndStatus();
}
