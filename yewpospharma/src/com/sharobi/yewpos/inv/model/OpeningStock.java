package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.List;

public class OpeningStock implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OpeningStockDetails> openingStockDetails;

	 

	/**
	 * @return the openingStockDetails
	 */
	public List<OpeningStockDetails> getOpeningStockDetails() {
		return openingStockDetails;
	}



	/**
	 * @param openingStockDetails the openingStockDetails to set
	 */
	public void setOpeningStockDetails(List<OpeningStockDetails> openingStockDetails) {
		this.openingStockDetails = openingStockDetails;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OpeningStock [openingStockDetails=" + openingStockDetails + "]";
	}
	 
	 

}
