package cn.com.evo.admin.manage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.admin.manage.domain.entity.RolePermission;

public interface RolePermissionRepository extends BaseRepository<RolePermission, String> {

    List<RolePermission> findByRoleId(String roleId);

    @Query("from RolePermission rp where rp.role.id=?1 and rp.permission.module.id=?2 order by rp.id asc")
    List<RolePermission> findByRoleIdAndModuleId(String roleId, String moduleId);
}
