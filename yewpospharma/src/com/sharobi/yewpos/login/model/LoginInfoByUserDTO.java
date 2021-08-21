/**
 * 
 */
package com.sharobi.yewpos.login.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;



/**
 * @author habib,Manodip
 *
 */
public class LoginInfoByUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
	private String userCode;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private int companyId;
	private int storeId;
	private int isCurrent;
	private int isActive;
	private int isLocked;
	private int finyrId;
	private String startDate;
	private String endDate;
	private String loginDate;
	private String message;
	private String lang;
	private String description;
	private String finyrCode;
	private String taxRegNo; // new
    private int isTaxRegNo;   // new
    private double vatPer;   // new
    private double taxPer;  // new
    private int productTypeId;
    private String dlLicenceNo;
    private String dlRegistrationDate;
    private String dlExpiryDate;
    private String stateLicenceNo;
    private String stateRegistrationDate;
    private String stateExpiryDate;
    private String storeCode;
    private int locationId;
    private String locationName; 
    private String versionName; 
    private String taxRegNoText;
    private int isExclusive;
    private int isMailEnable;
    private String mailPassword;
    private String mailPort;
    private String mailSmtp;
    private int isEsi;
    
    private String currency_code ;
    private String currency_desc ;
    
    private int is_account;
    private int isAdmin; // New Added 25.7.2019 for Admin Panel

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the isCurrent
	 */
	public int getIsCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}

	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isLocked
	 */
	public int getIsLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}

	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the finyrCode
	 */
	public String getFinyrCode() {
		return finyrCode;
	}

	/**
	 * @param finyrCode the finyrCode to set
	 */
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	/**
	 * @return the taxRegNo
	 */
	public String getTaxRegNo() {
		return taxRegNo;
	}

	/**
	 * @param taxRegNo the taxRegNo to set
	 */
	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	/**
	 * @return the isTaxRegNo
	 */
	public int getIsTaxRegNo() {
		return isTaxRegNo;
	}

	/**
	 * @param isTaxRegNo the isTaxRegNo to set
	 */
	public void setIsTaxRegNo(int isTaxRegNo) {
		this.isTaxRegNo = isTaxRegNo;
	}

	/**
	 * @return the vatPer
	 */
	public double getVatPer() {
		return vatPer;
	}

	/**
	 * @param vatPer the vatPer to set
	 */
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}

	/**
	 * @return the taxPer
	 */
	public double getTaxPer() {
		return taxPer;
	}

	/**
	 * @param taxPer the taxPer to set
	 */
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}

	/**
	 * @return the productTypeId
	 */
	public int getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the dlLicenceNo
	 */
	public String getDlLicenceNo() {
		return dlLicenceNo;
	}

	/**
	 * @param dlLicenceNo the dlLicenceNo to set
	 */
	public void setDlLicenceNo(String dlLicenceNo) {
		this.dlLicenceNo = dlLicenceNo;
	}

	/**
	 * @return the dlRegistrationDate
	 */
	public String getDlRegistrationDate() {
		return dlRegistrationDate;
	}

	/**
	 * @param dlRegistrationDate the dlRegistrationDate to set
	 */
	public void setDlRegistrationDate(String dlRegistrationDate) {
		this.dlRegistrationDate = dlRegistrationDate;
	}

	/**
	 * @return the dlExpiryDate
	 */
	public String getDlExpiryDate() {
		return dlExpiryDate;
	}

	/**
	 * @param dlExpiryDate the dlExpiryDate to set
	 */
	public void setDlExpiryDate(String dlExpiryDate) {
		this.dlExpiryDate = dlExpiryDate;
	}

	/**
	 * @return the stateLicenceNo
	 */
	public String getStateLicenceNo() {
		return stateLicenceNo;
	}

	/**
	 * @param stateLicenceNo the stateLicenceNo to set
	 */
	public void setStateLicenceNo(String stateLicenceNo) {
		this.stateLicenceNo = stateLicenceNo;
	}

	/**
	 * @return the stateRegistrationDate
	 */
	public String getStateRegistrationDate() {
		return stateRegistrationDate;
	}

	/**
	 * @param stateRegistrationDate the stateRegistrationDate to set
	 */
	public void setStateRegistrationDate(String stateRegistrationDate) {
		this.stateRegistrationDate = stateRegistrationDate;
	}

	/**
	 * @return the stateExpiryDate
	 */
	public String getStateExpiryDate() {
		return stateExpiryDate;
	}

	/**
	 * @param stateExpiryDate the stateExpiryDate to set
	 */
	public void setStateExpiryDate(String stateExpiryDate) {
		this.stateExpiryDate = stateExpiryDate;
	}

	/**
	 * @return the storeCode
	 */
	public String getStoreCode() {
		return storeCode;
	}

	/**
	 * @param storeCode the storeCode to set
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the taxRegNoText
	 */
	public String getTaxRegNoText() {
		return taxRegNoText;
	}

	/**
	 * @param taxRegNoText the taxRegNoText to set
	 */
	public void setTaxRegNoText(String taxRegNoText) {
		this.taxRegNoText = taxRegNoText;
	}

	/**
	 * @return the isExclusive
	 */
	public int getIsExclusive() {
		return isExclusive;
	}

	/**
	 * @param isExclusive the isExclusive to set
	 */
	public void setIsExclusive(int isExclusive) {
		this.isExclusive = isExclusive;
	}

	/**
	 * @return the isMailEnable
	 */
	public int getIsMailEnable() {
		return isMailEnable;
	}

	/**
	 * @param isMailEnable the isMailEnable to set
	 */
	public void setIsMailEnable(int isMailEnable) {
		this.isMailEnable = isMailEnable;
	}

	/**
	 * @return the mailPassword
	 */
	public String getMailPassword() {
		return mailPassword;
	}

	/**
	 * @param mailPassword the mailPassword to set
	 */
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	/**
	 * @return the mailPort
	 */
	public String getMailPort() {
		return mailPort;
	}

	/**
	 * @param mailPort the mailPort to set
	 */
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}

	/**
	 * @return the mailSmtp
	 */
	public String getMailSmtp() {
		return mailSmtp;
	}

	/**
	 * @param mailSmtp the mailSmtp to set
	 */
	public void setMailSmtp(String mailSmtp) {
		this.mailSmtp = mailSmtp;
	}

	/**
	 * @return the isEsi
	 */
	public int getIsEsi() {
		return isEsi;
	}

	/**
	 * @param isEsi the isEsi to set
	 */
	public void setIsEsi(int isEsi) {
		this.isEsi = isEsi;
	}

	/**
	 * @return the currency_code
	 */
	public String getCurrency_code() {
		return currency_code;
	}

	/**
	 * @param currency_code the currency_code to set
	 */
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	/**
	 * @return the currency_desc
	 */
	public String getCurrency_desc() {
		return currency_desc;
	}

	/**
	 * @param currency_desc the currency_desc to set
	 */
	public void setCurrency_desc(String currency_desc) {
		this.currency_desc = currency_desc;
	}

	/**
	 * @return the is_account
	 */
	public int getIs_account() {
		return is_account;
	}

	/**
	 * @param is_account the is_account to set
	 */
	public void setIs_account(int is_account) {
		this.is_account = is_account;
	}

	/**
	 * @return the isAdmin
	 */
	public int getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginInfoByUserDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", userCode="
				+ userCode + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone + ", email=" + email
				+ ", companyId=" + companyId + ", storeId=" + storeId + ", isCurrent=" + isCurrent + ", isActive="
				+ isActive + ", isLocked=" + isLocked + ", finyrId=" + finyrId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", loginDate=" + loginDate + ", message=" + message + ", lang=" + lang
				+ ", description=" + description + ", finyrCode=" + finyrCode + ", taxRegNo=" + taxRegNo
				+ ", isTaxRegNo=" + isTaxRegNo + ", vatPer=" + vatPer + ", taxPer=" + taxPer + ", productTypeId="
				+ productTypeId + ", dlLicenceNo=" + dlLicenceNo + ", dlRegistrationDate=" + dlRegistrationDate
				+ ", dlExpiryDate=" + dlExpiryDate + ", stateLicenceNo=" + stateLicenceNo + ", stateRegistrationDate="
				+ stateRegistrationDate + ", stateExpiryDate=" + stateExpiryDate + ", storeCode=" + storeCode
				+ ", locationId=" + locationId + ", locationName=" + locationName + ", versionName=" + versionName
				+ ", taxRegNoText=" + taxRegNoText + ", isExclusive=" + isExclusive + ", isMailEnable=" + isMailEnable
				+ ", mailPassword=" + mailPassword + ", mailPort=" + mailPort + ", mailSmtp=" + mailSmtp + ", isEsi="
				+ isEsi + ", currency_code=" + currency_code + ", currency_desc=" + currency_desc + ", is_account="
				+ is_account + ", isAdmin=" + isAdmin + "]";
	}
    
 
	
}
