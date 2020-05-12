package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ServerVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
