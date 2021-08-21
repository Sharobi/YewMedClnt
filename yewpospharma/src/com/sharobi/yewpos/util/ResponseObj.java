/**
 * 
 */
package com.sharobi.yewpos.util;

import java.io.Serializable;

import com.sharobi.yewpos.inv.model.ItemMaster;

/**
 * @author habib
 *
 */
public class ResponseObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String status;
	private String reason;
	private ItemMaster item;
	
	public ItemMaster getItem() {
		return item;
	}
	public void setItem(ItemMaster item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "ResponseObj [id=" + id + ", status=" + status + ", reason=" + reason + ", item=" + item + "]";
	}
	

}
