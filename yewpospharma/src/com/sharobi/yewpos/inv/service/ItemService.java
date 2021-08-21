package com.sharobi.yewpos.inv.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.BrandMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ItemCurrentStockDTO;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Manodip
 * 
 */
public class ItemService {
	private final static Logger logger = LogManager.getLogger(ItemService.class);
	private final static Gson gson = new Gson();

	public String checkSameItemExist(	String name,int itemid,
										String lang) {
		try {
			name = URLEncoder.encode(name, "UTF-8");
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEM_CHECK_DUPLICATE_NAME).replace("{1}", name.trim()).replace("{2}", String.valueOf(itemid));
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseVal = response.getEntity(String.class);
			return responseVal;
		} catch (Exception ex) {
			logger.debug("Exception in checkSameItemExist ", ex);
			return null;
		}

	}

	public ResponseObj addItem(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.debug("Exception in addItem ", ex);
			return null;
		}
	}

	public String addItemViaAjax(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in addItemViaAjax ", ex);
			return null;
		}
	}
	
	public ItemMaster getItemDetailsById(	int id,
											String lang) {
		try {

			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEMDETAILS_BY_ID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get item by Id: {}", responseString);
			ItemMaster itemMaster = gson.fromJson(responseString, new TypeToken<ItemMaster>() {}.getType());
			return itemMaster;
		} catch (Exception ex) {
			logger.debug("Exception in getItemDetailsById ", ex);
			return null;
		}

	}

	public String getItemDetailsByIdForEdit(int id,	String lang) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEMDETAILS_BY_ID).replace("{1}", String.valueOf(id)).replace("{2}", lang);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callGet(url);
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get item by Id for edit in modal: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getItemDetailsByIdForEdit ", ex);
			return null;
		}
	}
	
	public ResponseObj updateItem(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			ResponseObj responseObj = gson.fromJson(responseString, new TypeToken<ResponseObj>() {}.getType());
			return responseObj;
		} catch (Exception ex) {
			logger.debug("Exception in updateItem ", ex);
			return null;
		}

	}
	
	public String updateItemViaAjax(ItemMaster itemMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_UPDATE_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(itemMaster));
			String responseString = response.getEntity(String.class);
			//logger.debug("response string...{}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in updateItemViaAjax ", ex);
			return null;
		}

	}

	public List<CommonResultSetMapper> searchItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.debug("Exception in searchItem ", ex);
			return null;
		}
	}

	public List<CommonResultSetMapper> searchTransItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_TRANS_ITEM);
			logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.debug("Exception in searchTransItem ", ex);
			return null;
		}
	}
	
	
	public List<CommonResultSetMapper> searchItemAutoComplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_AUTOCOMPLETE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CommonResultSetMapper> commonResultSetList = new ArrayList<CommonResultSetMapper>();
			commonResultSetList = gson.fromJson(responseString, new TypeToken<List<CommonResultSetMapper>>() {}.getType());
			return commonResultSetList;
		} catch (Exception ex) {
			logger.debug("Exception in searchItemAutoComplete ", ex);
			return null;
		}
	}
	
	public String deleteItem(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_ITEM);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in delete brand: {}", responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in deleteItem", ex);
			return null;
		}

	}
	
	public String getAlternateMedicine(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ITEM_GET_SAMECONTENT);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getAlternateMedicine ", ex);
			return null;
		}
	}
	
	public String getItemsByContent(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_CONTENT_STOCK_AUTOCOMPLETE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<ItemCurrentStockDTO> itemCurrentStockDTOs = new ArrayList<ItemCurrentStockDTO>();
//			itemCurrentStockDTOs = gson.fromJson(responseString, new TypeToken<List<ItemCurrentStockDTO>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getItemsByContent ", ex);
			return null;
		}
	}
	
	public String getItemHistory(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_HISTORY_BY_ITEMID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getItemHistory:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getItemHistory ", ex);
			return null;
		}
	}
	
	public String getItemByBarcode(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_ITEM_BY_BARCODE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString getItemByBarcode:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getItemByBarcode ", ex);
			return null;
		}
	}
	
}
