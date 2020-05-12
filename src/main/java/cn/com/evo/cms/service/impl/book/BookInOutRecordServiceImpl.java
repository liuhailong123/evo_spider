package cn.com.evo.cms.service.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.book.BookInOutRecord;
import cn.com.evo.cms.repository.book.BookInOutRecordRepository;
import cn.com.evo.cms.service.book.BookInOutRecordService;

import java.util.List;

@Service
@Transactional
public class BookInOutRecordServiceImpl extends AbstractBaseService<BookInOutRecord, String> implements BookInOutRecordService {

	@Autowired
	private BookInOutRecordRepository bookInOutRecordRepository;

	@Override
	protected BaseRepository<BookInOutRecord, String> getBaseRepository() {
		return bookInOutRecordRepository;
	}


	@Override
	public List<BookInOutRecord> findByBookId(String id) {
		return bookInOutRecordRepository.findByBookId(id);
	}
}