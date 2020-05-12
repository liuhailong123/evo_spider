package com.frameworks.core.web.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.evo.admin.manage.domain.entity.DictData;
import cn.com.evo.admin.manage.service.DictDataService;


public class DictTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String var;
		
	private List<DictData> lstData = null;

	public void setVar(String var) {
		this.var = var;
	}
	
	public void setDictCode(String dictCode) {
		lstData = getDataList(dictCode);
	}

	@Override
	public int doStartTag() throws JspException {
		if(null == lstData){
			return SKIP_BODY;
		} else {
			pageContext.setAttribute(var, lstData);
			return EVAL_BODY_INCLUDE;
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		pageContext.removeAttribute(var);
		return EVAL_PAGE;
	}
	
	private List<DictData> getDataList(String dictCode) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
		DictDataService service = wac.getBean(DictDataService.class);
		return service.findByDictClassifyCode(dictCode);
		
	}
}
