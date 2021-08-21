package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

public class CustomerPaymentDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String invFrom;
	
	private int invId;

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

	@Override
	public String toString() {
		return "CustomerPaymentDetails [invFrom=" + invFrom + ", invId=" + invId + "]";
	}


}
