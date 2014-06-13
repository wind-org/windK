package org.wind.k.email;


import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author stephen
 * 
 * @Date 2014-6-5
 *
 */
public class MailService {
	
	private static Logger logger = LoggerFactory.getLogger(MailService.class);
	
	private JavaMailSender mailSender;
	private Template mailTemplate;
	private String attachmentPath;

	public void sendMail(SimpleMailMessage smm)throws MailException{
		if(smm != null){
			mailSender.send(smm);
			if(logger.isInfoEnabled()){
				logger.info("Email has been sent.");
			}
		}
		logger.error("Error in sending email");
	}
	
	public void sendMail(MimeMessage mm)throws MailException{
		if(mm != null){
			mailSender.send(mm);
			if(logger.isInfoEnabled()){
				logger.info("Email has been sent.");
			}
		}
		logger.error("Error in sending email");
	}
	
	public String bulidContentWithTemplate(String content) throws MessagingException{
		try{
			return FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, content);
		}catch(IOException e){
			logger.error("Email template does not exist.");
			throw new MessagingException("Email template does not exist.",e);
		}catch(TemplateException e){
			logger.error("Error in processing template.");
			throw new MessagingException("Error in processing template.",e);
		}
	}
	
	public File getAttachmentByPath() throws MessagingException{
		File attachment = null;
		try {
			if(attachmentPath == null){
				return null;
			}
			Resource resource = new ClassPathResource(attachmentPath);
			attachment =  resource.getFile();
		} catch (IOException e) {
			logger.error("Error in getting attachment.",e);
			throw new MessagingException("Error in getting attachment.",e);
		}
		return attachment;
	}
	
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException{
		freemarkerConfiguration.getTemplate("mailTemplate.ftl", "utf-8");
	}
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setMailTemplate(Template mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
	
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

}
