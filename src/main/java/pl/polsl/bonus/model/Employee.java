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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="employee")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Employee() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMPLOYEE_ID")
	private Integer employeeId;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TEAM_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	//@JsonBackReference
	private Team team;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PHONE_NO")
	private String phoneNumber;
	
	@Column(name = "MOBILE_NO")
	private String mobileNumber;
	
	@Column(name = "OFFICE_ADDRESS")
	private String officeAddress;
	
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	/*
	private String login;
	private String password;
*/
	// getters
	public Integer getEmployeeId() {
		return this.employeeId;
	}
	
	public Team getTeam() {
		return this.team;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public String getOfficeAddress() {
		return this.officeAddress;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}
/*
	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}
*/
	// setters

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setMoblieNumber(String moblieNumber) {
		this.mobileNumber = moblieNumber;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
/*
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/
}