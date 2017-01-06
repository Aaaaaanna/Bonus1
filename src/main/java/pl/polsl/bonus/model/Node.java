package pl.polsl.bonus.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Node {
	public Node(){}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NODE_ID")
	private Integer nodeId; 
	
	@OneToOne
	@JoinColumn(name="NODE_ID")
	private Node parentNode;
	
	public Node getParentNode() {
		return parentNode;
	}


	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}


	@OneToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee; 
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


//	@Column(name="OWN_BONUS")
	private Double ownBonus; 
	
	@Column(name="TEAM_BONUS")
	private Double teamBonus; 
	
	@Column(name="LEVEL")
	private Integer level;
	
	@Column(name="STATE")
	private String state; 
	

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

