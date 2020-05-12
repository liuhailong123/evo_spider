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

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;

@Controller
@RequestMapping("/api/contentPictureMove2")
public class ContentPictureMove2 extends BaseController {
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
	/**
	 * 竖版海报
	 */
	private String sPicture;
	/**
	 * 横版海报
	 */
	private String hPicture;

	@Autowired
	private SourceRelService sourceRelService;
	@Autowired
	private PictureService pictureService;

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
			String sql = "select id,sLogo,hLogo from cms_content_info ORDER BY createDate desc LIMIT " + count
					+ ",100";
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
			sPicture = rs.getString(2);
			hPicture = rs.getString(3);
			if(sPicture!=null&&!"".equals(sPicture)){
				insertContentPicture(sPicture,rs);
			}
			if(hPicture!=null&&!"".equals(hPicture)){
				insertContentPicture(hPicture,rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertContentPicture(String sourceId, ResultSet rs) {
		try {
			if (sourceId != null || !"".equals(sourceId)) {
				List<Picture> pictures = pictureService.findBySourceId(sourceId);
				if (pictures.size() > 0) {
					SourceRel sourceRel = new SourceRel();
					sourceRel.setBusinessType(1);//海报
					sourceRel.setRelType(1);//内容资源关系
					sourceRel.setSourcetype(2);//图片
					sourceRel.setfId(rs.getString(1));
					sourceRel.setSourceId(pictures.get(0).getId());
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
			String sql = "select count(*) from cms_content_info";
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
