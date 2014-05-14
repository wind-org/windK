package org.wind.k.webservice.cxf.service;

import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.wind.k.entity.User;
import org.wind.k.repository.mybatis.UserMyBatisDao;
import org.wind.k.utils.BeanMapper;
import org.wind.k.webservice.cxf.base.WSCXFSevice;
import org.wind.k.webservice.cxf.base.WSConstants;
import org.wind.k.webservice.cxf.dto.UserDTO;
import org.wind.k.webservice.cxf.response.UserResult;

//the annotation @WebService let us know which interface we need to create WSDL
@WebService(endpointInterface = "org.wind.k.webservice.cxf.service.IUserCXFService",serviceName="userCXFService")
public class UserCXFServiceImpl extends WSCXFSevice implements IUserCXFService {
	
	@Autowired
	private PropertiesConfiguration webServiceConfig;
	
	@Autowired
	private UserMyBatisDao userDao;
	
	public UserCXFServiceImpl(PropertiesConfiguration webServiceConfig){
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
