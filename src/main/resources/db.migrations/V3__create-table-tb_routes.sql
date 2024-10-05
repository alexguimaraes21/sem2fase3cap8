CREATE TABLE tb_routes (
    route_id INT AUTO_INCREMENT NOT NULL,
    description TEXT NOT NULL,
    start_time DATE NOT NULL,
    end_time DATE,
    truck_id INT NOT NULL,
    CONSTRAINT pk_route PRIMARY KEY (route_id),
    CONSTRAINT fk_routes_truck_users FOREIGN KEY (truck_id) REFERENCES tb_trucks(truck_id)
);
