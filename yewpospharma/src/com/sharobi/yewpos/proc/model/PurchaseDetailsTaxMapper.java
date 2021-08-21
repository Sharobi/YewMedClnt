/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author habib
 *
 */
public class PurchaseDetailsTaxMapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int purchaseId;
    private int purchaseDetailsId;
    private int itemId;
    private int taxId;
    private double percentage;
    private double amount;
    private String taxMode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getPurchaseDetailsId() {
		return purchaseDetailsId;
	}
	public void setPurchaseDetailsId(int purchaseDetailsId) {
		this.purchaseDetailsId = purchaseDetailsId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	@Override
	public String toString() {
		return "PurchaseDetailsTaxMapper [id=" + id + ", purchaseId="
				+ purchaseId + ", purchaseDetailsId=" + purchaseDetailsId
				+ ", itemId=" + itemId + ", taxId=" + taxId + ", percentage="
				+ percentage + ", amount=" + amount + ", taxMode=" + taxMode
				+ "]";
	}
    
    

}
