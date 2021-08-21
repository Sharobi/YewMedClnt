/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 *         22-Aug-2017
 */
public class TypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeName;

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

	@Override
	public String toString() {
		return "TypeMaster [id=" + id + ", typeName=" + typeName + "]";
	}
}
