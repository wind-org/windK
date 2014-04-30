package org.wind.k.web.controller.tools;

import java.io.File;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wind.k.utils.ServletTools;

/**
 * 
 * @author stephen
 * 
 * @Date 2014-4-30
 *
 */
@Controller
public class StaticContentDownloadController {

	//the mime type of the compressed content 
	private static final String [] MIME_TYPE = {"text/plain","text/html","text/css","text/javascript",
		"application/xhtml+xml","application/x-javascript","application/json","application/octet-stream"};
	//the minimum size of compressed content
	private static final int MINI_COMPRESSION_SIZE = 512; 
	//mime type map
	private MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
	
	@RequestMapping( value = "/tools/staticContent",method = RequestMethod.GET)
	public void downloadStaticContent(HttpServletRequest request, HttpServletResponse response){
		
		mimetypesFileTypeMap.addMimeTypes("text/css css");
		OutputStream output = null;
		try{
			//resource path
			String contentPath = request.getParameter("contentPath");
			if(StringUtils.isBlank(contentPath)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The content path parameter is required");
				return;
			}
			ContentInfo contentInfo = getContentInfo(request,contentPath);
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
				response.setHeader("Content-Encoding", "gzip");
				response.setHeader("Vary", "Accept-Encoding");
				output = new GZIPOutputStream(response.getOutputStream());
						
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
	private ContentInfo getContentInfo(HttpServletRequest request, String contentPath){
		
		ContentInfo contentInfo = new ContentInfo();
		
		String realPath = request.getServletContext().getRealPath(contentPath);
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
}
