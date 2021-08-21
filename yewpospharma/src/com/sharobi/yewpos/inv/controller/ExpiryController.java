package com.sharobi.yewpos.inv.controller;

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
import com.sharobi.yewpos.inv.model.Expiry;
import com.sharobi.yewpos.inv.model.ExpiryDTO;
import com.sharobi.yewpos.inv.model.ExpiryDetails;
import com.sharobi.yewpos.inv.model.ExpiryDetailsDTO;
import com.sharobi.yewpos.inv.service.ExpiryService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.CustomerMaster;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.proc.service.VendorService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/expiry")
public class ExpiryController {
	private final static Logger logger = LogManager.getLogger(ExpiryController.class);
       ExpiryService expiryService = new ExpiryService();
	   RoleService roleService = new RoleService();
       VendorService vendorService = new VendorService();
	   Gson gson = new Gson();
	
	@RequestMapping(value = "/searchexpiryinvoice",
			method = RequestMethod.POST)
	public ModelAndView searchExpiryInvoice(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
											HttpSession session,
											HttpServletResponse response) throws IOException {

		//System.out.println("searchExpiryInvoice mapper = "+commonResultSetMapper);
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
		commonResultSetMapper.setInvoiceNo(Constant.EXPIRY_INVOICE_MEMO + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setItemId(0);
		//		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.debug("In searchexpiryinvoice......{},{},{}", commonResultSetMapper.toString());
		List<ExpiryDetailsDTO> expiryDetailsDTOs = expiryService.getAllExpInvoice(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		//System.out.println("res:" + allVendors.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allVendors", allVendors);
		mav.addObject("expiryDetailsDTOs", expiryDetailsDTOs);
		mav.addObject("expiryId", 0);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE);
		mav.addObject("menuselect",menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		//System.out.println("invNo: "+commonResultSetMapper.getInvoiceNo());
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUE_PAGE);
		mav.addObject("selecteddistributor",commonResultSetMapper.getDistributorId());
		return mav;

	}
	
	@RequestMapping(value = "/searchexpiryinvoicebyid",method = RequestMethod.POST)
	public void searchExpiryInvoiceById(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException {

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setItemId(0);
			
			//logger.debug("In searchexpiryinvoicebyid......{},{},{}", commonResultSetMapper.toString());
			List<ExpiryDetailsDTO> expiryDetailsDTOs = expiryService.getAllExpInvoice(commonResultSetMapper);
			//System.out.println("res:"+expiryDetailsDTOs.toString());
			out.print(new Gson().toJson(expiryDetailsDTOs));
			out.flush();
		}

	}
	
	@RequestMapping(value="/getAllVendors",method=RequestMethod.GET)
	public void getDoctorbyId(HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getCustomerbyId......");
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
			List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
			//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
			//System.out.println("res:"+allVendors.toString());
			out.print(new Gson().toJson(allVendors));
			out.flush();
		}
	}
	
	@RequestMapping(value = "/searchexpiryinvoiceregister",
			method = RequestMethod.POST)
	public ModelAndView searchEIRegister(	@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
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
		commonResultSetMapper.setInvoiceNo(Constant.EXPIRY_INVOICE_MEMO + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
	//	logger.debug("In searchexpiryinvoiceregister......{},{},{}", commonResultSetMapper.toString());
		List<ExpiryDTO> expiryList = expiryService.getAllExpiryInvoice(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.PURCHASE_INVOICE_REGISTER_304, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("expiryList", expiryList);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUEREG);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUEREG_PAGE);
		return mav;
	}
	@RequestMapping(value = "/createorupdateexpiryinvoice",
			method = RequestMethod.POST)
	public void createOrUpdateExpiryInvoice(	@RequestBody String expString,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In createorupdateexpiryinvoice......{},{},{}", expString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			Expiry expiry = gson.fromJson(expString, new TypeToken<Expiry>() {}.getType());
			expiry.setCompanyId(userInfo.getCompanyId());
			expiry.setStoreId(userInfo.getStoreId());
			expiry.setCreatedBy(userInfo.getId());
			expiry.setFinyrId(userInfo.getFinyrId());
			expiry.setFinyrCode(userInfo.getFinyrCode());
			expiry.setLang(lang);
			expiry.setQs(Constant.EXPIRE_QS);
			//logger.debug("In  set expire qus......{},{},{}", expiry.toString());
			String res = expiryService.createOrUpdateExpiryInvoice(expiry);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loadexpinvoicedet/{expid}",
			method = RequestMethod.GET)
	public ModelAndView loadExpInvoiceDet(	@PathVariable("expid") Integer expid,
									Model model,
									HttpSession session) {
		//logger.debug("In loadexpinvoicedet......{},", expid);
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
		commonResultSetMapper.setExpiryId(expid);
		ExpiryDTO expiryHeader = expiryService.getExpiryInvoiceHeader(commonResultSetMapper);
		List<ExpiryDetailsDTO> expiryDetails = expiryService.geExpiryInvoiceDetails(commonResultSetMapper);
		List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendor(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.STOCK_ADJUSTMENT_202B, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("expiryHeader", expiryHeader);
		mav.addObject("expiryDetailsDTOs", expiryDetails);
		mav.addObject("allVendors", allVendors);
		mav.addObject("expiryId", expid);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.STOCK);
		menuselect.setChildsubMenu(Constant.STOCK_EXPIRY_ISSUE);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_STOCKEXPISSUE_PAGE);
		for(ExpiryDetailsDTO ob:expiryDetails){ 
		   mav.addObject("selecteddistributor", ob.getDistributorId());
		   break;
		}
		return mav;
	}
	
	@RequestMapping(value="/deleteexpinv",method=RequestMethod.POST)
	public void deleteExpInvoice(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In deleteExpInv......{}",commonResultsetMapperObj.toString());
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
			commonResultSetMapper.setDeletedBy(userInfo.getId());
			String res=expiryService.deleteExpInvoice(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/postexpiryinv",method=RequestMethod.POST)
	public void postExpiryInv(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In postexpiryinv......{}",commonResultsetMapperObj.toString());
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
			String res=expiryService.postExpiryInvoice(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
}
