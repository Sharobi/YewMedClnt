/**
 * 
 */
package com.sharobi.yewpos.role.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author habib,Manodip
 *
 */
public class MenuByUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleName;
	private int menuId;
	private String menuName;
	private String path;
	private int parentMenuId;
	private int displayOrder;
	private String menuUrl;
	private int companyId;
	private int storeId;
	private int isMenu;
	private String menuCode;
	private int isAll; // is used as isAdd and isEdit
	private int isView; // is used as isDelete
	private int isNone;
	private int level;
	private String ordering;
	   private String qs;
	
	List<MenuByUserDTO> subMenuList;
	private String lang;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getOrdering() {
		return ordering;
	}
	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	public List<MenuByUserDTO> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<MenuByUserDTO> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
	
	/**
	 * @return the qs
	 */
	public String getQs() {
		return qs;
	}
	/**
	 * @param qs the qs to set
	 */
	public void setQs(String qs) {
		this.qs = qs;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuByUserDTO [roleId=" + roleId + ", roleName=" + roleName + ", menuId=" + menuId + ", menuName="
				+ menuName + ", path=" + path + ", parentMenuId=" + parentMenuId + ", displayOrder=" + displayOrder
				+ ", menuUrl=" + menuUrl + ", companyId=" + companyId + ", storeId=" + storeId + ", isMenu=" + isMenu
				+ ", menuCode=" + menuCode + ", isAll=" + isAll + ", isView=" + isView + ", isNone=" + isNone
				+ ", level=" + level + ", ordering=" + ordering + ", qs=" + qs + ", subMenuList=" + subMenuList
				+ ", lang=" + lang + "]";
	}
 
}