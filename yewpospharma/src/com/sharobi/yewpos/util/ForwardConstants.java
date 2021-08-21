/**
 * 
 */
package com.sharobi.yewpos.util;

/**
 * @author habib
 *
 */
public class ForwardConstants {
	
	/* Role Module*/

	/**
	 * This key used to Forward page to Login Page
	 */
	public static final String VIEW_LOGIN_PAGE = "authentication.login";
	/**
	 * This key used to redirect Home
	 */
	public static final String REDIRECT_HOME = "redirect:/home/welcome.htm";
	public static final String VIEW_HOME_PAGE = "home";
	public static final String VIEW_ADMIN_HOME_PAGE="adminhome";
	public static final String REDIRECT_LOGIN_PAGE = "redirect:/authentication/login.htm";
	/* End Role Module*/
	/* POS Module*/
	/**
	 * This key used to view cash memo 
	 */
	public static final String VIEW_POS_PAGE = "pos.pos";
	/**
	 * This key used to view modify cash memo 
	 */
	public static final String VIEW_MODIFYCASHMEMO_PAGE = "pos.modifycashmemo";
	/**
	 * This key used to view reprint cash memo 
	 */
	public static final String VIEW_REPRINTCASHMEMO_PAGE = "pos.reprintcashmemo";
	/**
	 * This key used to view return memo 
	 */
	public static final String VIEW_RETURNMEMO_PAGE = "pos.returnmemo";
	/**
	 * This key used to view modify return memo 
	 */
	public static final String VIEW_MODIFYRETURNMEMO_PAGE = "pos.modifyreturnmemo";
	/**
	 * This key used to view reprint return memo 
	 */
	public static final String VIEW_REPRINTRETURNMEMO_PAGE = "pos.reprintreturnmemo";
	/**
	 * This key used to view customer 
	 */
	public static final String VIEW_CUSTOMER_PAGE = "pos.customer";
	/**
	 * This key used to view customer payment details
	 */
	public static final String VIEW_CUSTOMER_PAYMENT_DET_PAGE = "pos.customerpaydet";
	/**
	 * This key used to view customer payment
	 */
	public static final String VIEW_CUSTOMER_PAYMENT_PAGE = "pos.customerpay";
	public static final String 	VIEW_CUSTOMER_PAYMENT_PRINT = "print/cashreceipt";
	public static final String 	VIEW_JOURNAL_PAYMENT_PRINT = "print/journalpayment";
	
	public static final String 	VIEW_JOURNAL_RECEIPT_PRINT = "print/journalcashrecipt";
 
	
	/* End POS Module*/
	/* Inventory Module*/
	/**
	 * This key used to view brand page 
	 */
	public static final String VIEW_BRAND_PAGE = "inv.brand";
	/**
	 * This key used to view category page 
	 */
	public static final String VIEW_CATEGORY_PAGE = "inv.category";
	/**
	 * This key used to view content page 
	 */
	public static final String VIEW_CONTENT_PAGE = "inv.content";
	/**
	 * This key used to view doctor page 
	 */
	public static final String VIEW_DOCTOR_PAGE = "inv.doctor";
	/**
	 * This key used to view group page 
	 */
	public static final String VIEW_GROUP_PAGE = "inv.group";
	/**
	 * This key used to view item page 
	 */
	public static final String VIEW_ITEM_PAGE = "inv.item";
	/**
	 * This key used to view item master page 
	 */
	public static final String VIEW_ITEM_MASTER_PAGE = "inv.itemmst";
	/**
	 * This key used to view manufacturer page 
	 */
	public static final String VIEW_MANUFACTURER_PAGE = "inv.manufacturer";
	/**
	 * This key used to view rack page 
	 */
	public static final String VIEW_RACK_PAGE = "inv.rack";
	/**
	 * This key used to view schedule page 
	 */
	public static final String VIEW_SCHEDULE_PAGE = "inv.schedule";
	/**
	 * This key used to view sub category page 
	 */
	public static final String VIEW_SUBCATEGORY_PAGE = "inv.subcategory";
	/**
	 * This key used to view unit page 
	 */
	public static final String VIEW_UNIT_PAGE = "inv.unit";
	/**
	 * This key used to view stock entry page 
	 */
	public static final String VIEW_STOCKENTRY_PAGE = "inv.stockentry";
	/**
	 * This key used to view stock adj page 
	 */
	public static final String VIEW_STOCKADJ_PAGE = "inv.stockadj";
	/**
	 * This key used to view stock expiry issue page 
	 */
	public static final String VIEW_STOCKEXPISSUE_PAGE = "inv.stockexpissue";
	/**
	 * This key used to view stock expiry issue register page 
	 */
	public static final String VIEW_STOCKEXPISSUEREG_PAGE = "inv.stockexpissuereg";
	/**
	 * This key used to view item searching page 
	 */
	public static final String VIEW_ITEMSEARCHING_PAGE = "inv.itemsearching";
	/**
	 * This key used to view tax page 
	 */
	public static final String VIEW_TAX_PAGE = "inv.tax";
	
	
	/**
	 * This key used to view city page 
	 */
	public static final String VIEW_CITY_PAGE = "inv.city";
	/**
	 * This key used to view zone page 
	 */
	public static final String VIEW_ZONE_PAGE = "inv.zone";
	/**
	 * This key used to view area page 
	 */
	public static final String VIEW_AREA_PAGE = "inv.area";
	/**
	 * This key used to view year end page 
	 */
	public static final String VIEW_YEAR_END_PAGE = "inv.yearend";
	/* End Inventory Module*/
	
	/* Procurement Module*/
	/**
	 * This key used to view purchase invoice 
	 */
	public static final String VIEW_PURINVOICE_PAGE = "proc.purinvoice";
	/**
	 * This key used to view purchase invoice 
	 */
	public static final String VIEW_PURINVOICE_DETAILS_PAGE = "proc.purinvoicedet";
	/**
	 * This key used to view purchase return 
	 */
	public static final String VIEW_PURRETURN_PAGE = "proc.purreturn";
	/**
	 * This key used to view vendor 
	 */
	public static final String VIEW_VENDOR_PAGE = "proc.vendor";
	
	public static final String 	VIEW_VENDOR_PAYMENT_PRINT = "print/vendorpaymentprint";
	
	/**
	 * This key used to view purchase invoice register 
	 */
	public static final String VIEW_PURINVOICEREG_PAGE = "proc.purinvoicereg";
	/**
	 * This key used to view purchase return register 
	 */
	public static final String VIEW_PURRETURNREG_PAGE = "proc.purreturnreg";
	/**
	 * This key used to view vendor payment
	 */
	public static final String VIEW_VENDOR_PAYMENT_PAGE = "proc.vendorpay";
	/**
	 * This key used to view vendor payment reg
	 */
	public static final String VIEW_VENDOR_PAYMENT_REG_PAGE = "proc.vendorpayreg";
	/**
	 * This key used to view purchase order 
	 */
	public static final String VIEW_PURORDER_PAGE = "proc.purorder";
	/**
	 * This key used to view purchase order details
	 */
	public static final String VIEW_PURORDER_DETAILS_PAGE = "proc.purorder.details";
	/**
	 * This key used to view purchase order register 
	 */
	public static final String VIEW_PURORDREG_PAGE = "proc.purordreg";
	/* End Procurement Module*/
	
	/* Store Mgnt Module*/
	
	/* End Store Mgnt Module*/
	
	/* Accounts Module*/
	
	/* End Accounts Module*/
	
	/* Report Module*/
	/**
	 * This key used to view Stock register register 
	 */
	public static final String VIEW_STOCKREG_PAGE = "repinv.stockreg";
	
	/**
	 * This key used to view Expiry register register 
	 */
	public static final String VIEW_EXPREG_PAGE = "repinv.expreg";
	
	/**
	 * This key used to get Expiry report distributor wise 
	 */
	public static final String VIEW_EXPDISTWISE_PAGE = "repinv.expdistwise";
	/**
	 * This key used to view Stock on val register 
	 */
	public static final String VIEW_STOCKONVALUE_PAGE = "repinv.stockonvalue";
	/**
	 * This key used to view Group Wise Stock 
	 */
	public static final String VIEW_GROUP_WISE_STOCK_PAGE = "repinv.groupwisestock";
	public static final String VIEW_VENDOR_WISE_STOCK_PAGE = "repinv.vendorwisestock";
	public static final String VIEW_MANUFACTURER_WISE_STOCK_PAGE = "repinv.manufacturerwisestock";
	
	/**
	 * This key used to view Sales summary 
	 */
	public static final String VIEW_SALESUMMARY_PAGE = "reppos.salesummary";
	
	/**
	 * This key used to view Sale register register 
	 */
	public static final String VIEW_SALEREG_PAGE = "reppos.salereg";
	/**
	 * This key used to view Sale register register 
	 */
	public static final String VIEW_ESIONGC_SALEREG_PAGE = "reppos.esiongcsalereg";
	public static final String VIEW_SHEDULE_H1_PAGE = "reppos.scheduleh1reg";
	public static final String VIEW_TB_REG_PAGE = "reppos.tbreg";
	
	/**
	 * This key used to view Sale Item report 
	 */
	public static final String VIEW_SALEITEM_PAGE = "reppos.saleitem";

	public static final String VIEW_DOCTOR_WISE_SALE_PAGE = "reppos.doctorwisesale";
	
	/**
	 * This key used to view customer ledger register 
	 */
	public static final String VIEW_CUSTOMER_LEDGER_PAGE = "reppos.customerledger";
	/**
	 * This key used to view Purchase summary 
	 */
	public static final String VIEW_PURCHASESUMMARY_PAGE = "repproc.purchasesummary";
	
	/**
	 * This key used to view Purchase register register 
	 */
	public static final String VIEW_PURCHASEREG_PAGE = "repproc.purchasereg";
	
	/**
	 * This key used to view Purchase Item wise report 
	 */
	public static final String VIEW_PURCHASEITEMWISE_PAGE = "repproc.purchaseitemwise";
	
	/**
	 * This key used to view report of schedule h drug
	 */
	public static final String VIEW_PURCHASE_SCHEDULEH_PAGE = "repproc.purchasescheduleh";
	
	/**
	 * This key used to view report of schedule h1 drug
	 */
	public static final String VIEW_PURCHASE_SCHEDULEH1_PAGE = "repproc.purchasescheduleh1";
	
	/**
	 * This key used to view report of narco reg
	 */
	public static final String VIEW_PURCHASE_NARCOREG_PAGE = "repproc.purchasenarco";
	
	
	/**
	 * This key used to view Sales Return summary 
	 */
	public static final String VIEW_SALERETURNSUMMARY_PAGE = "reppos.salereturnsummary";
	
	/**
	 * This key used to view Sale Return register 
	 */
	public static final String VIEW_SALERETURNREG_PAGE = "reppos.salereturnreg";
	
	/**
	 * This key used to view Sale Return Item wise report 
	 */
	public static final String VIEW_SALERETURNITEMWISE_PAGE = "reppos.salereturnitemwise";
	
	/**
	 * This key used to view Non Moving Item report 
	 */
	public static final String VIEW_NONMOVINGITEM_PAGE = "reppos.nonmovingitem";
	
	
	/**
	 * This key used to view Purchase Return summary 
	 */
	public static final String VIEW_PURCHASERETURNSUMMARY_PAGE = "repproc.purchasereturnsummary";
	
	/**
	 * This key used to view Purchase Return register 
	 */
	public static final String VIEW_PURCHASERETURNREG_PAGE = "repproc.purchasereturnreg";
	
	/**
	 * This key used to view Purchase Return Item wise report 
	 */
	public static final String VIEW_PURCHASERETURNITEMWISE_PAGE = "repproc.purchasereturnitemwise";
	/**
	 * This key used to view free qty dist wise report 
	 */
	public static final String VIEW_FREEQTYDISTWISE_PAGE = "repproc.freeqtydistwise";
	/**
	 * This key used to view vendor ledger report 
	 */
	public static final String VIEW_VENDORLEDGER_PAGE = "repproc.vendorledger";
	/**
	 * This key used to view purchase order adj report 
	 */
	public static final String VIEW_PURCHASE_ORDER_ADJ = "repproc.purorderadj";
	/**
	 * This key used to view tax integrity report 
	 */
	public static final String VIEW_TAXINTEGRITY_PAGE = "reptax.taxintegrity";
	/**
	 * This key used to view tax summary report 
	 */
	public static final String VIEW_TAXSUMMARY_PAGE = "reptax.taxsummary";
	/**
	 * This key used to view tax B2CS report 
	 */
	public static final String VIEW_TAXB2CS_PAGE = "reptax.btwocs";
	/**
	 * This key used to view tax hsn report 
	 */
	public static final String VIEW_TAXHSN_PAGE = "reptax.hsn";
	public static final String VIEW_GSTR3B_PAGE = "gstr3b.hsn";
	public static final String VIEW_GSTR9A_PAGE = "gstr9a.hsn";
	/**
	 * This key used to view tax slab summary report 
	 */
	public static final String VIEW_TAX_SLABSUMMARY_PAGE = "reptax.taxslabsummary";
	
	/* End Report Module*/
	
	
	public static final String VIEW_PRINTMEMO_PAGE = "/pos/print";
	public static final String VIEW_PRINTMEMO_SPL_PAGE = "/pos/print_spcl";

	public static final String VIEW_PRINTMEMO80mm_NEW_PAGE = "/pos/print_80_new";
	
	public static final String VIEW_PRINTMEMO_NONCOMPOSITE_PAGE_NEW = "/pos/newprint";

	public static final String VIEW_PRINTMEMO_NONCOMPOSITE_PAGE = "/pos/print_noncomposite";
	
	public static final String VIEW_PRINTPUR_PAGE = "/proc/purchasebillprint";
	
	public static final String VIEW_PRINTRETURNSALES_PAGE = "/pos/printreturnmemo";
	
	/**
	 * This key used to view ledger page for account add on 15_2_2018
	 */

	/*========= Account Module start =============*/
	public static final String VIEW_LEDGER_PAGE = "acc.ledger";
	public static final String VIEW_ACCONTSETUP_PAGE = "acc.accsetup";
	
	public static final String VIEW_ACCONTJOURNAL_PAGE = "acc.journal";
	public static final String VIEW_CHARTOFACC_PAGE = "acc.chartofacc";
	public static final String RPT_LEDGER_VIEW = "repledger";
	
	public static final String RPT_TRIAL_VIEW = "reptrial";
	public static final String RPT_BALANCE_SHEET_VIEW = "rptbalancesheet";
	public static final String RPT_ACCOUNT_BALANCE_VIEW = "rptaccountbalacne";
	public static final String RPT_ACCOUNT_PROFITANDLOSS_VIEW = "rptprofitandloss";
	
	
	
	public static final String RPT_DAILY_COLLECTION_VIEW = "dailycollection";
	
	public static final String RPT_DAILY_PAYMENT_VIEW = "dailypayment";
	public static final String VIEW_PRINTPURCHASE_PAGE = "/proc/printpurchaseinv";
	
	
	
	/*========= Account Module Ends =============*/
	/*ADMIN MODULE*/
	/**
	 * This key used to view area page
	 */
	public static final String VIEW_USER_PAGE = "admin.user";
	public static final String VIEW_ROLE_PAGE = "admin.role";
	public static final String VIEW_MAPPING_PAGE = "admin.map";
}
