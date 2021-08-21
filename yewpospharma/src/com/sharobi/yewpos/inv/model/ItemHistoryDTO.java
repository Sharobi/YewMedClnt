/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class ItemHistoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int itemId;
	private String batchNo;
	private String expiryDateFormat;
	private int inPackQty;
	private int inLooseQty;
	private int inCalculateLooseQty;
	private int inStockQty;
	private int outPackQty;
	private int outLooseQty;
	private int outCalculateLooseQty;
	private String outStockQty;
	private String tranType;
	private String invNo;
	private String invDate;
	private String partyName;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the expiryDateFormat
	 */
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	/**
	 * @param expiryDateFormat the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	/**
	 * @return the inPackQty
	 */
	public int getInPackQty() {
		return inPackQty;
	}

	/**
	 * @param inPackQty the inPackQty to set
	 */
	public void setInPackQty(int inPackQty) {
		this.inPackQty = inPackQty;
	}

	/**
	 * @return the inLooseQty
	 */
	public int getInLooseQty() {
		return inLooseQty;
	}

	/**
	 * @param inLooseQty the inLooseQty to set
	 */
	public void setInLooseQty(int inLooseQty) {
		this.inLooseQty = inLooseQty;
	}

	/**
	 * @return the inCalculateLooseQty
	 */
	public int getInCalculateLooseQty() {
		return inCalculateLooseQty;
	}

	/**
	 * @param inCalculateLooseQty the inCalculateLooseQty to set
	 */
	public void setInCalculateLooseQty(int inCalculateLooseQty) {
		this.inCalculateLooseQty = inCalculateLooseQty;
	}

	/**
	 * @return the inStockQty
	 */
	public int getInStockQty() {
		return inStockQty;
	}

	/**
	 * @param inStockQty the inStockQty to set
	 */
	public void setInStockQty(int inStockQty) {
		this.inStockQty = inStockQty;
	}

	/**
	 * @return the outPackQty
	 */
	public int getOutPackQty() {
		return outPackQty;
	}

	/**
	 * @param outPackQty the outPackQty to set
	 */
	public void setOutPackQty(int outPackQty) {
		this.outPackQty = outPackQty;
	}

	/**
	 * @return the outLooseQty
	 */
	public int getOutLooseQty() {
		return outLooseQty;
	}

	/**
	 * @param outLooseQty the outLooseQty to set
	 */
	public void setOutLooseQty(int outLooseQty) {
		this.outLooseQty = outLooseQty;
	}

	/**
	 * @return the outCalculateLooseQty
	 */
	public int getOutCalculateLooseQty() {
		return outCalculateLooseQty;
	}

	/**
	 * @param outCalculateLooseQty the outCalculateLooseQty to set
	 */
	public void setOutCalculateLooseQty(int outCalculateLooseQty) {
		this.outCalculateLooseQty = outCalculateLooseQty;
	}

	/**
	 * @return the outStockQty
	 */
	public String getOutStockQty() {
		return outStockQty;
	}

	/**
	 * @param outStockQty the outStockQty to set
	 */
	public void setOutStockQty(String outStockQty) {
		this.outStockQty = outStockQty;
	}

	/**
	 * @return the tranType
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
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
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	@Override
	public String toString() {
		return "ItemHistoryDTO [name=" + name + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDateFormat=" + expiryDateFormat + ", inPackQty=" + inPackQty + ", inLooseQty=" + inLooseQty + ", inCalculateLooseQty=" + inCalculateLooseQty + ", inStockQty=" + inStockQty + ", outPackQty=" + outPackQty + ", outLooseQty=" + outLooseQty + ", outCalculateLooseQty=" + outCalculateLooseQty + ", outStockQty=" + outStockQty + ", tranType=" + tranType + ", invNo=" + invNo + ", invDate=" + invDate + ", partyName=" + partyName + "]";
	}

}
