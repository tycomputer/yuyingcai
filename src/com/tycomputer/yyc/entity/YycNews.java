package com.tycomputer.yyc.entity;

import java.util.Calendar;

/**
 * Present entity. @author MyEclipse Persistence Tools
 */

public class YycNews implements java.io.Serializable {

	// Fields

	private String uuid;
	private String newsTitle;
	private String description;
	private String flag;
	private Calendar addData;

	// Constructors

	/** default constructor */
	public YycNews() {
	}

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

}