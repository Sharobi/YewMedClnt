package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int expiryId;

	private String invNo;

	private String invDate;

	private int itemId;

	private String batchNo;

	private String expiryDate;

	private String expiryDateFormat;

	private int packUnitId;

	private int packQty;

	private int conversion;

	private int looseQty;

	private double freeQty;

	private double mrp;

	private double rate;

	private double amount;

	private int distributorId;

	private int finyrId;

	private int companyId;

	private int storeId;

	private String lang;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the expiryId
	 */
	public int getExpiryId() {
		return expiryId;
	}

	/**
	 * @param expiryId
	 *            the expiryId to set
	 */
	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo
	 *            the invNo to set
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	/**
	 * @return the invDate
	 */
	public String getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate
	 *            the invDate to set
	 */
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
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
	 * @param batchNo
	 *            the batchNo to set
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
	 * @param expiryDate
	 *            the expiryDate to set
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
	 * @param expiryDateFormat
	 *            the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	/**
	 * @return the packUnitId
	 */
	public int getPackUnitId() {
		return packUnitId;
	}

	/**
	 * @param packUnitId
	 *            the packUnitId to set
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
	 * @param packQty
	 *            the packQty to set
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
	 * @param conversion
	 *            the conversion to set
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
	 * @param looseQty
	 *            the looseQty to set
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
	 * @param freeQty
	 *            the freeQty to set
	 */
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}

	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}

	/**
	 * @param mrp
	 *            the mrp to set
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
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the distributorId
	 */
	public int getDistributorId() {
		return distributorId;
	}

	/**
	 * @param distributorId
	 *            the distributorId to set
	 */
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	/**
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}

	/**
	 * @param finyrId
	 *            the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
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
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "ExpiryDetails [id=" + id + ", expiryId=" + expiryId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packUnitId="
				+ packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty
				+ ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", distributorId="
				+ distributorId + ", finyrId=" + finyrId + ", companyId=" + companyId + ", storeId=" + storeId
				+ ", lang=" + lang + "]";
	}

}
