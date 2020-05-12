package cn.com.evo.cms.service.book;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.book.BookPicture;

public interface BookPictureService extends BaseService<BookPicture, String> {

	void save(String bookId, String pictureId, Integer enable);

    BookPicture findByBookId(String bookId);
}
