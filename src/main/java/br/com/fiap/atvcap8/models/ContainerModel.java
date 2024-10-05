package br.com.fiap.atvcap8.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_containers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "container_id")
    private Long id;

    private String location;

    private Float capacity;

    @Column(name = "current_level")
    private Integer currentLevel;
}
