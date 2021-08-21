package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 */
public class PurchaseDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int purchaseId;
	private String invNo;
	private String invDate;
	private int itemId;
	private String batchNo;
	private String expiryDate;
	private int packUnitId;
	private int packQty;
	private int conversion;
	private int looseQty;
	private double mrp;
	private double rate;
	private double amount;
	private double edPer;
	private double ed;
	private double taxPer;
	private double tax;
	private double vatPer;
	private double vat;
	private double discPer;
	private double disc;
	private double totAmount;
	private int finyrId;
	private int storeId;
	private int companyId;
	private double freeQty;
	private int isDeleted;
	private int createdBy;
	private String createdDate;
	private int updatedBy;
	private String updatedDate;
	private String lang;
	private String qryCondition;
	private String packUnitName;
	private String itemName;
	private int grpId;  
    private String grpName;  
    private int schdId; 
    private String schdName;  
    private int manuId;  
    private String manuName;
    private String manuCode;
    private String expiryDateFormat;
    private String itemUniqueKey;
    private double itemLotAdjAmount;    
    private int taxId;
    private double taxPercentage;
    private double taxAmount;
    private String taxName;
    private double taxableRate;
    private String taxMode;
    private int isGroupTax;
    private int isTaxOnMrp;
    private String sku;
    private String hsnCode;
    private double purchaseNetAmount;
    private double saleRate;
    private int purchaseOrderId;
    private String purchaseOrderInvNo;
    
	/**
	 * @return the purchaseOrderInvNo
	 */
	public String getPurchaseOrderInvNo() {
		return purchaseOrderInvNo;
	}
	/**
	 * @param purchaseOrderInvNo the purchaseOrderInvNo to set
	 */
	public void setPurchaseOrderInvNo(String purchaseOrderInvNo) {
		this.purchaseOrderInvNo = purchaseOrderInvNo;
	}
	/**
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	/**
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public double getPurchaseNetAmount() {
		return purchaseNetAmount;
	}
	public void setPurchaseNetAmount(double purchaseNetAmount) {
		this.purchaseNetAmount = purchaseNetAmount;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}
	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
	}
	public String getManuCode() {
		return manuCode;
	}
	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getPackUnitId() {
		return packUnitId;
	}
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}
	public int getPackQty() {
		return packQty;
	}
	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}
	public int getConversion() {
		return conversion;
	}
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}
	public int getLooseQty() {
		return looseQty;
	}
	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getEdPer() {
		return edPer;
	}
	public void setEdPer(double edPer) {
		this.edPer = edPer;
	}
	public double getEd() {
		return ed;
	}
	public void setEd(double ed) {
		this.ed = ed;
	}
	public double getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getVatPer() {
		return vatPer;
	}
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}
	public double getDiscPer() {
		return discPer;
	}
	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}
	public double getDisc() {
		return disc;
	}
	public void setDisc(double disc) {
		this.disc = disc;
	}
	public double getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
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
	public double getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getQryCondition() {
		return qryCondition;
	}
	public void setQryCondition(String qryCondition) {
		this.qryCondition = qryCondition;
	}
	public String getPackUnitName() {
		return packUnitName;
	}
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getGrpId() {
		return grpId;
	}
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public int getSchdId() {
		return schdId;
	}
	public void setSchdId(int schdId) {
		this.schdId = schdId;
	}
	public String getSchdName() {
		return schdName;
	}
	public void setSchdName(String schdName) {
		this.schdName = schdName;
	}
	public int getManuId() {
		return manuId;
	}
	public void setManuId(int manuId) {
		this.manuId = manuId;
	}
	public String getManuName() {
		return manuName;
	}
	public void setManuName(String manuName) {
		this.manuName = manuName;
	}
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}
	
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}
	
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public double getTaxableRate() {
		return taxableRate;
	}
	public void setTaxableRate(double taxableRate) {
		this.taxableRate = taxableRate;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	public int getIsGroupTax() {
		return isGroupTax;
	}
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}
	
	public int getIsTaxOnMrp() {
		return isTaxOnMrp;
	}
	public void setIsTaxOnMrp(int isTaxOnMrp) {
		this.isTaxOnMrp = isTaxOnMrp;
	}
	
	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseDetails [id=" + id + ", purchaseId=" + purchaseId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packUnitId="
				+ packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty
				+ ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed
				+ ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer
				+ ", disc=" + disc + ", totAmount=" + totAmount + ", finyrId=" + finyrId + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", freeQty=" + freeQty + ", isDeleted=" + isDeleted + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", lang=" + lang + ", qryCondition=" + qryCondition + ", packUnitName=" + packUnitName
				+ ", itemName=" + itemName + ", grpId=" + grpId + ", grpName=" + grpName + ", schdId=" + schdId
				+ ", schdName=" + schdName + ", manuId=" + manuId + ", manuName=" + manuName + ", manuCode=" + manuCode
				+ ", expiryDateFormat=" + expiryDateFormat + ", itemUniqueKey=" + itemUniqueKey + ", itemLotAdjAmount="
				+ itemLotAdjAmount + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + ", taxAmount="
				+ taxAmount + ", taxName=" + taxName + ", taxableRate=" + taxableRate + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", isTaxOnMrp=" + isTaxOnMrp + ", sku=" + sku + ", hsnCode=" + hsnCode
				+ ", purchaseNetAmount=" + purchaseNetAmount + ", saleRate=" + saleRate + ", purchaseOrderId="
				+ purchaseOrderId + ", purchaseOrderInvNo=" + purchaseOrderInvNo + "]";
	}
	
}
