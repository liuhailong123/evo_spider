package com.frameworks.core.web.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.BeanUtils;

public class JsonArrayTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String varName;
	
	private String keyName;
	
	private String valName;
	
	private Iterator<?> items;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("var ").append(varName).append("=[");
			while(items.hasNext()){
				Object item = items.next();
				strBuilder.append("{ \"").append(keyName).append("\" : \"").append(this.getKeyName(item, keyName)).append("\", ");
				strBuilder.append("\"").append(valName).append("\" : \"").append(this.getValName(item, valName)).append("\" }, ");
			}
			pageContext.getOut().write(strBuilder.toString());
		} catch (Exception e) {
			throw new JspException("发生错误", e);
		}
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("];");
			pageContext.getOut().write(strBuilder.toString());
		} catch (IOException e) {
			throw new JspException("发生错误", e);
		}
		return EVAL_PAGE;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public void setItems(Object items) {
		if(items instanceof Collection<?>){
			this.items = ((Collection<?>)items).iterator();
		}
	}
	
	private String getKeyName(Object item, String keyName) throws Exception{
		if(item instanceof Map.Entry<?, ?>){
			Map.Entry<?,?> entry = (Entry<?, ?>) item;
			return entry.getValue().toString();
		} else {
			return BeanUtils.getProperty(item, keyName);
		}
	}
	
	private String getValName(Object item, String valName) throws Exception{
		if(item instanceof Map.Entry<?, ?>){
			Map.Entry<?,?> entry = (Entry<?, ?>) item;
			return entry.getValue().toString();
		} else {
			return BeanUtils.getProperty(item, valName);
		}
	}
}
