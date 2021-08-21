/**
 * 
 */
package com.sharobi.yewpos.storemgnt.model;

import java.io.Serializable;

/**
 * @author habib
 *
 */
public class MenuProductTypeMapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int menuId;
    private int productTypeId;
    private int companyId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "MenuProductTypeMapper [id=" + id + ", menuId=" + menuId
				+ ", productTypeId=" + productTypeId + ", companyId="
				+ companyId + "]";
	}
    
    

}
