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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.ContentMaster;
import com.sharobi.yewpos.inv.model.ManufacturerMaster;
import com.sharobi.yewpos.inv.service.ManufacturerService;
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
@RequestMapping("/manufacturer")
public class ManufacturerController {
	private final static Logger logger=LogManager.getLogger(ManufacturerController.class);
	private final static ManufacturerService manufacturerService=new ManufacturerService();
	private final static RoleService roleService=new RoleService();
	
	@RequestMapping(value="/loadmanufacturer",method=RequestMethod.GET)
	public ModelAndView loadManufacturer(Model model,HttpSession session)
	{
		//logger.debug("In manufacturersetup......");
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
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.MANUFACTURER_SETUP_201E, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.MANUFACTURER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_MANUFACTURER_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/searchManufacturer",method=RequestMethod.POST)
	public ModelAndView searchManufacturer(@ModelAttribute("manufacturerMaster") ManufacturerMaster manufacturerMaster ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In searchManufacturer......{},{},{}",manufacturerMaster.toString());
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
		List<ManufacturerMaster> allManufacturers=manufacturerService.searchManufacturer(manufacturerMaster);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CONTENT_SETUP_201K, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.MANUFACTURER);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allManufacturers",allManufacturers);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_MANUFACTURER_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/addManufacturer",method=RequestMethod.POST)
	public void addManufacturer(@RequestBody String ManufacturerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In addManufacturer......{},{},{}",ManufacturerMasterObj.toString());
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
			ManufacturerMaster manufacturerMasterObject = gson.fromJson(ManufacturerMasterObj, new TypeToken<ManufacturerMaster>() {}.getType());
			manufacturerMasterObject.setCompanyId(userInfo.getCompanyId());
			manufacturerMasterObject.setCreatedBy(userInfo.getId());
			manufacturerMasterObject.setLang(lang);
			String res=manufacturerService.addManufacturer(manufacturerMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/editManufacturer",method=RequestMethod.POST)
	public void editManufacturer(@RequestBody String ManufacturerMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In editManufacturer......{}",ManufacturerMasterObj.toString());
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
			ManufacturerMaster manufacturerMasterObject = gson.fromJson(ManufacturerMasterObj, new TypeToken<ManufacturerMaster>() {}.getType());
			manufacturerMasterObject.setCompanyId(userInfo.getCompanyId());
			manufacturerMasterObject.setUpdatedBy(userInfo.getId());
			manufacturerMasterObject.setLang(lang);
			String res=manufacturerService.editManufacturer(manufacturerMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/deleteManufacturer/{id}",method=RequestMethod.GET)
	public void deleteManufacturer(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In deleteManufacturer......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = manufacturerService.deleteManufacturer(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/autocompleteitemmanufacturer", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemManufacturerAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.debug("In getItemManufacturerAutocompleteList......{}", tagName);
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
		ManufacturerMaster manufacturerMaster=new ManufacturerMaster();
		manufacturerMaster.setName(tagName.trim());
		manufacturerMaster.setCompanyId(userInfo.getCompanyId());
		manufacturerMaster.setStoreId(userInfo.getStoreId());
		List<CommonResultSetMapper> commonResultSetMappers=manufacturerService.searchManufacturerAutoComplete(manufacturerMaster);
		//System.out.println("allBrands List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
}
