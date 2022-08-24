package com.techelevator.model.dao;

import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutDAO {

    public void saveWorkout(String profile_id, String workoutName);

    public void updateWorkoutName(String profile_id, String workoutName);

    public void updateWorkoutType(String  profile_id, String workoutType);

    public void updateTotalCalories(int profile_id, Workout workout);

    public void updateDate(String profile_id, LocalDate date);

    public Object getWorkoutByWorkoutName(String userName, String workoutName);

    public void updateWorkout(String userName, Workout workout);

    public void createWorkout(User user, Workout workout);

    public List<Exercise> showExercises(String workoutName);

    public void deleteWorkout(Workout workout);

    public boolean isWorkoutAvailable( String workoutName, String userName);

    public List<Workout> showDistinctWorkout(String userName);

    public void addWorkout(Workout workout, User user);

}
