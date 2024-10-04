CREATE TABLE tb_collections (
    collection_id INT AUTO_INCREMENT NOT NULL,
    date_time DATE NOT NULL,
    container_id INT NOT NULL,
    route_id INT NOT NULL,
    PRIMARY KEY (collection_id),
    FOREIGN KEY (container_id) REFERENCES tb_containers(container_id),
    FOREIGN KEY (route_id) REFERENCES tb_routes(route_id)
);
