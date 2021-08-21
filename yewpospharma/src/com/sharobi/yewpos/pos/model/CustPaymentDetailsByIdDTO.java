package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

public class CustPaymentDetailsByIdDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int billNo;
	
	private String invNo;

	private String invDate;
	
	private double netAmount;
	
	private double creditAmount;

	private double dueAmount;

	private String invFrom;
	
	private String invid;
	
	private double outstandingAmount;
	private String invFromType;
	

	public double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
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

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getInvFrom() {
		return invFrom;
	}

	public void setInvFrom(String invFrom) {
		this.invFrom = invFrom;
	}

	public String getInvid() {
		return invid;
	}

	public void setInvid(String invid) {
		this.invid = invid;
	}
	
	public String getInvFromType() {
		return invFromType;
	}

	public void setInvFromType(String invFromType) {
		this.invFromType = invFromType;
	}

	@Override
	public String toString() {
		return "CustPaymentDetailsByIdDTO [billNo=" + billNo + ", invNo=" + invNo + ", invDate=" + invDate + ", netAmount=" + netAmount + ", creditAmount=" + creditAmount + ", dueAmount=" + dueAmount + ", invFrom=" + invFrom + ", invid=" + invid + ", outstandingAmount=" + outstandingAmount + ", invFromType=" + invFromType + "]";
	}

}
