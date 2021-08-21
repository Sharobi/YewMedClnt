/**
 * 
 */
package com.sharobi.yewpos.util;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author habib,Manodip
 *
 */
public class CommonResource {
	
	private final static Logger logger = LogManager.getLogger(CommonResource.class);
	private final static Properties resourceProperties = getProperties();
	
	public static final String DATE_FORMAT_GSON = "date.format.gson";
	public static final String DATE_FORMAT_GSON_TABLE = "date.format.gson.table";
	public static final String DATE_FORMAT_SHORT = "date.format.short";
	
	public static final String TARGET_WEBSERVICE_ENDPOINT = "target.webservice.endpoint";
	
	/* Role Module*/
	public final static String WEBSERVICE_LOGIN_DOLOGIN = "webservice.login.dologin";
	public final static String WEBSERVICE_ROLE_GETAPPMENUBYUSER = "webservice.role.getappmenubyuser";
	public final static String WEBSERVICE_ROLE_ADD_ROLE = "webservice.role.add.role";
	public final static String WEBSERVICE_ROLE_UPDATE_ROLE = "webservice.role.update.role";
	public final static String WEBSERVICE_ROLE_GETSPECIFICMENUROLEBYUSER = "webservice.role.getspecificmenurolebyuser";
	/* End Role Module*/
	/* POS Module*/
	// Cash Memo
	public final static String WEBSERVICE_POS_CASHMEMO_HEADER_BYID = "webservice.pos.cashmemo.header.byid";
	public final static String WEBSERVICE_POS_CASHMEMO_DETAILS_BYID = "webservice.pos.cashmemo.details.byid";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_ALLSALEDETAILS = "webservice.pos.cashmemo.get.allsaledetails";
	public final static String WEBSERVICE_POS_CASHMEMO_CREATEORUPDATE_SALEDETAILS = "webservice.pos.cashmemo.createorupdate.saledetails";
	public final static String WEBSERVICE_POS_CASHMEMO_SALES_POSTSALESINVOICE = "webservice.pos.cashmemo.sales.postsalesinvoice";
	public final static String WEBSERVICE_POS_CASHMEMO_SALES_DELETESALESINVOICE = "webservice.pos.cashmemo.sales.deletesalesinvoice";
	public final static String WEBSERVICE_POS_CASHMEMO_REPRINT_COUNT= "webservice.pos.cashmemo.reprint.count";
	public final static String WEBSERVICE_POS_CASHMEMO_GETCUSTLASTSALEHEADER= "webservice.pos.cashmemo.getcustlastsaleheader";
	public final static String WEBSERVICE_POS_CASHMEMO_DETAILS_BYID_FORBILL = "webservice.pos.cashmemo.details.byid.forbill";
	public final static String WEBSERVICE_POS_CASHMEMO_HEADER_BYID_FORBILL = "webservice.pos.cashmemo.header.byid.forbill";
	public final static String WEBSERVICE_POS_CASHMEMO_TAXDETAILS_FORBILL = "webservice.pos.cashmemo.taxdetails.forbill";
	public final static String WEBSERVICE_POS_CASHMEMO_SALEBILL_GETSALEITEMDETAILSFORRETURN = "webservice.pos.cashmemo.salebill.getsaleitemdetailsforreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_SALES_POSTALLSALESINVOICE = "webservice.pos.cashmemo.sales.postallsalesinvoice";
	//Return Memo
	public final static String WEBSERVICE_POS_CASHMEMO_GET_RETURNHEADER_BYINVNO = "webservice.pos.cashmemo.get.returnheader.byinvno";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEDET_BYINVNO = "webservice.pos.cashmemo.get.returnsaledet.byinvno";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEALLDET_BYINVNO = "webservice.pos.cashmemo.get.returnsalealldet.byinvno";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_ALLRETURNDETAILS = "webservice.pos.cashmemo.get.allreturndetails";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_RETURNSALEALLDET_BYITEM = "webservice.pos.cashmemo.get.returnsalealldet.byitem";
	public final static String WEBSERVICE_POS_CASHMEMO_CREATEORUPDATE_SALESRETURN = "webservice.pos.cashmemo.createorupdate.salesreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_SALESALERETURNHEADER_BYRETURNID = "webservice.pos.cashmemo.get.salesalereturnheader.byreturnid";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_SALESALERETURNDETAILS_BYRETURNID = "webservice.pos.cashmemo.get.salesalereturndetails.byreturnid";
	public final static String WEBSERVICE_POS_CASHMEMO_DELETE_SALESRETURN = "webservice.pos.cashmemo.delete.salesreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_POST_SALESRETURN = "webservice.pos.cashmemo.post.salesreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_POST_ALL_SALESRETURN = "webservice.pos.cashmemo.post.all.salesreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_ADJSALESRETURN = "webservice.pos.cashmemo.get.adjsalesreturn";
	public final static String WEBSERVICE_POS_CASHMEMO_GET_ADJSALESRETURN_BYSALEID = "webservice.pos.cashmemo.get.adjsalesreturn.bysaleid";
	// Customer
	public final static String WEBSERVICE_POS_ADD_CUSTOMER = "webservice.pos.add.customer";
	public final static String WEBSERVICE_POS_GET_ALL_CUSTOMERS = "webservice.pos.get.allcustomer";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_BY_ID = "webservice.pos.get.customerById";
	public final static String WEBSERVICE_POS_EDIT_CUSTOMER = "webservice.pos.edit.customer";
	public final static String WEBSERVICE_POS_DELETE_CUSTOMER= "webservice.pos.delete.customer";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_BYNAMEORPH= "webservice.pos.get.customer.bynameorph";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_WITHCREDIT_BYNAMEORPH= "webservice.pos.get.customer.withcredit.bynameorph";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_GENDERLIST= "webservice.pos.get.customer.gender";
	// Customer Payment
	public final static String WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTDETAILS = "webservice.pos.get.allpaymentdetails";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_PENDINGPAYMENT_BYCUST = "webservice.pos.get.pendingpayment.bycust";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTHEADER_BYID = "webservice.pos.get.allpaymentheader.byid";
	public final static String WEBSERVICE_POS_GET_CUSTOMER_PAYMENTDETAILS_BYCUSTOMERID = "webservice.pos.get.paymentdetails.bycustomerid";
	public final static String WEBSERVICE_POS_POST_CUSTOMERPAYMENT = "webservice.pos.post.customerpayment";
	public final static String WEBSERVICE_POS_CREATE_UPDATE_CUSTOMERPAYMENT = "webservice.pos.create.update.customerpayment";
	public final static String WEBSERVICE_POS_DELETE_CUSTPAYINV = "webservice.pos.delete.custpayinv";
	public final static String WEBSERVICE_POS_SALEBILL_PRINT = "webservice.pos.salebill.print";
	public final static String WEBSERVICE_POS_SALEBILL_GETTYPES = "webservice.pos.salebill.gettypes";
	public final static String WEBSERVICE_POS_SALEBILL_GETESICODES = "webservice.pos.salebill.getesicodes";
	public final static String WEBSERVICE_POS_SALEBILL_GETPLACEOFTREATMENT = "webservice.pos.salebill.getplaceoftreatment";
	/* End POS Module*/
	/* Inventory Module*/
	//Tax
	public final static String WEBSERVICE_INV_GETTAXSBYCOMPID = "webservice.inv.gettaxesbycompid";
	public final static String WEBSERVICE_INV_AUTOCOMPLETE_GETTAXESBYNAME = "webservice.inv.autocomplete.gettaxesbyname";
	public final static String WEBSERVICE_INV_ADD_TAX = "webservice.inv.add.tax";
	public final static String WEBSERVICE_INV_UPDATE_TAX = "webservice.inv.update.tax";
	public final static String WEBSERVICE_INV_TAXDET_BY_ID = "webservice.inv.taxdet.by.id";
	public final static String WEBSERVICE_INV_TAX_BY_ID = "webservice.inv.tax.by.id";
	public final static String WEBSERVICE_INV_DELETE_TAX = "webservice.inv.delete.tax";
	// Brand
	public final static String WEBSERVICE_INV_ADD_BRAND = "webservice.inv.add.brand";
	public final static String WEBSERVICE_INV_UPDATE_BRAND = "webservice.inv.update.brand";
	public final static String WEBSERVICE_INV_DELETE_BRAND = "webservice.inv.delete.brand";
	public final static String WEBSERVICE_INV_SEARCH_BRAND = "webservice.inv.search.brand";
	public final static String WEBSERVICE_INV_GETBRANDSBYCOMPID = "webservice.inv.getbrandsbycompid";
	public final static String WEBSERVICE_INV_GETBRANDBYID = "webservice.inv.getbrandbyid";
	public final static String WEBSERVICE_INV_SEARCH_BRAND_AUTOCOMPLETE = "webservice.inv.search.brand.autocomplete";
	// Content
	public final static String WEBSERVICE_INV_ADD_CONTENT = "webservice.inv.add.content";
	public final static String WEBSERVICE_INV_UPDATE_CONTENT = "webservice.inv.update.content";
	public final static String WEBSERVICE_INV_DELETE_CONTENT = "webservice.inv.delete.content";
	public final static String WEBSERVICE_INV_GET_ALL_CONTENTS = "webservice.inv.get.allcontents";
	public final static String WEBSERVICE_INV_GET_CONTENT_BY_ID = "webservice.inv.contentdetails.by.id";
	public final static String WEBSERVICE_INV_SEARCH_CONTENT = "webservice.inv.search.content";
	public final static String WEBSERVICE_INV_SEARCH_CONTENT_AUTOCOMPLETE = "webservice.inv.search.content.autocomplete";
	public final static String WEBSERVICE_INV_SEARCH_CONTENT_STOCK_AUTOCOMPLETE = "webservice.inv.search.content.stock.autocomplete";
	// Manufacturer
	public final static String WEBSERVICE_INV_ADD_MANUFACTURER = "webservice.inv.add.manufacturer";
	public final static String WEBSERVICE_INV_UPDATE_MANUFACTURER = "webservice.inv.update.manufacturer";
	public final static String WEBSERVICE_INV_DELETE_MANUFACTURER = "webservice.inv.delete.manufacturer";
	public final static String WEBSERVICE_INV_GETMANUFACTURERSBYCOMPID = "webservice.inv.getmanufacturersbycompid";
	public final static String WEBSERVICE_INV_GETMANUFACTURERBYID = "webservice.inv.getmanufacturerbyid";
	public final static String WEBSERVICE_INV_SEARCH_MANUFACTURER = "webservice.inv.search.manufacturer";
	public final static String WEBSERVICE_INV_SEARCH_MANUFACTURER_AUTOCOMPLETE = "webservice.inv.search.manufacturer.autocomplete";
	// Doctor
	public final static String WEBSERVICE_INV_ADD_DOCTOR = "webservice.inv.add.doctor";
	public final static String WEBSERVICE_INV_GET_ALL_DOCTOR = "webservice.inv.get.alldoctor";
	public final static String WEBSERVICE_INV_GET_DOCTOR_BY_ID = "webservice.inv.get.doctorById";
	public final static String WEBSERVICE_INV_EDIT_DOCTOR = "webservice.inv.edit.doctor";
	public final static String WEBSERVICE_INV_DELETE_DOCTOR= "webservice.inv.delete.doctor";
	public final static String WEBSERVICE_INV_SEARCH_DOCTOR_AUTOCOMPLETE= "webservice.inv.search.doctor.autocomplete";
	public final static String WEBSERVICE_REP_POS_DOCTORS_WISE_SALE_REPORT= "webservice.inv.pos.doctorwisesalereport";
	
	// Unit
	public final static String WEBSERVICE_INV_ADD_UNIT = "webservice.inv.add.unit";
	public final static String WEBSERVICE_INV_GET_ALL_UNIT = "webservice.inv.get.allunit";
	public final static String WEBSERVICE_INV_GET__UNIT_BY_ID = "webservice.inv.get.unitById";
	public final static String WEBSERVICE_INV_EDIT_UNIT = "webservice.inv.edit.unit";
	public final static String WEBSERVICE_INV_DELETE_UNIT = "webservice.inv.delete.unit";
	public final static String WEBSERVICE_INV_SEARCH_UNIT_AUTOCOMPLETE = "webservice.inv.search.unit.autocomplete";
	// Category
	public final static String WEBSERVICE_INV_ADD_CAT = "webservice.inv.add.cat";
	public final static String WEBSERVICE_INV_GET_ALL_CAT = "webservice.inv.get.allcat";
	public final static String WEBSERVICE_INV_EDIT_CAT = "webservice.inv.edit.cat";
	public final static String WEBSERVICE_INV_DELETE_CAT = "webservice.inv.delete.cat";
	public final static String WEBSERVICE_INV_CATDETAILS_BY_ID = "webservice.inv.catdetails.by.id";
	// Sub-Category
	public final static String WEBSERVICE_INV_ADD_SUBCAT = "webservice.inv.add.subcat";
	public final static String WEBSERVICE_INV_GET_ALL_SUBCAT = "webservice.inv.get.allsubcat";
	public final static String WEBSERVICE_INV_EDIT_SUBCAT = "webservice.inv.edit.subcat";
	public final static String WEBSERVICE_INV_DELETE_SUBCAT = "webservice.inv.delete.subcat";
	// Rack
	public final static String WEBSERVICE_INV_ADD_RACK = "webservice.inv.add.rack";
	public final static String WEBSERVICE_INV_GET_ALL_RACK = "webservice.inv.get.allrack";
	public final static String WEBSERVICE_INV_EDIT_RACK = "webservice.inv.edit.rack";
	public final static String WEBSERVICE_INV_DELETE_RACK = "webservice.inv.delete.rack";
	// Group
	public final static String WEBSERVICE_INV_ADD_GROUP = "webservice.inv.add.group";
	public final static String WEBSERVICE_INV_GET_ALL_GROUPS = "webservice.inv.get.allgroup";
	public final static String WEBSERVICE_INV_EDIT_GROUP = "webservice.inv.edit.group";
	public final static String WEBSERVICE_INV_DELETE_GROUP = "webservice.inv.delete.group";
	public final static String WEBSERVICE_INV_GET_GROUP_BY_ID = "webservice.inv.groupdetails.by.id";
	// Schedule
	public final static String WEBSERVICE_INV_ADD_SCHEDULE = "webservice.inv.add.schedule";
	public final static String WEBSERVICE_INV_GET_ALL_SCHEDULES = "webservice.inv.get.allschedules";
	public final static String WEBSERVICE_INV_EDIT_SCHEDULE = "webservice.inv.edit.schedule";
	public final static String WEBSERVICE_INV_DELETE_SCHEDULE = "webservice.inv.delete.schedule";
	public final static String WEBSERVICE_INV_GET_SCHEDULE_BY_ID = "webservice.inv.scheduledetails.by.id";
	public final static String WEBSERVICE_INV_GET_ALL_RETURNREASONTYPE = "webservice.inv.get.all.returnreasontype";
	// Item
	public final static String WEBSERVICE_INV_ITEM_CHECK_DUPLICATE_NAME = "webservice.inv.item.check.duplicate.name";
	public final static String WEBSERVICE_INV_ADD_ITEM = "webservice.inv.add.item";
	public final static String WEBSERVICE_INV_ITEMDETAILS_BY_ID = "webservice.inv.itemdetails.by.id";
	public final static String WEBSERVICE_INV_UPDATE_ITEM = "webservice.inv.update.item";
	public final static String WEBSERVICE_INV_SEARCH_ITEM = "webservice.inv.search.item";
	public final static String WEBSERVICE_INV_SEARCH_TRANS_ITEM = "webservice.inv.search.transitem";
	public final static String WEBSERVICE_INV_DELETE_ITEM = "webservice.inv.delete.item";
	public final static String WEBSERVICE_INV_ITEM_GET_SAMECONTENT = "webservice.inv.item.get.samecontent";
	public final static String WEBSERVICE_INV_SEARCH_ITEM_AUTOCOMPLETE = "webservice.inv.search.item.autocomplete";
	public final static String WEBSERVICE_INV_SEARCH_ITEM_BY_CONTENT = "webservice.inv.search.item.by.content";
	public final static String WEBSERVICE_INV_SEARCH_ITEM_HISTORY_BY_ITEMID = "webservice.inv.search.item.history.by.itemid";
	public final static String WEBSERVICE_INV_SEARCH_ITEM_BY_BARCODE = "webservice.inv.search.item.by.barcode";
	// Stock
	public final static String WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM = "webservice.inv.stock.getcurrstock.ofitem";
	public final static String WEBSERVICE_INV_STOCK_UPLOADFILE = "webservice.inv.stock.uploadfile";
	public final static String WEBSERVICE_CREATE_OR_UPDATE_STOCKMANUAL = "webservice.inv.stock.createupdate.manual";
	public final static String WEBSERVICE_INV_STOCK_GETCURRENTSTOCK_OFITEM_BYBATCHEXPMRP = "webservice.inv.stock.getcurrentstock.ofitem.bybatchexpmrp";
	public final static String WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM_BYBARCODE = "webservice.inv.stock.getcurrstock.ofitem.bybarcode";
	public final static String WEBSERVICE_INV_STOCK_GETSTOCKDETAILS_FORADJUSTMENT = "webservice.inv.stock.getstockdetails.foradjustment";
	public final static String WEBSERVICE_INV_STOCK_STOCKDETAILS_UPDATE = "webservice.inv.stock.stockdetails.update";
	public final static String WEBSERVICE_INV_STOCK_STOCKDETAILS_DELETE = "webservice.inv.stock.stockdetails.delete";
	//Expiry
	public final static String WEBSERVICE_INV_GETALLPENDINGEXPIRYITEMS = "webservice.inv.getallpendingexpiryitems";
	public final static String WEBSERVICE_INV_GETALLEXPIRYDETAILS = "webservice.inv.getallexpirydetails";
	public final static String WEBSERVICE_INV_CREATE_OR_UPDATE_EXPIRYINVOICE = "webservice.inv.createorupdate.expiryinvoice";
	public final static String WEBSERVICE_INV_GET_EXPIRYHEADER_BYID = "webservice.inv.getexpiryheader.byid";
	public final static String WEBSERVICE_INV_GET_EXPIRYDETAILS_BYID = "webservice.inv.getexpirydetails.byid";
	public final static String WEBSERVICE_INV_DELETE_EXPIRYINVOICE = "webservice.inv.delete.expinvoice";
	public final static String WEBSERVICE_INV_POST_EXPIRYINVOICE = "webservice.inv.post.expinvoice";
	
	public final static String WEBSERVICE_INV_STK_TRNSFR_YR_END = "webservice.inv.stk.trnsfr.yr.end";
	
	/* End Inventory Module*/
	
	/* Procurement Module*/
	
	// Purchase Invoice
	public final static String WEBSERVICE_PROCUREMENT_GET_PURCHASEHEADER_BYID = "webservice.procurement.get.purchaseheader.byid";
	public final static String WEBSERVICE_PROCUREMENT_GET_PURCHASEDETAILS_BYID = "webservice.procurement.get.purchasedetails.byid";
	public final static String WEBSERVICE_PROCUREMENT_GET_ALL_PURCHASEDETAILS = "webservice.procurement.get.all.purchasedetails";
	public final static String WEBSERVICE_PROCUREMENT_CREATE_OR_UPDATE_PURCHASEINVOICE = "webservice.procurement.create.update.purchaseinvoice";
	public final static String WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYID = "webservice.procurement.get.purchaseinvoicedetails.byid";
	public final static String WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE = "webservice.procurement.print.by.sku";
	public final static String WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE_ALL = "webservice.procurement.print.by.sku.all";
	public final static String WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYSKU = "webservice.procurement.get.purchaseinvoicedetails.bysku";
	public final static String WEBSERVICE_PROCUREMENT_DELETE_PURCHASEINVOICE = "webservice.procurement.delete.purchaseinvoice";
	public final static String WEBSERVICE_PROCUREMENT_POST_PURCHASEINVOICE = "webservice.procurement.post.purchaseinvoice";
	public final static String WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEHISTORY = "webservice.procurement.get.purchaseinvoicehistory";
	public final static String WEBSERVICE_PROCUREMENT_CREATE_PURINV_FROMEXCEL = "webservice.procurement.create.purinv.fromexcel";
	public final static String WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASEINVOICE = "webservice.procurement.post.all.purchase";
	public final static String WEBSERVICE_PROCUREMENT_CHECK_DUPLICATE_BILL = "webservice.procurement.post.check.duplicate.bill";
	// Return Purchase Invoice
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEHEADER_BYID = "webservice.procurement.return.get.purchaseheader.byid";
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYID = "webservice.procurement.return.get.purchasedetails.byid";
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_ALL_PURCHASE = "webservice.procurement.return.get.all.purchase";
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYINVNO = "webservice.procurement.return.get.purchasedetails.byinvno";
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYITEMID = "webservice.procurement.return.get.purchasedetails.byitemid";
	public final static String WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYISKU = "webservice.procurement.return.get.purchasedetails.bysku";
	public final static String WEBSERVICE_PROCUREMENT_CREATE_UPDATE_PURRETURNINV = "webservice.procurement.create.update.purreturninv";
	public final static String WEBSERVICE_PROCUREMENT_DELETE_PURRETURNINV = "webservice.procurement.delete.purreturninv";
	public final static String WEBSERVICE_PROCUREMENT_POST_PURRETURNINV = "webservice.procurement.post.purreturninv";
	public final static String WEBSERVICE_PROCUREMENT_GET_ADJPURRETURN = "webservice.procurement.get.adjpurreturn";
	public final static String WEBSERVICE_PROCUREMENT_GET_ADJPURRETURNBYPURID = "webservice.procurement.get.adjpurreturnbypurid";
	public final static String WEBSERVICE_PROCUREMENT_GET_ADJEXPRETURN = "webservice.procurement.get.adjexpreturn";
	public final static String WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASERETURN = "webservice.procurement.post.all.purreturn";
	// Purchase Order
	public final static String WEBSERVICE_PURCHASEORDER_TEMP_CREATE_FROMSALE = "webservice.temp.purchaseorder.create.fromsale";
	public final static String WEBSERVICE_PURCHASEORDER_CREATE_OR_UPDATE_PURCHASEORDER = "webservice.procurement.create.update.purchaseorder";
	public final static String WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERHEADER_BYID = "webservice.procurement.get.purchaseorderheader.byid";
	public final static String WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYID = "webservice.procurement.get.purchaseorderdetails.byid";
	public final static String WEBSERVICE_PURCHASEORDER_GET_ALL_PURCHASEORDER = "webservice.procurement.get.all.purchaseorder";
	public final static String WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDER_BY_TYPE = "webservice.procurement.get.purchaseorder.by.type";
	public final static String WEBSERVICE_PURCHASEORDER_DELETE_PURCHASEORDER = "webservice.procurement.delete.purchaseorder";
	public final static String WEBSERVICE_PURCHASEORDER_POST_PURCHASEORDER = "webservice.procurement.post.purchaseorder";
	public final static String WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYINV = "webservice.procurement.get.purchaseorderdetails.byinv";
	public final static String WEBSERVICE_PURCHASEORDER_CLOSE_PURCHASEORDER = "webservice.procurement.close.purchaseorder";
	public final static String WEBSERVICE_PURCHASEORDER_CAL_PURCHASEORDERQTY = "webservice.procurement.cal.purordrqty";
	// Vendor
	public final static String WEBSERVICE_INV_ADD_VENDOR = "webservice.inv.add.vendor";
	public final static String WEBSERVICE_INV_GET_ALL_VENDOR = "webservice.inv.get.allvendor";
	public final static String WEBSERVICE_INV_EDIT_VENDOR = "webservice.inv.edit.vendor";
	public final static String WEBSERVICE_INV_DELETE_VENDOR = "webservice.inv.delete.vendor";
	public final static String WEBSERVICE_INV_GET_VENDOR_BY_ID = "webservice.inv.vendordetails.by.id";
	public final static String WEBSERVICE_INV_GET_ALLVENDOR_OUTSTANDING = "webservice.inv.get.allvendor.outstanding";
	
	public final static String WEBSERVICE_INV_GET_ALLVENDOR_LEGER = "webservice.inv.get.allvendor.ledgersearch";
	public final static String WEBSERVICE_INV_GET_ALL_LEGER_BY_GROUP_CODE = "webservice.inv.get.allvendor.ledgersearch";
	
	
	// Vendor Payment
	public final static String WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTDETAILS = "webservice.procurement.get.allpaymentdetails";
	public final static String WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTHEADER_BYID = "webservice.procurement.get.allpaymentheader.byid";
	public final static String WEBSERVICE_PROCUREMENT_GET_VENDOR_ALLPAYMENTDETAILS_BYID = "webservice.procurement.get.allpaymentdetails";
	public final static String WEBSERVICE_PROCUREMENT_GET_VENDOR_PAYMENTDETAILS_BYID = "webservice.procurement.get.allpaymentdetails.byid";
	public final static String WEBSERVICE_PROCUREMENT_GET_VENDOR_PENDINGPAYMENT_BYSUPPLIER = "webservice.procurement.get.pendingpayment.bysupplier";
	public final static String WEBSERVICE_PROCUREMENT_GET_PAYMENTMODES = "webservice.procurement.get.paymentmodes";
	public final static String WEBSERVICE_PROCUREMENT_CREATE_UPDATE_DISTRIBUTORPAYMENT = "webservice.procurement.create.update.distributorpayment";
	public final static String WEBSERVICE_PROCUREMENT_POST_DISTRIBUTORPAYMENT = "webservice.procurement.post.distributorpayment";
	/* End Procurement Module*/
	/* Store Mgnt Module*/
	public final static String WEBSERVICE_STORE_GETSTOREDETAILSBYID = "webservice.store.getstoredetailsbyid";
	public final static String WEBSERVICE_STORE_GETALLFINANCILAYEARS = "webservice.store.getallfinancialyears";
	/* End Store Mgnt Module*/
	/* Accounts Module*/
 
	public final static String WEBSERVICE_INV_GET_ALL_ACCGROUP = "webservice.acc.getall.accgroup";
	public final static String WEBSERVICE_INV_GET_ALL_ACCTYPE = "webservice.acc.getall.acctype";
	public final static String WEBSERVICE_ACC_ADD_GROUP = "webservice.acc.add.group";
	public final static String WEBSERVICE_ACC_UPDATE_GROUP = "webservice.acc.update.group";
	public final static String WEBSERVICE_ACC_DELETE_ACCGROUP = "webservice.acc.delete.accgroup";
	public final static String WEBSERVICE_ACC_SETUP_NEWACCOUNT = "webservice.acc.setup.addaccount";
	public final static String WEBSERVICE_ACC_SETUP_UPDATEACCOUNT ="webservice.acc.setup.updateaccount";
	public final static String WEBSERVICE_ACC_SETUP_GETALLACCOUNTSETUP = "webservice.acc.setup.getallaccount";
	public final static String WEBSERVICE_ACC_SETUP_DELETE = "webservice.acc.setup.deleteaccount";
	public final static String WEBSERVICE_ACC_SETUP_ALREAD_EXIST = "webservice.acc.setup.check_acc_already_exist";
	public final static String WEBSERVICE_ACC_SEARCH_LEDGER = "webservice.acc.setup.searchledger";
	public final static String WEBSERVICE_ACC_SEARCH_LEDGER_BANK = "webservice.acc.setup.searchledger_cash_bank";
	public final static String WEBSERVICE_CHART_OF_ACCOUNT = "webservice.acc.setup.chartofaccount";
	

	/*
	 * FOR JOURNAL 
	 */
	public final static String WEBSERVICE_ACC_ENTRY_TYPE = "webservice.acc.setup.entrytypes";
	public final static String WEBSERVICE_ACC_ADD_JOURNAL = "webservice.acc.setup.addjournal";
	public final static String WEBSERVICE_ACC_DEL_JOURNAL = "webservice.acc.setup.deljournal";
	 
	public final static String WEBSERVICE_ACC_GET_JOURNAL_LIST ="webservice.acc.setup.getjournallist";
	
	public final static String WEBSERVICE_ACC_GET_JOURNAL_BY_ID ="webservice.acc.setup.editjournallist";
	
	public final static String WEBSERVICE_ACC_UPDATE_JOURNAL ="webservice.acc.setup.updatejournal";
	
	/*
	 * for account report 
	 */
	
	public final static String WEBSERVICE_ACCOUNT_REPORT_SEARCH_BY_GROUP ="webservice.acc.setup.account_report_leger_search_by_group";
	public final static String WEBSERVICE_ACCOUNT_REPORT ="webservice.acc.setup.account_report";
	public final static String WEBSERVICE_ACCOUNT_TRIAL_BALANCE ="webservice.acc.setup.trialbalance";
	public final static String WEBSERVICE_ACCOUNT_BALANCE_SHEET ="webservice.acc.setup.balance_sheet";
	public final static String WEBSERVICE_ACCOUNT_BALANCE ="webservice.acc.setup.account_balance_report";
	
	public final static String WEBSERVICE_ACCOUNT_PROFIT_AND_LOSS="webservice.acc.setup.profitandloss";
	
	
	public final static String WEBSERVICE_ACCOUNT_DAILY_COLLETION ="webservice.acc.setup.dailycollection_acc";
	public final static String WEBSERVICE_ACCOUNT_DAILY_PAYMENT ="webservice.acc.setup.dailypayment_acc";
	
	
	
	
	/* End Accounts Module*/
	 
	/* Report Module*/
	public final static String WEBSERVICE_REP_INV_STOCKREGIS = "webservice.rep.inv.stockregis";
	public final static String WEBSERVICE_REP_INV_EXPREGIS = "webservice.rep.inv.expregis";
	public final static String WEBSERVICE_REP_INV_EXPDISTWISE = "webservice.rep.inv.expdistwise";
	public final static String WEBSERVICE_REP_INV_STOCK_GROUP_WISE = "webservice.rep.inv.stockgrpwise";
	public final static String WEBSERVICE_REP_POS_SALESUMMARY = "webservice.rep.pos.salesummary";
	public final static String WEBSERVICE_REP_POS_SALEREGIS = "webservice.rep.pos.saleregis";
	public final static String WEBSERVICE_REP_POS_SALESITEM = "webservice.rep.pos.salesitem";
	public final static String WEBSERVICE_REP_POS_SALERETURNSUMMARY = "webservice.rep.pos.salereturnsummary";
	public final static String WEBSERVICE_REP_POS_SALERETURNREGIS = "webservice.rep.pos.salereturnregis";
	public final static String WEBSERVICE_REP_POS_SALESRETURNITEM = "webservice.rep.pos.salesreturnitem";
	public final static String WEBSERVICE_REP_PROC_PURCHASESUMMARY = "webservice.rep.proc.purchasesummary";
	public final static String WEBSERVICE_REP_PROC_PURCHASEREGIS = "webservice.rep.proc.purchaseregis";
	public final static String WEBSERVICE_REP_PROC_PURCHASEITEM = "webservice.rep.proc.purchaseitem";
	public final static String WEBSERVICE_REP_PROC_PURCHASERETURNSUMMARY = "webservice.rep.proc.purchasereturnsummary";
	public final static String WEBSERVICE_REP_PROC_PURCHASERETURNREGIS = "webservice.rep.proc.purchasereturnregis";
	public final static String WEBSERVICE_REP_PROC_PURCHASERETURNITEM = "webservice.rep.proc.purchasereturnitem";
	public final static String WEBSERVICE_REP_TAX_TAXINTEGRITY = "webservice.rep.tax.taxintegrity";
	public final static String WEBSERVICE_REP_TAX_TAXSUMMARY = "webservice.rep.tax.taxsummary";
	public final static String WEBSERVICE_REP_TAX_TAXSLABSUMMARY = "webservice.rep.tax.taxslabsummary";
	public final static String WEBSERVICE_REP_PROC_PURCHASE_FREEQTY = "webservice.rep.proc.purchase.freeqty";
	public final static String WEBSERVICE_REP_INV_NONMOVING_ITEM = "webservice.rep.inv.nonmoving.item";
	public final static String WEBSERVICE_REP_INV_STOCKONVALUE = "webservice.rep.inv.stockonvalue";
	public final static String WEBSERVICE_REP_CUSTOMER_LEDGERREPORT = "webservice.rep.customer.ledgerreport";
	public final static String WEBSERVICE_REP_VENDOR_LEDGERREPORT = "webservice.rep.vendor.ledgerreport";
	public final static String WEBSERVICE_REP_PROC_PURCHASE_ORDER_ADJ = "webservice.rep.proc.purchase.order.adj.report";
	public final static String WEBSERVICE_REP_TAX_BTWOCS = "webservice.rep.tax.btwocs";
	public final static String WEBSERVICE_REP_POS_ESIONGC_SALEREGIS = "webservice.rep.pos.esiongc.saleregis";
	public final static String WEBSERVICE_REP_POS_SCHEDULEH1REG = "webservice.rep.pos.scheduleh1reg";
	public final static String WEBSERVICE_REP_POS_TBREG = "webservice.rep.pos.tbreg";
	public final static String WEBSERVICE_REP_TAX_HSN = "webservice.rep.tax.hsn";
	public final static String WEBSERVICE_REP_INV_VENDORWISE_STOCK = "webservice.rep.inv.vendorwise.stock";
	public final static String WEBSERVICE_REP_INV_MANUFACTURERWISE_STOCK = "webservice.rep.inv.manufacturerwise.stock";
	public final static String WEBSERVICE_REP_TAX_GSTR3B = "webservice.rep.tax.gstr3b";
	public final static String WEBSERVICE_REP_PROC_HREGIS = "webservice.rep.proc.hregis";
	public final static String WEBSERVICE_REP_PROC_H1REGIS = "webservice.rep.proc.h1regis";
	public final static String WEBSERVICE_REP_PROC_NARCOREGIS = "webservice.rep.proc.narcoregis";
	public final static String WEBSERVICE_REP_TAX_GSTR9A = "webservice.rep.tax.gstr9a";
	
	
	/* End Report Module*/
	
	/* Start Mail Section */
	public final static String WEBSERVICE_MAIL_SENDMAIL = "webservice.mail.sendmail";
	/* End Mail Section  */
	
	public final static String GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER = "general.setting.profit.percentage.of.retailer";
	public final static String GENERAL_SETTINGS_OF_NO_OF_DUEDAYS = "general.setting.no.of.duedates";
	public final static String GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE = "general.setting.purchase.orderQty.from.sale";
	public final static String GENERAL_SETTING_SALEBILL_DOTMATRIX_PRINT = "general.setting.salebill.printertype";
	public final static String GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE = "general.setting.salebill.dotmatrix.noteline.one";
	public final static String GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO = "general.setting.salebill.dotmatrix.noteline.two";
	public final static String GENERAL_SETTINGS_OF_EXPALERT_REQUIRED = "general.setting.expalert.required";
	public final static String GENERAL_SETTINGS_OF_SALE_HISTORY_DAY = "general.setting.sale.history.day";
	public final static String GENERAL_SETTINGS_OF_DAY_TO_PURCHASE = "general.setting.day.to.purchase";
	public final static String GENERAL_SETTING_SALEITEM_DETAILS_NOOFDAYS = "general.setting.saleitem.details.noofdays";
	
	
	
	//Area
	public final static String WEBSERVICE_INV_CREATE_AREA = "webservice.inv.create.area";
	public final static String WEBSERVICE_INV_EDIT_AREA = "webservice.inv.edit.area";
	public final static String WEBSERVICE_INV_DELETE_AREA = "webservice.inv.delete.area";
	public final static String WEBSERVICE_INV_GET_AREA_BYID = "webservice.inv.get.area.byid";
	public final static String WEBSERVICE_INV_GET_ALL_AREA = "webservice.inv.getall.area";
	
	
	//Zone
		public final static String WEBSERVICE_INV_CREATE_ZONE = "webservice.inv.create.zone";
		public final static String WEBSERVICE_INV_EDIT_ZONE = "webservice.inv.edit.zone";
		public final static String WEBSERVICE_INV_DELETE_ZONE = "webservice.inv.delete.zone";
		public final static String WEBSERVICE_INV_GET_ALLZONE_BYCITY = "webservice.inv.get.allzone.bycity";
		public final static String WEBSERVICE_INV_GET_ZONE_BYID = "webservice.inv.get.zone.byid";
		public final static String WEBSERVICE_INV_GET_ALL_ZONE = "webservice.inv.getall.zone";
	
	//City
	public final static String WEBSERVICE_INV_GET_ALLCOUNTRY = "webservice.inv.get.allcountry";
	public final static String WEBSERVICE_INV_GET_ALLSTATE_BYCOUNTRY = "webservice.inv.get.allstate.bycountry";
	public final static String WEBSERVICE_INV_GET_ALLCITY_BYSTATE = "webservice.inv.get.allcity.bystate";
	public final static String WEBSERVICE_INV_CREATE_CITY = "webservice.inv.create.city";
	public final static String WEBSERVICE_INV_EDIT_CITY = "webservice.inv.edit.city";
	public final static String WEBSERVICE_INV_DELETE_CITY = "webservice.inv.delete.city";
	public final static String WEBSERVICE_INV_GET_CITY_BYID = "webservice.inv.get.city.byid";
	public final static String WEBSERVICE_INV_GET_CITYLIST_BYNAME = "webservice.inv.get.citylist.byname";
	
	
	// dashboard

		public final static String  WEBSERVICE_DASHBOARD= "webservice.dashboard";
		public final static String  WEBSERVICE_DASHBOARD_ALLSTORE= "webservice.dashboard.allstore";
		public final static String  WEBSERVICE_DASHBOARD_LINECHART= "webservice.dashboard.linechart";
		public final static String  WEBSERVICE_DASHBOARD_PIECHART= "webservice.dashboard.piechart";
		public final static String  WEBSERVICE_DASHBOARD_ALLSTORE_NEW= "webservice.dashboard.allstorenew";

	
	/*
	 * for admin
	 */
	/* USER*/
	public final static String WEBSERVICE_ADMIN_ADD_USER = "webservice.admin.add.user";
	public final static String WEBSERVICE_ADMIN_GET_ALL_USER = "webservice.admin.get.alluser";
	public final static String WEBSERVICE_ADMIN_EDIT_USER="webservice.admin.edit.user";
	public final static String WEBSERVICE_ADMIN_DELETE_USER = "webservice.admin.delete.user";
	public final static String WEBSERVICE_ADMIN_GET_USER_BY_ID = "webservice.admin.get.userbyid";
	
	/* ROLE*/
	public final static String WEBSERVICE_ADMIN_ADD_ROLE = "webservice.admin.add.role";
	public final static String WEBSERVICE_ADMIN_UPDATE_ROLE = "webservice.admin.update.role";
	public final static String WEBSERVICE_ADMIN_GET_ALL_ROLE = "webservice.inv.get.allrole";
	public final static String WEBSERVICE_ADMIN_GET_ROLE_BY_ID = "webservice.admin.get.rolebyid";
	public final static String WEBSERVICE_ADMIN_DELETE_ROLE_BY_ID = "webservice.admin.delete.rolebyid";
	
	/*USER ROLE MAPPING*/
	public final static String WEBSERVICE_ADMIN_ADD_ROLEUSERMAPPING = "webservice.admin.add.roleusermapping";
	public final static String WEBSERVICE_ADMIN_UPDATE_ROLEUSERMAPPING = "webservice.admin.update.roleusermapping";
	public final static String WEBSERVICE_ADMIN_GET_ALL_ROLEUSERMAPPING = "webservice.inv.get.allroleusermapping";
	public final static String WEBSERVICE_ADMIN_DELETE_ROLEUSERMAPPING_BY_ID = "webservice.admin.delete.roleusermappingbyid";
	
	
	private static Properties getProperties() {
		logger.info("CommonResource initialized [+] ...");
		try {
			Properties properties = new Properties();
			properties.load(CommonResource.class.getClassLoader().getResourceAsStream("com/sharobi/yewpos/resources/commonResource.properties"));
			logger.debug("CommonResource properties loaded");
			return properties;
		} catch (Exception ex) {
			logger.error("Failed to initialize CommonResource [-] ", ex);
			return null;
		}
	}

	public static String getProperty(String key) {
		return resourceProperties.getProperty(key);
	}

}
