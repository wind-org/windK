package org.wind.k.webservice.cxf.service;

import javax.jws.WebService;

import org.wind.k.webservice.cxf.response.UserResult;

@WebService
public interface IUserCXFService {

	public UserResult getUser(Long id);
	
}
