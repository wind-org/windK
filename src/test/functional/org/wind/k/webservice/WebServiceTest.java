package org.wind.k.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.wind.k.webservice.dto.UserDTO;
import org.wind.k.webservice.soap.service.IUserSoapService;

/**
 * 
 * @author stephen
 * 
 * @Date 2014-5-19
 *
 */
public class WebServiceTest {
	
	
	@Test
	public void testCXFSoapService(){
		UserDTO user = null;
		JaxWsProxyFactoryBean webService = new JaxWsProxyFactoryBean();
		webService.setServiceClass(IUserSoapService.class);
		webService.setAddress("http://localhost:8080/windK/cxf/soap/userSoapService?wsdl");
		IUserSoapService userSoapService = (IUserSoapService)webService.create();
		if(userSoapService != null){
			user = userSoapService.getUser(1L).getUser();
			if(user!= null){
				System.out.println("---------------The result 1------------------");
				System.out.println(user.getLoginName());
				System.out.println(user.getName());
				System.out.println(user.getEmail());
			}
		}
		Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	
	@Test
	public void testCXFSoapService2(){
		UserDTO user = null;
		ApplicationContext clientContext = new ClassPathXmlApplicationContext(  
			      new String[]{"webservice/soap-client.xml"});  
		IUserSoapService clientService = (IUserSoapService) clientContext.getBean("userSoapServiceClient"); 
		
		if(clientService != null){
			user = clientService.getUser(1L).getUser();
			if(user!= null){
				System.out.println("---------------The result 2------------------");
				System.out.println(user.getLoginName());
				System.out.println(user.getName());
				System.out.println(user.getEmail());
			}
		}
		Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	
	@Test
	public void testCXFRestService4Xml(){
		 RestTemplate restTemplate = new RestTemplate();
		 String requestUrl = "http://localhost:8080/windK/cxf/jaxrs/user/1.xml";
		 UserDTO user = restTemplate.getForObject(requestUrl, UserDTO.class, 1L);
		 Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	
	@Test
	public void testCXFRestService4Json(){
		 RestTemplate restTemplate = new RestTemplate();
		 String requestUrl = "http://localhost:8080/windK/cxf/jaxrs/user/1.json";
		 UserDTO user = restTemplate.getForObject(requestUrl, UserDTO.class, 1L);
		 Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	
	@Test
	public void testSpringMVCRestService4Xml(){
		RestTemplate restTemplate = new RestTemplate();
		 String requestUrl = "http://localhost:8080/windK/rest/user/1.xml";
		 UserDTO user = restTemplate.getForObject(requestUrl, UserDTO.class, 1L);
		 Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	
	@Test
	public void testSpringMVCRestService4Json(){
		RestTemplate restTemplate = new RestTemplate();
		 String requestUrl = "http://localhost:8080/windK/rest/user/1.json";
		 UserDTO user = restTemplate.getForObject(requestUrl, UserDTO.class, 1L);
		 Assert.assertTrue("admin".equals(user.getLoginName()));
	}
	

	
}
