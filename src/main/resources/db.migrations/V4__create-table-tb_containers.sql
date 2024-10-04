CREATE TABLE tb_containers (
    container_id INT AUTO_INCREMENT NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity FLOAT DEFAULT 0.0 NOT NULL,
    current_level INT NOT NULL,
    PRIMARY KEY (container_id)
);
