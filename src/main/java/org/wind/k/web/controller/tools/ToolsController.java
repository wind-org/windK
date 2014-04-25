package org.wind.k.web.controller.tools;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author stephen
 * 
 * @Date 2014-4-25
 *
 */
public class ToolsController {
	
	@RequestMapping("/tools")
	String toToolsMainPage(){
		return "tools/main";
	}
	
}
