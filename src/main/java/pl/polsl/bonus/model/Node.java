package pl.polsl.bonus.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "node")
public class Node implements Serializable{
	public Node(){}
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NODE_ID")
	private Integer nodeId; 
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="PARENT_NODE_ID", referencedColumnName="NODE_ID")
	private Node parentNode;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee; 
	
	@Column(name="OWN_BONUS")
	private Double ownBonus; 
	
	@Column(name="TEAM_BONUS")
	private Double teamBonus; 
	
	@Column(name="LEVEL")
	private Integer level;
	
	@Column(name="STATE")
	private String state; 
	
	
	public Node getParentNode() {
		return parentNode;
	}


	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

	public Double getOwnBonus() {
		return ownBonus;
	}


	public void setOwnBonus(Double ownBonus) {
		this.ownBonus = ownBonus;
	}


	public Double getTeamBonus() {
		return teamBonus;
	}


	public void setTeamBonus(Double teamBonus) {
		this.teamBonus = teamBonus;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getNodeId() {
		return nodeId;
	}


	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}	
}

