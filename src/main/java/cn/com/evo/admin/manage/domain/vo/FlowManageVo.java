package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 陆鑫
 * @Description
 * @Date: Created in 2019年05月30日14:50:55
 * @Modified By: 流程中心
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowManageVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String classPath;

    private String funcName;

    private String info;

    private ProvinceVo province;

    private Integer sort;

    private Integer type;
}
