package org.wind.k.webservice.cxf.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.wind.k.webservice.cxf.base.WSConstants;

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
	private TeamDTO team;

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

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
