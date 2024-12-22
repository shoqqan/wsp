INSERT INTO
    users (username, password, email, role)
VALUES
    ('admin', 'admin123', 'admin@kbtu.kz', 'ADMIN'),
    (
        'teacher',
        'teach123',
        'teacher@kbtu.kz',
        'TEACHER'
    ),
    (
        'student',
        'stud123',
        'student@kbtu.kz',
        'STUDENT'
    );

INSERT INTO
    courses (title, credits)
VALUES
    ('Introduction to Programming', 3),
    ('Data Structures', 4),
    ('Database Systems', 4);

INSERT INTO
    news (title, content, is_pinned, author_id)
VALUES
    ('Пин', '1 пин', TRUE, 1),
    ('Пин', '2 пин', TRUE, 1),
    ('Пин', '3 пин', TRUE, 1),
    ('Пин', '4 пин', TRUE, 1),
    ('Пин', '5 пин', TRUE, 1),
    ('Пин', '1 непин', FALSE, 1),
    ('Пин', '2 непин', FALSE, 1),
    ('Пин', '3 непин', FALSE, 1),
    ('Пин', '4 непин', FALSE, 1),
    ('Пин', '5 непин', FALSE, 1),
    ('Пин', '6 непин', FALSE, 1),
    ('Пин', '7 непин', FALSE, 1),
    ('Пин', '8 непин', FALSE, 1),
    ('Пин', '9 непин', FALSE, 1),
    ('Пин', '10 непин', FALSE, 1),
    ('Пин', '11 непин', FALSE, 1),
    ('Пин', '12 непин', FALSE, 1),
    ('Пин', '13 непин', FALSE, 1),
    ('Пин', '14 непин', FALSE, 1),
    ('НеПин', '15 непин', FALSE, 1),
    ('НеПин', '1 непин', FALSE, 1),
    ('НеПин', '2 непин', FALSE, 1),
    ('НеПин', '3 непин', FALSE, 1),
    ('НеПин', '4 непин', FALSE, 1),
    ('НеПин', '5 непин', FALSE, 1),
    ('НеПин', '6 непин', FALSE, 1),
    ('НеПин', '7 непин', FALSE, 1),
    ('НеПин', '8 непин', FALSE, 1),
    ('НеПин', '9 непин', FALSE, 1),
    ('НеПин', '10 непин', FALSE, 1),
    ('НеПин', '11 непин', FALSE, 1),
    ('НеПин', '12 непин', FALSE, 1),
    ('НеПин', '13 непин', FALSE, 1),
    ('НеПин', '14 непин', FALSE, 1),
    ('НеПин', '15 непин', FALSE, 1);