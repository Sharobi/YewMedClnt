/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 *
 */
public class PurchaseDetailsForReturnDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int purchaseId;
    private String purchaseInvNo;
    private String purchaseInvDate;
    private String purchaseInvModeName;
    private double grossAmount;
    private double edAmount;
    private double discAmount;
    private double cst;
    private double vatDiff;
    private double vatAmount;
    private double taxAmount;
    private double adjAmount;
    private double lotAdjAmount;
    private double specDiscPer;
    private double specDiscAmount;
    private double roundOff;
    private double netAmount;
    private double totalMrp;
    private int distributorId;
    private String distributorName;
    private int itemId;
    private String itemName;
    private String batchNo;
    private String expiryDate;
    private String expiryDateFormat;
    private int packUnitId;
    private String packUnitName;
    private int packQty;
    private int conversion;
    private int freeQty;
    private double mrp;
    private double rate;
    private double amount;
    private int looseQty;
    private double edPer;
    private double ed;
    private double taxPer;
    private double tax;
    private double vatPer;
    private double vat;
    private double discPer;
    private double disc;
    private double totAmount;
    private int calculateLooseQty;
    private int prevReturnPackQty;
    private int prevReturnLooseQty;
    private String manufacturerName;
    private String contentName;
    private String manufacturerCode;
    private int hidePackQty;
    private int hideLooseQty;
    private String billNo;
    private int grpId;
    private String grpName;
    private int scheduleId;
    private String scheduleName;
    private int manufacturerId;
    private String itemUniqueKey;
    private String stockQty;
    
	public String getStockQty() {
		return stockQty;
	}
	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseInvNo() {
		return purchaseInvNo;
	}
	public void setPurchaseInvNo(String purchaseInvNo) {
		this.purchaseInvNo = purchaseInvNo;
	}
	public String getPurchaseInvDate() {
		return purchaseInvDate;
	}
	public void setPurchaseInvDate(String purchaseInvDate) {
		this.purchaseInvDate = purchaseInvDate;
	}
	public String getPurchaseInvModeName() {
		return purchaseInvModeName;
	}
	public void setPurchaseInvModeName(String purchaseInvModeName) {
		this.purchaseInvModeName = purchaseInvModeName;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public double getEdAmount() {
		return edAmount;
	}
	public void setEdAmount(double edAmount) {
		this.edAmount = edAmount;
	}
	public double getDiscAmount() {
		return discAmount;
	}
	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}
	public double getCst() {
		return cst;
	}
	public void setCst(double cst) {
		this.cst = cst;
	}
	public double getVatDiff() {
		return vatDiff;
	}
	public void setVatDiff(double vatDiff) {
		this.vatDiff = vatDiff;
	}
	public double getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}
	public double getLotAdjAmount() {
		return lotAdjAmount;
	}
	public void setLotAdjAmount(double lotAdjAmount) {
		this.lotAdjAmount = lotAdjAmount;
	}
	public double getSpecDiscPer() {
		return specDiscPer;
	}
	public void setSpecDiscPer(double specDiscPer) {
		this.specDiscPer = specDiscPer;
	}
	public double getSpecDiscAmount() {
		return specDiscAmount;
	}
	public void setSpecDiscAmount(double specDiscAmount) {
		this.specDiscAmount = specDiscAmount;
	}
	public double getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(double roundOff) {
		this.roundOff = roundOff;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getTotalMrp() {
		return totalMrp;
	}
	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}
	public int getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
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
	public int getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(int freeQty) {
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getLooseQty() {
		return looseQty;
	}
	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
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
	public int getCalculateLooseQty() {
		return calculateLooseQty;
	}
	public void setCalculateLooseQty(int calculateLooseQty) {
		this.calculateLooseQty = calculateLooseQty;
	}
	public int getPrevReturnPackQty() {
		return prevReturnPackQty;
	}
	public void setPrevReturnPackQty(int prevReturnPackQty) {
		this.prevReturnPackQty = prevReturnPackQty;
	}
	public int getPrevReturnLooseQty() {
		return prevReturnLooseQty;
	}
	public void setPrevReturnLooseQty(int prevReturnLooseQty) {
		this.prevReturnLooseQty = prevReturnLooseQty;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public int getGrpId() {
		return grpId;
	}
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public int getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}
	@Override
	public String toString() {
		return "PurchaseDetailsForReturnDTO [purchaseId=" + purchaseId + ", purchaseInvNo=" + purchaseInvNo
				+ ", purchaseInvDate=" + purchaseInvDate + ", purchaseInvModeName=" + purchaseInvModeName
				+ ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", cst="
				+ cst + ", vatDiff=" + vatDiff + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount
				+ ", adjAmount=" + adjAmount + ", lotAdjAmount=" + lotAdjAmount + ", specDiscPer=" + specDiscPer
				+ ", specDiscAmount=" + specDiscAmount + ", roundOff=" + roundOff + ", netAmount=" + netAmount
				+ ", totalMrp=" + totalMrp + ", distributorId=" + distributorId + ", distributorName=" + distributorName
				+ ", itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo + ", expiryDate="
				+ expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", looseQty="
				+ looseQty + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer="
				+ vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount
				+ ", calculateLooseQty=" + calculateLooseQty + ", prevReturnPackQty=" + prevReturnPackQty
				+ ", prevReturnLooseQty=" + prevReturnLooseQty + ", manufacturerName=" + manufacturerName
				+ ", contentName=" + contentName + ", manufacturerCode=" + manufacturerCode + ", hidePackQty="
				+ hidePackQty + ", hideLooseQty=" + hideLooseQty + ", billNo=" + billNo + ", grpId=" + grpId
				+ ", grpName=" + grpName + ", scheduleId=" + scheduleId + ", scheduleName=" + scheduleName
				+ ", manufacturerId=" + manufacturerId + ", itemUniqueKey=" + itemUniqueKey + ", stockQty=" + stockQty
				+ "]";
	}
	
    
}
