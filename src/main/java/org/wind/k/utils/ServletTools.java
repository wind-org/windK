package org.wind.k.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;

import com.google.common.net.HttpHeaders;

/***
 * 
 * @author Stephen
 * 
 * @Date 2014-4-21
 *
 */
public class ServletTools {
	
	
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
	
	/**
	 * get parameters from request by prefix
	 * 
	 * @param request
	 * @param prefix
	 * @return
	 */
	public static Map<String,Object> getParametersByPrefix(ServletRequest request,String prefix){
		Validate.notNull(request,"request can not be null");
		
		Map<String,Object> params = new TreeMap<String,Object>();
		
		Enumeration<String> paramNames = request.getParameterNames();
		if(prefix == null){
			prefix = "";
		}
		while((paramNames != null) && paramNames.hasMoreElements()){
			String element = paramNames.nextElement();
			if("".equals(prefix) || element.startsWith(prefix)){
				String noPrefixParam = element.substring(prefix.length());
				String[] values = request.getParameterValues(noPrefixParam);
				if((values == null) || values.length == 0){
					//do nothing
				}else if(values.length > 1){
					params.put(noPrefixParam, values);
				}else{
					params.put(noPrefixParam, values[0]);
				}
			}
		}
		return params;
	}
	
	/**
	 * check the HttpHeaders.IF_MODIFIED_SINCE
	 * 
	 * @param request
	 * @param response
	 * @param lastModified
	 * @return
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified){
		
		long ifmodifiedsince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
		if(ifmodifiedsince != -1 && (lastModified <(ifmodifiedsince + 1000))){
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}
	
	public static void setExpiresHeader(HttpServletResponse response,long expireSeconds){
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expireSeconds * 1000));
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expireSeconds);
	}
	
	
}
