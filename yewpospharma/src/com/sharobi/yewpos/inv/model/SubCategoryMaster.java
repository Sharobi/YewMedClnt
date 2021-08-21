package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *
 * Apr 5, 2018
 */
public class SubCategoryMaster implements Serializable{
	
	private int id;
    
    
    private int categoryId;
    
    
    private int parentId;

    
    private String name;

    
    private int companyId;

    
    private int isDeleted;

    
    private int createdBy;

    
    private Date createdDate;

    
    private int updatedBy;

    
    private Date updatedDate;

    private String categoryName;
    
    private String lang;
    


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
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}



	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}



	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}



	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}



	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubCategoryMaster [id=" + id + ", categoryId=" + categoryId + ", parentId=" + parentId + ", name="
				+ name + ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", categoryName=" + categoryName + ", lang=" + lang + "]";
	}
    
    

}
