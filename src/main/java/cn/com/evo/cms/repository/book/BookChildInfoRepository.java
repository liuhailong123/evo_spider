package cn.com.evo.cms.repository.book;

import org.springframework.data.jpa.repository.Query;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.book.BookChildInfo;

import java.util.List;

public interface BookChildInfoRepository extends BaseRepository<BookChildInfo, String> {

	@Query(value="select * from book_child_info where bookId=?1 ORDER BY number desc limit 1",nativeQuery=true)
	BookChildInfo getByBookIdOrderByNumber(String bookId);

	@Query(value="select * from book_child_info where storageId=?1 And bookId=?2 AND status=?3",nativeQuery=true)
    List<BookChildInfo> findByBookStorageIdAndBookInfoIdAndStatus(String bookStorageId, String bookId, Integer lendStatus);

	@Query(value="select * from book_child_info where number=?1 And bookId=?2",nativeQuery=true)
	BookChildInfo findByNumberAndBookInfoId(String number, String bookId);

	@Query(value="select * from book_child_info where bookId=?1 AND status=?2",nativeQuery=true)
	List<BookChildInfo> findByBookInfoIdAndStatus(String bookInfoId, Integer lendStatus);

	@Query(value="select * from book_child_info where bookId=?1",nativeQuery=true)
    List<BookChildInfo> findByBookInfoId(String bookId);
}
