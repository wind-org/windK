package org.wind.k.jms;

import java.util.Date;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

public class JmsTest {
	
	@Test
	public void testQueue(){
		
		ApplicationContext clientContext = new ClassPathXmlApplicationContext(  
			      new String[]{"jms/jms-context.xml"});  
		ActiveMQQueue queue = (ActiveMQQueue) clientContext.getBean("messageQueue"); 
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("from");
		smm.setSentDate(new Date());
		smm.setSubject("subject");
		smm.setReplyTo("replyTo");
		smm.setText("text");
		
		MessageProducer mp = new MessageProducer();
		mp.produce(smm, queue);
	}
}
