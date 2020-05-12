package cn.com.evo.cms.service.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.book.BookPicture;
import cn.com.evo.cms.repository.book.BookPictureRepository;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.book.BookPictureService;
import cn.com.evo.cms.service.cms.PictureService;

@Service
@Transactional
public class BookPictureServiceImpl extends AbstractBaseService<BookPicture, String> implements BookPictureService {

	@Autowired
	private BookPictureRepository bookPictureRepository;
	@Autowired
	private BookInfoService bookInfoService;
	@Autowired
	private PictureService pictureInfoService;
	@Override
	protected BaseRepository<BookPicture, String> getBaseRepository() {
		return bookPictureRepository;
	}

	@Override
	public void save(String bookId, String pictureId, Integer enable) {
		BookPicture bookPicture=bookPictureRepository.getByBookInfoIdAndPictureInfoId(bookId,pictureId);
		if(bookPicture==null){
			bookPicture= new BookPicture();
			bookPicture.setBookInfo(bookInfoService.findById(bookId));
			bookPicture.setPictureInfo(pictureInfoService.findById(pictureId));
			super.save(bookPicture);
		}
	}

    @Override
    public BookPicture findByBookId(String bookId) {
        return bookPictureRepository.findByBookId(bookId);
    }


}