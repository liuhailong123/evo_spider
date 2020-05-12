package cn.com.evo.cms.service.book;

import cn.com.evo.cms.domain.entity.book.BookStorage;
import com.frameworks.core.service.BaseService;

public interface BookStorageService extends BaseService<BookStorage, String> {
    BookStorage findByCityId(String city);
}
