package com.techelevator.model.dao.jdbc;

import javax.sql.DataSource;

import com.techelevator.model.dao.UserDAO;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.dto.User;
import com.techelevator.services.security.PasswordHasher;

import java.time.LocalDate;

@Component
public class JDBCUserDAO implements UserDAO
{

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public void saveUser(String userName, String password) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO app_user(user_name, password, salt) VALUES (?, ?, ?)",
				userName, hashedPassword, saltString);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updateUsername(String updateUsername, String userName) {
		jdbcTemplate.update("UPDATE app_user SET user_name = ? WHERE user_name = ?", updateUsername, userName);
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE user_name = ?", password, userName);
	}

	@Override
	public void updateRole(String userName, String role) {
		jdbcTemplate.update("UPDATE app_user SET role = ? WHERE user_name = ?", role, userName);
	}

	@Override
	public void updateName(String userName, String name) {
		jdbcTemplate.update("UPDATE app_user SET name = ? WHERE user_name = ?", name, userName);
	}

	@Override
	public void updateBirthday(String userName, LocalDate birthdate) {
		jdbcTemplate.update("UPDATE app_user SET birthdate = ? WHERE user_name = ?", birthdate.toString(), userName);
	}

	@Override
	public void updateHeight(String userName, int height) {
		jdbcTemplate.update("UPDATE app_user SET height = ? WHERE user_name = ?", height, userName);
	}

	@Override
	public void updateCurrentWeight(String userName, int currentWeight) {
		jdbcTemplate.update("UPDATE app_user SET current_weight = ? WHERE user_name = ?", currentWeight, userName);
	}

	@Override
	public void updateDesiredWeight(String userName, int desiredWeight) {
		jdbcTemplate.update("UPDATE app_user SET desired_weight = ? WHERE user_name = ?", desiredWeight, userName);
	}

	@Override
	public void updateGoal(String userName, String goal) {
		jdbcTemplate.update("UPDATE app_user SET goal = ? WHERE user_name = ?", goal, userName);

	}


	@Override
	public Object getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setName(user.getString("name"));
			thisUser.setHeight(user.getInt("height"));
			thisUser.setCurrentWeight(user.getInt("current_weight"));
			thisUser.setDesiredWeight(user.getInt("desired_weight"));
			if(user.getDate("birthdate") != null) {
			thisUser.setBirthdate(user.getDate("birthdate").toLocalDate());
			}
			thisUser.setGoal(user.getString("goal"));
			thisUser.setId(user.getInt("id"));
		}

		return thisUser;
	}

	@Override
	public boolean isUserNameAvailable(String userName) {
		if (getUserByUserName(userName) == null) {

			return true;
		}

		return false;
	}

	@Override
	public void updateProfile(String userName, User user) {
		jdbcTemplate.update("UPDATE app_user " +
				"SET name = ?," +
				" height = ?, " +
				" current_weight = ?, " +
				" desired_weight = ?, " +
				" goal = ?, " +
				" birthdate =  ?, " +
				" age = ? " +
				" WHERE user_name = ?",
				user.getName(), user.getHeight(), user.getCurrentWeight(), user.getDesiredWeight(), user.getGoal(), user.getBirthdate(), user.getAge(), userName);
	}

	@Override
	public void updateProfilePic(String userName, String profilePic) {


	}

}
