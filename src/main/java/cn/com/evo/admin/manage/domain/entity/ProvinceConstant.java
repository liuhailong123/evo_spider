package cn.com.evo.admin.manage.domain.entity;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Author 陆鑫
 * @Description
 * @Date: Created in 17:01 20/8/18
 * @Modified By: 省网配置信息维护
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_province_constant")
@NamedQuery(name = "ProvinceConstant.findAll", query = "SELECT p FROM ProvinceConstant p")
public class ProvinceConstant extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String constantKey;

    private String constantValue;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    private Province province;

    private Integer enable;
}
