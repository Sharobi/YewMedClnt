package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.sharobi.yewpos.inv.model.ItemSearchByContentDTO;
import com.sharobi.yewpos.inv.model.ManufacturerMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.service.ContentService;
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
@RequestMapping("/content")
public class ContentController {
	private final static Logger logger=LogManager.getLogger(ContentController.class);
      ContentService contentService=new ContentService();
	  RoleService roleService=new RoleService();
	
	@RequestMapping(value="/loadcontent",method=RequestMethod.GET)
	public ModelAndView loadContent(Model model,HttpSession session)
	{
		//logger.debug("In contentsetup......");
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
		//List<ContentMaster> allContents=contentService.getAllContent(userInfo.getCompanyId(),lang);
		//System.out.println("res:"+allContents.toString());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CONTENT_SETUP_201K, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CONTENT);
		mav.addObject("menuselect",menuselect);
		//mav.addObject("allContents",allContents);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_CONTENT_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/addContent",method=RequestMethod.POST)
	public void addContent(@RequestBody String ContentMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In addContent......{},{},{}",ContentMasterObj.toString());
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
			ContentMaster contentMasterObject = gson.fromJson(ContentMasterObj, new TypeToken<ContentMaster>() {}.getType());
			contentMasterObject.setCompanyId(userInfo.getCompanyId());
			contentMasterObject.setCreatedBy(userInfo.getId());
			contentMasterObject.setLang(lang);
			String res=contentService.addContent(contentMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/editContent",method=RequestMethod.POST)
	public void editContent(@RequestBody String ContentMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In editContent......{}",ContentMasterObj.toString());
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
			ContentMaster contentMasterObject = gson.fromJson(ContentMasterObj, new TypeToken<ContentMaster>() {}.getType());
			contentMasterObject.setCompanyId(userInfo.getCompanyId());
			contentMasterObject.setUpdatedBy(userInfo.getId());
			contentMasterObject.setLang(lang);
			String res=contentService.editContent(contentMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/deleteContent/{id}",method=RequestMethod.GET)
	public void deleteContent(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In deleteContent......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String res = contentService.deleteContent(id);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/searchContent",method=RequestMethod.POST)
	public ModelAndView searchBrand(@ModelAttribute("contentMaster") ContentMaster contentMaster ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In searchContent......{},{},{}",contentMaster.toString());
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
		List<ContentMaster> allContents=contentService.searchContent(contentMaster);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.CONTENT_SETUP_201K, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.CONTENT);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allContents",allContents);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_CONTENT_PAGE);
		return mav;
	}
	
	@RequestMapping(value = "/autocompleteitemcontent", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemContentAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.debug("In getItemContentAutocompleteList......{}", tagName);
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
		ContentMaster contentMaster=new ContentMaster();
		contentMaster.setName(tagName.trim());
		contentMaster.setCompanyId(userInfo.getCompanyId());
		contentMaster.setStoreId(userInfo.getStoreId());	 
		List<CommonResultSetMapper> commonResultSetMappers=contentService.searchContentAutoComplete(contentMaster);
		//System.out.println("allContent List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
	
}
