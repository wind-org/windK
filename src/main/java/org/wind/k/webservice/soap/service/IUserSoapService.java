package org.wind.k.webservice.soap.service;

import javax.jws.WebService;

import org.wind.k.webservice.soap.response.UserResult;

@WebService
public interface IUserSoapService {

	public UserResult getUser(Long id);
	
}
