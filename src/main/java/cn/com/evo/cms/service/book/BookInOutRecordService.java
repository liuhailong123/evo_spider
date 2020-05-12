package cn.com.evo.cms.service.book;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.book.BookInOutRecord;

import java.util.List;

public interface BookInOutRecordService extends BaseService<BookInOutRecord, String> {
    List<BookInOutRecord> findByBookId(String id);
}
