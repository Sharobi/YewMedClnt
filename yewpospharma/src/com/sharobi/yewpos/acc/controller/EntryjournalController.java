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
import com.sharobi.yewpos.acc.model.AccountGroupDTO;
import com.sharobi.yewpos.acc.model.AccountTypeDTO;
import com.sharobi.yewpos.acc.model.Journal;
import com.sharobi.yewpos.acc.model.JournalList;
import com.sharobi.yewpos.acc.model.ParaJournalTypeMaster;
import com.sharobi.yewpos.acc.service.AccGroupService;
import com.sharobi.yewpos.acc.service.AccJournalService;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.CountryMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author SK A SIDDIK
 *
 *         Feb 20, 2018
 */

@Controller
@RequestMapping("/accntenty")
public class EntryjournalController {

	private final static Logger logger = LogManager.getLogger(EntryjournalController.class);
	RoleService roleService = new RoleService();
	InvSetupService invsetupService = new InvSetupService();
	AccJournalService accJournalService = new AccJournalService();
	Gson gson = new Gson();
	AccGroupService accountgroupservice = new AccGroupService();
	  StoreMgntService storeService=new StoreMgntService();
	@RequestMapping(value = "/loadaccntjrl", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadAccntGrp(@ModelAttribute JournalList jl, HttpSession session) {
		//logger.debug("In loadaccntjrl journal entry......" + jl.toString());
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
		MenuByUserDTO menuByUserDTO = this.roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.ACCOUNTS_JOURNAL_402A, lang);

		commonResultSetMapper.setQs("0");

		List<ParaJournalTypeMaster> entrytypelist = this.accJournalService.getEntrytype(commonResultSetMapper);

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

		commonResultSetMapper.setQs(jl.getQs());
		List<Journal> journallist = this.accJournalService.getAlljournallist(commonResultSetMapper);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.ACCOUNTS);
		menuselect.setSubMenu(Constant.ACCOUNT_ENTRY);
		menuselect.setChildsubMenu(Constant.ACCOUNT_JOURNAL);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission
		mav.addObject("entypelist", entrytypelist);

		mav.addObject("cur", userInfo.getCurrency_code());

		if (jl.getQs() != null) {
			mav.addObject("qs", jl.getQs());
			mav.addObject("journallist", journallist);
		} else {
			mav.addObject("journallist", null);
		}
		String countrylist = invsetupService.getAllCountryList(commonResultSetMapper);
		List<CountryMaster> countryMasters = gson.fromJson(countrylist, List.class);
		List<AccountGroupDTO> accountgrouplist = accountgroupservice.getAllAccGroup(commonResultSetMapper);
		List<AccountTypeDTO> allAccTypes = accountgroupservice.getAllAccType(commonResultSetMapper);

		mav.addObject("contrylist", countryMasters);
		mav.addObject("accountgrouplist", accountgrouplist);
		mav.addObject("allAccTypes", allAccTypes);
		mav.setViewName(ForwardConstants.VIEW_ACCONTJOURNAL_PAGE);
		return mav;
	}

	/*
	 * for search ledger
	 */

	@RequestMapping(value = "/searchledger", method = RequestMethod.POST)
	public ResponseObj searchledger(@RequestParam String tagName, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In searchledger---------", tagName);

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

			List<AccountDTO> ledgerlist = this.accJournalService.searchledger(commonResultSetMapper);
			//logger.info("searchaccount List :" + ledgerlist);

			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(ledgerlist));
		}
		return null;
	}
	/*
	 * for add journal
	 */

	@RequestMapping(value = "/addjournal", method = RequestMethod.POST)
	public void addjournal(@RequestBody String journal, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In addjournal......{}" + journal);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			JournalList journalList = gson.fromJson(journal, new TypeToken<JournalList>() {
			}.getType());
			journalList.setCompanyId(userInfo.getCompanyId());
			journalList.setStoreId(userInfo.getStoreId());
			journalList.setCreatedBy(userInfo.getId());
			journalList.setLang(lang);
			journalList.setFinyrId(userInfo.getFinyrId());

			journalList.setFinyrCode(userInfo.getFinyrCode());

			//logger.info("journalList:" + journalList);

			String res = this.accJournalService.addjournal(journalList);

			//logger.info("res:" + res);

			out.print(res);
			out.flush();
		}
	}

	/*
	 * for update journal
	 */

	@RequestMapping(value = "/updatejournal", method = RequestMethod.POST)
	public void updatejournal(@RequestBody String journal, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In updatejournal......{}" + journal);

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			JournalList journalList = gson.fromJson(journal, new TypeToken<JournalList>() {
			}.getType());
			journalList.setCompanyId(userInfo.getCompanyId());
			journalList.setStoreId(userInfo.getStoreId());
			journalList.setCreatedBy(userInfo.getId());
			journalList.setLang(lang);
			journalList.setFinyrId(userInfo.getFinyrId());

			journalList.setFinyrCode(userInfo.getFinyrCode());

			String res = this.accJournalService.updatejournal(journalList);
			//logger.info("res:" + res);

			out.print(res);
			out.flush();
		}
	}

	/*
	 * delete journal
	 */
	@RequestMapping(value = "/deletejournal/{id}", method = RequestMethod.GET)
	public void deletejournal(@PathVariable("id") int id, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In deletejournal--------=", id);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setId(id);
			String res = this.accJournalService.deleteJournal(commonResultSetMapper);

			//logger.info("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/*
	 * edit journal
	 */
	@RequestMapping(value = "/editjournal/{id}", method = RequestMethod.GET)
	public void editjourla(@PathVariable("id") int id, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In editjournal--------=", id);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setId(id);
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());

			JournalList journalList = this.accJournalService.editjournalbyid(commonResultSetMapper);

			//logger.info("editjournal List :" + journalList);
			PrintWriter out1 = response.getWriter();
			response.setContentType("application/json");

			out1.print(gson.toJson(journalList));

		}
	}




	/*
	 * print journal
	 */
	@RequestMapping(value = "/paymentcashprintjv/{id}/type/{type}", method = RequestMethod.GET)
	public ModelAndView paymentcashprintjv(@PathVariable("id") int id,@PathVariable("type") String type ,  HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In paymentcashprintjv--------=", id);

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
		commonResultSetMapper.setId(id);
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		JournalList journalList = this.accJournalService.editjournalbyid(commonResultSetMapper);
		mav.addObject("journal", journalList);
		mav.addObject(Constant.SES_LOGGED_IN_STORE,store);
		mav.addObject("cur",userInfo.getCurrency_code());
		mav.addObject("curdes", userInfo.getCurrency_desc());

		if (type.equals("PAY")) {
			mav.setViewName(ForwardConstants.VIEW_JOURNAL_PAYMENT_PRINT);
		}
		if (type.equals("REC")) {
			mav.setViewName(ForwardConstants.VIEW_JOURNAL_RECEIPT_PRINT);
		}

		return mav;
	}


}
