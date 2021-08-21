package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

public class ReprintCashMemoService {
	private final static Logger logger=LogManager.getLogger(CashMemoService.class);
	private final static Gson gson = new Gson();
	
	public SaleHeaderDTO getSalesHeader(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_HEADER_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
			saleHeaderDTO = gson.fromJson(responseString, new TypeToken<SaleHeaderDTO>() {}.getType());
			return saleHeaderDTO;
		} catch (Exception ex) {
			logger.debug("Exception in getSalesHeader ", ex);
			return null;
		}
	}
	
	public List<SaleDetailsDTO> getSaleDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_DETAILS_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<SaleDetailsDTO> saleDetailsDTOsList = new ArrayList<SaleDetailsDTO>();
			saleDetailsDTOsList = gson.fromJson(responseString, new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			return saleDetailsDTOsList;
		} catch (Exception ex) {
			logger.debug("Exception in getSaleDetails ", ex);
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
			logger.debug("Exception in getSaleDetails ", ex);
			return null;
		}
	}
	
	public String getPrintCountRes(int id,String flag)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CASHMEMO_REPRINT_COUNT).replace("{1}", String.valueOf(id)).replace("{2}", flag);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string get cashmemo print count status: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getPrintCountRes ",ex);
			return null;
		}
		
	}

}
