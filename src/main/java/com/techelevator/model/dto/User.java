package com.techelevator.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

public class User {

	private String userName;

	@Size(min=8, message="Password too short, must be at least 8")
	@Pattern.List({
			@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
			@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	private String role;

	//@NotBlank(message="Name is required")
	private String name;

	//@NotNull(message="Current weight is required")
	private int currentWeight;

	//@NotNull(message="Desired weight is required")
	private int desiredWeight;

	private int age;

	//@NotNull(message="Height is required (inches)")
	private int height;

//	@NotNull(message="Birthday is required")
//	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate birthdate;

	private String confirmPassword;

	//@NotBlank(message="Please enter a goal")
	private String goal;

	private String updateUsername;

	public String getUserName() {
		return userName;
	}

	/**
	 * @return the role
	 */


	public String getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public int getDesiredWeight() {
		return desiredWeight;
	}

	public void setDesiredWeight(int desiredWeight) {
		this.desiredWeight = desiredWeight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public LocalDate getBirthDate() {
		return birthdate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthdate = birthDate;
	}

	public int getAge() {
		if (birthdate.getMonthValue() < LocalDate.now().getMonthValue()
				&& birthdate.getDayOfMonth() < LocalDate.now().getDayOfMonth()
				&&  birthdate.getYear() < LocalDate.now().getYear()) {

			age = LocalDate.now().getYear() - birthdate.getYear();

		} else {
			age = (LocalDate.now().getYear() - birthdate.getYear()) - 1;

		}
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}


	public void setRole(String role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
