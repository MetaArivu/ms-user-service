package com.user.adapter.dto;

import java.util.Date;

public class UserCreatedEvent {

	private String id;
	private String loginId;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private Integer age;
	private Date dob;

	public UserCreatedEvent(String id, String loginId, String password, String firstName, String middleName,
			String lastName, String email, Integer age, Date dob) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.dob = dob;
	}

	public static class Builder {

		private String id;
		private String loginId;
		private String password;
		private String firstName;
		private String middleName;
		private String lastName;
		private String email;
		private Integer age;
		private Date dob;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder loginId(String loginId) {
			this.loginId = loginId;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder age(Integer age) {
			this.age = age;
			return this;
		}

		public Builder dob(Date dob) {
			this.dob = dob;
			return this;
		}

		public UserCreatedEvent build() {
			return new UserCreatedEvent(id, loginId, password, firstName, middleName, lastName, email, age, dob);
		}

	}

	public String getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Integer getAge() {
		return age;
	}

	public Date getDob() {
		return dob;
	}

}
