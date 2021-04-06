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

CREATE TABLE Courses (
    prefix TEXT NOT NULL,
    course_id TEXT NOT NULL,
    student_id INTEGER NOT NULL,
    course_name TEXT NOT NULL,
    PRIMARY KEY (course_id),
    FOREIGN KEY (student_id) REFERENCES Students (student_id)
)
