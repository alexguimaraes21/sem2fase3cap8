CREATE SEQUENCE seq_user
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE tb_users
(
    user_id                 INTEGER       DEFAULT seq_user.NEXTVAL NOT NULL,
    name                    VARCHAR2(255)                          NOT NULL,
    email                   VARCHAR2(255)                          NOT NULL,
    password                VARCHAR2(150)                          NOT NULL,
    role                    VARCHAR2(150) DEFAULT ON NULL 'USER'   NOT NULL,
    account_non_expired     NUMBER(1, 0)  DEFAULT ON NULL 1        NOT NULL,
    credentials_non_expired NUMBER(1, 0)  DEFAULT ON NULL 1        NOT NULL,
    account_non_locked      NUMBER(1, 0)  DEFAULT ON NULL 1        NOT NULL,
    enabled                 NUMBER(1, 0)  DEFAULT ON NULL 1        NOT NULL
);

ALTER TABLE tb_users
    ADD CONSTRAINT pk_user PRIMARY KEY (user_id);

ALTER TABLE tb_users
    ADD CONSTRAINT unq_user_email UNIQUE (email);