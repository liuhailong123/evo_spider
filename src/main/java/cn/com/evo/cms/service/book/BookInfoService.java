package cn.com.evo.cms.service.book;

import org.springframework.web.multipart.MultipartFile;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.book.BookInfo;

import java.util.List;

public interface BookInfoService extends BaseService<BookInfo, String> {

	/**
	 * 书单导入
	 *
	 * @param bookInfoFile    图书信息excel
	 * @param pictureInfoFile 书单图片信息excel
	 * @param relFile         图书与图片关系excel
	 */
	void importFile(MultipartFile[] bookInfoFile, MultipartFile[] pictureInfoFile, MultipartFile[] relFile);

	void changeBookPictureEnable(String bookpictureId);

	void update(BookInfo entity, String bookPicture);

	void addRepertory(BookInfo entity, String storageId, String addCount);

    List<BookInfo> findByTags(String name);

    List<BookInfo> findBySectionId(String sectionId1);
}
