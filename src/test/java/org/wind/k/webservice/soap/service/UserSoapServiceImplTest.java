package org.wind.k.webservice.soap.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wind.k.webservice.soap.service.IUserSoapService;


public class UserSoapServiceImplTest {
	
	public static UserSoapServiceImplTest ust = new UserSoapServiceImplTest();
	private IUserSoapService client;
	
	private UserSoapServiceImplTest(){
		ApplicationContext clientContext = new ClassPathXmlApplicationContext(  
                new String[]{"org/wind/k/webservice/soap/client/soap-client.xml"});  
        client = (IUserSoapService) clientContext.getBean("userServiceClient"); 
	}
	

	public static void main(String[] args) {
		
//		JaxWsProxyFactoryBean webService = new JaxWsProxyFactoryBean();
//		webService.setServiceClass(IUserCXFService.class);
//		webService.setAddress("http://localhost:8080/windK/cxf/userCXFService");
//		IUserCXFService userCXFService = (IUserCXFService)webService.create();
//		System.out.println("The result"+userCXFService.checkUser("test"));
	}
	
}
