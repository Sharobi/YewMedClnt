package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.List;

public class CustomerPayment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int invNo;	

	private String invDate;

	private int invMode;
	
	private int customerId;
	
	private double paybleAmount;

	private double outstandingAmount;

	private double netAmount;

	private double roundoff;

	private double payAmount;

	private double remainingAmount;
	
	private String cardNo;

	private String chequeNo;

	private String chequeDate;
	
	private String bankName;
	
	private String remarks;
	
	private int isDeleted;
	
	private int isPosted;
	
	private int pstId;

	private String pstNo;

	private int finyrId;

	private int storeId;

	private int companyId;

	private int createdBy;

	private String createdDate;

	private int updatedBy;

	private String updatedDate;

	private String lang;
	
	private List<CustomerPaymentDetails> customerPaymentDetails;
	
	private String finyrCode;
	
	
	/*
	* add on Mar 2, 2018
	*/
	
	private double cr_amount;
	private double dr_amount;
	private int cr_account_id;
	private int dr_account_id;
	private String qs;
	 private int is_account;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvNo() {
		return invNo;
	}

	public void setInvNo(int invNo) {
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getPaybleAmount() {
		return paybleAmount;
	}

	public void setPaybleAmount(double paybleAmount) {
		this.paybleAmount = paybleAmount;
	}

	public double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public int getPstId() {
		return pstId;
	}

	public void setPstId(int pstId) {
		this.pstId = pstId;
	}

	public String getPstNo() {
		return pstNo;
	}

	public void setPstNo(String pstNo) {
		this.pstNo = pstNo;
	}

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<CustomerPaymentDetails> getCustomerPaymentDetails() {
		return customerPaymentDetails;
	}

	public void setCustomerPaymentDetails(List<CustomerPaymentDetails> customerPaymentDetails) {
		this.customerPaymentDetails = customerPaymentDetails;
	}

	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

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
	 * @return the is_account
	 */
	public int getIs_account() {
		return is_account;
	}

	/**
	 * @param is_account the is_account to set
	 */
	public void setIs_account(int is_account) {
		this.is_account = is_account;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerPayment [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode
				+ ", customerId=" + customerId + ", paybleAmount=" + paybleAmount + ", outstandingAmount="
				+ outstandingAmount + ", netAmount=" + netAmount + ", roundoff=" + roundoff + ", payAmount=" + payAmount
				+ ", remainingAmount=" + remainingAmount + ", cardNo=" + cardNo + ", chequeNo=" + chequeNo
				+ ", chequeDate=" + chequeDate + ", bankName=" + bankName + ", remarks=" + remarks + ", isDeleted="
				+ isDeleted + ", isPosted=" + isPosted + ", pstId=" + pstId + ", pstNo=" + pstNo + ", finyrId="
				+ finyrId + ", storeId=" + storeId + ", companyId=" + companyId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", customerPaymentDetails=" + customerPaymentDetails + ", finyrCode=" + finyrCode
				+ ", cr_amount=" + cr_amount + ", dr_amount=" + dr_amount + ", cr_account_id=" + cr_account_id
				+ ", dr_account_id=" + dr_account_id + ", qs=" + qs + ", is_account=" + is_account + "]";
	}

	 

 

	 

}
