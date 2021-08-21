/**
 * 
 */
package com.sharobi.yewpos.pos.model;

public class Dashboard {
	private int totalCustomer;
	private int totalProduct;
	private int totalVendor;
    private double saleTotalAmount;
    private double purchaseTotalAmount;
    private double saleReturnTotalAmount;
    private double purchaseReturnTotalAmount;
    private double valuation;
    private String stockqty;
    private double cashInHandCr;
    private double cashInHandDr;
    private double BankAmtCr;
    private double BankAmtDr;
    private int totalPurchase;
    private int totalSale;
    private int totalPurchaseReturn;
    private int totalSaleReturn;
    private int totalPurchaseInv;
    private int totalPurchaseOrder;
    private double totalSaleCashAmount;
    private double totalSaleCardAmount;
    private double totalSaleCreditAmount;
    private double purchaseOrderTotalAmount;
    private double purchaseInvTotalAmount;
    private double taxAndDutiesCr;
    private double taxAndDutiesDr;
    private double profitAndLoss;
	/**
	 * @return the totalCustomer
	 */
	public int getTotalCustomer() {
		return totalCustomer;
	}
	/**
	 * @param totalCustomer the totalCustomer to set
	 */
	public void setTotalCustomer(int totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
	/**
	 * @return the totalProduct
	 */
	public int getTotalProduct() {
		return totalProduct;
	}
	/**
	 * @param totalProduct the totalProduct to set
	 */
	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	/**
	 * @return the totalVendor
	 */
	public int getTotalVendor() {
		return totalVendor;
	}
	/**
	 * @param totalVendor the totalVendor to set
	 */
	public void setTotalVendor(int totalVendor) {
		this.totalVendor = totalVendor;
	}
	/**
	 * @return the saleTotalAmount
	 */
	public double getSaleTotalAmount() {
		return saleTotalAmount;
	}
	/**
	 * @param saleTotalAmount the saleTotalAmount to set
	 */
	public void setSaleTotalAmount(double saleTotalAmount) {
		this.saleTotalAmount = saleTotalAmount;
	}
	/**
	 * @return the purchaseTotalAmount
	 */
	public double getPurchaseTotalAmount() {
		return purchaseTotalAmount;
	}
	/**
	 * @param purchaseTotalAmount the purchaseTotalAmount to set
	 */
	public void setPurchaseTotalAmount(double purchaseTotalAmount) {
		this.purchaseTotalAmount = purchaseTotalAmount;
	}
	/**
	 * @return the saleReturnTotalAmount
	 */
	public double getSaleReturnTotalAmount() {
		return saleReturnTotalAmount;
	}
	/**
	 * @param saleReturnTotalAmount the saleReturnTotalAmount to set
	 */
	public void setSaleReturnTotalAmount(double saleReturnTotalAmount) {
		this.saleReturnTotalAmount = saleReturnTotalAmount;
	}
	/**
	 * @return the purchaseReturnTotalAmount
	 */
	public double getPurchaseReturnTotalAmount() {
		return purchaseReturnTotalAmount;
	}
	/**
	 * @param purchaseReturnTotalAmount the purchaseReturnTotalAmount to set
	 */
	public void setPurchaseReturnTotalAmount(double purchaseReturnTotalAmount) {
		this.purchaseReturnTotalAmount = purchaseReturnTotalAmount;
	}
	/**
	 * @return the valuation
	 */
	public double getValuation() {
		return valuation;
	}
	/**
	 * @param valuation the valuation to set
	 */
	public void setValuation(double valuation) {
		this.valuation = valuation;
	}
	/**
	 * @return the stockqty
	 */
	public String getStockqty() {
		return stockqty;
	}
	/**
	 * @param stockqty the stockqty to set
	 */
	public void setStockqty(String stockqty) {
		this.stockqty = stockqty;
	}
	/**
	 * @return the cashInHandCr
	 */
	public double getCashInHandCr() {
		return cashInHandCr;
	}
	/**
	 * @param cashInHandCr the cashInHandCr to set
	 */
	public void setCashInHandCr(double cashInHandCr) {
		this.cashInHandCr = cashInHandCr;
	}
	/**
	 * @return the cashInHandDr
	 */
	public double getCashInHandDr() {
		return cashInHandDr;
	}
	/**
	 * @param cashInHandDr the cashInHandDr to set
	 */
	public void setCashInHandDr(double cashInHandDr) {
		this.cashInHandDr = cashInHandDr;
	}
	/**
	 * @return the bankAmtCr
	 */
	public double getBankAmtCr() {
		return BankAmtCr;
	}
	/**
	 * @param bankAmtCr the bankAmtCr to set
	 */
	public void setBankAmtCr(double bankAmtCr) {
		BankAmtCr = bankAmtCr;
	}
	/**
	 * @return the bankAmtDr
	 */
	public double getBankAmtDr() {
		return BankAmtDr;
	}
	/**
	 * @param bankAmtDr the bankAmtDr to set
	 */
	public void setBankAmtDr(double bankAmtDr) {
		BankAmtDr = bankAmtDr;
	}
	/**
	 * @return the totalPurchase
	 */
	public int getTotalPurchase() {
		return totalPurchase;
	}
	/**
	 * @param totalPurchase the totalPurchase to set
	 */
	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	/**
	 * @return the totalSale
	 */
	public int getTotalSale() {
		return totalSale;
	}
	/**
	 * @param totalSale the totalSale to set
	 */
	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}
	/**
	 * @return the totalPurchaseReturn
	 */
	public int getTotalPurchaseReturn() {
		return totalPurchaseReturn;
	}
	/**
	 * @param totalPurchaseReturn the totalPurchaseReturn to set
	 */
	public void setTotalPurchaseReturn(int totalPurchaseReturn) {
		this.totalPurchaseReturn = totalPurchaseReturn;
	}
	/**
	 * @return the totalSaleReturn
	 */
	public int getTotalSaleReturn() {
		return totalSaleReturn;
	}
	/**
	 * @param totalSaleReturn the totalSaleReturn to set
	 */
	public void setTotalSaleReturn(int totalSaleReturn) {
		this.totalSaleReturn = totalSaleReturn;
	}
	/**
	 * @return the totalPurchaseInv
	 */
	public int getTotalPurchaseInv() {
		return totalPurchaseInv;
	}
	/**
	 * @param totalPurchaseInv the totalPurchaseInv to set
	 */
	public void setTotalPurchaseInv(int totalPurchaseInv) {
		this.totalPurchaseInv = totalPurchaseInv;
	}
	/**
	 * @return the totalPurchaseOrder
	 */
	public int getTotalPurchaseOrder() {
		return totalPurchaseOrder;
	}
	/**
	 * @param totalPurchaseOrder the totalPurchaseOrder to set
	 */
	public void setTotalPurchaseOrder(int totalPurchaseOrder) {
		this.totalPurchaseOrder = totalPurchaseOrder;
	}
	/**
	 * @return the totalSaleCashAmount
	 */
	public double getTotalSaleCashAmount() {
		return totalSaleCashAmount;
	}
	/**
	 * @param totalSaleCashAmount the totalSaleCashAmount to set
	 */
	public void setTotalSaleCashAmount(double totalSaleCashAmount) {
		this.totalSaleCashAmount = totalSaleCashAmount;
	}
	/**
	 * @return the totalSaleCardAmount
	 */
	public double getTotalSaleCardAmount() {
		return totalSaleCardAmount;
	}
	/**
	 * @param totalSaleCardAmount the totalSaleCardAmount to set
	 */
	public void setTotalSaleCardAmount(double totalSaleCardAmount) {
		this.totalSaleCardAmount = totalSaleCardAmount;
	}
	/**
	 * @return the totalSaleCreditAmount
	 */
	public double getTotalSaleCreditAmount() {
		return totalSaleCreditAmount;
	}
	/**
	 * @param totalSaleCreditAmount the totalSaleCreditAmount to set
	 */
	public void setTotalSaleCreditAmount(double totalSaleCreditAmount) {
		this.totalSaleCreditAmount = totalSaleCreditAmount;
	}
	/**
	 * @return the purchaseOrderTotalAmount
	 */
	public double getPurchaseOrderTotalAmount() {
		return purchaseOrderTotalAmount;
	}
	/**
	 * @param purchaseOrderTotalAmount the purchaseOrderTotalAmount to set
	 */
	public void setPurchaseOrderTotalAmount(double purchaseOrderTotalAmount) {
		this.purchaseOrderTotalAmount = purchaseOrderTotalAmount;
	}
	/**
	 * @return the purchaseInvTotalAmount
	 */
	public double getPurchaseInvTotalAmount() {
		return purchaseInvTotalAmount;
	}
	/**
	 * @param purchaseInvTotalAmount the purchaseInvTotalAmount to set
	 */
	public void setPurchaseInvTotalAmount(double purchaseInvTotalAmount) {
		this.purchaseInvTotalAmount = purchaseInvTotalAmount;
	}
	/**
	 * @return the taxAndDutiesCr
	 */
	public double getTaxAndDutiesCr() {
		return taxAndDutiesCr;
	}
	/**
	 * @param taxAndDutiesCr the taxAndDutiesCr to set
	 */
	public void setTaxAndDutiesCr(double taxAndDutiesCr) {
		this.taxAndDutiesCr = taxAndDutiesCr;
	}
	/**
	 * @return the taxAndDutiesDr
	 */
	public double getTaxAndDutiesDr() {
		return taxAndDutiesDr;
	}
	/**
	 * @param taxAndDutiesDr the taxAndDutiesDr to set
	 */
	public void setTaxAndDutiesDr(double taxAndDutiesDr) {
		this.taxAndDutiesDr = taxAndDutiesDr;
	}
	/**
	 * @return the profitAndLoss
	 */
	public double getProfitAndLoss() {
		return profitAndLoss;
	}
	/**
	 * @param profitAndLoss the profitAndLoss to set
	 */
	public void setProfitAndLoss(double profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dashboard [totalCustomer=" + totalCustomer + ", totalProduct=" + totalProduct + ", totalVendor="
				+ totalVendor + ", saleTotalAmount=" + saleTotalAmount + ", purchaseTotalAmount=" + purchaseTotalAmount
				+ ", saleReturnTotalAmount=" + saleReturnTotalAmount + ", purchaseReturnTotalAmount="
				+ purchaseReturnTotalAmount + ", valuation=" + valuation + ", stockqty=" + stockqty + ", cashInHandCr="
				+ cashInHandCr + ", cashInHandDr=" + cashInHandDr + ", BankAmtCr=" + BankAmtCr + ", BankAmtDr="
				+ BankAmtDr + ", totalPurchase=" + totalPurchase + ", totalSale=" + totalSale + ", totalPurchaseReturn="
				+ totalPurchaseReturn + ", totalSaleReturn=" + totalSaleReturn + ", totalPurchaseInv="
				+ totalPurchaseInv + ", totalPurchaseOrder=" + totalPurchaseOrder + ", totalSaleCashAmount="
				+ totalSaleCashAmount + ", totalSaleCardAmount=" + totalSaleCardAmount + ", totalSaleCreditAmount="
				+ totalSaleCreditAmount + ", purchaseOrderTotalAmount=" + purchaseOrderTotalAmount
				+ ", purchaseInvTotalAmount=" + purchaseInvTotalAmount + ", taxAndDutiesCr=" + taxAndDutiesCr
				+ ", taxAndDutiesDr=" + taxAndDutiesDr + ", profitAndLoss=" + profitAndLoss + "]";
	}
    
}
