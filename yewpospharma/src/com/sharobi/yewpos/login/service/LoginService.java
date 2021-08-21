/**
 * 
 */
package com.sharobi.yewpos.login.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip,habib
 *
 */
public class LoginService {
	
	private final static Logger logger=LogManager.getLogger(LoginService.class);
	private final static Gson gson = new Gson();
	
	public LoginInfoByUserDTO login(LoginInfoByUserDTO user)
	{
		try {
		ClientResponse response=WebServiceClient.callPost(CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_LOGIN_DOLOGIN), gson.toJson(user));
		String responseString=response.getEntity(String.class);
		//logger.debug("Response string: {}", responseString);
		LoginInfoByUserDTO loginInfo = gson.fromJson(responseString, LoginInfoByUserDTO.class);
		return loginInfo;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
