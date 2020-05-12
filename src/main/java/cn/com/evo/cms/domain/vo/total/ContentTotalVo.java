package cn.com.evo.cms.domain.vo.total;

import cn.com.evo.cms.domain.vo.cms.ColumnVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * The persistent class for the cms_column database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ContentTotalVo {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 业务值 (内容id,搜索关键字)
     */
    private String bizValue;
    /**
     * 集数
     */
    private Integer number;
    /**
     * 时长(秒)
     */
    private String duration;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账户
     */
    private String accountNo;
    /**
     * 应用id
     */
    private ColumnVo app;
    /**
     * 类型区分：1-播放记录；2-收藏记录；3-签到记录；4-观看时长 5-搜索关键字记录
     */
    private Integer type;

    private Date createDate;
    private Date modifyDate;
}