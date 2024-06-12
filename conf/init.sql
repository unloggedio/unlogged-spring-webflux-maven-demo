-- Create the `unlogged` database if it doesn't exist
CREATE DATABASE IF NOT EXISTS unlogged;

-- Use the `unlogged` database
USE unlogged;

-- Create the `players` table if it doesn't exist
CREATE TABLE IF NOT EXISTS players (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    age INT,
    PRIMARY KEY (id)
);
