package com.techelevator.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Progress {

    private int id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate workoutDate;

    private String workoutType;

    private int workoutLength;

    private int weightDesired;

    private int weightAfter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public int getWorkoutLength() {
        return workoutLength;
    }

    public void setWorkoutLength(int workoutLength) {
        this.workoutLength = workoutLength;
    }

    public int getWeightDesired() {
        return weightDesired;
    }

    public void setWeightDesired(int weightDesired) {
        this.weightDesired = weightDesired;
    }

    public int getWeightAfter() {
        return weightAfter;
    }

    public void setWeightAfter(int weightAfter) {
        this.weightAfter = weightAfter;
    }
}
