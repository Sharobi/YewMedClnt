package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

public class SaleReturnDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String invNo;

    private String invDate;

    private int invMode;

    private double grossAmount;

    private double edAmount;

    private double discAmount;

    private double vatAmount;

    private double taxAmount;

    private double adjAmount;

    private double roundoff;

    private double netAmount;

    private String remarks;

    private int customerId;

    private String customerName;

    private String customerPhone;

    private String customerAddress;

    private int doctorId;

    private String doctorName;

    private int isPosted;

    private int saleReturnId;
    
    private double preAdjAmount;
	private double specialDiscPer;
	private double specialDiscAmount;

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

	public int getSaleReturnId() {
		return saleReturnId;
	}

	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	
	public double getPreAdjAmount() {
		return preAdjAmount;
	}

	public void setPreAdjAmount(double preAdjAmount) {
		this.preAdjAmount = preAdjAmount;
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
		return "SaleReturnDTO [invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount + ", adjAmount=" + adjAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", remarks=" + remarks + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerAddress=" + customerAddress + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", isPosted=" + isPosted + ", saleReturnId=" + saleReturnId + ", preAdjAmount=" + preAdjAmount + ", specialDiscPer=" + specialDiscPer + ", specialDiscAmount=" + specialDiscAmount + "]";
	}
}
