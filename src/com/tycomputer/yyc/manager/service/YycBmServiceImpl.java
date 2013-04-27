/*
 * @(#)TechartiManagerImpl.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.yyc.manager.service;


import java.text.SimpleDateFormat;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tycomputer.common.util.DateUtil;
import com.tycomputer.common.util.SendMail;
import com.tycomputer.yyc.entity.YycForm;
import com.tycomputer.yyc.manager.action.YycBmForm;

/**
 * 
 * 日期 : 2011-10-8 下午6:15:14<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipinyyc<br>
 * 功能 : 育英才网站表单 ServiceImpl<br>
 */
public class YycBmServiceImpl implements IYycBmService {

	private HibernateTemplate dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tycomputer.yyc.manager.service.IYycBmService#bm(com.tycomputer.yyc
	 * .manager.action.YycBmForm)
	 */
	public String savebm(YycBmForm form) {
		if (form == null){
			return null;
		}
		YycForm yycForm = new YycForm();
		yycForm.setFormType("0");
		yycForm.setFlag("0");
		yycForm.setUsername(form.getName());
		yycForm.setSex(form.getSex());
		if (form.getBirthday() != null && (!form.getBirthday().equals(""))){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			try {
				yycForm.setBirthday(sdf.parse(form.getBirthday()));
			} catch (Exception e) {
			}		
		}
		yycForm.setMobile(form.getMobile());
		yycForm.setPhone(form.getPhone());
		yycForm.setEmail(form.getEmail());
		yycForm.setOnline(form.getOnline());
		yycForm.setNote(form.getNote());
		if ((form.getMsgFrom()!=null) && (form.getMsgFrom().length > 0)){
			StringBuffer sb = new StringBuffer();
			for (String msg : form.getMsgFrom()){
				sb.append(msg).append("   ");
			}
			yycForm.setMsgFrom(sb.toString());
		}
		yycForm.setInTime(DateUtil.getSysCalendar());
		yycForm.setPara1(form.getEdu());
		dao.save(yycForm);
		
		SendMail.getInstants().sendToTycomputer("有人报名了", null, "有人报名了，UUID=" + yycForm.getUuid());
		return yycForm.getUuid();
	}

	public void setDao(HibernateTemplate dao) {
		this.dao = dao;
	}

}