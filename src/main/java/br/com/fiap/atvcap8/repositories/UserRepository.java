package br.com.fiap.atvcap8.repositories;

import br.com.fiap.atvcap8.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    Optional<UserModel> findModelByEmail(String email);
}
