/**
 * 
 */
package com.sharobi.yewpos.proc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import com.sharobi.yewpos.inv.model.ExcelUploadResultObj;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.ManufacturerMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ManufacturerService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.proc.model.BarcodePrintParam;
import com.sharobi.yewpos.proc.model.BarcodePrintParamList;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseInvoiceExcelDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDetailsDTO;
import com.sharobi.yewpos.proc.service.PurInvoiceService;
import com.sharobi.yewpos.proc.service.PurReturnService;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
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
@RequestMapping("/purinv")
public class PurInvoiceController {
	private final static Logger logger = LogManager.getLogger(PurInvoiceController.class);
	private final static PurInvoiceService purInvoiceService = new PurInvoiceService();
	private final static RoleService roleService = new RoleService();
	private final static VendorService vendorService = new VendorService();
	private final static InvSetupService invSetUpService = new InvSetupService();
	private final static ManufacturerService manufacturerService = new ManufacturerService();
	private final static PurReturnService purReturnService = new PurReturnService();
	private final static Gson gson = new Gson();

	@RequestMapping(value = "/loadpurinvoice",
					method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(	Model model,
										HttpSession session) {
		//logger.debug("In loadpurinvoice......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		commonResultSetMapper.setRetType("P");
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService.getAllReturnReasonType(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO=new PurchaseInvoiceExcelDTO();
		mav.addObject("isAccount",userInfo.getIs_account());
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("noOfDue",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("purchaseInvoiceExcelDTO",purchaseInvoiceExcelDTO);
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("expalertrequiremnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_EXPALERT_REQUIRED));
		mav.addObject("allReturnReasonTypes",allReturnReasonType);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV);
		mav.addObject("menuselect", menuselect);
		mav.addObject("msg","-1");
		mav.setViewName(ForwardConstants.VIEW_PURINVOICE_PAGE);
		//System.out.println("Is Account in purchase = "+ userInfo);
		return mav;
	}

	@RequestMapping(value = "/loadpurinvoicedet/{pid}",
					method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(	@PathVariable("pid") Integer pid,
										Model model,
										HttpSession session) {
		//logger.debug("In loadpurinvoicedet......{},", pid);
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setPurInvId(pid);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		commonResultSetMapper.setRetType("P");
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService.getAllReturnReasonType(commonResultSetMapper);
		Purchase purchaseHeader = purInvoiceService.getPurchaseInvoiceHeader(commonResultSetMapper);
		//System.out.println(purchaseHeader.toString());
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<PurchaseDetails> purchaseDetails = purInvoiceService.getPurchaseInvoiceDetails(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purchaseHeader", purchaseHeader);
		mav.addObject("purchaseDetails", purchaseDetails);
		mav.addObject("allRacks",allRacks);
		mav.addObject("allGroups",allGroups);
		mav.addObject("allSchedules",allSchedules);
		mav.addObject("allReturnReasonTypes",allReturnReasonType);
		mav.addObject("noOfDue",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("expalertrequiremnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_EXPALERT_REQUIRED));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURINVOICE_DETAILS_PAGE);
		return mav;
	}

	@RequestMapping(value = "/loadpurinvregister",
					method = RequestMethod.GET)
	public ModelAndView loadPurInvRegister(	Model model,
											HttpSession session) {
		//logger.debug("In loadpurinvregister......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV_REG);
		mav.addObject("menuselect", menuselect);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_PURINVOICEREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchpurchaseinvoiceregister",
					method = RequestMethod.POST)
	public ModelAndView searchPIRegister(	@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
											HttpSession session,
											HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		String invno = commonResultSetMapper.getInvoiceNo();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_INVOICE + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//logger.debug("In searchpurchaseinvoiceregister......{},{},{}", commonResultSetMapper.toString());
		List<Purchase> purchaseList = purInvoiceService.getAllPurchaseInvoice(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purchaseList", purchaseList);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_PURINVOICEREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createorupdatepurchaseinvoice",
					method = RequestMethod.POST)
	public void createPurchaseInvoice(	@RequestBody String purchaseString,
										HttpSession session,
										HttpServletResponse response) throws IOException {
	//	logger.debug("In createorupdatepurchaseinvoice......{},{},{}", purchaseString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Purchase purchase = gson.fromJson(purchaseString, new TypeToken<Purchase>() {}.getType());
			purchase.setCompanyId(userInfo.getCompanyId());
			purchase.setStoreId(userInfo.getStoreId());
			purchase.setCreatedBy(userInfo.getId());
			purchase.setFinyrId(userInfo.getFinyrId());
			purchase.setFinyrCode(userInfo.getFinyrCode());
			purchase.setLang(lang);
			purchase.setQs(Constant.PURCHASE_PAYMENT_QS);
			purchase.setIs_account(userInfo.getIs_account());
			
	    	//System.err.println(purchase.toString());
			
		 
			
			String res = purInvoiceService.createOrUpdatePurchaseInvoice(purchase);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getitemdetails/{itemid}/{distid}",
					method = RequestMethod.GET)
	public ModelAndView loadItemMaster(	@PathVariable("itemid") Integer itemid,
										@PathVariable("distid") Integer distid,
										Model model,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getitemdetails Pur inv......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setItemId(itemid);
		commonResultSetMapper.setDistributorId(distid);
		commonResultSetMapper.setLang(lang);
		String purchaseDetails = purInvoiceService.getPurchaseInvoiceDetailsById(commonResultSetMapper);
		//System.out.println("In getitemdetails Pur inv List :" + purchaseDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
	 
		out.print(purchaseDetails);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/getpurinvdetailsbysku/{sku}",
			method = RequestMethod.GET)
	public ModelAndView getPurInvDetailsBySku(	@PathVariable("sku") String sku,
									Model model,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In getPurInvDetailsBySku......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setSku(sku);
		commonResultSetMapper.setLang(lang);
		String purchaseDetails = purInvoiceService.getPurInvDetailsBySku(commonResultSetMapper);
		//System.out.println("In getitemdetails Pur inv List by SKU:" + purchaseDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
 
		out.print(purchaseDetails);
		out.flush();
		return null;
	}
	
	@RequestMapping(value = "/deletepurinv",
					method = RequestMethod.POST)
	public void deletePurInv(	@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.debug("In deletepurinv......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purInvoiceService.deletePurchaseInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postpurinv",
					method = RequestMethod.POST)
	public void postPurInv(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		//logger.debug("In postpurinv......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purInvoiceService.postPurchaseInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/* ======================== Print Purchase Start ========================== */

	@RequestMapping(value = "/printPurInvoice",
					method = RequestMethod.GET)
	public ModelAndView printPurInvoice(Model model,
										HttpSession session,
										@RequestParam(	required = false,
														defaultValue = "0") Integer pid,
										@RequestParam(required = false) String backUrl) {
		//logger.debug("In printPurInvoice......{},{},", pid, backUrl);
		ModelAndView mav = new ModelAndView();
		String returnDt = "";
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setPurInvId(pid);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		Purchase purchaseHeader = purInvoiceService.getPurchaseInvoiceHeader(commonResultSetMapper);
		List<PurchaseDetails> purchaseDetails = purInvoiceService.getPurchaseInvoiceDetails(commonResultSetMapper);
		commonResultSetMapper.setDistributorId(purchaseHeader.getDistributorId());
		DistributorMaster distributorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper), new TypeToken<DistributorMaster>() {}.getType());
		List<PurchaseReturnDTO> purchaseReturnHeader = purInvoiceService.getPurchaseReturnHeader(commonResultSetMapper);
		if (!purchaseReturnHeader.isEmpty()) {
			commonResultSetMapper.setPurchaseReturnId(purchaseReturnHeader.get(0).getPurchaseReturnId());
			returnDt = purchaseReturnHeader.get(0).getInvDate();
		}
		List<PurchaseReturnDetailsDTO> purchaseReturnDetailsDTOs = gson.fromJson(purReturnService.getPurInvReturnMemoDetailsById(commonResultSetMapper), new TypeToken<List<PurchaseReturnDetailsDTO>>() {}.getType());
		/*int manuId=0;
		for(PurchaseDetails purdetails : purchaseDetails)
		{
			manuId=purdetails.getManuId();
		}
		ManufacturerMaster manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/

		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("purchaseHeader", purchaseHeader);
		mav.addObject("purchaseDetails", purchaseDetails);
		mav.addObject("backUrl", backUrl);
		mav.addObject("distributorDetails", distributorDetails);
		mav.addObject("purchaseReturnHeader", purchaseReturnHeader);
		mav.addObject("purchaseReturnDetailsDTOs", purchaseReturnDetailsDTOs);
		mav.addObject("purReturnId", commonResultSetMapper.getPurchaseReturnId());
		mav.addObject("returnDt", returnDt);
		mav.setViewName(ForwardConstants.VIEW_PRINTPUR_PAGE);
		return mav;
	}

	/* ======================== Print Purchase End ============================ */

	@RequestMapping(value = "/getpurhistoryofitem/{itemid}/{distid}",
					method = RequestMethod.GET)
	public ModelAndView getPurHistoryofItem(@PathVariable("itemid") Integer itemid,
											@PathVariable("distid") Integer distid,
											Model model,
											HttpSession session,
											HttpServletResponse response) throws IOException {
		//logger.debug("In getPurHistoryofItem Pur inv......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		//commonResultSetMapper.setFinYrId(userInfo.getFinyrId()); for previous year item purchase history
		commonResultSetMapper.setItemId(itemid);
		commonResultSetMapper.setDistributorId(distid);
		commonResultSetMapper.setNoOfRows(0);
		commonResultSetMapper.setLang(lang);
		String purchaseHistoryDetails = purInvoiceService.getPurchaseInvoiceHistoryDetailsById(commonResultSetMapper);
		//System.out.println("In getPurchaseInvoiceHistoryDetailsById :" + purchaseHistoryDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
	 
		out.print(purchaseHistoryDetails);
		out.flush();
		return null;
	}
	
	@RequestMapping(value="/uploadpurchaseinvexcel",method=RequestMethod.POST)
	public ModelAndView uploadPurInv(@Valid @ModelAttribute("purchaseInvoiceExcelDTO") PurchaseInvoiceExcelDTO purchaseInvoiceExcelDTO, @RequestParam MultipartFile fileUpload,HttpSession session,HttpServletResponse response,HttpServletRequest request,BindingResult result)
	{
		//logger.debug("uploadpurchaseinvexcel...!{}, ", purchaseInvoiceExcelDTO.toString());
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
				//System.out.println("file name:"+fileUpload.getName());
				//System.out.println("original file name:"+fileUpload.getOriginalFilename());
				//System.out.println("file size:"+fileUpload.getSize());
				inputFile=fileUpload.getInputStream();
				byteArr=fileUpload.getBytes();
				//System.out.println("inputsterm:"+inputFile);
				//System.out.println("byte arr:"+byteArr);
				String uploadsDir = "/uploads/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
				
				//System.out.println("path:"+realPathtoUploads);
				String orgName = fileUpload.getOriginalFilename();
                String filePath = realPathtoUploads +"/"+ orgName;
                File dest = new File(filePath);
                fileUpload.transferTo(dest);
				
				//MenuFile menuFile=new MenuFile();
				//menuFile.setStoreId(userInfo.getStoreId());
				//menuFile.setFileName(fileUpload.getOriginalFilename());
				
                purchaseInvoiceExcelDTO.setCompanyId(userInfo.getCompanyId());
                purchaseInvoiceExcelDTO.setStoreId(userInfo.getStoreId());
                purchaseInvoiceExcelDTO.setFinYrId(userInfo.getFinyrId());
                purchaseInvoiceExcelDTO.setCreatedBy(userInfo.getId());
				
				FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
				filePart.setContentDisposition(FormDataContentDisposition.name("file").fileName(fileUpload.getOriginalFilename()).build());
				MultiPart multipartEntity = new FormDataMultiPart().field("purchaseInvoiceExcelDTO", new Gson().toJson(purchaseInvoiceExcelDTO), MediaType.APPLICATION_JSON_TYPE).bodyPart(filePart);   
				
				String res=purInvoiceService.uploadPurInvFromExcel(inputFile,multipartEntity,purchaseInvoiceExcelDTO);
				ExcelUploadResultObj excelUploadResultObj = gson.fromJson(res, new TypeToken<ExcelUploadResultObj>() {}.getType());
				
				//System.out.println("result:"+excelUploadResultObj.getResult());
				mav.addObject("msg",excelUploadResultObj.getResult());
				mav.addObject("notupldfilelistobj",excelUploadResultObj);
				//inputFile.close();
			}catch (Exception e) {
				logger.error("File uploading problem: ", e);
			}
		}
		else
		{
			result.reject("NotEmpty.menuitemupload.file");
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_INV);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURINVOICE_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/postallselectedpurchaseinvoice",
			method = RequestMethod.POST)
	public void postAllSelectedPurInv(@RequestBody String purchaseString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In postallselectedpurchaseinvoice......{},{},{}", purchaseString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Purchase purchase = gson.fromJson(purchaseString, new TypeToken<Purchase>() {}.getType());
			purchase.setCompanyId(userInfo.getCompanyId());
			purchase.setStoreId(userInfo.getStoreId());
			purchase.setCreatedBy(userInfo.getId());
			purchase.setFinyrId(userInfo.getFinyrId());
			purchase.setFinyrCode(userInfo.getFinyrCode());
			purchase.setLang(lang);
			String res = purInvoiceService.postAllSelectedPurchaseInv(purchase);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/checksamebillonvendor",
			method = RequestMethod.POST)
	public void checkSameBillOnVendor(	@RequestBody String commonResultsetMapperObj,
						HttpSession session,
						HttpServletResponse response) throws IOException {
		//logger.debug("In checksamebillonvendor......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = purInvoiceService.checksamebillonvendor(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	
	@RequestMapping(value = "/printPurchaseInvoice/{purchaseid}",
		      method = RequestMethod.GET)
		public ModelAndView getPurHistoryofItem(@PathVariable("purchaseid") Integer purchaseid,
		                  Model model,
		                  HttpSession session,
		                  HttpServletResponse response) throws IOException {
			      
			Gson gson = new Gson();
			//  logger.debug("In printPurInvoice......{}", purchaseid);
		    ModelAndView mav = new ModelAndView();
		    String returnDt = "";
		    LoginInfoByUserDTO userInfo = null;
		    if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
		      mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
		      return mav;
		    }
		    String lang = null;
		    if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
		      lang = Constant.DEFAULT_LANG;
		    }
		    CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		    commonResultSetMapper.setPurInvId(purchaseid);
		    commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		    commonResultSetMapper.setStoreId(userInfo.getStoreId());
		    commonResultSetMapper.setLang(lang);
		    Purchase purchaseHeader = purInvoiceService.getPurchaseInvoiceHeader(commonResultSetMapper);
		    List<PurchaseDetails> purchaseDetails = purInvoiceService.getPurchaseInvoiceDetails(commonResultSetMapper);
		    commonResultSetMapper.setDistributorId(purchaseHeader.getDistributorId());
		    DistributorMaster distributorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper), new TypeToken<DistributorMaster>() {}.getType());
		    List<PurchaseReturnDTO> purchaseReturnHeader = purInvoiceService.getPurchaseReturnHeader(commonResultSetMapper);
		    if (!purchaseReturnHeader.isEmpty()) {
		      commonResultSetMapper.setPurchaseReturnId(purchaseReturnHeader.get(0).getPurchaseReturnId());
		      returnDt = purchaseReturnHeader.get(0).getInvDate();
		    }
		    List<PurchaseReturnDetailsDTO> purchaseReturnDetailsDTOs = gson.fromJson(purReturnService.getPurInvReturnMemoDetailsById(commonResultSetMapper), new TypeToken<List<PurchaseReturnDetailsDTO>>() {}.getType());
		    /*int manuId=0;
		    for(PurchaseDetails purdetails : purchaseDetails)
		    {
		      manuId=purdetails.getManuId();
		    }
		    ManufacturerMaster manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/

		    MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		    mav.addObject("menuByUserDTO", menuByUserDTO);
		    mav.addObject("purchaseHeader", purchaseHeader);
		    mav.addObject("purchaseDetails", purchaseDetails);
		    mav.addObject("distributorDetails", distributorDetails);
		    mav.addObject("purchaseReturnHeader", purchaseReturnHeader);
		    mav.addObject("purchaseReturnDetailsDTOs", purchaseReturnDetailsDTOs);
		    mav.addObject("purReturnId", commonResultSetMapper.getPurchaseReturnId());
		    mav.addObject("returnDt", returnDt);
		    mav.setViewName(ForwardConstants.VIEW_PRINTPURCHASE_PAGE);
		    return mav;
			  
		}
	
	
	


	@RequestMapping(value = "/printbysku",
			method = RequestMethod.POST)
	public ModelAndView printByBarcode(	@RequestBody String BarcodePrintParamObj,
									Model model,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In printbysku Pur inv......{}"+BarcodePrintParamObj.toString());
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		BarcodePrintParam barcodePrintParamObj = gson.fromJson(BarcodePrintParamObj, new TypeToken<BarcodePrintParam>() {}.getType());
		String printstat = purInvoiceService.printByBarcode(barcodePrintParamObj);
	//	System.out.println("In printbysku Pur inv List :" + printstat);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.print(printstat);
		out.flush();
		return null;
	}

	@RequestMapping(value = "/printbyskuall",
			method = RequestMethod.POST)
	public ModelAndView printByBarcodeAll(	@RequestBody String BarcodePrintParamObj,
									Model model,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.info("In printbysku Pur inv......{}"+BarcodePrintParamObj.toString());
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		BarcodePrintParamList barcodePrintParamObj = gson.fromJson(BarcodePrintParamObj, new TypeToken<BarcodePrintParamList>() {}.getType());
		String printstat = purInvoiceService.printByBarcodeAll(barcodePrintParamObj);
		//System.out.println("In printbysku Pur inv List :" + printstat);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.print(printstat);
		out.flush();
		return null;
	}


	
}

