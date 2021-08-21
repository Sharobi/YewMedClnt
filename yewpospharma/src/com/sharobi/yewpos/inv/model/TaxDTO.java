/**
 * 
 */
package com.sharobi.yewpos.inv.model;

/**
 * @author habib
 *
 */
public class TaxDTO {
	
	private int taxId;
    private String taxName;
    private double percentage;
    private String description;
    private int isGroup;
    private String taxMode;
    private String taxLabel;
    
    
	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}


	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}


	/**
	 * @return the taxName
	 */
	public String getTaxName() {
		return taxName;
	}


	/**
	 * @param taxName the taxName to set
	 */
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}


	/**
	 * @return the percentage
	 */
	public double getPercentage() {
		return percentage;
	}


	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the isGroup
	 */
	public int getIsGroup() {
		return isGroup;
	}


	/**
	 * @param isGroup the isGroup to set
	 */
	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}


	/**
	 * @return the taxMode
	 */
	public String getTaxMode() {
		return taxMode;
	}


	/**
	 * @param taxMode the taxMode to set
	 */
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}


	/**
	 * @return the taxLabel
	 */
	public String getTaxLabel() {
		return taxLabel;
	}


	/**
	 * @param taxLabel the taxLabel to set
	 */
	public void setTaxLabel(String taxLabel) {
		this.taxLabel = taxLabel;
	}


	@Override
	public String toString() {
		return "TaxDTO [taxId=" + taxId + ", taxName=" + taxName
				+ ", percentage=" + percentage + ", description=" + description
				+ ", isGroup=" + isGroup + ", taxMode=" + taxMode
				+ ", taxLabel=" + taxLabel + "]";
	}
    
    

}
