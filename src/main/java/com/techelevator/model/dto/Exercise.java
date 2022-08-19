package com.techelevator.model.dto;

import javax.validation.constraints.Pattern;

public class Exercise {

    private int id;
    private String exerciseName;

    @Pattern(regexp=".*[0-9].*", message="Must contain only numbers")
    private int reps;
    private int sets;

    @Pattern(regexp=".*[0-9].*", message="Must contain only numbers")
    private int calories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
