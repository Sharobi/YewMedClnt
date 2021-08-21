package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 * 
 */

public class CommonResultSetMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int companyId;
	private int storeId;
	private String name;
	private String lang;
	private int itemId;
	private String itemName;
	private String brandName;
	private String contentName;
	private String manufacturerName;
	private String packUnitName;
	private String looseUnitName;
	private String rackName;
	private int conv;
	private String groupName;
	private String schdName;
	private double price;
	private String queryCondn;
	private int purInvId;
	private int finYrId;
	private String startDate;
	private String endDate;
	private String invoiceNo;
	private String purOrderNo;
	private String distributorName;
	private int distributorId;
	private String upToDate;
	private int noOfExpiryMonth;
	private int saleId;
	private int custId;
	private String custName;
	private String custPh;
	private int status;
	private String doctorName;
	private String doctorPh;
	private String asOnDate;
	private String searchCriteria; // use for web
	private int deletedBy;
	private int createdBy;
	private int manuId;
	private int contentId;
	private String batchNo;
	private String expiryDateFormat;
	private double mrp;
	private int saleReturnId;
	private int noOfMonthBefore;
	private String manufacturerCode;
	private int purchaseReturnId;
	private int paymentId;
	private String paymentDate;
	private String finyrCode; // new used for search in reprint cash memo
	private int expiryId;
	private int taxId;
	private int isGroup;
	private String taxName;
	private int purTaxId;
	private String purTaxName;
	private int salTaxId;
	private String salTaxName;
	private double taxPer;
	private int noOfRows;
	private String frmDate;
	private String toDate;
	private String sku;
	private String hsnCode;
	private String retType;
	private int purchaseOrderId;
	private String invDate;
	private String poGenType;
	private String noteLineOne;
	private String noteLineTwo;
	private int isRePrint;
	private int printCount;
	private String remarks;
	private String barCode;
	private int lastSaleDays;
	private int comingPurchaseDays;
	private int noOfDays;
	private String billNo;

	private int countryId;
	private int stateId;
	private int cityId;
	private int zoneId;

	private String qs;
	private String qryCondition;

	private String groupCode;
	private int accountID;
	private int referenceID;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
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
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the contentName
	 */
	public String getContentName() {
		return contentName;
	}

	/**
	 * @param contentName
	 *            the contentName to set
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName
	 *            the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	/**
	 * @return the packUnitName
	 */
	public String getPackUnitName() {
		return packUnitName;
	}

	/**
	 * @param packUnitName
	 *            the packUnitName to set
	 */
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}

	/**
	 * @return the looseUnitName
	 */
	public String getLooseUnitName() {
		return looseUnitName;
	}

	/**
	 * @param looseUnitName
	 *            the looseUnitName to set
	 */
	public void setLooseUnitName(String looseUnitName) {
		this.looseUnitName = looseUnitName;
	}

	/**
	 * @return the rackName
	 */
	public String getRackName() {
		return rackName;
	}

	/**
	 * @param rackName
	 *            the rackName to set
	 */
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	/**
	 * @return the conv
	 */
	public int getConv() {
		return conv;
	}

	/**
	 * @param conv
	 *            the conv to set
	 */
	public void setConv(int conv) {
		this.conv = conv;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the schdName
	 */
	public String getSchdName() {
		return schdName;
	}

	/**
	 * @param schdName
	 *            the schdName to set
	 */
	public void setSchdName(String schdName) {
		this.schdName = schdName;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the queryCondn
	 */
	public String getQueryCondn() {
		return queryCondn;
	}

	/**
	 * @param queryCondn
	 *            the queryCondn to set
	 */
	public void setQueryCondn(String queryCondn) {
		this.queryCondn = queryCondn;
	}

	/**
	 * @return the purInvId
	 */
	public int getPurInvId() {
		return purInvId;
	}

	/**
	 * @param purInvId
	 *            the purInvId to set
	 */
	public void setPurInvId(int purInvId) {
		this.purInvId = purInvId;
	}

	/**
	 * @return the finYrId
	 */
	public int getFinYrId() {
		return finYrId;
	}

	/**
	 * @param finYrId
	 *            the finYrId to set
	 */
	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
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
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the purOrderNo
	 */
	public String getPurOrderNo() {
		return purOrderNo;
	}

	/**
	 * @param purOrderNo
	 *            the purOrderNo to set
	 */
	public void setPurOrderNo(String purOrderNo) {
		this.purOrderNo = purOrderNo;
	}

	/**
	 * @return the distributorName
	 */
	public String getDistributorName() {
		return distributorName;
	}

	/**
	 * @param distributorName
	 *            the distributorName to set
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	/**
	 * @return the distributorId
	 */
	public int getDistributorId() {
		return distributorId;
	}

	/**
	 * @param distributorId
	 *            the distributorId to set
	 */
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	/**
	 * @return the upToDate
	 */
	public String getUpToDate() {
		return upToDate;
	}

	/**
	 * @param upToDate
	 *            the upToDate to set
	 */
	public void setUpToDate(String upToDate) {
		this.upToDate = upToDate;
	}

	/**
	 * @return the noOfExpiryMonth
	 */
	public int getNoOfExpiryMonth() {
		return noOfExpiryMonth;
	}

	/**
	 * @param noOfExpiryMonth
	 *            the noOfExpiryMonth to set
	 */
	public void setNoOfExpiryMonth(int noOfExpiryMonth) {
		this.noOfExpiryMonth = noOfExpiryMonth;
	}

	/**
	 * @return the saleId
	 */
	public int getSaleId() {
		return saleId;
	}

	/**
	 * @param saleId
	 *            the saleId to set
	 */
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName
	 *            the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the custPh
	 */
	public String getCustPh() {
		return custPh;
	}

	/**
	 * @param custPh
	 *            the custPh to set
	 */
	public void setCustPh(String custPh) {
		this.custPh = custPh;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName
	 *            the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the doctorPh
	 */
	public String getDoctorPh() {
		return doctorPh;
	}

	/**
	 * @param doctorPh
	 *            the doctorPh to set
	 */
	public void setDoctorPh(String doctorPh) {
		this.doctorPh = doctorPh;
	}

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	/**
	 * @return the searchCriteria
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param searchCriteria
	 *            the searchCriteria to set
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the deletedBy
	 */
	public int getDeletedBy() {
		return deletedBy;
	}

	/**
	 * @param deletedBy
	 *            the deletedBy to set
	 */
	public void setDeletedBy(int deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the manuId
	 */
	public int getManuId() {
		return manuId;
	}

	/**
	 * @param manuId
	 *            the manuId to set
	 */
	public void setManuId(int manuId) {
		this.manuId = manuId;
	}

	/**
	 * @return the contentId
	 */
	public int getContentId() {
		return contentId;
	}

	/**
	 * @param contentId
	 *            the contentId to set
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo
	 *            the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the expiryDateFormat
	 */
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	/**
	 * @param expiryDateFormat
	 *            the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}

	/**
	 * @param mrp
	 *            the mrp to set
	 */
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	/**
	 * @return the saleReturnId
	 */
	public int getSaleReturnId() {
		return saleReturnId;
	}

	/**
	 * @param saleReturnId
	 *            the saleReturnId to set
	 */
	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}

	/**
	 * @return the noOfMonthBefore
	 */
	public int getNoOfMonthBefore() {
		return noOfMonthBefore;
	}

	/**
	 * @param noOfMonthBefore
	 *            the noOfMonthBefore to set
	 */
	public void setNoOfMonthBefore(int noOfMonthBefore) {
		this.noOfMonthBefore = noOfMonthBefore;
	}

	/**
	 * @return the manufacturerCode
	 */
	public String getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * @param manufacturerCode
	 *            the manufacturerCode to set
	 */
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	/**
	 * @return the purchaseReturnId
	 */
	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	/**
	 * @param purchaseReturnId
	 *            the purchaseReturnId to set
	 */
	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}

	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId
	 *            the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the finyrCode
	 */
	public String getFinyrCode() {
		return finyrCode;
	}

	/**
	 * @param finyrCode
	 *            the finyrCode to set
	 */
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	/**
	 * @return the expiryId
	 */
	public int getExpiryId() {
		return expiryId;
	}

	/**
	 * @param expiryId
	 *            the expiryId to set
	 */
	public void setExpiryId(int expiryId) {
		this.expiryId = expiryId;
	}

	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 *            the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the isGroup
	 */
	public int getIsGroup() {
		return isGroup;
	}

	/**
	 * @param isGroup
	 *            the isGroup to set
	 */
	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}

	/**
	 * @return the taxName
	 */
	public String getTaxName() {
		return taxName;
	}

	/**
	 * @param taxName
	 *            the taxName to set
	 */
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	/**
	 * @return the purTaxId
	 */
	public int getPurTaxId() {
		return purTaxId;
	}

	/**
	 * @param purTaxId
	 *            the purTaxId to set
	 */
	public void setPurTaxId(int purTaxId) {
		this.purTaxId = purTaxId;
	}

	/**
	 * @return the purTaxName
	 */
	public String getPurTaxName() {
		return purTaxName;
	}

	/**
	 * @param purTaxName
	 *            the purTaxName to set
	 */
	public void setPurTaxName(String purTaxName) {
		this.purTaxName = purTaxName;
	}

	/**
	 * @return the salTaxId
	 */
	public int getSalTaxId() {
		return salTaxId;
	}

	/**
	 * @param salTaxId
	 *            the salTaxId to set
	 */
	public void setSalTaxId(int salTaxId) {
		this.salTaxId = salTaxId;
	}

	/**
	 * @return the salTaxName
	 */
	public String getSalTaxName() {
		return salTaxName;
	}

	/**
	 * @param salTaxName
	 *            the salTaxName to set
	 */
	public void setSalTaxName(String salTaxName) {
		this.salTaxName = salTaxName;
	}

	/**
	 * @return the taxPer
	 */
	public double getTaxPer() {
		return taxPer;
	}

	/**
	 * @param taxPer
	 *            the taxPer to set
	 */
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}

	/**
	 * @return the noOfRows
	 */
	public int getNoOfRows() {
		return noOfRows;
	}

	/**
	 * @param noOfRows
	 *            the noOfRows to set
	 */
	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}

	/**
	 * @return the frmDate
	 */
	public String getFrmDate() {
		return frmDate;
	}

	/**
	 * @param frmDate
	 *            the frmDate to set
	 */
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the hsnCode
	 */
	public String getHsnCode() {
		return hsnCode;
	}

	/**
	 * @param hsnCode
	 *            the hsnCode to set
	 */
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	/**
	 * @return the retType
	 */
	public String getRetType() {
		return retType;
	}

	/**
	 * @param retType
	 *            the retType to set
	 */
	public void setRetType(String retType) {
		this.retType = retType;
	}

	/**
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * @param purchaseOrderId
	 *            the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * @return the invDate
	 */
	public String getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate
	 *            the invDate to set
	 */
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	/**
	 * @return the poGenType
	 */
	public String getPoGenType() {
		return poGenType;
	}

	/**
	 * @param poGenType
	 *            the poGenType to set
	 */
	public void setPoGenType(String poGenType) {
		this.poGenType = poGenType;
	}

	/**
	 * @return the noteLineOne
	 */
	public String getNoteLineOne() {
		return noteLineOne;
	}

	/**
	 * @param noteLineOne
	 *            the noteLineOne to set
	 */
	public void setNoteLineOne(String noteLineOne) {
		this.noteLineOne = noteLineOne;
	}

	/**
	 * @return the noteLineTwo
	 */
	public String getNoteLineTwo() {
		return noteLineTwo;
	}

	/**
	 * @param noteLineTwo
	 *            the noteLineTwo to set
	 */
	public void setNoteLineTwo(String noteLineTwo) {
		this.noteLineTwo = noteLineTwo;
	}

	/**
	 * @return the isRePrint
	 */
	public int getIsRePrint() {
		return isRePrint;
	}

	/**
	 * @param isRePrint
	 *            the isRePrint to set
	 */
	public void setIsRePrint(int isRePrint) {
		this.isRePrint = isRePrint;
	}

	/**
	 * @return the printCount
	 */
	public int getPrintCount() {
		return printCount;
	}

	/**
	 * @param printCount
	 *            the printCount to set
	 */
	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode
	 *            the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @return the lastSaleDays
	 */
	public int getLastSaleDays() {
		return lastSaleDays;
	}

	/**
	 * @param lastSaleDays
	 *            the lastSaleDays to set
	 */
	public void setLastSaleDays(int lastSaleDays) {
		this.lastSaleDays = lastSaleDays;
	}

	/**
	 * @return the comingPurchaseDays
	 */
	public int getComingPurchaseDays() {
		return comingPurchaseDays;
	}

	/**
	 * @param comingPurchaseDays
	 *            the comingPurchaseDays to set
	 */
	public void setComingPurchaseDays(int comingPurchaseDays) {
		this.comingPurchaseDays = comingPurchaseDays;
	}

	/**
	 * @return the noOfDays
	 */
	public int getNoOfDays() {
		return noOfDays;
	}

	/**
	 * @param noOfDays
	 *            the noOfDays to set
	 */
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo
	 *            the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the zoneId
	 */
	public int getZoneId() {
		return zoneId;
	}

	/**
	 * @param zoneId
	 *            the zoneId to set
	 */
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * @return the qs
	 */
	public String getQs() {
		return qs;
	}

	/**
	 * @param qs
	 *            the qs to set
	 */
	public void setQs(String qs) {
		this.qs = qs;
	}

	/**
	 * @return the qryCondition
	 */
	public String getQryCondition() {
		return qryCondition;
	}

	/**
	 * @param qryCondition
	 *            the qryCondition to set
	 */
	public void setQryCondition(String qryCondition) {
		this.qryCondition = qryCondition;
	}

	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode
	 *            the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the referenceID
	 */
	public int getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID
	 *            the referenceID to set
	 */
	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommonResultSetMapper [id=" + id + ", companyId=" + companyId + ", storeId=" + storeId + ", name="
				+ name + ", lang=" + lang + ", itemId=" + itemId + ", itemName=" + itemName + ", brandName=" + brandName
				+ ", contentName=" + contentName + ", manufacturerName=" + manufacturerName + ", packUnitName="
				+ packUnitName + ", looseUnitName=" + looseUnitName + ", rackName=" + rackName + ", conv=" + conv
				+ ", groupName=" + groupName + ", schdName=" + schdName + ", price=" + price + ", queryCondn="
				+ queryCondn + ", purInvId=" + purInvId + ", finYrId=" + finYrId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", invoiceNo=" + invoiceNo + ", purOrderNo=" + purOrderNo
				+ ", distributorName=" + distributorName + ", distributorId=" + distributorId + ", upToDate=" + upToDate
				+ ", noOfExpiryMonth=" + noOfExpiryMonth + ", saleId=" + saleId + ", custId=" + custId + ", custName="
				+ custName + ", custPh=" + custPh + ", status=" + status + ", doctorName=" + doctorName + ", doctorPh="
				+ doctorPh + ", asOnDate=" + asOnDate + ", searchCriteria=" + searchCriteria + ", deletedBy="
				+ deletedBy + ", createdBy=" + createdBy + ", manuId=" + manuId + ", contentId=" + contentId
				+ ", batchNo=" + batchNo + ", expiryDateFormat=" + expiryDateFormat + ", mrp=" + mrp + ", saleReturnId="
				+ saleReturnId + ", noOfMonthBefore=" + noOfMonthBefore + ", manufacturerCode=" + manufacturerCode
				+ ", purchaseReturnId=" + purchaseReturnId + ", paymentId=" + paymentId + ", paymentDate=" + paymentDate
				+ ", finyrCode=" + finyrCode + ", expiryId=" + expiryId + ", taxId=" + taxId + ", isGroup=" + isGroup
				+ ", taxName=" + taxName + ", purTaxId=" + purTaxId + ", purTaxName=" + purTaxName + ", salTaxId="
				+ salTaxId + ", salTaxName=" + salTaxName + ", taxPer=" + taxPer + ", noOfRows=" + noOfRows
				+ ", frmDate=" + frmDate + ", toDate=" + toDate + ", sku=" + sku + ", hsnCode=" + hsnCode + ", retType="
				+ retType + ", purchaseOrderId=" + purchaseOrderId + ", invDate=" + invDate + ", poGenType=" + poGenType
				+ ", noteLineOne=" + noteLineOne + ", noteLineTwo=" + noteLineTwo + ", isRePrint=" + isRePrint
				+ ", printCount=" + printCount + ", remarks=" + remarks + ", barCode=" + barCode + ", lastSaleDays="
				+ lastSaleDays + ", comingPurchaseDays=" + comingPurchaseDays + ", noOfDays=" + noOfDays + ", billNo="
				+ billNo + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId + ", zoneId="
				+ zoneId + ", qs=" + qs + ", qryCondition=" + qryCondition + ", groupCode=" + groupCode + ", accountID="
				+ accountID + ", referenceID=" + referenceID + "]";
	}

}
