package cn.com.evo.admin.manage.domain.entity;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Author 陆鑫
 * @Description
 * @Date: Created in 2019年05月30日14:50:55
 * @Modified By: 流程中心
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_flow_manage")
@NamedQuery(name = "FlowManage.findAll", query = "SELECT p FROM FlowManage p")
public class FlowManage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String classPath;

    private String funcName;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    private Province province;

    private Integer sort;

    /**
     * 流程类型：1-播放鉴权流程；2-获取订购产品流程
     */
    private Integer type;
}
