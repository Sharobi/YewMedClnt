package com.sharobi.yewpos.admin.model;

public class Menu {
	private int menuId;
	private int isAll;
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
	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", isAll=" + isAll + "]";
	}
	
	
}
