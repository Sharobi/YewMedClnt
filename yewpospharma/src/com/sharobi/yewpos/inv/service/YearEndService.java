/**
 * 
 */
package com.sharobi.yewpos.inv.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Arunima Roy
 *
 * Mar 23, 2018
 */
public class YearEndService {
	private final static Logger logger = LogManager.getLogger(BrandService.class);
	private final static Gson gson = new Gson();
	
	public String stkTrnsrFrYrend(CommonResultSetMapper commonResultMapObj) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STK_TRNSFR_YR_END);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultMapObj));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in stkTrnsrFrYrend ", ex);
			return null;
		}
	}
}
