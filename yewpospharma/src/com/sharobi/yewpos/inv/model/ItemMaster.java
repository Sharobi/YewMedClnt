package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 * 
 */
public class ItemMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String code;

	private int groupId;

	private int categoryId;

	private int subCategoryId;

	private int scheduleId;

	private int contentId;

	private int brandId;

	private int manufacturerId;

	private Date entryDate;

	private double vat;

	private int isOnMrp;

	private int packUnitId;

	private int conversion;

	private int looseUnitId;

	private String storage;

	private String care;

	private int reorderLevel;

	private int reorderLevelUnitId;

	private double price;

	private int isTaxable;

	private String note;

	private int companyId;

	private int isDeleted;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;

	private int rackId;

	private GroupMaster groupMaster;

	private CategoryMaster categoryMaster;

	private SubCategoryMaster subCategoryMaster;

	private ScheduleMaster scheduleMaster;

	private ContentMaster contentMaster;

	private BrandMaster brandMaster;

	private ManufacturerMaster manufacturerMaster;

	private UnitMaster packUnit;

	private UnitMaster looseUnit;

	private UnitMaster reorderLevelUnit;
	
	private RackMaster rack;
	
	private double markup;
	
	private String strength;
	
	private String netContent;
	
	private String lang;
	private int purchaseTaxId;
    private double purchaseTaxPercentage;
    private int saleTaxId;
    private double saleTaxPercentage;
    private String sku;
    private double discount;
    private double maxDiscountLimit;
    private int isDiscount;
    private TaxMaster saleTax;
    private TaxMaster purchaseTax;
    private String hsnCode;	
    private int isLooseSale;    
    

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}


	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}


	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}


	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	/**
	 * @return the subCategoryId
	 */
	public int getSubCategoryId() {
		return subCategoryId;
	}


	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}


	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}


	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}


	/**
	 * @return the contentId
	 */
	public int getContentId() {
		return contentId;
	}


	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}


	/**
	 * @return the brandId
	 */
	public int getBrandId() {
		return brandId;
	}


	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}


	/**
	 * @return the manufacturerId
	 */
	public int getManufacturerId() {
		return manufacturerId;
	}


	/**
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}


	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}


	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	/**
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}


	/**
	 * @param vat the vat to set
	 */
	public void setVat(double vat) {
		this.vat = vat;
	}


	/**
	 * @return the isOnMrp
	 */
	public int getIsOnMrp() {
		return isOnMrp;
	}


	/**
	 * @param isOnMrp the isOnMrp to set
	 */
	public void setIsOnMrp(int isOnMrp) {
		this.isOnMrp = isOnMrp;
	}


	/**
	 * @return the packUnitId
	 */
	public int getPackUnitId() {
		return packUnitId;
	}


	/**
	 * @param packUnitId the packUnitId to set
	 */
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}


	/**
	 * @return the conversion
	 */
	public int getConversion() {
		return conversion;
	}


	/**
	 * @param conversion the conversion to set
	 */
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}


	/**
	 * @return the looseUnitId
	 */
	public int getLooseUnitId() {
		return looseUnitId;
	}


	/**
	 * @param looseUnitId the looseUnitId to set
	 */
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}


	/**
	 * @return the storage
	 */
	public String getStorage() {
		return storage;
	}


	/**
	 * @param storage the storage to set
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}


	/**
	 * @return the care
	 */
	public String getCare() {
		return care;
	}


	/**
	 * @param care the care to set
	 */
	public void setCare(String care) {
		this.care = care;
	}


	/**
	 * @return the reorderLevel
	 */
	public int getReorderLevel() {
		return reorderLevel;
	}


	/**
	 * @param reorderLevel the reorderLevel to set
	 */
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}


	/**
	 * @return the reorderLevelUnitId
	 */
	public int getReorderLevelUnitId() {
		return reorderLevelUnitId;
	}


	/**
	 * @param reorderLevelUnitId the reorderLevelUnitId to set
	 */
	public void setReorderLevelUnitId(int reorderLevelUnitId) {
		this.reorderLevelUnitId = reorderLevelUnitId;
	}


	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	/**
	 * @return the isTaxable
	 */
	public int getIsTaxable() {
		return isTaxable;
	}


	/**
	 * @param isTaxable the isTaxable to set
	 */
	public void setIsTaxable(int isTaxable) {
		this.isTaxable = isTaxable;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
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
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}


	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}


	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}


	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	/**
	 * @return the rackId
	 */
	public int getRackId() {
		return rackId;
	}


	/**
	 * @param rackId the rackId to set
	 */
	public void setRackId(int rackId) {
		this.rackId = rackId;
	}


	/**
	 * @return the groupMaster
	 */
	public GroupMaster getGroupMaster() {
		return groupMaster;
	}


	/**
	 * @param groupMaster the groupMaster to set
	 */
	public void setGroupMaster(GroupMaster groupMaster) {
		this.groupMaster = groupMaster;
	}


	/**
	 * @return the categoryMaster
	 */
	public CategoryMaster getCategoryMaster() {
		return categoryMaster;
	}


	/**
	 * @param categoryMaster the categoryMaster to set
	 */
	public void setCategoryMaster(CategoryMaster categoryMaster) {
		this.categoryMaster = categoryMaster;
	}


	/**
	 * @return the subCategoryMaster
	 */
	public SubCategoryMaster getSubCategoryMaster() {
		return subCategoryMaster;
	}


	/**
	 * @param subCategoryMaster the subCategoryMaster to set
	 */
	public void setSubCategoryMaster(SubCategoryMaster subCategoryMaster) {
		this.subCategoryMaster = subCategoryMaster;
	}


	/**
	 * @return the scheduleMaster
	 */
	public ScheduleMaster getScheduleMaster() {
		return scheduleMaster;
	}


	/**
	 * @param scheduleMaster the scheduleMaster to set
	 */
	public void setScheduleMaster(ScheduleMaster scheduleMaster) {
		this.scheduleMaster = scheduleMaster;
	}


	/**
	 * @return the contentMaster
	 */
	public ContentMaster getContentMaster() {
		return contentMaster;
	}


	/**
	 * @param contentMaster the contentMaster to set
	 */
	public void setContentMaster(ContentMaster contentMaster) {
		this.contentMaster = contentMaster;
	}


	/**
	 * @return the brandMaster
	 */
	public BrandMaster getBrandMaster() {
		return brandMaster;
	}


	/**
	 * @param brandMaster the brandMaster to set
	 */
	public void setBrandMaster(BrandMaster brandMaster) {
		this.brandMaster = brandMaster;
	}


	/**
	 * @return the manufacturerMaster
	 */
	public ManufacturerMaster getManufacturerMaster() {
		return manufacturerMaster;
	}


	/**
	 * @param manufacturerMaster the manufacturerMaster to set
	 */
	public void setManufacturerMaster(ManufacturerMaster manufacturerMaster) {
		this.manufacturerMaster = manufacturerMaster;
	}


	/**
	 * @return the packUnit
	 */
	public UnitMaster getPackUnit() {
		return packUnit;
	}


	/**
	 * @param packUnit the packUnit to set
	 */
	public void setPackUnit(UnitMaster packUnit) {
		this.packUnit = packUnit;
	}


	/**
	 * @return the looseUnit
	 */
	public UnitMaster getLooseUnit() {
		return looseUnit;
	}


	/**
	 * @param looseUnit the looseUnit to set
	 */
	public void setLooseUnit(UnitMaster looseUnit) {
		this.looseUnit = looseUnit;
	}


	/**
	 * @return the reorderLevelUnit
	 */
	public UnitMaster getReorderLevelUnit() {
		return reorderLevelUnit;
	}


	/**
	 * @param reorderLevelUnit the reorderLevelUnit to set
	 */
	public void setReorderLevelUnit(UnitMaster reorderLevelUnit) {
		this.reorderLevelUnit = reorderLevelUnit;
	}


	/**
	 * @return the rack
	 */
	public RackMaster getRack() {
		return rack;
	}


	/**
	 * @param rack the rack to set
	 */
	public void setRack(RackMaster rack) {
		this.rack = rack;
	}


	/**
	 * @return the markup
	 */
	public double getMarkup() {
		return markup;
	}


	/**
	 * @param markup the markup to set
	 */
	public void setMarkup(double markup) {
		this.markup = markup;
	}


	/**
	 * @return the strength
	 */
	public String getStrength() {
		return strength;
	}


	/**
	 * @param strength the strength to set
	 */
	public void setStrength(String strength) {
		this.strength = strength;
	}


	/**
	 * @return the netContent
	 */
	public String getNetContent() {
		return netContent;
	}


	/**
	 * @param netContent the netContent to set
	 */
	public void setNetContent(String netContent) {
		this.netContent = netContent;
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
	 * @return the purchaseTaxId
	 */
	public int getPurchaseTaxId() {
		return purchaseTaxId;
	}


	/**
	 * @param purchaseTaxId the purchaseTaxId to set
	 */
	public void setPurchaseTaxId(int purchaseTaxId) {
		this.purchaseTaxId = purchaseTaxId;
	}


	/**
	 * @return the purchaseTaxPercentage
	 */
	public double getPurchaseTaxPercentage() {
		return purchaseTaxPercentage;
	}


	/**
	 * @param purchaseTaxPercentage the purchaseTaxPercentage to set
	 */
	public void setPurchaseTaxPercentage(double purchaseTaxPercentage) {
		this.purchaseTaxPercentage = purchaseTaxPercentage;
	}


	/**
	 * @return the saleTaxId
	 */
	public int getSaleTaxId() {
		return saleTaxId;
	}


	/**
	 * @param saleTaxId the saleTaxId to set
	 */
	public void setSaleTaxId(int saleTaxId) {
		this.saleTaxId = saleTaxId;
	}


	/**
	 * @return the saleTaxPercentage
	 */
	public double getSaleTaxPercentage() {
		return saleTaxPercentage;
	}


	/**
	 * @param saleTaxPercentage the saleTaxPercentage to set
	 */
	public void setSaleTaxPercentage(double saleTaxPercentage) {
		this.saleTaxPercentage = saleTaxPercentage;
	}


	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}


	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}


	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}


	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}


	/**
	 * @return the maxDiscountLimit
	 */
	public double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}


	/**
	 * @param maxDiscountLimit the maxDiscountLimit to set
	 */
	public void setMaxDiscountLimit(double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}


	/**
	 * @return the isDiscount
	 */
	public int getIsDiscount() {
		return isDiscount;
	}


	/**
	 * @param isDiscount the isDiscount to set
	 */
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}


	/**
	 * @return the saleTax
	 */
	public TaxMaster getSaleTax() {
		return saleTax;
	}


	/**
	 * @param saleTax the saleTax to set
	 */
	public void setSaleTax(TaxMaster saleTax) {
		this.saleTax = saleTax;
	}


	/**
	 * @return the purchaseTax
	 */
	public TaxMaster getPurchaseTax() {
		return purchaseTax;
	}


	/**
	 * @param purchaseTax the purchaseTax to set
	 */
	public void setPurchaseTax(TaxMaster purchaseTax) {
		this.purchaseTax = purchaseTax;
	}


	/**
	 * @return the hsnCode
	 */
	public String getHsnCode() {
		return hsnCode;
	}


	/**
	 * @param hsnCode the hsnCode to set
	 */
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}


	/**
	 * @return the isLooseSale
	 */
	public int getIsLooseSale() {
		return isLooseSale;
	}


	/**
	 * @param isLooseSale the isLooseSale to set
	 */
	public void setIsLooseSale(int isLooseSale) {
		this.isLooseSale = isLooseSale;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemMaster [id=" + id + ", name=" + name + ", code=" + code + ", groupId=" + groupId + ", categoryId="
				+ categoryId + ", subCategoryId=" + subCategoryId + ", scheduleId=" + scheduleId + ", contentId="
				+ contentId + ", brandId=" + brandId + ", manufacturerId=" + manufacturerId + ", entryDate=" + entryDate
				+ ", vat=" + vat + ", isOnMrp=" + isOnMrp + ", packUnitId=" + packUnitId + ", conversion=" + conversion
				+ ", looseUnitId=" + looseUnitId + ", storage=" + storage + ", care=" + care + ", reorderLevel="
				+ reorderLevel + ", reorderLevelUnitId=" + reorderLevelUnitId + ", price=" + price + ", isTaxable="
				+ isTaxable + ", note=" + note + ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", rackId=" + rackId + ", groupMaster=" + groupMaster
				+ ", categoryMaster=" + categoryMaster + ", subCategoryMaster=" + subCategoryMaster
				+ ", scheduleMaster=" + scheduleMaster + ", contentMaster=" + contentMaster + ", brandMaster="
				+ brandMaster + ", manufacturerMaster=" + manufacturerMaster + ", packUnit=" + packUnit + ", looseUnit="
				+ looseUnit + ", reorderLevelUnit=" + reorderLevelUnit + ", rack=" + rack + ", markup=" + markup
				+ ", strength=" + strength + ", netContent=" + netContent + ", lang=" + lang + ", purchaseTaxId="
				+ purchaseTaxId + ", purchaseTaxPercentage=" + purchaseTaxPercentage + ", saleTaxId=" + saleTaxId
				+ ", saleTaxPercentage=" + saleTaxPercentage + ", sku=" + sku + ", discount=" + discount
				+ ", maxDiscountLimit=" + maxDiscountLimit + ", isDiscount=" + isDiscount + ", saleTax=" + saleTax
				+ ", purchaseTax=" + purchaseTax + ", hsnCode=" + hsnCode + ", isLooseSale=" + isLooseSale + "]";
	}
	

}
