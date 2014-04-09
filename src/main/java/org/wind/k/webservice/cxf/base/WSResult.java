package org.wind.k.webservice.cxf.base;

import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-26
 *
 */
@XmlType(name = "WSResult")
public class WSResult {
	
	public static final String SUCCESS = "0";
	
	public static final String SYS_ERROR = "500";
	public static final String SYS_ERROR_MSG = "Runtime error";
	
	private String code = SUCCESS;
	private String message;
	
	public void setErrorMsg(String code,String message){
		this.code =  code;
		this.message = message;
	}
	
	public void setDefaultErrorMsg(){
		setErrorMsg(SYS_ERROR,SYS_ERROR_MSG);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
