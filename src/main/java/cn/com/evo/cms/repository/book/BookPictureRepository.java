package cn.com.evo.cms.repository.book;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.book.BookPicture;
import org.springframework.data.jpa.repository.Query;

public interface BookPictureRepository extends BaseRepository<BookPicture, String> {

	BookPicture getByBookInfoIdAndPictureInfoId(String bookId, String pictureId);

    @Query(value = "SELECT * FROM book_picture WHERE bookId = ?1",nativeQuery = true)
    BookPicture findByBookId(String bookId);
}
