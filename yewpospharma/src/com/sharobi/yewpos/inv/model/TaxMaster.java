/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author habib
 *
 */
public class TaxMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private double percentage;
    private String description;
    private int isGroup;
    private String taxMode;
    private int isDeleted;
    private int companyId;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
    private List<TaxGrpDetailsMaster> taxGrpDetailsMasters;    
    
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the taxGrpDetailsMasters
	 */
	public List<TaxGrpDetailsMaster> getTaxGrpDetailsMasters() {
		return taxGrpDetailsMasters;
	}

	/**
	 * @param taxGrpDetailsMasters the taxGrpDetailsMasters to set
	 */
	public void setTaxGrpDetailsMasters(List<TaxGrpDetailsMaster> taxGrpDetailsMasters) {
		this.taxGrpDetailsMasters = taxGrpDetailsMasters;
	}

	@Override
	public String toString() {
		return "TaxMaster [id=" + id + ", name=" + name + ", percentage="
				+ percentage + ", description=" + description + ", isGroup="
				+ isGroup + ", taxMode=" + taxMode + ", isDeleted=" + isDeleted
				+ ", companyId=" + companyId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}
    
    

}
