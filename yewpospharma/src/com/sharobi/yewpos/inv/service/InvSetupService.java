/**
 * 
 */
package com.sharobi.yewpos.inv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CategoryMaster;
import com.sharobi.yewpos.inv.model.CityDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.DoctorMaster;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.SubCategoryMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.model.ZoneDTO;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib,Manodip
 *
 */
public class InvSetupService {
	
	private final static Logger logger=LogManager.getLogger(InvSetupService.class);
	private final static Gson gson = new Gson();
	
	
	public String addUnit(UnitMaster UnitMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_UNIT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(UnitMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addUnit ",ex);
			return null;	
		}
	}
	

	public List<UnitMaster> getAllUnit(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_UNIT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all units: {}", responseString);
			List<UnitMaster> UnitMasterList = new ArrayList<UnitMaster>();
			UnitMasterList = gson.fromJson(responseString, new TypeToken<List<UnitMaster>>(){}.getType());
			return UnitMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllUnit ",ex);
			return null;
		}
		
	}
	
	public UnitMaster getUnitById(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET__UNIT_BY_ID).replace("{1}", String.valueOf(id).replace("{2}", lang));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get unit by Id: {}", responseString);
			UnitMaster UnitMasterobj = gson.fromJson(responseString, new TypeToken<UnitMaster>() {}.getType());
			return UnitMasterobj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getUnitById ",ex);
			return null;
		}
		
	}
	
	public String editUnit(UnitMaster UnitMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_UNIT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(UnitMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editUnit ",ex);
			return null;	
		}
	}
	
	public String deleteUnit(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_UNIT).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete unit: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteUnit ",ex);
			return null;
		}
		
	}
	
	public List<UnitMaster> searchUnitAutoComplete(UnitMaster unitMaster) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_UNIT_AUTOCOMPLETE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(unitMaster));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" +responseString);
			List<UnitMaster> unitMasters = new ArrayList<UnitMaster>();
			unitMasters = gson.fromJson(responseString, new TypeToken<List<UnitMaster>>() {}.getType());
			return unitMasters;
			} catch (Exception ex) {
			logger.debug("Exception in searchUnitAutoComplete ", ex);
			return null;
		}
	}
	
	public List<CategoryMaster> getAllCat(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_CAT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all categories: {}", responseString);
			List<CategoryMaster> CatMasterList = new ArrayList<CategoryMaster>();
			CatMasterList = gson.fromJson(responseString, new TypeToken<List<CategoryMaster>>(){}.getType());
			//System.out.println("return result: "+CatMasterList.toString());
			return CatMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllCat ",ex);
			return null;
		}
		
	}
	
	
	public String editCat(CategoryMaster CategoryMasterObj)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_CAT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CategoryMasterObj));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editCat ",ex);
			return null;	
		}
	}
	
	public String deleteCat(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CAT).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete category: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteCat ",ex);
			return null;
		}
		
	}
	
	public String addCat(CategoryMaster CategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_CAT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addCat ",ex);
			return null;	
		}
	}
	
	public CategoryMaster getCatDetailsById(int cat_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CATDETAILS_BY_ID).replace("{1}", String.valueOf(cat_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get category details by id: {}", responseString);
			CategoryMaster CategoryMasterObj = gson.fromJson(responseString, new TypeToken<CategoryMaster>() {}.getType());
			return CategoryMasterObj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getCatDetailsById ",ex);
			return null;
		}
		
	}
	
	public List<SubCategoryMaster> getAllSubCat(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_SUBCAT).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all subcategories: {}", responseString);
			List<SubCategoryMaster> SubCatMasterList = new ArrayList<SubCategoryMaster>();
			SubCatMasterList = gson.fromJson(responseString, new TypeToken<List<SubCategoryMaster>>(){}.getType());
			return SubCatMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllSubCat ",ex);
			return null;
		}
		
	}	

	public String addsubcat(SubCategoryMaster SubCategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_SUBCAT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(SubCategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addsubcat ",ex);
			return null;	
		}
	}	
	
	public String editsubcat(SubCategoryMaster SubCategoryMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_SUBCAT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(SubCategoryMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editsubcat ",ex);
			return null;	
		}
	}
	
	public String deleteSubCat(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_SUBCAT).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete subcategory: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteSubCat ",ex);
			return null;
		}
		
	}
	
	public List<DoctorMaster> getAllDctrs(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_DOCTOR).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all doctors: {}", responseString);
			List<DoctorMaster> doctorMasterList = new ArrayList<DoctorMaster>();
			doctorMasterList = gson.fromJson(responseString, new TypeToken<List<DoctorMaster>>(){}.getType());
			//System.out.println("return result: "+doctorMasterList.toString());
			return doctorMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllDctrs ",ex);
			return null;
		}
		
	}
	
	public String adddocctr(DoctorMaster doctorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_DOCTOR);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(doctorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in adddocctr ",ex);
			return null;	
		}
	}
	
	public String editdoctor(DoctorMaster DoctorMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_DOCTOR);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(DoctorMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editdoctor ",ex);
			return null;	
		}
	}
	
	public DoctorMaster getDoctorbyId(int id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_DOCTOR_BY_ID).replace("{1}", String.valueOf(id).replace("{2}", lang));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get doctor by Id: {}", responseString);
			DoctorMaster DoctorMasterobj = gson.fromJson(responseString, new TypeToken<DoctorMaster>() {}.getType());
			return DoctorMasterobj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getDoctorbyId ",ex);
			return null;
		}
		
	}
	
	public String deleteDoctor(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_DOCTOR).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete Doctor: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteDoctor ",ex);
			return null;
		}
		
	}
	
	public String getDoctorListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_SEARCH_DOCTOR_AUTOCOMPLETE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getDoctorListAutocomplete ", ex);
			return null;
		}
	}
	
	public List<RackMaster> getAllRack(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_RACK).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all racks: {}", responseString);
			List<RackMaster> rackMasterList = new ArrayList<RackMaster>();
			rackMasterList = gson.fromJson(responseString, new TypeToken<List<RackMaster>>(){}.getType());
			//System.out.println("return result: "+rackMasterList.toString());
			return rackMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllRack ",ex);
			return null;
		}
		
	}
	
	public String addrack(RackMaster RackMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_RACK);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(RackMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addrack",ex);
			return null;	
		}
	}
	
	public String editrack(RackMaster RackMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_RACK);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(RackMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editrack",ex);
			return null;	
		}
	}
	
	public String deleteRack(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_RACK).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete Rack: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteRack ",ex);
			return null;
		}
		
	}
	
	public List<GroupMaster> getAllGroup(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_GROUPS).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all groups: {}", responseString);
			List<GroupMaster> groupMasterList = new ArrayList<GroupMaster>();
			groupMasterList = gson.fromJson(responseString, new TypeToken<List<GroupMaster>>(){}.getType());
			//System.out.println("return result: "+groupMasterList.toString());
			return groupMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllGroup ",ex);
			return null;
		}
		
	}
	
	public String addgroup(GroupMaster groupMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_GROUP);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(groupMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addgroup ",ex);
			return null;	
		}
	}
	
	public String editgroup(GroupMaster groupMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_GROUP);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(groupMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editgroup ",ex);
			return null;	
		}
	}
	
	public String deleteGroup(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_GROUP).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete Group: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteGroup ",ex);
			return null;
		}
		
	}
	
	public List<ScheduleMaster> getAllSchedule(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_SCHEDULES).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all Schedules: {}", responseString);
			List<ScheduleMaster> ScheduleMasterList = new ArrayList<ScheduleMaster>();
			ScheduleMasterList = gson.fromJson(responseString, new TypeToken<List<ScheduleMaster>>(){}.getType());
			//System.out.println("return result: "+ScheduleMasterList.toString());
			return ScheduleMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllSchedule ",ex);
			return null;
		}
		
	}
	
	public String addSchedule(ScheduleMaster scheduleMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_ADD_SCHEDULE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(scheduleMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addSchedule ",ex);
			return null;	
		}
	}
	
	public String editSchedule(ScheduleMaster scheduleMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_SCHEDULE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(scheduleMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editSchedule ",ex);
			return null;	
		}
	}
	
	public String deleteSchedule(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_SCHEDULE).replace("{1}", String.valueOf(id));
			///logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete Schedule: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteSchedule ",ex);
			return null;
		}
		
	}
	

	public List<ReturnReasonTypeMaster> getAllReturnReasonType(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_RETURNREASONTYPE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all ReturnReasonType: {}", responseString);
			List<ReturnReasonTypeMaster> ReturnReasonTypeMasterList = new ArrayList<ReturnReasonTypeMaster>();
			ReturnReasonTypeMasterList = gson.fromJson(responseString, new TypeToken<List<ReturnReasonTypeMaster>>(){}.getType());
			return ReturnReasonTypeMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllReturnReasonType ",ex);
			return null;
		}
		
	}
	
	 
	/*
	 * for country list   ADD ON 16_2_2018 BY SIDDIK
	 */
	
	public String getAllCountryList(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLCOUNTRY);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in getAllCountryList: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllCountryList ",ex);
			return null;
		}
		
	}
	
	public String getAllStateByCountry(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLSTATE_BYCOUNTRY);
			//logger.debug("url....{}",url);
		 
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in getAllStateByCountry: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllStateByCountry ",ex);
			return null;
		}
		
	}
	
	public String getAllCityByState(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLCITY_BYSTATE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in getAllCityByState: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllStateByCountry ",ex);
			return null;
		}
		
	}
	
	public String addCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_CITY);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addCity ",ex);
			return null;	
		}
	}
	
	public String editCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_CITY);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editCity ",ex);
			return null;	
		}
	}
	
	public String deleteCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_CITY);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in deleteCity ",ex);
			return null;	
		}
	}
	
	public List<CityDTO> searchCity(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_CITYLIST_BYNAME);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
			cityDTOs = gson.fromJson(responseString, new TypeToken<List<CityDTO>>() {}.getType());
			return cityDTOs;
		} catch (Exception ex) {
			logger.debug("Exception in searchCity ", ex);
			return null;
		}
	}
	
	public String getCityById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_CITY_BYID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in getCityById: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getCityById ",ex);
			return null;
		}
		
	}
	
	public List<ZoneDTO> getAllZoneList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_ZONE);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get all zone: {}", responseString);
			List<ZoneDTO> zoneMasterList = new ArrayList<ZoneDTO>();
			zoneMasterList = gson.fromJson(responseString, new TypeToken<List<ZoneDTO>>() {}.getType());
			return zoneMasterList;
		} catch (Exception ex) {
			logger.debug("Exception in getAllZoneList ", ex);
			return null;
		}

	}
	
	public String addZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_ZONE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addZone ",ex);
			return null;	
		}
	}
	
	public String editZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_ZONE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editZone ",ex);
			return null;	
		}
	}
	
	public String deleteZone(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_ZONE);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in deleteZone ",ex);
			return null;	
		}
	}
	
	public ZoneDTO getZoneById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ZONE_BYID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get zone by Id: {}", responseString);
			ZoneDTO zoneMasterobj = gson.fromJson(responseString, new TypeToken<ZoneDTO>() {}.getType());
			return zoneMasterobj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getZoneById ",ex);
			return null;
		}
		
	}
	
	public String getAllZoneByCity(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALLZONE_BYCITY);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in getAllCityByState: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllZoneByCity ",ex);
			return null;
		}
		
	}
	
	public List<AreaDTO> getAllAreaList(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_ALL_AREA);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in get all area: {}", responseString);
			List<AreaDTO> areaMasterList = new ArrayList<AreaDTO>();
			areaMasterList = gson.fromJson(responseString, new TypeToken<List<AreaDTO>>() {}.getType());
			return areaMasterList;
		} catch (Exception ex) {
			logger.debug("Exception in getAllAreaList ", ex);
			return null;
		}

	}
	
	public String addArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_CREATE_AREA);
		//	logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addArea ",ex);
			return null;	
		}
	}
	
	public String editArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_EDIT_AREA);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editArea ",ex);
			return null;	
		}
	}
	
	public String deleteArea(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_DELETE_AREA);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in deleteArea ",ex);
			return null;	
		}
	}
	
	public AreaDTO getAreaById(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_INV_GET_AREA_BYID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get area by Id: {}", responseString);
			AreaDTO areaMasterobj = gson.fromJson(responseString, new TypeToken<AreaDTO>() {}.getType());
			return areaMasterobj;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAreaById ",ex);
			return null;
		}
		
	}
}
