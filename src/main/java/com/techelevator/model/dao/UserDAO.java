package com.techelevator.model.dao;

public interface UserDAO {

	public void saveUser(String userName, String password);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updateUsername(String userName, String password);

	public void updatePassword(String userName, String password);

	public void updateRole(String userName, String role);

	public void updateAge(String userName, int age);


	public Object getUserByUserName(String userName);



}
