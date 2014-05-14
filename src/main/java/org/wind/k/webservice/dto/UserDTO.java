package org.wind.k.webservice.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.wind.k.webservice.soap.base.WSConstants;

/**
 * 
 * @author Stephen
 * 
 * @Date 2014-3-25
 *
 */

@XmlRootElement
@XmlType(name="User")
public class UserDTO {

	private Long id;
	private String loginName;
	private String name;
	private String email;
	private Long teamId;

	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		id = value;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String value) {
		loginName = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		email = value;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
