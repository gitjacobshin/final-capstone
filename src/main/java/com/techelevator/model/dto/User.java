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
	private int weight;
	private int desiredWeight;
	private int age;
	private int height;
	private LocalDate birthDate;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		if (birthDate.getMonthValue() < LocalDate.now().getMonthValue()
				&& birthDate.getDayOfMonth() < LocalDate.now().getDayOfMonth()
				&&  birthDate.getYear() < LocalDate.now().getYear()) {

			age = LocalDate.now().getYear() - birthDate.getYear();

		} else {
			age = (LocalDate.now().getYear() - birthDate.getYear()) - 1;

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
