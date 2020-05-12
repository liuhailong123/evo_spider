package cn.com.evo.cms.domain.entity.total;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "total_content")
@NamedQuery(name = "ContentTotal.findAll", query = "SELECT c FROM ContentTotal c")
public class ContentTotal extends BaseEntity {
    private static final long serialVersionUID = 1L;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appId", columnDefinition = "varchar(32) comment '应用id'")
    private cn.com.evo.cms.domain.entity.cms.Column app;
    /**
     * 类型区分：1-播放记录；2-收藏记录；3-签到记录；4-观看时长 5-黑名单  6-搜索关键字
     */
    private Integer type;

    public ContentTotal() {
    }

    public ContentTotal(String bizValue, String duration, String userId, cn.com.evo.cms.domain.entity.cms.Column app, Integer type, Integer number) {
        this.bizValue = bizValue;
        this.duration = duration;
        this.userId = userId;
        this.app = app;
        this.type = type;
        this.number = number;
    }
}