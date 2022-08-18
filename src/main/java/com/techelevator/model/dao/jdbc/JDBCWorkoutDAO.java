package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.WorkoutDAO;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
                workoutName, profile_id);
    }

    @Override
    public void updateWorkoutType(String profile_id, String workoutType) {
        jdbcTemplate.update("UPDATE workout SET workout_type = ? WHERE profile_id = ?",
                workoutType, profile_id);
    }



    @Override
    public void updateTotalCalories(String profile_id, int totalCalories) {
        jdbcTemplate.update("UPDATE workout SET total_calories = ? WHERE profile_id = ?",
                totalCalories, profile_id);
    }

    @Override
    public void updateDate(String profile_id, LocalDate date) {
        jdbcTemplate.update("UPDATE workout SET date = ? WHERE profile_id = ?",
                date, profile_id);
    }

    @Override
    public Object getWorkoutByWorkoutName(String userName, String workoutName) {
        String sqlSearchForWorkout ="SELECT w.id, w.profile_id, w.workout_name, w.workout_type, w.total_calories, w.date "+
                "FROM app_user as u " +
                "LEFT JOIN workout w " +
                "ON u.id = w.profile_id "+
                "WHERE UPPER(user_name) = ?" +
                "AND workout_name = ?";

        SqlRowSet workout = jdbcTemplate.queryForRowSet(sqlSearchForWorkout, userName.toUpperCase(), workoutName);
        Workout thisWorkout = null;
        if(workout.next()) {
            thisWorkout = new Workout();
            thisWorkout.setId(workout.getInt("id"));
            thisWorkout.setWorkoutName(workout.getString("workout_name"));
            thisWorkout.setWorkoutType(workout.getString("workout_type"));
            thisWorkout.setTotalCalories(workout.getInt("total_calories"));
//            thisWorkout.setDate(workout.getDate("date"));
        }

        return thisWorkout;
    }

    @Override
    public void updateWorkout(String userName, Workout workout) {
        jdbcTemplate.update("UPDATE workout " +
                        "SET workout_name = ?," +
                        " workout_type = ?, " +
                        " total_calories = ?" +
                         "FROM app_user as u " +
                          "LEFT JOIN workout w " +
                         "ON u.id = w.profile_id "+
                         "WHERE UPPER(user_name) = ?" +
                             "AND workout_name = ?",
                workout.getWorkoutName(), workout.getWorkoutType(),
                workout.getTotalCalories(), userName);
    }

}
