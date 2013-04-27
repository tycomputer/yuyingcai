/*
 * @(#)SpringUtil.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 日期 : 2010-4-14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : GS_WUMONWeb<br>
 * 功能 : <br>
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	/**
	 * 
	 * 功能说明 : 返回 springBean
	 * 
	 * @param beanid
	 * @return
	 */
	public static Object getBean(String beanid) {
		return applicationContext.getBean(beanid);

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		SpringUtil.applicationContext = ac;

	}
	
	
}
