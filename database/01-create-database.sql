-- **************************************************************
-- This script destroys and re-creates the database
--
-- It also drops the users associated with the database
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'tech_fitness';

DROP DATABASE IF EXISTS tech_fitness;
CREATE DATABASE tech_fitness;

DROP USER tech_fitness_owner;
DROP USER tech_fitness_appuser;
