package org.wind.k.web.controller.tools;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Mashup Service, to support cross domain calls
 * 
 * @author Stephen
 * 
 * @Date 2014-6-17
 *
 */

@Controller
public class MashupController {

	@RequestMapping(value="/tools/mashup", produces = "application/javascript;charset=UTF-8")
	@ResponseBody
	public String getMashupData(@RequestParam("callback")String callBackName){
		
		String JonpStr = "";
		String mashupContent = "<span style='color:green'>you know, this is from mashup server(in other domain).</span>";
		try{
			Map contentMap = new HashMap();
			contentMap.put("content", mashupContent);
			JSONPObject jonpObj = new JSONPObject(callBackName,contentMap);
			ObjectMapper om = new ObjectMapper();
			JonpStr = om.writeValueAsString(jonpObj);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return JonpStr;
	}
	
	
	
}
