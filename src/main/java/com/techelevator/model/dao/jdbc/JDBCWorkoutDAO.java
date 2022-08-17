package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.WorkoutDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;

@Component
public class JDBCWorkoutDAO implements WorkoutDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCWorkoutDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveWorkout(String profile_id, String workoutName){

        jdbcTemplate.update("INSERT INTO workout(profile_id, workout_name) VALUES (?, ?)",
                profile_id, workoutName);
    }

    @Override
    public void updateWorkoutName(String profile_id, String workoutName) {
        jdbcTemplate.update("UPDATE workout SET workout_name = ? WHERE profile_id = ?",
                profile_id, workoutName);
    }

    @Override
    public void updateWorkoutType(String profile_id, String workoutType) {
        jdbcTemplate.update("UPDATE workout SET workout_type = ? WHERE profile_id = ?",
                profile_id, workoutType);
    }

    @Override
    public void updateTotalCalories(String profile_id, int totalCalories) {
        jdbcTemplate.update("UPDATE workout SET total_calories = ? WHERE profile_id = ?",
                profile_id, totalCalories);
    }

    @Override
    public void updateDate(String profile_id, LocalDate date) {
        jdbcTemplate.update("UPDATE workout SET date = ? WHERE profile_id = ?",
                profile_id, date);
    }

}
