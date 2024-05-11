package br.com.fiap.atvcap8.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "TB_ROUTES")
public class RouteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROUTE")
    @SequenceGenerator(name = "SEQ_ROUTE", sequenceName = "SEQ_ROUTE", allocationSize = 1)
    @Column(name = "route_id")
    private Long id;
    private String description;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private TruckModel truck;
}
