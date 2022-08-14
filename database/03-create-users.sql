-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER brewery_finder_owner WITH PASSWORD 'tech_fitness_owner1';

GRANT ALL 
ON ALL TABLES IN SCHEMA public
TO tech_fitness_owner;

GRANT ALL 
ON ALL SEQUENCES IN SCHEMA public
TO tech_fitness_owner;

CREATE USER brewery_finder_appuser WITH PASSWORD 'tech_fitness_appuser1';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO tech_fitness_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO tech_fitness_appuser;