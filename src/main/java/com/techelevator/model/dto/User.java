package com.techelevator.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class User {

	private int id;
	private String userName;

	@Size(min=8, message="Password too short, must be at least 8")
	@Pattern.List({
			@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
			@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	private String role = "user";

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
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthdate;

	private String confirmPassword;

	//@NotBlank(message="Please enter a goal")
	private String goal;

	private String profilePic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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


	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
