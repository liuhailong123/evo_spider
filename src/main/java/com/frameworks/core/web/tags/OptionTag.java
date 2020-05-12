package com.frameworks.core.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OptionTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String value;

    private String text;

    private boolean selected;

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<option value='").append(value).append("' ");
            if (selected) {
                strBuilder.append("selected='selected' ");
            }
            strBuilder.append(">").append(text).append("</option>");
            pageContext.getOut().write(strBuilder.toString());
        } catch (IOException e) {
            throw new JspTagException("发生错误!", e);
        }
        return SKIP_BODY;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
