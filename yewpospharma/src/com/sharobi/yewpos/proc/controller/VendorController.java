/**
 *
 */
package com.sharobi.yewpos.proc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.inv.model.AreaDTO;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.CustPaymentDetailsAllDTO;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.proc.model.DistributorDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.model.DistributorPayment;
import com.sharobi.yewpos.proc.model.PaymentDetailsAllDTO;
import com.sharobi.yewpos.proc.model.PaymentDetailsByIdDTO;
import com.sharobi.yewpos.proc.model.PaymentMode;
import com.sharobi.yewpos.proc.model.Purchase;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;
/*import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;*/

/**
 * @author Manodip,habib
 *
 */
@Controller
@RequestMapping("/vendor")
public class VendorController {
	private final static Logger logger = LogManager.getLogger(VendorController.class);
	private final static RoleService roleService = new RoleService();
	private final static VendorService vendorService = new VendorService();
	private final static Gson gson = new Gson();
	private final static  StoreMgntService storeService=new StoreMgntService();
	@RequestMapping(value = "/loadvendor",
					method = RequestMethod.GET)
	public ModelAndView loadvendor(	Model model,
									HttpSession session) {
		logger.debug("In loadvendor......");
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
		System.out.println("res:" + allVendors.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.VENDOR_MASTER_303, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.VENDOR);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAGE);
		return mav;
	}

	@RequestMapping(value = "/addVendor",
					method = RequestMethod.POST)
	public void addUnit(@RequestBody String DistributorMasterObj,
						HttpSession session,
						HttpServletResponse response) throws IOException {
		logger.debug("In addVendor......{},{},{}", DistributorMasterObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			DistributorMaster DistributorMasterObject = gson.fromJson(DistributorMasterObj, new TypeToken<DistributorMaster>() {}.getType());
			DistributorMasterObject.setCompanyId(userInfo.getCompanyId());
			DistributorMasterObject.setStoreId(userInfo.getStoreId());
			DistributorMasterObject.setCreatedBy(userInfo.getId());
			DistributorMasterObject.setFinyrId(userInfo.getFinyrId());
			DistributorMasterObject.setLang(lang);
			System.out.println("___________DistributorMasterObject__________::"+gson.toJson(DistributorMasterObject));
			String res = vendorService.addVendor(DistributorMasterObject);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/editVendor",
					method = RequestMethod.POST)
	public void editUnit(	@RequestBody String DistributorMasterObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In editVendor......{}", DistributorMasterObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			DistributorMaster DistributorMasterObject = gson.fromJson(DistributorMasterObj, new TypeToken<DistributorMaster>() {}.getType());
			DistributorMasterObject.setCompanyId(userInfo.getCompanyId());
			DistributorMasterObject.setStoreId(userInfo.getStoreId());
			DistributorMasterObject.setFinyrId(userInfo.getFinyrId());
			DistributorMasterObject.setCreatedBy(userInfo.getId());
			DistributorMasterObject.setLang(lang);
			String res = vendorService.editVendor(DistributorMasterObject);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/delvendor",
					method = RequestMethod.POST)
	public void delVendor(	@RequestBody String CommonResultSetMapperObj,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In delvendor......{}", CommonResultSetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapperObject = gson.fromJson(CommonResultSetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapperObject.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapperObject.setStoreId(userInfo.getStoreId());
			commonResultSetMapperObject.setLang(lang);
			String res = vendorService.deleteVendor(commonResultSetMapperObject);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deleteVendor/{id}",
					method = RequestMethod.GET)
	public void deleteUnit(	@PathVariable("id") int id,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		logger.debug("In deleteVendor......{}", id);
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = vendorService.deleteVendor(id);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loadvendorpay/{vid}",
					method = RequestMethod.GET)
	public ModelAndView loadVendorPayment(	@PathVariable("vid") int vid,
											Model model,
											HttpSession session) {
		logger.debug("In loadvendorpay......");
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
		List<DistributorDTO> allVendors = vendorService.getAllVendorOutStanding(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		System.out.println("res:" + allVendors.toString());
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		List<PaymentMode> paymentModes = gson.fromJson(vendorService.getPaymentModes(commonResultSetMapper), new TypeToken<List<PaymentMode>>() {}.getType());
		System.out.println("paymentModes:" + paymentModes.toString());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.VENDORPAY);
		mav.addObject("isAccount",userInfo.getIs_account());
		mav.addObject("menuselect", menuselect);
		mav.addObject("allVendors", allVendors);
		mav.addObject("paymentModes", paymentModes);
		mav.addObject("vid", commonResultSetMapper.getDistributorId());
		mav.addObject("is_account", userInfo.getIs_account());
		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAYMENT_PAGE);
		return mav;
	}

	@RequestMapping(value = "/loadvendorpayreg",
					method = RequestMethod.GET)
	public ModelAndView loadVendorPaymentReg(	Model model,
												HttpSession session) {
		logger.debug("In loadvendorpayreg......");
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
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		System.out.println("commonResultSetMapper.getStartDate()=" + commonResultSetMapper.getStartDate());
		System.out.println("commonResultSetMapper.getStartDate()");
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		System.out.println("res:" + allVendors.toString());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.VENDORPAY);
		mav.addObject("menuselect", menuselect);
		mav.addObject("allVendors", allVendors);
		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAYMENT_REG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchvendorpayregister",
					method = RequestMethod.POST)
	public ModelAndView searchVendorPayRegister(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		commonResultSetMapper.setInvoiceNo(Constant.VENDOR_PAY_MEMO + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		//logger.debug("In searchVendorPayRegister......{}", commonResultSetMapper.toString());
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		List<PaymentDetailsAllDTO> paymentDetailsAllDTOs = new ArrayList<PaymentDetailsAllDTO>();
		paymentDetailsAllDTOs = gson.fromJson(vendorService.getVendorAllPaymentDetails(commonResultSetMapper), new TypeToken<List<PaymentDetailsAllDTO>>() {}.getType());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.VENDOR_PAYMENT_306, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("paymentDetailsAllDTOs", paymentDetailsAllDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.VENDORPAY);
		mav.addObject("menuselect", menuselect);
		mav.addObject("is_acc", userInfo.getIs_account());
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAYMENT_REG_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getvendordetailsbyid",
					method = RequestMethod.POST)
	public void vendorDetailsById(	@RequestBody String CommonResultSetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In getvendordetailsbyid......{}", CommonResultSetMapperObj.toString());

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapperObject = gson.fromJson(CommonResultSetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapperObject.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapperObject.setStoreId(userInfo.getStoreId());
			commonResultSetMapperObject.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapperObject.setLang(lang);
			String res = vendorService.getVendorDet(commonResultSetMapperObject);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getvendorpaymentdetailsbyid/{paymentid}",
					method = RequestMethod.POST)
	public ModelAndView vendorPaymentDetailsById(	@PathVariable("paymentid") int paymentid,
													HttpSession session,
													HttpServletResponse response) throws IOException {
		logger.debug("In vendorPaymentDetailsById......{}", paymentid);
		logger.debug("In loadvendorpay......");
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
		List<PaymentMode> paymentModes = gson.fromJson(vendorService.getPaymentModes(commonResultSetMapper), new TypeToken<List<PaymentMode>>() {}.getType());
		System.out.println("paymentModes:" + paymentModes.toString());
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.PROCUREMENT);
		menuselect.setSubMenu(Constant.VENDORPAY);
		mav.addObject("menuselect", menuselect);
		mav.addObject("paymentModes", paymentModes);
		mav.addObject("vid", commonResultSetMapper.getDistributorId());
		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAYMENT_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchvendorpendingpayment",
					method = RequestMethod.POST)
	public void searchVendorPendingPayment(	@RequestBody String commonResultSetMapperStr,
											HttpSession session,
											HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
		String df = "yyyy-MM-dd";
		commonResultSetMapper.setPaymentDate(DateUtil.getCurrentDateString(df));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		logger.debug("In searchVendorPendingPayment......{}", commonResultSetMapper.toString());
		String res = vendorService.getVendorPendingPayment(commonResultSetMapper);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(res);
		out.flush();
	}

	@RequestMapping(value = "/getvendorsinglepaymentdetails",
					method = RequestMethod.POST)
	public void getVendorSinglePaymentDetails(	@RequestBody String commonResultSetMapperStr,
												HttpSession session,
												HttpServletResponse response) throws IOException {

		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setLang(lang);
		logger.debug("In getVendorSinglePaymentDetails......{}", commonResultSetMapper.toString());
		String res = vendorService.getVendorSinglePaymentDetailsById(commonResultSetMapper);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(res);
		out.flush();
	}

	@RequestMapping(value = "/createorupdatevendorpayment",
					method = RequestMethod.POST)
	public void createOrUpdateVendorPayment(@RequestBody String DistributorPaymentObj,
											HttpSession session,
											HttpServletResponse response) throws IOException {
		logger.debug("In createOrUpdateVendorPayment......{},{},{}", DistributorPaymentObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			DistributorPayment distributorPayment = gson.fromJson(DistributorPaymentObj, new TypeToken<DistributorPayment>() {}.getType());
			distributorPayment.setCompanyId(userInfo.getCompanyId());
			distributorPayment.setStoreId(userInfo.getStoreId());
			distributorPayment.setCreatedBy(userInfo.getId());
			distributorPayment.setFinyrId(userInfo.getFinyrId());
			distributorPayment.setFinyrCode(userInfo.getFinyrCode());
			distributorPayment.setLang(lang);
			distributorPayment.setQs(Constant.VENDOR_PAYMENT_QS);
			distributorPayment.setIs_account(userInfo.getIs_account());

			System.err.println(distributorPayment.toString());
			String res = vendorService.createOrUpdateVendorPayment(distributorPayment);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postvendorpayment",
					method = RequestMethod.POST)
	public void postVendorPayment(	@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		logger.debug("In postvendorpayment......{}", commonResultsetMapperObj.toString());
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
			String res = vendorService.postvendorpayment(commonResultSetMapper);
			System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/*
	* add on Mar 5, 2018
	*/



	@RequestMapping(value = "searchledgerusinggroup", method = RequestMethod.POST)
	public List<AccountDTO> searchledgerusinggroup(@RequestBody String commonresultsetmapperObj, HttpSession session,
			HttpServletResponse response) throws IOException {

		logger.debug("In searchledgerusinggrou......{}", commonresultsetmapperObj);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonresultsetmapperObj,
					new TypeToken<CommonResultSetMapper>() {
					}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			//commonResultSetMapper.setId(1);
			List<AccountDTO> allvendorledgerlist = vendorService.searchallvendelledger(commonResultSetMapper);

			logger.info("autocompleteitem List :" + allvendorledgerlist);
			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(allvendorledgerlist));

		}
		return null;
	}



	@RequestMapping(value = "/vendorpaymentprint/{id}",method = RequestMethod.GET)
	public ModelAndView vendorpayment(@PathVariable("id") int id,Model model,HttpSession session,	@RequestParam(required = false,defaultValue = "0") String custPaymentId) {
		logger.debug("In vendorpayment......{}", id);

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
		commonResultSetMapper.setLang(lang);
		commonResultSetMapper.setPaymentId(id);
		PaymentDetailsAllDTO paymentdet=vendorService.getVendorPayment(commonResultSetMapper);

		if (paymentdet.getChequeNo().trim().length()==0) {
			paymentdet.setChequeNo("NA");
		}
		/*if (paymentdet.getChequeDate().trim().length()==0) {
			paymentdet.setChequeDate("NA");
		}*/
		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		mav.addObject("custDet", paymentdet);
		mav.addObject(Constant.SES_LOGGED_IN_STORE,store);
		mav.addObject("cur",userInfo.getCurrency_code());
		mav.addObject("curdes", userInfo.getCurrency_desc());

		System.out.println(store.toString());

		mav.setViewName(ForwardConstants.VIEW_VENDOR_PAYMENT_PRINT);
		return mav;
	}



}
