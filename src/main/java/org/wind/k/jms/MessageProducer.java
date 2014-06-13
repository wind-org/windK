package org.wind.k.jms;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.SimpleMailMessage;

public class MessageProducer {
	
	private JmsTemplate jmsTemplate;
	private Destination jmsQueue;
	
	
	/**
	 * MapMessage: The names are String objects, and the values are primitive data types in the Java programming language
	 * ObjectMessage:An ObjectMessage object is used to send a message that contains a serializable object in the Java programming language
	 * 
	 * @param smm
	 * @param destination
	 */
	public void produce(final SimpleMailMessage smm,Destination destination){
		
		jmsTemplate.send(destination,new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				
//				String sentDateStr = "";
//				MapMessage message = session.createMapMessage();
//				message.setString("from", smm.getFrom());
//				message.setString("subject", smm.getSubject());
//				if(smm.getTo() != null && smm.getTo().length>0){
//					message.setObject("to", smm.getTo());
//				}
//				message.setString("text", smm.getText());
//				Date sentDate = smm.getSentDate();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:hh");
//				if(sentDate != null){
//					sentDateStr = sdf.format(sentDate);
//					message.setString("sentDate",sentDateStr);
//				}
				
				ObjectMessage message = session.createObjectMessage();
				if(smm.getTo() != null && smm.getTo().length>0){
					message.setObject(smm);
				}
				return message;
			}
		});
	}
	

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setJmsQueue(Destination jmsQueue) {
		this.jmsQueue = jmsQueue;
	}
	
}
