
package com.sharobi.yewpos.pos.model;

import java.util.Date;

/**
 * @author Manodip Jana
 *
 * 
 */
public class SaleDetailsForReturnDTO {
	private int saleId;
    private String saleInvNo;
    private Date saleInvDate;
    private String saleInvTime;
    private String saleInvModeName;
    private double grossAmount;
    private double edAmount;
    private double discAmount;
    private double vatAmount;
    private double taxAmount;
    private double adjAmount;
    private double roundOff;
    private double netAmount;
    private double cashAmount;
    private double cardAmount;
    private double creditAmount;
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private int doctorId;
    private String doctorName;
    private int itemId;
    private String itemName;
    private String batchNo;
    private Date expiryDate;
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
    private double mrpPerUnit;
    private double ratePerUnit;
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
    private int calculateLooseQty;
    private int prevReturnPackQty;
    private int prevReturnLooseQty;
    private String manufacturerName;
    private String contentName;
    private String itemUniqueKey;
	private double specialDiscPer;
	private double specialDiscAmount;
    
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
	public Date getSaleInvDate() {
		return saleInvDate;
	}
	public void setSaleInvDate(Date saleInvDate) {
		this.saleInvDate = saleInvDate;
	}
	public String getSaleInvTime() {
		return saleInvTime;
	}
	public void setSaleInvTime(String saleInvTime) {
		this.saleInvTime = saleInvTime;
	}
	public String getSaleInvModeName() {
		return saleInvModeName;
	}
	public void setSaleInvModeName(String saleInvModeName) {
		this.saleInvModeName = saleInvModeName;
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
	public double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
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
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
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
	@Override
	public String toString() {
		return "SaleDetailsForReturnDTO [saleId=" + saleId + ", saleInvNo=" + saleInvNo + ", saleInvDate=" + saleInvDate + ", saleInvTime=" + saleInvTime + ", saleInvModeName=" + saleInvModeName + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount + ", adjAmount=" + adjAmount + ", roundOff=" + roundOff + ", netAmount=" + netAmount + ", cashAmount=" + cashAmount + ", cardAmount=" + cardAmount + ", creditAmount=" + creditAmount + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerAddress=" + customerAddress + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseUnitId=" + looseUnitId + ", looseUnitName=" + looseUnitName + ", looseQty=" + looseQty + ", mrp=" + mrp + ", rate=" + rate + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", calculateLooseQty=" + calculateLooseQty + ", prevReturnPackQty=" + prevReturnPackQty + ", prevReturnLooseQty=" + prevReturnLooseQty + ", manufacturerName=" + manufacturerName + ", contentName=" + contentName + ", itemUniqueKey=" + itemUniqueKey + ", specialDiscPer=" + specialDiscPer + ", specialDiscAmount=" + specialDiscAmount + "]";
	}
    
}
