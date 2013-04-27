/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.tycomputer.common.util.DateUtil;
import com.tycomputer.yyc.entity.YycContent;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 生成html页面 managerImpl<br>
 */

public class YycGenhtmlServiceImpl implements IYycGenhtmlService {
	private HibernateTemplate dao;

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

	private ServletContext sc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tycomputer.manager.service.IGenhtmlService#genhtml()
	 */
	public boolean genhtml() {

		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		sc = request.getSession().getServletContext();

		delNewsDir();

		List<YycContent> newsList = dao.find("from YycContent t where t.typeId='01' and t.flag in ('1','2') order by t.flag desc,t.sn desc,t.addDate desc");

		if (newsList != null) {
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("UTF-8");
			cfg.setServletContextForTemplateLoading(sc, "WEB-INF/templates");
			try {
				Map map = new HashMap();
				map.put("contentList", newsList);
				// 生成 newsList
				Template t = cfg.getTemplate("newsList.ftl");
				t.setEncoding("UTF-8");
				t.setOutputEncoding("UTF-8");
				File page = new File(sc.getRealPath("/") + "/html/news/list.html");
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(page), "UTF-8"));
				t.process(map, out);
				
				//生成sitemap.xml
				t = cfg.getTemplate("yycSitemapXml.ftl");
				t.setEncoding("UTF-8");
				t.setOutputEncoding("UTF-8");
				page = new File(sc.getRealPath("/") + "/html/yyc/Sitemap.xml");
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(page), "UTF-8"));
				map = new HashMap();
				map.put("contentList", newsList);
				map.put("currDate", DateUtil.getCurrDate());
				t.process(map, out);

				
				List<YycContent> indexNewList = new ArrayList<YycContent>();	
				//生成每一个新闻
				t = cfg.getTemplate("newsDetail.ftl");
				t.setEncoding("UTF-8");
				t.setOutputEncoding("UTF-8");
				for (YycContent yycContent : newsList) {
					map = new HashMap();
					map.put("content", yycContent);		
					if (indexNewList.size() < 4){
						indexNewList.add(yycContent);						
					}
					page = new File(sc.getRealPath("/") + "/html/news/news" + yycContent.getContId() + ".html");
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(page), "UTF-8"));
					t.process(map, out);
				}
				
				//生成首页的index.js
				map = new HashMap();
				map.put("contentList", indexNewList);		
				t = cfg.getTemplate("indexjs.ftl");
				page = new File(sc.getRealPath("/") + "/html/js/index.js");
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(page), "UTF-8"));
				t.process(map, out);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	// 删除 先
	private void delNewsDir() {
		System.out.println("------  " + sc.getRealPath("/") + "/html/news/");
		File newsDir = new File(sc.getRealPath("/") + "/html/news/");
		if (newsDir.exists()) {
			File[] files = newsDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (!files[i].getName().equals("menu.js")) {
					files[i].delete();
				}
			}
		} else {
			try {
				newsDir.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// private boolean genHtml(List<YycContent> newsList){
	// Configuration cfg = new Configuration();
	// cfg.setDefaultEncoding("UTF-8");
	// cfg.setServletContextForTemplateLoading(sc, "WEB-INF/templates");
	// try {
	// Template t = cfg.getTemplate("newsList.ftl");
	// t.setEncoding("UTF-8");
	// t.setOutputEncoding("UTF-8");
	// File page = new File(sc.getRealPath("/") + "/html/news/list.html");
	// Writer out = new BufferedWriter(new OutputStreamWriter(new
	// FileOutputStream(page),"UTF-8"));
	// Map map = new HashMap();
	// map.put("contentList", newsList);
	// t.process(map, out);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	// }

}
