package cn.com.evo.cms.repository.cms;

import cn.com.evo.cms.domain.entity.cms.Column;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColumnRepository extends BaseRepository<Column, String> {

    @Query("from Column where columnCode like ? order by sort")
    List<Column> findLikeByCode(String string);

    List<Column> findByLevelOrderBySort(int level);

    List<Column> findByLevelAndParentIdOrderBySort(int i, String pId);

    @Query(value = "select * from cms_column where pId=?1 and type=?2 ORDER BY sort asc limit ?3,?4", nativeQuery = true)
    List<Column> findByPIdAndType(String columnId, Integer type, Integer start, Integer pageSize);

    @Query(value = "select * from cms_column where pId=?1 and enable=?2 ORDER BY sort asc", nativeQuery = true)
    List<Column> findByPIdAndEnable(String columnId, Integer enable);

    @Query(value = "select count(*) from cms_column where pId=?1 and type=?2", nativeQuery = true)
    Long findWaterfallCountByColumnIdAndType(String columnId, int type);

    List<Column> findByNameAndEnable(String columnsName, int enable);

    Column getByColumnCode(String columnCode);
    Column getByThirdCode(String columnCode);
}
