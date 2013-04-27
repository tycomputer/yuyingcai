/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.yyc.manager.service.IYycBmService;

/**
 * 
 * 日期 : 2011-10-8 下午6:17:44<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站表单Action<br>
 */
public class YycBmAction extends BaseAction {

	private static final long serialVersionUID = 2087555313429394556L;
	private IYycBmService yycBmService;
	private YycBmForm form;

	public String execute() throws Exception {
		if (form == null){
			return "bm";
		}
		String uuidString = yycBmService.savebm(form);
		if (uuidString != null){
			this.setRequest("name", form.getName());
			this.getResponse().sendRedirect("http://www.yuyingcai.com.cn/koucai/bmok.html");
			//return "result";
			return null;
		} 

		return "bm";
	}

	

	public YycBmForm getForm() {
		return form;
	}

	public void setForm(YycBmForm form) {
		this.form = form;
	}



	public void setYycBmService(IYycBmService yycBmService) {
		this.yycBmService = yycBmService;
	}

}
