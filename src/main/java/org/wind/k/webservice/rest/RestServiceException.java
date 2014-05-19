package org.wind.k.webservice.rest;

import org.springframework.http.HttpStatus;

public class RestServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public RestServiceException(){}
	
	public RestServiceException(HttpStatus status){
		this.status = status;
	}
	
	public RestServiceException(String message){
		super(message);
	}
	
	public RestServiceException(HttpStatus status,String message){
		super(message);
		this.status = status;
	}
}
