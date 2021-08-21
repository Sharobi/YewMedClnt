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
public class EsiCodeMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "EsiCodeMaster [id=" + id + ", code=" + code + "]";
	}
}
