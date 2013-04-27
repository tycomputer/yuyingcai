/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

/**
 * 
 * 
 * 日期 : 2012-5-19 下午8:42:20<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 生成静态页面<br>
 */
public class YycGenHtmlForm {

	private String method;// 方法genhtml、upload
	private String forceUpload;// true-->强制上传所有文件
	private String deleteFtpFile;// true-->先删除ftp所有文件
	private String message;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getForceUpload() {
		return forceUpload;
	}

	public void setForceUpload(String forceUpload) {
		this.forceUpload = forceUpload;
	}

	public String getDeleteFtpFile() {
		return deleteFtpFile;
	}

	public void setDeleteFtpFile(String deleteFtpFile) {
		this.deleteFtpFile = deleteFtpFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
