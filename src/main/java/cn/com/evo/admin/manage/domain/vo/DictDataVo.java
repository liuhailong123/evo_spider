package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class DictDataVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private int priority;

    private DictClassifyVo dictClassify;

    public DictDataVo() {
    }

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public DictClassifyVo getDictClassify() {
        return dictClassify;
    }

    public void setDictClassify(DictClassifyVo dictClassify) {
        this.dictClassify = dictClassify;
    }

}