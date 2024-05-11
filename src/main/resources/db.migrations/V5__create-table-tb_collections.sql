CREATE SEQUENCE seq_collection
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE tb_collections
(
    collection_id INTEGER DEFAULT seq_collection.NEXTVAL NOT NULL,
    date_time     DATE                                   NOT NULL,
    container_id  INTEGER                                NOT NULL,
    route_id      INTEGER                                NOT NULL
);

ALTER TABLE tb_collections
    ADD CONSTRAINT pk_collection PRIMARY KEY (container_id);

ALTER TABLE tb_collections
    ADD CONSTRAINT fk_collections_container_containers FOREIGN KEY (container_id)
        REFERENCES tb_containers (container_id);

ALTER TABLE tb_collections
    ADD CONSTRAINT fk_collections_route_routes FOREIGN KEY (route_id)
        REFERENCES tb_routes (route_id);