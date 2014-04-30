package org.wind.k.utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

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
	
	/**
	 * check if the HttpHeaders.If-None-Match equals Etag. 
	 * if it does, the server will send response code 304 not 200 and do not return content. 
	 * and then the browser will load the file from local cache
	 * 
	 * @param request
	 * @param response
	 * @param Etag
	 */
	public static boolean checkIfNoneMatchHeader(HttpServletRequest request,HttpServletResponse response,String Etag){
		String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
		boolean flag = false;
		if(headerValue != null){
			if(!"*".equals(headerValue)){
				String[] values =  headerValue.split(",");
				if(values!= null && values.length > 0 ){
					for(int i = 0; i<values.length; i++){
						if(values[i].trim().equals(Etag)){
							flag = true;
						}
					}
				}
			}else{
				flag = true;
			}
			if(flag == true){
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader(HttpHeaders.ETAG, Etag);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check if the encodingType is in HttpHeaders.Accept-Encoding
	 * 
	 * @param request
	 * @param encodingType
	 * @return
	 */
	public static boolean checkAcceptEncoding(HttpServletRequest request,String encodingType){
		// Http1.1 header
		String acceptEncoding  = request.getHeader("Accept-Encoding");
		return StringUtils.contains(acceptEncoding, encodingType);
	}
	
	/**
	 * set expire time
	 * @param response
	 * @param expireSeconds
	 */
	public static void setExpiresHeader(HttpServletResponse response,long expireSeconds){
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expireSeconds * 1000));
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expireSeconds);
	}
	
	/**
	 * set last modified date
	 * @param response
	 * @param lastModifiedDate
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,long lastModifiedDate){
		response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
	}
	
	/**
	 * set download file name
	 * @param response
	 * @param fileName
	 */
	public static void setContentDispositionHeader(HttpServletResponse response,String fileName){
		try {
			String encodedName = new String(fileName.getBytes(),"ISO8859-1");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedName + "\"");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
