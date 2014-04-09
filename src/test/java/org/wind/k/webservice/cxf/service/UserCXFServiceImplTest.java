package org.wind.k.webservice.cxf.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class UserCXFServiceImplTest {
	
	public static UserCXFServiceImplTest ust = new UserCXFServiceImplTest();
	private IUserCXFService client;
	
	private UserCXFServiceImplTest(){
		ApplicationContext clientContext = new ClassPathXmlApplicationContext(  
                new String[]{"org/wind/k/webservice/cxf/client/cxf-client.xml"});  
        client = (IUserCXFService) clientContext.getBean("userServiceClient"); 
	}
	

	public static void main(String[] args) {
		
//		JaxWsProxyFactoryBean webService = new JaxWsProxyFactoryBean();
//		webService.setServiceClass(IUserCXFService.class);
//		webService.setAddress("http://localhost:8080/windK/cxf/userCXFService");
//		IUserCXFService userCXFService = (IUserCXFService)webService.create();
//		System.out.println("The result"+userCXFService.checkUser("test"));
	}
	
}
