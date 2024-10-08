package br.com.fiap.atvcap8.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_collections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CollectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private ContainerModel container;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteModel route;
}
