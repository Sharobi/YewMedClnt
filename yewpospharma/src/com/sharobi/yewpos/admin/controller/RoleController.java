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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.admin.model.CommonMapData;
import com.sharobi.yewpos.admin.model.Role;
import com.sharobi.yewpos.admin.model.User;
import com.sharobi.yewpos.admin.service.RoleMgmtService;
import com.sharobi.yewpos.admin.service.UserService;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;

@Controller
@RequestMapping("/rolemanagement")
public class RoleController {
	private final static Logger logger=LoggerFactory.getLogger(RoleController.class);
	private final static UserService userService = new UserService();
	private final static RoleService roleService = new RoleService();
	private final static RoleMgmtService roleMgmtService = new RoleMgmtService();
	Gson gson = new Gson();
	
	@RequestMapping(value="/role",method=RequestMethod.GET)
	public ModelAndView openRole(Model model,HttpSession session)
	{
		//logger.info("In Role......");
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
		String allRoles = roleMgmtService.getAllRoles(userInfo.getCompanyId());
		List<Role> roleList =  gson.fromJson(allRoles, new TypeToken<List<Role>>() {}.getType());
		MenuByUserDTO menuByUserDTO= roleService.getRoleMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId(), RoleBasedCostants.AREA_SETUP_201R, lang);
		MenuSelection menuselect=new MenuSelection();
		menuselect.setMenu(Constant.ADMIN);
		menuselect.setSubMenu(Constant.ROLE_SETUP);
		mav.addObject("allusers",allUserOfCompany);
		mav.addObject("menuselect",menuselect);
		mav.addObject("allroles",roleList);
		mav.setViewName(ForwardConstants.VIEW_ROLE_PAGE);
		mav.addObject("menuByUserDTO",menuByUserDTO);
		return mav;
	}
	
	@RequestMapping(value="/getRolebyId",method=RequestMethod.POST)
	public void getRoleById(@RequestBody String commonresultsetmapperObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In getRoleById......{}",commonresultsetmapperObj);
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
			commonResultSetMapper.setLang(lang);
			out.print(roleMgmtService.getRoleById(commonResultSetMapper));
			out.flush();
		}
	}

	
	@RequestMapping(value = "/addRole",method = {RequestMethod.GET,RequestMethod.POST})
	public void addRole(@RequestBody String role,Model model,HttpSession session,HttpServletResponse response) throws IOException {
		//logger.info("In addRole......");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Role roleObj = gson.fromJson(role, new TypeToken<Role>() {}.getType());
			roleObj.setCompanyId(userInfo.getCompanyId());
			roleObj.setCreatedBy(userInfo.getId());
			//logger.info("In addRole......{},{},{}", role.toString());
			String res=roleMgmtService.addRole(roleObj);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
			
		}
     }
	
	@RequestMapping(value = "/updateRole",method = {RequestMethod.GET,RequestMethod.POST})
	public void updateRole(@RequestBody String role,Model model,HttpSession session,HttpServletResponse response) throws IOException {
		//logger.info("In updateRole......");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Role roleObj = gson.fromJson(role, new TypeToken<Role>() {}.getType());
			roleObj.setCompanyId(userInfo.getCompanyId());
			roleObj.setUpdatedBy(userInfo.getId());
		//	logger.info("In updateRole......{},{},{}", role.toString());
			String res=roleMgmtService.updateRole(roleObj);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
			
		}
     }
	
	@RequestMapping(value="/deleteRole/{id}",method=RequestMethod.GET)
	public void deleteBrand(@PathVariable("id") int id,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In deleteRole......{}",id);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			CommonMapData obj = new CommonMapData();
			obj.setId(id);
			obj.setCompanyId(userInfo.getCompanyId());
			String res = roleMgmtService.deleteRole(obj);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
}
