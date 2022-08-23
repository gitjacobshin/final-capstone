package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.ProgressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;

@Component
public class JDBCProgressDAO implements ProgressDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCProgressDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void addProgress(int userId, LocalDate workoutDate, String workoutType, int workoutLength, int weightAfter) {
        jdbcTemplate.update("INSERT INTO progress(user_id, date, workout_type, workout_length, weight_after)" +
                                "VALUES (?, ?, ?, ?, ?)",
                                userId, workoutDate, workoutType, workoutLength, weightAfter);
    }
}
