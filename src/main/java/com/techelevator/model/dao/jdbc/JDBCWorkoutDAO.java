package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.WorkoutDAO;
import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public void updateTotalCalories(int profile_id, Workout workout) {
        jdbcTemplate.update("UPDATE workout " +
                        "SET total_calories = (" +
                        "SELECT sum(e.calories) as total_calories " +
                        "FROM exercise as e " +
                        "LEFT JOIN workout w ON e.workout_id = w.id " +
                        "WHERE profile_id = ? AND workout_name = ?" +
                        ") " +
                        "WHERE profile_id = ? AND workout_name = ?",
                profile_id, workout.getWorkoutName(), profile_id, workout.getWorkoutName());
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
                "WHERE UPPER(user_name) = ? " +
                "AND workout_name = ?";

        SqlRowSet workout = jdbcTemplate.queryForRowSet(sqlSearchForWorkout, userName.toUpperCase(), workoutName);
        Workout thisWorkout = null;
        if(workout.next()) {
            thisWorkout = new Workout();
            thisWorkout.setId(workout.getInt("id"));
            thisWorkout.setWorkoutName(workout.getString("workout_name"));
            thisWorkout.setWorkoutType(workout.getString("workout_type"));
            thisWorkout.setTotalCalories(workout.getInt("total_calories"));
            if(workout.getDate("date") != null) {
                thisWorkout.setDate(workout.getDate("date").toLocalDate());
            }
        }

        return thisWorkout;
    }

    @Override
    public void updateWorkout(String userName, Workout workout) {
        jdbcTemplate.update("UPDATE workout " +
                        "SET workout_name = ?, " +
                        "workout_type = ?, " +
                        "date = ? " +
                        "FROM workout as w " +
                        "LEFT JOIN app_user u " +
                        "ON u.id = w.profile_id "+
                        "WHERE UPPER(user_name) = ? " +
                        "AND w.workout_name = ?",
                workout.getWorkoutName(), workout.getWorkoutType(), workout.getDate(),
                userName, workout.getWorkoutName());
    }

    @Override
    public void createWorkout(User user, Workout workout) {
        jdbcTemplate.update(
                "INSERT INTO workout(profile_id, workout_name, workout_type, date) VALUES (?, ?, ?, ?)",
                user.getId() , workout.getWorkoutName(), workout.getWorkoutType(), workout.getDate());
    }

    @Override
    public List<Exercise> showExercises(String workoutName) {
        String sqlSearchForWorkout ="SELECT e.id, e.exercise_name, e.reps, e.sets, e.calories "+
                "FROM exercise as e " +
                "LEFT JOIN workout w " +
                "ON w.id = e.workout_id "+
                "WHERE workout_name = ?";

        SqlRowSet exercise = jdbcTemplate.queryForRowSet(sqlSearchForWorkout, workoutName);
        List<Exercise> thisExerciseList = new ArrayList<>();
        while(exercise.next()) {
            Exercise thisExercise = new Exercise();
            thisExercise.setId(exercise.getInt("id"));
            thisExercise.setExerciseName(exercise.getString("exercise_name"));
            thisExercise.setReps(exercise.getInt("reps"));
            thisExercise.setSets(exercise.getInt("sets"));
            thisExercise.setCalories(exercise.getInt("calories"));

            thisExerciseList.add(thisExercise);
        }

        return thisExerciseList;
    }

    @Override
    public List<Workout> showDistinctWorkout(String userName) {
        String sqlSearchForWorkout ="SELECT DISTINCT ON (w.date, w.workout_name) w.id, workout_name, w.workout_type, w.total_calories, w.date " +
                "FROM workout as w " +
                "LEFT JOIN app_user u " +
                "ON u.id = w.profile_id " +
                "WHERE user_name = ? " +
                "GROUP BY w.workout_name, w.date, w.id " +
                "ORDER BY w.date DESC";

        SqlRowSet workout = jdbcTemplate.queryForRowSet(sqlSearchForWorkout, userName);
        List<Workout> workoutList = new ArrayList<>();
        while(workout.next()) {
            Workout thisWorkout = new Workout();
            thisWorkout.setId(workout.getInt("id"));
            thisWorkout.setWorkoutName(workout.getString("workout_name"));
            thisWorkout.setWorkoutType(workout.getString("workout_type"));
            thisWorkout.setTotalCalories(workout.getInt("total_calories"));
            if(workout.getDate("date") != null) {
                thisWorkout.setDate(workout.getDate("date").toLocalDate());
            }

            workoutList.add(thisWorkout);
        }

        return workoutList;
    }

    @Override
    public void deleteWorkout(Workout workout) {
//        jdbcTemplate.update("DELETE FROM exercise " +
//                        "WHERE workout_id = ?",
//                workout.getId());

        jdbcTemplate.update("DELETE FROM workout " +
                        "WHERE id = ? ",
                workout.getId());
    }


    @Override
    public boolean isWorkoutAvailable(String workoutName, String userName) {
        if (getWorkoutByWorkoutName(userName, workoutName) == null) {

            return true;
        }
        return false;
    }
}