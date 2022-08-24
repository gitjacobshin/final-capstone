package com.techelevator.model.dto;

//import jdk.vm.ci.meta.Local;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exercise {

    private int id;

    private String exerciseName;

//    @Pattern(regexp=".*[0-9].*", message="Must contain only numbers")
    @NotNull
    private int reps;

//    @Pattern(regexp=".*[0-9].*", message="Must contain only numbers")
    @NotNull
    private int sets;

//    @Pattern(regexp=".*[0-9].*", message="Must contain only numbers")
    @NotNull
    private int calories;

    private LocalDateTime date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
