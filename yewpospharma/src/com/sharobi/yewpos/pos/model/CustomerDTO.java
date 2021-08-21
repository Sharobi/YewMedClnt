/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 *         27-Jul-2017
 */
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String code;
	private String address;
	private String pin;
	private String city;
	private String state;
	private String country;
	private String phoneNo;
	private String fax;
	private double obBal;
	private double creditLimit;
	private double paybleAmount;
	private String paybleText;
	private String addharCardNo;
	private double outstandingAmount;
	private String dob;
	private String gender;
	private String panNo; // 23-12-2019
	private String gstNo;

	private String consiName;//// 02-03/2020
	private String consiAddress;
	private String consiPhone;
	private String consiGstNo;
	private int consiStateId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public double getObBal() {
		return obBal;
	}

	public void setObBal(double obBal) {
		this.obBal = obBal;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getPaybleAmount() {
		return paybleAmount;
	}

	public void setPaybleAmount(double paybleAmount) {
		this.paybleAmount = paybleAmount;
	}

	public String getPaybleText() {
		return paybleText;
	}

	public void setPaybleText(String paybleText) {
		this.paybleText = paybleText;
	}

	public String getAddharCardNo() {
		return addharCardNo;
	}

	public void setAddharCardNo(String addharCardNo) {
		this.addharCardNo = addharCardNo;
	}

	public double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getConsiName() {
		return consiName;
	}

	public void setConsiName(String consiName) {
		this.consiName = consiName;
	}

	public String getConsiAddress() {
		return consiAddress;
	}

	public void setConsiAddress(String consiAddress) {
		this.consiAddress = consiAddress;
	}

	public String getConsiPhone() {
		return consiPhone;
	}

	public void setConsiPhone(String consiPhone) {
		this.consiPhone = consiPhone;
	}

	public String getConsiGstNo() {
		return consiGstNo;
	}

	public void setConsiGstNo(String consiGstNo) {
		this.consiGstNo = consiGstNo;
	}

	public int getConsiStateId() {
		return consiStateId;
	}

	public void setConsiStateId(int consiStateId) {
		this.consiStateId = consiStateId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", pin=" + pin
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", phoneNo=" + phoneNo + ", fax="
				+ fax + ", obBal=" + obBal + ", creditLimit=" + creditLimit + ", paybleAmount=" + paybleAmount
				+ ", paybleText=" + paybleText + ", addharCardNo=" + addharCardNo + ", outstandingAmount="
				+ outstandingAmount + ", dob=" + dob + ", gender=" + gender + ", panNo=" + panNo + ", gstNo=" + gstNo
				+ ", consiName=" + consiName + ", consiAddress=" + consiAddress + ", consiPhone=" + consiPhone
				+ ", consiGstNo=" + consiGstNo + ", consiStateId=" + consiStateId + "]";
	}

}
