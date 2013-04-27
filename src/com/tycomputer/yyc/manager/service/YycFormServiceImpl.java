/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.yyc.entity.YycForm;
import com.tycomputer.yyc.manager.action.YycFormForm;

/**
 * 
 * 日期 : 2011-10-8 下午6:15:14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站表单 ServiceImpl<br>
 */
public class YycFormServiceImpl implements IYycFormService {

	private HibernateTemplate dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tycomputer.web.ITechartiManager#saveTecharti(com.tycomputer.web.
	 * TechartiForm)
	 */
	// @Override
	public void saveForm(YycFormForm form) {

		YycForm yycForm = (YycForm) dao.load(YycForm.class, form.getUuid());

		if (yycForm != null) {
			yycForm.setFlag(form.getFlag());
			yycForm.setPara3(form.getPara3());
			dao.update(yycForm);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.web.ITechartiManager#loadEntityAndSetFrom(com.tycomputer
	 * .web.TechartiForm)
	 */
	// @Override
	public void loadEntityAndSetFrom(YycFormForm form) {
		if (form == null) {
			form = new YycFormForm();
			return;
		}
		YycForm yycForm = (YycForm) dao.load(YycForm.class, form.getUuid());
		if (yycForm != null) {
			form.setFormType(yycForm.getFormType());
			form.setFlag(yycForm.getFlag().equals("0") ? "未读" : "已读");
			form.setFormType(yycForm.getFormType().equals("0") ? "报名" : "未知");
			form.setUsername(yycForm.getUsername());
			form.setChildname(yycForm.getChildname());
			form.setSex(yycForm.getSex().equals("0") ? "女" : "男");
			form.setBirthdayStr(DateUtil.format(yycForm.getBirthday()));
			form.setPhone(yycForm.getPhone());
			form.setMobile(yycForm.getMobile());
			form.setEmail(yycForm.getEmail());
			form.setAddr(yycForm.getAddr());
			form.setPost(yycForm.getPost());
			form.setOnline(yycForm.getOnline());
			form.setHavTime(yycForm.getHavTime());
			form.setMsgFrom(yycForm.getMsgFrom());
			form.setInTimeStr(DateUtil.format(yycForm.getInTime(), "yyyy-MM-dd HH:mm:ss"));
			form.setNote(yycForm.getNote());
			String para1 = "";
			if (yycForm.getPara1() != null) {
				if (yycForm.getPara1().equals("0")) {
					para1 = "高中以下";
				} else if (yycForm.getPara1().equals("1")) {
					para1 = "高中/中专";
				} else if (yycForm.getPara1().equals("2")) {
					para1 = "大专";
				} else if (yycForm.getPara1().equals("3")) {
					para1 = "本科";
				} else if (yycForm.getPara1().equals("4")) {
					para1 = "本科以上";
				}
			}
			form.setPara1(para1);
			form.setPara2(yycForm.getPara2());
			form.setPara3(yycForm.getPara3());
		}

	}

	public String getQuerySQL(YycFormForm form) {
		if (form == null) {
			form = new YycFormForm();
		}
		StringBuffer sb = new StringBuffer();

		sb.append("select t.uuid,t.username,t.formType,t.flag,t.inTime,t.para3 from YycForm t");
		boolean where = false;
		if ((form.getSearchFlag() != null) && (!form.getSearchFlag().equals(""))) {
			where = true;
			sb.append(" where t.flag='").append(form.getSearchFlag()).append("' ");
		}

		if ((form.getSearchName() != null) && (!form.getSearchName().trim().equals(""))) {
			if (!where) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(" t.username like '%").append(form.getSearchName().trim()).append("%' ");
		}

		sb.append(" order by t.flag,t.inTime desc");
		return sb.toString();
	}

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

}