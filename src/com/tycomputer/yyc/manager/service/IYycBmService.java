/*
 * @(#)ITechartiManager.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import com.tycomputer.yyc.manager.action.YycBmForm;

/**
 * 
 * 日期 : 2011-10-8 下午6:12:09<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站报名<br>
 */
public interface IYycBmService {

	/**
	 * 
	 * 功能说明 : 报名
	 * @param form
	 */
	public String savebm(YycBmForm form);

}
