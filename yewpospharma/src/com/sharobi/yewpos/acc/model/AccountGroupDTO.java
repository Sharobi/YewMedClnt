/**
 * 
 */
package com.sharobi.yewpos.acc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Arunima Roy
 *
 *         Nov 3, 2017
 */
public class AccountGroupDTO implements Serializable {
	private int id;

	private String name;

	private String code;

	private String description;
	private String accountTypeName;
	private int accountTypeId;

	private int companyId;

	private int storeId;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;
	private int is_system;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the is_system
	 */
	public int getIs_system() {
		return is_system;
	}

	/**
	 * @param is_system
	 *            the is_system to set
	 */
	public void setIs_system(int is_system) {
		this.is_system = is_system;
	}

	/**
	 * @return the accountTypeName
	 */
	public String getAccountTypeName() {
		return accountTypeName;
	}

	/**
	 * @param accountTypeName
	 *            the accountTypeName to set
	 */
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountGroupDTO [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description
				+ ", accountTypeName=" + accountTypeName + ", accountTypeId=" + accountTypeId + ", companyId="
				+ companyId + ", storeId=" + storeId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", is_system=" + is_system + "]";
	}

}
