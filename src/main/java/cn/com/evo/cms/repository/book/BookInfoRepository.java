package cn.com.evo.cms.repository.book;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.book.BookInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookInfoRepository extends BaseRepository<BookInfo, String> {

	BookInfo getByNumber(String number);

	@Query(value = "SELECT * FROM book_info WHERE columnsNames LIKE ?1",nativeQuery = true)
    List<BookInfo> findByTags(String name);

    @Query(value = "SELECT * FROM book_info WHERE sectionId LIKE ?1",nativeQuery = true)
    List<BookInfo> findBySectionId(String sectionId);

    @Modifying
    @Query(value = "insert into book_info(id,name,number,author,tags,info,ageTag,supplier,createDate,modifyDate) values(?1,?2,?3,?4,?5,?6,?7,?8,now(),now())", nativeQuery = true)
    void insertEntity(String id, String name, String number, String author, String tags, String info, String ageTag, String supplier);
}
