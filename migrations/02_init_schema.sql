CREATE TABLE users (
                       id VARCHAR(25) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       role     VARCHAR(50)  NOT NULL CHECK (role IN ('ADMIN', 'STUDENT', 'TEACHER', 'RESEARCHER', 'LIBRARIAN', 'MANAGER',
                                                                      'RECTOR', 'EMPLOYEE')),
                       iin CHAR(12) NOT NULL UNIQUE,
                       login VARCHAR(80) NOT NULL UNIQUE,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(60) NOT NULL,
                       email VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE courses
(
    id        VARCHAR(8) PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    period    VARCHAR(6)   NOT NULL,
    year      INTEGER      NOT NULL,
    credits   INTEGER      NOT NULL,
    teacher_id VARCHAR(25) REFERENCES users (id),
    CHECK ( period IN ('spring', 'fall') )
);

CREATE TABLE students (
                          student_id VARCHAR(25) PRIMARY KEY,
                          year_study INTEGER NOT NULL,
                          is_expelled BOOLEAN DEFAULT FALSE,
                          school VARCHAR(6) NOT NULL,
                          user_id VARCHAR(25) UNIQUE REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE teachers (
                          id VARCHAR(25) PRIMARY KEY,
                          user_id  VARCHAR(25) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          department VARCHAR(100) NOT NULL
);

CREATE TABLE course_student (
                                id SERIAL PRIMARY KEY,
                                course_id VARCHAR(8) NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                                student_id VARCHAR(25) NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
                                enrolled_at TIMESTAMP DEFAULT NOW(),
                                UNIQUE (course_id, student_id)
);

CREATE TABLE course_grades (
                               id SERIAL PRIMARY KEY,
                               student_id VARCHAR(25) NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
                               course_id VARCHAR(8) NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                               grade NUMERIC(5, 2),
                               date_assigned DATE DEFAULT CURRENT_DATE,
                               status VARCHAR(20) DEFAULT 'Incomplete',
                               UNIQUE (student_id, course_id)
);

CREATE TABLE course_sessions (
                                 id SERIAL PRIMARY KEY,
                                 course_id VARCHAR(8) NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                                 teacher_id VARCHAR(25) NOT NULL REFERENCES teachers(id) ON DELETE CASCADE,
                                 session_type VARCHAR(20) NOT NULL CHECK (session_type IN ('L', 'P')),
                                 day_of_week VARCHAR(15) NOT NULL CHECK (day_of_week IN ('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun')),
                                 start_time TIME NOT NULL,
                                 end_time TIME NOT NULL,
                                 room VARCHAR(10) NOT NULL,
                                 capacity INTEGER NOT NULL,
                                 UNIQUE (course_id, teacher_id, session_type, day_of_week, start_time, room)
);

CREATE TABLE semesters (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           start_date DATE NOT NULL,
                           end_date DATE NOT NULL
);

CREATE TABLE assessments (
                             id SERIAL PRIMARY KEY,
                             student_id VARCHAR(25) NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
                             course_id VARCHAR(8) NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                             semester_id INTEGER NOT NULL REFERENCES semesters(id) ON DELETE CASCADE,
                             grade NUMERIC(5, 2) NOT NULL,
                             letter_grade VARCHAR(5),
                             gpa NUMERIC(3, 2),
                             ects_credits NUMERIC(4, 2),
                             status VARCHAR(20) DEFAULT 'Incomplete',
                             UNIQUE (student_id, course_id, semester_id)
);


CREATE TABLE session_registrations (
                                       id SERIAL PRIMARY KEY,
                                       session_id INTEGER NOT NULL REFERENCES course_sessions(id) ON DELETE CASCADE,
                                       student_id VARCHAR(25) NOT NULL REFERENCES students(student_id) ON DELETE CASCADE,
                                       registered_at TIMESTAMP DEFAULT NOW(),
                                       UNIQUE (session_id, student_id)
);


CREATE TABLE news (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      is_pinned BOOLEAN DEFAULT FALSE,
                      created_at TIMESTAMP DEFAULT NOW(),
                      author_id VARCHAR(25) REFERENCES users(id)
);

CREATE OR REPLACE FUNCTION check_session_capacity()
    RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM session_registrations WHERE session_id = NEW.session_id) >=
       (SELECT capacity FROM course_sessions WHERE id = NEW.session_id) THEN
        RAISE EXCEPTION 'Превышен лимит регистраций для этого занятия';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_max_pinned_news()
    RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.is_pinned = TRUE) THEN
        IF (SELECT COUNT(*) FROM news WHERE is_pinned = TRUE) >= 5 THEN
            RAISE EXCEPTION 'Нельзя закрепить больше 5 новостей';
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_max_pinned_news_trigger
    BEFORE INSERT OR UPDATE ON news
    FOR EACH ROW
EXECUTE FUNCTION check_max_pinned_news();

CREATE TRIGGER check_session_capacity_trigger
    BEFORE INSERT ON session_registrations
    FOR EACH ROW
EXECUTE FUNCTION check_session_capacity();


CREATE OR REPLACE FUNCTION check_teacher_role()
    RETURNS TRIGGER AS
$$
BEGIN
    IF (NEW.teacher_id IS NOT NULL) THEN
        IF (SELECT role FROM users WHERE id = NEW.teacher_id) != 'TEACHER' THEN
            RAISE EXCEPTION 'User must have the role TEACHER to be assigned to a course';
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_teacher_role_trigger
    BEFORE INSERT OR UPDATE
    ON courses
    FOR EACH ROW
EXECUTE FUNCTION check_teacher_role();