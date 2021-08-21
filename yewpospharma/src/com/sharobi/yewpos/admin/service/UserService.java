package com.sharobi.yewpos.admin.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.service.AccGroupService;
import com.sharobi.yewpos.admin.model.CommonMapData;
import com.sharobi.yewpos.admin.model.User;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	Gson gson = new Gson();

	public List<User> getAllUserList(CommonResultSetMapper commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_GET_ALL_USER)+String.valueOf(commonResultSetMapper.getCompanyId());
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get all user: {}", responseString);
			List<User> userList = new ArrayList<User>();
			userList = gson.fromJson(responseString, new TypeToken<List<User>>(){}.getType());
			return userList;
		}catch(Exception ex)
		{
			logger.info("Exception in getAllUserList ",ex);
			return null;
		}
	}

	public  String addUser(CommonMapData commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_ADD_USER);
			//logger.info("url....{}",url);
			//logger.info("data....{}",gson.toJson(commonResultSetMapper));
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
		//	logger.info("response string add user...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in addUser",ex);
			return null;
		}
	}

	

	public  String editUser(CommonMapData commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_EDIT_USER);
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.info("response string edit user...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.info("Exception in editUser ",ex);
			return null;
		}
	}

	public  String deleteUser(CommonMapData commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_DELETE_USER).replace("{1}", String.valueOf(commonResultSetMapper.getId()));
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in delete user: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in deleteUser ",ex);
			return null;
		}
	}

	public  String getUserById(CommonMapData commonResultSetMapper) {
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ADMIN_GET_USER_BY_ID)+ String.valueOf(commonResultSetMapper.getId());
			//logger.info("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.info("Response string in get user by Id: {}", responseString);
			/*User userobj = gson.fromJson(responseString, new TypeToken<User>() {}.getType());*/
			return responseString;
		}catch(Exception ex)
		{
			logger.info("Exception in getUserById ",ex);
			return null;
		}
	}

}
