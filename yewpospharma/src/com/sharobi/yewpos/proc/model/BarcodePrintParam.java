/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Arunima Roy
 *
 * Mar 20, 2018
 */
public class BarcodePrintParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemId;
    
    private String itemName;
    
    private String sku;
    
    private String mrp;
    
    private String saleRate;
    
    private String batch;
    
    private String noOfCopies;
    
    private String expiryDate;
    
    private String storeName;
    
    private String storeCurrency;
    
    // new added 23.5.2019
    private String itemCategory;
    private String itemSubCategory;
    private String grpName;
    private String size;
    private String colour;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the mrp
	 */
	public String getMrp() {
		return mrp;
	}

	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	/**
	 * @return the saleRate
	 */
	public String getSaleRate() {
		return saleRate;
	}

	/**
	 * @param saleRate the saleRate to set
	 */
	public void setSaleRate(String saleRate) {
		this.saleRate = saleRate;
	}

	/**
	 * @return the batch
	 */
	public String getBatch() {
		return batch;
	}

	/**
	 * @param batch the batch to set
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}

	/**
	 * @return the noOfCopies
	 */
	public String getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(String noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the storeCurrency
	 */
	public String getStoreCurrency() {
		return storeCurrency;
	}

	/**
	 * @param storeCurrency the storeCurrency to set
	 */
	public void setStoreCurrency(String storeCurrency) {
		this.storeCurrency = storeCurrency;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSubCategory() {
		return itemSubCategory;
	}

	public void setItemSubCategory(String itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}

	@Override
	public String toString() {
		return "BarcodePrintParam [itemId=" + itemId + ", itemName=" + itemName + ", sku=" + sku + ", mrp=" + mrp
				+ ", saleRate=" + saleRate + ", batch=" + batch + ", noOfCopies=" + noOfCopies + ", expiryDate="
				+ expiryDate + ", storeName=" + storeName + ", storeCurrency=" + storeCurrency + ", itemCategory="
				+ itemCategory + ", itemSubCategory=" + itemSubCategory + ", grpName=" + grpName + ", size=" + size
				+ ", colour=" + colour + "]";
	}

	
	
	
	
    
}
