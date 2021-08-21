/**
 * 
 */
package com.sharobi.yewpos.storemgnt.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.BrandMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.storemgnt.model.FinYrMaster;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.EmailBean;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib
 *
 */
public class StoreMgntService {
	
	private final static Logger logger=LogManager.getLogger(StoreMgntService.class);
	private final static Gson gson=new Gson();
	
	public StoreMaster getStoreDetailsById(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_GETSTOREDETAILSBYID).replace("{1}", String.valueOf(id));
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get store by id: {}", responseString);
			StoreMaster store=gson.fromJson(responseString, StoreMaster.class);
			return store;
		}catch (Exception e) {
			logger.error("Exception: in getStoreDetailsById ", e);
			return null;
		}
	}
	
	public String sendMail(EmailBean emailBean) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_MAIL_SENDMAIL);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(emailBean));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in sendMail ", ex);
			return null;
		}
	}

	
	public List<FinYrMaster> getAllFinancilaYears(int companyId, int storeId) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_STORE_GETALLFINANCILAYEARS);
			CommonResultSetMapper commonobj = new CommonResultSetMapper();
			commonobj.setStoreId(storeId);
			commonobj.setCompanyId(companyId);
			//logger.info("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonobj));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in getgetAllFinancilaYears: {}", responseString);
			List<FinYrMaster> allFinYears=gson.fromJson(responseString, new TypeToken<List<FinYrMaster>>() {}.getType());
			return allFinYears;
		}catch (Exception e) {
			logger.error("Exception: in getAllFinancilaYears ", e);
			return null;
		}
	}

}
