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
public class PurchaseReturnDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int purchaseReturnId;
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
	private double lotAdjAmount;
	private double roundoff;
	private double netAmount;
	private double totalMrp;
	private String remarks;
	private int distributorId;
	private String distributorName;
	private int isPosted;
	private double preAdjAmount; 
	
	public double getPreAdjAmount() {
		return preAdjAmount;
	}

	public void setPreAdjAmount(double preAdjAmount) {
		this.preAdjAmount = preAdjAmount;
	}

	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
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

	public double getLotAdjAmount() {
		return lotAdjAmount;
	}

	public void setLotAdjAmount(double lotAdjAmount) {
		this.lotAdjAmount = lotAdjAmount;
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

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	@Override
	public String toString() {
		return "PurchaseReturnDTO [purchaseReturnId=" + purchaseReturnId + ", invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode + ", invModeName=" + invModeName + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount + ", adjAmount=" + adjAmount + ", lotAdjAmount=" + lotAdjAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp=" + totalMrp + ", remarks=" + remarks + ", distributorId=" + distributorId + ", distributorName=" + distributorName + ", isPosted=" + isPosted + "]";
	}

}
