package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip Jana
 */

public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String invNo;

	private String invDate;

	private int poOrderId;

	private int purchaseReturnId;

	private int invMode;

	private int distributorId;

	private double grossAmount;

	private double edAmount;

	private double discAmount;

	private double taxAmount;

	private double vatAmount;

	private double adjAmount;

	private double netAmount;

	private double lotAdjAmount;

	private double roundoff;

	private double cst;

	private double vatDiff;

	private String remarks;

	private int isPosted; // changed

	private int pstId;

	private String pstNo;

	private int finyrId;

	private int storeId;

	private int companyId;

	private int isDeleted;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;

	private String lang;

	private String qryCondition;

	private String invModeName;

	private String distName;

	private double totalMrp;

	private String finyrCode;

	private String billNo;

	private double specDiscPer;

	private double specDiscAmount;

	private double distDiscPer;

	private List<PurchaseDetails> purchaseDetails;

	private int paymentId;

	private double advAmount;

	private double distributorBillAmount;

	private double otherAdjAmount;

	List<PurchaseReturn> purchaseReturns;

	private double expiryAdjAmount;

	private List<ExpiryPurchaseMapping> expiryReturns;
	private String dueDate;

	private String purchaseOrderInvNo;

	/*
	 * add on Mar 12, 2018
	 */

	private int duties_ledger_id;
	private int round_ledger_id;
	private int purchase_ledger_id;
	private int discount_ledger_id;
	private int credior_ledger_id;
	private int lotadjas_ledger_id;
	private String qs;
	private double creditor_amt;

	private int is_account;

	/**
	 * @return the creditor_amt
	 */
	public double getCreditor_amt() {
		return creditor_amt;
	}

	/**
	 * @param creditor_amt
	 *            the creditor_amt to set
	 */
	public void setCreditor_amt(double creditor_amt) {
		this.creditor_amt = creditor_amt;
	}

	/**
	 * @return the duties_ledger_id
	 */
	public int getDuties_ledger_id() {
		return duties_ledger_id;
	}

	/**
	 * @param duties_ledger_id
	 *            the duties_ledger_id to set
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
	 * @param round_ledger_id
	 *            the round_ledger_id to set
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
	 * @param purchase_ledger_id
	 *            the purchase_ledger_id to set
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
	 * @param discount_ledger_id
	 *            the discount_ledger_id to set
	 */
	public void setDiscount_ledger_id(int discount_ledger_id) {
		this.discount_ledger_id = discount_ledger_id;
	}

	/**
	 * @return the credior_ledger_id
	 */
	public int getCredior_ledger_id() {
		return credior_ledger_id;
	}

	/**
	 * @param credior_ledger_id
	 *            the credior_ledger_id to set
	 */
	public void setCredior_ledger_id(int credior_ledger_id) {
		this.credior_ledger_id = credior_ledger_id;
	}

	/**
	 * @return the lotadjas_ledger_id
	 */
	public int getLotadjas_ledger_id() {
		return lotadjas_ledger_id;
	}

	/**
	 * @param lotadjas_ledger_id
	 *            the lotadjas_ledger_id to set
	 */
	public void setLotadjas_ledger_id(int lotadjas_ledger_id) {
		this.lotadjas_ledger_id = lotadjas_ledger_id;
	}

	/**
	 * @return the qs
	 */
	public String getQs() {
		return qs;
	}

	/**
	 * @param qs
	 *            the qs to set
	 */
	public void setQs(String qs) {
		this.qs = qs;
	}

	/**
	 * @return the purchaseOrderInvNo
	 */
	public String getPurchaseOrderInvNo() {
		return purchaseOrderInvNo;
	}

	/**
	 * @param purchaseOrderInvNo
	 *            the purchaseOrderInvNo to set
	 */
	public void setPurchaseOrderInvNo(String purchaseOrderInvNo) {
		this.purchaseOrderInvNo = purchaseOrderInvNo;
	}

	public List<ExpiryPurchaseMapping> getExpiryReturns() {
		return expiryReturns;
	}

	public void setExpiryReturns(List<ExpiryPurchaseMapping> expiryReturns) {
		this.expiryReturns = expiryReturns;
	}

	public double getExpiryAdjAmount() {
		return expiryAdjAmount;
	}

	public void setExpiryAdjAmount(double expiryAdjAmount) {
		this.expiryAdjAmount = expiryAdjAmount;
	}

	public List<PurchaseReturn> getPurchaseReturns() {
		return purchaseReturns;
	}

	public void setPurchaseReturns(List<PurchaseReturn> purchaseReturns) {
		this.purchaseReturns = purchaseReturns;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAdvAmount() {
		return advAmount;
	}

	public void setAdvAmount(double advAmount) {
		this.advAmount = advAmount;
	}

	public double getDistributorBillAmount() {
		return distributorBillAmount;
	}

	public void setDistributorBillAmount(double distributorBillAmount) {
		this.distributorBillAmount = distributorBillAmount;
	}

	public double getOtherAdjAmount() {
		return otherAdjAmount;
	}

	public void setOtherAdjAmount(double otherAdjAmount) {
		this.otherAdjAmount = otherAdjAmount;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
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

	public int getPoOrderId() {
		return poOrderId;
	}

	public void setPoOrderId(int poOrderId) {
		this.poOrderId = poOrderId;
	}

	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}

	public int getInvMode() {
		return invMode;
	}

	public void setInvMode(int invMode) {
		this.invMode = invMode;
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

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
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

	public double getCst() {
		return cst;
	}

	public void setCst(double cst) {
		this.cst = cst;
	}

	public double getVatDiff() {
		return vatDiff;
	}

	public void setVatDiff(double vatDiff) {
		this.vatDiff = vatDiff;
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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getQryCondition() {
		return qryCondition;
	}

	public void setQryCondition(String qryCondition) {
		this.qryCondition = qryCondition;
	}

	public String getInvModeName() {
		return invModeName;
	}

	public void setInvModeName(String invModeName) {
		this.invModeName = invModeName;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public List<PurchaseDetails> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	public double getSpecDiscPer() {
		return specDiscPer;
	}

	public void setSpecDiscPer(double specDiscPer) {
		this.specDiscPer = specDiscPer;
	}

	public double getSpecDiscAmount() {
		return specDiscAmount;
	}

	public void setSpecDiscAmount(double specDiscAmount) {
		this.specDiscAmount = specDiscAmount;
	}

	public double getDistDiscPer() {
		return distDiscPer;
	}

	public void setDistDiscPer(double distDiscPer) {
		this.distDiscPer = distDiscPer;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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
		return "Purchase [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", poOrderId=" + poOrderId
				+ ", purchaseReturnId=" + purchaseReturnId + ", invMode=" + invMode + ", distributorId=" + distributorId
				+ ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount
				+ ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount + ", adjAmount=" + adjAmount + ", netAmount="
				+ netAmount + ", lotAdjAmount=" + lotAdjAmount + ", roundoff=" + roundoff + ", cst=" + cst
				+ ", vatDiff=" + vatDiff + ", remarks=" + remarks + ", isPosted=" + isPosted + ", pstId=" + pstId
				+ ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", storeId=" + storeId + ", companyId=" + companyId
				+ ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", lang=" + lang + ", qryCondition="
				+ qryCondition + ", invModeName=" + invModeName + ", distName=" + distName + ", totalMrp=" + totalMrp
				+ ", finyrCode=" + finyrCode + ", billNo=" + billNo + ", specDiscPer=" + specDiscPer
				+ ", specDiscAmount=" + specDiscAmount + ", distDiscPer=" + distDiscPer + ", purchaseDetails="
				+ purchaseDetails + ", paymentId=" + paymentId + ", advAmount=" + advAmount + ", distributorBillAmount="
				+ distributorBillAmount + ", otherAdjAmount=" + otherAdjAmount + ", purchaseReturns=" + purchaseReturns
				+ ", expiryAdjAmount=" + expiryAdjAmount + ", expiryReturns=" + expiryReturns + ", dueDate=" + dueDate
				+ ", purchaseOrderInvNo=" + purchaseOrderInvNo + ", duties_ledger_id=" + duties_ledger_id
				+ ", round_ledger_id=" + round_ledger_id + ", purchase_ledger_id=" + purchase_ledger_id
				+ ", discount_ledger_id=" + discount_ledger_id + ", credior_ledger_id=" + credior_ledger_id
				+ ", lotadjas_ledger_id=" + lotadjas_ledger_id + ", qs=" + qs + ", creditor_amt=" + creditor_amt
				+ ", is_account=" + is_account + "]";
	}

 

}
