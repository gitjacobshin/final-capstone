package com.techelevator.model.dao;

import java.time.LocalDate;
import java.util.List;

public interface ProgressDAO {

    public void addProgress(int userId, LocalDate date, String workoutType, int workoutLength, int weightAfter, int weightDesired);

    public List getProgressDates(int userId);

    public List getProgressWeights(int userId);

    public List getProgressTimes(int userId);

    public List getDesiredWeights(int userId);

    public List getWorkoutType(int userId);
}
