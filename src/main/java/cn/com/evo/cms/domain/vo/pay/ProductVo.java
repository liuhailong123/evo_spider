package cn.com.evo.cms.domain.vo.pay;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import com.frameworks.core.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String thirdPartyId;

    private String thirdPartyCode;

    private String originalPrice;

    private String currentPrice;

    private Integer shortMssageInform;

    private String appId;

    private String info;

    private Integer sort;

    private Integer type;

    private String contentCode;
}
