package cn.com.evo.cms.repository.book;

import cn.com.evo.cms.domain.entity.book.BookStorage;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookStorageRepository extends BaseRepository<BookStorage, String> {

    @Query(value="select * from book_storage where cityId=?1",nativeQuery=true)
    BookStorage findByCityId(String city);
}
