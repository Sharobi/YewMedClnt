/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *
 * 
 */
public class SaleReturnDetailsDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int itemId;
    private String itemName;
    private String batchNo;
    private String expiryDate;
    private String expiryDateFormat;
    private int packUnitId;
    private String packUnitName;     
    private int packQty;
    private int conversion;
    private int looseUnitId;
    private String looseUnitName;
    private int looseQty;
    private double mrp;
    private double rate;
    private double amount;
    private double edPer;
    private double ed;
    private double taxPer;
    private double tax;
    private double vatPer;
    private double vat;
    private double discPer;
    private double disc;
    private double totAmount;
    private int saleId;
    private String saleInvNo;
    private double mrpPerUnit;
    private double ratePerUnit;
    private int hidePackQty;
    private int hideLooseQty;
    private String manufacturerName;
    private String manufacturerCode;
    private String contentName;
    private int remainingPackQty;
    private int remainingLooseQty;
    private String itemUniqueKey;
	private double specialDiscPer;
	private double specialDiscAmount;
	private int taxId;
    private String taxName;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private String sku;
    private String hsnCode;
    private int reasonId;
    private String reasonTypeName;
	
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
	public int getLooseUnitId() {
		return looseUnitId;
	}
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}
	public String getLooseUnitName() {
		return looseUnitName;
	}
	public void setLooseUnitName(String looseUnitName) {
		this.looseUnitName = looseUnitName;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getEdPer() {
		return edPer;
	}
	public void setEdPer(double edPer) {
		this.edPer = edPer;
	}
	public double getEd() {
		return ed;
	}
	public void setEd(double ed) {
		this.ed = ed;
	}
	public double getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getVatPer() {
		return vatPer;
	}
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
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
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getSaleInvNo() {
		return saleInvNo;
	}
	public void setSaleInvNo(String saleInvNo) {
		this.saleInvNo = saleInvNo;
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
	public int getHidePackQty() {
		return hidePackQty;
	}
	public void setHidePackQty(int hidePackQty) {
		this.hidePackQty = hidePackQty;
	}
	public int getHideLooseQty() {
		return hideLooseQty;
	}
	public void setHideLooseQty(int hideLooseQty) {
		this.hideLooseQty = hideLooseQty;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public int getRemainingPackQty() {
		return remainingPackQty;
	}
	public void setRemainingPackQty(int remainingPackQty) {
		this.remainingPackQty = remainingPackQty;
	}
	public int getRemainingLooseQty() {
		return remainingLooseQty;
	}
	public void setRemainingLooseQty(int remainingLooseQty) {
		this.remainingLooseQty = remainingLooseQty;
	}
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}
	
	public double getSpecialDiscPer() {
		return specialDiscPer;
	}
	public void setSpecialDiscPer(double specialDiscPer) {
		this.specialDiscPer = specialDiscPer;
	}
	public double getSpecialDiscAmount() {
		return specialDiscAmount;
	}
	public void setSpecialDiscAmount(double specialDiscAmount) {
		this.specialDiscAmount = specialDiscAmount;
	}
	
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
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
	public int getReasonId() {
		return reasonId;
	}
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	public String getReasonTypeName() {
		return reasonTypeName;
	}
	public void setReasonTypeName(String reasonTypeName) {
		this.reasonTypeName = reasonTypeName;
	}
	@Override
	public String toString() {
		return "SaleReturnDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion + ", looseUnitId=" + looseUnitId + ", looseUnitName=" + looseUnitName + ", looseQty=" + looseQty + ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", saleId=" + saleId + ", saleInvNo=" + saleInvNo + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", hidePackQty=" + hidePackQty + ", hideLooseQty=" + hideLooseQty + ", manufacturerName=" + manufacturerName + ", manufacturerCode=" + manufacturerCode + ", contentName=" + contentName + ", remainingPackQty=" + remainingPackQty
				+ ", remainingLooseQty=" + remainingLooseQty + ", itemUniqueKey=" + itemUniqueKey + ", specialDiscPer=" + specialDiscPer + ", specialDiscAmount=" + specialDiscAmount + ", taxId=" + taxId + ", taxName=" + taxName + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", reasonId=" + reasonId + ", reasonTypeName=" + reasonTypeName + "]";
	}

}
