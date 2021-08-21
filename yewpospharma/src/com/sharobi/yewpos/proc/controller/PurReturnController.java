/**
 * 
 */
package com.sharobi.yewpos.proc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ReturnReasonTypeMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.pos.model.SaleReturnDetailsDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseReturn;
import com.sharobi.yewpos.proc.model.PurchaseReturnDTO;
import com.sharobi.yewpos.proc.model.PurchaseReturnDetailsDTO;
import com.sharobi.yewpos.proc.service.PurReturnService;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author Manodip,habib
 * 
 */
@Controller
@RequestMapping("/purret")
public class PurReturnController {
	private final static Logger logger = LogManager.getLogger(PurReturnController.class);
	private final static PurReturnService purReturnService = new PurReturnService();
	private final static RoleService roleService = new RoleService();
	private final static VendorService vendorService = new VendorService();
	private final static InvSetupService invSetUpService=new InvSetupService();
	private final static Gson gson = new Gson();

	@RequestMapping(value = "/loadpurreturn",
					method = RequestMethod.GET)
	public ModelAndView loadPurReturn(	Model model,
										HttpSession session,
										@RequestParam(	required = false,
														defaultValue = "0") String purRetId) {
		logger.debug("In loadpurreturn.....,{}", purRetId);
		if (!purRetId.equals("0")) {
			purRetId = (String.valueOf(purRetId).split("-")[2]);
			System.out.println("new purRetId=" + purRetId);
		}
		PurchaseReturnDTO purchaseReturnDTO = new PurchaseReturnDTO();
		List<PurchaseReturnDetailsDTO> purchaseReturnDetailsDTOs = new ArrayList<PurchaseReturnDetailsDTO>();
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
		if (!purRetId.equals("0")) {			
			commonResultSetMapper.setPurchaseReturnId(Integer.valueOf(purRetId));			
			purchaseReturnDTO = gson.fromJson(purReturnService.getPurInvReturnMemoHeaderById(commonResultSetMapper), new TypeToken<PurchaseReturnDTO>() {}.getType());
			purchaseReturnDetailsDTOs = gson.fromJson(purReturnService.getPurInvReturnMemoDetailsById(commonResultSetMapper), new TypeToken<List<PurchaseReturnDetailsDTO>>() {}.getType());
		}
		commonResultSetMapper.setRetType("P");
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		List<ReturnReasonTypeMaster> allReturnReasonType = invSetUpService.getAllReturnReasonType(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_RETURN_DEBIT_NOTE_302, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purRetId", purRetId);
		mav.addObject("purchaseReturnDTO", purchaseReturnDTO);
		mav.addObject("purchaseReturnDetailsDTOs", purchaseReturnDetailsDTOs);
		mav.addObject("allReturnReasonTypes",allReturnReasonType);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_RET);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_PURRETURN_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getretheaderbyidforpur",
			method = RequestMethod.POST)
	public void postPurInv(	@RequestBody String commonResultsetMapperObj,
						HttpSession session,
						HttpServletResponse response) throws IOException {
		logger.debug("In getRetHeaderbyIdForPur......{}", commonResultsetMapperObj.toString());
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
			String res = purReturnService.getPurInvReturnMemoHeaderById(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/loadpurretregister",
					method = RequestMethod.GET)
	public ModelAndView loadPurRetRegister(	Model model,
											HttpSession session) {
		logger.debug("In loadpurretregister......");
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
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_RETURN_REGISTER_305, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_RET_REG);
		mav.addObject("menuselect", menuselect);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		mav.setViewName(ForwardConstants.VIEW_PURRETURNREG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchpurreturnmemoinvoice",
					method = RequestMethod.POST)
	public ModelAndView searchpurreturnmemoinvoice(	@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_INVOICE_RETURN_MEMO + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		logger.debug("In searchpurreturnmemoinvoice......{},{},{}", commonResultSetMapper.toString());
		List<PurchaseReturnDTO> purchaseReturnDTOs = purReturnService.getAllPurInvReturnMemoInvoice(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_RETURN_REGISTER_305, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("purchaseReturnDTOs", purchaseReturnDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.PUR_RET_REG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_PURRETURNREG_PAGE);
		return mav;

	}

	@RequestMapping(value = "/getpurinvdetbyinvnoforret",
					method = RequestMethod.POST)
	public void getPurInvDetByInvNoforRet(@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		logger.debug("In getPurInvDetByInvNoforRet......{}", commonResultsetMapperObj.toString());
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
			//commonResultSetMapper.setInvoiceNo(Constant.PURCHASE_INVOICE + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
			commonResultSetMapper.setLang(lang);
			String res = purReturnService.getPurInvDetailsByInvNoForRet(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getpurinvdetbyitemidforret",
					method = RequestMethod.POST)
	public void getPurInvDetByItemidforRet(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		logger.debug("In getPurInvDetByItemidforRet......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setNoOfMonthBefore(Constant.SHOW_PURCHASE_INVOICE_RETURN_ITEM_NOOFMONTHSBEFORE);
			commonResultSetMapper.setLang(lang);
			String res = purReturnService.getPurInvDetailsByItemIdForRet(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getpurinvdetbyskuforret",
			method = RequestMethod.POST)
	public void getPurInvDetBySkuforRet(@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In getPurInvDetBySkuforRet......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setNoOfMonthBefore(Constant.SHOW_PURCHASE_INVOICE_RETURN_ITEM_NOOFMONTHSBEFORE);
			commonResultSetMapper.setLang(lang);
			String res = purReturnService.getPurInvDetailsBySkuForRet(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/createorupdatepurchasereturn",
					method = RequestMethod.POST)
	public void createOrUpdatePurchaseReturnInv(	@RequestBody String purRetString,
												HttpSession session,
												HttpServletResponse response) throws IOException {
		logger.debug("In createOrUpdatePurchaseReturnInv......{},{},{}", purRetString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			PurchaseReturn purchaseReturn = gson.fromJson(purRetString, new TypeToken<PurchaseReturn>() {}.getType());
			purchaseReturn.setCompanyId(userInfo.getCompanyId());
			purchaseReturn.setStoreId(userInfo.getStoreId());
			purchaseReturn.setCreatedBy(userInfo.getId());
			purchaseReturn.setFinyrId(userInfo.getFinyrId());
			purchaseReturn.setFinyrCode(userInfo.getFinyrCode());
			purchaseReturn.setLang(lang);
			purchaseReturn.setQs(Constant.PURCHASE_RETURN_PAYMENT_QS);
			purchaseReturn.setIs_account(userInfo.getIs_account());
			System.err.println(purchaseReturn.toString());
			
			logger.debug("In createOrUpdatePurchaseReturnInv call", purchaseReturn.toString());
			String res = purReturnService.createOrUpdatePurReturnInvoice(purchaseReturn);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deletepurretinv",
					method = RequestMethod.POST)
	public void deletePurRetInv(	@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In deletepurretinv......{}", commonResultsetMapperObj.toString());
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
			String res = purReturnService.deletePurRetInvoice(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postpurretinv",
					method = RequestMethod.POST)
	public void postPurRetInv(@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		logger.debug("In postPurRetInv......{}", commonResultsetMapperObj.toString());
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
			String res = purReturnService.postPurRetInvoice(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getprevretadjdetbypid",
			method = RequestMethod.POST)
	public void getPrevRetAdjDetailsByPid(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
	
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
			System.out.println("commonResultSetMapper.getInvoiceNo()=" + commonResultSetMapper.getPurInvId());
			logger.debug("In getprevretadjdetbypid......{}", commonResultsetMapperObj.toString());
			String res = purReturnService.getPrevRetAdjDetailsByPid(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getretdetforadj",
			method = RequestMethod.POST)
	public void getReturnMemoDetailsForAdj(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
	
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
		//	if (null == commonResultSetMapper.getInvoiceNo()) {} else {
		//		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE_RETURN_MEMO + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//	}
			commonResultSetMapper.setLang(lang);
			System.out.println("commonResultSetMapper.getInvoiceNo()=" + commonResultSetMapper.getInvoiceNo());
			logger.debug("In getretdetforadj......{}", commonResultsetMapperObj.toString());
			String res = purReturnService.getReturnMemoDetailsForAdj(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/getexpdetforadj",
			method = RequestMethod.POST)
	public void getReturnExpDetailsForAdj(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
	
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
		//	if (null == commonResultSetMapper.getInvoiceNo()) {} else {
		//		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE_RETURN_MEMO + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//	}
			commonResultSetMapper.setLang(lang);
			System.out.println("commonResultSetMapper.getInvoiceNo()=" + commonResultSetMapper.getInvoiceNo());
			logger.debug("In getexpdetforadj......{}", commonResultsetMapperObj.toString());
			String res = purReturnService.getReturnExpDetailsForAdj(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/postallselectedpurchasereturn",
			method = RequestMethod.POST)
	public void postAllSelectedPurReturn(@RequestBody String purchaseReturnString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In postallselectedpurchasereturn......{},{},{}", purchaseReturnString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			PurchaseReturn purchaseReturn = gson.fromJson(purchaseReturnString, new TypeToken<PurchaseReturn>() {}.getType());
			purchaseReturn.setCompanyId(userInfo.getCompanyId());
			purchaseReturn.setStoreId(userInfo.getStoreId());
			purchaseReturn.setCreatedBy(userInfo.getId());
			purchaseReturn.setFinyrId(userInfo.getFinyrId());
			purchaseReturn.setFinyrCode(userInfo.getFinyrCode());
			purchaseReturn.setLang(lang);
			String res = purReturnService.postAllSelectedPurchaseReturn(purchaseReturn);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
}
