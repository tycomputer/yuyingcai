/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.yyc.manager.service.IYycFormService;

/**
 * 
 * 日期 : 2011-10-8 下午6:17:44<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站表单Action<br>
 */
public class YycFormAction extends BaseAction {

	private static final long serialVersionUID = 2087555313429394556L;
	private IYycFormService yycFormService;
	private YycFormForm form;

	public String execute() throws Exception {
		setRequest("sql", yycFormService.getQuerySQL(form));
		return "list";
	}

	public String toEdit() throws Exception {
		this.yycFormService.loadEntityAndSetFrom(form);
		return "add";
	}

	public String saveNews() throws Exception {
		yycFormService.saveForm(form);

		setMessage("保存成功！");
		return execute();

	}

	public YycFormForm getForm() {
		return form;
	}

	public void setForm(YycFormForm form) {
		this.form = form;
	}

	public void setYycFormService(IYycFormService yycFormService) {
		this.yycFormService = yycFormService;
	}

	

}
