package org.wind.k.web.controller.tools;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author stephen
 * 
 * @Date 2014-5-8
 *
 */
@Controller
public class FileUploadController {
	
	
	/**
	 * The file upload is implement by commons-fileupload in spring mvc
	 * 
	 * @param files
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tools/uploadFile" ,method = RequestMethod.POST)
	public String uploadFiles(@RequestParam MultipartFile[] files,HttpServletRequest request){
		
		StringBuffer sb = new StringBuffer();
		String responseMsg = "";
		try{
			if(files != null){
				for(MultipartFile file : files){
					if(file.isEmpty()){
						sb.append("'").append(file.getName()).append("'").append("is empty; ");
					}else{
		                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
		                File temp = new File(realPath, file.getOriginalFilename());
		                FileUtils.copyInputStreamToFile(file.getInputStream(), temp); 
		                sb.append("'").append(file.getOriginalFilename()).append("'").append("(").append(file.getSize()).append("B) has been uploaded").append(";");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		responseMsg = sb.substring(0,sb.lastIndexOf(";")).toString()+". ";
		return responseMsg;
	}
}
