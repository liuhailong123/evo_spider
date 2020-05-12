package cn.com.evo.cms.service.book;

import cn.com.evo.cms.domain.entity.book.BookChildInfo;
import com.frameworks.core.service.BaseService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookChildInfoService extends BaseService<BookChildInfo, String> {
    @Query(value = "select * from book_child_info where bookId=?1 ORDER BY number desc limit 1", nativeQuery = true)
    BookChildInfo getByBookInfoIdOrderByNumberDescLimitOne(String bookInfoId);

    List<BookChildInfo> findByBookStorageIdAndBookInfoIdAndStatus(String id, String bookId, Integer lendStatus);

    BookChildInfo findByNumberAndBookInfoId(String number, String bookId);

    List<BookChildInfo> findByBookInfoIdAndStatus(String id, Integer lendStatus);

    List<BookChildInfo> findByBookInfoId(String bookId);
}
