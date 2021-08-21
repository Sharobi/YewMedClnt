/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *
 * 
 */
public class PaymentDetailsAllDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int paymentId;
    private String invNo;
    private String invDate;
    private int invMode;
    private String invModeName;
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
    private int supplierId;
    private String supplierName;
    private int isPosted;
    private int createdBy;
    private String remarks;
    private String createdByUser;
        
            
	public String getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "PaymentDetailsAllDTO [paymentId=" + paymentId + ", invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode + ", invModeName=" + invModeName + ", paybleAmount=" + paybleAmount + ", outstandingAmount=" + outstandingAmount + ", netAmount=" + netAmount + ", roundoff=" + roundoff + ", payAmount=" + payAmount + ", remainingAmount=" + remainingAmount + ", cardNo=" + cardNo + ", chequeNo=" + chequeNo + ", chequeDate=" + chequeDate + ", bankName=" + bankName + ", supplierId=" + supplierId + ", supplierName=" + supplierName + "]";
	}
    
}
