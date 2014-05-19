package org.wind.k.webservice.soap.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSoapService {
	
	private static Logger logger = LoggerFactory.getLogger(BaseSoapService.class); 
	
	protected <T extends WSResult> T handleException(T result,Exception e,String massage){
		logger.error(massage, e);
		result.setErrorMsg(WSResult.SYS_ERROR, WSResult.SYS_ERROR_MSG);
		return result;
	}
}
