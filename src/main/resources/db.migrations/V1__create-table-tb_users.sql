CREATE TABLE tb_users (
    user_id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(150) NOT NULL,
    role VARCHAR(150) DEFAULT 'USER' NOT NULL,
    account_non_expired INT DEFAULT 1 NOT NULL,
    credentials_non_expired INT DEFAULT 1 NOT NULL,
    account_non_locked INT DEFAULT 1 NOT NULL,
    enabled INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);
