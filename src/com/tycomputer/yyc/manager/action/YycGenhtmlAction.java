/*
 * @(#)TechartiController.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.action;

import com.tycomputer.common.web.BaseAction;
import com.tycomputer.yyc.manager.service.FtpServiceImpl;
import com.tycomputer.yyc.manager.service.IYycGenhtmlService;

/**
 * 
 * 
 * 日期 : 2012-5-18 下午9:42:02<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 生成静态html网页<br>
 */
public class YycGenhtmlAction extends BaseAction {

	private IYycGenhtmlService yycGenHtmlService;

	private FtpServiceImpl yycFtpService;
	
	private YycGenHtmlForm form;

	@Override
	public String execute() throws Exception {
		if (form != null ){
			if (form.getMethod().equalsIgnoreCase("genhtml")){
				if (yycGenHtmlService.genhtml()) {
					this.setMessage("生成静态HTML成功！");
				} else {
					this.setMessage("生成静态HTML不成功！");
				}
			} else if (form.getMethod().equalsIgnoreCase("upload")){
				String realPath =  this.getSession().getServletContext().getRealPath("/");
				
				//yycFtpService.setLocalPath(realPath + (yycFtpService.getLocalPath() == null ? "" : yycFtpService.getLocalPath()));
				String msg = "";
				if (form.getDeleteFtpFile() != null && (form.getDeleteFtpFile().equalsIgnoreCase("true"))){
					if (yycFtpService.delFtpFiles()){
						msg = "删除ftp上的文件成功，";
					}
				}
				if (form.getForceUpload() != null && (form.getForceUpload().equalsIgnoreCase("true"))){
					yycFtpService.setForceUpload(true);
				}
				
				if (yycFtpService.uploadFiles(realPath)){
					msg += "  上传文件成功！ 请打开网站看看！";
					StringBuilder sb = new StringBuilder();
					for (String str : yycFtpService.getUploadFileList()) {
						sb.append(str).append("\n");
					}
					form.setMessage(sb.toString());
				} else {
					msg += "  上传文件不成功！ 请立即与tycomputer 联系！";
				}
				this.setMessage(msg);
			}
		}
		return "list";
	}

	

	public IYycGenhtmlService getYycGenHtmlService() {
		return yycGenHtmlService;
	}

	public void setYycGenHtmlService(IYycGenhtmlService yycGenHtmlService) {
		this.yycGenHtmlService = yycGenHtmlService;
	}

	public FtpServiceImpl getYycFtpService() {
		return yycFtpService;
	}

	public void setYycFtpService(FtpServiceImpl yycFtpService) {
		this.yycFtpService = yycFtpService;
	}

	public YycGenHtmlForm getForm() {
		return form;
	}

	public void setForm(YycGenHtmlForm form) {
		this.form = form;
	}

}
