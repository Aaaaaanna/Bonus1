package pl.polsl.bonus.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class BonusListDTO {

	@JsonProperty
	private Integer bonusListId;
	
	@JsonProperty
	private NodeDTO node; 
	
	@JsonProperty
	private String state; 
	
	@JsonProperty
	private Date beginningDate; 
	
	@JsonProperty
	private Date endDate;

	@JsonProperty
	private Double budget;
	
	@JsonProperty
	private String description;
	
	public Integer getBonusListId() {
		return bonusListId;
	}

	public NodeDTO getNode() {
		return node;
	}

	public String getState() {
		return state;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setBonusListId(Integer bonusListId) {
		this.bonusListId = bonusListId;
	}

	public void setNode(NodeDTO node) {
		this.node = node;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	
	
}
