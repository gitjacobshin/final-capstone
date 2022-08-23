package com.techelevator.model.dao;

import java.time.LocalDate;

public interface ProgressDAO {

    public void addProgress(int userId, LocalDate date, String workoutType, int workoutLength, int weightAfter);

}
