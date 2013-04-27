package com.tycomputer.yyc.entity;


public class YycContentType implements java.io.Serializable {

	private static final long serialVersionUID = 550795814962535365L;
	private String typeId;
	private String typeName;
	private String templatesFile;
	private String flag;
	private String outDir;
	//private Set<YycContent> contents = new HashSet<YycContent>(0);

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTemplatesFile() {
		return templatesFile;
	}

	public void setTemplatesFile(String templatesFile) {
		this.templatesFile = templatesFile;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOutDir() {
		return outDir;
	}

	public void setOutDir(String outDir) {
		this.outDir = outDir;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
		YycContentType other = (YycContentType) obj;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}

}