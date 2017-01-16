package pl.polsl.bonus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TeamDTO {

	@JsonProperty
	private Integer teamId;
	@JsonProperty
	private EmployeeDTO teamManager;
	@JsonProperty
	private String teamName;
	
	
	public Integer getTeamId() {
		return teamId;
	}
	public EmployeeDTO getTeamManager() {
		return teamManager;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public void setTeamManager(EmployeeDTO teamManagaer) {
		this.teamManager = teamManagaer;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
}
