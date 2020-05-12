package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Source;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SourceRepository extends BaseRepository<Source, String> {

    @Modifying
    @Query(value = "update cms_source set id=:sourceId where id=:oldSourceId", nativeQuery = true)
    void dataMoveUpdate(@Param("sourceId") String sourceId, @Param("oldSourceId") String oldSourceId);

    @Query(value = "select * from  cms_source where name like ?1 order by name", nativeQuery = true)
    List<Source> findLikeName(String name);

    Source getByName(String name);

    List<Source> findByNameAndType(String name, int type);

    @Modifying
    @Query(value = "insert into cms_source(id,name,type,createDate,modifyDate) values(REPLACE (UUID(), '-', ''),?1,?2,NOW(),NOW())", nativeQuery = true)
    void insertEntity(String name, int type);
}
