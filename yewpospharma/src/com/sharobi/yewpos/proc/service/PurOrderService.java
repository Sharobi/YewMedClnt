/**
 * 
 */
package com.sharobi.yewpos.proc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseOrder;
import com.sharobi.yewpos.proc.model.PurchaseOrderDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDetailsDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

public class PurOrderService {
	private final static Logger logger=LogManager.getLogger(PurInvoiceService.class);
	private final static Gson gson = new Gson();
	
	public String createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CREATE_OR_UPDATE_PURCHASEORDER);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseOrder));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String createTempPOFromSale(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_TEMP_CREATE_FROMSALE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}

	public PurchaseOrderDTO getPurchaseOrderHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERHEADER_BYID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
			purchaseOrder = gson.fromJson(responseString, new TypeToken<PurchaseOrderDTO>() {}.getType());
			return purchaseOrder;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public List<PurchaseOrderDetailsDTO> getPurchaseOrderDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYID);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<PurchaseOrderDetailsDTO> purchaseOrderDetailsList = new ArrayList<PurchaseOrderDetailsDTO>();
			purchaseOrderDetailsList = gson.fromJson(responseString, new TypeToken<List<PurchaseOrderDetailsDTO>>() {}.getType());
			return purchaseOrderDetailsList;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String getPurOrderDetailsByOrderNoForPI(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDERDETAILS_BYINV);
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
	
	public List<PurchaseOrderDTO> getAllPurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_ALL_PURCHASEORDER);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<PurchaseOrderDTO> purchaseOrderList = new ArrayList<PurchaseOrderDTO>();
			purchaseOrderList = gson.fromJson(responseString, new TypeToken<List<PurchaseOrderDTO>>() {}.getType());
			return purchaseOrderList;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String getPurOrderByType(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_GET_PURCHASEORDER_BY_TYPE);
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
	
	public String deletePurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_DELETE_PURCHASEORDER);
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
	
	public String postPurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_POST_PURCHASEORDER);
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
	
	public String closePurchaseOrder(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CLOSE_PURCHASEORDER);
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
	
	public String calPurOrdrQty(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PURCHASEORDER_CAL_PURCHASEORDERQTY);
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
}
