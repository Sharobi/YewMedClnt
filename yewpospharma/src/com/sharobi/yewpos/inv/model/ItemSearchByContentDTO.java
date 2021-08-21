package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

public class ItemSearchByContentDTO implements Serializable{
	
	private int itemId;

	private String itemName;

 
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
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}


	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	@Override
	public String toString() {
		return "ItemSearchByContentDTO [itemId=" + itemId + ", itemName=" + itemName + "]";
	}


}
