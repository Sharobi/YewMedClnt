package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 */

public class ItemCurrentStockDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private String batchNo;
	private Date expiryDate;
	private String expiryDateFormat;
	private int packUnitId;
	private int conversion;
	private double mrp;
	private int packQty;
	private double looseQty;
	private String packUnitName;
	private String contentName;
	private String manufacturerName;
	private String netContent;
	private String stockQty;
	private String rackName;
	private String holdQty;
	private int calculateLooseHoldQty;
	private String groupName;
	private int looseUnitId;
	private String looseUnitName;
	private int scheduleId;
	private String scheduleName;
	private int calculateLooseQty;
	private String itemUniqueKey;
	private int expiryStatusMode;
	private String expiryStatus;
	private double vatPer;
	private double taxPer;
	private String itemName;
	private String sku;
	private String hsnCode;
	private int taxId;
	private String taxName;
	private double taxPercentage;
	private String taxMode;
	private int isGroupTax;
	private double discount;
	private int isDiscount;
	private double maxDiscountLimit;
	private double purchaseCostPerUnit;
	private String note;
	private double saleRate;
	private double purchaseRate;
	private int claculateLooseReorderLevelQty;
	
	 private String purInvNo;    //22-08-2019
	 private Date purInvDate;

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
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
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
	 * @return the looseQty
	 */
	public double getLooseQty() {
		return looseQty;
	}

	/**
	 * @param looseQty
	 *            the looseQty to set
	 */
	public void setLooseQty(double looseQty) {
		this.looseQty = looseQty;
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
	 * @return the contentName
	 */
	public String getContentName() {
		return contentName;
	}

	/**
	 * @param contentName
	 *            the contentName to set
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName
	 *            the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
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
	 * @return the holdQty
	 */
	public String getHoldQty() {
		return holdQty;
	}

	/**
	 * @param holdQty
	 *            the holdQty to set
	 */
	public void setHoldQty(String holdQty) {
		this.holdQty = holdQty;
	}

	/**
	 * @return the calculateLooseHoldQty
	 */
	public int getCalculateLooseHoldQty() {
		return calculateLooseHoldQty;
	}

	/**
	 * @param calculateLooseHoldQty
	 *            the calculateLooseHoldQty to set
	 */
	public void setCalculateLooseHoldQty(int calculateLooseHoldQty) {
		this.calculateLooseHoldQty = calculateLooseHoldQty;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the looseUnitId
	 */
	public int getLooseUnitId() {
		return looseUnitId;
	}

	/**
	 * @param looseUnitId
	 *            the looseUnitId to set
	 */
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}

	/**
	 * @return the looseUnitName
	 */
	public String getLooseUnitName() {
		return looseUnitName;
	}

	/**
	 * @param looseUnitName
	 *            the looseUnitName to set
	 */
	public void setLooseUnitName(String looseUnitName) {
		this.looseUnitName = looseUnitName;
	}

	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId
	 *            the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * @param scheduleName
	 *            the scheduleName to set
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
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
	 * @return the expiryStatusMode
	 */
	public int getExpiryStatusMode() {
		return expiryStatusMode;
	}

	/**
	 * @param expiryStatusMode
	 *            the expiryStatusMode to set
	 */
	public void setExpiryStatusMode(int expiryStatusMode) {
		this.expiryStatusMode = expiryStatusMode;
	}

	/**
	 * @return the expiryStatus
	 */
	public String getExpiryStatus() {
		return expiryStatus;
	}

	/**
	 * @param expiryStatus
	 *            the expiryStatus to set
	 */
	public void setExpiryStatus(String expiryStatus) {
		this.expiryStatus = expiryStatus;
	}

	/**
	 * @return the vatPer
	 */
	public double getVatPer() {
		return vatPer;
	}

	/**
	 * @param vatPer
	 *            the vatPer to set
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
	 * @param taxPer
	 *            the taxPer to set
	 */
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
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
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
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
	 * @param hsnCode
	 *            the hsnCode to set
	 */
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 *            the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the taxName
	 */
	public String getTaxName() {
		return taxName;
	}

	/**
	 * @param taxName
	 *            the taxName to set
	 */
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	/**
	 * @return the taxPercentage
	 */
	public double getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage
	 *            the taxPercentage to set
	 */
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	/**
	 * @return the taxMode
	 */
	public String getTaxMode() {
		return taxMode;
	}

	/**
	 * @param taxMode
	 *            the taxMode to set
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
	 * @param isGroupTax
	 *            the isGroupTax to set
	 */
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the isDiscount
	 */
	public int getIsDiscount() {
		return isDiscount;
	}

	/**
	 * @param isDiscount
	 *            the isDiscount to set
	 */
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}

	/**
	 * @return the maxDiscountLimit
	 */
	public double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}

	/**
	 * @param maxDiscountLimit
	 *            the maxDiscountLimit to set
	 */
	public void setMaxDiscountLimit(double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}

	/**
	 * @return the purchaseCostPerUnit
	 */
	public double getPurchaseCostPerUnit() {
		return purchaseCostPerUnit;
	}

	/**
	 * @param purchaseCostPerUnit
	 *            the purchaseCostPerUnit to set
	 */
	public void setPurchaseCostPerUnit(double purchaseCostPerUnit) {
		this.purchaseCostPerUnit = purchaseCostPerUnit;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the saleRate
	 */
	public double getSaleRate() {
		return saleRate;
	}

	/**
	 * @param saleRate
	 *            the saleRate to set
	 */
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	/**
	 * @return the purchaseRate
	 */
	public double getPurchaseRate() {
		return purchaseRate;
	}

	/**
	 * @param purchaseRate
	 *            the purchaseRate to set
	 */
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	/**
	 * @return the claculateLooseReorderLevelQty
	 */
	public int getClaculateLooseReorderLevelQty() {
		return claculateLooseReorderLevelQty;
	}

	/**
	 * @param claculateLooseReorderLevelQty
	 *            the claculateLooseReorderLevelQty to set
	 */
	public void setClaculateLooseReorderLevelQty(int claculateLooseReorderLevelQty) {
		this.claculateLooseReorderLevelQty = claculateLooseReorderLevelQty;
	}

	
	/**
	 * @return the purInvNo
	 */
	public String getPurInvNo() {
		return purInvNo;
	}

	/**
	 * @param purInvNo the purInvNo to set
	 */
	public void setPurInvNo(String purInvNo) {
		this.purInvNo = purInvNo;
	}

	/**
	 * @return the purInvDate
	 */
	public Date getPurInvDate() {
		return purInvDate;
	}

	/**
	 * @param purInvDate the purInvDate to set
	 */
	public void setPurInvDate(Date purInvDate) {
		this.purInvDate = purInvDate;
	}

	@Override
	public String toString() {
		return "ItemCurrentStockDTO [itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate
				+ ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId + ", conversion=" + conversion
				+ ", mrp=" + mrp + ", packQty=" + packQty + ", looseQty=" + looseQty + ", packUnitName=" + packUnitName
				+ ", contentName=" + contentName + ", manufacturerName=" + manufacturerName + ", netContent="
				+ netContent + ", stockQty=" + stockQty + ", rackName=" + rackName + ", holdQty=" + holdQty
				+ ", calculateLooseHoldQty=" + calculateLooseHoldQty + ", groupName=" + groupName + ", looseUnitId="
				+ looseUnitId + ", looseUnitName=" + looseUnitName + ", scheduleId=" + scheduleId + ", scheduleName="
				+ scheduleName + ", calculateLooseQty=" + calculateLooseQty + ", itemUniqueKey=" + itemUniqueKey
				+ ", expiryStatusMode=" + expiryStatusMode + ", expiryStatus=" + expiryStatus + ", vatPer=" + vatPer
				+ ", taxPer=" + taxPer + ", itemName=" + itemName + ", sku=" + sku + ", hsnCode=" + hsnCode + ", taxId="
				+ taxId + ", taxName=" + taxName + ", taxPercentage=" + taxPercentage + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", discount=" + discount + ", isDiscount=" + isDiscount
				+ ", maxDiscountLimit=" + maxDiscountLimit + ", purchaseCostPerUnit=" + purchaseCostPerUnit + ", note="
				+ note + ", saleRate=" + saleRate + ", purchaseRate=" + purchaseRate
				+ ", claculateLooseReorderLevelQty=" + claculateLooseReorderLevelQty + ", purInvNo=" + purInvNo
				+ ", purInvDate=" + purInvDate + "]";
	}

}
