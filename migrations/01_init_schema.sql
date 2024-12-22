CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    role     VARCHAR(50)  NOT NULL CHECK (role IN ('ADMIN', 'STUDENT', 'TEACHER', 'RESEARCHER', 'LIBRARIAN', 'MANAGER',
                                                   'RECTOR', 'EMPLOYEE'))
);

CREATE TABLE courses
(
    id        VARCHAR(8) PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    period    VARCHAR(6)   NOT NULL,
    year      INTEGER      NOT NULL,
    credits   INTEGER      NOT NULL,
    teacherId INTEGER REFERENCES users (id),
    CHECK ( period IN ('spring', 'fall') )
);

CREATE TABLE news
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    is_pinned  BOOLEAN   DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    author_id  INTEGER REFERENCES users (id)
);


-- Создание функции для проверки ограничения на количество закрепленных новостей
CREATE OR REPLACE FUNCTION check_max_pinned_news()
    RETURNS TRIGGER AS
$$
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
    BEFORE INSERT OR UPDATE
    ON news
    FOR EACH ROW
EXECUTE FUNCTION check_max_pinned_news();

-- Создание функции для проверки роли преподавателя
CREATE OR REPLACE FUNCTION check_teacher_role()
    RETURNS TRIGGER AS
$$
BEGIN
    IF (NEW.teacherId IS NOT NULL) THEN
        IF (SELECT role FROM users WHERE id = NEW.teacherId) != 'TEACHER' THEN
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