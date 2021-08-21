/**
 * 
 */
package com.sharobi.yewpos.acc.model;

import java.io.Serializable;

/**
 * @author habib
 *
 */
public class QSMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String qs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQs() {
		return qs;
	}
	public void setQs(String qs) {
		this.qs = qs;
	}
	@Override
	public String toString() {
		return "QSMaster [id=" + id + ", qs=" + qs + "]";
	} 
    
    

}
