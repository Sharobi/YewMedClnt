package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

public class CustomerMaster implements Serializable{

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

    private int storeId;

    private int companyId;

    private int isDeleted;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;

    private String lang;
    
    private int finyrId;
    private String addharCardNo;
    private Date dob;
    private String gender;
    
    private int age;
    private String guardian_name;
    
    private String panNo; //23-12-2019

    private String gstNo;    
	private String consiName;// 02-03/2020
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
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}
	public String getAddharCardNo() {
		return addharCardNo;
	}
	public void setAddharCardNo(String addharCardNo) {
		this.addharCardNo = addharCardNo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGuardian_name() {
		return guardian_name;
	}
	public void setGuardian_name(String guardian_name) {
		this.guardian_name = guardian_name;
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
		return "CustomerMaster [id=" + id + ", name=" + name + ", code=" + code + ", address=" + address + ", pin="
				+ pin + ", city=" + city + ", state=" + state + ", country=" + country + ", phoneNo=" + phoneNo
				+ ", fax=" + fax + ", obBal=" + obBal + ", creditLimit=" + creditLimit + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", finyrId=" + finyrId + ", addharCardNo=" + addharCardNo + ", dob=" + dob
				+ ", gender=" + gender + ", age=" + age + ", guardian_name=" + guardian_name + ", panNo=" + panNo
				+ ", gstNo=" + gstNo + ", consiName=" + consiName + ", consiAddress=" + consiAddress + ", consiPhone="
				+ consiPhone + ", consiGstNo=" + consiGstNo + ", consiStateId=" + consiStateId + "]";
	}
    
	
}
