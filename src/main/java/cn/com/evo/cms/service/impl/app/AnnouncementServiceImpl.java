package cn.com.evo.cms.service.impl.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.mysql.jdbc.Connection;

import cn.com.evo.cms.domain.entity.app.Announcement;
import cn.com.evo.cms.repository.app.AnnouncementRepository;
import cn.com.evo.cms.service.app.AnnouncementService;

@Service
@Transactional
public class AnnouncementServiceImpl extends AbstractBaseService<Announcement, String> implements AnnouncementService {
	Connection conn = null;
	@Autowired
	private AnnouncementRepository announcementRepository;


	@Override
	protected BaseRepository<Announcement, String> getBaseRepository() {
		return announcementRepository;
	}


	@Override
	public List<Announcement> findByStartTimeAndEndTimeAndStatus() {
		List<Announcement> entitys=Lists.newArrayList();
		//获取当前时间
		String thisTime=getThisTime();
		entitys=announcementRepository.findByStartTimeAndEndTimeAndStatus(thisTime,thisTime);
		return entitys;
	}


	private String getThisTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}


}
