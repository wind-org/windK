package org.wind.k.web.controller.tools;

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
public class ToolsController {
	
	@RequestMapping( value = "/tools",method = RequestMethod.GET)
	public String toToolsMainPage(){
		return "tools/main";
	}
	
}
