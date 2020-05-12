package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.repository.cms.SourceRepository;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.service.cms.VideoService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SourceServiceImpl extends AbstractBaseService<Source, String> implements SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private VideoService videoService;

    @Autowired
    private PictureService pictureService;

    @Override
    protected BaseRepository<Source, String> getBaseRepository() {
        return sourceRepository;
    }

    @Override
    public void deleteById(String id) {
        Source source = this.findById(id);

        // 删除资源附属信息
        SourceTypeEnum sourceTypeEnum = SourceTypeEnum.val(source.getType());
        switch (sourceTypeEnum) {
            case video:
                videoService.deleteBySourceId(id);
                break;
            case music:
                break;
            case image:
                pictureService.deleteBySourceId(id);
                break;
            case text:
                break;
            default:
                break;
        }

        // 删除资源对象
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void dataMoveUpdate(String sourceId, String oldSourceId) {
        try {
            sourceRepository.dataMoveUpdate(sourceId, oldSourceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Source> findLikeName(String name) {
        return sourceRepository.findLikeName("%" + name + "%");
    }

    @Override
    public List<Source> findByName(String name) {
        return sourceRepository.findLikeName(name);
    }

    @Override
    public Source getByName(String name) {
        return sourceRepository.getByName(name);
    }

    @Override
    public List<Source> findByNameAndType(String name, int type) {

        return sourceRepository.findByNameAndType(name, type);
    }

}
