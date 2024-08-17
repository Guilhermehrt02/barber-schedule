package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
