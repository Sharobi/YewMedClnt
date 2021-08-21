package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

public class ReturnReasonTypeMaster implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String typeName;
    private String typeTag;
    private String remarks;
 
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}


	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	/**
	 * @return the typeTag
	 */
	public String getTypeTag() {
		return typeTag;
	}


	/**
	 * @param typeTag the typeTag to set
	 */
	public void setTypeTag(String typeTag) {
		this.typeTag = typeTag;
	}


	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}


	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "ReturnReasonTypeMaster [id=" + id + ", typeName=" + typeName + ", typeTag=" + typeTag + ", remarks="
				+ remarks + "]";
	}

}
