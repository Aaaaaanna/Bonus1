

package pl.polsl.bonus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="team")
public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TEAM_ID")
	private Integer teamId;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TEAM_MANAGER_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Employee teamManager;

	@Column(name = "TEAM_NAME")
	private String teamName;

	
	public Team() {
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Employee getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(Employee teamManager) {
		this.teamManager = teamManager;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}