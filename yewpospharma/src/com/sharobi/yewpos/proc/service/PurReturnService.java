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
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseReturn;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,habib
 * 
 */
public class PurReturnService {
	private final static Logger logger = LogManager.getLogger(PurReturnService.class);
	private final static Gson gson = new Gson();

	public List<PurchaseReturnDTO> getAllPurInvReturnMemoInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_ALL_PURCHASE);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			List<PurchaseReturnDTO> purchaseReturnDTOs = new ArrayList<PurchaseReturnDTO>();
			purchaseReturnDTOs = gson.fromJson(responseString, new TypeToken<List<PurchaseReturnDTO>>() {}.getType());
			return purchaseReturnDTOs;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}

	public String getPurInvReturnMemoHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEHEADER_BYID);
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

	public String getPurInvReturnMemoDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYID);
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
	
	public String getPurInvDetailsByInvNoForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYINVNO);
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
	
	public String getPurInvDetailsByItemIdForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYITEMID);
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
	
	public String getPurInvDetailsBySkuForRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_RETURN_GET_PURCHASEDETAILS_BYISKU);
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
	
	public String createOrUpdatePurReturnInvoice(PurchaseReturn purchaseReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_CREATE_UPDATE_PURRETURNINV);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseReturn));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public String deletePurRetInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_DELETE_PURRETURNINV);
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
	
	public String postPurRetInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_PURRETURNINV);
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
	
	public String getPrevRetAdjDetailsByPid(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURNBYPURID);
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
	
	public String getReturnMemoDetailsForAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJPURRETURN);
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
	
	public String getReturnExpDetailsForAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_GET_ADJEXPRETURN);
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
	
	public String postAllSelectedPurchaseReturn(PurchaseReturn purchaseReturn) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_PROCUREMENT_POST_ALL_PURCHASERETURN);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(purchaseReturn));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
}
