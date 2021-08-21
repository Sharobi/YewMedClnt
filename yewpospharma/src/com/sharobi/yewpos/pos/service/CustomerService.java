/**
 * 
 */
package com.sharobi.yewpos.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.JournalList;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.pos.model.CustPaymentDetailsAllDTO;
import com.sharobi.yewpos.pos.model.CustomerDTO;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.pos.model.CustomerPayment;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author habib
 *
 */
public class CustomerService {
	
	private final static Logger logger=LogManager.getLogger(CustomerService.class);
	private final static Gson gson = new Gson();

	public List<CustomerMaster> getAllCustomers(int cmpny_id,String lang)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_ALL_CUSTOMERS).replace("{1}", String.valueOf(cmpny_id)).replace("{2}", lang);
		//	logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in get all doctors: {}", responseString);
			List<CustomerMaster> customerMasterList = new ArrayList<CustomerMaster>();
			customerMasterList = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>(){}.getType());
			//System.out.println("return result: "+customerMasterList.toString());
			return customerMasterList;
		}catch(Exception ex)
		{
			logger.debug("Exception in getAllCustomers ",ex);
			return null;
		}
		
	}
	
	public List<CustomerDTO> getAllCustomersPost(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_ALL_CUSTOMERS);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
			customerDTOList = gson.fromJson(responseString, new TypeToken<List<CustomerDTO>>(){}.getType());
			//System.out.println("return result: "+customerDTOList.toString());
			return customerDTOList;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in getAllCustomersPost ",ex);
			return null;	
		}
	}
	
	public String addcustomer(CustomerMaster customerMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_ADD_CUSTOMER);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(customerMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in addcustomer ",ex);
			return null;	
		}
	}
	
	public String editcustomer(CustomerMaster CustomerMasterObject)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_EDIT_CUSTOMER);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CustomerMasterObject));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in editcustomer ",ex);
			return null;	
		}
	}
	
	public String deletecustomer(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTOMER);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in deletecustomer ",ex);
			return null;	
		}
	}
	
	
	public String getCustomerbyId(CommonResultSetMapper commonResultSetMapper)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_BY_ID);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in getCustomerbyId ",ex);
			return null;	
		}
	}
	
	public String deleteCustomer(int id)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTOMER).replace("{1}", String.valueOf(id));
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callGet(url);
			String responseString=response.getEntity(String.class);
			//logger.debug("Response string in delete Customer: {}", responseString);
			return responseString;
		}catch(Exception ex)
		{
			logger.debug("Exception in deleteCustomer ",ex);
			return null;
		}
		
	}
	
	public String getCustomerListAutocomplete(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_BYNAMEORPH);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerListAutocomplete ", ex);
			return null;
		}
	}
	
	public String getCustomerWithCreditListAutocom(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_WITHCREDIT_BYNAMEORPH);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
//			List<CustomerMaster> customerMasters = new ArrayList<CustomerMaster>();
//			customerMasters = gson.fromJson(responseString, new TypeToken<List<CustomerMaster>>() {}.getType());
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerWithCreditListAutocom ", ex);
			return null;
		}
	}
	
	public String getCustomerAllPaymentDetails(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTDETAILS);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerAllPaymentDetails ", ex);
			return null;
		}
	}
	
	public String postcustomerpayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_POST_CUSTOMERPAYMENT);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in postcustomerpayment ", ex);
			return null;
		}
	}
	
	public String getCustomerPendingPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_PENDINGPAYMENT_BYCUST);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerPendingPayment ", ex);
			return null;
		}
	}
	
	public String createOrUpdateCustomerPayment(CustomerPayment CustomerPayment)
	{
		try{
			String url=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_POS_CREATE_UPDATE_CUSTOMERPAYMENT);
			//logger.debug("url....{}",url);
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(CustomerPayment));
			String responseString=response.getEntity(String.class);
			//logger.debug("response string...{}",responseString);
			return responseString;
		}
		catch(Exception ex)
		{
			logger.debug("Exception in createOrUpdateCustomerPayment ",ex);
			return null;	
		}
	}
	
	public String getCustomerPaymentHeaderById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTHEADER_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerPaymentHeaderById ", ex);
			return null;
		}
	}
	
	public String getCustomerPaymentDetailsById(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_PAYMENTDETAILS_BYCUSTOMERID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in getCustomerPaymentDetailsById", ex);
			return null;
		}
	}
	
	public String deleteCustPaymentInvoice(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT) + CommonResource.getProperty(CommonResource.WEBSERVICE_POS_DELETE_CUSTPAYINV);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//System.out.println("responseString:" + responseString);
			return responseString;
		} catch (Exception ex) {
			logger.debug("Exception in deleteCustPaymentInvoice ", ex);
			return null;
		}
	}
	
	
	
	public CustPaymentDetailsAllDTO getcustomerPayment(CommonResultSetMapper commonResultSetMapper) {
		try {
			String url = CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)
					+ CommonResource.getProperty(CommonResource.WEBSERVICE_POS_GET_CUSTOMER_ALLPAYMENTHEADER_BYID);
			//logger.debug("url....{}", url);
			ClientResponse response = WebServiceClient.callPost(url, gson.toJson(commonResultSetMapper));
			String responseString = response.getEntity(String.class);
			//logger.debug("Response string in customerpayment print: {}", responseString);

			CustPaymentDetailsAllDTO cd = new CustPaymentDetailsAllDTO();
			cd = gson.fromJson(responseString, CustPaymentDetailsAllDTO.class);
			//
			return cd;

		} catch (Exception ex) {
			logger.debug("Exception in getcustomerPayment", ex);
			return null;
		}

	}
}
