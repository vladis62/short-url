CREATE TABLE IF NOT EXISTS urls
(
    id             BIGSERIAL  PRIMARY KEY,
    hash           VARCHAR(7) NOT NULL,
    original_url   TEXT       NOT NULL,
    expiration     TIMESTAMP,
    create_date    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL
);