package pl.polsl.bonus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class BonusList {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BONUS_LIST_ID")
	private Integer bonusListId;
	
	@OneToOne
	@JoinColumn(name="NODE_ID")
	private Node node;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="BEGINNING_DATE")
	private Date beginningDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="DESCRIPTION")
	private String description;

	public BonusList() {

	}

	public Integer getBonusListId() {
		return bonusListId;
	}

	public void setBonusListId(Integer bonusListId) {
		this.bonusListId = bonusListId;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

