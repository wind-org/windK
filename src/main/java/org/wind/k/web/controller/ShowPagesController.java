package org.wind.k.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @author stephen
 * 
 * @Date 2014-4-25
 *
 */
@Controller
public class ShowPagesController {
	
	@RequestMapping( value = "/tools",method = RequestMethod.GET)
	public String toToolsPage(){
		return "showpages/tools";
	}
	
	@RequestMapping(value = "/webservice" ,method = RequestMethod.GET)
	public String toWebServicePage(){
		return "showpages/webservice";
	}
	
	@RequestMapping(value = "/jms" ,method = RequestMethod.GET)
	public String toJms(){
		return "showpages/emailByJms";
	}
}
