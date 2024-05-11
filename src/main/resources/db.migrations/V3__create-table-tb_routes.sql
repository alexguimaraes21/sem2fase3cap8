CREATE SEQUENCE seq_route
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE tb_routes
(
    route_id    INTEGER DEFAULT seq_route.NEXTVAL NOT NULL,
    description CLOB                              NOT NULL,
    start_time  DATE                              NOT NULL,
    end_time    DATE,
    truck_id    INTEGER                           NOT NULL
);

ALTER TABLE tb_routes
    ADD CONSTRAINT pk_route PRIMARY KEY (route_id);

ALTER TABLE tb_routes
    ADD CONSTRAINT fk_routes_truck_users FOREIGN KEY (truck_id)
        REFERENCES tb_trucks (truck_id);