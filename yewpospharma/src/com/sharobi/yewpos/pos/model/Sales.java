package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip
 */
public class Sales implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String invNo;

	private String invDate;

	private String invTime;

	private int customerId;

	private String customerName;

	private String customerAddress;

	private String customerPhone;

	private int doctorId;

	private String doctorName;

	private int invMode;

	private double grossAmount;

	private double edAmount;

	private double discAmount;

	private double taxAmount;

	private double vatAmount;

	private double adjAmount;

	private double netAmount;

	private double roundoff;

	private String remarks;

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

	private List<SalesDetails> salesDetails;

	private String finyrCode;

	private double cashAmount;

	private double cardAmount;

	private double creditAmount;

	private String cardFourDigit;

	private String cardExpDate;

	private double tenderAmount;

	private int printCount;

	private int holdFlag;

	private int isposted;

	private double customerDiscPer;

	private String dateText;
	private int saleReturnId;
	private String saleReturnInvNo;
	private List<SaleReturn> saleReturns;
	private double specialDiscPer;
	private double specialDiscAmount;
	private double totalMrp;
	private String prescriptionRegNo;
	private String prescriptionIssueDate;
	private String slipNo;
	private String esiCode;
	private String esiType;

	/*
	 * add on Mar 6, 2018
	 */

	private int duties_ledger_id;
	private int round_ledger_id;
	private int sale_ledger_id;
	private int discount_ledger_id;
	private int debitor_ledger_id;
	private int debitor_cash_ledger_id;
	private int card_ledger_id;
	private String qs;
	private int is_account;
	private int isExclusive;
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
	 * @return the invTime
	 */
	public String getInvTime() {
		return invTime;
	}
	/**
	 * @param invTime the invTime to set
	 */
	public void setInvTime(String invTime) {
		this.invTime = invTime;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}
	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	/**
	 * @return the customerPhone
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}
	/**
	 * @param customerPhone the customerPhone to set
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the invMode
	 */
	public int getInvMode() {
		return invMode;
	}
	/**
	 * @param invMode the invMode to set
	 */
	public void setInvMode(int invMode) {
		this.invMode = invMode;
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
	 * @return the edAmount
	 */
	public double getEdAmount() {
		return edAmount;
	}
	/**
	 * @param edAmount the edAmount to set
	 */
	public void setEdAmount(double edAmount) {
		this.edAmount = edAmount;
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
	 * @return the vatAmount
	 */
	public double getVatAmount() {
		return vatAmount;
	}
	/**
	 * @param vatAmount the vatAmount to set
	 */
	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
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
	 * @return the pstId
	 */
	public int getPstId() {
		return pstId;
	}
	/**
	 * @param pstId the pstId to set
	 */
	public void setPstId(int pstId) {
		this.pstId = pstId;
	}
	/**
	 * @return the pstNo
	 */
	public String getPstNo() {
		return pstNo;
	}
	/**
	 * @param pstNo the pstNo to set
	 */
	public void setPstNo(String pstNo) {
		this.pstNo = pstNo;
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
	 * @return the salesDetails
	 */
	public List<SalesDetails> getSalesDetails() {
		return salesDetails;
	}
	/**
	 * @param salesDetails the salesDetails to set
	 */
	public void setSalesDetails(List<SalesDetails> salesDetails) {
		this.salesDetails = salesDetails;
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
	 * @return the cashAmount
	 */
	public double getCashAmount() {
		return cashAmount;
	}
	/**
	 * @param cashAmount the cashAmount to set
	 */
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * @return the cardAmount
	 */
	public double getCardAmount() {
		return cardAmount;
	}
	/**
	 * @param cardAmount the cardAmount to set
	 */
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	/**
	 * @return the creditAmount
	 */
	public double getCreditAmount() {
		return creditAmount;
	}
	/**
	 * @param creditAmount the creditAmount to set
	 */
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	/**
	 * @return the cardFourDigit
	 */
	public String getCardFourDigit() {
		return cardFourDigit;
	}
	/**
	 * @param cardFourDigit the cardFourDigit to set
	 */
	public void setCardFourDigit(String cardFourDigit) {
		this.cardFourDigit = cardFourDigit;
	}
	/**
	 * @return the cardExpDate
	 */
	public String getCardExpDate() {
		return cardExpDate;
	}
	/**
	 * @param cardExpDate the cardExpDate to set
	 */
	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}
	/**
	 * @return the tenderAmount
	 */
	public double getTenderAmount() {
		return tenderAmount;
	}
	/**
	 * @param tenderAmount the tenderAmount to set
	 */
	public void setTenderAmount(double tenderAmount) {
		this.tenderAmount = tenderAmount;
	}
	/**
	 * @return the printCount
	 */
	public int getPrintCount() {
		return printCount;
	}
	/**
	 * @param printCount the printCount to set
	 */
	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}
	/**
	 * @return the holdFlag
	 */
	public int getHoldFlag() {
		return holdFlag;
	}
	/**
	 * @param holdFlag the holdFlag to set
	 */
	public void setHoldFlag(int holdFlag) {
		this.holdFlag = holdFlag;
	}
	/**
	 * @return the isposted
	 */
	public int getIsposted() {
		return isposted;
	}
	/**
	 * @param isposted the isposted to set
	 */
	public void setIsposted(int isposted) {
		this.isposted = isposted;
	}
	/**
	 * @return the customerDiscPer
	 */
	public double getCustomerDiscPer() {
		return customerDiscPer;
	}
	/**
	 * @param customerDiscPer the customerDiscPer to set
	 */
	public void setCustomerDiscPer(double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
	}
	/**
	 * @return the dateText
	 */
	public String getDateText() {
		return dateText;
	}
	/**
	 * @param dateText the dateText to set
	 */
	public void setDateText(String dateText) {
		this.dateText = dateText;
	}
	/**
	 * @return the saleReturnId
	 */
	public int getSaleReturnId() {
		return saleReturnId;
	}
	/**
	 * @param saleReturnId the saleReturnId to set
	 */
	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	/**
	 * @return the saleReturnInvNo
	 */
	public String getSaleReturnInvNo() {
		return saleReturnInvNo;
	}
	/**
	 * @param saleReturnInvNo the saleReturnInvNo to set
	 */
	public void setSaleReturnInvNo(String saleReturnInvNo) {
		this.saleReturnInvNo = saleReturnInvNo;
	}
	/**
	 * @return the saleReturns
	 */
	public List<SaleReturn> getSaleReturns() {
		return saleReturns;
	}
	/**
	 * @param saleReturns the saleReturns to set
	 */
	public void setSaleReturns(List<SaleReturn> saleReturns) {
		this.saleReturns = saleReturns;
	}
	/**
	 * @return the specialDiscPer
	 */
	public double getSpecialDiscPer() {
		return specialDiscPer;
	}
	/**
	 * @param specialDiscPer the specialDiscPer to set
	 */
	public void setSpecialDiscPer(double specialDiscPer) {
		this.specialDiscPer = specialDiscPer;
	}
	/**
	 * @return the specialDiscAmount
	 */
	public double getSpecialDiscAmount() {
		return specialDiscAmount;
	}
	/**
	 * @param specialDiscAmount the specialDiscAmount to set
	 */
	public void setSpecialDiscAmount(double specialDiscAmount) {
		this.specialDiscAmount = specialDiscAmount;
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
	 * @return the prescriptionRegNo
	 */
	public String getPrescriptionRegNo() {
		return prescriptionRegNo;
	}
	/**
	 * @param prescriptionRegNo the prescriptionRegNo to set
	 */
	public void setPrescriptionRegNo(String prescriptionRegNo) {
		this.prescriptionRegNo = prescriptionRegNo;
	}
	/**
	 * @return the prescriptionIssueDate
	 */
	public String getPrescriptionIssueDate() {
		return prescriptionIssueDate;
	}
	/**
	 * @param prescriptionIssueDate the prescriptionIssueDate to set
	 */
	public void setPrescriptionIssueDate(String prescriptionIssueDate) {
		this.prescriptionIssueDate = prescriptionIssueDate;
	}
	/**
	 * @return the slipNo
	 */
	public String getSlipNo() {
		return slipNo;
	}
	/**
	 * @param slipNo the slipNo to set
	 */
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	/**
	 * @return the esiCode
	 */
	public String getEsiCode() {
		return esiCode;
	}
	/**
	 * @param esiCode the esiCode to set
	 */
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	/**
	 * @return the esiType
	 */
	public String getEsiType() {
		return esiType;
	}
	/**
	 * @param esiType the esiType to set
	 */
	public void setEsiType(String esiType) {
		this.esiType = esiType;
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
	 * @return the sale_ledger_id
	 */
	public int getSale_ledger_id() {
		return sale_ledger_id;
	}
	/**
	 * @param sale_ledger_id the sale_ledger_id to set
	 */
	public void setSale_ledger_id(int sale_ledger_id) {
		this.sale_ledger_id = sale_ledger_id;
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
	 * @return the debitor_ledger_id
	 */
	public int getDebitor_ledger_id() {
		return debitor_ledger_id;
	}
	/**
	 * @param debitor_ledger_id the debitor_ledger_id to set
	 */
	public void setDebitor_ledger_id(int debitor_ledger_id) {
		this.debitor_ledger_id = debitor_ledger_id;
	}
	/**
	 * @return the debitor_cash_ledger_id
	 */
	public int getDebitor_cash_ledger_id() {
		return debitor_cash_ledger_id;
	}
	/**
	 * @param debitor_cash_ledger_id the debitor_cash_ledger_id to set
	 */
	public void setDebitor_cash_ledger_id(int debitor_cash_ledger_id) {
		this.debitor_cash_ledger_id = debitor_cash_ledger_id;
	}
	/**
	 * @return the card_ledger_id
	 */
	public int getCard_ledger_id() {
		return card_ledger_id;
	}
	/**
	 * @param card_ledger_id the card_ledger_id to set
	 */
	public void setCard_ledger_id(int card_ledger_id) {
		this.card_ledger_id = card_ledger_id;
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
	public int getIsExclusive() {
		return isExclusive;
	}
	public void setIsExclusive(int isExclusive) {
		this.isExclusive = isExclusive;
	}
	@Override
	public String toString() {
		return "Sales [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", invTime=" + invTime
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", doctorId=" + doctorId + ", doctorName="
				+ doctorName + ", invMode=" + invMode + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount
				+ ", discAmount=" + discAmount + ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount
				+ ", adjAmount=" + adjAmount + ", netAmount=" + netAmount + ", roundoff=" + roundoff + ", remarks="
				+ remarks + ", pstId=" + pstId + ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", salesDetails=" + salesDetails + ", finyrCode=" + finyrCode + ", cashAmount="
				+ cashAmount + ", cardAmount=" + cardAmount + ", creditAmount=" + creditAmount + ", cardFourDigit="
				+ cardFourDigit + ", cardExpDate=" + cardExpDate + ", tenderAmount=" + tenderAmount + ", printCount="
				+ printCount + ", holdFlag=" + holdFlag + ", isposted=" + isposted + ", customerDiscPer="
				+ customerDiscPer + ", dateText=" + dateText + ", saleReturnId=" + saleReturnId + ", saleReturnInvNo="
				+ saleReturnInvNo + ", saleReturns=" + saleReturns + ", specialDiscPer=" + specialDiscPer
				+ ", specialDiscAmount=" + specialDiscAmount + ", totalMrp=" + totalMrp + ", prescriptionRegNo="
				+ prescriptionRegNo + ", prescriptionIssueDate=" + prescriptionIssueDate + ", slipNo=" + slipNo
				+ ", esiCode=" + esiCode + ", esiType=" + esiType + ", duties_ledger_id=" + duties_ledger_id
				+ ", round_ledger_id=" + round_ledger_id + ", sale_ledger_id=" + sale_ledger_id
				+ ", discount_ledger_id=" + discount_ledger_id + ", debitor_ledger_id=" + debitor_ledger_id
				+ ", debitor_cash_ledger_id=" + debitor_cash_ledger_id + ", card_ledger_id=" + card_ledger_id + ", qs="
				+ qs + ", is_account=" + is_account + ", isExclusive=" + isExclusive + "]";
	}

}
