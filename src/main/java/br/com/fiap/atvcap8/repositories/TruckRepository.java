package br.com.fiap.atvcap8.repositories;

import br.com.fiap.atvcap8.models.TruckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<TruckModel, Long> {

    Optional<TruckModel> findByLicensePlate(String licensePlate);
}
