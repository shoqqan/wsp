INSERT INTO users (id, password, role, iin, login, first_name, last_name, email)
VALUES ('18ADM030317', 'admin123', 'ADMIN', '123456789012', 'admin', 'Admin', 'User', 'admin@example.com'),
       ('18SL030318', 'teach123', 'TEACHER', '123456789013', 'teacher', 'John', 'Smith', 'teacher@example.com'),
       ('18PRF030319', 'teach456', 'TEACHER', '123456789346', 'teacher2', 'Liza', 'Smith', 'teacher2@example.com'),

       ('23B030317', 'stud123', 'STUDENT', '123456789014', 'student', 'Alice', 'Johnson', 'student@example.com');

INSERT INTO courses (id, title, period, year, credits)
VALUES ('CS101', 'Introduction to Computer Science', 'fall', 2023, 3),
       ('MATH201', 'Calculus I', 'spring', 2023, 4),
       ('PHYS101', 'Physics I', 'fall', 2023, 4),
       ('ENG101', 'English Composition', 'spring', 2023, 3),
       ('HIST101', 'World History', 'fall', 2023, 3);
-- INSERT INTO courses (title, credits) VALUES
-- ('Introduction to Programming 1', 3),
-- ('Algorithms and Data Structures', 4),
-- ('Object Oriented Programming and Design', 4),
-- ('Databases', 4);

INSERT INTO students (student_id, year_study, is_expelled, school, user_id)
VALUES ((SELECT id FROM users WHERE login = 'student'), 1, FALSE, 'CS', (SELECT id FROM users WHERE login = 'student'));


INSERT INTO teachers(id, user_id, department)
VALUES ((SELECT id FROM users where login = 'teacher'), (SELECT id FROM users where login = 'teacher'), 'CS'),
       ((SELECT id FROM users where login = 'teacher2'), (SELECT id FROM users where login = 'teacher2'), 'CS');

INSERT INTO semesters (name, start_date, end_date)
VALUES ('2023-2024 Fall', '2023-09-01', '2023-12-31'),
       ('2023-2024 Spring', '2024-01-15', '2024-05-15');

INSERT INTO course_sessions (course_id, teacher_id, session_type, day_of_week, start_time, end_time, room, capacity)
VALUES ('CS101', (SELECT id FROM users where login = 'teacher2'), 'L', 'Mon', '12:00:00', '14:00:00', '424', 200),
       ('CS101', (SELECT id FROM users where login = 'teacher'), 'P', 'Mon', '16:00:00', '17:00:00', '202', 30),
       ('CS101', (SELECT id FROM users where login = 'teacher2'), 'L', 'Tue', '12:00:00', '14:00:00', '424', 200),
       ('CS101', (SELECT id FROM users where login = 'teacher'), 'P', 'Tue', '16:00:00', '17:00:00', '202', 30);

INSERT INTO session_registrations (session_id, student_id)
VALUES (1, (SELECT id FROM users where login = 'student')),
-- (1, 2),
       (2, (SELECT id FROM users where login = 'student'));
-- (2, 3);

INSERT INTO assessments (student_id, course_id, semester_id, grade, letter_grade, gpa, ects_credits, status)
VALUES ((SELECT id FROM users where login = 'student'), 'CS101', 1, 85.00, 'B', 3.33, 5.0, 'Completed');
-- (2, 2, 1, 92.00, 'A', 4.00, 6.0, 'Completed'),
-- (3, 3, 2, 70.00, 'C', 2.67, 4.0, 'Completed');

INSERT INTO news (title, content, is_pinned, author_id)
VALUES ('Welcome to the Semester', 'The semester starts on September 1st!', TRUE,
        (SELECT id FROM users where login = 'admin')),
       ('Holiday Schedule', 'There will be no classes during the holidays.', TRUE,
        (SELECT id FROM users where login = 'admin')),
       ('New Library Hours', 'Library hours have been extended for exam week.', TRUE,
        (SELECT id FROM users where login = 'admin')),
       ('Research Opportunities', 'New research projects are available.', FALSE,
        (SELECT id FROM users where login = 'admin')),
       ('Career Fair', 'Join us for the annual career fair on October 15th.', FALSE,
        (SELECT id FROM users where login = 'admin'));
