package com.sharobi.yewpos.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.admin.model.CommonMapData;
import com.sharobi.yewpos.admin.model.User;
import com.sharobi.yewpos.admin.service.UserService;
import com.sharobi.yewpos.inv.controller.InvSetupController;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/usermanagement")
public class UserController {
	private final static Logger logger=LoggerFactory.getLogger(UserController.class);
	private final static UserService userService = new UserService();
	private final static RoleService roleService = new RoleService();
	Gson gson = new Gson();
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView openUser(Model model,HttpSession session)
	{
		//logger.info("In User......");
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
		commonResultSetMapper.setLang(lang);
	    List<User> allUserOfCompany = userService.getAllUserList(commonResultSetMapper);
		
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.AREA_SETUP_201R, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.ADMIN);
		menuselect.setSubMenu(Constant.USER_SETUP);
		mav.addObject("allusers",allUserOfCompany);
		mav.addObject("menuselect",menuselect);
		mav.setViewName(ForwardConstants.VIEW_USER_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public ModelAndView searchUser(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper ,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In searchUser.....{},{},{}",commonResultSetMapper.toString());
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
		commonResultSetMapper.setLang(lang);
		List<User> allUsers=userService.getAllUserList(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.ZONE_SETUP_201Q, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.ADMIN);
		menuselect.setSubMenu(Constant.USER_SETUP);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allAreas",allUsers);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		mav.setViewName(ForwardConstants.VIEW_USER_PAGE);
		return mav;
	}
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public void addUser(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In addUser......{}",commonresultsetmapperObj.toString());
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
			CommonMapData commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonMapData>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setCreatedBy(userInfo.getId());
			/*commonResultSetMapper.setStoreId(userInfo.getStoreId());*/
			commonResultSetMapper.setLang(lang);
			String res=userService.addUser(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	public void editUser(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In edit area......{}",commonresultsetmapperObj.toString());
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
			CommonMapData commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonMapData>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			/*commonResultSetMapper.setStoreId(userInfo.getStoreId());*/
			commonResultSetMapper.setLang(lang);
			String res=userService.editUser(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public void deleteUser(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In commonresultsetmapperObj for User delete......{}",commonresultsetmapperObj);
		//ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		String lang=null;
		if((lang=(String) session.getAttribute(Constant.SES_LOGGED_IN_LANG))==null)
		{
			lang=Constant.DEFAULT_LANG;
		}
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonMapData commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonMapData>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			String res=userService.deleteUser(commonResultSetMapper);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value="/getUserbyId",method=RequestMethod.POST)
	public void getUserById(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getUserById......{}",commonresultsetmapperObj);
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
			CommonMapData commonResultSetMapper = gson.fromJson(commonresultsetmapperObj, new TypeToken<CommonMapData>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			out.print(userService.getUserById(commonResultSetMapper));
			out.flush();
		}
	}

	
}
