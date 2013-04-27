package com.tycomputer.common.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.views.jsp.ui.SelectTag;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * 日期 : 2010-5-10<br>
 * 作者 : zhangliuhua<br>
 * 项目 : GS_WUMONWeb<br>
 * 功能 : <br>
 */
public class SqlSelectTag extends SelectTag {

	private static final long serialVersionUID = 7214772770751704280L;

	

	private String sql = null; // 查询sql

	//private String dao;// spring 中配置的dao的id


	private void getQueryData() {
		if ((this.sql == null) || (this.sql.equals(""))) {
			return;
		}
		// 如果sql 不是以select开头，从request中取
		if (!sql.toLowerCase().trim().startsWith("select")) {
			sql = (String) pageContext.getRequest().getAttribute(sql);
			if (sql == null) {
				return;
			}
		}
		List list = getBean().find(sql);
		StringBuffer sb = new StringBuffer("#{");
		for (int i = 0; ((list != null) && (i < list.size())); i++) {
			Object[] obj = (Object[]) list.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append("'").append(obj[0]).append("':'").append(obj[1]).append("'");
		}
		sb.append("}");
		this.setList(sb.toString());

	}

	

	protected HibernateTemplate getBean() {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		WebApplicationContext webApplicationContext = (WebApplicationContext) request.getSession().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		return (HibernateTemplate) webApplicationContext.getBean("dao");
	}
	
	

	public void setSql(String sql) {
		this.sql = sql;
		getQueryData();
	}

	
	
}
