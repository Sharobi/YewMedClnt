/**
 * 
 */
package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.TaxDTO;
import com.sharobi.yewpos.inv.model.TaxMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib
 *
 */
public class TaxService {
	
	private final static Logger logger = LogManager.getLogger(TaxService.class);
	private final static Gson gson = new Gson();
	
	public List<TaxDTO> getAllTaxes(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETTAXSBYCOMPID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all taxes: {}", responseString);
			List<TaxDTO> taxMasterList = new ArrayList<TaxDTO>();
			taxMasterList = gson.fromJson(responseString, new TypeToken<List<TaxDTO>>(){}.getType());
			//System.out.println("return result: "+taxMasterList.toString());
			return taxMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllTaxes ",ex);
			return null;
		}
		
	}
	
	public String getSingleTaxs(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GETTAXSBYCOMPID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getSingleTaxs ", ex);
			return null;
		}
	}
	
	public String getTaxListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_AUTOCOMPLETE_GETTAXESBYNAME);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getTaxListAutocomplete ", ex);
			return null;
		}
	}
	
	public String addtax(TaxMaster taxMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_TAX);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(taxMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addtax ",ex);
			return null;	
		}
	}
	
	public String updatetax(TaxMaster taxMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_TAX);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(taxMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in updatetax ",ex);
			return null;	
		}
	}
	
	public String getTaxDetbyId(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_TAXDET_BY_ID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url,gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get tax details by Id: {}", responseString);
			//TaxDTO taxDTOobj = gson.fromJson(responseString, new TypeToken<TaxDTO>() {}.getType());
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getTaxDetbyId ",ex);
			return null;
		}
		
	}
	
	public TaxDTO getTaxById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_TAX_BY_ID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url,gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get tax by Id: {}", responseString);
			TaxDTO taxDTOobj = gson.fromJson(responseString, new TypeToken<TaxDTO>() {}.getType());
			return taxDTOobj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getTaxDetbyId ",ex);
			return null;
		}
		
	}
	
	public String deleteTax(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_TAX);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url,gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete tax: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteTax ",ex);
			return null;
		}
		
	}

}
