package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class ExpiryDTO implements Serializable {

	private int expiryId;

	private String invNo;

	private String invDate;

	private String fromDate;

	private String toDate;

	private String remarks;

	private int isPosted;

	private String lang;

	/**
	 * @return the expiryId
	 */
	public int getExpiryId() {
		return expiryId;
	}

	/**
	 * @param expiryId
	 *            the expiryId to set
	 */
	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo
	 *            the invNo to set
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
	 * @param invDate
	 *            the invDate to set
	 */
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the isPosted
	 */
	public int getIsPosted() {
		return isPosted;
	}

	/**
	 * @param isPosted
	 *            the isPosted to set
	 */
	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "ExpiryAllDTO [expiryId=" + expiryId + ", invNo=" + invNo + ", invDate=" + invDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", remarks=" + remarks + ", isPosted=" + isPosted + ", lang=" + lang
				+ "]";
	}

}
