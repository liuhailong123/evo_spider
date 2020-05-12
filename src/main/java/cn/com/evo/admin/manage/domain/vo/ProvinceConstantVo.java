package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 陆鑫
 * @Description
 * @Date: Created in 17:01 20/8/18
 * @Modified By: 省网配置信息维护
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProvinceConstantVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String constantKey;

    private String constantValue;

    private String info;

    private ProvinceVo province;

    private Integer enable;
}
