package org.wind.k.webservice.soap.service;

import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.wind.k.entity.User;
import org.wind.k.repository.mybatis.UserMyBatisDao;
import org.wind.k.utils.BeanMapper;
import org.wind.k.webservice.dto.UserDTO;
import org.wind.k.webservice.soap.base.WSSoapService;
import org.wind.k.webservice.soap.response.UserResult;

//the annotation @WebService let us know which interface we need to create WSDL
@WebService(endpointInterface = "org.wind.k.webservice.soap.service.IUserSoapService",serviceName="userSoapService")
public class UserSoapServiceImpl extends WSSoapService implements IUserSoapService {
	
	@Autowired
	private PropertiesConfiguration webServiceConfig;
	
	@Autowired
	private UserMyBatisDao userDao;
	
	public UserSoapServiceImpl(PropertiesConfiguration webServiceConfig){
		this.webServiceConfig = webServiceConfig;
	}
	
	public UserResult getUser(Long id){
		UserResult userResult = new UserResult();
		User user = null;
		UserDTO userDTO = null;
		UserDTO userDTO2 = null;
		try{
			user = userDao.get(id);
			//System.out.println("config:"+webServiceConfig.getProperty("webservice.test"));
			//dozer
			userDTO = BeanMapper.map(user, UserDTO.class);
			//BeanUtils
			userDTO2 = new UserDTO();
			BeanUtils.copyProperties(userDTO2, user);
		}catch(Exception e){
			e.printStackTrace();
			this.handleException(userResult, e, "error in getting UserResult.");
		}
		if(user != null){
			userResult.setUser(userDTO);
		}
		return userResult;
	}
	
	
}
