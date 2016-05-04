DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);

CREATE TABLE workplace (
	workplace_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	company_name VARCHAR(40),
	snippet VARCHAR(128)
)
