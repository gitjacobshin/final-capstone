package com.techelevator.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class User {
	private String userName;

	@Size(min=8, message="Password too short, must be at least 8")
	@Pattern.List({
			@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
			@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	private String role;
	private String name;
	private int currentWeight;
	private int desiredWeight;
	private int age;
	private int heightInInches;
	private LocalDate birthdate;
	private String confirmPassword;

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

	public int getHeightInInches() {
		return heightInInches;
	}

	public void setHeightInInches(int getHeightInInches) {
		this.heightInInches = heightInInches;
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
