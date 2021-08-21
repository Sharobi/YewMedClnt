package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.EsiCodeMaster;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.Sales;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip
 * 
 */

public class CashMemoService {
	private final static Logger logger = LogManager.getLogger(CashMemoService.class);
	private final static Gson gson = new Gson();

	public String getSalesHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_HEADER_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			//			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSalesHeader ", ex);
			return null;
		}
	}

	public String getSalesHeaderForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_HEADER_BYID_FORBILL);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//ystem.out.println("getSalesHeaderForBill responseString:" + responseString);
			//			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			//			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSalesHeaderForBill ", ex);
			return null;
		}
	}

	public String getSaleDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DETAILS_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			///System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSaleDetails ", ex);
			return null;
		}
	}

	public String getSaleDetailsForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DETAILS_BYID_FORBILL);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("getSaleDetailsForBill responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSaleDetailsForBill ", ex);
			return null;
		}
	}

	public String getTaxDetailsForBill(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_TAXDETAILS_FORBILL);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("getTaxDetailsForBill responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getTaxDetailsForBill ", ex);
			return null;
		}
	}

	public List<SaleDetailsAllDTO> getAllSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GET_ALLSALEDETAILS);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleDetailsAllDTO> saleDetailsAllDTOs = new ArrayList<SaleDetailsAllDTO>();
			saleDetailsAllDTOs = gson.fromJson(responseString, new TypeToken<List<SaleDetailsAllDTO>>() {}.getType());
			return saleDetailsAllDTOs;
		} catch (Exception ex) {
			logger.debug("Exception in getAllSalesInvoice ", ex);
			return null;
		}
	}

	public String createOrUpdateSalesInvoice(Sales sales) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_CREATEORUPDATE_SALEDETAILS);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(sales));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in createOrUpdateSalesInvoice ", ex);
			return null;
		}
	}
	
	public String postAllSelSalesInvoice(Sales sales) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_POSTALLSALESINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(sales));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in postAllSelSalesInvoice ", ex);
			return null;
		}
	}

	public String deleteSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_DELETESALESINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in deleteSalesInvoice ", ex);
			return null;
		}
	}

	public String postSalesInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALES_POSTSALESINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in postSalesInvoice ", ex);
			return null;
		}
	}

	public String getCustomerLastSalesHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_GETCUSTLASTSALEHEADER);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerLastSalesHeader ", ex);
			return null;
		}
	}

	public String saleBillPrint(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_PRINT);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			//			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			//			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in saleBillPrint ", ex);
			return null;
		}
	}

	public List<TypeMaster> getTypes(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETTYPES);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<TypeMaster> typeMasterList = new ArrayList<TypeMaster>();
			typeMasterList = gson.fromJson(responseString, new TypeToken<List<TypeMaster>>() {}.getType());
			return typeMasterList;
		} catch (Exception ex) {
			logger.debug("Exception in getTypes ", ex);
			return null;
		}
	}

	public List<EsiCodeMaster> getEsiCode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETESICODES);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<EsiCodeMaster> esiCodeList = new ArrayList<EsiCodeMaster>();
			esiCodeList = gson.fromJson(responseString, new TypeToken<List<EsiCodeMaster>>() {}.getType());
			return esiCodeList;
		} catch (Exception ex) {
			logger.debug("Exception in getEsiCode ", ex);
			return null;
		}
	}
	
	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 
	 */
	public String getPlaceOfTreatmentListAutocom(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_SALEBILL_GETPLACEOFTREATMENT);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			placeOfTreatmentList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return  responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getPlaceOfTreatmentListAutocom ", ex);
			return null;
		}
	}

	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 
	 */
	public List<GenderMaster> getGenderList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_GENDERLIST);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<GenderMaster> genderList = new ArrayList<GenderMaster>();
			genderList = gson.fromJson(responseString, new TypeToken<List<GenderMaster>>() {}.getType());
			return genderList;
		} catch (Exception ex) {
			logger.debug("Exception in getGenderList ", ex);
			return null;
		}
	}
	
	/**
	 * @author Manodip Jana
	 *
	 * @param commonResultSetMapper
	 * @return
	 * 
	 */
	public String getSaleItemDetailsforRet(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_SALEBILL_GETSALEITEMDETAILSFORRETURN);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return  responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSaleItemDetailsforRet", ex);
			return null;
		}
	}
	
	
	/*
	 * for searche ledger
	 */
	
	public List<AccountDTO> searchledger(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_SEARCH_LEDGER_BANK);
			//logger.debug("service searchledger special url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("special string...{}", responseString);
			
			List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
			listAccountDTO = gson.fromJson(responseString, new TypeToken<List<AccountDTO>>(){}.getType());
			//
			return listAccountDTO;
			
			
		
		} catch (Exception ex) {
			logger.debug("Exception in searchledger", ex);
			return null;
		}
	}
	

}
