/**
 * 
 */
package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.service.HomeService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.storemgnt.model.StoreMaster;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.ResponseObj;
import com.google.gson.Gson;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;

/**
 * @author habib
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	private final static Logger logger=LogManager.getLogger(HomeController.class);
	private final static RoleService roleService=new RoleService();
	private final static StoreMgntService storeService=new StoreMgntService();
	private final static HomeService homeService = new HomeService();
	Gson gson = new Gson();
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(Model model,HttpSession session)
	{
		//logger.debug("In welcome......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))==null)
		{
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		
		// new added 25.7.2019 start for admin panel and dashboard
		CommonResultSetMapper commonResultSetMapper= new CommonResultSetMapper();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId()); 
		List<StoreMaster>  allStorelist=homeService.getAllStore(commonResultSetMapper);
		/*if(allStorelist.size()>0){*/
	//	System.out.println("==============="+allStorelist.toString());
        session.setAttribute(Constant.SES_ALL_STORES_DATA, allStorelist); 
		/*}*/
        // new added 25.7.2019 end for admin panel and dashboard
        
		StoreMaster store=storeService.getStoreDetailsById(userInfo.getStoreId());
		/*List<MenuByUserDTO> appMenuList=roleService.getAppMenuByUser(userInfo.getCompanyId(), userInfo.getStoreId(), userInfo.getId(),userInfo.getProductTypeId());*/
		List<MenuByUserDTO> appMenuList=roleService.getAppMenuByUser(userInfo.getCompanyId(), (Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),userInfo.getProductTypeId()); // New added 25.7.2019 for Admin Part
		//System.out.println(appMenuList);
		session.setAttribute(Constant.SES_LOGGED_IN_STORE, store);
		session.setAttribute(Constant.SES_APP_MENU_LIST, appMenuList);
		mav.addObject(Constant.SES_LOGGED_IN_STORE,store);
		mav.addObject(Constant.SES_APP_MENU_LIST,appMenuList);
		Locale locale = LocaleContextHolder.getLocale();
		session.setAttribute(Constant.SES_LOGGED_IN_LANG, locale.getDisplayLanguage());
		mav.addObject("financialyear", userInfo.getStartDate().toString());
		mav.addObject("allstorelist", allStorelist);
		mav.addObject("isTRN", userInfo.getIsTaxRegNo());
		mav.addObject("cur", userInfo.getCurrency_code());
		mav.addObject("cur_id", store.getCurrencyId());
		mav.addObject(Constant.HOME,"Y");
		if(userInfo.getIsAdmin() == 1){
        	mav.setViewName(ForwardConstants.VIEW_ADMIN_HOME_PAGE);
			/*mav.setViewName(ForwardConstants.VIEW_HOME_PAGE);*/
        }else{
        	mav.setViewName(ForwardConstants.VIEW_HOME_PAGE);
        }
		/*mav.setViewName(ForwardConstants.VIEW_HOME_PAGE);*/
		return mav;
	}
	
	
	/*New added 25.7.2019 for admin panel*/
	@RequestMapping(value="/changeStore/{storeId}",method=RequestMethod.GET)
	public void changeCurrentStore(@PathVariable int storeId,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.info("In changestore......{}",storeId);
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			List<StoreMaster>  allStorelist = null;
			allStorelist = (List<StoreMaster>) session.getAttribute(Constant.SES_ALL_STORES_DATA);
			for(StoreMaster ob: allStorelist){
				if(ob.getId()==storeId){
					userInfo.setStoreId(storeId);
					userInfo.setFinyrId(ob.getFinYrId());
					break;
				}
			}
			userInfo.setPassword(null);
			session.setAttribute(Constant.SES_LOGGED_IN_USER, userInfo);
			
			LoginInfoByUserDTO ob = ((LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)); 
			//System.out.println("++++++++++++++ UPDATED SESSION ++++++++++++"+ob.toString());
			
			out.print("success");
			out.flush();
		}
	}
	
	
	// For Dashboard 26.7.2019
	
	@RequestMapping(value = "/getalltotal", method = RequestMethod.POST)
	/*public void getalltotal(@RequestBody CommonResultSetMapper commonResultSetMapper,HttpSession session, HttpServletResponse response)
			throws IOException {*/
	public void getalltotal(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int id,HttpSession session, HttpServletResponse response)
			throws IOException {
		//logger.info("In getalltotal......{}" );

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

		     CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
		     commonResultSetMapper.setStartDate(startDate);
			 commonResultSetMapper.setEndDate(endDate);
			 commonResultSetMapper.setId(id);
			 commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			 commonResultSetMapper.setStoreId(userInfo.getStoreId());
			 commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			String res =gson.toJson(homeService.getTotal(commonResultSetMapper));
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}


	@RequestMapping(value = "/getlinechart", method = RequestMethod.POST)
	/*public void getLinechart(@RequestBody CommonResultSetMapper commonResultSetMapper,HttpSession session, HttpServletResponse response)
			throws IOException {*/
	public void getLinechart(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int id,HttpSession session, HttpServletResponse response)
			throws IOException {
	
		//logger.info("In getlinechart......{}" );

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			CommonResultSetMapper commonResultSetMapper=new CommonResultSetMapper();
			commonResultSetMapper.setStartDate(startDate);
			commonResultSetMapper.setEndDate(endDate);
			commonResultSetMapper.setId(id);
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			//String res =gson.toJson(homeService.getLineChart(commonResultSetMapper));
			 String res =homeService.getLineChart(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

}
