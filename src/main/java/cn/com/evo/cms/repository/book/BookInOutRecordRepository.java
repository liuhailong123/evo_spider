package cn.com.evo.cms.repository.book;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.book.BookInOutRecord;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookInOutRecordRepository extends BaseRepository<BookInOutRecord, String> {

    @Query(value = "SELECT * FROM book_in_out_record WHERE bookId=?1",nativeQuery = true)
    List<BookInOutRecord> findByBookId(String id);
}
