/**
 *
 */
package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.Dashboard;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author SK A SIDDIK
 *
 * Jun 25, 2018
 */
@Service
public class HomeService {
	private final static Logger logger=LoggerFactory.getLogger(HomeService.class);
	Gson gson = new Gson();



	
	public Dashboard getTotal(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_DASHBOARD);
			//logger.info("url....{}", url);
			ClientResponse response =WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in WEBSERVICE_DASHBOARD print: {}", responseString);

			Dashboard cd = new Dashboard();
			cd = gson.fromJson(responseString, Dashboard.class);
			//
			return cd;

		} catch (Exception ex) {
			logger.info("Exception in getTotal ", ex);
			return null;
		}

	}

	public List<StoreMaster>  getAllStore(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					/*+ CommonResource.getProperty(CommonResource.WEBSERVICE_DASHBOARD_ALLSTORE);*/
					  + CommonResource.getProperty(CommonResource.WEBSERVICE_DASHBOARD_ALLSTORE_NEW);
					
			//logger.info("url....{}", url);
			/*ClientResponse response =WebServiceClient.callGet(url);*/
			ClientResponse response =WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in dashboard store  list: {}", responseString);

			List<StoreMaster> cd = new  ArrayList<StoreMaster>();
			cd = gson.fromJson(responseString, new TypeToken<List<StoreMaster>>() {
			}.getType());
			//
			return cd;

		} catch (Exception ex) {
			logger.info("Exception in getAllStore ", ex);
			return null;
		}

	}


	public String  getLineChart(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_DASHBOARD_LINECHART);
			//logger.info("url getLineChart....{}", url);
			ClientResponse response =WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.info("Response string in getLineChart store  list: {}", responseString);

		/*	List<StoreMaster> cd = new  ArrayList<StoreMaster>();
			cd = gson.fromJson(responseString, new TypeToken<List<StoreMaster>>() {
			}.getType());*/
			//
			return responseString;

		} catch (Exception ex) {
			logger.info("Exception in getLineChart", ex);
			return null;
		}

	}
}
