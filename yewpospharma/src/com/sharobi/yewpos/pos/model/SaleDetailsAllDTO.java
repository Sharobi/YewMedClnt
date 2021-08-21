package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip
 */
public class SaleDetailsAllDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int saleId;
	private String invNo;
	private String invDate;
	private int invMode;
	private String invModeName;
	private double grossAmount;
	private double edAmount;
	private double discAmount;
	private double vatAmount;
	private double taxAmount;
	private double adjAmount;
	private double roundoff;
	private double netAmount;
	private double cashAmount;
	private double cardAmount;
	private double creditAmount;
	private String cardFourDigit;
	private String cardExpDate;
	private double tenderAmount;
	private int printCount;
	private int holdFlag;
	private String remarks;
	private int customerId;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private int doctorId;
	private String doctorName;
	private int isPosted;
	private double customerDiscPer;
    private double specialDiscPer;
    private double specialDiscAmount;
    private double totalMrp;

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
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

	public int getInvMode() {
		return invMode;
	}

	public void setInvMode(int invMode) {
		this.invMode = invMode;
	}

	public String getInvModeName() {
		return invModeName;
	}

	public void setInvModeName(String invModeName) {
		this.invModeName = invModeName;
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

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
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

	public String getCardFourDigit() {
		return cardFourDigit;
	}

	public void setCardFourDigit(String cardFourDigit) {
		this.cardFourDigit = cardFourDigit;
	}

	public String getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public double getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(double tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public int getPrintCount() {
		return printCount;
	}

	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}

	public int getHoldFlag() {
		return holdFlag;
	}

	public void setHoldFlag(int holdFlag) {
		this.holdFlag = holdFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public double getCustomerDiscPer() {
		return customerDiscPer;
	}

	public void setCustomerDiscPer(double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
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

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	@Override
	public String toString() {
		return "SaleDetailsAllDTO [saleId=" + saleId + ", invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode + ", invModeName=" + invModeName + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount + ", adjAmount=" + adjAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", cashAmount=" + cashAmount + ", cardAmount=" + cardAmount + ", creditAmount=" + creditAmount + ", cardFourDigit=" + cardFourDigit + ", cardExpDate=" + cardExpDate + ", tenderAmount=" + tenderAmount + ", printCount=" + printCount + ", holdFlag=" + holdFlag + ", remarks=" + remarks + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerAddress=" + customerAddress + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", isPosted=" + isPosted + ", customerDiscPer=" + customerDiscPer + ", specialDiscPer=" + specialDiscPer
				+ ", specialDiscAmount=" + specialDiscAmount + ", totalMrp=" + totalMrp + "]";
	}

}
