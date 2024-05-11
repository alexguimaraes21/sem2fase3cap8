package br.com.fiap.atvcap8.repositories;

import br.com.fiap.atvcap8.models.ContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<ContainerModel, Long> {
}
