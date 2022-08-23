package com.techelevator.model.dao;

import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.Workout;

public interface ExerciseDAO {

    public void saveExercise(String name, int calories, int reps, int sets, String category);

    public void createExercise(Workout workout, Exercise exercise);

    public Object getExerciseByExerciseName(String userName, String workoutName);

    public void updateExercise(Workout workout, Exercise exercise);

    public void deleteExercise(Workout workout, Exercise exercise);

    public boolean isExerciseNameAvailable(String exerciseName, String workoutName);

    public Object getExerciseByExerciseId(String workoutName, int exerciseId);
}
