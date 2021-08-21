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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.acc.model.AccountGroupDTO;
import com.sharobi.yewpos.acc.model.AccountTypeDTO;
import com.sharobi.yewpos.acc.service.AccGroupService;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * 
 *  @author Arunima, SK A SIDDIK
 *
 *       update  Feb 15, 2018
 */

@Controller
@RequestMapping("/accntgrp")
public class AccGroupController {
	private final static Logger logger = LogManager.getLogger(AccGroupController.class);
	AccGroupService accGroupService = new AccGroupService();
	RoleService roleService = new RoleService();

	@RequestMapping(value = "/loadaccntgrp", method = RequestMethod.GET)
	public ModelAndView loadAccntGrp(Model model, HttpSession session) {
		///logger.debug("In accgroup setup......");
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
		List<AccountTypeDTO> allAccTypes = accGroupService.getAllAccType(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.LEDGER_SETUP_401A, lang);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.ACCOUNTS);
		menuselect.setSubMenu(Constant.ACCOUNT_SETUP);
		menuselect.setChildsubMenu(Constant.LEDGER);

		mav.addObject("menuselect", menuselect);
		mav.addObject("allAccTypes", allAccTypes);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_LEDGER_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchaccntgrp", method = RequestMethod.POST)
	public ModelAndView searchAccntGrp(
			@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper, HttpSession session,
			HttpServletResponse response) {
		//logger.debug("In searchaccntgrp......{},{},{}", commonResultSetMapper.toString());
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
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<AccountGroupDTO> allAccGroups = accGroupService.getAllAccGroup(commonResultSetMapper);
		List<AccountTypeDTO> allAccTypes = accGroupService.getAllAccType(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID),
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.LEDGER_SETUP_401A, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.ACCOUNTS);
		menuselect.setSubMenu(Constant.ACCOUNT_SETUP);
		menuselect.setChildsubMenu(Constant.LEDGER);
		mav.addObject("menuselect", menuselect);
		mav.addObject("allAccGroups", allAccGroups);
		mav.addObject("allAccTypes", allAccTypes);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("groupName", commonResultSetMapper.getGroupName());
		mav.setViewName(ForwardConstants.VIEW_LEDGER_PAGE);
		return mav;
	}

	@RequestMapping("/addaccgrp")
	public void addAccGroup(@RequestBody String AccGroupMasterObj, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In addAccGroup......{},{},{}", AccGroupMasterObj);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			AccountGroupDTO accGrpMaster = gson.fromJson(AccGroupMasterObj, new TypeToken<AccountGroupDTO>() {
			}.getType());
			accGrpMaster.setCompanyId(userInfo.getCompanyId());
			accGrpMaster.setCreatedBy(userInfo.getId());
			accGrpMaster.setStoreId(userInfo.getStoreId());
			String res = accGroupService.addAccGroup(accGrpMaster);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/editaccgrp", method = RequestMethod.POST)
	public void editBrand(@RequestBody String AccGroupMasterObj, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In editaccgrp......{}", AccGroupMasterObj);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			AccountGroupDTO accGrpMaster = gson.fromJson(AccGroupMasterObj, new TypeToken<AccountGroupDTO>() {
			}.getType());
			accGrpMaster.setCompanyId(userInfo.getCompanyId());
			accGrpMaster.setUpdatedBy(userInfo.getId());
			accGrpMaster.setStoreId(userInfo.getStoreId());
			String res = accGroupService.editAccGroup(accGrpMaster);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deleteaccgroup/{id}", method = RequestMethod.GET)
	public void deleteAccGroup(@PathVariable("id") int id, HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.debug("In deleteCat......{}", id);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setId(id);
			String res = accGroupService.deleteAccGroup(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

}