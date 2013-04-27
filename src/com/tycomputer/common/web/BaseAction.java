/*
 * @(#)BaseAction.java 
 * 
 * Copyright 2009
 * All rights reserved.
 *
 */
package com.tycomputer.common.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tycomputer.common.util.Constants;

/**
 * 
 * 日期 : 2010-4-12<br>
 * 作者 : zhangliuhua<br>
 * 项目 : GS_WUMONWeb<br>
 * 功能 : 基础Action<br>
 */
public abstract class BaseAction extends ActionSupport {
	

	/**
	 * 
	 * 功能说明 : 得到 HttpServletRequest
	 * 
	 * @return
	 */
	protected void setRequest(String key, Object obj) {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute(key, obj);

	}
	
	/**
	 * 
	 * 功能说明 : 得到 HttpServletRequest
	 * 
	 * @return
	 */
	protected void setMessage(String message) {
		if ((message != null) && (!message.trim().equals(""))) {
			ActionContext ct = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute(Constants._MESSAGE, message);
		}

	}

	/**
	 * 
	 * 功能说明 : 得到 HttpServletRequest
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		ActionContext ct = ActionContext.getContext();		
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		return request;
	}
	
	/**
	 * 默认进入Action 方法
	 */
	public abstract String execute() throws Exception;
	
	/**
	 * 
	 * 功能说明 : 得到 HttpServletResponse
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		ActionContext ct = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ct.get(ServletActionContext.HTTP_RESPONSE);
		return response;
	}
	
	
	/**
	 * 
	 * 功能说明 : 得到 HttpSession
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		HttpServletRequest request = this.getRequest();
		return request.getSession();
	}

	protected Map getSessionMap() {		
		return ActionContext.getContext().getSession();
	}
}
