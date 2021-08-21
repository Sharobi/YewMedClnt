/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrderDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
    
    private int purchaseOrderId;

    private String invNo;

    private Date invDate;

    private int itemId;
    
    private int packUnitId;
    
    private int packQty;
    
    private int conversion;
    
    private int looseQty;
    
    private double freeQty;
   
    private int recPackQty;
    
    private int recLooseQty;
    
    private double mrp;
    
    private double rate;
    
    private double saleRate;

    private double grossAmount;
    
    private double discPer;

    private double disc;
   
    private int taxId;
    
    private double taxPercentage;
    
    private double taxAmount;
    
    private String taxMode;
    
    private int isGroupTax;
    
    private double netAmount;
   
    private int tmpId;
  
    private int finyrId;

    private int storeId;

    private int companyId;

    private String lang;
   
    private String invType;
    
    private double itemTotalMrp;

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
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo the invNo to set
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	/**
	 * @return the invDate
	 */
	public Date getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate the invDate to set
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
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
	 * @return the freeQty
	 */
	public double getFreeQty() {
		return freeQty;
	}

	/**
	 * @param freeQty the freeQty to set
	 */
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}

	/**
	 * @return the recPackQty
	 */
	public int getRecPackQty() {
		return recPackQty;
	}

	/**
	 * @param recPackQty the recPackQty to set
	 */
	public void setRecPackQty(int recPackQty) {
		this.recPackQty = recPackQty;
	}

	/**
	 * @return the recLooseQty
	 */
	public int getRecLooseQty() {
		return recLooseQty;
	}

	/**
	 * @param recLooseQty the recLooseQty to set
	 */
	public void setRecLooseQty(int recLooseQty) {
		this.recLooseQty = recLooseQty;
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
	 * @return the grossAmount
	 */
	public double getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount the grossAmount to set
	 */
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the discPer
	 */
	public double getDiscPer() {
		return discPer;
	}

	/**
	 * @param discPer the discPer to set
	 */
	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	/**
	 * @return the disc
	 */
	public double getDisc() {
		return disc;
	}

	/**
	 * @param disc the disc to set
	 */
	public void setDisc(double disc) {
		this.disc = disc;
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
	 * @return the netAmount
	 */
	public double getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the tmpId
	 */
	public int getTmpId() {
		return tmpId;
	}

	/**
	 * @param tmpId the tmpId to set
	 */
	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
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
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the invType
	 */
	public String getInvType() {
		return invType;
	}

	/**
	 * @param invType the invType to set
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}

	/**
	 * @return the itemTotalMrp
	 */
	public double getItemTotalMrp() {
		return itemTotalMrp;
	}

	/**
	 * @param itemTotalMrp the itemTotalMrp to set
	 */
	public void setItemTotalMrp(double itemTotalMrp) {
		this.itemTotalMrp = itemTotalMrp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseOrderDetails [id=" + id + ", purchaseOrderId=" + purchaseOrderId + ", invNo=" + invNo
				+ ", invDate=" + invDate + ", itemId=" + itemId + ", packUnitId=" + packUnitId + ", packQty=" + packQty
				+ ", conversion=" + conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", recPackQty="
				+ recPackQty + ", recLooseQty=" + recLooseQty + ", mrp=" + mrp + ", rate=" + rate + ", saleRate="
				+ saleRate + ", grossAmount=" + grossAmount + ", discPer=" + discPer + ", disc=" + disc + ", taxId="
				+ taxId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", netAmount=" + netAmount + ", tmpId=" + tmpId + ", finyrId="
				+ finyrId + ", storeId=" + storeId + ", companyId=" + companyId + ", lang=" + lang + ", invType="
				+ invType + ", itemTotalMrp=" + itemTotalMrp + "]";
	}
	
    

}
