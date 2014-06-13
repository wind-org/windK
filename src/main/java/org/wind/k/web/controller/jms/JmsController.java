package org.wind.k.web.controller.jms;

import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wind.k.jms.MessageProducer;

@Controller
public class JmsController {

	@Autowired 
	private MessageProducer messageProducer;
	
	@Resource
	private Destination messageQueue;
	
	@RequestMapping(value = "/jms/sendMsg")
	public String sendEmailMessage(SimpleMailMessage smm,Model model){
		try{
			if(smm != null){
				if(smm.getTo().length>0 && StringUtils.isNotBlank(smm.getFrom()) && StringUtils.isNotBlank(smm.getSubject())){
					smm.setSentDate(new Date());
					messageProducer.produce(smm, messageQueue);
				}
			}
			model.addAttribute("mailMsg", smm);
		}catch(Exception e){
				e.printStackTrace();
		}
		return "showpages/success";
	}
	
}
