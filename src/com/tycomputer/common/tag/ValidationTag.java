/*
 * @(#)ContextPathTag.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.tag;

import javax.servlet.jsp.JspException;

/**
 * 日期 : 2010-1-14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : mon<br>
 * 功能 : <br>
 */
public class ValidationTag extends MySimpleTagSupport {

	private String formId;

	private String options;

	public void doTag() throws JspException {

		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript' src='js/validation/validation.js'></script>\n");
		sb.append("<script type='text/javascript'>var ").append(formId).append("Validation;");
		sb.append("document.observe('dom:loaded', function() {").append(formId).append("Validation=new Validation('").append(formId).append("',");

		if ((options != null) && (!options.trim().equals(""))) {
			sb.append(options);
		} else {
			sb.append("{immediate:true,onSubmit:true}");
		}
		sb.append(")});</script>\n");

		try {
			getJspContext().getOut().println(sb.toString());
		} catch (Exception e) {
		}
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}
