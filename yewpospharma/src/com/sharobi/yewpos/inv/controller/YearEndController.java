/**
 * 
 */
package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.service.YearEndService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author Arunima Roy
 *
 * Mar 23, 2018
 */
@Controller
@RequestMapping("/yearend")
public class YearEndController {
	private final static Logger logger=LogManager.getLogger(BrandController.class);
	YearEndService yearEndService=new YearEndService();
	RoleService roleService=new RoleService();
	
	@RequestMapping(value="/loadyearend",method=RequestMethod.GET)
	public ModelAndView loadYearEnd(Model model,HttpSession session)
	{
		//logger.debug("In loadyearend......");
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
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.YEAR_END_SETUP_201M, lang);
	
		
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.YEAR_END);
		mav.addObject("menuselect",menuselect);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_YEAR_END_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/stktrnsfrfryrend",method=RequestMethod.POST)
	public void stkTrnsrFrYrend(@RequestBody String commonResultMap,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In stktrnsfrfryrend......{}",commonResultMap.toString());
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
			CommonResultSetMapper commonResultMapObj = gson.fromJson(commonResultMap, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultMapObj.setCompanyId(userInfo.getCompanyId());
			commonResultMapObj.setStoreId(userInfo.getStoreId());
			commonResultMapObj.setFinYrId(userInfo.getFinyrId());
			commonResultMapObj.setItemId(0);
			commonResultMapObj.setCreatedBy(userInfo.getId());
			String res=yearEndService.stkTrnsrFrYrend(commonResultMapObj);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
}
