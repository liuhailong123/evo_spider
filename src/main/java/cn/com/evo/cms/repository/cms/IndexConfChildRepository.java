package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface IndexConfChildRepository extends BaseRepository<IndexConfChild, String> {

    List<IndexConfChild> findByIndexConfIdOrderByPositionAsc(String indexConfId);

    List<IndexConfChild> findByContentId(String contentId);
}
