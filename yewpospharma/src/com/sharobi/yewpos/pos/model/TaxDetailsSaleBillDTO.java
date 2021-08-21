/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

public class TaxDetailsSaleBillDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int taxId;
    
    private String taxName;
    
    private double taxPercentage;
    
    private double taxAmount;
    
    private double taxableAmount;
    
    private double cgst;
    
    private double cgstPercentage;
    
    private double sgst;
    
    private double sgstPercentage;
    
    private double igst;
    
    private double igstPercentage;

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
	 * @return the taxPercentage
	 */
	public double getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	/**
	 * @return the taxAmount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the taxableAmount
	 */
	public double getTaxableAmount() {
		return taxableAmount;
	}

	/**
	 * @param taxableAmount the taxableAmount to set
	 */
	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	/**
	 * @return the cgst
	 */
	public double getCgst() {
		return cgst;
	}

	/**
	 * @param cgst the cgst to set
	 */
	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	/**
	 * @return the cgstPercentage
	 */
	public double getCgstPercentage() {
		return cgstPercentage;
	}

	/**
	 * @param cgstPercentage the cgstPercentage to set
	 */
	public void setCgstPercentage(double cgstPercentage) {
		this.cgstPercentage = cgstPercentage;
	}

	/**
	 * @return the sgst
	 */
	public double getSgst() {
		return sgst;
	}

	/**
	 * @param sgst the sgst to set
	 */
	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	/**
	 * @return the sgstPercentage
	 */
	public double getSgstPercentage() {
		return sgstPercentage;
	}

	/**
	 * @param sgstPercentage the sgstPercentage to set
	 */
	public void setSgstPercentage(double sgstPercentage) {
		this.sgstPercentage = sgstPercentage;
	}

	/**
	 * @return the igst
	 */
	public double getIgst() {
		return igst;
	}

	/**
	 * @param igst the igst to set
	 */
	public void setIgst(double igst) {
		this.igst = igst;
	}

	/**
	 * @return the igstPercentage
	 */
	public double getIgstPercentage() {
		return igstPercentage;
	}

	/**
	 * @param igstPercentage the igstPercentage to set
	 */
	public void setIgstPercentage(double igstPercentage) {
		this.igstPercentage = igstPercentage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxDetailsSaleBillDTO [taxId=" + taxId + ", taxName=" + taxName + ", taxPercentage=" + taxPercentage
				+ ", taxAmount=" + taxAmount + ", taxableAmount=" + taxableAmount + ", cgst=" + cgst
				+ ", cgstPercentage=" + cgstPercentage + ", sgst=" + sgst + ", sgstPercentage=" + sgstPercentage
				+ ", igst=" + igst + ", igstPercentage=" + igstPercentage + "]";
	}


}
