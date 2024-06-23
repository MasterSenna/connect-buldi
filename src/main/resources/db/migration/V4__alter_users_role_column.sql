-- V4__alter_users_role_column.sql
ALTER TABLE users
ALTER COLUMN role SET DATA TYPE smallint
USING role::smallint;
