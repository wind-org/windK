package org.wind.k.webservice.rest;


import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wind.k.entity.User;
import org.wind.k.service.user.IUserService;
import org.wind.k.webservice.dto.UserDTO;

@RestController
public class UserRestController {

	Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	IUserService userService;
	
	/**
	 * it need 'contentNegotiationManager' to control the format of output 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/rest/user/{id}")
	public UserDTO getUser(@PathVariable("id") Long id){
		UserDTO userDTO = null;
		try{
			User user = userService.getUser(id);
			if(user == null){
				String msg = "The user is not exist.(id="+id+")";
				logger.warn(msg);
				throw new RestServiceException(HttpStatus.NOT_FOUND,msg);
			}
			userDTO = new UserDTO();
			BeanUtils.copyProperties(userDTO, user);
			userDTO.setTeamId(user.getTeam().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTO;
	}
}
