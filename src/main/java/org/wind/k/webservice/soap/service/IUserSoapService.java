package org.wind.k.webservice.soap.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.wind.k.webservice.soap.response.UserResult;
// name  to define <wsdl:portType> in wsdl
@WebService(name = "userSoapService")
public interface IUserSoapService {

	public UserResult getUser(@WebParam(name = "id")Long id);
	
}
