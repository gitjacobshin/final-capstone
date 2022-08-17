package com.techelevator.model.dao;

import java.time.LocalDate;

public interface WorkoutDAO {

    public void saveWorkout(String profile_id, String workoutName);

    public void updateWorkoutName(String profile_id, String workoutName);

    public void updateWorkoutType(String  profile_id, String workoutType);

    public void updateTotalCalories(String profile_id, int totalCalories);

    public void updateDate(String profile_id, LocalDate date);

}
