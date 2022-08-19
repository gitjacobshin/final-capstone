package com.techelevator.model.dao;

public interface ExerciseDAO {

    public void saveExercise(String name, int calories, int reps, int sets, String category);

}
