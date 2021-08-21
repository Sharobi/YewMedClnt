package com.sharobi.yewpos.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sharobi.yewpos.admin.model.CommonMapData;
import com.sharobi.yewpos.admin.model.UserRoleMapping;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class MappingService {
	private final static Logger logger = LoggerFactory.getLogger(MappingService.class);
	 Gson gson = new Gson();
	public String addMapping(UserRoleMapping mapObj) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_ADD_ROLEUSERMAPPING);
			//logger.info("url....{}",url);
			//logger.info("data....{}",gson.toJson(mapObj));
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(mapObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string add mapping...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addMapping ",ex);
			return null;
		}
	}
	public String updateMapping(UserRoleMapping mapObj) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_UPDATE_ROLEUSERMAPPING);
			//logger.info("url....{}",url);
			//logger.info("data....{}",gson.toJson(mapObj));
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(mapObj));
			String responseString=response.getEntity(String.class);
			//logger.info("response string update mapping...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in updateMapping ",ex);
			return null;
		}
	}
	public String deleteMapping(CommonMapData obj) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_DELETE_ROLEUSERMAPPING_BY_ID);
			//logger.info("url....{}", url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(obj));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in delete mapping: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.info("Exception in deleteMapping ", ex);
			return null;
		}
	}
	public String getAllMapping(int companyId) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_GET_ALL_ROLEUSERMAPPING)+ String.valueOf(companyId);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get mapping by company id: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllMapping ",ex);
			return null;
		}
	}
}
