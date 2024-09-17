package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email); // JPA Query Method. It will be implemented by Spring Data JPA
}
