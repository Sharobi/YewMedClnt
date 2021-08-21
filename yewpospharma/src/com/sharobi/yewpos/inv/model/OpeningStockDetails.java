package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class OpeningStockDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private int itemId;

    private String batchNo;

    private Date expiryDate;
    
	private String expiryDateFormat;

    private int packQty;

    private double mrp;

    private double rate;

    private String asOnDate;

    private int lastDistributorId;

    private int finyrId;

    private int packUnitId;

    private int conversion;

    private int looseQty;

    private int storeId;

    private int companyId;

    private int isDeleted;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;
    
    private double vatPer;

    private double taxPer;
    
    private int taxId;

    private double taxPercentage;
    
    private double taxAmount;
    
    private int isGroupTax;
    
    private String taxMode;
    
    private double saleRate;
    

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
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}


	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}


	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}


	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	/**
	 * @return the expiryDateFormat
	 */
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}


	/**
	 * @param expiryDateFormat the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}


	/**
	 * @return the packQty
	 */
	public int getPackQty() {
		return packQty;
	}


	/**
	 * @param packQty the packQty to set
	 */
	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}


	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}


	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}


	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}


	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}


	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}


	/**
	 * @param asOnDate the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}


	/**
	 * @return the lastDistributorId
	 */
	public int getLastDistributorId() {
		return lastDistributorId;
	}


	/**
	 * @param lastDistributorId the lastDistributorId to set
	 */
	public void setLastDistributorId(int lastDistributorId) {
		this.lastDistributorId = lastDistributorId;
	}


	/**
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}


	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}


	/**
	 * @return the packUnitId
	 */
	public int getPackUnitId() {
		return packUnitId;
	}


	/**
	 * @param packUnitId the packUnitId to set
	 */
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}


	/**
	 * @return the conversion
	 */
	public int getConversion() {
		return conversion;
	}


	/**
	 * @param conversion the conversion to set
	 */
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}


	/**
	 * @return the looseQty
	 */
	public int getLooseQty() {
		return looseQty;
	}


	/**
	 * @param looseQty the looseQty to set
	 */
	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
	}


	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}


	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}


	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}


	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}


	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}


	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}


	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	/**
	 * @return the vatPer
	 */
	public double getVatPer() {
		return vatPer;
	}


	/**
	 * @param vatPer the vatPer to set
	 */
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}


	/**
	 * @return the taxPer
	 */
	public double getTaxPer() {
		return taxPer;
	}


	/**
	 * @param taxPer the taxPer to set
	 */
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}


	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}


	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}


	/**
	 * @return the taxPercentage
	 */
	public double getTaxPercentage() {
		return taxPercentage;
	}


	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}


	/**
	 * @return the taxAmount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}


	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}


	/**
	 * @return the isGroupTax
	 */
	public int getIsGroupTax() {
		return isGroupTax;
	}


	/**
	 * @param isGroupTax the isGroupTax to set
	 */
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}


	/**
	 * @return the taxMode
	 */
	public String getTaxMode() {
		return taxMode;
	}


	/**
	 * @param taxMode the taxMode to set
	 */
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}


	/**
	 * @return the saleRate
	 */
	public double getSaleRate() {
		return saleRate;
	}


	/**
	 * @param saleRate the saleRate to set
	 */
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpeningStockDetails [id=" + id + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate="
				+ expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packQty=" + packQty + ", mrp=" + mrp
				+ ", rate=" + rate + ", asOnDate=" + asOnDate + ", lastDistributorId=" + lastDistributorId
				+ ", finyrId=" + finyrId + ", packUnitId=" + packUnitId + ", conversion=" + conversion + ", looseQty="
				+ looseQty + ", storeId=" + storeId + ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", vatPer=" + vatPer + ", taxPer=" + taxPer + ", taxId=" + taxId
				+ ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax
				+ ", taxMode=" + taxMode + ", saleRate=" + saleRate + "]";
	}

	
}
