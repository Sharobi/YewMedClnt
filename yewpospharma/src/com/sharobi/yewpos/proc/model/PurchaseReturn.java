/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class PurchaseReturn implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String invNo;
	private String invDate;
	private int purchaseId;
	private int invMode;
	private int invType;
	private int basedOn;
	private int distributorId;
	private double grossAmount;
	private double edAmount;
	private double discAmount;
	private double taxAmount;
	private double vatAmount;
	private double adjAmount;
	private double lotAdjAmount;
	private double spcDis;
	private double spcDisAmount;
	private double roundoff;
	private double netAmount;
	private double totalMrp;
	private String creditNote;
	private String remarks;
	private int isDeleted;
	private int isposted;
	private int pstId;
	private String pstNo;
	private int finyrId;
	private int storeId;
	private int companyId;
	private int createdBy;
	private Date createdDate;
	private int updatedBy;
	private Date updatedDate;
	private String lang;
	private List<PurchaseReturnDetails> purchaseReturnDetails;
	private String finyrCode;

	
	
	/*
	* add on Mar 8, 2018
	*/
	
	  
	   private double creditor_debit_amt;
	   private int duties_ledger_id;
	   private int round_ledger_id;
	   private int purchase_ledger_id;
	   private int discount_ledger_id;
	   private int lot_ledger_id;
	   private int creditor_ledger_id;
	   private String qs;
	  private int is_account;
	
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

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getInvMode() {
		return invMode;
	}

	public void setInvMode(int invMode) {
		this.invMode = invMode;
	}

	public int getInvType() {
		return invType;
	}

	public void setInvType(int invType) {
		this.invType = invType;
	}

	public int getBasedOn() {
		return basedOn;
	}

	public void setBasedOn(int basedOn) {
		this.basedOn = basedOn;
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
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

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
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

	public double getSpcDis() {
		return spcDis;
	}

	public void setSpcDis(double spcDis) {
		this.spcDis = spcDis;
	}

	public double getSpcDisAmount() {
		return spcDisAmount;
	}

	public void setSpcDisAmount(double spcDisAmount) {
		this.spcDisAmount = spcDisAmount;
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

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
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

	public int getIsposted() {
		return isposted;
	}

	public void setIsposted(int isposted) {
		this.isposted = isposted;
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

	public List<PurchaseReturnDetails> getPurchaseReturnDetails() {
		return purchaseReturnDetails;
	}

	public void setPurchaseReturnDetails(List<PurchaseReturnDetails> purchaseReturnDetails) {
		this.purchaseReturnDetails = purchaseReturnDetails;
	}

	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
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

	/**
	 * @return the creditor_debit_amt
	 */
	public double getCreditor_debit_amt() {
		return creditor_debit_amt;
	}

	/**
	 * @param creditor_debit_amt the creditor_debit_amt to set
	 */
	public void setCreditor_debit_amt(double creditor_debit_amt) {
		this.creditor_debit_amt = creditor_debit_amt;
	}

	/**
	 * @return the duties_ledger_id
	 */
	public int getDuties_ledger_id() {
		return duties_ledger_id;
	}

	/**
	 * @param duties_ledger_id the duties_ledger_id to set
	 */
	public void setDuties_ledger_id(int duties_ledger_id) {
		this.duties_ledger_id = duties_ledger_id;
	}

	/**
	 * @return the round_ledger_id
	 */
	public int getRound_ledger_id() {
		return round_ledger_id;
	}

	/**
	 * @param round_ledger_id the round_ledger_id to set
	 */
	public void setRound_ledger_id(int round_ledger_id) {
		this.round_ledger_id = round_ledger_id;
	}

	/**
	 * @return the purchase_ledger_id
	 */
	public int getPurchase_ledger_id() {
		return purchase_ledger_id;
	}

	/**
	 * @param purchase_ledger_id the purchase_ledger_id to set
	 */
	public void setPurchase_ledger_id(int purchase_ledger_id) {
		this.purchase_ledger_id = purchase_ledger_id;
	}

	/**
	 * @return the discount_ledger_id
	 */
	public int getDiscount_ledger_id() {
		return discount_ledger_id;
	}

	/**
	 * @param discount_ledger_id the discount_ledger_id to set
	 */
	public void setDiscount_ledger_id(int discount_ledger_id) {
		this.discount_ledger_id = discount_ledger_id;
	}

	/**
	 * @return the lot_ledger_id
	 */
	public int getLot_ledger_id() {
		return lot_ledger_id;
	}

	/**
	 * @param lot_ledger_id the lot_ledger_id to set
	 */
	public void setLot_ledger_id(int lot_ledger_id) {
		this.lot_ledger_id = lot_ledger_id;
	}

	/**
	 * @return the creditor_ledger_id
	 */
	public int getCreditor_ledger_id() {
		return creditor_ledger_id;
	}

	/**
	 * @param creditor_ledger_id the creditor_ledger_id to set
	 */
	public void setCreditor_ledger_id(int creditor_ledger_id) {
		this.creditor_ledger_id = creditor_ledger_id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseReturn [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", purchaseId=" + purchaseId
				+ ", invMode=" + invMode + ", invType=" + invType + ", basedOn=" + basedOn + ", distributorId="
				+ distributorId + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount="
				+ discAmount + ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount + ", adjAmount=" + adjAmount
				+ ", lotAdjAmount=" + lotAdjAmount + ", spcDis=" + spcDis + ", spcDisAmount=" + spcDisAmount
				+ ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp=" + totalMrp + ", creditNote="
				+ creditNote + ", remarks=" + remarks + ", isDeleted=" + isDeleted + ", isposted=" + isposted
				+ ", pstId=" + pstId + ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang
				+ ", purchaseReturnDetails=" + purchaseReturnDetails + ", finyrCode=" + finyrCode
				+ ", creditor_debit_amt=" + creditor_debit_amt + ", duties_ledger_id=" + duties_ledger_id
				+ ", round_ledger_id=" + round_ledger_id + ", purchase_ledger_id=" + purchase_ledger_id
				+ ", discount_ledger_id=" + discount_ledger_id + ", lot_ledger_id=" + lot_ledger_id
				+ ", creditor_ledger_id=" + creditor_ledger_id + ", qs=" + qs + ", is_account=" + is_account + "]";
	}

 
	 

}
