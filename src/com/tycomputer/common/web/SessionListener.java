/**
 * 
 */
package com.tycomputer.common.web;

/**
 * @author Administrator
 * 
 */
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListener implements HttpSessionListener {

	private static final Log logger = LogFactory.getLog(SessionListener.class);

	

	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		session.setMaxInactiveInterval(2 * 60 * 60);// 2小时过期
		
	}

	public void sessionDestroyed(HttpSessionEvent evt) {

	}
}