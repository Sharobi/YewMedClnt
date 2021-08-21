/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 
 */
public class PaymentMode implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String mode;
    private int storeId;
    private int companyId;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		return "PaymentMode [id=" + id + ", mode=" + mode + ", storeId=" + storeId + ", companyId=" + companyId + "]";
	}

}
