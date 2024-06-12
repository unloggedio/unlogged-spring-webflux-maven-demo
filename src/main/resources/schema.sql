USE unlogged;


CREATE TABLE IF NOT EXISTS players (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    age INT,
    PRIMARY KEY (id)
);