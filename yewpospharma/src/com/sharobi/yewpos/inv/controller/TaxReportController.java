/**
 * 
 */
package com.sharobi.yewpos.inv.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.proc.model.DistributorMaster;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.storemgnt.model.FinYrMaster;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;

/**
 * @author Manodip Jana
 *
 * 
 */
@Controller
@RequestMapping("/taxreport")
public class TaxReportController {
	private final static Logger logger=LogManager.getLogger(TaxReportController.class);
	private final static InvSetupService invSetUpService=new InvSetupService();
	private final static StoreMgntService storeMgntService = new StoreMgntService();
	
	@RequestMapping(value="/taxsummary",method=RequestMethod.GET)
	public ModelAndView taxSunmmary(Model model,HttpSession session)
	{
		//logger.debug("In taxsummary......");
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
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_TAX_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_TAXSUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/taxslabwisesummary",method=RequestMethod.GET)
	public ModelAndView taxSlabSunmmary(Model model,HttpSession session)
	{
		//logger.debug("In taxSlabSunmmary......");
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
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_TAX_SLAB_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_TAX_SLABSUMMARY_PAGE);
		//mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/integrity",method=RequestMethod.GET)
	public ModelAndView integrity(Model model,HttpSession session)
	{
		//logger.debug("In integrity......");
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
		List<ScheduleMaster> allSchedules=invSetUpService.getAllSchedule(userInfo.getCompanyId(),lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_TAX_INTEGRITY);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allSchedules", allSchedules);
		mav.setViewName(ForwardConstants.VIEW_TAXINTEGRITY_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/btwocs",method=RequestMethod.GET)
	public ModelAndView bTwoCS(Model model,HttpSession session)
	{
		//logger.debug("In bTwoCS......");
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
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_TAX_B2CS_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_TAXB2CS_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/hsn",method=RequestMethod.GET)
	public ModelAndView Hsn(Model model,HttpSession session)
	{
		//logger.debug("In hsn......");
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
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_TAX_HSN_SUMMARY);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_TAXHSN_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/gstr3b",method=RequestMethod.GET)
	public ModelAndView Gstr3b(Model model,HttpSession session)
	{
		//logger.debug("In hsn......");
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
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_GSTR3B);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_GSTR3B_PAGE);
		return mav;
	}
	@RequestMapping(value="/gstr9a",method=RequestMethod.GET)
	public ModelAndView Gstr9a(Model model,HttpSession session)
	{
		//logger.info("In gstr9......");
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
		/*StoreMaster store = (StoreMaster)session.getAttribute(Constant.SES_LOGGED_IN_STORE);
		mav.addObject("isExclusive",store.getIsExclusive());*/
		List<FinYrMaster> allFinYears=storeMgntService.getAllFinancilaYears(userInfo.getCompanyId(),userInfo.getStoreId());
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_TAX);
		menuselect.setChildsubMenu(Constant.REP_GSTR9A);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allFinYear",allFinYears);
		mav.setViewName(ForwardConstants.VIEW_GSTR9A_PAGE);
		return mav;
	}
}
