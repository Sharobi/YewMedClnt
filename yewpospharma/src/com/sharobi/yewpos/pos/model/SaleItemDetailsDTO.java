/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 
 */
public class SaleItemDetailsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int saleId;
    private int saleDetailsId;
    private String invNo;
    private String invDate;
    private int itemId;
    private String itemName;
    private String batchNo;
    private String expiryDate;
    private String expiryDateFormat;
    private String itemUniqueKey;
    private int packUnitId;
    private String packUnitName;
    private int packQty;
    private int conversion;
    private int looseQty;
    private double mrp;
    private double rate;
    private double saleRate;
    private double mrpPerUnit;
    private double ratePerUnit;
    private double amount;
    private double discPer;
    private double disc;
    private double totAmount;
    private int taxId;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private String sku;
    private String hsnCode;
    private double discount;
    private int isDiscount;
    private double maxDiscountLimit;
    private double purchaseCostPerUnit;
    private double itemTotalMrp;
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private int doctorId;
    private String doctorName;
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getSaleDetailsId() {
		return saleDetailsId;
	}
	public void setSaleDetailsId(int saleDetailsId) {
		this.saleDetailsId = saleDetailsId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}
	public int getPackUnitId() {
		return packUnitId;
	}
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}
	public String getPackUnitName() {
		return packUnitName;
	}
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
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
	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}
	public double getMrpPerUnit() {
		return mrpPerUnit;
	}
	public void setMrpPerUnit(double mrpPerUnit) {
		this.mrpPerUnit = mrpPerUnit;
	}
	public double getRatePerUnit() {
		return ratePerUnit;
	}
	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDiscPer() {
		return discPer;
	}
	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}
	public double getDisc() {
		return disc;
	}
	public void setDisc(double disc) {
		this.disc = disc;
	}
	public double getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public int getIsGroupTax() {
		return isGroupTax;
	}
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}
	public double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}
	public void setMaxDiscountLimit(double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}
	public double getPurchaseCostPerUnit() {
		return purchaseCostPerUnit;
	}
	public void setPurchaseCostPerUnit(double purchaseCostPerUnit) {
		this.purchaseCostPerUnit = purchaseCostPerUnit;
	}
	public double getItemTotalMrp() {
		return itemTotalMrp;
	}
	public void setItemTotalMrp(double itemTotalMrp) {
		this.itemTotalMrp = itemTotalMrp;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@Override
	public String toString() {
		return "SaleItemDetailsDTO [saleId=" + saleId + ", saleDetailsId=" + saleDetailsId + ", invNo=" + invNo + ", invDate=" + invDate + ", itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", itemUniqueKey=" + itemUniqueKey + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty + ", mrp=" + mrp + ", rate=" + rate + ", saleRate=" + saleRate + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", amount=" + amount + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", discount=" + discount + ", isDiscount=" + isDiscount + ", maxDiscountLimit=" + maxDiscountLimit
				+ ", purchaseCostPerUnit=" + purchaseCostPerUnit + ", itemTotalMrp=" + itemTotalMrp + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerAddress=" + customerAddress + ", doctorId=" + doctorId + ", doctorName=" + doctorName + "]";
	}
    
}
