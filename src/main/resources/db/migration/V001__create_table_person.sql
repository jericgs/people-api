CREATE TABLE IF NOT EXISTS person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(60) NOT NULL,
    date_birth TIMESTAMP NOT NULL
);