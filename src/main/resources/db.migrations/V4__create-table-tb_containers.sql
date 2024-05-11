CREATE SEQUENCE seq_container
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE tb_containers
(
    container_id  INTEGER DEFAULT seq_container.NEXTVAL NOT NULL,
    location      VARCHAR2(255)                         NOT NULL,
    capacity      FLOAT   DEFAULT 0.0                   NOT NULL,
    current_level INTEGER                               NOT NULL
);

ALTER TABLE tb_containers
    ADD CONSTRAINT pk_container PRIMARY KEY (container_id);