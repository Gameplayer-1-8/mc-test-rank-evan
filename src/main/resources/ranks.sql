CREATE TABLE ranks (
    UUID VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(16) NOT NULL,
    rank LONGTEXT NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);