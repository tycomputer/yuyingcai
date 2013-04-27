/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.opensymphony.xwork2.ActionContext;
import com.tycomputer.yyc.entity.YycContent;
import com.tycomputer.yyc.manager.action.YycContentForm;

/**
 * 
 * 
 * 日期 : 2012-5-14 下午7:20:30<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站新闻及其它内容 ServiceImpl<br>
 */
public class YycContentServiceImpl implements IYycContentService {

	private HibernateTemplate dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tycomputer.web.ITechartiManager#saveTecharti(com.tycomputer.web.
	 * TechartiForm)
	 */
	// @Override
	public Integer saveContent(YycContentForm form) {
		
		Integer id = form.getContId();
		boolean isnew = true;
		YycContent content = null;

		if ((id != null) && (!id.equals(""))) {
			content = (YycContent) dao.load(YycContent.class, id);
			content.setSn(form.getSn());
			isnew = false;
		}
		if (content == null) {
			content = new YycContent();
			isnew = true;

			List l = dao.find("select max(t.contId),max(t.sn) from YycContent t ");
			Object[] objs = (Object[]) l.get(0);
			Integer[] maxs = new Integer[]{0,0};
			if (objs[0] == null){
				maxs[0] = 0;
			} else {
				maxs[0] = (Integer)objs[0];
			}
			if (objs[1] == null){
				maxs[1] = 0;
			} else {
				maxs[1] = (Integer)objs[1];
			}
			maxs[0]++;
			maxs[1]++;
			content.setContId(maxs[0]);
			content.setSn(maxs[1]);
		}

		//YycContentType contentType = (YycContentType) dao.load(YycContentType.class, form.getTypeId());
		ActionContext ct = ActionContext.getContext();		
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		content.setContDesc(parseHtmlImgPath(form.getContDesc(),request.getSession().getServletContext().getContextPath()));
		content.setFlag(form.getFlag());
		content.setTypeId(form.getTypeId());
		content.setFlag(form.getFlag());
		content.setContTitle(form.getContTitle());
		content.setAddDate(Calendar.getInstance());
		if (isnew) {
			dao.save(content);
		} else {
			dao.update(content);
		}

		return content.getContId();

	}
	
	
	private String parseHtmlImgPath(String html,String contextPath){
		System.out.println("html:" + html);
		System.out.println("contextPath:" + contextPath);
		String str = null;
		if (html == null || (html.trim().equals(""))){
			str = "";
		} else if (html.indexOf("<img ") != -1){
			str = html.replaceAll(contextPath +"/html/imgs/", "../imgs/");
		} else {
			str = html;
		}
		return str;
		
	}
	public static void main(String[] args){
		YycContentServiceImpl a = new YycContentServiceImpl();
		String html= "<p><img alt=\"\" width=\"635\" height=\"453\" src=\"/dclipinyyc/html/imgs/image/Screenshot.png\" />在这里输入新闻内容！</p>";
		System.out.println(a.parseHtmlImgPath(html, "/dclipinyyc"));;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.ITechartiManager#loadEntityAndSetFrom(com.tycomputer
	 * .web.TechartiForm)
	 */
	// @Override
	public void loadEntityAndSetFrom(YycContentForm form) {
		if (form == null) {
			form = new YycContentForm();
			return;
		}
		YycContent content = (YycContent) dao.load(YycContent.class, form.getContId());
		if (content != null) {
			form.setFlag(content.getFlag());
			form.setContDesc(content.getContDesc() == null ? "" : content.getContDesc());
			form.setContTitle(content.getContTitle());
			form.setSn(content.getSn());
			form.setContId(content.getContId());
			form.setTypeId(content.getTypeId());
			
		}

	}

	public String getQuerySQL(YycContentForm form) {
		if (form == null) {
			form = new YycContentForm();
		}
		StringBuffer sb = new StringBuffer();

		sb.append("select t.contId,b.typeName,t.contTitle,t.flag,t.sn,t.addDate from YycContent t,YycContentType b where b.typeId=t.typeId ");

		if ((form.getSearchContTitle() != null) && (!form.getSearchContTitle().trim().equals(""))) {
			sb.append(" and t.contTitle like '%").append(form.getSearchContTitle().trim()).append("%' ");
		}
		if ((form.getSearchTypeId() != null) && (!form.getSearchTypeId().trim().equals(""))) {
			sb.append(" and t.contentType.typeId ='").append(form.getSearchTypeId().trim()).append("' ");
		}

		sb.append(" order by t.addDate desc");
		return sb.toString();
	}

	public void delContent(YycContentForm form) {
		YycContent content = (YycContent) dao.load(YycContent.class, form.getContId());
		if (content != null) {
			
			dao.delete(content);
		}
	}

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

}
