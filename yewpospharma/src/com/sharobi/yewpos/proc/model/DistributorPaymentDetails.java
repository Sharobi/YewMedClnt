/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class DistributorPaymentDetails implements Serializable {
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
		return "DistributorPaymentDetails [invFrom=" + invFrom + ", invId=" + invId + "]";
	}

}
