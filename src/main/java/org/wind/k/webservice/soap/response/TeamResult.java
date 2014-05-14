package org.wind.k.webservice.cxf.response;

import javax.xml.bind.annotation.XmlType;

import org.wind.k.webservice.cxf.base.WSConstants;
import org.wind.k.webservice.cxf.base.WSResult;
import org.wind.k.webservice.cxf.dto.TeamDTO;

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
