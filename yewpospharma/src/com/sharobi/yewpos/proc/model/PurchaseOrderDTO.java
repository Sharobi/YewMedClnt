/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrderDTO  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int purchaseOrderId;

    private String invNo;

    private String invDate;

    private String dueDate;

    private String invType;

    private int distributorId;

    private String distributorName; 

    private double distributorDiscPer;

    private double grossAmount;

    private double discAmount;

    private double adjAmount;

    private double taxAmount;

    private double roundoff;

    private double netAmount;

    private double totalMrp;

    private String remarks;

    private int status;

    private int isPosted;

	/**
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo the invNo to set
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
	 * @param invDate the invDate to set
	 */
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the invType
	 */
	public String getInvType() {
		return invType;
	}

	/**
	 * @param invType the invType to set
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}

	/**
	 * @return the distributorId
	 */
	public int getDistributorId() {
		return distributorId;
	}

	/**
	 * @param distributorId the distributorId to set
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
	 * @param distributorName the distributorName to set
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	/**
	 * @return the distributorDiscPer
	 */
	public double getDistributorDiscPer() {
		return distributorDiscPer;
	}

	/**
	 * @param distributorDiscPer the distributorDiscPer to set
	 */
	public void setDistributorDiscPer(double distributorDiscPer) {
		this.distributorDiscPer = distributorDiscPer;
	}

	/**
	 * @return the grossAmount
	 */
	public double getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount the grossAmount to set
	 */
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the discAmount
	 */
	public double getDiscAmount() {
		return discAmount;
	}

	/**
	 * @param discAmount the discAmount to set
	 */
	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}

	/**
	 * @return the adjAmount
	 */
	public double getAdjAmount() {
		return adjAmount;
	}

	/**
	 * @param adjAmount the adjAmount to set
	 */
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}

	/**
	 * @return the taxAmount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the roundoff
	 */
	public double getRoundoff() {
		return roundoff;
	}

	/**
	 * @param roundoff the roundoff to set
	 */
	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	/**
	 * @return the netAmount
	 */
	public double getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the totalMrp
	 */
	public double getTotalMrp() {
		return totalMrp;
	}

	/**
	 * @param totalMrp the totalMrp to set
	 */
	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the isPosted
	 */
	public int getIsPosted() {
		return isPosted;
	}

	/**
	 * @param isPosted the isPosted to set
	 */
	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseOrderDTO [purchaseOrderId=" + purchaseOrderId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", dueDate=" + dueDate + ", invType=" + invType + ", distributorId=" + distributorId
				+ ", distributorName=" + distributorName + ", distributorDiscPer=" + distributorDiscPer
				+ ", grossAmount=" + grossAmount + ", discAmount=" + discAmount + ", adjAmount=" + adjAmount
				+ ", taxAmount=" + taxAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp="
				+ totalMrp + ", remarks=" + remarks + ", status=" + status + ", isPosted=" + isPosted + "]";
	} 


}
