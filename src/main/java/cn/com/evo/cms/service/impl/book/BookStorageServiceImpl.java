package cn.com.evo.cms.service.impl.book;

import cn.com.evo.cms.domain.entity.book.BookStorage;
import cn.com.evo.cms.repository.book.BookStorageRepository;
import cn.com.evo.cms.service.book.BookStorageService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 10:57 15/1/18
 * @Modified By:
 */
@Service
@Transactional
public class BookStorageServiceImpl extends AbstractBaseService<BookStorage, String> implements BookStorageService {

    @Autowired
    private BookStorageRepository bookStorageRepository;

    @Override
    protected BaseRepository<BookStorage, String> getBaseRepository() {
        return bookStorageRepository;
    }

    @Override
    public BookStorage findByCityId(String city) {
        return bookStorageRepository.findByCityId(city);
    }
}
