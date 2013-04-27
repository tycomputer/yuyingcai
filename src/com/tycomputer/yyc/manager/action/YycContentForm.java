/*
 * @(#)TechartiForm.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;


/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 育英才网站新闻及其它内容  form<br>
 */
public class YycContentForm {

	private Integer contId;
	private String typeId;
	private String contTitle;
	private String searchContTitle;
	private String searchTypeId;
	private String contDesc;
	private String flag;
	private Integer sn;

	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getContTitle() {
		return contTitle;
	}

	public void setContTitle(String contTitle) {
		this.contTitle = contTitle;
	}

	public String getSearchContTitle() {
		return searchContTitle;
	}

	public void setSearchContTitle(String searchContTitle) {
		this.searchContTitle = searchContTitle;
	}

	public String getSearchTypeId() {
		return searchTypeId;
	}

	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public String getContDesc() {
		return contDesc;
	}

	public void setContDesc(String contDesc) {
		this.contDesc = contDesc;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

}
