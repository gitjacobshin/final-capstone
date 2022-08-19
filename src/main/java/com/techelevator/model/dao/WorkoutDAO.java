package com.techelevator.model.dao;

import com.techelevator.model.dto.Workout;

import java.time.LocalDate;

public interface WorkoutDAO {

    public void saveWorkout(String profile_id, String workoutName);

    public void updateWorkoutName(String profile_id, String workoutName);

    public void updateWorkoutType(String  profile_id, String workoutType);

    public void updateTotalCalories(String profile_id, int totalCalories);

    public void updateDate(String profile_id, LocalDate date);

    public Object getWorkoutByWorkoutName(String userName, String workoutName);

    public void updateWorkout(String userName, Workout workout);

//    public Object createWorkout(String userName, Workout workout);

}
