CREATE SEQUENCE seq_truck
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE tb_trucks
(
    truck_id      INTEGER      DEFAULT seq_truck.NEXTVAL NOT NULL,
    license_plate CHAR(7)                                NOT NULL,
    capacity      FLOAT        DEFAULT 0.0               NOT NULL,
    available     NUMBER(1, 0) DEFAULT 1                 NOT NULL
);

ALTER TABLE tb_trucks
    ADD CONSTRAINT pk_truck PRIMARY KEY (truck_id);