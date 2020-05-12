package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cms_auxiliary_fall_template database table.
 * 
 */
@Entity
@Table(name="cms_auxiliary_fall_template")
@NamedQuery(name="AuxiliaryFallTemplate.findAll", query="SELECT a FROM AuxiliaryFallTemplate a")
public class AuxiliaryFallTemplate extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "columnId", columnDefinition = "bigint(20) COMMENT '栏目id'")
    private Column column; //栏目表

    @ManyToOne
    @JoinColumn(name = "fallTemplateId", columnDefinition = "varchar(32) COMMENT '瀑布流模版id'")
    private FallTemplate fallTemplate; //栏目表

    private Integer isShowTitle;
    
	public Integer getIsShowTitle() {
		return isShowTitle;
	}

	public void setIsShowTitle(Integer isShowTitle) {
		this.isShowTitle = isShowTitle;
	}

	public AuxiliaryFallTemplate() {
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public FallTemplate getFallTemplate() {
		return fallTemplate;
	}

	public void setFallTemplate(FallTemplate fallTemplate) {
		this.fallTemplate = fallTemplate;
	}

}