package org.wind.k.service.security;

import java.io.Serializable;

public class ShiroUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public Long id;
	public String loginName;
	public String name;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
