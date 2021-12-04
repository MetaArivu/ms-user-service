package com.user.adapter.entities;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.adapter.dto.UserCreatedEvent;

@Document(value = "user_details")
public class UserDetails extends BaseEntity {

	@Indexed
	private String loginId;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private Integer age;
	private Date dob;

	public UserDetails() {
		super();
		this.active = true;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public static String invalidMsg() {
		return "Please enter valid User data (LoginId/Name)";
	}

	@Override
	public String toString() {
		return id + "|" + loginId + "|" + firstName + " " + middleName + " " + lastName + "|" + email + "|" + age + "|" + dob;
	}

	@JsonIgnore
	@Override
	public boolean isValid() {
		return true;
	}
	
	public String token() {
		return this.id+"|"+this.loginId;
	}
	
	public UserCreatedEvent userCreatedEvent() {
		return new UserCreatedEvent.Builder()
				.age(age)
				.dob(dob)
				.email(email)
				.firstName(firstName)
				.id(getId())
				.lastName(lastName)
				.loginId(loginId)
				.middleName(middleName)
				.password(password)
				.build();
	}

}
