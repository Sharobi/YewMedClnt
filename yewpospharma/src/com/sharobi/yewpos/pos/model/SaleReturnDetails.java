/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip Jana
 *
 * 
 */
public class SaleReturnDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int saleReturnId;
    private String invNo;
    private Date invDate;
    private int itemId;
    private String batchNo;
    private Date expiryDate;
    private int packUnitId;
    private int packQty;
    private int conversion;
    private int looseUnitId;
    private int looseQty;
    private double mrp;
    private double mrpPerUnit;
    private double ratePerUnit;
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
    private int saleId;
    private String saleBillNo;
    private String saleInvNo;
    private int storeId;
    private int companyId;
    private String lang;
    private int createdBy;
    private String expiryDateFormat;
	private double adjAmount;
	private double specialDiscPer;
	private double specialDiscAmount;
	private int taxId;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private int reasonId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSaleReturnId() {
		return saleReturnId;
	}
	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
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
	public int getLooseUnitId() {
		return looseUnitId;
	}
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
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
	public double getMrpPerUnit() {
		return mrpPerUnit;
	}
	public void setMrpPerUnit(double mrpPerUnit) {
		this.mrpPerUnit = mrpPerUnit;
	}
	public double getRatePerUnit() {
		return ratePerUnit;
	}
	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
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
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getSaleBillNo() {
		return saleBillNo;
	}
	public void setSaleBillNo(String saleBillNo) {
		this.saleBillNo = saleBillNo;
	}
	public String getSaleInvNo() {
		return saleInvNo;
	}
	public void setSaleInvNo(String saleInvNo) {
		this.saleInvNo = saleInvNo;
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
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}
	
	public double getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}
	public double getSpecialDiscPer() {
		return specialDiscPer;
	}
	public void setSpecialDiscPer(double specialDiscPer) {
		this.specialDiscPer = specialDiscPer;
	}
	public double getSpecialDiscAmount() {
		return specialDiscAmount;
	}
	public void setSpecialDiscAmount(double specialDiscAmount) {
		this.specialDiscAmount = specialDiscAmount;
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
	public int getReasonId() {
		return reasonId;
	}
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	@Override
	public String toString() {
		return "SaleReturnDetails [id=" + id + ", saleReturnId=" + saleReturnId + ", invNo=" + invNo + ", invDate=" + invDate + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseUnitId=" + looseUnitId + ", looseQty=" + looseQty + ", mrp=" + mrp + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", finyrId=" + finyrId + ", saleId=" + saleId + ", saleBillNo=" + saleBillNo + ", saleInvNo=" + saleInvNo + ", storeId=" + storeId + ", companyId=" + companyId + ", lang=" + lang + ", createdBy=" + createdBy + ", expiryDateFormat=" + expiryDateFormat + ", adjAmount=" + adjAmount + ", specialDiscPer=" + specialDiscPer
				+ ", specialDiscAmount=" + specialDiscAmount + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", reasonId=" + reasonId + "]";
	}
}
