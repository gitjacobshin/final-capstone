package com.techelevator.model.dao;

import java.time.LocalDate;

public interface UserDAO {

	public void saveUser(String userName, String password);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updateUsername(String updateUsername, String userName);

	public void updatePassword(String userName, String password);

	public void updateRole(String userName, String role);

	public void updateBirthday(String userName, LocalDate birthdate);

	public void updateHeight(String userName, int height);

	public void updateCurrentWeight(String userName, int currentWeight);

	public void updateDesiredWeight(String userName, int desiredWeight);

	public void updateGoal(String userName, String goal);

	public Object getUserByUserName(String userName);
}
