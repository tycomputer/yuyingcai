/*
 * @(#)MySimpleTagSupport.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *	日期		:	2010-7-8<br>
 *	作者		:	zhangliuhua<br>
 *	项目		:	dclipin<br>
 *	功能		:	<br>
 */
public class MySimpleTagSupport extends SimpleTagSupport {

	protected HttpServletRequest getRequest() {
		PageContext pageContext = (PageContext)getJspContext();  
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest(); 
		return request;
	}

	protected HttpServletResponse getResponse() {
		PageContext pageContext = (PageContext)getJspContext();  

		HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
		return res;
	}

}
