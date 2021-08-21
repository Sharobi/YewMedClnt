/**
 * 
 */
package com.sharobi.yewpos.role.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib,Manodip
 * 
 */
public class RoleService {

	private final static Logger logger = LogManager.getLogger(RoleService.class);
	private final static Gson gson = new Gson();

	public List<MenuByUserDTO> getAppMenuByUser(int compId,
												int storeId,
												int userId,
												int productTypeId) {
		List<MenuByUserDTO> appMenuList = new ArrayList<MenuByUserDTO>();
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_ROLE_GETAPPMENUBYUSER).replace("{1}", String.valueOf(compId)).replace("{2}", String.valueOf(storeId)).replace("{3}", String.valueOf(userId)).replace("{4}", String.valueOf(productTypeId));
			//System.out.println("getAppMenuByUser url="+url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.debug("all app menu string fetched: {}", responseString);
			appMenuList = gson.fromJson(responseString, new TypeToken<List<MenuByUserDTO>>() {}.getType());
			//logger.debug("all app menu List fetched: {}", appMenuList);
			return appMenuList;
		} catch (Exception ex) {
			logger.debug("Exception in getAppMenuByUser ", ex);
			return null;
		}
	}

	public MenuByUserDTO getRoleMenuByUser(	int compId,
											int storeId,
											int userId,
											int productTypeId,
											int menuId,
											String lang) {
		try {
			MenuByUserDTO roleMenu = new MenuByUserDTO();
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_ROLE_GETSPECIFICMENUROLEBYUSER).replace("{1}", String.valueOf(compId)).replace("{2}", String.valueOf(storeId)).replace("{3}", String.valueOf(userId)).replace("{4}", String.valueOf(menuId)).replace("{5}", String.valueOf(productTypeId)).replace("{6}", lang);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.debug("all roleMenu string fetched: {}", responseString);
			roleMenu = gson.fromJson(responseString, new TypeToken<MenuByUserDTO>() {}.getType());
			//logger.debug("all roleMenu List fetched: {}", roleMenu);
			return roleMenu;
		} catch (Exception ex) {
			logger.debug("Exception in getRoleMenuByUser ", ex);
			return null;
		}
	}

}
