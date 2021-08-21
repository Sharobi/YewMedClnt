package com.sharobi.yewpos.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sharobi.yewpos.admin.model.CommonMapData;
import com.sharobi.yewpos.admin.model.Role;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;
@Service
public class RoleMgmtService {
	private final static Logger logger = LoggerFactory.getLogger(RoleMgmtService.class);
	 Gson gson = new Gson();
	 
	 public String addRole(Role role) {
			try{
				String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_ADD_ROLE);
				//logger.info("url....{}",url);
				//logger.info("data....{}",gson.toJson(role));
				ClientResponse response=WebServiceClient.callPost(url, gson.toJson(role));
				String responseString=response.getEntity(String.class);
				//logger.info("response string add role...{}",responseString);
				return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in addRole ",ex);
				return null;
			}
		}
	    
		public String updateRole(Role role) {
			try{
				String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_UPDATE_ROLE);
				//logger.info("url....{}",url);
				//logger.info("data....{}",gson.toJson(role));
				ClientResponse response=WebServiceClient.callPost(url, gson.toJson(role));
				String responseString=response.getEntity(String.class);
				//logger.info("response string update role...{}",responseString);
				return responseString;
			}
			catch(Exception ex)
			{
				logger.info("Exception in updateRole ",ex);
				return null;
			}
		}

		public String getRoleById(CommonMapData commonResultSetMapper) {
			try{
				String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_GET_ROLE_BY_ID)+ String.valueOf(commonResultSetMapper.getId());
				//logger.info("url....{}",url);
				ClientResponse response=WebServiceClient.callGet(url);
				String responseString=response.getEntity(String.class);
				//logger.info("Response string in get role by Id: {}", responseString);
				return responseString;
			}catch(Exception ex)
			{
				logger.info("Exception in updateRole ",ex);
				return null;
			}
		}

		public String deleteRole(CommonMapData obj) {
			try {
				String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_DELETE_ROLE_BY_ID);
				//logger.info("url....{}", url);
				ClientResponse response=WebServiceClient.callPost(url, gson.toJson(obj));
				String responseString = response.getEntity(String.class);
				//logger.info("Response string in delete role: {}", responseString);
				return responseString;
			} catch (Exception ex) {
				logger.info("Exception in deleteRole ", ex);
				return null;
			}
		}

		public String getAllRoles(int companyId) {
			try{
				String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_GET_ALL_ROLE)+ String.valueOf(companyId);
				//logger.info("url....{}",url);
				ClientResponse response=WebServiceClient.callGet(url);
				String responseString=response.getEntity(String.class);
				//logger.info("Response string in get roles by company id: {}", responseString);
				return responseString;
			}catch(Exception ex)
			{
				logger.info("Exception in getAllRoles ",ex);
				return null;
			}
		}

}
