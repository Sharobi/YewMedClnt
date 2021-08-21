/**
 * 
 */
package com.sharobi.yewpos.pos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.inv.model.CommonResultSetMapper;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsAllDTO;
import com.sharobi.yewpos.pos.model.SaleDetailsDTO;
import com.sharobi.yewpos.pos.model.SaleHeaderDTO;
import com.sharobi.yewpos.pos.model.SaleReturn;
import com.sharobi.yewpos.pos.model.SaleReturnDTO;
import com.sharobi.yewpos.pos.model.SaleReturnDetails;
import com.sharobi.yewpos.pos.model.SaleReturnDetailsDTO;
import com.sharobi.yewpos.pos.model.Sales;
import com.sharobi.yewpos.pos.service.ReturnMemoService;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.DateUtil;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sharobi.yewpos.util.Words;

/**
 * @author habib,Manodip
 * 
 */
@Controller
@RequestMapping("/retunmemo")
public class ReturnMemoController {
	private final static Logger logger = LogManager.getLogger(ReturnMemoController.class);
	private final static ReturnMemoService returnmemoService = new ReturnMemoService();
	private final static RoleService roleService = new RoleService();
	private final static Gson gson = new Gson();

	@RequestMapping(value = "/loadreturnmemo",
					method = RequestMethod.GET)
	public ModelAndView loadReturnMemo(	Model model,
										HttpSession session,
										@RequestParam(	required = false,
														defaultValue = "0") String saleRetId) {
		//logger.debug("In loadreturnmemo......,{}", saleRetId);
		if (!saleRetId.equals("0")) {
			saleRetId = (String.valueOf(saleRetId).split("-")[2]);
			//System.out.println("new saleRetId=" + saleRetId);
		}
		ModelAndView mav = new ModelAndView();
		SaleReturnDTO saleReturnDTO = new SaleReturnDTO();
		List<SaleReturnDetailsDTO> saleReturnDetailsDTOs = new ArrayList<SaleReturnDetailsDTO>();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if (!saleRetId.equals("0")) {
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setSaleReturnId(Integer.valueOf(saleRetId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			saleReturnDTO = gson.fromJson(returnmemoService.getReturnMemoSaleHeaderByRetInvId(commonResultSetMapper), new TypeToken<SaleReturnDTO>() {}.getType());
			saleReturnDetailsDTOs = gson.fromJson(returnmemoService.getReturnMemoSaleDetailsByRetInvId(commonResultSetMapper), new TypeToken<List<SaleReturnDetailsDTO>>() {}.getType());
		}
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RETURN_CREDIT_MEMO_102, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleRetId", saleRetId);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.RET_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.addObject("saleReturnDTO", saleReturnDTO);
		mav.addObject("saleReturnDetailsDTOs", saleReturnDetailsDTOs);
		mav.setViewName(ForwardConstants.VIEW_RETURNMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getsaleretdetbyid",
					method = RequestMethod.POST)
	public void getSaleretDetById(	@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In getsaleretdetbyid......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.getReturnMemoSaleHeaderByRetInvId(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/modifyreturnmemo",
					method = RequestMethod.GET)
	public ModelAndView modifyReturnMemo(	Model model,
											HttpSession session) {
		//logger.debug("In modifyreturnmemo......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
		commonResultSetMapper.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		commonResultSetMapper.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
		mav.addObject("commonResultSetMapper", commonResultSetMapper);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.MC_RET_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_MODIFYRETURNMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/reprintreturnmemo",
					method = RequestMethod.GET)
	public ModelAndView reprintReturnMemo(	Model model,
											HttpSession session) {
		//logger.debug("In reprintreturnmemo......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.REPRINT_RET_MEMO);
		mav.addObject("menuselect", menuselect);
		mav.setViewName(ForwardConstants.VIEW_REPRINTRETURNMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/searchreturnmemoinvoice",
					method = RequestMethod.POST)
	public ModelAndView searchreturnmemoinvoice(@ModelAttribute("commonResultSetMapper") CommonResultSetMapper commonResultSetMapper,
												HttpSession session,
												HttpServletResponse response) throws IOException {

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
		String invno = commonResultSetMapper.getInvoiceNo();
		commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
		commonResultSetMapper.setStoreId(userInfo.getStoreId());
		commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
		commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + commonResultSetMapper.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
		commonResultSetMapper.setStatus(Constant.ORDER_ALL);
		//logger.debug("In searchsalesinvoice......{},{},{}", commonResultSetMapper.toString());
		List<SaleReturnDTO> saleReturnDTOs = returnmemoService.getAllReturnMemoInvoice(commonResultSetMapper);
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),(Integer)session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(), userInfo.getProductTypeId(), RoleBasedCostants.RETURN_CREDIT_MEMO_102, lang);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		mav.addObject("saleReturnDTOs", saleReturnDTOs);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.POS);
		menuselect.setSubMenu(Constant.MC_RET_MEMO);
		mav.addObject("menuselect", menuselect);
		commonResultSetMapper.setInvoiceNo(invno); // after result replaced with only inv no
		mav.setViewName(ForwardConstants.VIEW_MODIFYRETURNMEMO_PAGE);
		return mav;
	}

	@RequestMapping(value = "/getretrsaledetbyinvno",
					method = RequestMethod.POST)
	public void getRetSaleDetByInvNo(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getretrsaledetbyinvno......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.getReturnMemoSaleDetailsByInvNo(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getretrsaleheaderbyinvno",
					method = RequestMethod.POST)
	public void getRetSaleHeaderByInvNo(@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getretrsaledetbyinvno......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.getReturnMemoSaleDetailsByInvNo(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getretrsalealldetbyinvno",
					method = RequestMethod.POST)
	public void getRetSaleAllDetByInvNo(@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getRetSaleAllDetByInvNo......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			//commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.getReturnMemoSaleALLDetailsByInvNo(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getretrsalealldetbyitem",
					method = RequestMethod.POST)
	public void getRetSaleAllDetByItem(	@RequestBody String commonResultsetMapperObj,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In getretrsalealldetbyitem......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setNoOfMonthBefore(Constant.SHOW_SALES_INVOICE_RETURN_ITEM_NOOFMONTHSBEFORE);
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.getReturnMemoSaleALLDetailsByItem(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/createorupdatesalesreturn",
					method = RequestMethod.POST)
	public void createOrUpdateSalesReturnInv(	@RequestBody String salesRetString,
												HttpSession session,
												HttpServletResponse response) throws IOException {
		//logger.debug("In createOrUpdateSalesReturnInv......{},{},{}", salesRetString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			SaleReturn saleReturn = gson.fromJson(salesRetString, new TypeToken<SaleReturn>() {}.getType());
			saleReturn.setCompanyId(userInfo.getCompanyId());
			saleReturn.setStoreId(userInfo.getStoreId());
			saleReturn.setCreatedBy(userInfo.getId());
			saleReturn.setFinyrId(userInfo.getFinyrId());
			saleReturn.setFinyrCode(userInfo.getFinyrCode());
			saleReturn.setLang(lang);
			saleReturn.setQs(Constant.SALE_RETURN_PAYMENT_QS);
			saleReturn.setIs_account(userInfo.getIs_account());
			
			//System.err.println(saleReturn.toString());
			//logger.debug("In createOrUpdateSalesReturnInv.---->qs",saleReturn.toString());
			String res = returnmemoService.createOrUpdateSalesReturnInvoice(saleReturn);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deleteretsalesinv",
					method = RequestMethod.POST)
	public void deleteRetSalesInv(	@RequestBody String commonResultsetMapperObj,
									HttpSession session,
									HttpServletResponse response) throws IOException {
		//logger.debug("In deleteretsalesinv......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.deleteRetSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postretsalesinv",
					method = RequestMethod.POST)
	public void postRetSalesInv(@RequestBody String commonResultsetMapperObj,
								HttpSession session,
								HttpServletResponse response) throws IOException {
		//logger.debug("In postretsalesinv......{}", commonResultsetMapperObj.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			String res = returnmemoService.postRetSalesInvoice(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getretdetforadj",
					method = RequestMethod.POST)
	public void getReturnMemoDetailsForAdj(	@RequestBody String commonResultsetMapperObj,
											HttpSession session,
											HttpServletResponse response) throws IOException {

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			//			if (null == commonResultSetMapper.getInvoiceNo()) {} else {
			//				commonResultSetMapper.setInvoiceNo(Constant.SALES_INVOICE_RETURN_MEMO + "/" + userInfo.getFinyrCode() + "/" + commonResultSetMapper.getInvoiceNo());
			//			}
			commonResultSetMapper.setLang(lang);
			//System.out.println("commonResultSetMapper.getInvoiceNo()=" + commonResultSetMapper.getInvoiceNo());
			//logger.debug("In getretdetforadj......{}", commonResultsetMapperObj.toString());
			String res = returnmemoService.getReturnMemoDetailsForAdj(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getretdetforadjbysaleid",
					method = RequestMethod.POST)
	public void getReturnMemoDetailsForAdjBySaleid(	@RequestBody String commonResultsetMapperObj,
													HttpSession session,
													HttpServletResponse response) throws IOException {

		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			CommonResultSetMapper commonResultSetMapper = gson.fromJson(commonResultsetMapperObj, new TypeToken<CommonResultSetMapper>() {}.getType());
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setFinYrId(userInfo.getFinyrId());
			commonResultSetMapper.setLang(lang);
			//logger.debug("In getretdetforadj......{}", commonResultsetMapperObj.toString());
			String res = returnmemoService.getReturnMemoDetailsForAdjBySaleId(commonResultSetMapper);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/postallselectedreturnsalesinvoice",
					method = RequestMethod.POST)
	public void postAllSelectedReturnSalesInv(@RequestBody String salesRetString,
										HttpSession session,
										HttpServletResponse response) throws IOException {
		//logger.debug("In postAllSelectedReturnSalesInv......{},{},{}", salesRetString.toString());
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			SaleReturn saleReturn = gson.fromJson(salesRetString, new TypeToken<SaleReturn>() {}.getType());
			saleReturn.setCompanyId(userInfo.getCompanyId());
			saleReturn.setStoreId(userInfo.getStoreId());
			saleReturn.setCreatedBy(userInfo.getId());
			saleReturn.setFinyrId(userInfo.getFinyrId());
			saleReturn.setFinyrCode(userInfo.getFinyrCode());
			saleReturn.setLang(lang);
			String res = returnmemoService.postAllSelectedReturnSalesInv(saleReturn);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	/*==================================== Print Sales Return ========================================*/

	@RequestMapping(value = "/printreturnmemo",
					method = RequestMethod.GET)
	public ModelAndView printReturnMemo(Model model,
										HttpSession session,
										@RequestParam(	required = false,
														defaultValue = "0") String saleRetId,
										@RequestParam(required = false) String backUrl) {
		//logger.debug("In printreturnmemo......,{},{}", saleRetId, backUrl);
		if (!saleRetId.equals("0")) {
			saleRetId = (String.valueOf(saleRetId).split("-")[2]);
			//System.out.println("new saleRetId=" + saleRetId);
		}
		ModelAndView mav = new ModelAndView();
		SaleReturnDTO saleReturnDTO = new SaleReturnDTO();
		//List<SaleReturnDetailsDTO> saleReturnDetailsDTOs = new ArrayList<SaleReturnDetailsDTO>();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if (!saleRetId.equals("0")) {
			CommonResultSetMapper commonResultSetMapper = new CommonResultSetMapper();
			commonResultSetMapper.setSaleReturnId(Integer.valueOf(saleRetId));
			commonResultSetMapper.setCompanyId(userInfo.getCompanyId());
			commonResultSetMapper.setStoreId(userInfo.getStoreId());
			commonResultSetMapper.setLang(lang);
			saleReturnDTO = gson.fromJson(returnmemoService.getReturnMemoSaleHeaderByRetInvId(commonResultSetMapper), new TypeToken<SaleReturnDTO>() {}.getType());
			//saleReturnDetailsDTOs = gson.fromJson(returnmemoService.getReturnMemoSaleDetailsByRetInvId(commonResultSetMapper), new TypeToken<List<SaleReturnDetailsDTO>>() {}.getType());
		}
		mav.addObject("stringAmount", Words.convertNumberToWords(BigDecimal.valueOf(saleReturnDTO.getNetAmount()), true, true));
		mav.addObject("backUrl", backUrl);
		mav.addObject("saleReturnDTO", saleReturnDTO);
		mav.setViewName(ForwardConstants.VIEW_PRINTRETURNSALES_PAGE);
		return mav;
	}

	/*================================== End Print Sales Return ======================================*/

}
