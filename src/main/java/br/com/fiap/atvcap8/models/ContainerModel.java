package br.com.fiap.atvcap8.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CONTAINERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTAINER")
    @SequenceGenerator(name = "SEQ_CONTAINER", sequenceName = "SEQ_CONTAINER", allocationSize = 1)
    @Column(name = "container_id")
    private Long id;
    private String location;
    private Float capacity;
    @Column(name = "current_level")
    private Integer currentLevel;
}
