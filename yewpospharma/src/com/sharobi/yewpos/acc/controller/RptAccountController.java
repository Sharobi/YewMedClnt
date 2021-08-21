/**
 * 
 */
package com.sharobi.yewpos.acc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sharobi.yewpos.acc.model.AccountDTO;
import com.sharobi.yewpos.acc.model.AccountGroupDTO;
import com.sharobi.yewpos.acc.service.AccGroupService;
import com.sharobi.yewpos.acc.service.Chartofaccountservice;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author SK A SIDDIK * Mar 2, 2018
 */

@Controller
@RequestMapping("/accrpt")
public class RptAccountController {
	private final static Logger logger = LogManager.getLogger(EntryjournalController.class);
	RoleService roleService = new RoleService();
	Gson gson = new Gson();
	AccGroupService accountgroupservice = new AccGroupService();

	Chartofaccountservice chartofaccountservice = new Chartofaccountservice();

	/*
	 * for ledger display 
	 */
	@RequestMapping(value = "/ledger_rep", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadrptledger(HttpSession session) {
		//logger.debug("In  loadrptledger  display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_lEDGER_VIEW_805A, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_LEDGER_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_LEDGER_VIEW);
		return mav;
	}
	
	/*
	 * for daily_collection
	 */
	@RequestMapping(value = "/daily_collection", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView daily_collection(HttpSession session) {
		//logger.debug("In  daily_collection  display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_DAILY_COLLECTION_805D, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_DAILY_COLLECTION_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_DAILY_COLLECTION_VIEW);
		return mav;
	}

	/*
	 * for daily_payment 
	 */
	@RequestMapping(value = "/daily_payment", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView daily_payment(HttpSession session) {
		//logger.debug("In  daily_payment  display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_DAILY_PAYMENT_805E, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_DAILY_PAYMENT_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_DAILY_PAYMENT_VIEW);
		return mav;
	}
	
	
	/*
	 * for trial balance 
	 */
	@RequestMapping(value = "/trial_rep", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadrpttrial(HttpSession session) {
		//logger.debug("In  trial balance   display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_TRIAL_BALANCE_VIEW_805B, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_TRIAL_BALANCE_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_TRIAL_VIEW);
		return mav;
	}
	
	
	
	/*
	 * for balshet_rep
	 */
	@RequestMapping(value = "/balshet_rep", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView balshet_rep(HttpSession session) {
		//logger.debug("In  balance sheet   display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_BALANCE_SHEET_VIEW_805C, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_BALANCE_SHEET_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_BALANCE_SHEET_VIEW);
		return mav;
	}

	
	
	/*
	 * for account_blnc
	 */
	@RequestMapping(value = "/account_blnc", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView account_blnc(HttpSession session) {
		//logger.debug("In  account_blnc   display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_ACCOUNT_BALANCE_805F, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_ACCOUNT_BALANCE_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_ACCOUNT_BALANCE_VIEW);
		return mav;
	}
	
	
	
	
	/*
	 * for profit and loss 
	 */
	@RequestMapping(value = "/profit_loss", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView profit_and_loss(HttpSession session) {
		//logger.debug("In  profit_loss   display report.....");
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RPT_PROFIT_AND_LOSS_805G, lang);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.REPORTS);
		menuselect.setSubMenu(Constant.REP_LEDGER);
		menuselect.setChildsubMenu(Constant.REP_ACCOUNT_PROFIT_AND_LOSS_REPORT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("accountgrouplist", accountgrouplist);

		mav.setViewName(ForwardConstants.RPT_ACCOUNT_PROFITANDLOSS_VIEW);
		return mav;
	}
	
	
	
	

	/*
	 * for search ledger
	 */

	@RequestMapping(value = "/searchledgerinreport", method = RequestMethod.POST)
	public ResponseObj searchledger(@RequestParam String tagName, @RequestParam int groupCode, HttpSession session,
			HttpServletResponse response) throws IOException {
		//logger.info("In searchledgerinreport---------", tagName, groupCode);

		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setName(tagName.trim());
			commonResultSetMapper.setId(groupCode);
				commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
				commonResultSetMapper.setStoreId(userInfo.getStoreId());
				commonResultSetMapper.setLang(lang);
				//logger.info("searchledgerinreport input string :" + commonResultSetMapper.toString());
	
				List<AccountDTO> ledgerlist = this.chartofaccountservice.searchledgerinreport(commonResultSetMapper);
				//logger.info("searchledgerinreport List :" + ledgerlist);
	
				PrintWriter out1 = response.getWriter();
				response.setContentType("application/json");
	
				out1.print(gson.toJson(ledgerlist));
			}
			return null;
	}

}
