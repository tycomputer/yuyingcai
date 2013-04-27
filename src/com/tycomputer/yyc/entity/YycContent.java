package com.tycomputer.yyc.entity;

import java.util.Calendar;

import com.tycomputer.common.util.DateUtil;

/**
 * Datatype entity. @author MyEclipse Persistence Tools
 */

public class YycContent implements java.io.Serializable {

	private static final long serialVersionUID = 6360838059711097901L;
	private java.lang.Integer contId;
	private String typeId;
	private String contTitle;
	private String contDesc;
	private Integer sn;
	private String flag;
	private Calendar addDate;
	
	
	public String getContDate(){
		return DateUtil.format(addDate);
	}
	

	public String getContTitle() {
		return contTitle;
	}

	public void setContTitle(String contTitle) {
		this.contTitle = contTitle;
	}

	public String getContDesc() {
		return contDesc;
	}

	public void setContDesc(String contDesc) {
		this.contDesc = contDesc;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public java.lang.Integer getContId() {
		return contId;
	}

	public void setContId(java.lang.Integer contId) {
		this.contId = contId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contId == null) ? 0 : contId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YycContent other = (YycContent) obj;
		if (contId == null) {
			if (other.contId != null)
				return false;
		} else if (!contId.equals(other.contId))
			return false;
		return true;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Calendar getAddDate() {
		return addDate;
	}

	public void setAddDate(Calendar addDate) {
		this.addDate = addDate;
	}

}