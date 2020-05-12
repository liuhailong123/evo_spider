package cn.com.evo.admin.manage.repository;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.Module;
import org.springframework.data.jpa.repository.Query;

public interface ModuleRepository extends BaseRepository<Module, String> {

    List<Module> findByStatusAndParentIdOrderByPriorityAsc(int status, String parentId);

    Module findByCode(String code);

}
