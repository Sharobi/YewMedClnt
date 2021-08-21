package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryPurchaseMapping implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private int purchaseId;

    private int purchaseInvNo;

    private Date purchaseInvDate;

    private int expiryId;

    private int expiryDetailsId;

    private String expiryInvNo;

    private String expiryInvDate;

    private int itemId;

    private String batchNo;

    private Date expiryDate;

    private int packQty;

    private int conversion;

    private int looseQty;

    private double freeQty;

    private double mrp;

    private double rate;

    private int isMrp;

    private double netAmount;

    private double adjAmount;

    private String lang;
    
    private String expiryDateFormat;
    
    

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

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

	public int getPurchaseInvNo() {
		return purchaseInvNo;
	}

	public void setPurchaseInvNo(int purchaseInvNo) {
		this.purchaseInvNo = purchaseInvNo;
	}

	public Date getPurchaseInvDate() {
		return purchaseInvDate;
	}

	public void setPurchaseInvDate(Date purchaseInvDate) {
		this.purchaseInvDate = purchaseInvDate;
	}

	public int getExpiryId() {
		return expiryId;
	}

	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
	}

	public int getExpiryDetailsId() {
		return expiryDetailsId;
	}

	public void setExpiryDetailsId(int expiryDetailsId) {
		this.expiryDetailsId = expiryDetailsId;
	}

	public String getExpiryInvNo() {
		return expiryInvNo;
	}

	public void setExpiryInvNo(String expiryInvNo) {
		this.expiryInvNo = expiryInvNo;
	}

	public String getExpiryInvDate() {
		return expiryInvDate;
	}

	public void setExpiryInvDate(String expiryInvDate) {
		this.expiryInvDate = expiryInvDate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getPackQty() {
		return packQty;
	}

	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public int getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
	}

	public double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getIsMrp() {
		return isMrp;
	}

	public void setIsMrp(int isMrp) {
		this.isMrp = isMrp;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getAdjAmount() {
		return adjAmount;
	}

	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "ExpiryPurchaseMapping [id=" + id + ", purchaseId=" + purchaseId + ", purchaseInvNo=" + purchaseInvNo
				+ ", purchaseInvDate=" + purchaseInvDate + ", expiryId=" + expiryId + ", expiryDetailsId="
				+ expiryDetailsId + ", expiryInvNo=" + expiryInvNo + ", expiryInvDate=" + expiryInvDate + ", itemId="
				+ itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packQty=" + packQty
				+ ", conversion=" + conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp
				+ ", rate=" + rate + ", isMrp=" + isMrp + ", netAmount=" + netAmount + ", adjAmount=" + adjAmount
				+ ", lang=" + lang + ", expiryDateFormat=" + expiryDateFormat + "]";
	}

	

}
