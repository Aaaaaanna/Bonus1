package pl.polsl.bonus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EmployeeDTO {

	@JsonProperty 
	private Integer employeeId;
	@JsonProperty
	private TeamDTO team;
	@JsonProperty
	private String firstName;
	@JsonProperty
	private String lastName;
	@JsonProperty
	private String phoneNumber;
	@JsonProperty
	private String mobileNumber;
	@JsonProperty
	private String officeAddress;
	@JsonProperty
	private String emailAddress;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public TeamDTO getTeam() {
		return team;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setTeam(TeamDTO team) {
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
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
