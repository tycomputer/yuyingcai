/*
 * @(#)TechartiForm.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import com.tycomputer.yyc.entity.YycForm;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 网站技术文章管理 form<br>
 */
public class YycFormForm extends YycForm {

	private String inTimeStr;
	private String birthdayStr;
	private String searchFlag;
	private String searchName;

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getInTimeStr() {
		return inTimeStr;
	}

	public void setInTimeStr(String inTimeStr) {
		this.inTimeStr = inTimeStr;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

}
