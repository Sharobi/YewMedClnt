/**
 * 
 */
package com.sharobi.yewpos.storemgnt.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author habib
 *
 */
public class Menu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private int parentMenuId;
    private int displayOrder;
    private String menuUrl;
    private int companyId;
    private String menuCode;
    private int isMenu;
    private int storeId;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
    private int roleId;
    private int menuId;
    private int isAll;
    private int isView;
    private int isNone;
    private String lang;
    private String qs;
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
	public int getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public int getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getIsAll() {
		return isAll;
	}
	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}
	public int getIsView() {
		return isView;
	}
	public void setIsView(int isView) {
		this.isView = isView;
	}
	public int getIsNone() {
		return isNone;
	}
	public void setIsNone(int isNone) {
		this.isNone = isNone;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getQs() {
		return qs;
	}
	public void setQs(String qs) {
		this.qs = qs;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", parentMenuId="
				+ parentMenuId + ", displayOrder=" + displayOrder
				+ ", menuUrl=" + menuUrl + ", companyId=" + companyId
				+ ", menuCode=" + menuCode + ", isMenu=" + isMenu
				+ ", storeId=" + storeId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", roleId=" + roleId
				+ ", menuId=" + menuId + ", isAll=" + isAll + ", isView="
				+ isView + ", isNone=" + isNone + ", lang=" + lang + ", qs="
				+ qs + "]";
	}
    
    

}
