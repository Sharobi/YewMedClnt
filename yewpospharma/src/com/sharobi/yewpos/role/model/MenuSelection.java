package com.sharobi.yewpos.role.model;

import java.io.Serializable;

/**
 * @author habib,Manodip
 *
 */
public class MenuSelection implements Serializable{

	private static final long serialVersionUID = 1L;
	private String menu;
	private String subMenu;
	private String childsubMenu;
	private String lang;

	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}
	public String getChildsubMenu() {
		return childsubMenu;
	}
	public void setChildsubMenu(String childsubMenu) {
		this.childsubMenu = childsubMenu;
	}
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "MenuSelection [menu=" + menu + ", subMenu=" + subMenu + ", childsubMenu=" + childsubMenu + ", lang=" + lang + "]";
	}

}
