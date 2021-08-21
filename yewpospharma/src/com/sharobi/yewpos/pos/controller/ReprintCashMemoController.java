package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.service.ManufacturerService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.CustomerDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.TaxDetailsSaleBillDTO;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.pos.service.ReprintCashMemoService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.CommonResource;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/reprintmemo")
public class ReprintCashMemoController {
	private final static Logger logger = LogManager.getLogger(PosController.class);
	private final static ReprintCashMemoService reprintCashMemoService = new ReprintCashMemoService();
	private final static CashMemoService cashMemoService = new CashMemoService();
	private final static RoleService roleService = new RoleService();
	private final static ManufacturerService manufacturerService=new ManufacturerService();

	private final static Gson gson = new Gson();

	@RequestMapping(value = "/searchreprintsalesinvoice",
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
		String finYrCode= commonResultSetMapper.getFinyrCode();
		session.setAttribute("reprintSearchStrtDate", commonResultSetMapper.getStartDate());
		session.setAttribute("reprintSearchEndDate", commonResultSetMapper.getEndDate());
		session.setAttribute("reprintSearchInvNo", commonResultSetMapper.getInvoiceNo());
		session.setAttribute("reprintSearchFinyrCode", finYrCode);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + finYrCode + "/" + commonResultSetMapper.getInvoiceNo());
		//System.out.println("invoice no ::::::::::::" +commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.debug("In searchsalesinvoice......{},{},{}", commonResultSetMapper.toString());
		List<SaleDetailsAllDTO> saleDetailsAllDTOs = reprintCashMemoService.getAllSalesInvoice(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.MODIFY_CANCEL_CASHMEMO_103, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleDetailsAllDTOs", saleDetailsAllDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_CASH_MEMO);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		commonResultSetMapper.setFinyrCode(finYrCode); // after result replaced with only financial year code
		mav.setViewName(ForwardConstants.VIEW_REPRINTCASHMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/cashmemo",
			method = RequestMethod.GET)
	public ModelAndView cashMemo(	Model model,
								HttpSession session,
								@RequestParam(	required = false,
												defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl) {
	//logger.debug("In cashmemo......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		//System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
	List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	///List<CustomerDTO> customerDTO = new ArrayList<CustomerDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			//System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		//logger.debug("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		//System.out.println("ss="+commonResultSetMapper.getNoteLineOne());	
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
		/*for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/
		mav.addObject("saleId", saleId);
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
		///mav.addObject("customerDTO",customerDTO);
	}
	StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	int isExclusive =  store.getIsExclusive();
	//System.out.println("+++++++++++++++++++++++++IS EXCLUSIVE+++++++++++++++++++++++++::"+isExclusive);	
	if(isExclusive==0)///Must be 0
	{ 
			/*
			 * if(userInfo.getCompanyId()==420) {
			 * mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_SPL_PAGE); } else {
			 */
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_PAGE);
		/*mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_SPL_PAGE);*/
		
	}
	else
	{
		/*if(userInfo.getCompanyId() == 413 || userInfo.getCompanyId() == 414 || userInfo.getCompanyId() == 1){*/
		if(userInfo.getCompanyId() == 413 || userInfo.getCompanyId() == 414){
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE_NEW);
		}
		else if(userInfo.getCompanyId()==420)
		{
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_SPL_PAGE);
		}
		
		else{
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);
		}
	}
	return mav;
	}
	
	//24.08.2019
	
	@RequestMapping(value = "/cashmemo80mm",
			method = RequestMethod.GET)
	public ModelAndView cashmemo80mm(	Model model,
								HttpSession session,
								@RequestParam(	required = false,
												defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl) {
	//logger.debug("In cashmemo......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		//System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
	List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			//System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		//logger.debug("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		//System.out.println("ss="+commonResultSetMapper.getNoteLineOne());	
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
		/*for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);*/
		mav.addObject("saleId", saleId);
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
	}
	StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	int isExclusive =  store.getIsExclusive();
	//System.out.println("+++++++++++++++++++++++++IS EXCLUSIVE+++++++++++++++++++++++++::"+isExclusive);	
	if(isExclusive==0)
	{ 
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_PAGE);
	}
	else
	{
		/*if(userInfo.getCompanyId() == 413 || userInfo.getCompanyId() == 414 || userInfo.getCompanyId() == 1){*/
		if(userInfo.getCompanyId() == 413 || userInfo.getCompanyId() == 414){
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE_NEW);
		}else{
			//mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);
			mav.setViewName(ForwardConstants.VIEW_PRINTMEMO80mm_NEW_PAGE);
		}
	}
	return mav;
	}
	
	/*@RequestMapping(value = "/cashmemo80mm",method = RequestMethod.GET)
	public ModelAndView cashMemo80mm(Model model,HttpSession session,@RequestParam(	required = false,defaultValue = "0") String saleId,@RequestParam(required = false) String reprint,@RequestParam(required = false) String backUrl){
	logger.info("In cashmemo80mm......{},{}", saleId,backUrl);
	if (!saleId.equals("0")) {
		saleId = (String.valueOf(saleId).split("-")[2]);
		System.out.println("new saleId=" + saleId);
	}
	int manuId=0;
	ModelAndView mav = new ModelAndView();
	SaleHeaderDTO saleHeaderDTO = new SaleHeaderDTO();
	List<SaleDetailsDTO> saleDetailsDTOs = new ArrayList<SaleDetailsDTO>();
	List<TaxDetailsSaleBillDTO> taxDetailsDTOs = new ArrayList<TaxDetailsSaleBillDTO>();
	String printCountRes = "";
	int isRePrint=0;
    int printCount=0;
	//ManufacturerMaster manufacturerDetails = new ManufacturerMaster();
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
		String flag="";
		if(reprint.equals("Y"))
		{
			flag = "r";
			isRePrint=1;
			printCountRes = reprintCashMemoService.getPrintCountRes(Integer.valueOf(saleId),flag);
			System.out.println("print count status: "+printCountRes);
			printCount=Integer.valueOf(printCountRes);
		}else{}

		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());


		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(Integer.valueOf(saleId));
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setIsRePrint(isRePrint);
		commonResultSetMapper.setPrintCount(printCount);
		commonResultSetMapper.setNoteLineOne(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE);
		commonResultSetMapper.setNoteLineTwo(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO);
		logger.info("In print commonResultSetMapper......{},", commonResultSetMapper.toString());
		System.out.println("ss="+commonResultSetMapper.getNoteLineOne());
		//commonResultSetMapper.setLang(lang);
		saleHeaderDTO =gson.fromJson( cashMemoService.getSalesHeaderForBill(commonResultSetMapper), new TypeToken<SaleHeaderDTO>() {}.getType());
		saleDetailsDTOs = gson.fromJson(cashMemoService.getSaleDetailsForBill(commonResultSetMapper), new TypeToken<List<SaleDetailsDTO>>() {}.getType());
		taxDetailsDTOs = gson.fromJson(cashMemoService.getTaxDetailsForBill(commonResultSetMapper), new TypeToken<List<TaxDetailsSaleBillDTO>>() {}.getType());
		System.err.println("_________saleHeaderDTO__________"+saleHeaderDTO.toString());
		System.err.println("_________saleDetailsDTOs________"+saleDetailsDTOs.toString());
		for(SaleDetailsDTO saledetails : saleDetailsDTOs)
		{
			manuId=saledetails.getManufacturerId();
		}
		manufacturerDetails = manufacturerService.getManufacturerbyId(manuId,lang);
		
		mav.addObject("saleId", saleId);
		mav.addObject("backUrl",backUrl);
		mav.addObject("reprint",reprint);
		mav.addObject("printCountRes",printCountRes);
		mav.addObject("saleHeaderDTO", saleHeaderDTO);
		mav.addObject("saleDetailsDTOs", saleDetailsDTOs);
		mav.addObject("taxDetailsDTOs",taxDetailsDTOs);
		mav.addObject("isCur",store.getCurrencyId());
		System.out.println(store.getCurrencyId());

	}
	StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
	int isExclusive =  store.getIsExclusive();
	//mav.setViewName(ForwardConstants.VIEW_PRINTMEMO80mm_PAGE);
	mav.setViewName(ForwardConstants.VIEW_PRINTMEMO80mm_NEW_PAGE);
	if(isExclusive==0)
	{
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_PAGE);
	}
	else
	{
		mav.setViewName(ForwardConstants.VIEW_PRINTMEMO_NONCOMPOSITE_PAGE);
	}
	return mav;
	}*/

		
	
}
