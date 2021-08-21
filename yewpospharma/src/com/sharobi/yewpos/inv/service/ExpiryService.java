package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.Expiry;
import com.sharobi.yewpos.inv.model.ExpiryDTO;
import com.sharobi.yewpos.inv.model.ExpiryDetails;
import com.sharobi.yewpos.inv.model.ExpiryDetailsDTO;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

public class ExpiryService {
	private final static Logger logger = LogManager.getLogger(ExpiryService.class);
	private final static Gson gson = new Gson();
	
	public List<ExpiryDTO> getAllExpiryInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALLEXPIRYDETAILS);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDTO> expiryList = new ArrayList<ExpiryDTO>();
			expiryList = gson.fromJson(responseString, new TypeToken<List<ExpiryDTO>>() {}.getType());
			return expiryList;
		} catch (Exception ex) {
			logger.debug("Exception in getAllExpiryInvoice ", ex);
			return null;
		}
	}
	
	public List<ExpiryDetailsDTO> getAllExpInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETALLPENDINGEXPIRYITEMS);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDetailsDTO> expiryDetailsDTOs = new ArrayList<ExpiryDetailsDTO>();
			expiryDetailsDTOs = gson.fromJson(responseString, new TypeToken<List<ExpiryDetailsDTO>>() {}.getType());
			return expiryDetailsDTOs;
		} catch (Exception ex) {
			logger.debug("Exception in getAllExpInvoice ", ex);
			return null;
		}
	}
	
	public String createOrUpdateExpiryInvoice(Expiry expiry) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_OR_UPDATE_EXPIRYINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(expiry));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in createOrUpdateExpiryInvoice ", ex);
			return null;
		}
	}
	
	public ExpiryDTO getExpiryInvoiceHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_EXPIRYHEADER_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			ExpiryDTO expiryDTO = new ExpiryDTO();
			expiryDTO = gson.fromJson(responseString, new TypeToken<ExpiryDTO>() {}.getType());
			return expiryDTO;
		} catch (Exception ex) {
			logger.debug("Exception in getExpiryInvoiceHeader ", ex);
			return null;
		}
	}
	
	public List<ExpiryDetailsDTO> geExpiryInvoiceDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_EXPIRYDETAILS_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<ExpiryDetailsDTO> expiryDetailsList = new ArrayList<ExpiryDetailsDTO>();
			expiryDetailsList = gson.fromJson(responseString, new TypeToken<List<ExpiryDetailsDTO>>() {}.getType());
			return expiryDetailsList;
		} catch (Exception ex) {
			logger.debug("Exception in geExpiryInvoiceDetails ", ex);
			return null;
		}
	}
	
	public String deleteExpInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_EXPIRYINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in deleteExpInvoice ", ex);
			return null;
		}
	}
	
	public String postExpiryInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_POST_EXPIRYINVOICE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in postExpiryInvoice ", ex);
			return null;
		}
	}
}
