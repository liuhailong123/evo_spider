package cn.com.evo.cms.web.dataDispose.dataMoveApi;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frameworks.core.web.controller.BaseController;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.cms.VideoService;

@Controller
@RequestMapping("/api/contentVideoMove")
public class ContentVideoMove extends BaseController {
	/**
	 * 连接
	 */
	Connection conn = null;
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 数据分页查询 开始值
	 */
	private int start = 0;
	/**
	 * 已迁移数
	 */
	private int count = 0;

	@Autowired
	private SourceRelService sourceRelService;
	@Autowired
	private VideoService videoService;

	/**
	 * 迁移
	 */
	@RequestMapping(value = "/migrate/{count}", method = { RequestMethod.GET })
	@ResponseBody
	public void migrate(@PathVariable("count") int count) {
		try {
			this.count = count;
			total = getTotal();
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 迁移开始
	 */
	private void start() {
		PreparedStatement pstmt = null;
		if (conn == null) {
			conn = Conn.getConn();
		}
		try {
			String sql = "select contentId,videoId from cms_content_video where contentId in (select id from cms_content_info where contentClassify=1)" 
							+ " ORDER BY createDate desc LIMIT "+ count + ",100";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				intoDB(rs);
				System.out.println(++count);
			}
			if (total - count > 0) {
				this.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && conn != null) {
					pstmt.close();// 关闭Statement对象，释放资源
					conn.close();// 关闭连接
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void intoDB(ResultSet rs) {
		try {
			String sourceId = rs.getString(2);
			if (sourceId != null || !"".equals(sourceId)) {
				List<Video> videos = videoService.findBySourceId(sourceId);
				for (Video video : videos) {
					SourceRel sourceRel = new SourceRel();
					sourceRel.setBusinessType(4);//正片
					sourceRel.setRelType(1);//内容资源关系
					sourceRel.setSourcetype(1);//视频
					sourceRel.setfId(rs.getString(1));
					sourceRel.setSourceId(video.getId());
					sourceRelService.save(sourceRel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	private int getTotal() {
		int total = 0;
		try {
			String sql = "select count(*) from cms_content_video where contentId in (select id from cms_content_info where contentClassify=1)";
			Connection conn = Conn.getConn();
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
				break;
			}
			pstmt.close();// 关闭Statement对象，释放资源
			conn.close();// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
