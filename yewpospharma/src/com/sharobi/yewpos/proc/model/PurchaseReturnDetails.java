/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class PurchaseReturnDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int purchaseReturnId;
	private String invNo;
	private String invDate;
	private int itemId;
	private String batchNo;
	private Date expiryDate;
	private int packUnitId;
	private int packQty;
	private int conversion;
	private int looseQty;
	private double freeQty;
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
	private int purchaseId;
	private String purchaseInvNo;
	private int storeId;
	private int companyId;
	private String lang;
	private String expiryDateFormat;
	private int grpId;
	private String grpName;
	private String itemUniqueKey;
	private double itemLotAdjAmount; 
	
	private int taxId;    
	private String taxName;
	private double itemTaxAmount;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
	private String taxMode; 
    private String sku;
    private String hsnCode;
    private int reasonId;
    
    public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public double getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(double itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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

	public int getIsGroupTax() {
		return isGroupTax;
	}

	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}

	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
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

	public double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
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

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
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
	
	public String getPurchaseInvNo() {
		return purchaseInvNo;
	}

	public void setPurchaseInvNo(String purchaseInvNo) {
		this.purchaseInvNo = purchaseInvNo;
	}
	
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	@Override
	public String toString() {
		return "PurchaseReturnDetails [id=" + id + ", purchaseReturnId=" + purchaseReturnId + ", invNo=" + invNo
				+ ", invDate=" + invDate + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate
				+ ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty="
				+ looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount
				+ ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer
				+ ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", finyrId="
				+ finyrId + ", purchaseId=" + purchaseId + ", purchaseInvNo=" + purchaseInvNo + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", lang=" + lang + ", expiryDateFormat=" + expiryDateFormat + ", grpId="
				+ grpId + ", grpName=" + grpName + ", itemUniqueKey=" + itemUniqueKey + ", itemLotAdjAmount="
				+ itemLotAdjAmount + ", taxId=" + taxId + ", taxName=" + taxName + ", itemTaxAmount=" + itemTaxAmount
				+ ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax
				+ ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", reasonId=" + reasonId + "]";
	}	
	
	
}
