/*
 * @(#)TechartiForm.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import java.util.Calendar;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站技术文章管理 form<br>
 */
public class YycNewsForm {

	private String uuid;
	private String newsTitle;
	private String searchNewsTitle;
	private String description;
	private String flag;
	private Calendar addData;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Calendar getAddData() {
		return addData;
	}

	public void setAddData(Calendar addData) {
		this.addData = addData;
	}

	public String getSearchNewsTitle() {
		return searchNewsTitle;
	}

	public void setSearchNewsTitle(String searchNewsTitle) {
		this.searchNewsTitle = searchNewsTitle;
	}

}
