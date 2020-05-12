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

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.service.cms.ContentService;

/**
 * 内容迁移
 * 
 * @author shilinxiao
 *
 */
@Controller
@RequestMapping("/api/contentMove")
public class ContentMove extends BaseController {
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
	 * 内容id
	 */
	private String id;
	/**
	 * 分类标签
	 */
	private String classifyTags;
	/**
	 * 演员标签
	 */
	private String actorTags;
	/**
	 * 导演标签
	 */
	private String directorTags;
	/**
	 * 区域标签
	 */
	private String areaTags;
	/**
	 * 内容分类 电影/剧集
	 */
	private int classify = 1;
	/**
	 * 内容编码
	 */
	private String code;
	/**
	 * 是否启用
	 */
	private int enable;
	/**
	 * 评分
	 */
	private String grade;
	/**
	 * 内容简介
	 */
	private String info;
	/**
	 * 内容名称
	 */
	private String name;
	/**
	 * 内容名称全拼
	 */
	private String nameSpellLong;
	/**
	 * 内容名称简拼
	 */
	private String nameSpellShort;
	/**
	 * 排序
	 */
	private int sort = 1;
	/**
	 * 内容标题
	 */
	private String title;
	/**
	 * 标题全拼
	 */
	private String titleSpellLong;
	/**
	 * 标题简拼
	 */
	private String titleSpellShort;
	/**
	 * 上映年份
	 */
	private String year;
	/**
	 * 年代标签
	 */
	private String yearTags;
	/**
	 * 总集数
	 */
	private int sumNum=1;
	@Autowired
	private ContentService contentService;

	/**
	 * 迁移
	 * 
	 * @param count
	 * @return
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
			String sql = "select id,name,`code`,title,info,`year`,classifyTags,actorTags,"
					+ "directorTags,yearTags,areaTags,scores,nameLongPy,nameShortPy,"
					+ "`enable`,titleLongPy,titleShortPy,contentClassify,sumnum"
					+ " from cms_content_info ORDER BY createDate desc limit " + count + ",100";
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

	/**
	 * 入库
	 * 
	 * @param rs
	 */
	private void intoDB(ResultSet rs) {
		try {
			id = rs.getString(1);
			name = rs.getString(2);
			code = rs.getString(3);
			title = rs.getString(4);
			info = rs.getString(5);
			year = rs.getString(6);
			classifyTags = rs.getString(7);
			actorTags = rs.getString(8);
			directorTags = rs.getString(9);
			yearTags = rs.getString(10);
			areaTags = rs.getString(11);
			grade = rs.getString(12);
			nameSpellLong = rs.getString(13);
			nameSpellShort = rs.getString(14);
			if (rs.getString(15) == null || "".equals(rs.getString(15))) {
				enable = 0;
			} else {
				enable = rs.getInt(15);
			}
			titleSpellLong = rs.getString(16);
			titleSpellShort = rs.getString(17);
			if (rs.getString(18) != null || !"".equals(rs.getString(18))) {
				if (rs.getInt(18) == 3) {
					classify = 2;
				} else {
					classify = rs.getInt(18);
				}
			}
			if(rs.getString(19) != null || !"".equals(rs.getString(19))){
				if(classify!=1){
					sumNum=rs.getInt(19);
				}
			}
			Content content = new Content();
			content.setName(name);
			content.setCode(code);
			content.setTitle(title);
			content.setInfo(info);
			content.setYear(year);
			content.setClassifyTags(classifyTags);
			content.setActorTags(actorTags);
			content.setDirectorTags(directorTags);
			content.setYearTags(yearTags);
			content.setAreaTags(areaTags);
			content.setGrade(grade);
			content.setNameSpellLong(nameSpellLong);
			content.setNameSpellShort(nameSpellShort);
			content.setTitleSpellLong(titleSpellLong);
			content.setTitleSpellShort(titleSpellShort);
			content.setSort(sort);
			content.setClassify(classify);
			content.setSumNum(sumNum);
			contentService.save(content);
			contentService.dataMoveUpdate(id, content.getId());
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
