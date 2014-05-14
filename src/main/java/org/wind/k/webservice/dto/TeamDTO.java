package org.wind.k.webservice.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.wind.k.webservice.soap.base.WSConstants;

import com.google.common.collect.Lists;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-25
 *
 */

@XmlRootElement
@XmlType(name = "Team")
public class TeamDTO {

	private String name;
	private UserDTO master;
	
	
	private List<UserDTO> userList = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDTO getMaster() {
		return master;
	}

	public void setMaster(UserDTO master) {
		this.master = master;
	}

	@XmlElementWrapper( name = "userList")
	@XmlElement(name = "user")
	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
