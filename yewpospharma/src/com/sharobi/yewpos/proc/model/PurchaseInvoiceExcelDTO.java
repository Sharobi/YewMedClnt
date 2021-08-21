/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 20-Jul-2017
 */
public class PurchaseInvoiceExcelDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int excelUploadVendorId;
	private String excelUploadBillNo;
	private int excelUploadStartRow;
	private int excelUploadEndRow;
	private String excelUploadItemName;
	private String excelUploadPack;
	private String excelUploadBatch;
	private String excelUploadMfg;
	private String excelUploadExpdate;
	private String excelUploadMrp;
	private String excelUploadRate;
	private String excelUploadQty;
	private String excelUploadFree;
	private String excelUploadDiscPerc;
	private String excelUploadTaxPerc;
	private String excelUploadAmt;
	private int companyId;
	private int storeId;
	private int finYrId;
	private int createdBy;
	
	public int getExcelUploadVendorId() {
		return excelUploadVendorId;
	}
	public void setExcelUploadVendorId(int excelUploadVendorId) {
		this.excelUploadVendorId = excelUploadVendorId;
	}
	public String getExcelUploadBillNo() {
		return excelUploadBillNo;
	}
	public void setExcelUploadBillNo(String excelUploadBillNo) {
		this.excelUploadBillNo = excelUploadBillNo;
	}
	public int getExcelUploadStartRow() {
		return excelUploadStartRow;
	}
	public void setExcelUploadStartRow(int excelUploadStartRow) {
		this.excelUploadStartRow = excelUploadStartRow;
	}
	public int getExcelUploadEndRow() {
		return excelUploadEndRow;
	}
	public void setExcelUploadEndRow(int excelUploadEndRow) {
		this.excelUploadEndRow = excelUploadEndRow;
	}
	public String getExcelUploadItemName() {
		return excelUploadItemName;
	}
	public void setExcelUploadItemName(String excelUploadItemName) {
		this.excelUploadItemName = excelUploadItemName;
	}
	public String getExcelUploadPack() {
		return excelUploadPack;
	}
	public void setExcelUploadPack(String excelUploadPack) {
		this.excelUploadPack = excelUploadPack;
	}
	public String getExcelUploadBatch() {
		return excelUploadBatch;
	}
	public void setExcelUploadBatch(String excelUploadBatch) {
		this.excelUploadBatch = excelUploadBatch;
	}
	public String getExcelUploadMfg() {
		return excelUploadMfg;
	}
	public void setExcelUploadMfg(String excelUploadMfg) {
		this.excelUploadMfg = excelUploadMfg;
	}
	public String getExcelUploadExpdate() {
		return excelUploadExpdate;
	}
	public void setExcelUploadExpdate(String excelUploadExpdate) {
		this.excelUploadExpdate = excelUploadExpdate;
	}
	public String getExcelUploadMrp() {
		return excelUploadMrp;
	}
	public void setExcelUploadMrp(String excelUploadMrp) {
		this.excelUploadMrp = excelUploadMrp;
	}
	public String getExcelUploadRate() {
		return excelUploadRate;
	}
	public void setExcelUploadRate(String excelUploadRate) {
		this.excelUploadRate = excelUploadRate;
	}
	public String getExcelUploadQty() {
		return excelUploadQty;
	}
	public void setExcelUploadQty(String excelUploadQty) {
		this.excelUploadQty = excelUploadQty;
	}
	public String getExcelUploadFree() {
		return excelUploadFree;
	}
	public void setExcelUploadFree(String excelUploadFree) {
		this.excelUploadFree = excelUploadFree;
	}
	public String getExcelUploadDiscPerc() {
		return excelUploadDiscPerc;
	}
	public void setExcelUploadDiscPerc(String excelUploadDiscPerc) {
		this.excelUploadDiscPerc = excelUploadDiscPerc;
	}
	public String getExcelUploadTaxPerc() {
		return excelUploadTaxPerc;
	}
	public void setExcelUploadTaxPerc(String excelUploadTaxPerc) {
		this.excelUploadTaxPerc = excelUploadTaxPerc;
	}
	public String getExcelUploadAmt() {
		return excelUploadAmt;
	}
	public void setExcelUploadAmt(String excelUploadAmt) {
		this.excelUploadAmt = excelUploadAmt;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getFinYrId() {
		return finYrId;
	}
	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "PurchaseInvoiceExcelDTO [excelUploadVendorId=" + excelUploadVendorId + ", excelUploadBillNo=" + excelUploadBillNo + ", excelUploadStartRow=" + excelUploadStartRow + ", excelUploadEndRow=" + excelUploadEndRow + ", excelUploadItemName=" + excelUploadItemName + ", excelUploadPack=" + excelUploadPack + ", excelUploadBatch=" + excelUploadBatch + ", excelUploadMfg=" + excelUploadMfg + ", excelUploadExpdate=" + excelUploadExpdate + ", excelUploadMrp=" + excelUploadMrp + ", excelUploadRate=" + excelUploadRate + ", excelUploadQty=" + excelUploadQty + ", excelUploadFree=" + excelUploadFree + ", excelUploadDiscPerc=" + excelUploadDiscPerc + ", excelUploadTaxPerc=" + excelUploadTaxPerc + ", excelUploadAmt=" + excelUploadAmt + ", companyId=" + companyId + ", storeId=" + storeId + ", finYrId=" + finYrId + ", createdBy=" + createdBy + "]";
	}
	
}
