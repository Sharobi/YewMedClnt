package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Expiry implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private int id;

    private String invNo;
   
    private String invDate;
    
    private String fromDate;
    
    private String toDate;
    
    private String remarks;
    
    private int isPosted;
   
    private int finyrId;
    
    private String finyrCode;

    private int companyId;
    
    private int storeId;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private String lang;
    
    private List<ExpiryDetails> expiryDetails;
    

	private double cr_amount;
	private double dr_amount;
	private int cr_account_id;
	private int dr_account_id;
	private String qs;
    
    
    
	/**
	 * @return the cr_amount
	 */
	public double getCr_amount() {
		return cr_amount;
	}

	/**
	 * @param cr_amount the cr_amount to set
	 */
	public void setCr_amount(double cr_amount) {
		this.cr_amount = cr_amount;
	}

	/**
	 * @return the dr_amount
	 */
	public double getDr_amount() {
		return dr_amount;
	}

	/**
	 * @param dr_amount the dr_amount to set
	 */
	public void setDr_amount(double dr_amount) {
		this.dr_amount = dr_amount;
	}

	/**
	 * @return the cr_account_id
	 */
	public int getCr_account_id() {
		return cr_account_id;
	}

	/**
	 * @param cr_account_id the cr_account_id to set
	 */
	public void setCr_account_id(int cr_account_id) {
		this.cr_account_id = cr_account_id;
	}

	/**
	 * @return the dr_account_id
	 */
	public int getDr_account_id() {
		return dr_account_id;
	}

	/**
	 * @param dr_account_id the dr_account_id to set
	 */
	public void setDr_account_id(int dr_account_id) {
		this.dr_account_id = dr_account_id;
	}

	/**
	 * @return the qs
	 */
	public String getQs() {
		return qs;
	}

	/**
	 * @param qs the qs to set
	 */
	public void setQs(String qs) {
		this.qs = qs;
	}

	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	public List<ExpiryDetails> getExpiryDetails() {
		return expiryDetails;
	}

	public void setExpiryDetails(List<ExpiryDetails> expiryDetails) {
		this.expiryDetails = expiryDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Expiry [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", remarks=" + remarks + ", isPosted=" + isPosted + ", finyrId=" + finyrId + ", finyrCode="
				+ finyrCode + ", companyId=" + companyId + ", storeId=" + storeId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", expiryDetails=" + expiryDetails + ", cr_amount=" + cr_amount + ", dr_amount="
				+ dr_amount + ", cr_account_id=" + cr_account_id + ", dr_account_id=" + dr_account_id + ", qs=" + qs
				+ "]";
	}

 

}
