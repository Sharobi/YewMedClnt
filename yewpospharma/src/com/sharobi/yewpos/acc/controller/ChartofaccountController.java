/**
 * 
 */
package com.sharobi.yewpos.acc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sharobi.yewpos.acc.model.Chartofaccount;
import com.sharobi.yewpos.acc.model.JournalList;
import com.sharobi.yewpos.acc.service.Chartofaccountservice;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author SK A SIDDIK
 *
 *         Mar 14, 2018
 */

@Controller
@RequestMapping("/chatacc")
public class ChartofaccountController {

	private final static Logger logger = LogManager.getLogger(ChartofaccountController.class);
	RoleService roleService = new RoleService();
	Chartofaccountservice chartofservice = new Chartofaccountservice();
	Gson gson = new Gson();

	@RequestMapping(value = "/chartofaccnt", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loadAccntGrp(@ModelAttribute Chartofaccount cofa, HttpSession session) {
		//logger.debug("In chartofaccnt  accall......" + cofa.toString());
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
				userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.ACCOUNTS_CHARTOFACCOUNT_402B, lang);

		commonResultSetMapper.setQs("0");

		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setAccountID(0);
		commonResultSetMapper.setId(0);// as group id
		
		//System.out.println(cofa.getAsOnDate());
		
		if (cofa.getAsOnDate()!=null) {
			commonResultSetMapper.setAsOnDate(cofa.getAsOnDate());
			mav.addObject("ason_Date", cofa.getAsOnDate());
			
		}else
		{
			cofa.setAsOnDate("0");
			mav.addObject("ason_Date", 0);
			
			commonResultSetMapper.setAsOnDate( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
		
	 
		
		List<Chartofaccount> chartofaccounts = this.chartofservice.getAllChartofaccount(commonResultSetMapper);

		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.ACCOUNTS);
		menuselect.setSubMenu(Constant.ACCOUNT_ENTRY);
		menuselect.setChildsubMenu(Constant.ACCOUNT_CHARTOFACCOUNT);

		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);// for permission

		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("chartofaccounts", chartofaccounts);
		

		mav.setViewName(ForwardConstants.VIEW_CHARTOFACC_PAGE);
		return mav;
	}

}
