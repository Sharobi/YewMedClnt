/**
 * 
 */
package com.sharobi.yewpos.acc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.acc.model.Chartofaccount;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author SK A SIDDIK
 *
 *         Mar 14, 2018
 */

@Service
public class Chartofaccountservice {
	private final static Logger logger = LogManager.getLogger(Chartofaccountservice.class);
	Gson gson = new Gson();

	public List<Chartofaccount> getAllChartofaccount(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_CHART_OF_ACCOUNT);
			//logger.debug("service getAllChartofaccount url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);

			List<Chartofaccount> chartofaccount = new ArrayList<Chartofaccount>();
			chartofaccount = gson.fromJson(responseString, new TypeToken<List<Chartofaccount>>() {
			}.getType());
			//
			return chartofaccount;
		} catch (Exception ex) {
			logger.debug("Exception in getAllChartofaccount", ex);
			return null;
		}
	}

	/*
	 * for searche ledger in report
	 */

	public List<AccountDTO> searchledgerinreport(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_ACCOUNT_REPORT_SEARCH_BY_GROUP);
			//logger.debug("service searchledger url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);

			List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
			listAccountDTO = gson.fromJson(responseString, new TypeToken<List<AccountDTO>>() {
			}.getType());
			//
			return listAccountDTO;

		} catch (Exception ex) {
			logger.debug("Exception in searchledgerinreport ", ex);
			return null;
		}
	}

}
