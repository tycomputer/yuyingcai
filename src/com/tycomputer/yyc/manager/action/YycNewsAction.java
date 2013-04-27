/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.yyc.manager.service.IYycNewsService;

/**
 * 
 * 日期 : 2011-10-8 下午6:17:44<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站新闻 Action<br>
 */
public class YycNewsAction extends BaseAction {

	private static final long serialVersionUID = -4713225296514714873L;
	private IYycNewsService yycNewsService;
	private YycNewsForm form;
	
	public String execute() throws Exception {
		setRequest("sql", yycNewsService.getQuerySQL(form));
		return "list";
	}

	public String addNews() throws Exception {
		if (form == null){
			form = new YycNewsForm();
			form.setDescription("在这里输入新闻内容！");
		}
		return "add";
	}

	public String toEdit() throws Exception {
		this.yycNewsService.loadEntityAndSetFrom(form);
		return "add";
	}

	public String toDele() throws Exception {
		this.yycNewsService.delNews(form);
		return execute();
	}

	public String saveNews() throws Exception {
		String uid = yycNewsService.saveNews(form);

		if (uid != null) {
			setMessage("添加或修改新闻成功！");
			return execute();
		} else {
			setMessage("出错了，请检查或联系tycomputer！");
			return "add";
		}
	}

	public IYycNewsService getYycNewsService() {
		return yycNewsService;
	}

	public void setYycNewsService(IYycNewsService yycNewsService) {
		this.yycNewsService = yycNewsService;
	}

	public YycNewsForm getForm() {
		return form;
	}

	public void setForm(YycNewsForm form) {
		this.form = form;
	}


}
