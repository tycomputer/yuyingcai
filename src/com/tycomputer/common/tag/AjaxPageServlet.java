/*
 * @(#)AjaxPageServlet.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.tag;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.util.SpringUtil;

/**
 * 日期 : 2010-3-5<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : <br>
 */
public class AjaxPageServlet extends HttpServlet {

	protected final Log log = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 8012148959678364533L;
	private AjaxPageBean pageBean;
	private HibernateTemplate dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		if (!getPageBean(request)) {
			return;
		}
		response.getWriter().println(getPageNoData());
		response.flushBuffer();
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	private String getPageNoData() {
		JSONObject json = new JSONObject();

		// pageIndex++;
		// 查询当前页记录
		// int page_size = pageBean.getPageSize();
		int cols = pageBean.getCols();
		try {
			// int startRecNo = (pageBean.getCurrPage() - 1) * page_size;
			// int endRecNo = page_size;
			List list = getPageData();
			list = transform(list);

			json.put("nav", pageBean.getPageNavigator());

			// NavigatorPage
			// 内容
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray rowArray = new JSONArray();// 每行数据
				Object objTemp = list.get(i);
				if (objTemp instanceof Object[]) {
					Object[] obj = (Object[]) list.get(i);
					for (int t = 0; t < cols; t++) {
						Object temp = obj[t];
						// 处理一些特殊的数据类型
						if (temp != null) {
							if (temp instanceof java.util.GregorianCalendar) {
								Calendar c = (Calendar) temp;
								if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0) {
									rowArray.put(DateUtil.format(c, "yyyy-MM-dd"));
								} else {
									rowArray.put(DateUtil.format(c, "yyyy-MM-dd HH:mm:ss"));
								}
							} else if (temp instanceof java.util.Date) {
								java.util.Date d = (java.util.Date) temp;
								Calendar c = Calendar.getInstance();
								c.setTime(d);
								if (c.get(Calendar.HOUR) == 0 && c.get(Calendar.MINUTE) == 0) {
									rowArray.put(DateUtil.format(c, "yyyy-MM-dd"));

								} else {
									rowArray.put(DateUtil.format(c, "yyyy-MM-dd HH:mm:ss"));
								}
							} else {
								rowArray.put(obj[t]);
							}
						} else {
							rowArray.put(" ");
						}
					}
				} else {
					rowArray.put(list.get(i));
				}
				array.put(rowArray);
			}
			json.put("data", array);
		} catch (Exception ex) {
			log.error("this is test" + ex);
			return json.toString();
		}

		return json.toString();
	}

	private List transform(List l) {
		if ((pageBean.getTransform() != null) && (!pageBean.getTransform().equals(""))) {
			try {
				String cName = pageBean.getTransform().substring(0, pageBean.getTransform().lastIndexOf('.'));
				String mName = pageBean.getTransform().substring(pageBean.getTransform().lastIndexOf('.') + 1, pageBean.getTransform().length());
				Class class1 = Class.forName(cName);

				Method m = class1.getMethod(mName, new Class[] { List.class, AjaxPageBean.class });
				m.invoke(class1.newInstance(), new Object[] { l, pageBean });
				return l;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return l;
	}

	/**
	 * 
	 * 功能说明 : 得到session中的PageBean并得到DAO
	 * 
	 * @param request
	 * @return
	 */
	private boolean getPageBean(HttpServletRequest request) {
		String pageBeanId = request.getParameter("i");
		String pageIndexStr = request.getParameter("p");
		int pageIndex = 1;
		try {
			pageIndex = Integer.parseInt(pageIndexStr);
		} catch (Exception e) {
		}
		Object obj = request.getSession().getAttribute(pageBeanId);
		if (obj == null) {
			return false;
		}
		pageBean = (AjaxPageBean) obj;
		dao = (HibernateTemplate) SpringUtil.getBean("dao");
		if (dao == null) {
			return false;
		}

		int count = (int) pageBean.getCount();
		int totalPage = count / pageBean.getPageSize(); // 总页数

		if (count % pageBean.getPageSize() != 0) {
			totalPage++;
		}
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrPage(pageIndex);
		request.getSession().setAttribute(pageBeanId, pageBean);
		return true;

	}

	/**
	 * 
	 * 功能说明 : 得到DAO
	 * 
	 * @return
	 */
	// private HibernateTemplate getDao() {
	// if ((pageBean.getDaoId() != null) && (!pageBean.getDaoId().equals(""))) {
	// Object obj = SpringUtil.getBean(pageBean.getDaoId());
	// if (obj != null) {
	// return (HibernateTemplate) obj;
	// }
	// }
	// // else if ((pageBean.getDbId() != null) &&
	// // (!pageBean.getDbId().equals(""))) {
	// // HibernateTemplate dao = (HibernateTemplate)
	// // SpringUtil.getBean(Constants.DEFAULT_HIBERNATE_DAO);
	// // MnDbmonconfDt dbmonconf = (MnDbmonconfDt)
	// // dao.load(MnDbmonconfDt.class, pageBean.getDbId());
	// // if (dbmonconf != null) {
	// // return new DbUtil(dbmonconf.getMnDbinfoDt());
	// // }
	// // }
	// return null;
	// }

	private List getPageData() {

		List list = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(pageBean.getSql());

				ScrollableResults sr = query.scroll();
				sr.last();
				int totalCount = sr.getRowNumber();
				totalCount++;
				pageBean.setCount(totalCount);
				int totalPage = totalCount / pageBean.getPageSize(); // 总页数
				if (totalCount % pageBean.getPageSize() != 0) {
					totalPage++;
				}
				pageBean.setTotalPage(totalPage);
				query.setFirstResult(pageBean.getPageSize() *( pageBean.getCurrPage()-1));
				query.setMaxResults(pageBean.getPageSize());
				List list = query.list();

				return list;
			}
		});
		return list;
	}

}
