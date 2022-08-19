package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.ExerciseDAO;
import com.techelevator.services.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCExerciseDAO implements ExerciseDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCExerciseDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveExercise(String exerciseName, int calories, int reps, int sets, String category) {

        jdbcTemplate.update("INSERT INTO exercise(name, calories, reps, sets, category) VALUES (?, ?, ?, ?, ?)",
                exerciseName, calories, reps, sets, category);
    }


}
