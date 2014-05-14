package org.wind.k.webservice.cxf.response;

import javax.xml.bind.annotation.XmlType;

import org.wind.k.webservice.cxf.base.WSConstants;
import org.wind.k.webservice.cxf.base.WSResult;
import org.wind.k.webservice.cxf.dto.UserDTO;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-26
 *
 */
@XmlType(name = "UserResult")
public class UserResult extends WSResult{
	
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
