package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

public class CustomerInfoDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String phoneNo;
	
	private String address;
	
	private double creditLimit;
	private String code;

	private String fax; //23-12-2019

	private String gstNo;

	
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	@Override
	public String toString() {
		return "CustomerInfoDTO [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", address=" + address
				+ ", creditLimit=" + creditLimit + ", code=" + code + ", fax=" + fax + ", gstNo=" + gstNo + "]";
	}

}
