package pl.polsl.bonus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NodeDTO {

	@JsonProperty
	private Integer nodeId; 
	
	@JsonProperty
	private NodeDTO parentNode; 
	
	@JsonProperty
	private EmployeeDTO employee;
	
	@JsonProperty
	private Double ownBonus; 
	
	@JsonProperty
	private Double teamBonus; 

	@JsonProperty
	private Integer level; 
	
	@JsonProperty
	private String state;

	public Integer getNodeId() {
		return nodeId;
	}

	public NodeDTO getParentNode() {
		return parentNode;
	}

	public Double getOwnBonus() {
		return ownBonus;
	}

	public Double getTeamBonus() {
		return teamBonus;
	}

	public Integer getLevel() {
		return level;
	}

	public String getState() {
		return state;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public void setParentNode(NodeDTO parentNode) {
		this.parentNode = parentNode;
	}

	public void setOwnBonus(Double ownBonus) {
		this.ownBonus = ownBonus;
	}

	public void setTeamBonus(Double teamBonus) {
		this.teamBonus = teamBonus;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setState(String state) {
		this.state = state;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	} 
	
	
}
