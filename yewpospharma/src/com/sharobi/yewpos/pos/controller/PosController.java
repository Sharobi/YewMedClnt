/**
 * 
 */
package com.sharobi.yewpos.pos.controller;

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
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.EsiCodeMaster;
import com.sharobi.yewpos.pos.model.GenderMaster;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.pos.model.Sales;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.ReprintCashMemoService;
import com.sharobi.yewpos.proc.controller.PurInvoiceController;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.model.PurchaseDetails;
import com.sharobi.yewpos.proc.service.PurInvoiceService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author habib,Manodip
 * 
 */
@Controller
@RequestMapping("/pos")
public class PosController {
	private final static Logger logger = LogManager.getLogger(PosController.class);
	private final static CashMemoService cashMemoService = new CashMemoService();
	private final static RoleService roleService = new RoleService();
	private final static ReprintCashMemoService reprintCashMemoService = new ReprintCashMemoService();
	private final static Gson gson = new Gson();

	@RequestMapping(value = "/cashmemo",
					method = RequestMethod.GET)
	public ModelAndView cashMemo(	Model model,
									HttpSession session,
									@RequestParam(	required = false,
													defaultValue = "0") String saleId) {
		//logger.debug("In cashmemo..x....{}", saleId);
		if (!saleId.equals("0")) {
			saleId = (String.valueOf(saleId).split("-")[2]);
			//System.out.println("new saleId=" + saleId);
		}
		ModelAndView mav = new ModelAndView();
		SaleReturnDTO saleReturnDTO = new SaleReturnDTO();
		SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
		List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if (!saleId.equals("0")) {
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			saleHeaderDTO = gson.fromJson(cashMemoService.getSalesHeader(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
			saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetails(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
			//saleReturnDTO.setIsPosted(1);
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<TypeMaster> typeMasters = cashMemoService.getTypes(commonResultSetMapper);
		List<EsiCodeMaster> esiCodeMasters = cashMemoService.getEsiCode(commonResultSetMapper);
		List<GenderMaster> genderlist = cashMemoService.getGenderList(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.CASH_MEMO_101, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleId", saleId);
		mav.addObject("saleRetId", 0);// same page sale ret
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("saleReturnDTO", saleReturnDTO);
		mav.addObject("typeMasters", typeMasters);
		mav.addObject("esiCodeMasters", esiCodeMasters);
		mav.addObject("genderlist", genderlist);
		mav.addObject("isAccount", userInfo.getIs_account());
		mav.setViewName(ForwardConstants.VIEW_POS_PAGE);
		return mav;
	}

	@RequestMapping(value = "/createorupdatesalesinvoice",
					method = RequestMethod.POST)
	public void createOrUpdateSalesInv(	@RequestBody String salesString,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In createOrUpdateSalesInv......{},{},{}", salesString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Sales sales = gson.fromJson(salesString, new TypeToken<Sales>() {}.getType());
			sales.setCompanyId(userInfo.getCompanyId());
			sales.setStoreId(userInfo.getStoreId());
			sales.setCreatedBy(userInfo.getId());
			sales.setFinyrId(userInfo.getFinyrId());
			sales.setFinyrCode(userInfo.getFinyrCode());
			sales.setLang(lang);
			sales.setQs(Constant.SALE_QS);
			sales.setIs_account(userInfo.getIs_account());
			//System.err.println(sales.toString());
			//logger.debug("In final......{},{},{}", sales.toString());
			String res = cashMemoService.createOrUpdateSalesInvoice(sales);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/modifycashmemo",
					method = RequestMethod.GET)
	public ModelAndView modifyCashmemo(	Model model,
										HttpSession session) {
		//logger.debug("In modifycashmemo......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.MC_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_MODIFYCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/reprintcashmemo",
					method = RequestMethod.GET)
	public ModelAndView reprintCashmemo(Model model,
										HttpSession session) {
		//logger.debug("In reprintcashmemo......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_REPRINTCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchsalesinvoice",
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
		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.debug("In searchsalesinvoice......{},{},{}", commonResultSetMapper.toString());
		//System.out.println("commonResultSetMapper::"+commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = cashMemoService.getAllSalesInvoice(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.MC_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_MODIFYCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/deletesalesinv",
					method = RequestMethod.POST)
	public void deleteSalesInv(	@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.debug("In deleteSalesInv......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.deleteSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postsalesinv",
					method = RequestMethod.POST)
	public void postSalesInv(	@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.debug("In postsalesinv......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.postSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getsaleheaderdetbycustomerphno",
					method = RequestMethod.POST)
	public void getSaleHeaderByCustPhNo(@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getsaleheaderdetbycustomerphno......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.getCustomerLastSalesHeader(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getsaledetbycustomerphno",
					method = RequestMethod.POST)
	public void getSaleDetByCustPhNo(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getsaledetbycustomerphno......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.getSaleDetails(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/reprintbacktosearch",
					method = RequestMethod.GET)
	public ModelAndView reprintbacktosearch(Model model,
											HttpSession session) throws IOException {

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
		String invno = (String) session.getAttribute("reprintSearchInvNo");
		String finYrCode = (String) session.getAttribute("reprintSearchFinyrCode");
		commonResultSetMapper.setStartDate((String) session.getAttribute("reprintSearchStrtDate"));
		commonResultSetMapper.setEndDate((String) session.getAttribute("reprintSearchEndDate"));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + finYrCode + "/" + invno);
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.debug("In reprintbacktosearch......{},{},{}", commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = reprintCashMemoService.getAllSalesInvoice(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);
		session.setAttribute("reprintSearchStrtDate", commonResultSetMapper.getStartDate());
		session.setAttribute("reprintSearchEndDate", commonResultSetMapper.getEndDate());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		commonResultSetMapper.setFinyrCode(finYrCode); // after result replaced with only financial year code
		mav.setViewName(ForwardConstants.VIEW_REPRINTCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/salebillprint",
					method = RequestMethod.POST)
	public void saleBillPrint(	@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.debug("In saleBillPrint......{}", commonResultsetMapperObj.toString());
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
			String res = cashMemoService.saleBillPrint(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getplaceoftreatmentautocomplete",
					method = RequestMethod.GET)
	public void getplaceoftreatmentListAutocom(	@RequestParam String tagName,
												HttpSession session,
												HttpServletResponse response) throws IOException {
	//logger.debug("In getplaceoftreatmentautocomplete......{}", tagName.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
	 
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setRemarks(tagName.toString());
			//			gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());

			String res = cashMemoService.getPlaceOfTreatmentListAutocom(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/saleitemdetforret",
					method = RequestMethod.POST)
	public void saleItemDetforRet(	@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In saleItemDetforRet......{}", commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setNoOfDays(Integer.valueOf(CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEITEM_DETAILS_NOOFDAYS)));
			commonResultSetMapper.setLang(lang);
			String res = cashMemoService.getSaleItemDetailsforRet(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postallselectedesalesinvoice",
					method = RequestMethod.POST)
	public void postAllSelectedSalesInv(	@RequestBody String salesString,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In postAllSelectedSalesInv......{},{},{}", salesString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Sales sales = gson.fromJson(salesString, new TypeToken<Sales>() {}.getType());
			sales.setCompanyId(userInfo.getCompanyId());
			sales.setStoreId(userInfo.getStoreId());
			sales.setCreatedBy(userInfo.getId());
			sales.setFinyrId(userInfo.getFinyrId());
			sales.setFinyrCode(userInfo.getFinyrCode());
			sales.setLang(lang);
			String res = cashMemoService.postAllSelSalesInvoice(sales);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}
	
	/*
	 * for search ledger
	 */

	@RequestMapping(value = "/searchledgerbytype", method = RequestMethod.POST)
	public ResponseObj searchledger(@RequestParam String tagName,@RequestParam int type,HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In searchledgerbytype---------", tagName,type);

		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}

	

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setName(tagName.trim());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			commonResultSetMapper.setId(type);

			List<AccountDTO> ledgerlist = this.cashMemoService.searchledger(commonResultSetMapper);
			//logger.info("searchledgerbytype List :" + ledgerlist);
		 
			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(ledgerlist));
		}
		return null;
	}
}
