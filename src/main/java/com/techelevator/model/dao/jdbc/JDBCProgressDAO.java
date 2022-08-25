package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.ProgressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JDBCProgressDAO implements ProgressDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCProgressDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void addProgress(int userId, LocalDate workoutDate, String workoutType, int workoutLength, int weightAfter, int weightDesired) {
        jdbcTemplate.update("INSERT INTO progress(user_id, date, workout_type, workout_length, weight_after, weight_desired)" +
                                "VALUES (?, ?, ?, ?, ?, ?)",
                                userId, workoutDate, workoutType, workoutLength, weightAfter, weightDesired);
    }

    @Override
    public List getProgressDates(int userId) {
        List<String> progressDates = new ArrayList<>();

        String query = "SELECT date " +
                        "FROM progress " +
                        "WHERE user_id = ? " +
                        "ORDER BY date desc LIMIT 7";

        SqlRowSet queryOutput = jdbcTemplate.queryForRowSet(query, userId);

        while(queryOutput.next()) {
            String date = queryOutput.getDate("date").toString();
            progressDates.add(date);
        }

        Collections.reverse(progressDates);

        return progressDates;
    }

    @Override
    public List getProgressWeights(int userId) {
        List<Integer> progressWeights = new ArrayList<>();

        String query = "SELECT weight_after " +
                        "FROM progress " +
                        "WHERE user_id = ? " +
                        "ORDER BY date desc LIMIT 7";

        SqlRowSet queryOutput = jdbcTemplate.queryForRowSet(query, userId);

        while(queryOutput.next()) {

            int weight = queryOutput.getInt("weight_after");
            progressWeights.add(weight);
        }

        Collections.reverse(progressWeights);

        return progressWeights;
    }

    @Override
    public List getProgressTimes(int userId) {
        List<Integer> progressTimes = new ArrayList<>();

        String query = "SELECT workout_length " +
                        "FROM progress " +
                        "WHERE user_id = ? " +
                        "ORDER BY date desc LIMIT 7";

        SqlRowSet queryOutput = jdbcTemplate.queryForRowSet(query, userId);

        while(queryOutput.next()) {

            int times = queryOutput.getInt("workout_length");
            progressTimes.add(times);
        }

        Collections.reverse(progressTimes);

        return progressTimes;
    }

    @Override
    public List getDesiredWeights(int userId) {
        List<Integer> desiredWeights = new ArrayList<>();

        String query = "SELECT weight_desired " +
                "FROM progress " +
                "WHERE user_id = ? " +
                "ORDER BY date desc LIMIT 7";

        SqlRowSet queryOutput = jdbcTemplate.queryForRowSet(query, userId);

        while(queryOutput.next()) {

            int weights = queryOutput.getInt("weight_desired");
            desiredWeights.add(weights);
        }

        Collections.reverse(desiredWeights);

        return desiredWeights;
    }

    @Override
    public List getWorkoutType(int userId) {
        List<String> workoutType = new ArrayList<>();

        String query = "SELECT workout_type " +
                "FROM progress " +
                "WHERE user_id = ? " +
                "ORDER BY date desc LIMIT 30";

        SqlRowSet queryOutput = jdbcTemplate.queryForRowSet(query, userId);

        while(queryOutput.next()) {

            String type = queryOutput.getString("workout_type");
            workoutType.add(type);
        }

        Collections.reverse(workoutType);

        return workoutType;
    }
}
