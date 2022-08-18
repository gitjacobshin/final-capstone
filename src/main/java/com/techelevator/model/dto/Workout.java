package com.techelevator.model.dto;

import java.time.LocalDate;

public class Workout {

    private String profile_id;
    private String workoutName;
    private String workoutType;
    private int workoutReps;
    private int workoutSets;
    private int workoutLength;
    private int totalCalories;
    private LocalDate date;

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }


    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public int getWorkoutReps() {
        return workoutReps;
    }

    public void setWorkoutReps(int workoutReps) {
        this.workoutReps = workoutReps;
    }

    public int getWorkoutSets() {
        return workoutSets;
    }

    public void setWorkoutSets(int workoutSets) {
        this.workoutSets = workoutSets;
    }

    public int getWorkoutLength() {
        return workoutLength;
    }

    public void setWorkoutLength(int workoutLength) {
        this.workoutLength = workoutLength;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
