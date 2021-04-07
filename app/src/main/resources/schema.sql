CREATE TABLE Students (
    student_id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT UNIQUE,
    gender TEXT,
    age INTEGER CHECK (age > 0),
    classification TEXT NOT NULL,
    PRIMARY KEY (student_id)
);