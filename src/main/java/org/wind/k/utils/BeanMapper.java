package org.wind.k.utils;

import org.dozer.DozerBeanMapper;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-25
 *
 */
public class BeanMapper {

	private static DozerBeanMapper dozer =  new DozerBeanMapper();
	
	public static <T> T map(Object source, Class<T> destinationClass){
		return dozer.map(source, destinationClass);
	}
	
	public static void copy(Object source,Object destinationClass){
		 dozer.map(source, destinationClass);
	}
	
}
