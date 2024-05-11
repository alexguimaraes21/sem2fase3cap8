package br.com.fiap.atvcap8.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "TB_TRUCKS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class TruckModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRUCK")
    @SequenceGenerator(name = "SEQ_TRUCK", sequenceName = "SEQ_TRUCK", allocationSize = 1)
    @Column(name = "truck_id")
    private Long id;
    @Column(name = "license_plate")
    private String licensePlate;
    private Float capacity;
    private Boolean available;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "truck")
//    private List<RouteModel> routes;
}
