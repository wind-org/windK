package org.wind.k.web.servlet.staticdownload;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.wind.k.utils.ServletTools;

/**
 *  download static content by Gzip compressed transfer
 *  
 * @author Stephen
 * 
 * @Date 2014-4-16
 *
 */
public class StaticContentDownloadServlet extends HttpServlet {

	
	private static final long serialVersionUID = -8454600150937164037L;
	
	//the mime type of the compressed content 
	private static final String [] MIME_TYPE = {"text/plain","text/html","text/css","text/javascript",
		"application/xhtml+xml","application/x-javascript","application/json"};
	//the minimum size of compressed content
	private static final int MINI_COMPRESSION_SIZE = 512; 
	//mime type map
	private MimetypesFileTypeMap mimetypesFileTypeMap;
	
	private ApplicationContext applicationContext;
	
	
	public void init() throws ServletException {
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		//add text/css type
		mimetypesFileTypeMap.addMimeTypes("text/css css");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		OutputStream output = null;
		
		try{
			//resource path
			String contentPath = request.getParameter("contentPath");
			if(StringUtils.isNoneBlank(contentPath)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The content path parameter is required");
				return;
			}
			ContentInfo contentInfo = getContentInfo(contentPath);
			//no modified
			if(!ServletTools.checkIfModifiedSince(request, response, contentInfo.getLastModified())
					|| !ServletTools.checkIfNoneMatchHeader(request, response, contentInfo.getEtag())){
				return;
			}
			ServletTools.setExpiresHeader(response,ServletTools.ONE_YEAR_SECONDS);
			ServletTools.setLastModifiedHeader(response, contentInfo.getLastModified());
			response.setContentType(contentInfo.getMimeType());
			ServletTools.setContentDispositionHeader(response,contentInfo.getFileName());
			
			//build compression outputStream
			if(ServletTools.checkAcceptEncoding(request,"gzip") && contentInfo.isNeedCompression()){
				output = buildCompressionOutputStream(response);
						
			//get normal outputSteam
			}else{
				response.setContentLength(contentInfo.getLength());
				output = response.getOutputStream();
			}
			FileUtils.copyFile(contentInfo.getFile(), output);
			output.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ContentInfo getContentInfo(String contentPath){
		
		ContentInfo contentInfo = new ContentInfo();
		
		String realPath = this.getServletContext().getRealPath(contentPath);
		File file = new File(realPath);
		if(file.exists()){
			contentInfo.setPath(realPath);
			contentInfo.setFile(file);
			contentInfo.setFileName(file.getName());
			contentInfo.setLastModified(file.lastModified());
			contentInfo.setLength((int)file.length());
			contentInfo.setMimeType(mimetypesFileTypeMap.getContentType(file.getName()));
			contentInfo.setEtag("\"f"+file.lastModified()+"\"");
			if(file.length() >= MINI_COMPRESSION_SIZE && ArrayUtils.contains(MIME_TYPE,contentInfo.getMimeType())){
				contentInfo.setNeedCompression(true);
			}else{
				contentInfo.setNeedCompression(false);
			}
		}
		return contentInfo;
	}
	
	private OutputStream buildCompressionOutputStream(HttpServletResponse response) throws IOException{
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}
	
	
	

}
