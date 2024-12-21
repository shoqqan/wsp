CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50)
);

CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         credits INTEGER NOT NULL
);

CREATE TABLE news (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      is_pinned BOOLEAN DEFAULT FALSE,
                      created_at TIMESTAMP DEFAULT NOW(),
                      author_id INTEGER REFERENCES users(id)
);

-- Создание функции для проверки ограничения на количество закрепленных новостей
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
