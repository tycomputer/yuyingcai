/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import org.apache.struts2.ServletActionContext;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.yyc.manager.service.IYycContentService;

/**
 * 
 * 日期 : 2011-10-8 下午6:17:44<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站新闻及其它内容  Action<br>
 */
public class YycContentAction extends BaseAction {

	private static final long serialVersionUID = 4648169188136960245L;
	private IYycContentService yycContentService;
	private YycContentForm form;
	
	public String execute() throws Exception {
		setRequest("sql", yycContentService.getQuerySQL(form));
		return "list";
	}

	public String addContent() throws Exception {
		if (form == null){
			form = new YycContentForm();
			form.setContDesc("在这里输入新闻内容！");
		}
		return "add";
	}

	public String toEdit() throws Exception {
		this.yycContentService.loadEntityAndSetFrom(form);
		return "add";
	}

	public String delContent() throws Exception {
		this.yycContentService.delContent(form);
		setRequest("sql", yycContentService.getQuerySQL(form));
		return "list";
	}

	public String saveContent() throws Exception {
		
		Integer uid = yycContentService.saveContent(form);

		if (uid != null) {
			setMessage("添加或修改网站内容成功！");
			return execute();
		} else {
			setMessage("出错了，请检查或联系tycomputer！");
			return "add";
		}
	}

	public IYycContentService getYycContentService() {
		return yycContentService;
	}

	public void setYycContentService(IYycContentService yycContentService) {
		this.yycContentService = yycContentService;
	}

	public YycContentForm getForm() {
		return form;
	}

	public void setForm(YycContentForm form) {
		this.form = form;
	}

	

}
