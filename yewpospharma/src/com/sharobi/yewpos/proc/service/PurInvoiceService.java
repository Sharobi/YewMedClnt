/**
 * 
 */
package com.sharobi.yewpos.proc.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.proc.model.BarcodePrintParam;
import com.sharobi.yewpos.proc.model.BarcodePrintParamList;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseInvoiceExcelDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author habib,Manodip
 *
 */
public class PurInvoiceService {
	private final static Logger logger=LogManager.getLogger(PurInvoiceService.class);
	private final static Gson gson = new Gson();

	public Purchase getPurchaseInvoiceHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEHEADER_BYID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			Purchase purchase = new Purchase();
			purchase = gson.fromJson(responseString, new TypeToken<Purchase>() {}.getType());
			return purchase;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public List<PurchaseDetails> getPurchaseInvoiceDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEDETAILS_BYID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<PurchaseDetails> purchaseDetailsList = new ArrayList<PurchaseDetails>();
			purchaseDetailsList = gson.fromJson(responseString, new TypeToken<List<PurchaseDetails>>() {}.getType());
			return purchaseDetailsList;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public List<PurchaseReturnDTO> getPurchaseReturnHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURNBYPURID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<PurchaseReturnDTO> purchaseReturnHeader = new ArrayList<PurchaseReturnDTO>();
			purchaseReturnHeader = gson.fromJson(responseString, new TypeToken<List<PurchaseReturnDTO>>() {}.getType());
			return purchaseReturnHeader;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String getPurInvDetailsBySku(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYSKU);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	/**
	 * 
	 * @author Manodip Jana
	 * @param commonResultSetMapper, itemid,distributorid
	 * @return List<PurchaseDetails>
	 * @used for Purchase Invoice item search
	 * 
	 */
	public String getPurchaseInvoiceDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEDETAILS_BYID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public List<Purchase> getAllPurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ALL_PURCHASEDETAILS);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<Purchase> purchaseList = new ArrayList<Purchase>();
			purchaseList = gson.fromJson(responseString, new TypeToken<List<Purchase>>() {}.getType());
			return purchaseList;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String createOrUpdatePurchaseInvoice(Purchase purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_OR_UPDATE_PURCHASEINVOICE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String deletePurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_DELETE_PURCHASEINVOICE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String postPurchaseInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_PURCHASEINVOICE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	/**
	 * 
	 * @author Manodip Jana
	 * @param commonResultSetMapper, itemid,distributorid
	 * @return List<PurchaseHistoryDTO>
	 * @used for Purchase Invoice History during item search
	 * 
	 */
	public String getPurchaseInvoiceHistoryDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_PURCHASEINVOICEHISTORY);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	/*
	 * 
	 */

	public String uploadPurInvFromExcel(InputStream inputFile,MultiPart multipart,PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_PURINV_FROMEXCEL);
			logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPostDataPurInvExc(url,inputFile,multipart,purchaseInvoiceExcelDTO);
			String responseString=response.getEntity(String.class);
			logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception",ex);
			return null;	
		}
	}
	
	public String postAllSelectedPurchaseInv(Purchase purchase) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASEINVOICE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchase));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String checksamebillonvendor(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CHECK_DUPLICATE_BILL);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	
	


	public String printByBarcode(BarcodePrintParam barcodePrintParamObj) {
		try {
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE);

			logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(barcodePrintParamObj));
			String responseString=response.getEntity(String.class);
			logger.info("Response string get cashmemo print count status: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}

	public String printByBarcodeAll(BarcodePrintParamList barcodePrintParamObj) {
		try {
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_PRINT_BY_BARCODE_ALL);

			logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(barcodePrintParamObj));
			String responseString=response.getEntity(String.class);
			logger.info("Response string get cashmemo print count status: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception", ex);
			return null;
		}
	}
}
