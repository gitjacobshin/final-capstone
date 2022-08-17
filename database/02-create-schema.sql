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
  heightInInches int,
  birthdate DATE,
  salt varchar(255) NOT NULL,
  goal varchar(300) NOT NULL
);

COMMIT;