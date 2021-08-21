/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class ExcelUploadResultObj implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String result; // contains invoice numbr / 0
	private List<String> failedItemList;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the failedItemList
	 */
	public List<String> getFailedItemList() {
		return failedItemList;
	}

	/**
	 * @param failedItemList
	 *            the failedItemList to set
	 */
	public void setFailedItemList(List<String> failedItemList) {
		this.failedItemList = failedItemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelUploadResultObj [id=" + id + ", result=" + result + ", failedItemList=" + failedItemList + "]";
	}

}
