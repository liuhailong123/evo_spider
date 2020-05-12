package cn.com.evo.cms.service.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.book.BookChildInfo;
import cn.com.evo.cms.repository.book.BookChildInfoRepository;
import cn.com.evo.cms.service.book.BookChildInfoService;

import java.util.List;

@Service
@Transactional
public class BookChildInfoServiceImpl extends AbstractBaseService<BookChildInfo, String> implements BookChildInfoService {

	@Autowired
	private BookChildInfoRepository bookChildInfoRepository;
	
	@Override
	protected BaseRepository<BookChildInfo, String> getBaseRepository() {
		return bookChildInfoRepository;
	}


	@Override
	public BookChildInfo getByBookInfoIdOrderByNumberDescLimitOne(String bookInfoId) {
		return bookChildInfoRepository.getByBookIdOrderByNumber(bookInfoId);
	}
	
	@Override
	public void save(BookChildInfo entity) {
//		BookChildInfo bookChildInfo=bookChildInfoRepository.getByBookIdOrderByNumber(entity.getBookInfo().getId());
//		entity.setNumber(bookChildInfo.getNumber()+1);
		super.save(entity);
	}


    @Override
    public List<BookChildInfo> findByBookStorageIdAndBookInfoIdAndStatus(String bookStorageId, String bookId, Integer lendStatus) {
        return bookChildInfoRepository.findByBookStorageIdAndBookInfoIdAndStatus(bookStorageId,bookId,lendStatus);
    }

	@Override
	public BookChildInfo findByNumberAndBookInfoId(String number, String bookId) {
		return bookChildInfoRepository.findByNumberAndBookInfoId(number,bookId);
	}

    @Override
    public List<BookChildInfo> findByBookInfoIdAndStatus(String bookInfoId, Integer lendStatus) {
        return bookChildInfoRepository.findByBookInfoIdAndStatus(bookInfoId,lendStatus);
    }

    @Override
    public List<BookChildInfo> findByBookInfoId(String bookId) {
        return bookChildInfoRepository.findByBookInfoId(bookId);
    }
}