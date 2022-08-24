package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.ExerciseDAO;
import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;
import com.techelevator.services.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JDBCExerciseDAO implements ExerciseDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCExerciseDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createExercise(User user, Workout workout, Exercise exercise) {
        jdbcTemplate.update(
                "INSERT INTO exercise(workout_id, user_id, exercise_name, reps, sets, calories) VALUES (?, ?, ?, ?, ?, ?)",
                workout.getId(), user.getId(), exercise.getExerciseName(), exercise.getReps(), exercise.getSets(), exercise.getCalories()
        );
    }

    @Override
    public Object getExerciseByExerciseName(String workoutName, String exerciseName) {
        String sqlSearchForExercise = "SELECT e.id, e.workout_id, e.exercise_name, e.calories, e.reps, e.sets " +
                "FROM exercise as e " +
                "LEFT JOIN workout w " +
                "ON w.id = e.workout_id " +
                "WHERE workout_name = ? " +
                "AND exercise_name = ?";

        SqlRowSet exercise = jdbcTemplate.queryForRowSet(sqlSearchForExercise, workoutName, exerciseName);
        Exercise thisExercise = null;
        if (exercise.next()) {
            thisExercise = new Exercise();
            thisExercise.setId(exercise.getInt("id"));
            thisExercise.setExerciseName(exercise.getString("exercise_name"));
            thisExercise.setReps(exercise.getInt("reps"));
            thisExercise.setSets(exercise.getInt("sets"));
            thisExercise.setCalories(exercise.getInt("calories"));
//            if (exercise.getDate("date") != null) {
//                thisExercise.setDate(exercise.getDate("date").toLocalDate().atTime());
//            }
        }
        return thisExercise;
    }

    @Override
    public Object getExerciseByExerciseId(String workoutName, int exerciseId) {
        String sqlSearchForExercise ="SELECT e.id, e.workout_id, e.exercise_name, e.calories, e.reps, e.sets " +
                "FROM exercise as e " +
                "LEFT JOIN workout w " +
                "ON w.id = e.workout_id "+
                "AND e.id = ?";

        SqlRowSet exercise = jdbcTemplate.queryForRowSet(sqlSearchForExercise, exerciseId);
        Exercise thisExercise = null;
        if (exercise.next()) {
            thisExercise = new Exercise();
            thisExercise.setId(exercise.getInt("id"));
            thisExercise.setExerciseName(exercise.getString("exercise_name"));
            thisExercise.setReps(exercise.getInt("reps"));
            thisExercise.setSets(exercise.getInt("sets"));
            thisExercise.setCalories(exercise.getInt("calories"));
//            if (exercise.getDate("date") != null) {
//                thisExercise.setDate(exercise.getDate("date").toLocalDate().atTime());
//            }
        }
        return thisExercise;
    }

    @Override
    public List<Exercise> showDistinctExercises(String userName) {
        String sqlSearchForWorkout ="SELECT DISTINCT ON (e.date, e.exercise_name) e.id, e.exercise_name, e.reps, e.sets, e.calories " +
                "FROM exercise as e " +
                "LEFT JOIN app_user u " +
                "ON u.id = e.user_id " +
                "WHERE user_name = ? " +
                "GROUP BY e.exercise_name, e.date, e.id " +
                "ORDER BY e.date DESC";

        SqlRowSet exercise = jdbcTemplate.queryForRowSet(sqlSearchForWorkout, userName);
        List<Exercise> exerciseList = new ArrayList<>();
        while(exercise.next()) {
            Exercise thisExercise = new Exercise();
            thisExercise.setId(exercise.getInt("id"));
            thisExercise.setExerciseName(exercise.getString("exercise_name"));
            thisExercise.setReps(exercise.getInt("reps"));
            thisExercise.setSets(exercise.getInt("sets"));
            thisExercise.setCalories(exercise.getInt("calories"));
//            if(exercise.getDate("date") != null) {
//                thisExercise.setDate(exercise.getDate("date").toLocalDate());
//            }

            exerciseList.add(thisExercise);
        }

        return exerciseList;
    }

    @Override
    public void updateExercise(Workout workout, Exercise exercise) {
        jdbcTemplate.update("UPDATE exercise " +
                        "SET exercise_name = ?, " +
                        "reps = ?, " +
                        "sets = ?, " +
                        "calories = ? " +
                        "WHERE id = ?",
                exercise.getExerciseName(), exercise.getReps(),
                exercise.getSets(), exercise.getCalories(), exercise.getId());
    }

    @Override
    public void deleteExercise(Exercise exercise) {

        jdbcTemplate.update("DELETE FROM exercise " +
                        "WHERE id = ? ",
                        exercise.getId());

    }

    @Override
    public void addExercise(Exercise exercise) {

        jdbcTemplate.update("INSERT INTO exercise " +
                        "WHERE id = ? ",
                        exercise.getId());
    }

    @Override
    public boolean isExerciseNameAvailable(String exerciseName, String workoutName) {
        if (getExerciseByExerciseName(workoutName, exerciseName) == null) {

            return true;
        }
        return false;
    }

}
