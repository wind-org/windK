package org.wind.k.webservice.soap.response;

import javax.xml.bind.annotation.XmlType;

import org.wind.k.webservice.dto.TeamDTO;
import org.wind.k.webservice.soap.base.WSConstants;
import org.wind.k.webservice.soap.base.WSResult;

/***
 * 
 * @author Stephen
 * 
 * @Date 2014-3-26
 *
 */
@XmlType(name = "TeamResult" )
public class TeamResult extends WSResult{
	
	private TeamDTO team;	
	
	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}
}
