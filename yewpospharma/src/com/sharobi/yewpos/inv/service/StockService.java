/**
 * 
 */
package com.sharobi.yewpos.inv.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ItemCurrentStockDTO;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.MenuFile;
import com.sharobi.yewpos.inv.model.OpeningStock;
import com.sharobi.yewpos.inv.model.StockDetailsAdjustmentDTO;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author habib,Manodip
 *
 */
public class StockService {
	
	private final static Logger logger=LogManager.getLogger(StockService.class);
	private final static Gson gson = new Gson();
	
	public String getCurrentStockOfItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCurrentStockOfItem ", ex);
			return null;
		}
	}
	
	public String getCurrentStockOfItemByBarcode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRSTOCK_OFITEM_BYBARCODE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCurrentStockOfItemByBarcode ", ex);
			return null;
		}
	}
	
	public String createorupdateStock(OpeningStock stockStringObj) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_CREATE_OR_UPDATE_STOCKMANUAL);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(stockStringObj));
			String responseString = response.getEntity(String.class);
		//	System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in createorupdateStock ", ex);
			return null;
		}
	}
	
	public String uploadstockexcel(InputStream inputFile,MultiPart multipart,CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_UPLOADFILE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPostData(url,inputFile,multipart,commonResultSetMapper);
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in uploadstockexcel ",ex);
			return null;	
		}
	}
	
	public String getCurrentStockOfItemByBatExpMrp(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETCURRENTSTOCK_OFITEM_BYBATCHEXPMRP);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			System.out.println("responseString: in uploadstockexcel " + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception", ex);
			return null;
		}
	}
	
	public List<StockDetailsAdjustmentDTO> getStockDetailsForAdjustment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_GETSTOCKDETAILS_FORADJUSTMENT);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<StockDetailsAdjustmentDTO> stockDetailsAdjustmentDTOs = new ArrayList<StockDetailsAdjustmentDTO>();
			stockDetailsAdjustmentDTOs = gson.fromJson(responseString, new TypeToken<List<StockDetailsAdjustmentDTO>>() {}.getType());
			return stockDetailsAdjustmentDTOs;
		} catch (Exception ex) {
			logger.debug("Exception in getStockDetailsForAdjustment ", ex);
			return null;
		}
	}
	
	public ResponseObj updateStkAdj(StockDetailsAdjustmentDTO stockDetailsAdjustmentDTO) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_STOCKDETAILS_UPDATE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(stockDetailsAdjustmentDTO));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.debug("Exception in updateStkAdj ", ex);
			return null;
		}

	}
	public ResponseObj deleteStkAdj(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_STOCK_STOCKDETAILS_DELETE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.debug("Exception in deleteStkAdj ", ex);
			return null;
		}

	}

}
