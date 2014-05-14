package org.wind.k.webservice.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wind.k.entity.User;
import org.wind.k.service.user.IUserService;
import org.wind.k.webservice.dto.UserDTO;

/**
 * 
 * @author stephen
 * 
 * @Date 2014-5-14
 *
 */
@Path("/user")
public class UserJaxRsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserJaxRsService.class);
	
	@Autowired
	IUserService userService;
	
	@GET
	@Path("/{id}.xml")
	@Produces("application/xml;charset=UTF-8")//response mime type
	public UserDTO getUserAsXml(@PathParam("id") Long id){
		
		UserDTO userDTo = null;
		try{
			User user = userService.getUser(id);
			if(user == null){
				String msg = "The user is not exist.(id="+id+")";
				logger.warn(msg);
				throw buildException(Status.NOT_FOUND,msg);
			}
			userDTo = new UserDTO();
			BeanUtils.copyProperties(userDTo, user);
			userDTo.setTeamId(user.getTeam().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTo;
	}
	
	@GET
	@Path("/{id}.json")
	@Produces("application/json;charset=UTF-8")
	public UserDTO getUserAsJson(@PathParam("id") Long id){
		UserDTO userDTo = null;
		try{
			User user = userService.getUser(id);
			if(user == null){
				String msg = "The user is not exist.(id="+id+")";
				logger.warn(msg);
				throw buildException(Status.NOT_FOUND,msg);
			}
			userDTo = new UserDTO();
			BeanUtils.copyProperties(userDTo, user);
			userDTo.setTeamId(user.getTeam().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTo;
	}
	
	private WebApplicationException buildException(Status status,String message){
		return new WebApplicationException(Response.status(status).entity(message).type("text/plain; charset=UTF-8").build());
	}

}
