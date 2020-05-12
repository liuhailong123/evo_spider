package cn.com.evo.cms.domain.entity.vip;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "vip_notice_conf")
@NamedQuery(name = "NoticeConf.findAll", query = "SELECT a FROM NoticeConf a")
public class NoticeConf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer type;

    private Integer count;

    private String info;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
