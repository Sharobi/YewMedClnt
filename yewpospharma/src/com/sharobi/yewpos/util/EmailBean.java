/**
 * 
 */
package com.sharobi.yewpos.util;

import java.io.Serializable;

public class EmailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String toAddr;
    private String subject;
    private String messageBody;
    private String isAttachment;  // Y/N
    private String transType;
    private int transId;
    private String flag;
    private int storeId;
    private int companyId;
    private int finYrId;
    private String cc;
    
	/**
	 * @return the toAddr
	 */
	public String getToAddr() {
		return toAddr;
	}
	/**
	 * @param toAddr the toAddr to set
	 */
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}
	/**
	 * @param messageBody the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	/**
	 * @return the isAttachment
	 */
	public String getIsAttachment() {
		return isAttachment;
	}
	/**
	 * @param isAttachment the isAttachment to set
	 */
	public void setIsAttachment(String isAttachment) {
		this.isAttachment = isAttachment;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * @return the transId
	 */
	public int getTransId() {
		return transId;
	}
	/**
	 * @param transId the transId to set
	 */
	public void setTransId(int transId) {
		this.transId = transId;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the finYrId
	 */
	public int getFinYrId() {
		return finYrId;
	}
	/**
	 * @param finYrId the finYrId to set
	 */
	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}
	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailBean [toAddr=" + toAddr + ", subject=" + subject + ", messageBody=" + messageBody
				+ ", isAttachment=" + isAttachment + ", transType=" + transType + ", transId=" + transId + ", flag="
				+ flag + ", storeId=" + storeId + ", companyId=" + companyId + ", finYrId=" + finYrId + ", cc=" + cc
				+ "]";
	}    
	
}
