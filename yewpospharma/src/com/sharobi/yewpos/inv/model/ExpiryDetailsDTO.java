package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDetailsDTO implements Serializable {

	private int itemId;

	private String itemName;

	private String batchNo;

	private String expiryDate;

	private String expiryDateFormat;

	private int packUnitId;

	private String packUnitName;

	private int packQty;

	private int conversion;

	private int looseQty;

	private double freeQty;

	private double mrp;

	private double rate;

	private double amount;

	private int distributorId;

	private String distributorName;

	private String itemUniqueKey;

	private String lang;

	private String netContent;

	private int calculateLooseQty;

	private String stockQty;

	private String rackName;

	private int rackId;

	private int expiryId;

	private int expiryDetailsId;

	private String invNo;

	private Date invDate;

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
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	 * @return the packUnitName
	 */
	public String getPackUnitName() {
		return packUnitName;
	}

	/**
	 * @param packUnitName
	 *            the packUnitName to set
	 */
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
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
	 * @return the distributorName
	 */
	public String getDistributorName() {
		return distributorName;
	}

	/**
	 * @param distributorName
	 *            the distributorName to set
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	/**
	 * @return the itemUniqueKey
	 */
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	/**
	 * @param itemUniqueKey
	 *            the itemUniqueKey to set
	 */
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
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

	/**
	 * @return the netContent
	 */
	public String getNetContent() {
		return netContent;
	}

	/**
	 * @param netContent
	 *            the netContent to set
	 */
	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}

	/**
	 * @return the calculateLooseQty
	 */
	public int getCalculateLooseQty() {
		return calculateLooseQty;
	}

	/**
	 * @param calculateLooseQty
	 *            the calculateLooseQty to set
	 */
	public void setCalculateLooseQty(int calculateLooseQty) {
		this.calculateLooseQty = calculateLooseQty;
	}

	/**
	 * @return the stockQty
	 */
	public String getStockQty() {
		return stockQty;
	}

	/**
	 * @param stockQty
	 *            the stockQty to set
	 */
	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}

	/**
	 * @return the rackName
	 */
	public String getRackName() {
		return rackName;
	}

	/**
	 * @param rackName
	 *            the rackName to set
	 */
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	/**
	 * @return the rackId
	 */
	public int getRackId() {
		return rackId;
	}

	/**
	 * @param rackId
	 *            the rackId to set
	 */
	public void setRackId(int rackId) {
		this.rackId = rackId;
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
	 * @return the expiryDetailsId
	 */
	public int getExpiryDetailsId() {
		return expiryDetailsId;
	}

	/**
	 * @param expiryDetailsId
	 *            the expiryDetailsId to set
	 */
	public void setExpiryDetailsId(int expiryDetailsId) {
		this.expiryDetailsId = expiryDetailsId;
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
	public Date getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate
	 *            the invDate to set
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	@Override
	public String toString() {
		return "ExpiryDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount="
				+ amount + ", distributorId=" + distributorId + ", distributorName=" + distributorName
				+ ", itemUniqueKey=" + itemUniqueKey + ", lang=" + lang + ", netContent=" + netContent
				+ ", calculateLooseQty=" + calculateLooseQty + ", stockQty=" + stockQty + ", rackName=" + rackName
				+ ", rackId=" + rackId + ", expiryId=" + expiryId + ", expiryDetailsId=" + expiryDetailsId + ", invNo="
				+ invNo + ", invDate=" + invDate + "]";
	}

}
