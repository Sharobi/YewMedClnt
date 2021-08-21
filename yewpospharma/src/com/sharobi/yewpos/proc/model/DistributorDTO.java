/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 28-Jul-2017
 */
public class DistributorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String pin;
    private String address;
    private String city;
    private String state;
    private String country;
    private String phoneNo1;
    private String phoneNo2;
    private String fax;
    private String email;
    private String contactPerson;
    private String registrationNo;
    private double obBal;
    private double creditLimit;
    private double discount;
    private double discountAmount;
    private String licenceNo;
    private int finyrId;
    private double paybleAmount;
    private String paybleText;
    private double outstandingAmount;
    
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
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
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
	public double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	@Override
	public String toString() {
		return "DistributorDTO [id=" + id + ", name=" + name + ", pin=" + pin + ", address=" + address + ", city=" + city + ", state=" + state + ", country=" + country + ", phoneNo1=" + phoneNo1 + ", phoneNo2=" + phoneNo2 + ", fax=" + fax + ", email=" + email + ", contactPerson=" + contactPerson + ", registrationNo=" + registrationNo + ", obBal=" + obBal + ", creditLimit=" + creditLimit + ", discount=" + discount + ", discountAmount=" + discountAmount + ", licenceNo=" + licenceNo + ", finyrId=" + finyrId + ", paybleAmount=" + paybleAmount + ", paybleText=" + paybleText + ", outstandingAmount=" + outstandingAmount + "]";
	}

}
