/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PurchaseOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String invNo;

    private String invDate;

    private String dueDate;

    private String invType;

    private int distributorId;

    private double distributorDiscPer;

    private double grossAmount;

    private double discAmount;

    private double adjAmount;

    private double taxAmount;

    private double roundoff;

    private double netAmount;

    private double totalMrp;

    private String remarks;

    private int isCanceled;

    private int isDeleted;

    private int status;

    private int finyrId;
    
    private String finyrCode;

    private int storeId;

    private int companyId;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;
    
    private String lang;
    
    private List<PurchaseOrderDetails> purchaseOrderDetails;
    
    private int isPosted;
    
    
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

	/**
	 * @return the finyrCode
	 */
	public String getFinyrCode() {
		return finyrCode;
	}

	/**
	 * @param finyrCode the finyrCode to set
	 */
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the isCanceled
	 */
	public int getIsCanceled() {
		return isCanceled;
	}

	/**
	 * @param isCanceled the isCanceled to set
	 */
	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
	}

	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}

	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the purchaseOrderDetails
	 */
	public List<PurchaseOrderDetails> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	/**
	 * @param purchaseOrderDetails the purchaseOrderDetails to set
	 */
	public void setPurchaseOrderDetails(List<PurchaseOrderDetails> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", dueDate=" + dueDate
				+ ", invType=" + invType + ", distributorId=" + distributorId + ", distributorDiscPer="
				+ distributorDiscPer + ", grossAmount=" + grossAmount + ", discAmount=" + discAmount + ", adjAmount="
				+ adjAmount + ", taxAmount=" + taxAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount
				+ ", totalMrp=" + totalMrp + ", remarks=" + remarks + ", isCanceled=" + isCanceled + ", isDeleted="
				+ isDeleted + ", status=" + status + ", finyrId=" + finyrId + ", finyrCode=" + finyrCode + ", storeId="
				+ storeId + ", companyId=" + companyId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang
				+ ", purchaseOrderDetails=" + purchaseOrderDetails + ", isPosted=" + isPosted + "]";
	}

	
}
