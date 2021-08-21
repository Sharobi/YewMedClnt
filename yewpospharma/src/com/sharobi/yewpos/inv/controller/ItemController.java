package com.sharobi.yewpos.inv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.sharobi.yewpos.inv.model.GroupMaster;
import com.sharobi.yewpos.inv.model.ItemMaster;
import com.sharobi.yewpos.inv.model.RackMaster;
import com.sharobi.yewpos.inv.model.ScheduleMaster;
import com.sharobi.yewpos.inv.model.UnitMaster;
import com.sharobi.yewpos.inv.service.InvSetupService;
import com.sharobi.yewpos.inv.service.ItemService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.sharobi.yewpos.util.RoleBasedCostants;

/**
 * @author Manodip
 * 
 */

@Controller
@RequestMapping("/item")
public class ItemController {
	private final static Logger logger = LogManager.getLogger(ItemController.class);
	private final static ItemService itemService = new ItemService();
	private final static RoleService roleService = new RoleService();
	private final static InvSetupService invSetUpService = new InvSetupService();

	@RequestMapping(value = "/loaditem",
					method = RequestMethod.GET)
	public ModelAndView loadItem(	Model model,
									HttpSession session) {
		//logger.debug("In itemsetup......");
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
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		return mav;
	}

	@RequestMapping(value = "/loaditemmst/{itemid}",
					method = RequestMethod.GET)
	public ModelAndView loadItemMaster(	@PathVariable("itemid") Integer itemid,
										Model model,
										HttpSession session) {
		//logger.debug("In itemsetup......");
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
		if (itemid != 0) {// get item details for edit
			ItemMaster itemMaster = itemService.getItemDetailsById(itemid, lang);
			mav.addObject("itemMaster", itemMaster);
		}
		List<RackMaster> allRacks = invSetUpService.getAllRack(userInfo.getCompanyId(), lang);
		List<GroupMaster> allGroups = invSetUpService.getAllGroup(userInfo.getCompanyId(), lang);
		List<ScheduleMaster> allSchedules = invSetUpService.getAllSchedule(userInfo.getCompanyId(), lang);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("allGroups", allGroups);
		mav.addObject("allSchedules", allSchedules);
		mav.addObject("allRacks", allRacks);
		mav.addObject("result", "");
		mav.addObject("add_edit_delete", "");
		mav.addObject("item_id",itemid);
		mav.setViewName(ForwardConstants.VIEW_ITEM_MASTER_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getitemdetailsmod/{itemid}",
			method = RequestMethod.GET)
	public ModelAndView loadItemMaster(	@PathVariable("itemid") Integer itemid,
									Model model,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In getitemdetails......");
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
		String itemDetails = itemService.getItemDetailsByIdForEdit(itemid, lang);
		//System.out.println("In getitemdetails in edit item modal :" + itemDetails);
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
	 
		out.print(itemDetails);
		out.flush();
		return null;
	}
	
	@RequestMapping(value = "/checkSameItemExists/{name}/{itemid}",
					method = RequestMethod.GET)
	public void checkSameItemExists(@PathVariable("name") String name,@PathVariable("itemid") Integer itemid,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In checkSameItemExists......{},{}", name,itemid);
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			String val = itemService.checkSameItemExist(name.trim(),itemid, lang);
			//System.out.println("res:" + val);
			out.print(val);
			out.flush();
		}
	}

	@RequestMapping(value = "/addorupdateitem",
					method = RequestMethod.POST)
	public ModelAndView addorUpdateItem(@ModelAttribute("itemMaster") ItemMaster itemMaster,
										BindingResult result,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In addorUpdateItem......{},{},{}", itemMaster.toString());
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String add_edit_delete = "";
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		String s;
		ResponseObj responseObj = new ResponseObj();
		if (itemMaster.getId() == 0) {
			itemMaster.setCreatedBy(userInfo.getId());
			responseObj = itemService.addItem(itemMaster);
			add_edit_delete = "add";
		} else {
			//System.out.println("inside UpdateItem");
			itemMaster.setUpdatedBy(userInfo.getId());
			responseObj = itemService.updateItem(itemMaster);
			add_edit_delete = "edit";
		}
		//System.out.println("response obj=" + responseObj.toString());
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		mav.addObject("result", responseObj.getId());
		mav.addObject("add_edit_delete", add_edit_delete);
		return mav;
	}
	
	@RequestMapping(value="/searchitem",method=RequestMethod.POST)
	public ModelAndView searchItem(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In searchItem......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchItem(commonResultSetMapper);
		//System.out.println("commonResultSetMappers="+commonResultSetMappers.size());
		String searchCriteria=commonResultSetMapper.getSearchCriteria();
		mav.addObject("searchCriteria",searchCriteria);
		mav.addObject("allItems",commonResultSetMappers);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/searchtransitem",method=RequestMethod.POST)
	public ModelAndView searchTransItem(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In searchtransitem......{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchTransItem(commonResultSetMapper);
		//System.out.println("commonResultSetMappers="+commonResultSetMappers.size());
		String searchCriteria=commonResultSetMapper.getSearchCriteria();
		mav.addObject("searchCriteria",searchCriteria);
		mav.addObject("allItems",commonResultSetMappers);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ITEM_SETUP_201J, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.INVENTORY);
		menuselect.setSubMenu(Constant.INV_SETUP);
		menuselect.setChildsubMenu(Constant.ITEM);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_ITEM_PAGE);
		return mav;
	}
	
	@RequestMapping(value="/deleteItem/{id}",method=RequestMethod.GET)
	public void deleteItem(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In deleteBrand......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setItemId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			String res = itemService.deleteItem(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value = "/autocompleteitem", method = RequestMethod.GET)
	public List<CommonResultSetMapper> getItemBrandAutocompleteList(@RequestParam String tagName, HttpSession session, HttpServletResponse response) throws IOException {
		//logger.debug("In autocompleteitem......{}", tagName);
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
		CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		commonResultSetMapper.setItemName(tagName.trim());
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setLang(lang);
		List<CommonResultSetMapper> commonResultSetMappers=itemService.searchItemAutoComplete(commonResultSetMapper);
		//System.out.println("autocompleteitem List :" + commonResultSetMappers);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		out.print(gson.toJson(commonResultSetMappers));
		return null;

	}
	@RequestMapping(value="/getalternatemedicine",method=RequestMethod.POST)
	public void getAlternateMed(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getAlternateMed......{}",commonResultsetMapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getAlternateMedicine(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/addnewitem",method=RequestMethod.POST)
	public void addNewItem(@RequestBody String itemMasterObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In addNewItem......{},{},{}",itemMasterObj.toString());
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
			ItemMaster itemMaster= gson.fromJson(itemMasterObj, new TypeToken<ItemMaster>() {}.getType());
			String res = "";
			itemMaster.setCompanyId(userInfo.getCompanyId());
			itemMaster.setLang(lang);
			if (itemMaster.getId() == 0) {
				itemMaster.setCreatedBy(userInfo.getId());
				res = itemService.addItemViaAjax(itemMaster);
			} else {
				//System.out.println("inside UpdateItem");
				itemMaster.setUpdatedBy(userInfo.getId());
				res = itemService.updateItemViaAjax(itemMaster);
			}			
			
		//	System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	/*================================= Search Items by Content ======================== */
	
	@RequestMapping(value="/getitemsbycontent/{contentid}",method=RequestMethod.GET)
	public void getItemsByContent(@PathVariable int contentid,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getcurrstock......{}",contentid);
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
		 
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
//			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setContentId(contentid);
			commonResultSetMapper.setLang(lang);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = df.format(c.getTime());
			//System.out.println("Date ::" + formattedDate);
			commonResultSetMapper.setAsOnDate(formattedDate);
			String res=itemService.getItemsByContent(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	/*========================================== End ====================================*/
	
	@RequestMapping(value="/getitemhistory",method=RequestMethod.POST)
	public void getItemHistory(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getitemhistory......{}",commonResultsetMapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getItemHistory(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
	@RequestMapping(value="/getitembybarcode",method=RequestMethod.POST)
	public void getItemByBarcode(@RequestBody String commonResultsetMapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In getItemByBarcode......{}",commonResultsetMapperObj.toString());
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
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
//			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res=itemService.getItemByBarcode(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
}
