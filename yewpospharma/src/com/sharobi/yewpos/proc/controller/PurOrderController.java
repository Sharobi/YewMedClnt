/**
 * 
 */
package com.sharobi.yewpos.proc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.model.PurchaseOrder;
import com.sharobi.yewpos.proc.model.PurchaseOrderDTO;
import com.sharobi.yewpos.proc.model.PurchaseOrderDetailsDTO;
import com.sharobi.yewpos.proc.service.PurInvoiceService;
import com.sharobi.yewpos.proc.service.PurOrderService;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/purorder")
public class PurOrderController {
	private final static Logger logger = LogManager.getLogger(PurOrderController.class);
	private final static RoleService roleService = new RoleService();
	private final static VendorService vendorService = new VendorService();
	private final static InvSetupService invSetUpService = new InvSetupService();
	private final static PurOrderService purOrderService = new PurOrderService();
	private final static Gson gson = new Gson();
	
	@RequestMapping(value = "/loadpurorder",
			method = RequestMethod.GET)
	public ModelAndView loadPurInvoice(	Model model,
									HttpSession session) {
		logger.debug("In loadpurorder......");
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
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("noOfDue",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_NO_OF_DUEDAYS));
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("purOrderQtyFromSale",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURORDER_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/loadpurordregister",
			method = RequestMethod.GET)
	public ModelAndView loadPurInvRegister(	Model model,
											HttpSession session) {
		logger.debug("In loadpurordregister......");
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
		menuselect.setSubMenu(Constant.PUR_ORD_REG);
		mav.addObject("menuselect", menuselect);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_PURORDREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createorupdatepurchaseorder",
			method = RequestMethod.POST)
	public void createPurchaseOrder(	@RequestBody String purchaseString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In createorupdatepurchaseorder......{},{},{}", purchaseString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			PurchaseOrder purchaseOrder = gson.fromJson(purchaseString, new TypeToken<PurchaseOrder>() {}.getType());
			purchaseOrder.setCompanyId(userInfo.getCompanyId());
			purchaseOrder.setStoreId(userInfo.getStoreId());
			purchaseOrder.setCreatedBy(userInfo.getId());
			purchaseOrder.setFinyrId(userInfo.getFinyrId());
			purchaseOrder.setFinyrCode(userInfo.getFinyrCode());
			purchaseOrder.setLang(lang);
			String res = purOrderService.createOrUpdatePurchaseOrder(purchaseOrder);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/createtemppofromsale",method=RequestMethod.POST)
	public void createTempPOFromSale(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		logger.debug("In createTempPOFromSale......{}",commonResultsetMapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=purOrderService.createTempPOFromSale(commonResultSetMapper);
			System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/loadpurorderdet/{pid}",
			method = RequestMethod.GET)
	public ModelAndView loadPurOrderDet(	@PathVariable("pid") Integer pid,
									Model model,
									HttpSession session) {
		logger.debug("In loadpurorderdet......{},", pid);
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
		commonResultSetMapper.setPurchaseOrderId(pid);		
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		PurchaseOrderDTO purchaseOrderHeader = purOrderService.getPurchaseOrderHeader(commonResultSetMapper);
		commonResultSetMapper.setDistributorId(purchaseOrderHeader.getDistributorId());
		DistributorMaster vendorDetails = gson.fromJson(vendorService.getVendorDet(commonResultSetMapper), new TypeToken<DistributorMaster>() {}.getType()); 
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		List<PurchaseOrderDetailsDTO> purchaseOrderDetails = purOrderService.getPurchaseOrderDetails(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_301, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("purchaseOrderHeader", purchaseOrderHeader);
		mav.addObject("purchaseOrderDetails", purchaseOrderDetails);
		mav.addObject("allRacks",allRacks);
		mav.addObject("allGroups",allGroups);
		mav.addObject("allSchedules",allSchedules);
		mav.addObject("distEmail",vendorDetails.getEmail());
		mav.addObject("retailerProfitPrcnt",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PROFIT_PERCENTAGE_OF_RETAILER));
		mav.addObject("purOrderQtyFromSale",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_PUR_ORDER_QTY_FROM_SALE));
		mav.addObject("saleHistoryDay",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_SALE_HISTORY_DAY));
		mav.addObject("dayToPurchase",CommonResource.getProperty(CommonResource.GENERAL_SETTINGS_OF_DAY_TO_PURCHASE));
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURORDER_DETAILS_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/searchpurchaseorderregister",
			method = RequestMethod.POST)
	public ModelAndView searchPORegister(	@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_ORDER + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		logger.debug("In searchpurchaseorderregister......{}", commonResultSetMapper.toString());
		List<PurchaseOrderDTO> purchaseOrderList = purOrderService.getAllPurchaseOrder(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purchaseOrderList", purchaseOrderList);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_ORD_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_PURORDREG_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/getpurorderbytype",
			method = RequestMethod.POST)
	public void getPurOrderByType(@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In getPurOrderByType......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.getPurOrderByType(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/deletepurorder",
			method = RequestMethod.POST)
	public void deletePurOrder(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In deletepurorder......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.deletePurchaseOrder(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/postpurorder",
			method = RequestMethod.POST)
	public void postPurOrder(	@RequestBody String commonResultsetMapperObj,
						HttpSession session,
						HttpServletResponse response) throws IOException {
		logger.debug("In postpurorder......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.postPurchaseOrder(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getpurorderdetbyordernoforpi",
			method = RequestMethod.POST)
	public void getPurOrderDetByOrderNoforPI(@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In getPurOrderDetByOrderNoforPI......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.getPurOrderDetailsByOrderNoForPI(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/closepurorder",
			method = RequestMethod.POST)
	public void closePurOrder(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In closepurorder......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.closePurchaseOrder(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getcalpurordrqty",
			method = RequestMethod.POST)
	public void getCalPurOrdrQty(	@RequestBody String commonResultsetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In getcalpurordrqty......{}", commonResultsetMapperObj.toString());
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
			String res = purOrderService.calPurOrdrQty(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
}
