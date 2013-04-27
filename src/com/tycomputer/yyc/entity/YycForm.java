package com.tycomputer.yyc.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * YycForm entity. @author MyEclipse Persistence Tools
 */

public class YycForm implements java.io.Serializable {

	// Fields

	private String uuid;
	private String formType;
	private String flag;
	private String username;
	private String childname;
	private String sex;
	private Date birthday;
	private String phone;
	private String mobile;
	private String email;
	private String addr;
	private String post;
	private String online;
	private String havTime;
	private String msgFrom;
	private Calendar inTime;
	private String note;
	private String para1;
	private String para2;
	//para3 为阅读后的标注
	private String para3;

	// Constructors

	/** default constructor */
	public YycForm() {
	}

	/** minimal constructor */
	public YycForm(String formType) {
		this.formType = formType;
	}

	/** full constructor */
	public YycForm(String formType, String username, String childname, String sex, Date birthday, String phone, String mobile, String email, String addr,
			String post, String online, String havTime, String msgFrom, String note, String para1, String para2, String para3) {
		this.formType = formType;
		this.username = username;
		this.childname = childname;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.addr = addr;
		this.post = post;
		this.online = online;
		this.havTime = havTime;
		this.msgFrom = msgFrom;
		this.note = note;
		this.para1 = para1;
		this.para2 = para2;
		this.para3 = para3;
	}

	// Property accessors

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFormType() {
		return this.formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChildname() {
		return this.childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getOnline() {
		return this.online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getHavTime() {
		return this.havTime;
	}

	public void setHavTime(String havTime) {
		this.havTime = havTime;
	}

	public String getMsgFrom() {
		return this.msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPara1() {
		return this.para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return this.para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public String getPara3() {
		return this.para3;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Calendar getInTime() {
		return inTime;
	}

	public void setInTime(Calendar inTime) {
		this.inTime = inTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}