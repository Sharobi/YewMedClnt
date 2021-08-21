package com.sharobi.yewpos.pos.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharobi.yewpos.inv.controller.InvReportController;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.TypeMaster;
import com.sharobi.yewpos.pos.service.CashMemoService;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

@Controller
@RequestMapping("/posreport")
public class PosReportController {
	private final static Logger logger=LogManager.getLogger(InvReportController.class);
	private final static InvSetupService invSetUpService=new InvSetupService();
	private final static CashMemoService cashMemoService = new CashMemoService();
	
	@RequestMapping(value="/salesummary",method=RequestMethod.GET)
	public ModelAndView salesummary(Model model,HttpSession session)
	{
		//logger.debug("In salesummary......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALESUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/salereg",method=RequestMethod.GET)
	public ModelAndView saleReg(Model model,HttpSession session)
	{
		//logger.debug("In saleReg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		List<GroupMaster> allGroups=invSetUpService.getAllGroup(userInfo.getCompanyId(),lang);
		List<ScheduleMaster> allSchedules=invSetUpService.getAllSchedule(userInfo.getCompanyId(),lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_REG);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allGroups",allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.setViewName(ForwardConstants.VIEW_SALEREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}

	@RequestMapping(value="/itemwisesales",method=RequestMethod.GET)
	public ModelAndView saleItem(Model model,HttpSession session)
	{
		//logger.debug("In saleItem......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_ITEM);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALEITEM_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	/* ========================= Sales Return Reports start ======================= */
	
	@RequestMapping(value="/salereturnsummary",method=RequestMethod.GET)
	public ModelAndView salereturnsummary(Model model,HttpSession session)
	{
		//logger.debug("In salereturnsummary......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_RETURN_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALERETURNSUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/salereturnreg",method=RequestMethod.GET)
	public ModelAndView salereturnreg(Model model,HttpSession session)
	{
		//logger.debug("In salereturnreg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_RETURN_REG);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALERETURNREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/itemwisesalereturn",method=RequestMethod.GET)
	public ModelAndView itemwisesalereturn(Model model,HttpSession session)
	{
		//logger.debug("In itemwisesalereturn......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_RETURN_ITEM);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_SALERETURNITEMWISE_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/nonmoving",method=RequestMethod.GET)
	public ModelAndView nonMovingItem(Model model,HttpSession session)
	{
		//logger.debug("In nonMovingItem......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_NON_MOVING);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_NONMOVINGITEM_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/customerledger",method=RequestMethod.GET)
	public ModelAndView customerLedger(Model model,HttpSession session)
	{
		//logger.debug("In customerledger......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_SALES_CUSTOMER_LEDGER);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_CUSTOMER_LEDGER_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/esiongcreg",method=RequestMethod.GET)
	public ModelAndView esiOngcReg(Model model,HttpSession session)
	{
		//logger.debug("In esiOngcReg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(0);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<TypeMaster> typeMasters=cashMemoService.getTypes(commonResultSetMapper);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_ESIONGC_SALES_REG);
		mav.addObject("menuselect",menuselect);
		mav.addObject("typeMasters", typeMasters);
		mav.setViewName(ForwardConstants.VIEW_ESIONGC_SALEREG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/scheduleh1reg",method=RequestMethod.GET)
	public ModelAndView Scheduleh1Reg(Model model,HttpSession session)
	{
		//logger.debug("In scheduleh1reg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(0);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<TypeMaster> typeMasters=cashMemoService.getTypes(commonResultSetMapper);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_H1_REGISTER);
		mav.addObject("menuselect",menuselect);
//		mav.addObject("typeMasters", typeMasters);
		mav.setViewName(ForwardConstants.VIEW_SHEDULE_H1_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/tbreg",method=RequestMethod.GET)
	public ModelAndView TbReg(Model model,HttpSession session)
	{
		//logger.debug("In tbreg......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setSaleId(0);
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_TB_REGISTER);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_TB_REG_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}




	@RequestMapping(value="/doctorwisesales",method=RequestMethod.GET)
	public ModelAndView doctorWiseSales(Model model,HttpSession session)
	{
		//logger.debug("In saleItem......");
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
		//MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		//List<DistributorMaster> allVendors = vendorService.getAllVendorPost(userInfo, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_SALES);
		menuselect.setChildsubMenu(Constant.REP_DOCTOR_WISE_SALE);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_DOCTOR_WISE_SALE_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
}
