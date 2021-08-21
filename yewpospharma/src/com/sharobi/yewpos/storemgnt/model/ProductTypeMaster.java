/**
 * 
 */
package com.sharobi.yewpos.storemgnt.model;

import java.io.Serializable;

/**
 * @author habib
 *
 */
public class ProductTypeMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String typeName;
    private String typeTag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(String typeTag) {
		this.typeTag = typeTag;
	}
	@Override
	public String toString() {
		return "ProductTypeMaster [id=" + id + ", typeName=" + typeName
				+ ", typeTag=" + typeTag + "]";
	}
    
    

}
