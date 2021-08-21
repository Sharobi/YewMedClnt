/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *  
 */
public class StockDetailsAdjustmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int itemId;
	private String itemName;
	private String sku;
	private String hsnCode;
	private String batchNo;
	private String expiryDate;
	private String expiryDateFormat;
	private int packQty;
	private int conversion;
	private int looseQty;
	private int packUnitId;
	private String packUnitName;
	private String asOnDate;
	private double mrp;
	private double rate;
	private double saleRate;
	private int distributorId;
	private String distributorName;
	private int remainingLooseQty;
	private int isSale;
	private int companyId;
    private int storeId;
    private int finyrId;
    private int createdBy;
    private int taxId;
    private double taxPercentage;

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
	 * @return the hsnCode
	 */
	public String getHsnCode() {
		return hsnCode;
	}

	/**
	 * @param hsnCode the hsnCode to set
	 */
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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
	 * @return the packUnitName
	 */
	public String getPackUnitName() {
		return packUnitName;
	}

	/**
	 * @param packUnitName the packUnitName to set
	 */
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
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

	/**
	 * @return the distributorId
	 */
	public int getDistributorId() {
		return distributorId;
	}

	/**
	 * @param distributorId the distributorId to set
	 */
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	/**
	 * @return the distributorName
	 */
	public String getDistributorName() {
		return distributorName;
	}

	/**
	 * @param distributorName the distributorName to set
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	/**
	 * @return the remainingLooseQty
	 */
	public int getRemainingLooseQty() {
		return remainingLooseQty;
	}

	/**
	 * @param remainingLooseQty the remainingLooseQty to set
	 */
	public void setRemainingLooseQty(int remainingLooseQty) {
		this.remainingLooseQty = remainingLooseQty;
	}

	/**
	 * @return the isSale
	 */
	public int getIsSale() {
		return isSale;
	}

	/**
	 * @param isSale the isSale to set
	 */
	public void setIsSale(int isSale) {
		this.isSale = isSale;
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

	@Override
	public String toString() {
		return "StockDetailsAdjustmentDTO [id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", sku=" + sku + ", hsnCode=" + hsnCode + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", asOnDate=" + asOnDate + ", mrp=" + mrp + ", rate=" + rate + ", saleRate=" + saleRate + ", distributorId=" + distributorId + ", distributorName=" + distributorName + ", remainingLooseQty=" + remainingLooseQty + ", isSale=" + isSale + ", companyId=" + companyId + ", storeId=" + storeId + ", finyrId=" + finyrId + ", createdBy=" + createdBy + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + "]";
	}

}
