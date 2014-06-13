package org.wind.k.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.wind.k.email.MailService;

public class MessageConsumer implements MessageListener{

	private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	
	@Autowired(required = false)
	MailService mailService;
	
	
	@Override
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			
//			SimpleMailMessage smm = new SimpleMailMessage();
//			smm.setFrom(mapMessage.getString("from"));
//			String sentDateStr = mapMessage.getString("sentDate");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:hh");
//			if(sentDateStr != null){
//				smm.setSentDate(sdf.parse(sentDateStr));
//			}
//			smm.setSubject(mapMessage.getString("subject"));
//			smm.setText(mapMessage.getString("text"));
//			String[] toList =(String [])mapMessage.getObject("to");
//			
//			if(toList != null && toList.length>0){
//				smm.setTo(toList);
//				mailService.sendMail(smm);
//			}
			
			if(objMessage != null && objMessage.getObject() != null){
				SimpleMailMessage smm = (SimpleMailMessage) objMessage.getObject();
				mailService.sendMail(smm);
				System.out.println("The mail has been sent");
			}
			
			
		}catch(MailSendException me){
			logger.error("error in sending email");
			me.printStackTrace();
		}catch (Exception e) {
			logger.error("error in consumming message");
			e.printStackTrace();
		}
	}

}
