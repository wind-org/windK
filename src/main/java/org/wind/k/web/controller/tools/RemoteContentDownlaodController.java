package org.wind.k.web.controller.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wind.k.utils.ServletTools;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-5-4
 *
 */
@Controller
public class RemoteContentDownlaodController {
	
	
	private static final int TIMEOUT_SECONDS = 20;
	private static final int POOL_SIZE = 10;
	private static Logger logger = LoggerFactory.getLogger(RemoteContentDownlaodController.class);
	private static CloseableHttpClient httpClient;

	
	@RequestMapping(value = "/tools/remoteContent")
	public void getRemoteContent(HttpServletRequest request, HttpServletResponse response,
			String remoteUrl,String type){
		try{
			if(StringUtils.isBlank(remoteUrl)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The remote url parameter is required");
				return;
			}
			if("Y".equals(type)){
				getRemoteContentByHttpClient(request,response,remoteUrl);
			}else{
				getRemoteContentByUrlConnection(request,response,remoteUrl);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * by apache http client
	 * @param request
	 * @param response
	 * @param remoteUrl
	 */
	public void getRemoteContentByHttpClient(HttpServletRequest request, HttpServletResponse response,String remoteUrl)throws IOException{
		
		CloseableHttpResponse httpResponse = null;
		try{
			//init 
			initApacheHttpClient();
			HttpGet httpGet = new HttpGet(remoteUrl);
			httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode >= 400){
				response.sendError(statusCode, "Error in get remote content by http client");
				return;
			}
			HttpEntity he = httpResponse.getEntity();
			response.setContentType(he.getContentType().getValue());
			if(he.getContentLength()>0){
				response.setContentLength((int)he.getContentLength());
			}
			String fileName = remoteUrl.substring(remoteUrl.lastIndexOf("/")-1,remoteUrl.length());
			System.out.println(fileName);
			ServletTools.setContentDispositionHeader(response,fileName);
			InputStream input = he.getContent();
			OutputStream output = response.getOutputStream();
			try{
				IOUtils.copy(input, output);
				output.flush();
			}finally {
				IOUtils.closeQuietly(input);
			}
		}finally{
			httpResponse.close();
			httpClient.close();
		}
	}
	public void initApacheHttpClient(){
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(TIMEOUT_SECONDS * 1000).setConnectTimeout(TIMEOUT_SECONDS*1000).build();
		
		httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(POOL_SIZE).setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(requestConfig).build();
	}
	
	public void getRemoteContentByUrlConnection(HttpServletRequest request, HttpServletResponse response,String remoteUrl)throws IOException{
		
		InputStream input;
		HttpURLConnection urlConnection = (HttpURLConnection)new URL(remoteUrl).openConnection();
		try {
			
			urlConnection.setReadTimeout(TIMEOUT_SECONDS*1000);
			urlConnection.connect();
			try {
				input = urlConnection.getInputStream();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_NOT_FOUND, remoteUrl + " is not found.");
				return;
			}
			response.setContentType(urlConnection.getContentType());
			if(urlConnection.getContentLength()>0){
				response.setContentLength(urlConnection.getContentLength());
			}
			String fileName = remoteUrl.substring(remoteUrl.lastIndexOf("/")+1,remoteUrl.length());
			ServletTools.setContentDispositionHeader(response,fileName);
			OutputStream output = response.getOutputStream();
			try{
				IOUtils.copy(input, output);
				output.flush();
			}finally {
				IOUtils.closeQuietly(input);
			}
		}finally{
			urlConnection.disconnect();
		}	
	}
}
