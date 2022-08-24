package com.techelevator.model.dao;

import com.techelevator.model.dto.Exercise;
import com.techelevator.model.dto.User;
import com.techelevator.model.dto.Workout;

import java.util.List;

public interface ExerciseDAO {

    public void createExercise(User user, Workout workout, Exercise exercise);

    public Object getExerciseByExerciseName(String userName, String workoutName);

    public void updateExercise(Workout workout, Exercise exercise);

    public void deleteExercise(Exercise exercise);

    public void addExercise(Exercise exercise);

    public boolean isExerciseNameAvailable(String exerciseName, String workoutName);

    public Object getExerciseByExerciseId(String workoutName, int exerciseId);

    public List<Exercise> showDistinctExercises(String userName);
}
