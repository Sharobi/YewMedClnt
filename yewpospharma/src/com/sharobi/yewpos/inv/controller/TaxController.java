/**
 * 
 */
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.inv.model.TaxDTO;
import com.sharobi.yewpos.inv.model.TaxMaster;
import com.sharobi.yewpos.inv.service.TaxService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;


/**
 * @author habib
 *
 */
@Controller
@RequestMapping("/tax")
public class TaxController {
	private final static Logger logger=LogManager.getLogger(TaxController.class);
	private final static RoleService roleService=new RoleService();
	private final static TaxService taxService=new TaxService();
	private final static Gson gson = new Gson();
	
	
	@RequestMapping(value="/loadtax",method=RequestMethod.GET)
	public ModelAndView loadTax(Model model,HttpSession session)
	{
		//logger.debug("In loadtax......");
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
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setTaxId(0);
		commonResultSetMapper.setIsGroup(0);
		
		List<TaxDTO> allTaxes=taxService.getAllTaxes(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.TAX_SETUP_201L, lang);
		
		///System.out.println("ss"+menuByUserDTO);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.TAX);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allTaxes",allTaxes);
		mav.addObject("menuByUserDTO",menuByUserDTO);		
		mav.setViewName(ForwardConstants.VIEW_TAX_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getSingleTaxs",
			method = RequestMethod.POST)
		public void getSingleTaxes(	@RequestBody String commonResultSetMapperStr,
											HttpSession session,
											HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setTaxId(0);
		//commonResultSetMapper.setIsGroup(1); set at jQuery
		//logger.debug("In getSingleTaxes......{}", commonResultSetMapper.toString());
		String res = taxService.getSingleTaxs(commonResultSetMapper);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(res);
		out.flush();																	
	}
	
	@RequestMapping(value = "/gettaxlistforautocomplete",
			method = RequestMethod.GET)
		public void getTaxListForAutocomplete(	@RequestParam String tagName,
											HttpSession session,
											HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			//return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setTaxName(tagName.trim());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		//commonResultSetMapper.setIsGroup(1); set at jQuery
		//logger.debug("In gettaxlistforautocomplete......{}", commonResultSetMapper.toString());
		String res = taxService.getTaxListAutocomplete(commonResultSetMapper);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print(res);
		out.flush();																	
	}
	
	@RequestMapping(value="/addtax",method=RequestMethod.POST)
	public void addtax(@RequestBody String TaxMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In addtax......{}",TaxMasterObj.toString());
		//ModelAndView mav = new ModelAndView();
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
			TaxMaster taxMasterObject = gson.fromJson(TaxMasterObj, new TypeToken<TaxMaster>() {}.getType());
			taxMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			taxMasterObject.setCompanyId(userInfo.getCompanyId());
			taxMasterObject.setCreatedBy(userInfo.getId());
			String res=taxService.addtax(taxMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/updatetax",method=RequestMethod.POST)
	public void updatetax(@RequestBody String TaxMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In updatetax......{}",TaxMasterObj.toString());
		//ModelAndView mav = new ModelAndView();
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
			TaxMaster taxMasterObject = gson.fromJson(TaxMasterObj, new TypeToken<TaxMaster>() {}.getType());
			taxMasterObject.setCreatedDate(DateUtil.getCurrentDate());
			taxMasterObject.setCompanyId(userInfo.getCompanyId());
			taxMasterObject.setCreatedBy(userInfo.getId());
			String res=taxService.updatetax(taxMasterObject);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/getTaxById/{id}",method=RequestMethod.GET)
	public void getTaxById(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getTaxById......{}",id);
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setTaxId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			TaxDTO taxDTOObject = taxService.getTaxById(commonResultSetMapper);
			//System.out.println("res:"+taxDTOObject.toString());
			out.print(new Gson().toJson(taxDTOObject));
			out.flush();
		}
	}
	
	@RequestMapping(value="/getTaxDetbyId",method=RequestMethod.POST)
	public void getTaxDetbyId(@RequestBody String commonResultSetMapperStr,HttpSession session,HttpServletResponse response) throws IOException
	{
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			//PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			/*TaxDTO taxDTOObject = taxService.getTaxDetbyId(commonResultSetMapper);
			System.out.println("res:"+taxDTOObject.toString());
			out.print(new Gson().toJson(taxDTOObject));*/
			
			String res = taxService.getTaxDetbyId(commonResultSetMapper);
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(res);
			
			out.flush();
		}
	}
	
	@RequestMapping(value="/deleteTax",method=RequestMethod.POST)
	public void deleteTax(@RequestBody String commonResultSetMapperStr,HttpSession session,HttpServletResponse response) throws IOException
	{
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultSetMapperStr, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			response.setContentType("text/plain");
			String res = taxService.deleteTax(commonResultSetMapper);
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(res);
			
			out.flush();
		}
	}
}
