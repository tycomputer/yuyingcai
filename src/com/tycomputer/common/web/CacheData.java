/*
 * @(#)OnLoadCacheData.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 启动时载入，缓存标准数据<br>
 */
public class CacheData {

	private HibernateTemplate dao;
	
	/**
	 * 育英才 新闻，最后添加的100个，网站只显示100个
	 */
	public static List<String[]> YYC_NEWS = new ArrayList<String[]>();



	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	public void cache(){
		
		
		
		CacheData.YYC_NEWS = dao.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("select t.uuid,t.newsTitle,t.addData from YycNews t where t.flag='0' order by t.addData desc");
				query.setFirstResult(0);
				query.setMaxResults(100);
				List l = query.list();
				List<String[]> itemList = new ArrayList<String[]>();
				for (int i=0; i<l.size(); i++){
					Object[] obj = (Object[])l.get(i);
					String[] item = new String[3];
					item[0] = (String)obj[0];
					item[1] = (String)obj[1];
					item[2] = DateUtil.format((Calendar)obj[2], "yyyy-MM-dd");
					itemList.add(item);
				}				
				return itemList;		
			}
		});
	}
	
}
