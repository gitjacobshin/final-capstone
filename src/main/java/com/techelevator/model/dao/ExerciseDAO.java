package com.techelevator.model.dao;

public interface ExerciseDAO {

    public void saveExercise(String exerciseName, int calories, int reps, int sets, String category);

}
