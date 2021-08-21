/**
 * 
 */
package com.sharobi.yewpos.inv.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.BrandMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.MenuFile;
import com.sharobi.yewpos.inv.model.OpeningStock;
import com.sharobi.yewpos.inv.model.OpeningStockDetails;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.StockDetailsAdjustmentDTO;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.StockService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

/**
 * @author habib,Manodip
 *
 */
@Controller
@RequestMapping("/stock")
public class StockController {
	private final static Logger logger=LogManager.getLogger(StockController.class);
	private final static StockService stockService = new StockService();
	private final static VendorService vendorService = new VendorService();
	private final static InvSetupService invSetUpService = new InvSetupService();
	private final static RoleService roleService = new RoleService();
	
	@RequestMapping(value="/stockentry",method=RequestMethod.GET)
	public ModelAndView loadStockentry(Model model,HttpSession session)
	{
		//logger.debug("In stockentry......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		mav.addObject("allVendors", allVendors);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ENTRY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("storeId",userInfo.getStoreId());
		mav.addObject("createdBy",userInfo.getId());
		mav.addObject("finyrId",userInfo.getFinyrId());
		mav.addObject("cmpnyId",userInfo.getCompanyId());
		mav.addObject("msg","fresh");
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.setViewName(ForwardConstants.VIEW_STOCKENTRY_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/createorupdateStock",
			method = RequestMethod.POST)
	public void createPurchaseInvoice(	@RequestBody String stockString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In createorupdateStock......{},{},{}", stockString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			OpeningStock stockStringObj = gson.fromJson(stockString, new TypeToken<OpeningStock>() {}.getType());
			/*String stockStringDetailsObj = stockStringObj.getOpeningStockDetails().toString();
			//OpeningStockDetails ostd = (OpeningStockDetails) stockStringObj.getOpeningStockDetails();
			//OpeningStockDetails ostd[] = gson.fromJson(stockStringDetailsObj, new TypeToken<OpeningStockDetails>() {}.getType());
			List<OpeningStockDetails> ostd = (List<OpeningStockDetails>) stockStringObj.getOpeningStockDetails();
			List<OpeningStockDetails> obj = null;
 			for(OpeningStockDetails openingStockDetails : ostd)
			{
				openingStockDetails.setStoreId(userInfo.getStoreId());
				openingStockDetails.setCreatedBy(userInfo.getId());
				openingStockDetails.setFinyrId(userInfo.getFinyrId());
				openingStockDetails.setCreatedDate(DateUtil.getCurrentDate());
				stockStringObj.setOpeningStockDetails((List<OpeningStockDetails>) openingStockDetails);
			}
			
			*/
			
			String res = stockService.createorupdateStock(stockStringObj);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/stockadj",method=RequestMethod.GET)
	public ModelAndView loadStockadj(Model model,HttpSession session)
	{
		//logger.debug("In stockadj......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.setViewName(ForwardConstants.VIEW_STOCKADJ_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/stkadjupdatesearch",method=RequestMethod.POST)
	public ModelAndView searchItem(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In stkadjupdate......{},{},{}",commonResultSetMapper.toString());
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		/*CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));*/
		List<StockDetailsAdjustmentDTO> stockDetailsAdjustmentDTOs=stockService.getStockDetailsForAdjustment(commonResultSetMapper);
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allVendors",allVendors);
		mav.addObject("stockDetailsAdjustmentDTOs",stockDetailsAdjustmentDTOs);
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.setViewName(ForwardConstants.VIEW_STOCKADJ_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/stkadjupdate",method=RequestMethod.POST)
	public void stkAdjUpdate(@RequestBody String StockDetailsAdjustmentDTOObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In stkadjupdate......{},{},{}",StockDetailsAdjustmentDTOObj.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			StockDetailsAdjustmentDTO commonResultSetMapper= gson.fromJson(StockDetailsAdjustmentDTOObj, new TypeToken<StockDetailsAdjustmentDTO>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinyrId(userInfo.getFinyrId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			//commonResultSetMapper.setLang(lang);
			ResponseObj res=stockService.updateStkAdj(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(gson.toJson(res));
			out.flush();
		}
	}
	
	@RequestMapping(value="/stkadjdelete",method=RequestMethod.POST)
	public void stkAdjDelete(@RequestBody String commonResultSetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In stkAdjDelete......{},{},{}",commonResultSetMapperObj.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper= gson.fromJson(commonResultSetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			ResponseObj res=stockService.deleteStkAdj(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(gson.toJson(res));
			out.flush();
		}
	}
	
	@RequestMapping(value="/expissue",method=RequestMethod.GET)
	public ModelAndView loadExpIssue(Model model,HttpSession session)
	{
		//logger.debug("In expissue......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("allVendors", allVendors);
		mav.addObject("menuselect",menuselect);
		mav.addObject("expiryId", 0);
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUE_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/expissuereg",method=RequestMethod.GET)
	public ModelAndView loadExpIssueReg(Model model,HttpSession session)
	{
		//logger.debug("In expissuereg......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUEREG);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUEREG_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/getcurrstock/{itemid}",method=RequestMethod.GET)
	public void getCurrStock(@PathVariable int itemid,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getcurrstock......{}",itemid);
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
	 
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setItemId(itemid);
			commonResultSetMapper.setLang(lang);
			String res=stockService.getCurrentStockOfItem(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/getcurrstocksku/{barcode}",method=RequestMethod.GET)
	public void getCurrStock(@PathVariable String barcode,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getcurrstocksku......{}",barcode);
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
	 
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setSku(barcode);
			commonResultSetMapper.setLang(lang);
			String res=stockService.getCurrentStockOfItemByBarcode(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/getcurrstockbybatexpmrp",method=RequestMethod.POST)
	public void getCurrentStockByBatExpMrp(@RequestBody String commonResultSetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getCurrentStockByBatExpMrp......{},{},{}",commonResultSetMapperObj.toString());
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper= gson.fromJson(commonResultSetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=stockService.getCurrentStockOfItemByBatExpMrp(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/uploadstockexcel",method=RequestMethod.POST)
	public ModelAndView uploadStock(@Valid @ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper, @RequestParam MultipartFile fileUpload,HttpSession session,HttpServletResponse response,HttpServletRequest request,BindingResult result)
	{
		//logger.debug("uploadstockexcel...! ");
		ModelAndView mav=new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		InputStream inputFile = null;
		byte[] byteArr=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		if (fileUpload != null && fileUpload.getSize() > 0) {
			try{
				///System.out.println("file name:"+fileUpload.getName());
				//System.out.println("original file name:"+fileUpload.getOriginalFilename());
				//System.out.println("file size:"+fileUpload.getSize());
				inputFile=fileUpload.getInputStream();
				byteArr=fileUpload.getBytes();
				//System.out.println("inputsterm:"+inputFile);
			 
				String uploadsDir = "/uploads/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
				
			//	System.out.println("path:"+realPathtoUploads);
				String orgName = fileUpload.getOriginalFilename();
                String filePath = realPathtoUploads +"/"+ orgName;
                File dest = new File(filePath);
                fileUpload.transferTo(dest);
				
				//MenuFile menuFile=new MenuFile();
				//menuFile.setStoreId(userInfo.getStoreId());
				//menuFile.setFileName(fileUpload.getOriginalFilename());
				
				commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
				commonResultSetMapper.setStoreId(userInfo.getStoreId());
				commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
				commonResultSetMapper.setCreatedBy(userInfo.getId());
				
				FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
				filePart.setContentDisposition(FormDataContentDisposition.name("file").fileName(fileUpload.getOriginalFilename()).build());
				MultiPart multipartEntity = new FormDataMultiPart().field("commonResultSetMapper", new Gson().toJson(commonResultSetMapper), MediaType.APPLICATION_JSON_TYPE).bodyPart(filePart);   
				
				
				String res=stockService.uploadstockexcel(inputFile,multipartEntity,commonResultSetMapper);
				//System.out.println("result:"+res);
				mav.addObject("msg",res);
				//inputFile.close();
			}catch (Exception e) {
				logger.error("File uploading problem: ", e);
			}
		}
		else
		{
			result.reject("NotEmpty.menuitemupload.file");
		}
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_ADJ);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_STOCKENTRY_PAGE);
		return mav;
	}
	
}
