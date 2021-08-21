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
public class PaymentDetailsByIdDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    private String billNo;
    private String invNo;
    private Date invDate;
    private double netAmount;
    private double adjAmount;
    private double dueAmount;
    private String invFrom;
    private int invId;
    private double outstandingAmount;
    private String invFromType;
    private double otherAdjAmount;
    
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
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
	public int getInvId() {
		return invId;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	
	public double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	
	public String getInvFromType() {
		return invFromType;
	}
	public void setInvFromType(String invFromType) {
		this.invFromType = invFromType;
	}
	
	public double getOtherAdjAmount() {
		return otherAdjAmount;
	}
	public void setOtherAdjAmount(double otherAdjAmount) {
		this.otherAdjAmount = otherAdjAmount;
	}
	@Override
	public String toString() {
		return "PaymentDetailsByIdDTO [billNo=" + billNo + ", invNo=" + invNo + ", invDate=" + invDate + ", netAmount=" + netAmount + ", adjAmount=" + adjAmount + ", dueAmount=" + dueAmount + ", invFrom=" + invFrom + ", invId=" + invId + ", outstandingAmount=" + outstandingAmount + ", invFromType=" + invFromType + ", otherAdjAmount=" + otherAdjAmount + "]";
	}
    
}
