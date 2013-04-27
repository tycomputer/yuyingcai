/*
 * @(#)ContextPathTag.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.tycomputer.common.util.Constants;

/**
 * 日期 : 2010-1-14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : mon<br>
 * 功能 : <br>
 */
public class ManagerContextPath extends MySimpleTagSupport {

	public void doTag() throws JspException {

		HttpServletRequest request = getRequest();
		String basePath = null;
		try {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		} catch (Exception e) {
			basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
//		if (request.getSession().getAttribute(Constants.USER_NAME) == null) {
//
//			try {
//				getResponse().sendRedirect(basePath + "/login.do");
//			} catch (IOException e) {
//				try {
//					this.getJspContext().getOut().print("<script language='JavaScript'>location.href='" + request.getContextPath() + "/login.do';</script>");
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				e.printStackTrace();
//			}
//
//		}

		StringBuffer sb = new StringBuffer();

		sb.append("<base href='").append(basePath).append("'>\n");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n");
		sb.append("<meta http-equiv='pragma' content='no-cache'>\n");
		sb.append("<meta http-equiv='cache-control' content='no-cache'>\n");
		sb.append("<link type='text/css' rel='stylesheet' href='css/mon.css'>\n");
		if (request.getAttribute(Constants._MESSAGE) != null) {
			sb.append("<script>alert ('").append(request.getAttribute(Constants._MESSAGE)).append("');</script>\n");
		}
		try {
			this.getJspContext().getOut().print(sb.toString());
		} catch (Exception e) {
		}

	}

}
