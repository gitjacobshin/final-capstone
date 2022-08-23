-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  name varchar(32),
  current_weight int,
  desired_weight int,
  age int,
  height int,
  birthdate date,
  salt varchar(255) NOT NULL,
  goal varchar(300),
  profile_pic varchar(300)
);

DROP TABLE IF EXISTS workout;

CREATE TABLE workout (
                          id SERIAL PRIMARY KEY,
                          profile_id int NOT NULL,
                          workout_name varchar(32) NOT NULL,
                          workout_type varchar(32),
                          total_calories int,
                          date date
);

DROP TABLE IF EXISTS exercise;

CREATE TABLE exercise (
                          id SERIAL PRIMARY KEY,
                          workout_id int NOT NULL,
                          exercise_name varchar(32) NOT NULL,
                          calories int,
                          reps int,
                          sets int
);


COMMIT;