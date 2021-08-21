package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import com.sharobi.yewpos.inv.model.BrandMaster;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.service.BrandService;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author Manodip
 *
 */

@Controller
@RequestMapping("/brand")
public class BrandController {
	private final static Logger logger=LogManager.getLogger(BrandController.class);
	  BrandService brandService=new BrandService();
	  RoleService roleService=new RoleService();

	@RequestMapping(value="/loadbrand",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView loadBrand(@ModelAttribute("brandMaster") BrandMaster brandMaster ,Model model,HttpSession session)
	{
		//logger.debug("In brandsetup......"+brandMaster.toString());
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
		
		if (brandMaster.getName()!=null) {
			//System.out.println("brand_------------------");
			List<BrandMaster> allBrands=brandService.searchBrand(brandMaster);
			mav.addObject("allBrands",allBrands);
			
		}
	
		//List<BrandMaster> allBrands=brandService.getAllBrand(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.BRAND_SETUP_201A, lang);
	
		
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.BRAND);
		mav.addObject("menuselect",menuselect);
		//mav.addObject("allBrands",allBrands);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_BRAND_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/addbrand",method=RequestMethod.POST)
	public void addBrand(@RequestBody String brandMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In addbrand......{},{},{}",brandMasterObj.toString());
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
			BrandMaster brandMaster= gson.fromJson(brandMasterObj, new TypeToken<BrandMaster>() {}.getType());
			brandMaster.setCompanyId(userInfo.getCompanyId());
			brandMaster.setCreatedBy(userInfo.getId());
			brandMaster.setLang(lang);
			String res=brandService.addBrand(brandMaster);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/getBrandbyId/{id}",method=RequestMethod.GET)
	public void getBrandById(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getBrandbyId......{}",id);
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
			BrandMaster brandMaster = brandService.getBrandById(id, lang);
			//System.out.println("res:"+brandMaster.toString());
			out.print(brandMaster.toString());
			out.flush();
		}
	}
	
	@RequestMapping(value="/editbrand",method=RequestMethod.POST)
	public void editBrand(@RequestBody String brandMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In editbrand......{}",brandMasterObj.toString());
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
			BrandMaster brandMaster = gson.fromJson(brandMasterObj, new TypeToken<BrandMaster>() {}.getType());
			brandMaster.setCompanyId(userInfo.getCompanyId());
			brandMaster.setLang(lang);
			brandMaster.setUpdatedBy(userInfo.getId());
			String res=brandService.editBrand(brandMaster);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/deleteBrand/{id}",method=RequestMethod.GET)
	public void deleteBrand(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In deleteBrand......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = brandService.deleteBrand(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/searchbrand",method=RequestMethod.POST)
	public ModelAndView searchBrand(@ModelAttribute("brandMaster") BrandMaster brandMaster ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In searchbrand......{},{},{}",brandMaster.toString());
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
		List<BrandMaster> allBrands=brandService.searchBrand(brandMaster);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.BRAND_SETUP_201A, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.BRAND);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allBrands",allBrands);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_BRAND_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/autocompleteitembrand", method = RequestMethod.GET)
	public List<BrandMaster> getItemBrandAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.debug("In getItemBrandAutocompleteList......{}", tagName);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
		//	mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return null;
		}
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		BrandMaster brandMaster=new BrandMaster();
		brandMaster.setName(tagName.trim());
		brandMaster.setCompanyId(userInfo.getCompanyId());
		brandMaster.setStoreId(userInfo.getStoreId());
		List<CommonResultSetMapper> commonResultSetMappers=brandService.searchBrandAutoComplete(brandMaster);
		//System.out.println("allBrands List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
	
}
