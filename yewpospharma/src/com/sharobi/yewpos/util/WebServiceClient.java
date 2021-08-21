/**
 * 
 */
package com.sharobi.yewpos.util;

import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.MenuFile;
import com.sharobi.yewpos.proc.model.PurchaseInvoiceExcelDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author habib
 *
 */
public class WebServiceClient {
	
	public static ClientResponse callGet(String url)
	{
		Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return response;
	}
	public static ClientResponse callPost(String url,String param)
	{
		Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(StringUtils.isNotEmpty(param))
			{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;
	}
	public static ClientResponse callPostData(String url,MultiPart param)
	{
		Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(param!=null)
			{
			response=webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;
	}
	
	public static ClientResponse callPostData(String url,InputStream inputFile,MultiPart param,CommonResultSetMapper commonResultSetMapper)
	{
		/*Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(param!=null)
			{
			response = webResource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;*/
		Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(param!=null)
			{
			
	        	response=webResource.type(
	                    MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
	                    		param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;
	}
	
	public static ClientResponse callPostDataPurInvExc(String url,InputStream inputFile,MultiPart param,PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO)
	{
		/*Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(param!=null)
			{
			response = webResource.type(MediaType.MULTIPART_FORM_DATA).post(ClientResponse.class, param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;*/
		Client client=Client.create();
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		if(param!=null)
			{
			
	        	response=webResource.type(
	                    MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
	                    		param);
			}
		else{
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
			}
		return response;
	}

}
