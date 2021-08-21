/**
 * 
 */
package com.sharobi.yewpos.storemgnt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.storemgnt.service.StoreMgntService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.EmailBean;

@Controller
@RequestMapping("/mail")
public class MailController {

	private final static Logger logger=LogManager.getLogger(MailController.class);
	private final static StoreMgntService storeMgntService=new StoreMgntService();
	
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	public void sendMail(@RequestBody String emailBeanObj,HttpSession session,HttpServletResponse response) throws IOException
	{
		//logger.debug("In sendmail......{},{},{}",emailBeanObj.toString());
		LoginInfoByUserDTO userInfo=null;
		if((userInfo=(LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER))!=null)
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new Gson();
			EmailBean emailBean= gson.fromJson(emailBeanObj, new TypeToken<EmailBean>() {}.getType());
			emailBean.setCompanyId(userInfo.getCompanyId());
			emailBean.setStoreId(userInfo.getStoreId());
			emailBean.setFinYrId(userInfo.getFinyrId());
			String res=storeMgntService.sendMail(emailBean);
			//System.out.println("res:"+res);
			out.print(res);
			out.flush();
		}
	}
	
}
