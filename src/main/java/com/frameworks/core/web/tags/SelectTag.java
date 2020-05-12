package com.frameworks.core.web.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

public class SelectTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String name;

    private String className;

    private String var;

    private Iterator<?> items;

    private boolean hasDefault = false;

    private String defaultOption;

    private boolean disabled = false;

    private String onchange;

    @Override
    public int doStartTag() throws JspException {
        try {
            if (items.hasNext()) {
                pageContext.setAttribute(var, items.next());
            }
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<select id='").append(id).append("' ");
            strBuilder.append("name='").append(name).append("' ");
            if(disabled){
                strBuilder.append("disabled='").append("disabled").append("' ");
            }
            if(StringUtils.isNotBlank(onchange)){
                strBuilder.append("onchange='").append(onchange).append("' ");
            }
            strBuilder.append("class='").append(className).append("'>");
            if(hasDefault){
                strBuilder.append("<option value=\"\">").append(defaultOption).append("</option>");
            }
            pageContext.getOut().write(strBuilder.toString());
        } catch (IOException e) {
            throw new JspTagException("发生错误!", e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (items.hasNext()) {
            pageContext.setAttribute(var, items.next());
            return EVAL_BODY_AGAIN;
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.removeAttribute(var);
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("</select>");
            pageContext.getOut().write(strBuilder.toString());
        } catch (IOException e) {
            throw new JspTagException("发生错误!", e);
        }
        return EVAL_PAGE;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setItems(Object items) {
        if (items instanceof Collection<?>) {
            this.items = ((Collection<?>) items).iterator();
        } else {
            this.items = Lists.newArrayList().iterator();
        }
    }

    public void setHasDefault(boolean hasDefault) {
        this.hasDefault = hasDefault;
    }

    public void setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }
}
