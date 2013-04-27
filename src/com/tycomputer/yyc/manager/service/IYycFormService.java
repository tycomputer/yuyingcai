/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import com.tycomputer.yyc.manager.action.YycFormForm;

/**
 * 
 * 日期 : 2011-10-8 下午6:12:09<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站表单<br>
 */
public interface IYycFormService {

	/**
	 * 
	 * 功能说明 : 根据提交的查询条件构成SQL
	 * 
	 * @param form
	 * @return
	 */
	public String getQuerySQL(YycFormForm form);

	/**
	 * 
	 * 功能说明 : 保存Form，改变两个字段：flag和para3
	 * 
	 * @param form
	 */
	public void saveForm(YycFormForm form);

	/**
	 * 
	 * 功能说明 : 根据ID 载入实体类并设置 form
	 * 
	 * @param form
	 */
	public void loadEntityAndSetFrom(YycFormForm form);

}
