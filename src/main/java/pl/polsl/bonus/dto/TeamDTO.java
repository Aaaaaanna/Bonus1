package pl.polsl.bonus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pl.polsl.bonus.model.Employee;

@JsonSerialize
public class TeamDTO {

	@JsonProperty
	private Integer teamId;
	@JsonProperty
	private Employee managerTeamId;
	@JsonProperty
	private String teamName;
	public Integer getTeamId() {
		return teamId;
	}
	public Employee getManagerTeamId() {
		return managerTeamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public void setManagerTeamId(Employee managerTeamId) {
		this.managerTeamId = managerTeamId;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
}
