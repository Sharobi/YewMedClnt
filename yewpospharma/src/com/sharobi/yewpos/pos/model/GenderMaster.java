/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 
 */
public class GenderMaster implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
 
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "GenderMaster [id=" + id + ", name=" + name + "]";
	}
    
    
}
