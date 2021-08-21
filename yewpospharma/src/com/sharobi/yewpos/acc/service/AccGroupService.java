/**
 * 
 */
package com.sharobi.yewpos.acc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountGroupDTO;
import com.sharobi.yewpos.acc.model.AccountTypeDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.service.BrandService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Arunima Roy SK A SIDDIK
 *
 *         Nov 3, 2017
 */
@Service
public class AccGroupService {
	private final static Logger logger = LogManager.getLogger(BrandService.class);
	Gson gson = new Gson();

	public List<AccountGroupDTO> getAllAccGroup(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_ACCGROUP);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get all account group: {}", responseString);
			List<AccountGroupDTO> accountGrpMasterList = new ArrayList<AccountGroupDTO>();
			accountGrpMasterList = gson.fromJson(responseString, new TypeToken<List<AccountGroupDTO>>() {
			}.getType());
			//System.out.println(accountGrpMasterList);
			return accountGrpMasterList;
		} catch (Exception ex) {
			logger.debug("Exception in getAllAccGroup ", ex);
			return null;
		}

	}

	public List<AccountTypeDTO> getAllAccType(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_ACCTYPE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get all brand: {}", responseString);
			List<AccountTypeDTO> accountTypeList = new ArrayList<AccountTypeDTO>();
			accountTypeList = gson.fromJson(responseString, new TypeToken<List<AccountTypeDTO>>() {
			}.getType());
			return accountTypeList;
		} catch (Exception ex) {
			logger.debug("Exception in getAllAccType ", ex);
			return null;
		}

	}

	public String addAccGroup(AccountGroupDTO accGrpMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_ADD_GROUP);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(accGrpMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in addAccGroup", ex);
			return null;
		}
	}

	public String editAccGroup(AccountGroupDTO accGrpMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_UPDATE_GROUP);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(accGrpMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in editAccGroup ", ex);
			return null;
		}
	}

	public String deleteAccGroup(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACC_DELETE_ACCGROUP);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in delete Account Group: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in deleteAccGroup ", ex);
			return null;
		}

	}

}
