package cn.com.evo.cms.web.dataDispose.dataMoveApi;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frameworks.core.web.controller.BaseController;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.service.cms.VideoService;

/**
 * 视频信息迁移
 * 
 * @author shilinxiao
 *
 */
@Controller
@RequestMapping("/api/videoMove")
public class VideoMove extends BaseController {
	/**
	 * 连接
	 */
	Connection conn = null;
	/**
	 * 总条数
	 */
	private int total;
	/**
	 * 已迁移数
	 */
	private int count = 0;
	/**
	 * 资源id
	 */
	private String id = "";
	/**
	 * 视频名称
	 */
	private String name = "";
	/**
	 * 别名
	 */
	private String byName = "";
	/**
	 * 片源类型 [1:2D] [2:3D] [3:4K] [4:VR]
	 */
	private String contentType = "";
	/**
	 * 清晰度 [1:手机] [2:标清] [3:高清] [4:超清] [5:蓝光] [6:4K] [7:8K]
	 */
	private int definition = 1;
	/**
	 * 视频类型 [1:2D] [2:3D] [3:VR]
	 */
	private int videoType = 1;
	/**
	 * 原画
	 */
	private String sourceUrl = "";
	/**
	 * 蓝光
	 */
	private String hqUrl = "";
	/**
	 * 高清
	 */
	private String hdUrl = "";
	/**
	 * 标清
	 */
	private String sdUrl = "";
	/**
	 * 手机
	 */
	private String phoneUrl = "";

	@Autowired
	private VideoService videoService;
	@Autowired
	private SourceService sourceService;

	/**
	 * 迁移
	 * 
	 * @param mapData
	 * @return
	 */
	@RequestMapping(value = "/migrate/{count}", method = { RequestMethod.GET })
	@ResponseBody
	public void migrate(@PathVariable("count") int count) {
		try {
			this.count = count;
			total = getTotal();
			System.out.println("总条数：" + total);
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
			System.out.println("获取连接");
		}
		try {
			String sql = "select name,byName,contentType,cosSourceUrl,cosHqUrl,cosHdUrl,cosSdUrl,cosPhoneUrl,id "
					+ "from cms_video_info ORDER BY createDate desc limit " + count + ",100";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("标记位1");
				intoDB(rs);
				System.out.println(++count);
			}
			if (total - count > 0) {
				System.out.println("标记位2");
				this.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null&&pstmt!=null){
					pstmt.close();// 关闭Statement对象，释放资源
					conn.close();// 关闭连接
					System.out.println("关闭连接");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 数据入库
	 * 
	 * @param rs
	 */
	private void intoDB(ResultSet rs) {
		try {
			name = rs.getString(1);
			byName = rs.getString(2);
			contentType = rs.getString(3);
			sourceUrl = rs.getString(4);
			hqUrl = rs.getString(5);
			hdUrl = rs.getString(6);
			sdUrl = rs.getString(7);
			phoneUrl = rs.getString(8);
			id = rs.getString(9);
			// 资源表
			Source source = new Source();
			source.setName(name);
			source.setRemark(byName);
			source.setType(1);// 视频
			sourceService.save(source);
			System.out.println("标记位3");
			sourceService.dataMoveUpdate(id, source.getId());
			System.out.println("标记位4");
			source = sourceService.findById(id);
			if (sourceUrl != null && !"".equals(sourceUrl)) {
				definition = 5;
				saveVideo(source, sourceUrl);
			}
			if (hqUrl != null && !"".equals(hqUrl)) {
				definition = 4;
				saveVideo(source, hqUrl);
			}
			if (hdUrl != null && !"".equals(hdUrl)) {
				definition = 3;
				saveVideo(source, hdUrl);
			}
			if (sdUrl != null && !"".equals(sdUrl)) {
				definition = 2;
				saveVideo(source, sdUrl);
			}
			if (phoneUrl != null && !"".equals(phoneUrl)) {
				definition = 1;
				saveVideo(source, phoneUrl);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(count);
	}

	/**
	 * 视频表数据入库
	 * 
	 * @param source
	 * @param url
	 */
	private void saveVideo(Source source, String url) {
		try {
			if (contentType != null && !"".equals(contentType)) {
				if (Integer.valueOf(contentType) == 3) {
					videoType = 1;
				} else {
					videoType = Integer.valueOf(contentType);
				}
			}
			// XugglerUtil xu = new XugglerUtil(url);
			// JSONObject jo = xu.getWidthAndHeigth();
			// Long time = xu.getDuration();
			// Long size = xu.getFileSize() / 1000;
			Video video = new Video();
			// video.setResolution(jo.getString("height") + "*" +
			// jo.getString("width"));
			// video.setTime(time + "");
			// video.setSize(size);
			video.setUrl(url);
			video.setExt(url.split("\\.")[url.split("\\.").length - 1]);
			video.setSource(source);
			video.setType(videoType);
			video.setDefinition(definition);
			videoService.save(video);
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
			String sql = "select count(*) from cms_video_info";
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
			System.out.println("获取总条数-关闭连接");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}
